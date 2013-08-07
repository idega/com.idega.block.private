package is.idega.idegaweb.egov.reykjavik.business;

import is.idega.idegaweb.egov.reykjavik.beans.AdvancedParameter;
import is.idega.idegaweb.egov.reykjavik.data.EveningAndWeekendServiceData;

import java.util.List;
import java.util.Map;

public class EveningAndWeekendServicesSubmission extends ReykjavikServicesViewSubmission{

	@Override
	public Map<String, Object> resolveVariables() {
		Map<String, Object> variables = super.resolveVariables();
		Object variable = variables.remove(EveningAndWeekendServiceData.VARIABLE_NAME_DAYS_OF_SERVICES);
		if(variable != null){
			@SuppressWarnings("unchecked")
			List<AdvancedParameter> daysData = (List<AdvancedParameter>) variable;
			Map<String,String> resolvedDays = EveningAndWeekendServiceData.getDaysVariables(daysData);
			variables.putAll(resolvedDays);
		}
		return variables;
	}
	
	

	
}
