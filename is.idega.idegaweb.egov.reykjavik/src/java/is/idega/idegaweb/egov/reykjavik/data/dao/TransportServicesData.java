package is.idega.idegaweb.egov.reykjavik.data.dao;

import is.idega.idegaweb.egov.reykjavik.data.ReykjavikGeneralData;

import com.idega.util.CoreConstants;

public class TransportServicesData extends ReykjavikGeneralData{
	
	public static final String VARIABLE_NAME_SPOUSE_NAME = "string_spouseName";
	
	
	public String getSpouseName(){
		String spouseName = (String) getValue(VARIABLE_NAME_SPOUSE_NAME);
		return spouseName == null ? CoreConstants.EMPTY : spouseName;
	}
	
	public void setSpouseName(String spouseName){
		add(VARIABLE_NAME_SPOUSE_NAME, spouseName);
	}
}
