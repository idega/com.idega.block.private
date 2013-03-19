package com.idega.egov.hub.test;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.directwebremoting.annotations.Param;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.spring.SpringCreator;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.idega.core.business.DefaultSpringBean;
import com.idega.dwr.business.DWRAnnotationPersistance;
import com.idega.egov.hub.HubConstants;
import com.idega.egov.hub.bean.ApplicationData;
import com.idega.egov.hub.bean.request.ChangeItemValueRequest;
import com.idega.egov.hub.bean.request.ServiceRequest;
import com.idega.util.CoreConstants;
import com.idega.util.ListUtil;
import com.idega.util.URIUtil;
import com.idega.xroad.processes.bean.XRoadProcess;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

@Scope(BeanDefinition.SCOPE_SINGLETON)
@Service(RestfulEGovServicesTest.BEAN_NAME)
@RemoteProxy(creator=SpringCreator.class, name=RestfulEGovServicesTest.DWR_OBJECT, creatorParams={
	@Param(name="beanName", value=RestfulEGovServicesTest.BEAN_NAME),
	@Param(name="javascript", value=RestfulEGovServicesTest.DWR_OBJECT)
})
public class RestfulEGovServicesTest extends DefaultSpringBean implements DWRAnnotationPersistance {

	public static final String	BEAN_NAME = "restfulEGovServicesTest",
								DWR_OBJECT = "RestfulEGovServicesTest";

	private String getHostAndPort() {
		String host = getApplication().getSettings().getProperty("Server URL", "http://www.citizenehub.com:80");
		if (host.endsWith(CoreConstants.SLASH))
			host = host.substring(0, host.length() - 1);
		return host;
	}

	@RemoteMethod
	public String getAvailableServices(String userId) {
		try {
			Client client = new Client();

			URIUtil uriUtil = new URIUtil(getHostAndPort() + HubConstants.URI_SERVICE + HubConstants.URI_GET_AVAILABLE_SERVICES);
			uriUtil.setParameter("userId", userId);
			URI uri = new URI(uriUtil.getUri());

			WebResource webResource = client.resource(uri);

			Object response = webResource.type(MediaType.APPLICATION_JSON).get(XRoadProcess.class);
			return response == null ? "Error" : response.toString();
		} catch (Exception e) {
			getLogger().log(Level.WARNING, "Error getting available services for user: " + userId, e);
		}
		return null;
	}

	@RemoteMethod
	public Map<String,String> getData(String requestUri,Map<String,List<String>> parameters){
		try {
			Client client = new Client();

			URIUtil uriUtil = new URIUtil(getHostAndPort() + HubConstants.URI_SERVICE + requestUri);
			URI uri = new URI(uriUtil.getUri());



			WebResource webResource = client.resource(uri);
			MultivaluedMap<String,String> queryParams = new MultivaluedMapImpl();
			Set<String> keySet = parameters.keySet();
			for(String key : keySet){
				List<String> values = parameters.get(key);
				if(ListUtil.isEmpty(values)){
					continue;
				}
				for(String value : values){
					queryParams.add(key,value);
				}
			}
			String response = webResource.queryParams(queryParams).get(String.class);
			HashMap<String,String> responseMap = new HashMap<String,String>();
			responseMap.put("status","OK");
			responseMap.put("message",response);
			return responseMap;
		} catch (Exception e) {
			getLogger().log(Level.WARNING, "Error ", e);
		}
		return null;
	}

	@RemoteMethod
	public Map<String, String> addMyService(Map<String,List<String>> parameters){
		List<String> values = parameters.get("serviceId");
		String serviceId =  ListUtil.isEmpty(values) ? null : values.get(0);
		values = parameters.get("userId");
		String userId =  ListUtil.isEmpty(values) ? null : values.get(0);
		ServiceRequest addMyServiceRequest = new ServiceRequest();
		addMyServiceRequest.setServiceId(serviceId);
		addMyServiceRequest.setUserId(userId);

		HashMap<String,String> responseMap = new HashMap<String,String>();
		try {
			Client client = new Client();

			URIUtil uriUtil = new URIUtil(getHostAndPort() + HubConstants.URI_SERVICE + HubConstants.URI_ADD_SERVICE_TO_USER);
			URI uri = new URI(uriUtil.getUri());

			WebResource webResource = client.resource(uri);

			String requestJSON = new Gson().toJson(addMyServiceRequest);
			String response = webResource.type(MediaType.APPLICATION_JSON).post(String.class, requestJSON);
			responseMap.put("status","OK");
			responseMap.put("message",response);
			return responseMap;
		} catch (Exception e) {
			String message = "Error adding service";
			getLogger().log(Level.WARNING, message, e);
			responseMap.put("status", "Error");
			responseMap.put("message",message + e.getMessage());
			return responseMap;
		}
	}

	@RemoteMethod
	public Map<String, String> setCaseRead(String serviceId, String userId, String caseId, Boolean read){

		HashMap<String, String> responseMap = new HashMap<String,String>();
		try {
			Client client = new Client();

			URIUtil uriUtil = new URIUtil(getHostAndPort() + HubConstants.URI_SERVICE + HubConstants.URI_SET_CASE_READ);
			URI uri = new URI(uriUtil.getUri());

			ChangeItemValueRequest<Boolean> changeItemValueRequest = new ChangeItemValueRequest<Boolean>();
			changeItemValueRequest.setServiceId(serviceId);
			changeItemValueRequest.setUserId(userId);
			changeItemValueRequest.setItemId(caseId);
			changeItemValueRequest.setValue(read);

			WebResource webResource = client.resource(uri);

			String requestJSON = new Gson().toJson(changeItemValueRequest);
			String response = webResource.type(MediaType.APPLICATION_JSON).post(String.class, requestJSON);
			responseMap.put("status","OK");
			responseMap.put("message",response);
			return responseMap;
		} catch (Exception e) {
			String message = "Error setting case read";
			getLogger().log(Level.WARNING, message, e);
			responseMap.put("status", "Error");
			responseMap.put("message",message + e.getMessage());
			return responseMap;
		}
	}

	@RemoteMethod
	public Map<String,String> setMessageRead(String serviceId, String userId, String messageId, Boolean read){
		HashMap<String,String> responseMap = new HashMap<String,String>();
		try {
			Client client = new Client();

			URIUtil uriUtil = new URIUtil(getHostAndPort() + HubConstants.URI_SERVICE + HubConstants.URI_SET_MESSAGE_READ);
			URI uri = new URI(uriUtil.getUri());

			ChangeItemValueRequest<Boolean> changeItemValueRequest = new ChangeItemValueRequest<Boolean>();
			changeItemValueRequest.setServiceId(serviceId);
			changeItemValueRequest.setUserId(userId);
			changeItemValueRequest.setItemId(messageId);
			changeItemValueRequest.setValue(read);

			WebResource webResource = client.resource(uri);

			String requestJSON = new Gson().toJson(changeItemValueRequest);
			String response = webResource.type(MediaType.APPLICATION_JSON).post(String.class, requestJSON);
			responseMap.put("status","OK");
			responseMap.put("message",response);
			return responseMap;
		} catch (Exception e) {
			String message = "Error setting case read";
			getLogger().log(Level.WARNING, message, e);
			responseMap.put("status", "Error");
			responseMap.put("message",message + e.getMessage());
			return responseMap;
		}
	}

	@RemoteMethod
	public String doSubmitAppplication(String application, String user) {
		try {
			URIUtil util = new URIUtil(getHostAndPort() + HubConstants.URI_SERVICE + HubConstants.URI_SUBMIT_APPLICATION);
			URI uri = new URI(util.getUri());

			Client client = new Client();
			WebResource resource = client.resource(uri);

			ApplicationData data = new ApplicationData();
			data.setApplicationName(application);
			data.setUserId(user);

			Map<String, Object> variables = new HashMap<String, Object>();
			variables.put("ownerPersonalId", "0202774919");
			variables.put("ownerFullName", "Þórhallur Helgason");
			variables.put("ownerAddress", "Laugateigi 33");
			variables.put("ownerPostCode", "105");
			variables.put("ownerAppartmentNumber", "2019365");
			variables.put("relationsToOwnerOfApartments", "spouse");
			variables.put("otherRelationExplanationToOwnerOfApartments", "1");
			variables.put("ownerCommune", "Reykjavik");
			variables.put("ownerEmailAddress", "laddi@idega.is");
			variables.put("repeatedOwnerEmailAddress", "laddi@idega.is");
			variables.put("applicantMobilePhone", "5346759");
			variables.put("applicantPhone", "5346759");
			variables.put("parkingCardComment", "Testing via X-Road");
			variables.put("vehicleRegistrationNumber", "OL109");
			variables.put("ownerAttachments", "");

			data.setVariables(variables);

			String json = new Gson().toJson(data);
			String result = resource.type(MediaType.APPLICATION_JSON_TYPE).post(String.class, json);
			return result;
		} catch (Exception e) {
			getLogger().log(Level.WARNING, "Error submitting application " + application + " by user " + user, e);
			return null;
		}
	}

	@RemoteMethod
	public String getCasesList(String service, String userId) {
		try {
			URIUtil util = new URIUtil(getHostAndPort() + HubConstants.URI_SERVICE + HubConstants.URI_GET_USER_CASES);
			util.setParameter("serviceId", service);
			util.setParameter("userId", userId);
			URI uri = new URI(util.getUri());
			Client client = new Client();
			WebResource resource = client.resource(uri);
			String response = resource.type(MediaType.APPLICATION_JSON_TYPE).get(String.class);
			return response;
		} catch (Exception e) {
			getLogger().log(Level.WARNING, "Error geting cases from service " + service + " for user " + userId, e);
		}
		return null;
	}

}