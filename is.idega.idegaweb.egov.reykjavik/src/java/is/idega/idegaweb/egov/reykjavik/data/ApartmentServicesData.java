package is.idega.idegaweb.egov.reykjavik.data;

import is.idega.idegaweb.egov.reykjavik.beans.ReykjavikServices;
import is.idega.idegaweb.egov.reykjavik.data.dao.ReykjavikDao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.idega.util.ListUtil;
import com.idega.util.expression.ELUtil;

public class ApartmentServicesData extends ReykjavikGeneralData{

	public static final String VARIABLE_NAME_RESIDENCY = "string_residency"; 
	public static final String VARIABLE_NAME_ARRIVAL_TO_CAPITAL_TIME = "date_arrivalToCapitalTime";
	public static final String VARIABLE_NAME_MARITAL_STATUS = "string_maritalStatus";
	public static final String VARIABLE_NAME_SPOUSE_NAME = "string_spouseName";
	public static final String VARIABLE_NAME_SPOUSE_PERSONAL_ID = "string_spousePersonalId";
	public static final String VARIABLE_NAME_ADDITIONAL_DATA = "long_apartmentServicesData";
	public static final String VARIABLE_NAME_CHILDREN_AMOUNT = "long_childrenAmount";
	public static final String VARIABLE_NAME_CHILDREN_AMOUNT_IN_REYKJAVIK = "long_childrenAmountInReykjavik";
	public static final String VARIABLE_NAME_HOUSING_CONDITION = "long_housingCondition";
	public static final String VARIABLE_NAME_HOUSE_TYPE = "long_houseType";
	public static final String VARIABLE_NAME_FLOOR = "long_floor";
	public static final String VARIABLE_NAME_ELEVATORS = "long_elevators";
	public static final String VARIABLE_NAME_LOCATION_TYPE = "string_locationType";
	public static final String VARIABLE_NAME_LOCATION = "list_locations";
	public static final String VARIABLE_NAME_LEASE_START = "date_leaseStart";
	public static final String VARIABLE_NAME_LEASE_END = "date_leaseEnd";
	public static final String VARIABLE_NAME_MONTHLY_RENT = "long_monthlyRent";
	public static final String VARIABLE_NAME_NUMBER_OF_ROOMS = "long_roomNumber";
	public static final String VARIABLE_NAME_HOUSING_DEFECTS = "string_housingDefects";
	public static final String VARIABLE_NAME_POOR_ACCESS = "string_poorAccess";
	public static final String VARIABLE_NAME_CURRENT_SERVICES = "objlist_services";
	public static final String VARIABLE_NAME_HELP_DESCRIPTION = "string_helpDescription";
	public static final String VARIABLE_NAME_DOCTOR_NAME = "string_doctorName";
	public static final String VARIABLE_NAME_DOCTOR_PHONE = "string_doctorPhone";
	public static final String VARIABLE_NAME_ANOTHER_APPLICANT_STATEMENTS = "string_anotherApplicantStatements";
	
	@Autowired
	private ReykjavikDao reykjavikDao;
	
	private ReykjavikServices reykjavikServices;
	private ApartmentServicesDataEntity apartmentServicesDataEntity;
	
	
	public String getHousingDefects() {
		return (String) getValue(VARIABLE_NAME_HOUSING_DEFECTS);
	}

	public void setHousingDefects(String housingDefects) {
		add(VARIABLE_NAME_HOUSING_DEFECTS, housingDefects);
	}
	
	public String getHelpDescription() {
		return (String) getValue(VARIABLE_NAME_HELP_DESCRIPTION);
	}

	public void setHelpDescription(String helpDescription) {
		add(VARIABLE_NAME_HELP_DESCRIPTION, helpDescription);
	}
	public String getDoctorName() {
		return (String) getValue(VARIABLE_NAME_DOCTOR_NAME);
	}

	public void setDoctorName(String doctorName) {
		add(VARIABLE_NAME_DOCTOR_NAME, doctorName);
	}
	public String getDoctorPhone() {
		return (String) getValue(VARIABLE_NAME_DOCTOR_PHONE);
	}

	public void setDoctorPhone(String doctorPhone) {
		add(VARIABLE_NAME_DOCTOR_PHONE, doctorPhone);
	}
	public String getAnotherApplicantStatements() {
		return (String) getValue(VARIABLE_NAME_ANOTHER_APPLICANT_STATEMENTS);
	}

	public void setAnotherApplicantStatementss(String anotherApplicantStatements) {
		add(VARIABLE_NAME_ANOTHER_APPLICANT_STATEMENTS, anotherApplicantStatements);
	}
	public String getPoorAccess() {
		return (String) getValue(VARIABLE_NAME_POOR_ACCESS);
	}

	public void setPoorAccess(String poorAccess) {
		add(VARIABLE_NAME_POOR_ACCESS, poorAccess);
	}
	
	
	public Long getMonthlyRent() {
		return (Long) getValue(VARIABLE_NAME_MONTHLY_RENT);
	}
	public void setMonthlyRent(Long monthlyRent) {
		add(VARIABLE_NAME_MONTHLY_RENT, monthlyRent);
	}
	public Long getRoomNumber() {
		return (Long) getValue(VARIABLE_NAME_NUMBER_OF_ROOMS);
	}

	public void setRoomNumber(Long roomNumber) {
		add(VARIABLE_NAME_NUMBER_OF_ROOMS, roomNumber);
	}
	public Date getLeaseStart() {
		return (Date) getValue(VARIABLE_NAME_LEASE_START);
	}

	public void setLeaseStart(Date leaseStart) {
		add(VARIABLE_NAME_LEASE_START, leaseStart);
	}
	public Date getLeaseEnd() {
		return (Date) getValue(VARIABLE_NAME_LEASE_END);
	}

	public void setLeaseEnd(Date leaseEnd) {
		add(VARIABLE_NAME_LEASE_END, leaseEnd);
	}
	
	
	public Date getArrivalToCapitalTime() {
		return (Date) getValue(VARIABLE_NAME_ARRIVAL_TO_CAPITAL_TIME);
	}

	public void setArrivalToCapitalTime(Date arrivalToCapitalTime) {
		add(VARIABLE_NAME_ARRIVAL_TO_CAPITAL_TIME, arrivalToCapitalTime);
	}

	public String getResidency() {
		return (String) getValue(VARIABLE_NAME_RESIDENCY);
	}

	public void setResidency(String residency) {
		add(VARIABLE_NAME_RESIDENCY, residency);
	}

	public String getMaritalStatus() {
		return (String) getValue(VARIABLE_NAME_MARITAL_STATUS);
	}

	public void setMaritalStatus(String maritalStatus) {
		add(VARIABLE_NAME_MARITAL_STATUS, maritalStatus);
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

	public Long getChildrenAmount() {
		return (Long) getValue(VARIABLE_NAME_CHILDREN_AMOUNT);
	}

	public void setChildrenAmount(Long childrenAmount) {
		add(VARIABLE_NAME_CHILDREN_AMOUNT, childrenAmount);
	}

	public Long getChildrenAmountInReykjavik() {
		return (Long) getValue(VARIABLE_NAME_CHILDREN_AMOUNT_IN_REYKJAVIK);
	}

	public void setChildrenAmountInReykjavik(Long childrenAmountInReykjavik) {
		add(VARIABLE_NAME_CHILDREN_AMOUNT_IN_REYKJAVIK, childrenAmountInReykjavik);
	}

	private ApartmentServicesDataEntity getApartmentServicesDataEntity(){
		if(apartmentServicesDataEntity != null){
			return apartmentServicesDataEntity;
		}
		Long variable =  (Long) getValue(VARIABLE_NAME_ADDITIONAL_DATA);
		if(variable == null){
			return null;
		}
		if(reykjavikDao == null){
			ELUtil.getInstance().autowire(this);
		}
		apartmentServicesDataEntity = reykjavikDao.getById(variable, ApartmentServicesDataEntity.class);
		return apartmentServicesDataEntity;
	}
	
	public List<ContactPersonEntity> getContacts() {
		ApartmentServicesDataEntity apartmentServicesDataEntity = getApartmentServicesDataEntity();
		if(apartmentServicesDataEntity == null){
			return Collections.emptyList();
		}
		
		Set<ContactPersonEntity> contacts = apartmentServicesDataEntity.getContacts();
		if(contacts == null){
			return Collections.emptyList();
		}
		return new ArrayList<ContactPersonEntity>(contacts);
	}
	
	public Long getHousingCondition(){
		return (Long) getValue(VARIABLE_NAME_HOUSING_CONDITION);
	}
	
	public List<Map<String,Object>> getAllHousingConditions(){
		ReykjavikServices reykjavikServices = getReykjavikServices();
		List<Map<String,Object>> conditions = reykjavikServices.getAllHousingConditions();
		Long selectedCondition = getHousingCondition();
		for(Map<String,Object> condition : conditions){
			Boolean selected = condition.get("id").equals(selectedCondition);
			condition.put("selected",selected);
		}
		return conditions;
	}
	
	public Long getHouseType(){
		return (Long) getValue(VARIABLE_NAME_HOUSE_TYPE);
	}
	
	public Long getFloor(){
		return (Long) getValue(VARIABLE_NAME_FLOOR);
	}
	
	public Long getElevators(){
		return (Long) getValue(VARIABLE_NAME_ELEVATORS);
	}
	public String getLocationType(){
		return (String) getValue(VARIABLE_NAME_LOCATION_TYPE);
	}
	public List<String> getLocations(){
		@SuppressWarnings("unchecked")
		List<String> variable = (List<String>) getValue(VARIABLE_NAME_LOCATION);
		if(ListUtil.isEmpty(variable)){
			variable = Collections.emptyList();
		}
		return variable;
	}
	
	public List<Map<String,Object>> getAllHousetypes(){
		ReykjavikServices reykjavikServices = getReykjavikServices();
		List<Map<String,Object>> houseTypes = reykjavikServices.getAllHouseTypes();
		Long selectedHouseType = getHouseType();
		for(Map<String,Object> houseType : houseTypes){
			Boolean selected = houseType.get("id").equals(selectedHouseType);
			houseType.put("selected",selected);
		}
		return houseTypes;
	}
	
	public List<Map<String,Object>> getAllElevators(){
		ReykjavikServices reykjavikServices = getReykjavikServices();
		List<Map<String,Object>> allElevators = reykjavikServices.getAllElevators();
		Long selectedElevators = getElevators();
		for(Map<String,Object> elevators : allElevators){
			Boolean selected = elevators.get("id").equals(selectedElevators);
			elevators.put("selected",selected);
		}
		return allElevators;
	}
	
	public List<Map<String,Object>> getAllDesirableLocations(){
		ReykjavikServices reykjavikServices = getReykjavikServices();
		List<Map<String,Object>> allDesirableLocations = reykjavikServices.getAllDesirableLocations();
		List<String> selectedLocations = getLocations();
		boolean empty = ListUtil.isEmpty(selectedLocations);
		for(Map<String,Object> location : allDesirableLocations){
			Boolean selected = empty ? Boolean.FALSE : selectedLocations.contains(location.get("id"));
			location.put("selected",selected);
		}
		return allDesirableLocations;
	}
	
	public List<Map<String,Object>> getAllResidentRights(){
		ReykjavikServices reykjavikServices = getReykjavikServices();
		List<Map<String,Object>> allResidentRights = reykjavikServices.getAllResidentRights();
		List<String> selectedLocations = getLocations();
		boolean empty = ListUtil.isEmpty(selectedLocations);
		for(Map<String,Object> elevators : allResidentRights){
			Boolean selected = empty ? Boolean.FALSE : selectedLocations.contains(elevators.get("id"));
			elevators.put("selected",selected);
		}
		return allResidentRights;
	}
	
	@Override
	public List<Map<String, Object>> getAllServices(){
		@SuppressWarnings("unchecked")
		List<Map<String, String>> currentServices = (List<Map<String, String>>) getValue(VARIABLE_NAME_CURRENT_SERVICES);
		Map<String,Map<String, String>> map;
		if(!ListUtil.isEmpty(currentServices)){
			map = new HashMap<String, Map<String,String>>();
			for(Map<String, String> service : currentServices){
				map.put(service.get("id"), service);
			}
		}else{
			map = Collections.emptyMap();
		}
		ReykjavikServices reykjavikServices = getReykjavikServices();
		List<String> socialServices = reykjavikServices.getSocialServices();
		List<Map<String, Object>> services = new ArrayList<Map<String,Object>>(socialServices.size());
		for(String serviceKey : socialServices){
			Map<String, Object> service = new HashMap<String, Object>();
			services.add(service);
			service.put("id",serviceKey);
			Map<String, String> currentService = map.remove(serviceKey);
			Boolean current = currentService != null;
			service.put("selected", current);
			service.put("name", serviceKey);
			if(!current){
				continue;
			}
			service.put("frequency", currentService.get("frequency"));
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
