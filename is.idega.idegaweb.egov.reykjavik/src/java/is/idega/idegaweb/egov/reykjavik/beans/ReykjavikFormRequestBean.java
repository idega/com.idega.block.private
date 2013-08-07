package is.idega.idegaweb.egov.reykjavik.beans;

import is.idega.idegaweb.egov.bpm.cases.exe.CaseIdentifier;
import is.idega.idegaweb.egov.cases.presentation.CaseViewer;
import is.idega.idegaweb.egov.reykjavik.ReykjavikConstants;
import is.idega.idegaweb.egov.reykjavik.data.ReykjavikGeneralData;
import is.idega.idegaweb.egov.reykjavik.data.dao.JBPMVariablesDataDAO;
import is.idega.idegaweb.egov.reykjavik.process.view.DefaultSocialServiceProcessView;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

import org.jbpm.graph.exe.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.idega.core.business.DefaultSpringBean;
import com.idega.idegaweb.IWResourceBundle;
import com.idega.jbpm.exe.BPMFactory;
import com.idega.jbpm.exe.ProcessConstants;
import com.idega.jbpm.exe.ProcessDefinitionW;
import com.idega.jbpm.exe.ProcessInstanceW;
import com.idega.jbpm.exe.ProcessManager;
import com.idega.jbpm.exe.TaskInstanceW;
import com.idega.jbpm.variables.BinaryVariable;
import com.idega.presentation.IWContext;
import com.idega.user.bean.UserDataBean;
import com.idega.user.business.UserApplicationEngine;
import com.idega.user.data.User;
import com.idega.util.CoreConstants;
import com.idega.util.CoreUtil;
import com.idega.util.StringUtil;
import com.idega.util.expression.ELUtil;

@Service(ReykjavikFormRequestBean.BEAN_NAME)
@Scope("request")
public class ReykjavikFormRequestBean extends DefaultSpringBean {
	
	public static final String BEAN_NAME = "reykjavikFormRequestBean";
	
	private IWContext iwc = null;
	
	private Long taskInstanceId = null;
	
	private boolean pdf = false;
	
	public boolean isPdf() {
		return pdf;
	}

	public void setPdf(boolean isPdf) {
		this.pdf = isPdf;
	}
	@Autowired
	private BPMFactory bpmFactory;
	
	@Autowired
	private CaseIdentifier caseIdentifier;
	
	@Autowired
	private JBPMVariablesDataDAO jBPMVariablesDataDAO;
	
	private Object[] identifiers = null;
	
	private Integer caseId;
	
	private ReykjavikGeneralData data = null;

	private Class<? extends ReykjavikGeneralData> dataClass = ReykjavikGeneralData.class;

	private DefaultSocialServiceProcessView view;

	public Class<? extends ReykjavikGeneralData> getDataClass() {
		return dataClass;
	}

	public void setDataClass(Class<? extends ReykjavikGeneralData> dataClass) {
		this.dataClass = dataClass;
	}
	
	protected JBPMVariablesDataDAO getJBPMVariablesDataDAO() {
		if (jBPMVariablesDataDAO == null) {
			ELUtil.getInstance().autowire(this);
		}
		
		return jBPMVariablesDataDAO;
	}
	
	protected BPMFactory getBPMFactory() {
		if (bpmFactory == null) {
			ELUtil.getInstance().autowire(this);
		}
		
		return bpmFactory;
	}

	/**
	 * 
	 * @return converted current {@link User};
	 * @author <a href="mailto:martynas@idega.com">Martynas Stakė</a>
	 */
	protected is.idega.idegaweb.egov.reykjavik.data.ReykjavikGeneralData getDefaultData() {
		try {
			ReykjavikGeneralData reykjavikGeneralData = getDataClass().newInstance();
			reykjavikGeneralData.setUserDataBean(getUserDataBean());
			return reykjavikGeneralData;
		}catch (Exception e) {
			getLogger().log(Level.WARNING, "Failed creating instance of class: " + getDataClass(), e);
		}
		
		return null;
	}
	
	public UserDataBean getUserDataBean(){
		UserApplicationEngine userApplicationEngine = ELUtil.getInstance().getBean(UserApplicationEngine.class);
		UserDataBean userDataBean = userApplicationEngine.getUserInfo(getCurrentUser());
		return userDataBean;
	}
	/**
	 * 
	 * <p>Gets {@link User} from jBPM process instance of current user.</p>
	 * @return user from {@link ProcessInstance} or current {@link User},
	 * <code>null</code>;
	 * @author <a href="mailto:martynas@idega.com">Martynas Stakė</a>
	 */
	public ReykjavikGeneralData getData() {
		if (data != null) {
			return data;
		}
		
		data = getDefaultData();
		if (getTaskInstanceId() == null) {
			return data;
		}
		
		TaskInstanceW taskInstanceW = getBPMFactory().getTaskInstanceW(getTaskInstanceId());
		if (taskInstanceW == null) {
			return data;
		}

		ProcessInstanceW processInstance = taskInstanceW.getProcessInstanceW();
		if (processInstance == null) {
			return data;
		}

		data = getJBPMVariablesDataDAO().loadData(
				processInstance.getProcessInstanceId(), 
				getDataClass());
		List<BinaryVariable> attachments = taskInstanceW.getAttachments();
		data.setAttachments(attachments);

		return data;
	}
	
	public Integer getCaseId() {
		if(caseId == null){
			String id = getIwc().getParameter(CaseViewer.PARAMETER_CASE_PK);
			if(id == null){
				return null;
			}
			try{
				caseId = Integer.valueOf(id);
			}catch (NumberFormatException e) {
				return null;
			}
		}
		return caseId;
	}

	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
	}
	
	/**
	 * 
	 * @return Process name and user name
	 * @author <a href="mailto:martynas@idega.is">Martynas Stakė</a>
	 */
	public String getCaseDescription() {
		return getProcessName() + CoreConstants.COLON + 
				CoreConstants.SPACE + getData().getName();
	}
	
	public String getProcessName() {
		String processDefinitionId = getIwc().getParameter(ProcessConstants.PROCESS_DEFINITION_ID);
		Long pdId;
		if(StringUtil.isEmpty(processDefinitionId)){
			Long taskInstanceId = getTaskInstanceId();
			if(taskInstanceId == null){
				getLogger().warning("processDefinitionId is not defined");
				return CoreConstants.EMPTY;
			}
			ProcessManager processManager = bpmFactory.getProcessManagerByTaskInstanceId(taskInstanceId);
			TaskInstanceW taskInstanceW = processManager.getTaskInstance(taskInstanceId);
			pdId = taskInstanceW.getProcessInstanceW().getProcessDefinitionW().getProcessDefinitionId();
		}else{
			pdId = Long.valueOf(processDefinitionId);
		}
		ProcessManager processManager = bpmFactory.getProcessManager(pdId);
		ProcessDefinitionW pdw = processManager.getProcessDefinition(pdId);
		String processName = pdw.getProcessDefinition().getName();
		processName = getIwrb(getIwc()).getLocalizedString(processName.toLowerCase(), processName);
		return processName;
	}
	
	public IWContext getIwc() {
		if(iwc == null){
			iwc = CoreUtil.getIWContext();
		}
		return iwc;
	}

	private Object[] getIdentifiers(){
		if(identifiers == null){
			identifiers = caseIdentifier.generateNewCaseIdentifier();
		}
		return identifiers;
	}
	public String getCaseIdentifierNumber(){
		return String.valueOf(getIdentifiers()[0]);
	}
	public String getCaseIdentifier(){
		return String.valueOf(getIdentifiers()[1]);
	}

	public void setTaskInstanceId(Long taskInstanceId) {
		this.taskInstanceId = taskInstanceId; 
	}

	public Long getTaskInstanceId() {
		return taskInstanceId;
	}
	
	
	public String getViewType() {
		if (view == null) {
			return null;
		}
		
		return view.getViewType();
	}
	
	public String getViewId(){
		if (view == null) {
			return null;
		}
		
		return view.getViewId();
	}
	
	public DefaultSocialServiceProcessView getView() {
		return view;
	}

	public void setView(DefaultSocialServiceProcessView view) {
		this.view = view;
	}
	
	public boolean isSubmitted() {
		if (view == null) {
			return false;
		}
		
		return view.isSubmitted();
	}
	
	public boolean isReadOnly() {
		return isSubmitted() || isPdf();
	}

	public List<String> getWeekDays(){
		IWResourceBundle iwrb = getIwrb(getIwc());
		List<String> weekdays = Arrays.asList(
				iwrb.getLocalizedString("sunday", "Sunday"),
				iwrb.getLocalizedString("monday", "Monday"),
				iwrb.getLocalizedString("tuesday", "Tuesday"),
				iwrb.getLocalizedString("wednesday", "Wednesday"),
				iwrb.getLocalizedString("thursday", "Thursday"),
				iwrb.getLocalizedString("friday", "Friday"),
				iwrb.getLocalizedString("saturday", "Saturday")
		);
		return weekdays;
	}
	private IWResourceBundle getIwrb(IWContext iwc) {
		IWResourceBundle iwrb = iwc.getIWMainApplication().getBundle(ReykjavikConstants.IW_BUNDLE_IDENTIFIER).getResourceBundle(iwc);
		return iwrb;
	}
	public List<String> getSocialServices(){
		List<String> services = Arrays.asList(
				"home_services",
				"daycare",
				"food_delivery",
				"relatives_assist",
				"nursing_homes",
				"transport_services"
		);
		return services;
	}
}
