package is.idega.idegaweb.egov.reykjavik.data.dao;

import is.idega.idegaweb.egov.reykjavik.beans.ReykjavikFormRequestBean;
import is.idega.idegaweb.egov.reykjavik.data.ReykjavikGeneralData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.idega.util.CoreConstants;
import com.idega.util.ListUtil;
import com.idega.util.expression.ELUtil;

public class FoodDeliveryServiceData extends ReykjavikGeneralData{

	public static final String VARIABLE_NAME_RELATIVE_NAME = "string_relativeName";
	public static final String VARIABLE_NAME_RELATIVE_PHONE = "string_relativePhone";
	public static final String VARIABLE_NAME_RELATIOONSHIP = "string_relationship";
	public static final String VARIABLE_NAME_DELIVERY_DAYS = "list_deliveryDays";
	public static final String VARIABLE_NAME_DIETS = "list_diets";
	public static final String VARIABLE_NAME_COMMENTS = "string_foodDeliveryComments";
	
	public String getRelativeName() {
		String variable = (String) getValue(VARIABLE_NAME_RELATIVE_NAME);
		return variable == null ? CoreConstants.EMPTY : variable;
	}
	public void setRelativeName(String relativeName) {
		add(VARIABLE_NAME_RELATIVE_NAME, relativeName);
	}
	public String getRelativePhone() {
		String variable = (String) getValue(VARIABLE_NAME_RELATIVE_PHONE);
		return variable == null ? CoreConstants.EMPTY : variable;
	}
	public void setRelativePhone(String relativePhone) {
		add(VARIABLE_NAME_RELATIVE_PHONE, relativePhone);
	}
	public String getRelationship() {
		String variable = (String) getValue(VARIABLE_NAME_RELATIOONSHIP);
		return variable == null ? CoreConstants.EMPTY : variable;
	}
	public void setRelationship(String relationship) {
		add(VARIABLE_NAME_RELATIOONSHIP, relationship);
	}
	
	public List<Map<String, Object>> getDeliveryDays(){
		@SuppressWarnings("unchecked")
		List<String> deliveryDays = (List<String>) getValue(VARIABLE_NAME_DELIVERY_DAYS);
		if(ListUtil.isEmpty(deliveryDays)){
			deliveryDays =  Collections.emptyList();
		}
		ReykjavikFormRequestBean reykjavikFormRequestBean= ELUtil.getInstance().getBean(ReykjavikFormRequestBean.BEAN_NAME);
		List<String> weekDays = reykjavikFormRequestBean.getWeekDays();
		List<Map<String, Object>> deliveryDaysData = new ArrayList<Map<String,Object>>(7);
		for(int i = 0; i < 7; i++){
			TreeMap<String, Object> dayData = new TreeMap<String, Object>();
			deliveryDaysData.add(dayData);
			dayData.put("name", weekDays.get(i));
			dayData.put("value", i);
			dayData.put("deliver", deliveryDays.contains(String.valueOf(i)) ? Boolean.TRUE : Boolean.FALSE);
		}
		return deliveryDaysData;
	}
	
	public String getDiets() {
		@SuppressWarnings("unchecked")
		ArrayList<String> variable = (ArrayList<String>) getValue(VARIABLE_NAME_DIETS);
		return ListUtil.isEmpty(variable) ? CoreConstants.EMPTY : variable.get(0);
	}
	public void setDiets(String diets) {
		ArrayList<String> dietsList = new ArrayList<String>(1);
		dietsList.add(diets);
		add(VARIABLE_NAME_DIETS, dietsList);
	}
	public String getFoodDeliveryComments() {
		String variable = (String) getValue(VARIABLE_NAME_COMMENTS);
		return variable == null ? CoreConstants.EMPTY : variable;
	}
	public void setFoodDeliveryComments(String comments) {
		add(VARIABLE_NAME_COMMENTS, comments);
	}
}
