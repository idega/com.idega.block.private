package is.idega.idegaweb.egov.reykjavik.business;

import is.idega.idegaweb.egov.reykjavik.data.ApartmentServicesData;
import is.idega.idegaweb.egov.reykjavik.data.ApartmentServicesDataEntity;
import is.idega.idegaweb.egov.reykjavik.data.ContactPersonEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.ojb.broker.cache.RuntimeCacheException;

import com.idega.util.ListUtil;

public class ApartmentServicesSubmission extends ReykjavikServicesViewSubmission{

	private ApartmentServicesDataEntity apartmentServicesDataEntity = null;
	@Override
	public Map<String, Object> resolveVariables() {
		Map<String, Object> variables = super.resolveVariables();
		variables.put(ApartmentServicesData.VARIABLE_NAME_ADDITIONAL_DATA, getApartmentServicesDataEntityId(variables));
		return variables;
	}
	
	private Long getApartmentServicesDataEntityId(Map<String, Object> variables ){
		if(apartmentServicesDataEntity != null){
			return apartmentServicesDataEntity.getId();
		}
		List<ContactPersonEntity> contacts = resolveContactsList(variables);
		if(ListUtil.isEmpty(contacts)){
			throw new RuntimeCacheException("Required variable (objlist_contacts) not submitted");
		}
		apartmentServicesDataEntity = new ApartmentServicesDataEntity();
		apartmentServicesDataEntity.setContacts(new HashSet<ContactPersonEntity>(contacts));
		apartmentServicesDataEntity = (ApartmentServicesDataEntity) apartmentServicesDataEntity.merge();
		return apartmentServicesDataEntity.getId();
	}

	@Override
	public void revertChanges() {
		if(apartmentServicesDataEntity != null){
			apartmentServicesDataEntity.remove();
		}
		super.revertChanges();
	}

	
}