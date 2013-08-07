package is.idega.idegaweb.egov.reykjavik.data;

import is.idega.idegaweb.egov.reykjavik.ReykjavikConstants;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = ReykjavikConstants.DATA_PREFIX+"CONTACT_PERSON")
public class ContactPersonEntity extends ReykjavikEntity implements Serializable {
	
	private static final long serialVersionUID = 6484632528546868203L;

	private static final String PROPERTY_PREFIX = ReykjavikConstants.DATA_PREFIX + "contact_person_prop";
	
	
	public static String PROP_NAME = PROPERTY_PREFIX + "name";
	@Column(name = "name")
	private String name;
	
	public static String PROP_RELATION = PROPERTY_PREFIX + "relation";
	// TODO: should be int and mapped somewhere
	@Column(name = "relation")
	private String relation;
	
	
	public static String PROP_PHONE = PROPERTY_PREFIX + "phone";
	// TODO: probably should be mapped to some already existing contact table
	@Column(name = "phone")
	private String phone;

	public String getRelation() {
		return relation;
	}


	public void setRelation(String relation) {
		this.relation = relation;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
}
