package is.idega.idegaweb.egov.reykjavik.data;

import is.idega.idegaweb.egov.reykjavik.beans.ReykjavikServices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.idega.util.ListUtil;
import com.idega.util.expression.ELUtil;

public class PersonalizedAssistanceData extends ReykjavikGeneralData {
	public static final String VARIABLE_NAME_REAL_ADDRESS = "string_realAddress";
	public static final String VARIABLE_NAME_REAL_POST_CODE = "string_realPostCode";
	public static final String VARIABLE_NAME_GUARDIAN_NAME = "string_guardianName";
	public static final String VARIABLE_NAME_GUARDIAN_PERSONAL_ID = "string_guardianPersonalId";
	public static final String VARIABLE_NAME_GUARDIAN_ADDRESS = "string_guardianAddress";
	public static final String VARIABLE_NAME_GUARDIAN_POSTCODE = "string_guardianPostCode";
	public static final String VARIABLE_NAME_GUARDIAN_PHONE = "string_guardianPhone";
	public static final String VARIABLE_NAME_GUARDIAN_GSM = "string_guardianGSM";
	public static final String VARIABLE_NAME_GUARDIAN_EMAIL = "string_guardianEmail";
	
	public static final String VARIABLE_NAME_RELATIVE_NAME = "string_relativeName";
	public static final String VARIABLE_NAME_RELATIVE_PERSONAL_ID = "string_relativePersonalId";
	public static final String VARIABLE_NAME_RELATIVE_ADDRESS = "string_relativeAddress";
	public static final String VARIABLE_NAME_RELATIVE_POSTCODE = "string_relativePostCode";
	public static final String VARIABLE_NAME_RELATIVE_PHONE = "string_relativePhone";
	public static final String VARIABLE_NAME_RELATIVE_GSM = "string_relativeGSM";
	public static final String VARIABLE_NAME_RELATIVE_EMAIL = "string_relativeEmail";
	public static final String VARIABLE_NAME_RELATIVE_RELATIONSHIP = "string_relativeRelationship";
	public static final String VARIABLE_NAME_SERVICES_APPLICANT = "list_currentServices";
	
	
	private ReykjavikServices reykjavikServices;
	
	public String getRealAddress() {
		return (String) getValue(VARIABLE_NAME_REAL_ADDRESS);
	}
	public void setRealAddress(String realAddress) {
		add(VARIABLE_NAME_REAL_ADDRESS, realAddress);
	}
	public String getRealPostCode() {
		return (String) getValue(VARIABLE_NAME_REAL_POST_CODE);
	}
	public void setRealPostCode(String realPostCode) {
		add(VARIABLE_NAME_REAL_POST_CODE, realPostCode);
	}
	public String getGuardianName() {
		return (String) getValue(VARIABLE_NAME_GUARDIAN_NAME);
	}
	public void setGuardianName(String guardianName) {
		add(VARIABLE_NAME_GUARDIAN_NAME, guardianName);
	}
	public String getGuardianPersonalId() {
		return (String) getValue(VARIABLE_NAME_GUARDIAN_PERSONAL_ID);
	}
	public void setGuardianPersonalId(String guardianPersonalId) {
		add(VARIABLE_NAME_GUARDIAN_PERSONAL_ID, guardianPersonalId);
	}
	public String getGuardianAddress() {
		return (String) getValue(VARIABLE_NAME_GUARDIAN_ADDRESS);
	}
	public void setGuardianAddress(String guardianAddress) {
		add(VARIABLE_NAME_GUARDIAN_ADDRESS, guardianAddress);
	}
	public String getGuardianPostCode() {
		return (String) getValue(VARIABLE_NAME_GUARDIAN_POSTCODE);
	}
	public void setGuardianPostCode(String guardianPostCode) {
		add(VARIABLE_NAME_GUARDIAN_POSTCODE, guardianPostCode);
	}
	public String getGuardianPhone() {
		return (String) getValue(VARIABLE_NAME_GUARDIAN_PHONE);
	}
	public void setGuardianPhone(String guardianPhone) {
		add(VARIABLE_NAME_GUARDIAN_PHONE, guardianPhone);
	}
	public String getGuardianGSM() {
		return (String) getValue(VARIABLE_NAME_GUARDIAN_GSM);
	}
	public void setGuardianGSM(String guardianGSM) {
		add(VARIABLE_NAME_GUARDIAN_GSM, guardianGSM);
	}
	public String getGuardianEmail() {
		return (String) getValue(VARIABLE_NAME_GUARDIAN_EMAIL);
	}
	public void setGuardianEmail(String guardianEmail) {
		add(VARIABLE_NAME_GUARDIAN_EMAIL, guardianEmail);
	}
	public String getRelativeName() {
		return (String) getValue(VARIABLE_NAME_RELATIVE_NAME);
	}
	public void setRelativeName(String relativeName) {
		add(VARIABLE_NAME_RELATIVE_NAME, relativeName);
	}
	public String getRelativePersonalId() {
		return (String) getValue(VARIABLE_NAME_RELATIVE_PERSONAL_ID);
	}
	public void setRelativePersonalId(String relativePersonalId) {
		add(VARIABLE_NAME_RELATIVE_PERSONAL_ID, relativePersonalId);
	}
	public String getRelativeAddress() {
		return (String) getValue(VARIABLE_NAME_RELATIVE_ADDRESS);
	}
	public void setRelativeAddress(String relativeAddress) {
		add(VARIABLE_NAME_RELATIVE_ADDRESS, relativeAddress);
	}
	public String getRelativePostCode() {
		return (String) getValue(VARIABLE_NAME_RELATIVE_POSTCODE);
	}
	public void setRelativePostCode(String relativePostCode) {
		add(VARIABLE_NAME_RELATIVE_POSTCODE, relativePostCode);
	}
	public String getRelativePhone() {
		return (String) getValue(VARIABLE_NAME_RELATIVE_PHONE);
	}
	public void setRelativePhone(String relativePhone) {
		add(VARIABLE_NAME_RELATIVE_PHONE, relativePhone);
	}
	public String getRelativeGSM() {
		return (String) getValue(VARIABLE_NAME_RELATIVE_GSM);
	}
	public void setRelativeGSM(String relativeGSM) {
		add(VARIABLE_NAME_RELATIVE_GSM, relativeGSM);
	}
	public String getRelativeEmail() {
		return (String) getValue(VARIABLE_NAME_RELATIVE_EMAIL);
	}
	public void setRelativeEmail(String relativeEmail) {
		add(VARIABLE_NAME_RELATIVE_EMAIL, relativeEmail);
	}
	public String getRelativeRelationship() {
		return (String) getValue(VARIABLE_NAME_RELATIVE_RELATIONSHIP);
	}
	public void setRelativeRelationship(String relativeRelationship) {
		add(VARIABLE_NAME_RELATIVE_RELATIONSHIP, relativeRelationship);
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getServicesApplicant() {
		return (List<String>) getValue(VARIABLE_NAME_SERVICES_APPLICANT);
	}
	public void setServicesApplicant(List<String> servicesApplicant) {
		add(VARIABLE_NAME_SERVICES_APPLICANT, new ArrayList<String>(servicesApplicant));
	}
	@Override
	public List<Map<String, Object>> getAllServices() {
		ReykjavikServices reykjavikServices = getReykjavikServices();
		List<String> allServices = reykjavikServices.getAssistanceServices();
		List<String> selectedservices = getServicesApplicant();
		List<Map<String, Object>> services = new ArrayList<Map<String,Object>>();
		boolean empty = ListUtil.isEmpty(selectedservices);
		for(String serviceName : allServices){
			Map<String, Object> service = new HashMap<String, Object>();
			services.add(service);
			service.put("id", serviceName);
			service.put("name", serviceName);
			if(empty){
				service.put("selected", Boolean.FALSE);
				continue;
			}
			service.put("selected", selectedservices.remove(serviceName));
		}
		if(ListUtil.isEmpty(selectedservices)){
			return services;
		}
		for(String serviceName : selectedservices){
			Map<String, Object> service = new HashMap<String, Object>();
			services.add(service);
			service.put("id", serviceName);
			service.put("name", serviceName);
			service.put("selected", Boolean.TRUE);
		}
		return services;
	}
	
	public ReykjavikServices getReykjavikServices() {
		if(reykjavikServices == null){
			this.reykjavikServices = ELUtil.getInstance().getBean(ReykjavikServices.BEAN_NAME);
		}
		return reykjavikServices;
	}
	
}