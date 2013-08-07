package is.idega.idegaweb.egov.reykjavik.data;

import is.idega.idegaweb.egov.reykjavik.ReykjavikConstants;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = ReykjavikConstants.DATA_PREFIX+"APARTMENT_SERVICES")
public class ApartmentServicesDataEntity extends ReykjavikEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6420879848851566819L;


	private static final String PROPERTY_PREFIX = ReykjavikConstants.DATA_PREFIX + "contact_person_list_prop_";
	
	
	public static String PROP_RELATION = PROPERTY_PREFIX + "relation";
	@OneToMany(fetch=FetchType.EAGER, cascade= { CascadeType.ALL})
    @JoinTable(name = ReykjavikConstants.DATA_PREFIX+"J_APARTMENT_SERVICES_CONTACTS",
            joinColumns = @JoinColumn(name = "list_fk"),
            inverseJoinColumns = @JoinColumn(name = "contact_fk"))
	private Set<ContactPersonEntity> contacts;
	
	public Set<ContactPersonEntity> getContacts() {
		return contacts;
	}
	public void setContacts(Set<ContactPersonEntity> contacts) {
		this.contacts = contacts;
	}	
	
}