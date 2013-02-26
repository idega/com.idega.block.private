package com.idega.egov.hub.business.impl;

import is.idega.idegaweb.egov.application.business.ApplicationBusiness;
import is.idega.idegaweb.egov.application.data.Application;
import is.idega.idegaweb.egov.application.presentation.ApplicationBlock;
import is.idega.idegaweb.egov.bpm.application.ApplicationTypeBPM;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.idega.block.process.business.CaseManagersProvider;
import com.idega.block.process.business.CasesRetrievalManager;
import com.idega.block.process.presentation.beans.CasePresentation;
import com.idega.core.accesscontrol.business.LoginBusinessBean;
import com.idega.core.business.DefaultSpringBean;
import com.idega.jbpm.exe.BPMFactory;
import com.idega.jbpm.exe.ProcessConstants;
import com.idega.jbpm.exe.ProcessDefinitionW;
import com.idega.jbpm.view.View;
import com.idega.jbpm.view.ViewSubmission;
import com.idega.presentation.IWContext;
import com.idega.presentation.paging.PagedDataCollection;
import com.idega.servlet.filter.RequestResponseProvider;
import com.idega.user.business.UserBusiness;
import com.idega.user.data.User;
import com.idega.util.CoreConstants;
import com.idega.util.CoreUtil;
import com.idega.util.ListUtil;
import com.idega.util.StringUtil;
import com.idega.util.URIUtil;
import com.idega.util.expression.ELUtil;
import com.idega.xroad.processes.bean.XRoadProcess;
import com.idega.xroad.processes.business.XRoadProcessServices;

@Service
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class BPMProcessServices extends DefaultSpringBean implements XRoadProcessServices {

	@Autowired
	private CaseManagersProvider caseManagersProvider;

	@Autowired
	private BPMFactory bpmFactory;

	private CaseManagersProvider getCaseManagersProvider() {
		if (caseManagersProvider == null)
			ELUtil.getInstance().autowire(this);
		return caseManagersProvider;
	}

	private BPMFactory getBPMFactory() {
		if (bpmFactory == null)
			ELUtil.getInstance().autowire(this);
		return bpmFactory;
	}

	private User getUser(String userId) {
		if (StringUtil.isEmpty(userId)) {
			getLogger().warning("User ID is not provided");
			return null;
		}

		UserBusiness userBusiness = getServiceInstance(UserBusiness.class);
		try {
			return userBusiness.getUser(userId);					//	Trying by personal ID
		} catch (Exception e) {}
		try {
			return userBusiness.getUser(Integer.valueOf(userId));	//	Trying by ID of DB table ic_user
		} catch (Exception e) {}

		getLogger().warning("User can not be found by ID: " + userId);
		return null;
	}

	private RequestResponseProvider getRequestResponseProvider() {
		RequestResponseProvider provider = ELUtil.getInstance().getBean(RequestResponseProvider.class);
		return provider;
	}

	private IWContext getIWContext() {
		IWContext iwc = CoreUtil.getIWContext();
		if (iwc == null) {
			RequestResponseProvider provider = getRequestResponseProvider();
			HttpServletRequest request = provider.getRequest();
			iwc = new IWContext(request, provider.getResponse(), request.getSession().getServletContext());
		}

		return iwc;
	}

	private boolean doLogin(IWContext iwc, User user) {
		HttpServletRequest request = iwc.getRequest();
		LoginBusinessBean login = LoginBusinessBean.getLoginBusinessBean(request);
		if (login.isLoggedOn(request))
			return true;

		try {
			return login.logInByUUID(request, user.getUniqueId());
		} catch (Exception e) {
			getLogger().log(Level.WARNING, "Error whilw loging in as " + user, e);
		}
		return false;
	}

	public Collection<XRoadProcess> getAvailableProceses(String userPersonalId) {
		IWContext iwc = getIWContext();
		if (iwc == null) {
			getLogger().warning("IWContext is not initialized");
			return Collections.emptyList();
		}

		User user = getUser(userPersonalId);
		if (user == null) {
			getLogger().warning("User can not be found by ID: " + userPersonalId);
			return Collections.emptyList();
		}

		if (!doLogin(iwc, user))
			return Collections.emptyList();

		ApplicationBusiness applicationBusiness = getServiceInstance(ApplicationBusiness.class);
		Collection<Application> apps = applicationBusiness.getAvailableApplications(iwc, null);
		if (ListUtil.isEmpty(apps))
			return Collections.emptyList();

		Locale locale = getCurrentLocale();

		String host = getHost();

		Collection<XRoadProcess> processes = new ArrayList<XRoadProcess>();
		for (Application app: apps) {
			String type = app.getAppType();
			if (StringUtil.isEmpty(type))
				continue;

			if (ApplicationTypeBPM.appType.equals(type)) {
				XRoadProcess process = new XRoadProcess();
				String id = app.getPrimaryKey().toString();
				process.setId(id);
				process.setName(app.getUrl());
				process.setLocalizedName(applicationBusiness.getApplicationName(app, locale));
				URIUtil uri = new URIUtil(host.concat(CoreConstants.PAGES_URI_PREFIX).concat(CoreConstants.SLASH));
				uri.setParameter(ApplicationBlock.PARAMETER_APPLICATION_PK, id);
				process.setUrl(uri.getUri());
				processes.add(process);
			}
		}

		return processes;
	}

	private Collection<CasePresentation> getCases(String userPersonalId, String caseCode) {
		IWContext iwc = getIWContext();
		if (iwc == null) {
			getLogger().warning("IWContext is not initialized");
			return Collections.emptyList();
		}

		User user = getUser(userPersonalId);
		if (user == null)
			return Collections.emptyList();

		PagedDataCollection<CasePresentation> cases = getCaseManagersProvider().getCaseManager().getCases(user,
				CasesRetrievalManager.CASE_LIST_TYPE_OPEN, getCurrentLocale(), StringUtil.isEmpty(caseCode) ? null : Arrays.asList(caseCode), null,
						null, Integer.MAX_VALUE, -1, false, true);
		if (cases == null || ListUtil.isEmpty(cases.getCollection()))
			return Collections.emptyList();

		return cases.getCollection();
	}

	public Collection<CasePresentation> getAllCases(String userPersonalId) {
		return getCases(userPersonalId, null);
	}

	public Collection<CasePresentation> getCasesForProcess(String userPersonalId, String processName) {
		return getCases(userPersonalId, processName);
	}

	@Transactional(readOnly = false)
	public Long doSubmitProcess(String userPersonalId, String processName, Map<String, Object> processData) {
		try {
			IWContext iwc = getIWContext();
			if (iwc == null) {
				getLogger().warning("IWContext is not defined");
				return null;
			}

			User creator = getUser(userPersonalId);
			if (creator == null)
				return null;

			Integer issueCreatorId = Integer.valueOf(creator.getId());
			View initView = getBPMFactory().getProcessManager(processName).getProcessDefinition(processName).loadInitView(issueCreatorId);

			Map<String, String> parameters = initView.resolveParameters();
			Long processDefinitionId = new Long(parameters.get(ProcessConstants.PROCESS_DEFINITION_ID));
			String viewId = parameters.get(ProcessConstants.VIEW_ID);
			String viewType = parameters.get(ProcessConstants.VIEW_TYPE);

			ViewSubmission viewSubmission = getBPMFactory().getViewSubmission();

			viewSubmission.setProcessDefinitionId(processDefinitionId);
			viewSubmission.setViewId(viewId);
			viewSubmission.setViewType(viewType);

			viewSubmission.populateParameters(parameters);

			viewSubmission.populateVariables(processData);
			viewSubmission.populateParameters(parameters);

			ProcessDefinitionW pdw = getBPMFactory().getProcessManager(processDefinitionId).getProcessDefinition(processDefinitionId);
			Long piId = pdw.startProcess(viewSubmission);
			if (piId == null) {
				getLogger().warning("Unable to submit process using provided data:\nProcess name: " + processName + "; user ID: " + userPersonalId +
						"; variables: " + processData);
				return null;
			}

			return piId;
		} catch (Exception e) {
			getLogger().log(Level.WARNING, "Error to start process using provided data:\nProcess name: " + processName + "; user ID: " +
					userPersonalId + "; variables: " + processData, e);
		}

		return null;
	}

}