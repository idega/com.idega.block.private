package com.idega.egov.hub.bean.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Data structure to hold data when adding a service for a user
 *
 * @author valdas
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ServiceRequest implements Serializable {

	private static final long serialVersionUID = -3925303487610962661L;

	private String serviceId, userId;

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Service: " + getServiceId() + ", user: " + getUserId();
	}

}