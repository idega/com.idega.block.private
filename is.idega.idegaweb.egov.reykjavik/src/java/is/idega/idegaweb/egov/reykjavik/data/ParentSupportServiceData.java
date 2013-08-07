package is.idega.idegaweb.egov.reykjavik.data;

public class ParentSupportServiceData extends ReykjavikGeneralData{
	
	public static final String VARIABLE_NAME_EDUCATION = "string_education";
	public static final String VARIABLE_NAME_PREVIOUS_WORK = "string_previousWork";
	public static final String VARIABLE_NAME_CURRENT_JOB = "string_currentJob";
	public static final String VARIABLE_NAME_EMPLOYER = "string_employer";
	public static final String VARIABLE_NAME_SPOUSE_NAME = "string_spouseName";
	public static final String VARIABLE_NAME_SPOUSE_PERSONAL_ID = "string_spousePersonalId";
	public static final String VARIABLE_NAME_SPOUSE_ADDRESS = "string_spouseAddress";
	public static final String VARIABLE_NAME_SPOUSE_POSTAL_CODE = "string_spousePostalCode";
	public static final String VARIABLE_NAME_SPOUSE_PHONE = "string_spousePhone";
	public static final String VARIABLE_NAME_SPOUSE_EMAIL = "string_spouseEmail";
	public static final String VARIABLE_NAME_SPOUSE_EDUCATION = "string_spouseEducation";
	public static final String VARIABLE_NAME_SPOUSE_PREVIOUS_WORK = "string_spousePreviousWork";
	public static final String VARIABLE_NAME_SPOUSE_CURRENT_JOB = "string_spouseCurrentJob";
	public static final String VARIABLE_NAME_SPOUSE_EMPLOYER = "string_spouseEmployer";
	public static final String VARIABLE_NAME_CHILDREN_APPLICANT = "string_childrenApplicant";
	public static final String VARIABLE_NAME_OTHER_HOUSEHOLD = "string_otherHouseHold";
	public static final String VARIABLE_NAME_HOUSING_TYPE = "string_housingType";
	public static final String VARIABLE_NAME_SUPPORT_REASON = "string_supportReason";
	public static final String VARIABLE_NAME_REFERALS = "string_referals";
	
	
	public String getEducation() {
		return (String) getValue(VARIABLE_NAME_EDUCATION);
	}
	public void setEducation(String education) {
		add(VARIABLE_NAME_EDUCATION, education);
	}
	public String getPreviousWork() {
		return (String) getValue(VARIABLE_NAME_PREVIOUS_WORK);
	}
	public void setPreviousWork(String previousWork) {
		add(VARIABLE_NAME_PREVIOUS_WORK, previousWork);
	}
	public String getCurrentJob() {
		return (String) getValue(VARIABLE_NAME_CURRENT_JOB);
	}
	public void setCurrentJob(String currentJob) {
		add(VARIABLE_NAME_CURRENT_JOB, currentJob);
	}
	public String getEmployer() {
		return (String) getValue(VARIABLE_NAME_EMPLOYER);
	}
	public void setEmployer(String employer) {
		add(VARIABLE_NAME_EMPLOYER, employer);
	}
	public String getSpouseName() {
		return (String) getValue(VARIABLE_NAME_SPOUSE_NAME);
	}
	public void setSpouseName(String spouseName) {
		add(VARIABLE_NAME_SPOUSE_NAME, spouseName);
	}
	public String getSpousePersonalId() {
		return (String) getValue(VARIABLE_NAME_SPOUSE_PERSONAL_ID);
	}
	public void setSpousePersonalId(String spousePersonalId) {
		add(VARIABLE_NAME_SPOUSE_PERSONAL_ID, spousePersonalId);
	}
	public String getSpouseAddress() {
		return (String) getValue(VARIABLE_NAME_SPOUSE_ADDRESS);
	}
	public void setSpouseAddress(String spouseAddress) {
		add(VARIABLE_NAME_SPOUSE_ADDRESS, spouseAddress);
	}
	public String getSpousePostalCode() {
		return (String) getValue(VARIABLE_NAME_SPOUSE_POSTAL_CODE);
	}
	public void setSpousePostalCode(String spousePostalCode) {
		add(VARIABLE_NAME_SPOUSE_POSTAL_CODE, spousePostalCode);
	}
	public String getSpousePhone() {
		return (String) getValue(VARIABLE_NAME_SPOUSE_PHONE);
	}
	public void setSpousePhone(String spousePhone) {
		add(VARIABLE_NAME_SPOUSE_PHONE, spousePhone);
	}
	public String getSpouseEmail() {
		return (String) getValue(VARIABLE_NAME_SPOUSE_EMAIL);
	}
	public void setSpouseEmail(String spouseEmail) {
		add(VARIABLE_NAME_SPOUSE_EMAIL, spouseEmail);
	}
	public String getSpouseEducation() {
		return (String) getValue(VARIABLE_NAME_SPOUSE_EDUCATION);
	}
	public void setSpouseEducation(String spouseEducation) {
		add(VARIABLE_NAME_SPOUSE_EDUCATION, spouseEducation);
	}
	public String getSpousePreviousWork() {
		return (String) getValue(VARIABLE_NAME_SPOUSE_PREVIOUS_WORK);
	}
	public void setSpousePreviousWork(String spousePreviousWork) {
		add(VARIABLE_NAME_SPOUSE_PREVIOUS_WORK, spousePreviousWork);
	}
	public String getSpouseCurrentJob() {
		return (String) getValue(VARIABLE_NAME_SPOUSE_CURRENT_JOB);
	}
	public void setSpouseCurrentJob(String spouseCurrentJob) {
		add(VARIABLE_NAME_SPOUSE_CURRENT_JOB, spouseCurrentJob);
	}
	public String getSpouseEmployer() {
		return (String) getValue(VARIABLE_NAME_SPOUSE_EMPLOYER);
	}
	public void setSpouseEmployer(String spouseEmployer) {
		add(VARIABLE_NAME_SPOUSE_EMPLOYER, spouseEmployer);
	}
	public String getChildrenApplicant() {
		return (String) getValue(VARIABLE_NAME_CHILDREN_APPLICANT);
	}
	public void setChildrenApplicant(String childrenApplicant) {
		add(VARIABLE_NAME_CHILDREN_APPLICANT, childrenApplicant);
	}
	public String getOtherHouseHold() {
		return (String) getValue(VARIABLE_NAME_OTHER_HOUSEHOLD);
	}
	public void setOtherHouseHold(String otherHouseHold) {
		add(VARIABLE_NAME_OTHER_HOUSEHOLD, otherHouseHold);
	}
	public String getHousingType() {
		return (String) getValue(VARIABLE_NAME_HOUSING_TYPE);
	}
	public void setHousingType(String housingType) {
		add(VARIABLE_NAME_HOUSING_TYPE, housingType);
	}
	public String getSupportReason() {
		return (String) getValue(VARIABLE_NAME_SUPPORT_REASON);
	}
	public void setSupportReason(String supportReason) {
		add(VARIABLE_NAME_SUPPORT_REASON, supportReason);
	}
	public String getReferals() {
		return (String) getValue(VARIABLE_NAME_REFERALS);
	}
	public void setReferals(String referals) {
		add(VARIABLE_NAME_REFERALS, referals);
	}
}
