package com.idega.egov.hub.restful;

import java.util.List;

import javax.ws.rs.core.Response;

import com.idega.egov.hub.bean.ApplicationData;
import com.idega.egov.hub.bean.request.ChangeItemValueRequest;
import com.idega.egov.hub.bean.request.ServiceRequest;
import com.idega.xroad.processes.bean.XRoadProcess;

/**
 * Gateway to the e-Gov services via restful web service
 *
 * @author valdas
 *
 */
public interface RestfulEGovServices {

	/**
	 * Returns collection of available services {@link XRoadProcess}, GET: /mobile/egov/services/available
	 *
	 * @param userId - services for a user
	 * @return collection of services {@link com.idega.egov.hub.bean.Service}
	 */
	public Response getAvailableServices(String userId);

	/**
	 * Returns collection of messages {@link com.idega.xroad.bean.Message} for a user from provided service, GET: /mobile/egov/messages/user
	 *
	 * @param serviceId
	 * @param userId
	 * @return collection of messages {@link com.idega.xroad.bean.Message}
	 */
	public Response getMessagesByUser(String serviceId, String userId);

	/**
	 * Returns collection of unread messages {@link com.idega.xroad.bean.Message} for a user from a provided service, GET: /mobile/egov/messages/unread/user
	 *
	 * @param serviceId
	 * @param userId
	 * @return collection of messages {@link com.idega.xroad.bean.Message}
	 */
	public Response getUnreadMessagesByUser(String serviceId, String userId);

	/**
	 * Returns collection of cases {@link com.idega.xroad.bean.Case} for a user from provided service, GET: /mobile/egov/cases/user
	 *
	 * @param serviceId
	 * @param userId
	 * @return collection of cases {@link com.idega.xroad.bean.Case}
	 */
	public Response getCasesByUser(String serviceId, String userId);

	/**
	 * Returns collection of unread cases {@link com.idega.xroad.bean.Case} for a user from a provided service, GET: /mobile/egov/cases/unread/user
	 *
	 * @param serviceId
	 * @param userId
	 * @return collection of cases {@link com.idega.xroad.bean.Case}
	 */
	public Response getUnreadCasesByUser(String serviceId, String userId);

	/**
	 * Returns message {@link com.idega.xroad.bean.Message} for a user from provided service, GET: /mobile/egov/messages/id/user
	 *
	 * @param serviceId
	 * @param userId
	 * @return message {@link com.idega.xroad.bean.Message}
	 */
	public Response getMessage(String serviceId, String userId, String messageId);

	/**
	 * Returns case {@link com.idega.xroad.bean.Case} for a user from provided service, GET: /mobile/egov/cases/id/user
	 *
	 * @param serviceId
	 * @param userId
	 * @return case {@link com.idega.xroad.bean.Case}
	 */
	public Response getCase(String serviceId, String userId, String caseId);

	/**
	 * Returns a list with services {@link com.idega.egov.hub.bean.Service} for a provided user, GET: /mobile/egov/services/available/user
	 *
	 * @param userId
	 * @return
	 */
	public Response getUserServices(String userId);

	/**
	 * Adds service for a user, POST: /mobile/egov/service/add
	 *
	 * @param request - data structure of {@link ServiceRequest}
	 * @return
	 */
	public Response addServiceToUser(ServiceRequest request);

	/**
	 * Sets that case is read or not read by user POST: /mobile/egov/cases/set/read
	 * @param changeItemValueRequest - data structure {@link ChangeItemValueRequest}
	 * @return
	 */
	public Response setCaseRead(ChangeItemValueRequest<Boolean> changeItemValueRequest);

	/**
	 * Sets that message is read or not read by user POST: /mobile/egov/messages/set/read
	 * @param changeItemValueRequest - data structure {@link ChangeItemValueRequest}
	 * @return
	 */
	public Response setMessageRead(ChangeItemValueRequest<Boolean> changeItemValueRequest);

	/**
	 * Searches cases by these parameters. Service ID must be specified. If any other than serviceId parameter is empty or null than it will not be in search.
	 * For example if only parameters serviceId, dateFrom, dateTo and owner are specified than cases will be returned by these parameters.
	 * GET: /mobile/egov/cases/criteria
	 * @return collection of cases {@link com.idega.xroad.bean.Case}
	 */
	public Response getCasesByCriteria(String serviceId,String caseNumber, String description, String name, String personalId, List<String> statuses, String dateFrom, String dateTo, String ownerId, List<String> groups, Boolean simpleCases, Boolean notGeneralCases);

	/**
	 *	Method to submit electronic application. POST: /mobile/egov/submit/app
	 *
	 * @param data object of {@link ApplicationData}
	 * @return
	 */
	public Response doSubmitApplication(ApplicationData data);

}