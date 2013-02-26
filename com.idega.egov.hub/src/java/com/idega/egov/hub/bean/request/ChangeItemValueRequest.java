package com.idega.egov.hub.bean.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Data structure to hold request key and serializable value
 *
 * @author valdas
 *
 * @param <T>
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ChangeItemValueRequest<T extends Serializable> extends ServiceRequest {

	private static final long serialVersionUID = 5527805367067567401L;

	public ChangeItemValueRequest(){
		super();
	}

	private String itemId;
	private T value;

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return super.toString() + ", item: " + getItemId() + ", value: " + getValue();
	}

}
