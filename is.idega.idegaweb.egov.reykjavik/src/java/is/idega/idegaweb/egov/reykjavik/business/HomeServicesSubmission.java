package is.idega.idegaweb.egov.reykjavik.business;

import is.idega.idegaweb.egov.reykjavik.data.ContactPersonEntity;
import is.idega.idegaweb.egov.reykjavik.data.HomeservicesDataEntity;
import is.idega.idegaweb.egov.reykjavik.data.dao.impl.HomeServicesData;

import java.util.List;
import java.util.Map;

import com.idega.util.ListUtil;

public class HomeServicesSubmission extends ReykjavikServicesViewSubmission{

	private HomeservicesDataEntity homeservicesDataEntity = null;
	@Override
	public Map<String, Object> resolveVariables() {
		Map<String, Object> variables = super.resolveVariables();
		variables.put(HomeServicesData.VARIABLE_NAME_ADDITIONAL_DATA, getHomeservicesDataEntityId(variables));
		return variables;
	}
	
	private Long getHomeservicesDataEntityId(Map<String, Object> variables ){
		if(homeservicesDataEntity != null){
			return homeservicesDataEntity.getId();
		}
		List<ContactPersonEntity> contacts = resolveContactsList(variables);
		if(ListUtil.isEmpty(contacts)){
			return null;
		}
		homeservicesDataEntity = new HomeservicesDataEntity();
		homeservicesDataEntity.setContacts(contacts);
		homeservicesDataEntity = (HomeservicesDataEntity) homeservicesDataEntity.merge();
		return homeservicesDataEntity.getId();
	}

	@Override
	public void revertChanges() {
		if(homeservicesDataEntity != null){
			homeservicesDataEntity.remove();
		}
		super.revertChanges();
	}

	
}
