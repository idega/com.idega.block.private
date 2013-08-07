package is.idega.idegaweb.egov.reykjavik.beans;

import is.idega.idegaweb.egov.bpm.cases.CasesBPMProcessConstants;
import is.idega.idegaweb.egov.cases.presentation.CaseViewer;
import is.idega.idegaweb.egov.reykjavik.ReykjavikConstants;
import is.idega.idegaweb.egov.reykjavik.business.ApartmentServicesSubmission;
import is.idega.idegaweb.egov.reykjavik.business.EveningAndWeekendServicesSubmission;
import is.idega.idegaweb.egov.reykjavik.business.HomeServicesSubmission;
import is.idega.idegaweb.egov.reykjavik.business.ReykjavikServicesViewSubmission;
import is.idega.idegaweb.egov.reykjavik.process.view.ApartmentServiceView;
import is.idega.idegaweb.egov.reykjavik.process.view.EveningAndWeekendServiceView;
import is.idega.idegaweb.egov.reykjavik.process.view.HomeServicesView;

import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.directwebremoting.annotations.Param;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.spring.SpringCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.idega.block.process.variables.Variable;
import com.idega.block.process.variables.VariableDataType;
import com.idega.builder.bean.AdvancedProperty;
import com.idega.business.IBOLookup;
import com.idega.core.business.DefaultSpringBean;
import com.idega.dwr.business.DWRAnnotationPersistance;
import com.idega.idegaweb.IWResourceBundle;
import com.idega.idegaweb.egov.bpm.data.CaseProcInstBind;
import com.idega.idegaweb.egov.bpm.data.dao.CasesBPMDAO;
import com.idega.jbpm.artifacts.presentation.ProcessArtifacts;
import com.idega.jbpm.events.ProcessInstanceCreatedEvent;
import com.idega.jbpm.exe.BPMFactory;
import com.idega.jbpm.exe.ProcessConstants;
import com.idega.jbpm.exe.ProcessDefinitionW;
import com.idega.jbpm.exe.ProcessInstanceW;
import com.idega.jbpm.exe.TaskInstanceW;
import com.idega.presentation.IWContext;
import com.idega.presentation.ui.handlers.IWDatePickerHandler;
import com.idega.slide.business.IWSlideService;
import com.idega.user.business.UserBusiness;
import com.idega.user.data.User;
import com.idega.util.CoreUtil;
import com.idega.util.ListUtil;
import com.idega.util.StringUtil;
import com.idega.util.datastructures.map.MapUtil;
import com.idega.util.expression.ELUtil;


@Service(ReykjavikServices.BEAN_NAME)
@Scope(BeanDefinition.SCOPE_SINGLETON)
@RemoteProxy(creator=SpringCreator.class, creatorParams={
	@Param(name="beanName", value=ReykjavikServices.BEAN_NAME),
	@Param(name="javascript", value="ReykjavikServices")
}, name="ReykjavikServices")
public class ReykjavikServices extends DefaultSpringBean implements DWRAnnotationPersistance {
	public static final String BEAN_NAME = "reykjavikServices";
	
	private Logger logger = Logger.getLogger(ReykjavikServices.class.getName());
	
	@Autowired
	private BPMFactory bpmFactory;
	
	@Autowired
	private CasesBPMDAO casesBPMDAO;
	
	@Autowired
	private ProcessArtifacts processArtifacts;
	
//	private static String ATTACHMENTS_DIRECTORY = IWMainApplication.getDefaultIWMainApplication().getRealPath("/tmpFiles/xFormsAttachments");
	private long uploadId = 0;
	
	protected User getUser(String userId) {
		if (StringUtil.isEmpty(userId))
			return null;

		UserBusiness userBusiness = getServiceInstance(UserBusiness.class);
		try {
			Integer id = Integer.valueOf(userId);
			return userBusiness.getUser(id);
		} catch (NumberFormatException e) {
		} catch (RemoteException e) {}
		
		return null;
	}
	
	private IWResourceBundle getIwrb(IWContext iwc) {
		IWResourceBundle iwrb = iwc.getIWMainApplication().getBundle(ReykjavikConstants.IW_BUNDLE_IDENTIFIER).getResourceBundle(iwc);
		return iwrb;
	}
	public ReykjavikServicesViewSubmission getViewSubmission(String viewId){
		if(viewId.equals(EveningAndWeekendServiceView.RESOURCE_IDENTIFIER)){
			return new EveningAndWeekendServicesSubmission();
		}else if(viewId.equals(HomeServicesView.RESOURCE_IDENTIFIER)){
			return new HomeServicesSubmission();
		}else if(viewId.equals(ApartmentServiceView.RESOURCE_IDENTIFIER)){
			return new ApartmentServicesSubmission();
		}
		return new ReykjavikServicesViewSubmission();
	}
	@RemoteMethod
	public Map<String,  List<String>> saveProcess(Map<String,List<AdvancedParameter>> advancedParameters,Map<String, List<String>> parameters){
		IWContext iwc = CoreUtil.getIWContext();
		IWResourceBundle iwrb = getIwrb(iwc);
		boolean result = false;
		List<String> params;
		String viewId = parameters.remove(ProcessConstants.VIEW_ID).get(0);
		String viewType = parameters.remove(ProcessConstants.VIEW_TYPE).get(0);
		
		Long processDefinitionId;
		try {
			processDefinitionId = Long.valueOf(parameters.remove(ProcessConstants.PROCESS_DEFINITION_ID).get(0));
		} catch (Exception e) {
			processDefinitionId = null;
		}
		List<AdvancedParameter> files = new ArrayList<AdvancedParameter>();
		params = parameters.remove(CaseViewer.PARAMETER_CASE_PK);
		ReykjavikServicesViewSubmission viewSubmission = null;
		try {
			String fileParameter = null;
			for(String variableName : advancedParameters.keySet()){
				if(variableName.startsWith("files_")){
					fileParameter = variableName;
					break;
				}
			}
			if(fileParameter != null){
				List<AdvancedParameter> fileDatas = advancedParameters.remove(fileParameter);
				if(!ListUtil.isEmpty(fileDatas)){
					files.addAll(fileDatas);
				}
			}
			Map<String,Object> variables = getVariables(parameters,advancedParameters);
			if(params == null){
				params = parameters.get(CasesBPMProcessConstants.userIdActionVariableName);
				String userUUID = params.get(0);
				User creator = getUser(userUUID);
				if (creator == null) {
					logger.warning("Unable to find user by provided ID: " + userUUID);
					return parameters;
				}
				
				viewSubmission = getViewSubmission(viewId);

				viewSubmission.populateVariables(variables);
				
				Map<String, String> jbpmParameters = getJBPMParameters(parameters);
				viewSubmission.populateParameters(jbpmParameters);
				viewSubmission.setProcessDefinitionId(processDefinitionId);
				
				viewSubmission.setViewId(viewId);
				viewSubmission.setViewType(viewType);
				
				ProcessDefinitionW pdw = bpmFactory.getProcessManager(processDefinitionId)
						.getProcessDefinition(processDefinitionId);
				String processDefinitionName = pdw.getProcessDefinition().getName();
				Long piId = pdw.startProcess(viewSubmission);
				if (piId == null) {
					result = false;
					throw new RuntimeException("Process instance id can not be null");
				}
	
				CaseProcInstBind bind = casesBPMDAO.getCaseProcInstBindByProcessInstanceId(piId);
				params = new ArrayList<String>(1);
				params.add(String.valueOf(bind.getCaseId()));
				parameters.put("case-id", params);
				ProcessInstanceW piw = bpmFactory.getProcessInstanceW(bind.getProcInstId());
				params = new ArrayList<String>(1);
				params.add(String.valueOf(piw.getStartTaskInstance().getTaskInstance().getId()));
				parameters.put(ProcessConstants.TASK_INSTANCE_ID, params);
				TaskInstanceW taskInstance = bpmFactory.getProcessInstanceW(piId).getStartTaskInstance();
				
				saveFiles(files, taskInstance, iwc);
				result = true;
				if (result && !StringUtil.isEmpty(processDefinitionName) && piId != null)
					ELUtil.getInstance().publishEvent(new ProcessInstanceCreatedEvent(processDefinitionName, piId));
			}else{
				Integer caseId = Integer.valueOf(params.get(0));
				Integer taskInstanceId = Integer.valueOf(parameters.get(ProcessConstants.TASK_INSTANCE_ID).get(0)); 
				TaskInstanceW taskInstanceW = bpmFactory.getTaskInstanceW(taskInstanceId);
				CaseProcInstBind bind = casesBPMDAO.getCaseProcInstBindByCaseId(caseId);
				ProcessInstanceW piw = bpmFactory.getProcessInstanceW(bind.getProcInstId());
				result =  piw.doSubmitSharedTask(taskInstanceW.getTaskInstance().getName(), variables);
				saveFiles(files, taskInstanceW, iwc);
			}
		} catch (Exception e) {
			try{
				logger.log(Level.WARNING, "Error creating issue by the provided data: " + parameters, e);
				if(viewSubmission != null){
					viewSubmission.revertChanges();
				}
			}catch (Exception fe) {
				logger.log(Level.WARNING, "Failed reverting changes after saving failure", fe);
			}
		} 
		params = new ArrayList<String>(1);
		params.add(String.valueOf(result));
		parameters.put("success", params);
		if(result){
			params = new ArrayList<String>(1);
			params.add(iwrb.getLocalizedString("sent", "Sent"));
			parameters.put("message", params);
			AdvancedProperty link = processArtifacts.getHomepageLinkAndLocalizedString();
			String redirectUri = (link != null) && (link.getId() != null) ? link.getId() : "/pages";
			params = new ArrayList<String>(1);
			params.add(redirectUri);
			parameters.put("redirect-uri", params);
		}else{
			params = new ArrayList<String>(1);
			params.add(iwrb.getLocalizedString("failed_saving", "Failed saving"));
			parameters.put("message", params);
		}
		return parameters;
	}
	
	@SuppressWarnings("unchecked")
	private void saveFiles(List<AdvancedParameter> files,TaskInstanceW taskInstance,IWContext iwc) throws Exception{
		for(AdvancedParameter file : files){
			String path;
			Object property = file.get("path");
			if(property instanceof List){
				path = ((List<String>) property).get(0);
			}else{
				path = (String) property;
			}
			if(StringUtil.isEmpty(path)){
				continue;
			}
			String name;
			property = file.get("name");
			if(property instanceof List){
				name = ((List<String>) property).get(0);
			}else{
				name = (String) property;
			}
			if(StringUtil.isEmpty(name)){
				String [] parts =  path.split("/");
				name = parts[parts.length-1];
			}
			Variable variable = new Variable("file_attachment", VariableDataType.FILE);
			IWSlideService iwSlideService = IBOLookup.getServiceInstance(iwc, IWSlideService.class);
			InputStream is = iwSlideService.getInputStream(path);
			taskInstance.addAttachment(variable,name, name, is);
//			int folderEnd = path.lastIndexOf('/');
//			String folder = path.substring(0, folderEnd+1);
//			taskInstance.addAttachment(variable,name, name, is,folder,false); TODO: create this
		}
	}
	
	private Map<String,String> getJBPMParameters(Map<String,List<String>> parameters){
		HashMap<String, String> jbpmParameters = new HashMap<String, String>();
		Set<String> keysSet = parameters.keySet();
		for(String key : keysSet){
			List<String> params = parameters.get(key);
			if(params.size() == 1){
				String parameter = params.get(0);
				jbpmParameters.put(key, parameter);
			}
		}
		return jbpmParameters;
	}
	
	/**
	 * Converts elements whose keys does not start with list to String 
	 * and not objlist advanced parameters to {@link AdvancedParameter}
	 * and puts everything to one map.
	 * @param parameters
	 * @return
	 */
	private Map<String, Object> getVariables(Map<String,List<String>> parameters,Map<String,List<AdvancedParameter>> advancedParameters){
		Map<String, Object> variables = new HashMap<String, Object>(parameters.size());
		Set<String> keysSet = parameters.keySet();
		for(String key : keysSet){
			List<String> params = parameters.get(key);
			if(key.toLowerCase().startsWith("list") || key.toLowerCase().startsWith("files")){
				variables.put(key, params);
				continue;
			}else if(key.toLowerCase().startsWith("date")){
				String parameter = params.get(0);
				Date date = IWDatePickerHandler.getParsedDate(parameter);
				variables.put(key, date);
				continue;
			}
			else if(key.toLowerCase().startsWith("long")){
				for(String parameter : params){
					try {
						Long longValue = Long.valueOf(parameter);
						variables.put(key, longValue);
						break;
					} catch (Exception e) {
						getLogger().log(Level.WARNING, "wrong long number: " + parameter, e);
						variables.put(key, null);
					}
				}
				continue;
			}else if(key.toLowerCase().startsWith("double")){
				for(String parameter : params){
					try {
						Double doubleValue = Double.valueOf(parameter);
						variables.put(key, doubleValue);
						break;
					} catch (Exception e) {
						getLogger().log(Level.WARNING, "wrong long number: " + parameter, e);
						variables.put(key, null);
					}
				}
				continue;
			}
			String parameter = params.get(0);
			variables.put(key, parameter);
		}
		if(MapUtil.isEmpty(advancedParameters)){
			return variables;
		}
		keysSet = advancedParameters.keySet();
		for(String key : keysSet){
			List<AdvancedParameter> params = advancedParameters.get(key);
			if(ListUtil.isEmpty(params)){
				continue;
			}
			if(key.toLowerCase().startsWith("objlist")){
				variables.put(key, params);
				continue;
			}
			AdvancedParameter parameter = params.get(0);
			variables.put(key, parameter);
		}
		
		return variables;
	}
	
	private synchronized long getUploadId(){
		return ++uploadId;
	}
	
	@RemoteMethod
	public String getAttachmentUploadPath(){
		long ms = System.currentTimeMillis();
		long t1 = ms / 3600000;
		long t2 = ms / 100000000;
		long t3 = t2 / 360;
		return "/files/bpm/attachments/" + t3 + "/" + t2 + "/" + t1 + "/" + getUploadId();
	}
	
	// TODO: make static final variable
	public List<Map<String,Object>> getAllHousingConditions(){
		List<Map<String,Object>> housingConditions = new ArrayList<Map<String,Object>>();
		HashMap<String, Object> housingCondition = new HashMap<String, Object>();

		housingCondition.put("id", Long.valueOf(1));
		housingCondition.put("name", "lease_Apartment");
		housingConditions.add(housingCondition);
		
		housingCondition = new HashMap<String, Object>();
		housingCondition.put("id", Long.valueOf(2));
		housingCondition.put("name", "social_buy_apartments");
		housingConditions.add(housingCondition);
		
		housingCondition = new HashMap<String, Object>();
		housingCondition.put("id", Long.valueOf(3));
		housingCondition.put("name", "privately_owned_homes");
		housingConditions.add(housingCondition);
		
		housingCondition = new HashMap<String, Object>();
		housingCondition.put("id", Long.valueOf(4));
		housingCondition.put("name", "housing_available");
		housingConditions.add(housingCondition);
		
		housingCondition = new HashMap<String, Object>();
		housingCondition.put("id", Long.valueOf(5));
		housingCondition.put("name", "living_with_relatives_temporarily");
		housingConditions.add(housingCondition);
		
		housingCondition = new HashMap<String, Object>();
		housingCondition.put("id", Long.valueOf(6));
		housingCondition.put("name", "rental_apartment");
		housingConditions.add(housingCondition);
		
		return housingConditions;
	}
	
	public List<Map<String,Object>> getAllHouseTypes(){
		List<Map<String,Object>> housetypes = new ArrayList<Map<String,Object>>();
		HashMap<String, Object> housetype = new HashMap<String, Object>();

		housetype.put("id", Long.valueOf(1));
		housetype.put("name", "houses_townhouses");
		housetypes.add(housetype);
		
		housetype = new HashMap<String, Object>();
		housetype.put("id", Long.valueOf(2));
		housetype.put("name", "double_home");
		housetypes.add(housetype);
		
		housetype = new HashMap<String, Object>();
		housetype.put("id", Long.valueOf(3));
		housetype.put("name", "entire");
		housetypes.add(housetype);
		
		return housetypes;
	}
	
	public List<Map<String,Object>> getAllElevators(){
		List<Map<String,Object>> elevators = new ArrayList<Map<String,Object>>();
		HashMap<String, Object> elevator = new HashMap<String, Object>();

		elevator.put("id", Long.valueOf(1));
		elevator.put("name", "level");
		elevators.add(elevator);
		
		elevator = new HashMap<String, Object>();
		elevator.put("id", Long.valueOf(2));
		elevator.put("name", "elevator");
		elevators.add(elevator);
		
		elevator = new HashMap<String, Object>();
		elevator.put("id", Long.valueOf(3));
		elevator.put("name", "outside_stairs");
		elevators.add(elevator);
		
		return elevators;
	}
	
	public List<Map<String,Object>> getAllDesirableLocations(){
		List<Map<String,Object>> locations = new ArrayList<Map<String,Object>>();
		HashMap<String, Object> location = new HashMap<String, Object>();

		location.put("id", "1");
		location.put("name", "dalbraut_21_27");
		locations.add(location);
		
		location = new HashMap<String, Object>();
		location.put("id", "2");
		location.put("name", "langahli6_3");
		locations.add(location);
		
		location = new HashMap<String, Object>();
		location.put("id", "3");
		location.put("name", "furuger6i_1");
		locations.add(location);
		
		location = new HashMap<String, Object>();
		location.put("id", "4");
		location.put("name", "nor6urbrun_1");
		locations.add(location);
		
		location = new HashMap<String, Object>();
		location.put("id", "5");
		location.put("name", "lindargata_57_66");
		locations.add(location);
		
		location = new HashMap<String, Object>();
		location.put("id", "6");
		location.put("name", "seljahli6");
		locations.add(location);
		
		return locations;
	}
	
	public List<Map<String,Object>> getAllResidentRights(){
		List<Map<String,Object>> locations = new ArrayList<Map<String,Object>>();
		HashMap<String, Object> location = new HashMap<String, Object>();

		location.put("id", "1");
		location.put("name", "lindargata_57_66");
		locations.add(location);
		
		return locations;
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
	
	public List<String> getAssistanceServices(){
		List<String> assistanceServices = Arrays.asList(
				"assistanceinlearningservice",
				"workwithsupport",
				"residenceService",
				"uwordconditioningservice",
				"tourismservice",
				"commitandaidingsocialservice",
				"furtherpersonalsupportservice",
				"caringforchildrenservice",
				"weekendsuwordservice",
				"lengthpresenceservice",
				"helpatworkservice",
				"adviceservice",
				"carepaymentsservice",
				"shelteredworkservice"
		);
		List<String> socialServices = getSocialServices();
		ArrayList<String> services = new ArrayList<String>(assistanceServices.size() + socialServices.size());
		services.addAll(assistanceServices);
		services.addAll(socialServices);
		return services;
	}
	
}
