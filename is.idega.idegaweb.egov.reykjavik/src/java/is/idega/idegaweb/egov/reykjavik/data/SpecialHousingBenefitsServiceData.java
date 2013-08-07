package is.idega.idegaweb.egov.reykjavik.data;

import com.idega.util.CoreConstants;

public class SpecialHousingBenefitsServiceData extends ReykjavikGeneralData {
	
	public static final String VARIABLE_NAME_SPOUSE_NAME = "string_spouseName";
	public static final String VARIABLE_NAME_SPOUSE_PERSONAL_ID = "string_spousePersonalId";
	
	public String getSpouseName(){
		String spouseName = (String) getValue(VARIABLE_NAME_SPOUSE_NAME);
		return spouseName == null ? CoreConstants.EMPTY : spouseName;
	}
	
	public void setSpouseName(String spouseName){
		add(VARIABLE_NAME_SPOUSE_NAME, spouseName);
	}

	public String getSpousePersonalId(){
		return (String) getValue(VARIABLE_NAME_SPOUSE_PERSONAL_ID);
	}
	
	public void setSpousePersonalId(String spousePersonalId){
		add(VARIABLE_NAME_SPOUSE_PERSONAL_ID, spousePersonalId);
	}
}
