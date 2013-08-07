package is.idega.idegaweb.egov.reykjavik.data;

import is.idega.idegaweb.egov.reykjavik.beans.AdvancedParameter;

import java.util.ArrayList;
import java.util.List;

public class HousingBenefitsServiceData extends ReykjavikGeneralData{
	
	public static final String VARIABLE_NAME_SEAT = "string_seat";
	public static final String VARIABLE_NAME_JOB_POSTING = "string_jobPosting";
	public static final String VARIABLE_NAME_LOCATION = "string_location";
	public static final String VARIABLE_NAME_SIZE= "double_locationSize";
	public static final String VARIABLE_NAME_NUMBER_OF_ROOMS = "long_numberOfRooms";
	public static final String VARIABLE_NAME_OTHER_RESIDENTS = "objlist_otherResidents";
	public static final String VARIABLE_NAME_ANOTHER_APPLICANT_STATEMENTS = "string_anotherApplicantStatements";
	
	public static final String VARIABLE_NAME_ACCOUNT_HOLDER_NAME = "string_accountHolderName";
	public static final String VARIABLE_NAME_HOLDER_PERSONAL_ID = "string_holderPersonalId";
	public static final String VARIABLE_NAME_BRANCH_NUMBER = "string_branchNumber";
	public static final String VARIABLE_NAME_LEDGER = "string_ledger";
	public static final String VARIABLE_NAME_ACCOUNT_NUMBER = "string_accountNumber";
	
	public String getAccountNumber() {
		return (String) getValue(VARIABLE_NAME_ACCOUNT_NUMBER);
	}
	public void setAccountNumber(String accountNumber) {
		add(VARIABLE_NAME_ACCOUNT_NUMBER, accountNumber);
	}
	public String getLedger() {
		return (String) getValue(VARIABLE_NAME_LEDGER);
	}
	public void setLedger(String ledger) {
		add(VARIABLE_NAME_LEDGER, ledger);
	}
	public String getBranchNumber() {
		return (String) getValue(VARIABLE_NAME_BRANCH_NUMBER);
	}
	public void setBranchNumber(String branchNumber) {
		add(VARIABLE_NAME_BRANCH_NUMBER, branchNumber);
	}
	public String getHolderPersonalId() {
		return (String) getValue(VARIABLE_NAME_HOLDER_PERSONAL_ID);
	}
	public void setHolderPersonalId(String personalId) {
		add(VARIABLE_NAME_HOLDER_PERSONAL_ID, personalId);
	}
	public String getAccountHolderName() {
		return (String) getValue(VARIABLE_NAME_ACCOUNT_HOLDER_NAME);
	}
	public void setAccountHolderName(String accountHolderName) {
		add(VARIABLE_NAME_ACCOUNT_HOLDER_NAME, accountHolderName);
	}
	
	public String getSeat() {
		return (String) getValue(VARIABLE_NAME_SEAT);
	}
	public void setSeat(String seat) {
		add(VARIABLE_NAME_SEAT, seat);
	}
	public String getJobPosting() {
		return (String) getValue(VARIABLE_NAME_JOB_POSTING);
	}
	public void setJobPosting(String jobPosting) {
		add(VARIABLE_NAME_JOB_POSTING, jobPosting);
	}
	
	public String getLocation() {
		return (String) getValue(VARIABLE_NAME_LOCATION);
	}
	public void setLocation(String location) {
		add(VARIABLE_NAME_LOCATION, location);
	}
	public Double getLocationSize() {
		return (Double) getValue(VARIABLE_NAME_SIZE);
	}
	public void setLocationSize(Double locationSize) {
		add(VARIABLE_NAME_SIZE, locationSize);
	}
	public Long getNumberOfRooms() {
		return (Long) getValue(VARIABLE_NAME_NUMBER_OF_ROOMS);
	}
	public void setNumberOfRooms(Long numberOfRooms) {
		add(VARIABLE_NAME_NUMBER_OF_ROOMS, numberOfRooms);
	}
	@SuppressWarnings("unchecked")
	public List<AdvancedParameter> getOtherResidents() {
		return (List<AdvancedParameter>) getValue(VARIABLE_NAME_OTHER_RESIDENTS);
	}
	public void setOtherResidents(List<AdvancedParameter> otherResidents) {
		add(VARIABLE_NAME_OTHER_RESIDENTS, new ArrayList<AdvancedParameter>(otherResidents));
	}
	public String getAnotherApplicantStatements() {
		return (String) getValue(VARIABLE_NAME_ANOTHER_APPLICANT_STATEMENTS);
	}
	public void setAnotherApplicantStatements(String anotherApplicantStatements) {
		add(VARIABLE_NAME_ANOTHER_APPLICANT_STATEMENTS, anotherApplicantStatements);
	}

}
