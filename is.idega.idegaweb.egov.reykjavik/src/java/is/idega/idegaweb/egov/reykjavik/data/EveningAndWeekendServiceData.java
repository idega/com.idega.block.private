/**
 * @(#)EveningAndWeekendServiceData.java    1.0.0 11:34:46 AM
 *
 * Idega Software hf. Source Code Licence Agreement x
 *
 * This agreement, made this 10th of February 2006 by and between 
 * Idega Software hf., a business formed and operating under laws 
 * of Iceland, having its principal place of business in Reykjavik, 
 * Iceland, hereinafter after referred to as "Manufacturer" and Agura 
 * IT hereinafter referred to as "Licensee".
 * 1.  License Grant: Upon completion of this agreement, the source 
 *     code that may be made available according to the documentation for 
 *     a particular software product (Software) from Manufacturer 
 *     (Source Code) shall be provided to Licensee, provided that 
 *     (1) funds have been received for payment of the License for Software and 
 *     (2) the appropriate License has been purchased as stated in the 
 *     documentation for Software. As used in this License Agreement, 
 *     �Licensee� shall also mean the individual using or installing 
 *     the source code together with any individual or entity, including 
 *     but not limited to your employer, on whose behalf you are acting 
 *     in using or installing the Source Code. By completing this agreement, 
 *     Licensee agrees to be bound by the terms and conditions of this Source 
 *     Code License Agreement. This Source Code License Agreement shall 
 *     be an extension of the Software License Agreement for the associated 
 *     product. No additional amendment or modification shall be made 
 *     to this Agreement except in writing signed by Licensee and 
 *     Manufacturer. This Agreement is effective indefinitely and once
 *     completed, cannot be terminated. Manufacturer hereby grants to 
 *     Licensee a non-transferable, worldwide license during the term of 
 *     this Agreement to use the Source Code for the associated product 
 *     purchased. In the event the Software License Agreement to the 
 *     associated product is terminated; (1) Licensee's rights to use 
 *     the Source Code are revoked and (2) Licensee shall destroy all 
 *     copies of the Source Code including any Source Code used in 
 *     Licensee's applications.
 * 2.  License Limitations
 *     2.1 Licensee may not resell, rent, lease or distribute the 
 *         Source Code alone, it shall only be distributed as a 
 *         compiled component of an application.
 *     2.2 Licensee shall protect and keep secure all Source Code 
 *         provided by this this Source Code License Agreement. 
 *         All Source Code provided by this Agreement that is used 
 *         with an application that is distributed or accessible outside
 *         Licensee's organization (including use from the Internet), 
 *         must be protected to the extent that it cannot be easily 
 *         extracted or decompiled.
 *     2.3 The Licensee shall not resell, rent, lease or distribute 
 *         the products created from the Source Code in any way that 
 *         would compete with Idega Software.
 *     2.4 Manufacturer's copyright notices may not be removed from 
 *         the Source Code.
 *     2.5 All modifications on the source code by Licencee must 
 *         be submitted to or provided to Manufacturer.
 * 3.  Copyright: Manufacturer's source code is copyrighted and contains 
 *     proprietary information. Licensee shall not distribute or 
 *     reveal the Source Code to anyone other than the software 
 *     developers of Licensee's organization. Licensee may be held 
 *     legally responsible for any infringement of intellectual property 
 *     rights that is caused or encouraged by Licensee's failure to abide 
 *     by the terms of this Agreement. Licensee may make copies of the 
 *     Source Code provided the copyright and trademark notices are 
 *     reproduced in their entirety on the copy. Manufacturer reserves 
 *     all rights not specifically granted to Licensee.
 *
 * 4.  Warranty & Risks: Although efforts have been made to assure that the 
 *     Source Code is correct, reliable, date compliant, and technically 
 *     accurate, the Source Code is licensed to Licensee as is and without 
 *     warranties as to performance of merchantability, fitness for a 
 *     particular purpose or use, or any other warranties whether 
 *     expressed or implied. Licensee's organization and all users 
 *     of the source code assume all risks when using it. The manufacturers, 
 *     distributors and resellers of the Source Code shall not be liable 
 *     for any consequential, incidental, punitive or special damages 
 *     arising out of the use of or inability to use the source code or 
 *     the provision of or failure to provide support services, even if we 
 *     have been advised of the possibility of such damages. In any case, 
 *     the entire liability under any provision of this agreement shall be 
 *     limited to the greater of the amount actually paid by Licensee for the 
 *     Software or 5.00 USD. No returns will be provided for the associated 
 *     License that was purchased to become eligible to receive the Source 
 *     Code after Licensee receives the source code. 
 */
package is.idega.idegaweb.egov.reykjavik.data;

import is.idega.idegaweb.egov.reykjavik.beans.AdvancedParameter;
import is.idega.idegaweb.egov.reykjavik.beans.ReykjavikFormRequestBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.idega.util.CoreConstants;
import com.idega.util.ListUtil;
import com.idega.util.expression.ELUtil;

/**
 * <p>You can report about problems to: 
 * <a href="mailto:martynas@idega.is">Martynas Stakė</a></p>
 *
 * @version 1.0.0 Jul 19, 2013
 * @author <a href="mailto:aleksandras@idega.com">Aleksandras Sivkovas</a>
 */
public class EveningAndWeekendServiceData extends WeekServiceData {

	public static final String VARIABLE_NAME_RELATIVE_NAME = "string_relativeName";
	public static final String VARIABLE_NAME_RELATIVE_PHONE = "string_relativePhone";
	public static final String VARIABLE_NAME_RELATIOONSHIP = "string_relationship";
	public static final String VARIABLE_NAME_COMMENTS = "string_comments";
	public static final String VARIABLE_NAME_DAYS_OF_SERVICES = "objlist_daysOfServices";

	
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
	
	public List<Map<String, String>> getDays() {
		List<Map<String, String>> variable = new ArrayList<Map<String,String>>(7);
		for(int i = 0;i < 7; i++){
			String daySelected = (String) getValue(DAY_SELECTED+i);
			if(!"Y".equals(daySelected)){
				continue;
			}
			HashMap<String, String> day = new HashMap<String, String>();
			variable.add(day);
			String from = (String) getValue(DAY_TIME_FROM+i);
			String to = (String) getValue(DAY_TIME_TO+i);
			day.put("number", String.valueOf(i));
			day.put("from", from);
			day.put("to", to);
		}
		return variable;
	}

	@SuppressWarnings("unchecked")
	public static Map<String,String> getDaysVariables(List<AdvancedParameter> days){
		Map<String,String> daysVariables = new HashMap<String,String>(7);
		for(AdvancedParameter day : days){
			Object property = day.get("number");
			String number;
			if(property instanceof List){
				number = ((List<String>) property).get(0);
			}else{
				number = (String) property;
			}
			daysVariables.put(DAY_SELECTED+number, "Y");
			property = day.get("from");
			String from;
			if(property instanceof List){
				from = ((List<String>) property).get(0);
			}else{
				from = (String) property;
			}
			property = day.get("to");
			String to;
			if(property instanceof List){
				to = ((List<String>) property).get(0);
			}else{
				to = (String) property;
			}
			daysVariables.put(DAY_TIME_FROM+number, from);
			daysVariables.put(DAY_TIME_TO+number, to);
		}
		return daysVariables;
	}

	public List<Map<String, Object>> getAllDaysData(){
		List<Map<String, String>> currentDays = getDays();
		if(ListUtil.isEmpty(currentDays)){
			currentDays =  Collections.emptyList();
		}
		Map<Integer,Map<String, String>> daysMap = new HashMap<Integer, Map<String,String>>(7);
		for(Map<String, String> dayData : currentDays){
			daysMap.put(Integer.valueOf(dayData.get("number")), dayData);
		}
		ReykjavikFormRequestBean reykjavikFormRequestBean= ELUtil.getInstance().getBean(ReykjavikFormRequestBean.BEAN_NAME);
		List<String> weekDays = reykjavikFormRequestBean.getWeekDays();
		List<Map<String, Object>> deliveryDaysData = new ArrayList<Map<String,Object>>(7);
		for(int i = 0; i < 7; i++){
			TreeMap<String, Object> dayData = new TreeMap<String, Object>();
			deliveryDaysData.add(dayData);
			dayData.put("name", weekDays.get(i));
			dayData.put("number", i);
			Map<String, String> current = daysMap.get(i);
			boolean empty = current == null;
			String from = empty ? CoreConstants.EMPTY : current.get("from");
			String to = empty ? CoreConstants.EMPTY : current.get("to");
			dayData.put("from", from);
			dayData.put("to",to);
			dayData.put("filled",!empty);
		}
		return deliveryDaysData;
	}
	public String getComments() {
		return (String) getValue(VARIABLE_NAME_COMMENTS);
	}
	
	public String getVariableNameComments() {
		return VARIABLE_NAME_COMMENTS;
	}
}
