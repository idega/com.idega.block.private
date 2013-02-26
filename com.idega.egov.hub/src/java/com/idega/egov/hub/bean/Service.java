package com.idega.egov.hub.bean;

import is.idega.idegaweb.egov.citizen.data.CitizenRemoteServices;

import java.io.Serializable;

/**
 * Data structure to hold remote service description
 *
 * @author valdas
 *
 */
public class Service implements Serializable {

	private static final long serialVersionUID = -521813386789440115L;

	private String name, address, id;

	public Service(){
		super();
	}

	public Service(String name, String address, String id) {
		this.name = name;
		this.address = address;
		this.id = id;
	}

	public Service(CitizenRemoteServices service){
		this.name = service.getServerName();
		this.address = service.getAddress();
		this.id = service.getPrimaryKey().toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
