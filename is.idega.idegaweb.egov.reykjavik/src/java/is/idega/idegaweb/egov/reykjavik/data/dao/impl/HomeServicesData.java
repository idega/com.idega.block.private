package is.idega.idegaweb.egov.reykjavik.data.dao.impl;

import is.idega.idegaweb.egov.reykjavik.data.ContactPersonEntity;
import is.idega.idegaweb.egov.reykjavik.data.HomeservicesDataEntity;
import is.idega.idegaweb.egov.reykjavik.data.ReykjavikGeneralData;
import is.idega.idegaweb.egov.reykjavik.data.dao.ReykjavikDao;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.idega.util.CoreConstants;
import com.idega.util.StringUtil;
import com.idega.util.expression.ELUtil;

public class HomeServicesData  extends ReykjavikGeneralData{
	
	public static final String VARIABLE_NAME_MARITAL_STATUS = "string_maritalStatus";
	
	public static final String VARIABLE_NAME_SPOUSE_NAME = "string_spouseName";
	public static final String VARIABLE_NAME_SPOUSE_PERSONAL_ID = "string_spousePersonalId";
	public static final String VARIABLE_NAME_SPOUSE_OCCUPATION = "string_spouseOccupation";
	
	public static final String VARIABLE_NAME_CHILD_NAME = "string_childName";
	public static final String VARIABLE_NAME_CHILD_PERSONAL_ID = "string_childPersonalId";
	
	public static final String VARIABLE_NAME_CHILDREN_AMOUNT = "long_childrenAmount";
	public static final String VARIABLE_NAME_CHILD_BIRTH_DATE = "date_childBirthDate";
	
	public static final String VARIABLE_NAME_DOCTOR_NAME = "string_doctorName";
	public static final String VARIABLE_NAME_DOCTOR_PHONE = "string_doctorPhone";
	
	public static final String VARIABLE_NAME_IS_SMOKING_AT_HOME = "string_isSmokingAtHome";
	public static final String VARIABLE_NAME_IS_PETS_AT_HOME = "string_isPetsAtHome";
	
	public static final String VARIABLE_NAME_ASSISTANCE_DESCRIPTION = "string_assistanceDescription";
	
	public static final String VARIABLE_NAME_ANOTHER_APPLICANT = "string_anotherApplicant";
	
	public static final String VARIABLE_NAME_ADDITIONAL_DATA = "long_homeServicesData";
	
	@Autowired
	private ReykjavikDao reykjavikDao;
	
	private HomeservicesDataEntity homeservicesDataEntity;
	
	private HomeservicesDataEntity getHomeservicesDataEntity(){
		if(homeservicesDataEntity != null){
			return homeservicesDataEntity;
		}
		Long variable =  (Long) getValue(VARIABLE_NAME_ADDITIONAL_DATA);
		if(variable == null){
			return null;
		}
		if(reykjavikDao == null){
			ELUtil.getInstance().autowire(this);
		}
		homeservicesDataEntity = reykjavikDao.getById(variable, HomeservicesDataEntity.class);
		return homeservicesDataEntity;
	}
	
	public String getMaritalStatus() {
		String variable = (String) getValue(VARIABLE_NAME_MARITAL_STATUS);
		return variable == null ? CoreConstants.EMPTY : variable;
	}
	public void setMaritalStatus(String maritalStatus) {
		add(VARIABLE_NAME_MARITAL_STATUS, maritalStatus);
	}
	public String getSpouseName() {
		String variable = (String) getValue(VARIABLE_NAME_SPOUSE_NAME);
		return variable == null ? CoreConstants.EMPTY : variable;
	}
	public void setSpouseName(String spouseName) {
		add(VARIABLE_NAME_SPOUSE_NAME, spouseName);
	}
	public String getSpousePersonalId() {
		String variable = (String) getValue(VARIABLE_NAME_SPOUSE_PERSONAL_ID);
		return variable == null ? CoreConstants.EMPTY : variable;
	}
	public void setSpousePersonalId(String spousePersonalId) {
		add(VARIABLE_NAME_SPOUSE_PERSONAL_ID, spousePersonalId);
	}
	public String getSpouseOccupation() {
		return (String) getValue(VARIABLE_NAME_SPOUSE_OCCUPATION);
	}
	public void setSpouseOccupation(String spouseOccupation) {
		add(VARIABLE_NAME_SPOUSE_OCCUPATION, spouseOccupation);
	}
	public String getChildName() {
		String variable = (String) getValue(VARIABLE_NAME_CHILD_NAME);
		return variable == null ? CoreConstants.EMPTY : variable;
	}
	public void setChildName(String childName) {
		add(VARIABLE_NAME_CHILD_NAME, childName);
	}
	public String getChildPersonalId() {
		String variable = (String) getValue(VARIABLE_NAME_CHILD_PERSONAL_ID);
		return variable == null ? CoreConstants.EMPTY : variable;
	}
	public void setChildPersonalId(String childPersonalId) {
		add(VARIABLE_NAME_CHILD_PERSONAL_ID, childPersonalId);
	}
	public Long getChildrenAmount() {
		Long variable = (Long) getValue(VARIABLE_NAME_CHILDREN_AMOUNT);
		return variable;
	}
	public void setChildrenAmount(Long childrenAmount) {
		add(VARIABLE_NAME_CHILDREN_AMOUNT, childrenAmount);
	}
	public Date getChildBirthDate() {
		Date variable = (Date) getValue(VARIABLE_NAME_CHILD_BIRTH_DATE);
		return variable;
	}
	public void setChildBirthDate(Date childBirthDate) {
		add(VARIABLE_NAME_CHILD_BIRTH_DATE, childBirthDate);
	}
	public String getDoctorName() {
		String variable = (String) getValue(VARIABLE_NAME_DOCTOR_NAME);
		return variable == null ? CoreConstants.EMPTY : variable;
	}
	public void setDoctorName(String doctorName) {
		add(VARIABLE_NAME_DOCTOR_NAME, doctorName);
	}
	public String getDoctorPhone() {
		String variable = (String) getValue(VARIABLE_NAME_DOCTOR_PHONE);
		return variable == null ? CoreConstants.EMPTY : variable;
	}
	public void setDoctorPhone(String doctorPhone) {
		add(VARIABLE_NAME_DOCTOR_PHONE, doctorPhone);
	}
	
	public List<ContactPersonEntity> getContacts() {
		HomeservicesDataEntity homeservicesDataEntity = getHomeservicesDataEntity();
		if(homeservicesDataEntity == null){
			return Collections.emptyList();
		}
		
		List<ContactPersonEntity> contacts = homeservicesDataEntity.getContacts();
		if(contacts == null){
			return Collections.emptyList();
		}
		
		return contacts;
	}
	
	public boolean isSmokingAtHome() {
		String variable = (String) getValue(VARIABLE_NAME_IS_SMOKING_AT_HOME);
		return !StringUtil.isEmpty(variable) && variable.equals("Y");
	}
	public void setSmokingAtHome(boolean smokingAtHome) {
		String variable = smokingAtHome ? "Y" : "N";
		add(VARIABLE_NAME_IS_SMOKING_AT_HOME, variable);
	}
	
	public boolean isPetsAtHome() {
		String variable = (String) getValue(VARIABLE_NAME_IS_PETS_AT_HOME);
		return !StringUtil.isEmpty(variable) && variable.equals("Y");
	}
	public void setPetsAtHome(boolean isPetsAtHome) {
		String variable = isPetsAtHome ? "Y" : "N";
		add(VARIABLE_NAME_IS_PETS_AT_HOME, variable);
	}
	public String getAssistanceDescription() {
		String variable = (String) getValue(VARIABLE_NAME_ASSISTANCE_DESCRIPTION);
		return variable == null ? CoreConstants.EMPTY : variable;
	}
	public void setAssistanceDescription(String assistanceDescription) {
		add(VARIABLE_NAME_ASSISTANCE_DESCRIPTION, assistanceDescription);
	}
	
	public String getAnotherApplicant() {
		String variable = (String) getValue(VARIABLE_NAME_ANOTHER_APPLICANT);
		return variable == null ? CoreConstants.EMPTY : variable;
	}
	public void setAnotherApplicant(String anotherApplicant) {
		add(VARIABLE_NAME_ANOTHER_APPLICANT, anotherApplicant);
	}
	
}
