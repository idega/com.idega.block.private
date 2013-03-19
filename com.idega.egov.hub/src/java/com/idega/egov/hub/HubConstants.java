package com.idega.egov.hub;

public class HubConstants {

	public static final String IW_BUNDLE_IDENTIFIER = "com.idega.egov";

	public static final String	URI_SERVICE = "/hub/egov",

								// Services
								URI_GET_AVAILABLE_SERVICES = "/services/available",
								URI_GET_SERVICES_BY_USER = "/services/available/user",

								URI_ADD_SERVICE_TO_USER = "/services/add",

								// Cases
								URI_GET_USER_CASES = "/cases/user",
								URI_GET_CASE_BY_ID_AND_USER  = "/cases/id/user",
								URI_GET_UNREAD_CASES_BY_USER = "/cases/unread/user",
								URI_GET_CASES_BY_CRITERIA = "/cases/criteria",

								URI_SET_CASE_READ = "/cases/set/read",

								// Messages
								URI_GET_USER_MESSAGES = "/messages/user",
								URI_GET_MESSAGE_BY_ID_AND_USER = "/messages/id/user",
								URI_GET_UNREAD_MESSAGES_BY_USER = "/messages/unread/user",

								URI_SET_MESSAGE_READ = "/messages/set/read",

								//	Submit e-application
								URI_SUBMIT_APPLICATION = "/submit/app",

								// Parameters
								PARAMETER_USER_ID = "userId",
								PARAMETER_SERVICE_ID = "serviceId",
								PARAMETER_CASE_ID = "caseId",
								PARAMETER_MESSAGE_ID = "messageId",
								PARAMETER_READ = "read",
								PARAMETER_CASE_NUMBER = "caseNumber";

}