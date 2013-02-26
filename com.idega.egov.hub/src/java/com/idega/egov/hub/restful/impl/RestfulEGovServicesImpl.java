package com.idega.egov.hub.restful.impl;

import is.idega.idegaweb.egov.citizen.data.CitizenRemoteServices;
import is.idega.idegaweb.egov.citizen.data.CitizenRemoteServicesHome;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;

import javax.ejb.FinderException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.idega.data.IDOLookup;
import com.idega.egov.hub.EGovConstants;
import com.idega.egov.hub.bean.ApplicationData;
import com.idega.egov.hub.bean.Service;
import com.idega.egov.hub.bean.request.ChangeItemValueRequest;
import com.idega.egov.hub.bean.request.ServiceRequest;
import com.idega.egov.hub.business.impl.ElectronicXRoadServices;
import com.idega.egov.hub.restful.RestfulEGovServices;
import com.idega.idegaweb.egov.bpm.data.dao.CasesBPMDAO;
import com.idega.jbpm.artifacts.presentation.ProcessArtifacts;
import com.idega.mobile.restful.DefaultRestfulService;
import com.idega.user.data.User;
import com.idega.util.ListUtil;
import com.idega.util.StringUtil;
import com.idega.util.expression.ELUtil;
import com.idega.xroad.bean.Case;
import com.idega.xroad.bean.Message;
import com.idega.xroad.business.CasesDataProvider;
import com.idega.xroad.business.XroadGateway;
import com.idega.xroad.processes.bean.XRoadProcess;
import com.idega.xroad.processes.business.XRoadProcessServices;

@Path(EGovConstants.URI_SERVICE)
public class RestfulEGovServicesImpl extends DefaultRestfulService implements RestfulEGovServices {

	@Autowired
	private XRoadProcessServices xroadProcessesServices;

	@Autowired
	private CasesBPMDAO casesBPMDAO;

	@Autowired
	private ProcessArtifacts processArtifacts;

	private XRoadProcessServices getXRoadProcessServices() {
		if (xroadProcessesServices == null)
			ELUtil.getInstance().autowire(this);
		return xroadProcessesServices;
	}

	@Override
	@GET
	@Path(EGovConstants.URI_GET_AVAILABLE_SERVICES)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAvailableServices(@QueryParam("userId") String userId) {
		if (StringUtil.isEmpty(userId)) {
			String message = "User ID is not provided";
			getLogger().warning(message);
			return getResponse(Response.Status.BAD_REQUEST, message);
		}

		Collection<XRoadProcess> services = getXRoadProcessServices().getAvailableProceses(userId);
		if (ListUtil.isEmpty(services))
			return getResponse(Response.Status.OK, new ArrayList<XRoadProcess>(0));

		return getResponse(Response.Status.OK, new ArrayList<XRoadProcess>(services));
	}

	@Override
	@GET
	@Path(EGovConstants.URI_GET_USER_MESSAGES)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMessagesByUser(@QueryParam("serviceId") String serviceId, @QueryParam("userId")String userId) {
		if (StringUtil.isEmpty(serviceId) || StringUtil.isEmpty(userId)){
			String message = "Error getting messages of user " + userId + " from service "  + serviceId;
			getLogger().warning(message);
    		return getResponse(Response.Status.BAD_REQUEST, message);
		}

		try{
			Collection<Message> messagesToSend = getServicesProvider().getMessagesByUser(serviceId, userId);
			if(messagesToSend instanceof Serializable){
				return getResponse(Response.Status.OK, (Serializable)messagesToSend);
			}
			return getResponse(Response.Status.OK, new ArrayList<Message>(messagesToSend));

		} catch(Exception e){
			String message = "Error getting messages of user " + userId + " from service "  + serviceId + ". Error: " + e.getMessage();
    		getLogger().log(Level.WARNING, message, e);
    		return getResponse(Response.Status.INTERNAL_SERVER_ERROR, message);
		}
	}

	@Override
	@GET
	@Path(EGovConstants.URI_GET_USER_CASES)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCasesByUser(
			@QueryParam("serviceId") String serviceId,
			@QueryParam("userId") String userId
	) {
		if(StringUtil.isEmpty(serviceId) || StringUtil.isEmpty(userId)){
			String message = "Error getting cases of user " + userId + " from service "  + serviceId;
			getLogger().warning(message);
    		return getResponse(Response.Status.BAD_REQUEST, message);
		}
		try{
			Collection<Case> cases = getServicesProvider().getCasesByUser(serviceId, userId);
			if (cases instanceof Serializable)
				return getResponse(Response.Status.OK, (Serializable)cases);

			return getResponse(Response.Status.OK, new ArrayList<Case>(cases));
		} catch(Exception e){
			String message = "Error getting cases of user " + userId + " from service "  + serviceId + ". Error: " + e.getMessage();
    		getLogger().log(Level.WARNING, message, e);
    		return getResponse(Response.Status.INTERNAL_SERVER_ERROR, message);
		}
	}

	@Override
	@GET
	@Path(EGovConstants.URI_GET_CASE_BY_ID_AND_USER)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCase(@QueryParam("serviceId") String serviceId, @QueryParam("userId")String userId, @QueryParam("caseId")  String caseId) {
		if (StringUtil.isEmpty(serviceId) || StringUtil.isEmpty(userId) || StringUtil.isEmpty(caseId)){
			String message = "Error getting case"+ caseId +" for user " + userId + " from service "  + serviceId;
    		return getResponse(Response.Status.BAD_REQUEST, message);
		}
		try{
			Case caseToSend = getServicesProvider().getCase(serviceId, userId, caseId);
			if (caseToSend == null) {
				String message = "Case was not found for user " + userId + ", on service " + serviceId + " by case ID: " + caseId;
				getLogger().warning(message);
				return getResponse(Response.Status.OK, null);
			}

			return getResponse(Response.Status.OK, caseToSend);
		} catch(Exception e){
			String message = "Error getting cases of user " + userId + " from service "  + serviceId + ". Error: " + e.getMessage();
    		getLogger().log(Level.WARNING, message, e);
    		return getResponse(Response.Status.INTERNAL_SERVER_ERROR, message);
		}
	}

	@Override
	@GET
	@Path(EGovConstants.URI_GET_SERVICES_BY_USER)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserServices(@QueryParam("userId")String userId) {
		if (StringUtil.isEmpty(userId)) {
			String message = "Error getting services for user " + userId;;
    		return getResponse(Response.Status.BAD_REQUEST, message);
		}

		try {
			ArrayList<Service> services = null;

			CitizenRemoteServicesHome citizenRemoteServicesHome = (CitizenRemoteServicesHome) IDOLookup.getHome(CitizenRemoteServices.class);
			Collection<CitizenRemoteServices> remoteServices = citizenRemoteServicesHome.getRemoteServicesByUserId(userId);
			if (ListUtil.isEmpty(remoteServices) && getApplication().getSettings().getBoolean("add_mockup_ehub_services", Boolean.TRUE)) {
				services = new ArrayList<Service>();
				services.add(new Service("Innovation centre test service", "impra.sidan.is", "1"));
				services.add(new Service("Parking test service", "biladev.sidan.is", "2"));
				services.add(new Service("Reykjavik test service", "rafraen.reykjavik.is", "3"));
			} else {
				services = new ArrayList<Service>(remoteServices.size());
				for (CitizenRemoteServices service: remoteServices)
					services.add(new Service(service));
			}

			return getResponse(Response.Status.OK, services);
		} catch(Exception e){
			String message = "Error getting services for user " + userId + ". Error: " + e.getMessage();
    		getLogger().log(Level.WARNING, message, e);
    		return getResponse(Response.Status.INTERNAL_SERVER_ERROR, message);
		}
	}

	@Override
	@POST
	@Path(EGovConstants.URI_ADD_SERVICE_TO_USER)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addServiceToUser(ServiceRequest request) {
		if(request == null){
			String message = "Error adding service by request " + request;
    		return getResponse(Response.Status.BAD_REQUEST, message);
		}
		try{
			CitizenRemoteServicesHome citizenRemoteServicesHome = (CitizenRemoteServicesHome)IDOLookup.getHome(CitizenRemoteServices.class);
			CitizenRemoteServices citizenRemoteServices = citizenRemoteServicesHome.findByPrimaryKeyIDO(request.getServiceId());
			User user = getUser(request.getUserId());
			citizenRemoteServices.addUser(user);
			return getResponse(Response.Status.OK, "OK");
		} catch(Exception e){
			String message = "Error adding service " + request + ". Error: " + e.getMessage();
    		getLogger().log(Level.WARNING, message, e);
    		return getResponse(Response.Status.INTERNAL_SERVER_ERROR, message);
		}
	}

	@GET
	@Path(EGovConstants.URI_GET_UNREAD_CASES_BY_USER)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response getUnreadCasesByUser(@QueryParam("serviceId") String serviceId, @QueryParam("userId")String userId) {
		if(StringUtil.isEmpty(serviceId) || StringUtil.isEmpty(userId)){
			String message = "Error getting unread cases of user " + userId + " from service "  + serviceId;
    		return getResponse(Response.Status.BAD_REQUEST, message);
		}

		try{
			Collection<Case> casesToSend = getServicesProvider().getUnreadCasesByUser(serviceId, userId);
			if(casesToSend instanceof Serializable){
				return getResponse(Response.Status.OK, (Serializable)casesToSend);
			}
			return getResponse(Response.Status.OK, new ArrayList<Case>(casesToSend));
		} catch(Exception e){
			String message = "Error getting cases of user " + userId + " from service "  + serviceId + ". Error: " + e.getMessage();
    		getLogger().log(Level.WARNING, message, e);
    		return getResponse(Response.Status.INTERNAL_SERVER_ERROR, message);
		}
	}

	@GET
	@Path(EGovConstants.URI_GET_UNREAD_MESSAGES_BY_USER)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response getUnreadMessagesByUser(@QueryParam("serviceId") String serviceId, @QueryParam("userId")String userId) {
		if(StringUtil.isEmpty(serviceId) || StringUtil.isEmpty(userId)){
			String message = "Error getting unread messages of user " + userId + " from service "  + serviceId;
    		return getResponse(Response.Status.BAD_REQUEST, message);
		}

		try{
			Collection<Message> messagesToSend = getServicesProvider().getUnreadMessagesByUser(serviceId, userId);
			if(messagesToSend instanceof Serializable){
				return getResponse(Response.Status.OK, (Serializable)messagesToSend);
			}
			return getResponse(Response.Status.OK, new ArrayList<Message>(messagesToSend));
		} catch(Exception e){
			String message = "Error getting messages of user " + userId + " from service "  + serviceId + ". Error: " + e.getMessage();
    		getLogger().log(Level.WARNING, message, e);
    		return getResponse(Response.Status.INTERNAL_SERVER_ERROR, message);
		}
	}

	@GET
	@Path(EGovConstants.URI_GET_MESSAGE_BY_ID_AND_USER)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response getMessage(
			@QueryParam("serviceId") String serviceId,
			@QueryParam("userId") String userId,
			@QueryParam("messageId") String messageId
	) {
		if(StringUtil.isEmpty(serviceId) || StringUtil.isEmpty(userId) || StringUtil.isEmpty(userId)){
			String message = "Error getting message "+ messageId +" of user " + userId + " from service "  + serviceId;
    		return getResponse(Response.Status.BAD_REQUEST, message);
		}

		try{
			Message messageToSend = getServicesProvider().getMessage(serviceId, userId, messageId);
			return getResponse(Response.Status.OK, messageToSend);
		} catch(Exception e){
			String message = "Error getting message of user " + userId + " from service "  + serviceId + ". Error: " + e.getMessage();
    		getLogger().log(Level.WARNING, message, e);
    		return getResponse(Response.Status.INTERNAL_SERVER_ERROR, message);
		}
	}

	@POST
	@Path(EGovConstants.URI_SET_CASE_READ)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response setCaseRead(ChangeItemValueRequest<Boolean> changeItemValueRequest) {
		String serviceId = changeItemValueRequest.getServiceId();
		String userId = changeItemValueRequest.getUserId();
		String caseId = changeItemValueRequest.getItemId();
		Boolean read = changeItemValueRequest.getValue();
		if(StringUtil.isEmpty(serviceId) || StringUtil.isEmpty(userId) || StringUtil.isEmpty(caseId)){
			String message = "Error getting case"+ caseId +" for user " + userId + " from service "  + serviceId;
    		return getResponse(Response.Status.BAD_REQUEST, message);
		}
		try{
			getServicesProvider().setCaseRead(serviceId, userId, caseId, read);
			return getResponse(Response.Status.OK, "OK");
		} catch(FinderException e){
			String message = "Case " + caseId +" of user " + userId + " in service "  + serviceId + " not found. Error: " + e.getMessage();
    		getLogger().log(Level.WARNING, message, e);
    		return getResponse(Response.Status.INTERNAL_SERVER_ERROR, message);
		} catch(Exception e){
			String message = "Error setting case " + caseId +" read for user " + userId + " from service "  + serviceId + ". Error: " + e.getMessage();
    		getLogger().log(Level.WARNING, message, e);
    		return getResponse(Response.Status.INTERNAL_SERVER_ERROR, message);
		}
	}

	@POST
	@Path(EGovConstants.URI_SET_MESSAGE_READ)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response setMessageRead(ChangeItemValueRequest<Boolean> changeItemValueRequest) {
		String serviceId = changeItemValueRequest.getServiceId();
		String userId = changeItemValueRequest.getUserId();
		String messageId = changeItemValueRequest.getItemId();
		Boolean read = changeItemValueRequest.getValue();

		if(StringUtil.isEmpty(serviceId) || StringUtil.isEmpty(userId) || StringUtil.isEmpty(userId)){
			String message = "Error getting message "+ messageId +" of user " + userId + " from service "  + serviceId;
    		return getResponse(Response.Status.BAD_REQUEST, message);
		}
		if(read == null){
			read = false;
		}
		try{
			getServicesProvider().setMessageRead(serviceId, userId, messageId, read);
			return getResponse(Response.Status.OK, "OK");
		} catch(FinderException e){
			String message = "Message " + messageId +" of user " + userId + " in service "  + serviceId + " not found. Error: " + e.getMessage();
    		getLogger().log(Level.WARNING, message, e);
    		return getResponse(Response.Status.INTERNAL_SERVER_ERROR, message);
		} catch(Exception e){
			String message = "Error setting message " + messageId +" read for user " + userId + " from service "  + serviceId + ". Error: " +
					e.getMessage();
    		getLogger().log(Level.WARNING, message, e);
    		return getResponse(Response.Status.INTERNAL_SERVER_ERROR, message);
		}
	}

	@GET
	@Path(EGovConstants.URI_GET_CASES_BY_CRITERIA)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response getCasesByCriteria(
			@QueryParam("serviceId") String serviceId,
			@QueryParam("caseNumber") String caseNumber,
			@QueryParam("description") String description,
			@QueryParam("name") String name,
			@QueryParam("personalId") String personalId,
			@QueryParam("status") List<String> statusesList,
			@QueryParam("dateFrom") String dateFrom,
			@QueryParam("dateTo") String dateTo,
			@QueryParam("ownerId") String ownerId,
			@QueryParam("group") List<String> groups,
			@QueryParam("simpleCases") Boolean simpleCases,
			@QueryParam("notGeneralCases") Boolean notGeneralCases
	) {
		if(StringUtil.isEmpty(serviceId)){
			String message = "Error getting cases by query";
    		return getResponse(Response.Status.BAD_REQUEST, message);
		}
		try{
			Collection<Case> casesToSend = getServicesProvider().getCasesByCriteria(serviceId, caseNumber, description, name, personalId,
					statusesList, dateFrom, dateTo, ownerId, groups, simpleCases, notGeneralCases);
			if(casesToSend instanceof Serializable){
				return getResponse(Response.Status.OK, (Serializable)casesToSend);
			}
			return getResponse(Response.Status.OK, new ArrayList<Case>(casesToSend));
		} catch(Exception e){
			String message = "Error getting cases by criterias. Error: " + e.getMessage();
    		getLogger().log(Level.WARNING, message, e);
    		return getResponse(Response.Status.INTERNAL_SERVER_ERROR, message);
		}
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

	private XroadGateway getServicesProvider() {
		return getXroadGateway(ElectronicXRoadServices.BEAN_NAME);
	}

	private XroadGateway getXroadGateway(String beanName) {
		if (beanName == null)
			beanName = ElectronicXRoadServices.BEAN_NAME;

		XroadGateway gateway = ELUtil.getInstance().getBean(beanName);
		return gateway;
	}

	@Override
	@POST
	@Path(EGovConstants.URI_SUBMIT_APPLICATION)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response doSubmitApplication(ApplicationData data) {
		String message = null;
		if (data == null) {
			message = "Data for application were not provided";
			getLogger().warning(message);
			return getResponse(Response.Status.BAD_REQUEST, message);
		}

		try {
			String result = getXroadGateway(CasesDataProvider.BEAN_NAME)
					.submitParkingCardStatement(data.getApplicationName(), data.getUserId(), data.getVariables());

			getLogger().info(result);

			return getResponse(Response.Status.OK, result);
		} catch (Exception e) {
			message = "Error submitting application " + data.getApplicationName() + " by user " + data.getUserId() + " and data: " + data.getVariables();
			getLogger().log(Level.WARNING, message, e);
			return getResponse(Response.Status.INTERNAL_SERVER_ERROR, message);
		}
	}

}