package com.idega.egov.hub.business.impl;

import is.idega.idegaweb.egov.bpm.cases.presentation.beans.CasesBPMAssetsState;
import is.idega.idegaweb.egov.cases.business.CasesBusiness;
import is.idega.idegaweb.egov.cases.util.CasesConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.ejb.FinderException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.idega.block.process.business.CaseBusiness;
import com.idega.block.process.data.Case;
import com.idega.block.process.data.CaseBMPBean;
import com.idega.block.process.data.CaseHome;
import com.idega.block.process.message.business.MessageBusiness;
import com.idega.block.process.message.data.Message;
import com.idega.block.process.presentation.UserCases;
import com.idega.builder.business.BuilderLogic;
import com.idega.business.IBOLookup;
import com.idega.core.idgenerator.business.UUIDGenerator;
import com.idega.data.IDOLookup;
import com.idega.idegaweb.IWMainApplication;
import com.idega.idegaweb.egov.bpm.data.CaseProcInstBind;
import com.idega.idegaweb.egov.bpm.data.dao.CasesBPMDAO;
import com.idega.jbpm.artifacts.presentation.ProcessArtifacts;
import com.idega.jbpm.exe.BPMDocument;
import com.idega.jbpm.exe.BPMEmailDocument;
import com.idega.jbpm.exe.ProcessInstanceW;
import com.idega.mobile.restful.DefaultRestfulService;
import com.idega.presentation.IWContext;
import com.idega.user.data.Group;
import com.idega.user.data.GroupHome;
import com.idega.user.data.User;
import com.idega.util.CoreConstants;
import com.idega.util.CoreUtil;
import com.idega.util.IWTimestamp;
import com.idega.util.ListUtil;
import com.idega.util.StringUtil;
import com.idega.util.expression.ELUtil;
import com.idega.xroad.bean.Document;
import com.idega.xroad.bean.EmailDocument;
import com.idega.xroad.business.CasesDataProvider;
import com.idega.xroad.business.XroadGateway;

@Service(ElectronicXRoadServices.BEAN_NAME)
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class ElectronicXRoadServices extends DefaultRestfulService implements XroadGateway {

	public static final String BEAN_NAME = "electronicXroadServices";

	@Autowired
	private CasesBPMDAO casesBPMDAO;

	@Autowired
	private ProcessArtifacts processArtifacts;

	private boolean isThisService(String serviceId) {
		return false;
	}

	private com.idega.xroad.bean.User createFakeUser(String userId){
		com.idega.xroad.bean.User user = new com.idega.xroad.bean.User();
		user.setEmail("fake@email.com");
		user.setId(userId);
		user.setName("Fake Name");
		user.setPhone("f4k3_ph0n3");
		return user;
	}

	private com.idega.xroad.bean.Message createFakeMessage(String userId){
		com.idega.xroad.bean.Message message = new com.idega.xroad.bean.Message();
		message.setBody("fake message body");
		message.setSubject("fake message subject");
		message.setOwner(createFakeUser(userId));
		message.setNumber(UUIDGenerator.getInstance().generateUUID());
		message.setCreated(IWTimestamp.getTimestampRightNow());
		try {
			CasesBusiness casesBusiness = IBOLookup.getServiceInstance(IWMainApplication.getDefaultIWApplicationContext(), CasesBusiness.class);
			IWContext iwc = CoreUtil.getIWContext();
			message.setStatus(casesBusiness.getLocalizedCaseStatusDescription(casesBusiness.getCaseStatus(CaseBMPBean.CASE_STATUS_OPEN_KEY), iwc.getCurrentLocale()));
		} catch (Exception e) {

		}
		return message;
	}

	private com.idega.xroad.bean.Case createFakeCase(String userId){
		com.idega.xroad.bean.Case theCase = new com.idega.xroad.bean.Case();
		theCase.setSubject("fake case subject " + System.currentTimeMillis());
		theCase.setOwner(createFakeUser(userId));
		theCase.setNumber(UUIDGenerator.getInstance().generateUUID());
		theCase.setCreated(IWTimestamp.getTimestampRightNow());
		try {
			CasesBusiness casesBusiness = IBOLookup.getServiceInstance(IWMainApplication.getDefaultIWApplicationContext(), CasesBusiness.class);
			IWContext iwc = CoreUtil.getIWContext();
			theCase.setStatus(casesBusiness.getLocalizedCaseStatusDescription(casesBusiness.getCaseStatus(CaseBMPBean.CASE_STATUS_PENDING_KEY), iwc.getCurrentLocale()));
		} catch (Exception e) {}

		theCase.setTasks(Arrays.asList(
			new Document("Task number 1", "Test User1", "www.citizenehub.com", theCase.getCreated()),
			new Document("Task number 2", "Test User2", "www.citizenehub.com", theCase.getCreated())
		));
		theCase.setDocuments(Arrays.asList(
			new Document("Document number 1", "Test User1", "www.citizenehub.com", theCase.getCreated()),
			new Document("Document number 2", "Test User2", "www.citizenehub.com", theCase.getCreated())
		));
		theCase.setEmails(Arrays.asList(
			new EmailDocument("Email number 1", "Test User1", "test1@citizenehub.com"),
			new EmailDocument("Email number 2", "Test User2", "test2@citizenehub.com")
		));
		theCase.setUsersConnectedToProcess(Arrays.asList(
			new com.idega.xroad.bean.User("123", "test1@citizenehub.com", "789", "Test User1"),
			new com.idega.xroad.bean.User("456", "test2@citizenehub.com", "000", "Test User2")
		));

		return theCase;
	}

	private Collection<com.idega.xroad.bean.Message> getFakeMessages(String userId){
		List<com.idega.xroad.bean.Message> messages = new ArrayList<com.idega.xroad.bean.Message>();
		for (int i = 0; i<10; i++)
			messages.add(createFakeMessage(userId));

		return messages;
	}

	private Collection<com.idega.xroad.bean.Case> getFakeCases(String userId){
		List<com.idega.xroad.bean.Case> cases = new ArrayList<com.idega.xroad.bean.Case>();
		for (int i = 0;i<10;i++)
			cases.add(createFakeCase(userId));

		return cases;
	}

	@Override
	public Collection<com.idega.xroad.bean.Message> getMessagesByUser(String serviceId, String userId) throws Exception{
		if(isThisService(serviceId)){
			return getMessagesByUserFromThisHost(userId);
		}
		return getMessagesByUserFromRemoteHost(serviceId, userId);
	}
	private Collection<com.idega.xroad.bean.Message> getMessagesByUserFromRemoteHost(String serviceId,String userId) throws Exception{
		return getFakeMessages(userId);
	}

	@SuppressWarnings("unchecked")
	private Collection<com.idega.xroad.bean.Message> getMessagesByUserFromThisHost(String userId) throws Exception{
		if(StringUtil.isEmpty(userId)){
			throw new RuntimeException("User id is not specified");
		}

		User user = getUser(userId);
		MessageBusiness messageBusiness = IBOLookup.getServiceInstance(IWMainApplication.getDefaultIWApplicationContext(), MessageBusiness.class);
		Collection<? extends Message> messages;
		try{
			messages = messageBusiness.findMessages(null,user);
		}catch(FinderException e){
			messages = Collections.emptyList();
		}

		IWContext iwc = CoreUtil.getIWContext();
		CasesBusiness casesBusiness = IBOLookup.getServiceInstance(iwc, CasesBusiness.class);
		Locale locale = iwc.getCurrentLocale();
		List<com.idega.xroad.bean.Message> messagesToSend = new ArrayList<com.idega.xroad.bean.Message>(messages.size());
		for(Message message : messages){
			messagesToSend.add(getMessage(message, casesBusiness, locale));
		}

		return messagesToSend;
	}

	private com.idega.xroad.bean.Message getMessage(Message message, CasesBusiness casesBusiness, Locale locale) throws Exception {
		com.idega.xroad.bean.Message messageToSend = new com.idega.xroad.bean.Message(message);
		messageToSend.setStatus(casesBusiness.getLocalizedCaseStatusDescription(casesBusiness.getCaseStatus(CaseBMPBean.CASE_STATUS_PENDING_KEY), locale));
		return messageToSend;
	}

	@Override
	public Collection<com.idega.xroad.bean.Message> getUnreadMessagesByUser(String serviceId, String userId) throws Exception{
		return isThisService(serviceId) ? getUnreadMessagesByUserFromThisHost(userId) : getUnreadMessagesByUserFromRemoteHost(serviceId, userId);
	}

	private Collection<com.idega.xroad.bean.Message>  getUnreadMessagesByUserFromThisHost(String userId) throws Exception{
		if (StringUtil.isEmpty(userId))
			throw new RuntimeException("User id is not specified");

		IWContext iwc = CoreUtil.getIWContext();
		User user = getUser(userId);
		MessageBusiness messageBusiness = IBOLookup.getServiceInstance(iwc, MessageBusiness.class);
		Collection<Message> messages;
		try {
			messages = messageBusiness.findMessagesForUser(null, user, null, Boolean.FALSE);
		} catch(FinderException e) {
			messages = Collections.emptyList();
		}

		CasesBusiness casesBusiness = IBOLookup.getServiceInstance(iwc, CasesBusiness.class);
		Locale locale = iwc.getCurrentLocale();
		List<com.idega.xroad.bean.Message> messagesToSend = new ArrayList<com.idega.xroad.bean.Message>(messages.size());
		for (Message message : messages)
			messagesToSend.add(getMessage(message, casesBusiness, locale));

		return messagesToSend;
	}

	private Collection<com.idega.xroad.bean.Message> getUnreadMessagesByUserFromRemoteHost(String serviceId, String userId) throws Exception{
		return getFakeMessages(userId);	//	TODO
	}

	@Override
	public Collection<com.idega.xroad.bean.Case> getCasesByUser(String serviceId, String userId) throws Exception{
		return isThisService(serviceId) ? getCasesByUserFromThisHost(userId) : getGateway().getCasesByUser(serviceId, userId);
	}

	@SuppressWarnings("unchecked")
	private Collection<com.idega.xroad.bean.Case> getCasesByUserFromThisHost(String userId) throws Exception{
		if (StringUtil.isEmpty(userId))
			throw new RuntimeException("User id is not specified");

		IWContext iwc = CoreUtil.getIWContext();
		Locale locale = iwc.getCurrentLocale();
		User user = getUser(userId);
		CaseBusiness caseBusiness = IBOLookup.getServiceInstance(IWMainApplication.getDefaultIWApplicationContext(), CaseBusiness.class);
		Collection<Case> cases = null;
		try {
			cases = caseBusiness.getAllCasesForUser(user,CasesConstants.CASE_CODE_KEY);
		} catch (FinderException e) {
			cases = Collections.emptyList();
		}

		List<com.idega.xroad.bean.Case> casesToSend = new ArrayList<com.idega.xroad.bean.Case>(cases.size());
		for (Case theCase : cases)
			casesToSend.add(getCase(theCase, user, locale, caseBusiness.getLocalizedCaseStatusDescription(theCase, theCase.getCaseStatus(), locale)));

		return casesToSend;
	}

	private com.idega.xroad.bean.Case getCase(Case theCase, User user, Locale locale, String localizedStatus) {
		String openCasesUri =  new StringBuilder( BuilderLogic.getInstance().getFullPageUrlByPageType(user, "openCases", true))
				.append(CoreConstants.QMARK).append(UserCases.PARAMETER_ACTION).append(CoreConstants.EQ).append(UserCases.ACTION_CASE_MANAGER_VIEW)
				.append(CoreConstants.AMP).append(UserCases.PARAMETER_CASE_PK).append(CoreConstants.EQ).append(theCase.getId()).toString() ;
		com.idega.xroad.bean.Case caseToSend = new com.idega.xroad.bean.Case(theCase);
		caseToSend.setStatus(localizedStatus);
		CaseProcInstBind caseProcInstBind = getCasesBPMDAO().getCaseProcInstBindByCaseId(Integer.valueOf(theCase.getId()));

		Long processInstanceId = caseProcInstBind.getProcInstId();
		ProcessArtifacts processArtifacts = getProcessArtifacts();
		ProcessInstanceW piW = processArtifacts.getBpmFactory().getProcessManagerByProcessInstanceId(processInstanceId).
				getProcessInstance(processInstanceId);

		//	Tasks
		List<BPMDocument> bpmTasks = piW.getTaskDocumentsForUser(user, locale);
		List<Document> tasks = new ArrayList<Document>(bpmTasks.size());
		for (BPMDocument task : bpmTasks) {
			Document taskDocument = new Document(task);
			if (task.isHasViewUI()) {
				String viewUri = openCasesUri + CoreConstants.AMP + CasesBPMAssetsState.TASK_INSTANCE_ID_PARAMETER +
						CoreConstants.EQ + task.getTaskInstanceId();
				taskDocument.setViewUri(viewUri);
			}
			tasks.add(taskDocument);
		}

		//	Documents
		List <BPMDocument> bpmDocuments = piW.getSubmittedDocumentsForUser(user, locale);
		List <Document> documents = new ArrayList<Document>(bpmDocuments.size());
		for(BPMDocument bpmDocument : bpmDocuments){
			Document document = new Document(bpmDocument);
			if(bpmDocument.isHasViewUI()){
				String viewUri = openCasesUri + CoreConstants.AMP + CasesBPMAssetsState.TASK_INSTANCE_ID_PARAMETER +
						CoreConstants.EQ  + bpmDocument.getTaskInstanceId();
				document.setViewUri(viewUri);
			}
			documents.add(document);
		}

		//	Emails
		List<BPMEmailDocument> bpmEmailDocuments = piW.getAttachedEmails(user);
		List <EmailDocument> emails = new ArrayList<EmailDocument>(bpmDocuments.size());
		for (BPMEmailDocument bpmDocument : bpmEmailDocuments) {
			EmailDocument emailDocument = new EmailDocument(bpmDocument);
			if (bpmDocument.isHasViewUI()) {
				String viewUri = openCasesUri + CoreConstants.AMP + CasesBPMAssetsState.TASK_INSTANCE_ID_PARAMETER +
						CoreConstants.EQ  + bpmDocument.getTaskInstanceId();
				emailDocument.setViewUri(viewUri);
			}
			documents.add(emailDocument);
		}

		Collection<User> connectedUsers = processArtifacts.getUsersConnectedToProces(piW);

		caseToSend.setTasks(tasks);
		caseToSend.setDocuments(documents);
		caseToSend.setEmails(emails);
		caseToSend.setIdegaUsersConnectedToProcess(connectedUsers);
		return caseToSend;
	}

	@Override
	public Collection<com.idega.xroad.bean.Case> getUnreadCasesByUser(String serviceId, String userId) throws Exception{
		return isThisService(serviceId) ? getUnreadCasesByUserFromThisHost(userId) : getUnreadCasesByUserFromRemoteHost(serviceId, userId);
	}

	private Collection<com.idega.xroad.bean.Case> getUnreadCasesByUserFromThisHost(String userId) throws Exception {
		if (StringUtil.isEmpty(userId))
			throw new RuntimeException("User id is not specified");

		IWContext iwc = CoreUtil.getIWContext();
		Locale locale = iwc.getCurrentLocale();
		User user = getUser(userId);
		CaseHome caseHome = (CaseHome) IDOLookup.getHome(Case.class);
		Collection<Case> cases = null;
		CaseBusiness caseBusiness = IBOLookup.getServiceInstance(iwc, CaseBusiness.class);

		try{
			cases = caseHome.getCases(user, null, CasesConstants.CASE_CODE_KEY, Boolean.FALSE);
		} catch(FinderException e) {
			cases = Collections.emptyList();
		}

		List<com.idega.xroad.bean.Case> casesToSend = new ArrayList<com.idega.xroad.bean.Case>(cases.size());
		for (Case theCase: cases)
			casesToSend.add(getCase(theCase, user, locale,caseBusiness.getLocalizedCaseStatusDescription(theCase, theCase.getCaseStatus(), locale)));

		return casesToSend;
	}

	private Collection<com.idega.xroad.bean.Case> getUnreadCasesByUserFromRemoteHost(String serviceId, String userId){
		return getFakeCases(userId);
	}

	@Override
	public com.idega.xroad.bean.Message getMessage(String serviceId, String userId, String messageId) throws Exception{
		return isThisService(serviceId) ? getMessageFromThisHost(userId, messageId) : getMessageFromRemoteHost(serviceId, userId, messageId);
	}

	private com.idega.xroad.bean.Message getMessageFromThisHost(String userId, String messageId) throws Exception{
		if (StringUtil.isEmpty(userId) || StringUtil.isEmpty(userId))
			throw new RuntimeException("Not all data provided");

		IWContext iwc = CoreUtil.getIWContext();
		Locale locale = iwc.getCurrentLocale();
		CasesBusiness casesBusiness = IBOLookup.getServiceInstance(iwc, CasesBusiness.class);
		MessageBusiness messageBusiness = IBOLookup.getServiceInstance(iwc, MessageBusiness.class);
		Message message = messageBusiness.getMessage(messageId);
		if (isUserOwner(message, getUser(userId)))
			return getMessage(message, casesBusiness, locale);

		throw new FinderException("Message " + messageId + " is not owned by user " + userId);
	}

	private com.idega.xroad.bean.Message getMessageFromRemoteHost(String serviceId, String userId, String messageId) throws Exception{
		return createFakeMessage(userId);
	}

	@Override
	public com.idega.xroad.bean.Case getCase(String serviceId, String userId, String caseId) throws Exception{
		return isThisService(serviceId) ? getCaseFromThisHost(userId,caseId) : getCaseFromRemoteHost(serviceId, userId, caseId);
	}

	private com.idega.xroad.bean.Case getCaseFromThisHost(String userId, String caseId) throws Exception {
		if (StringUtil.isEmpty(userId) || StringUtil.isEmpty(caseId))
			throw new RuntimeException("Not all data provided");

		IWContext iwc = CoreUtil.getIWContext();
		Locale locale = iwc.getCurrentLocale();
		CaseBusiness caseBusiness = IBOLookup.getServiceInstance(iwc, CaseBusiness.class);
		User user = getUser(userId);
		Case theCase = caseBusiness.getCase(caseId);
		if (isUserOwner(theCase, user))
			return getCase(theCase, user, locale, caseBusiness.getLocalizedCaseStatusDescription(theCase, theCase.getCaseStatus(), locale));

		throw new FinderException("Case " + caseId + " is not owned by user " + userId);
	}

	private com.idega.xroad.bean.Case getCaseFromRemoteHost(String serviceId, String userId, String caseId) throws Exception {
		return createFakeCase(userId);
	}

	@Override
	public void setCaseRead(String serviceId, String userId, String caseId, Boolean read) throws Exception{
		if (isThisService(serviceId)) {
			setCaseReadOnThisHost(userId, caseId, read);
			return;
		}

		setCaseReadOnRemoteHost(serviceId, userId, caseId, read);
	}

	private void setCaseReadOnThisHost(String userId, String caseId, Boolean read) throws Exception{
		if (StringUtil.isEmpty(userId) || StringUtil.isEmpty(caseId))
			throw new RuntimeException("Not all data provided");

		if (read == null)
			read = false;

		CaseBusiness caseBusiness = IBOLookup.getServiceInstance(IWMainApplication.getDefaultIWApplicationContext(), CaseBusiness.class);
		Case theCase = caseBusiness.getCase(caseId);
		theCase.setRead(read);
		theCase.store();
	}

	private void setCaseReadOnRemoteHost(String serviceId, String userId, String caseId, Boolean read) throws Exception{
		//	TODO: implement
	}

	@Override
	public void setMessageRead(String serviceId, String userId, String messageId, Boolean read) throws Exception{
		if(isThisService(serviceId)){
			setMessageReadOnThisHost(userId, messageId, read);
			return;
		}
		setMessageReadOnRemoteHost(serviceId, userId, messageId, read);
	}

	private void setMessageReadOnThisHost(String userId, String messageId, Boolean read) throws Exception {
		if (StringUtil.isEmpty(userId) || StringUtil.isEmpty(userId))
			throw new RuntimeException("Not all data provided");

		if (read == null)
			read = false;

		MessageBusiness messageBusiness = IBOLookup.getServiceInstance(IWMainApplication.getDefaultIWApplicationContext(), MessageBusiness.class);
		Message message = messageBusiness.getMessage(messageId);
		message.setRead(read);
		message.store();
	}

	private void setMessageReadOnRemoteHost(String serviceId, String userId, String messageId, Boolean read) throws Exception{
		//	TODO: implement
	}

	@Override
	public Collection<com.idega.xroad.bean.Case> getCasesByCriteria(String serviceId,String caseNumber, String description,
			String name, String personalId, List<String> statuses, String dateFrom, String dateTo,
			String ownerId, List<String> groups, Boolean simpleCases, Boolean notGeneralCases) throws Exception{
		if(isThisService(serviceId)){
			return getCasesByCriteriaOnThisHost(caseNumber, description, name, personalId, statuses, dateFrom, dateTo,
					ownerId,  groups, simpleCases, notGeneralCases);
		}
		return getCasesByCriteriaOnRemoteHost(serviceId, caseNumber, description, name, personalId, statuses, dateFrom, dateTo,
				ownerId,  groups, simpleCases, notGeneralCases);
	}

	@SuppressWarnings("unchecked")
	private Collection<com.idega.xroad.bean.Case> getCasesByCriteriaOnThisHost(String caseNumber, String description,
			String name, String personalId, List<String> statusesList, String dateFrom, String dateTo,
			String ownerId, List<String> groups, Boolean simpleCases, Boolean notGeneralCases) throws Exception{
		if(StringUtil.isEmpty(caseNumber)){
			caseNumber = null;
		}
		if(StringUtil.isEmpty(description)){
			description = null;
		}
		if(StringUtil.isEmpty(name)){
			name = null;
		}
		if(StringUtil.isEmpty(personalId)){
			name = null;
		}
		if(simpleCases == null){
			simpleCases = false;
		}
		if(notGeneralCases == null){
			notGeneralCases = false;
		}

		String [] statuses = null;
		if(!ListUtil.isEmpty(statusesList)){
			statuses = statusesList.toArray(new String [0]);
		}

		IWContext iwc = CoreUtil.getIWContext();
		Locale locale = iwc.getCurrentLocale();
		Collection<Group> groupCollection = null;
		if(!ListUtil.isEmpty(groups)){
			GroupHome groupHome = (GroupHome) IDOLookup.getHome(Group.class);
			groupCollection = groupHome.findByPrimaryKeyCollection(groups);
		}
		User owner = getUser(ownerId);
		if (owner == null)
			return new ArrayList<com.idega.xroad.bean.Case>(0);

		CasesBusiness casesBusiness = IBOLookup.getServiceInstance(IWMainApplication.getDefaultIWApplicationContext(), CasesBusiness.class);
		Collection<Case> cases;

		IWTimestamp from = null;
		if(!StringUtil.isEmpty(dateFrom)){
			from = new IWTimestamp(dateFrom);
		}
		IWTimestamp to = null;
		if(!StringUtil.isEmpty(dateTo)){
			from = new IWTimestamp(dateTo);
		}
		cases = casesBusiness.getCasesByCriteria(caseNumber,description,name, personalId,statuses, from, to, owner, groupCollection, simpleCases, notGeneralCases);

		List<com.idega.xroad.bean.Case> casesToSend;
		if (ListUtil.isEmpty(cases)) {
			casesToSend = new ArrayList<com.idega.xroad.bean.Case>(0);
		} else{
			casesToSend = new ArrayList<com.idega.xroad.bean.Case>(cases.size());
			for (Case theCase : cases) {
				casesToSend.add(getCase(theCase, theCase.getOwner(), locale,
						casesBusiness.getLocalizedCaseStatusDescription(theCase.getCaseStatus(), locale)));
			}
		}

		return casesToSend;
	}

	private boolean isUserOwner(Case theCase, User user) {
		if (theCase == null || user == null)
			return false;

		User owner = theCase.getOwner();
		if (owner == null)
			return false;

		String ownerId = owner.getId();
		String userId = user.getId();
		if (ownerId != null && userId != null && ownerId.equals(userId))
			return true;

		String ownerPersonalId = owner.getPersonalID();
		String userPersonalId = user.getPersonalID();
		return ownerPersonalId != null && userPersonalId != null && ownerPersonalId.equals(userPersonalId);
	}

	private Collection<com.idega.xroad.bean.Case> getCasesByCriteriaOnRemoteHost(String serviceId,String caseNumber, String description,
			String name, String personalId, List<String> statuses, String dateFrom, String dateTo,
			String ownerId, List<String> groups, Boolean simpleCases, Boolean notGeneralCases) throws Exception{
		return getFakeCases(ownerId);
	}

	protected CasesBPMDAO getCasesBPMDAO() {
		if (casesBPMDAO == null)
			ELUtil.getInstance().autowire(this);

		return casesBPMDAO;
	}

	protected ProcessArtifacts getProcessArtifacts() {
		if (processArtifacts == null)
			ELUtil.getInstance().autowire(this);

		return processArtifacts;
	}

	private XroadGateway getGateway() {
		XroadGateway gateway = ELUtil.getInstance().getBean(CasesDataProvider.BEAN_NAME);
		return gateway;
	}

	@Override
	public String submitParkingCardStatement(String serviceId, String userId, Map<String, Object> data) throws Exception {
		if (isThisService(serviceId)) {
			getLogger().info("Implement this method!");	//	TODO
			return null;
		}

		return getGateway().submitParkingCardStatement(serviceId, userId, data);
	}
}
