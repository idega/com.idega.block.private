package com.idega.egov.hub.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.idega.util.StringUtil;

/**
 * Data structure to hold e-application data when submitting it
 *
 * @author valdas
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ApplicationData implements Serializable {

	private static final long serialVersionUID = -2627425524333877888L;

	private String userId, applicationName;

	private Map<String, Object> variables;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public Map<String, Object> getVariables() {
		return variables;
	}

	public void setVariables(Map<String, Object> variables) {
		this.variables = variables;
	}

	public void addVariable(String name, Serializable value) {
		if (StringUtil.isEmpty(name) || value == null)
			return;

		if (variables == null)
			variables = new HashMap<String, Object>();

		variables.put(name, value);
	}

	@Override
	public String toString() {
		return "User " + getUserId() + ", application " + getApplicationName() + ", variables " + getVariables();
	}
}