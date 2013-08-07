package is.idega.idegaweb.egov.reykjavik.data;

import is.idega.idegaweb.egov.reykjavik.ReykjavikConstants;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = ReykjavikConstants.DATA_PREFIX+"CONTACT_PERSON_LIST")
public class HomeservicesDataEntity extends ReykjavikEntity implements Serializable {
	
	private static final long serialVersionUID = 6484632528546868203L;

	private static final String PROPERTY_PREFIX = ReykjavikConstants.DATA_PREFIX + "contact_person_list_prop_";
	
	
	public static String PROP_RELATION = PROPERTY_PREFIX + "relation";
	@OneToMany(fetch=FetchType.EAGER, cascade= { CascadeType.ALL})
    @JoinTable(name = ReykjavikConstants.DATA_PREFIX+"J_LIST_CONTACT_PERSON",
            joinColumns = @JoinColumn(name = "list_fk"),
            inverseJoinColumns = @JoinColumn(name = "contact_fk"))
	private List<ContactPersonEntity> contacts;
	
	
	public List<ContactPersonEntity> getContacts() {
		return contacts;
	}
	public void setContacts(List<ContactPersonEntity> contacts) {
		this.contacts = contacts;
	}	
	
}