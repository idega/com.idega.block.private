package is.idega.idegaweb.egov.reykjavik.business;

import is.idega.idegaweb.egov.reykjavik.beans.AdvancedParameter;
import is.idega.idegaweb.egov.reykjavik.data.ContactPersonEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.idega.jbpm.view.ViewSubmissionImpl;
import com.idega.util.ListUtil;

public class ReykjavikServicesViewSubmission extends ViewSubmissionImpl{
	
	@SuppressWarnings("unchecked")
	protected List<ContactPersonEntity> resolveContactsList(Map<String, Object> variables){
		List<AdvancedParameter> variable = (List<AdvancedParameter>) variables.remove("objlist_contact");
		if(ListUtil.isEmpty(variable)){
			return null;
		}
		List<ContactPersonEntity> contacts = new ArrayList<ContactPersonEntity>(variable.size());
		for(AdvancedParameter contactData : variable){
			Object property = contactData.get("name");
			String name;
			if(property instanceof List){
				name = ((List<String>) property).get(0);
			}else{
				name = (String) property;
			}
			ContactPersonEntity contactPersonEntity = new ContactPersonEntity();
			contactPersonEntity.setName(name);
			property = contactData.get("relation");
			String relation;
			if(property instanceof List){
				relation = ((List<String>) property).get(0);
			}else{
				relation = (String) property;
			}
			contactPersonEntity.setRelation(relation);
			property = contactData.get("phone");
			String phone;
			if(property instanceof List){
				phone = ((List<String>) property).get(0);
			}else{
				phone = (String) property;
			}
			contactPersonEntity.setPhone(phone);
			contacts.add(contactPersonEntity);
		}
		return contacts;
	}
	
	public void revertChanges(){}

}
