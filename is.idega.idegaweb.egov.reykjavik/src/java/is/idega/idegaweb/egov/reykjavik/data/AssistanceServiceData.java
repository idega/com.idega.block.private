/**
 * @(#)AssistanceServiceData.java    1.0.0 9:16:19 AM
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
 *     Licensee shall also mean the individual using or installing 
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

import is.idega.idegaweb.egov.reykjavik.ReykjavikConstants;
import is.idega.idegaweb.egov.reykjavik.process.presentation.AssistanceServiceViewer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.idega.util.CoreConstants;
import com.idega.util.ListUtil;
import com.idega.util.StringUtil;
import com.idega.util.datastructures.map.MapUtil;

/**
 * <p>Variables managing bean for {@link AssistanceServiceViewer}</p>
 * <p>You can report about problems to: 
 * <a href="mailto:martynas@idega.is">Martynas Stakė</a></p>
 *
 * @version 1.0.0 Jul 25, 2013
 * @author <a href="mailto:martynas@idega.is">Martynas Stakė</a>
 */
public class AssistanceServiceData extends ReykjavikGeneralData {

	public static final String VARIABLE_NAME_OTHER_HABITANTS = "objlist_otherHabitants"; // Example: 
	
	public static final String VARIABLE_NAME_HELP_INFO = "string_helpInfo";
	public static final String VARIABLE_NAME_DESCRIPTION_OF_DISABILITY = "string_descriptionOfDisability";
	public static final String VARIABLE_NAME_DISABILITY = "string_disability";
	public static final String VARIABLE_NAME_DISABILITY_PERCENT = "long_percentOfDisability";
	public static final String VARIABLE_NAME_THERAPY_DURATION = "string_therapyDuration";
	public static final String VARIABLE_NAME_FOLLOWED_SERVICE = "string_followedService";
	
	public static final String VARIABLE_NAME_FAMILY_SUPPORT = "string_familySupportSelected";
	public static final String VARIABLE_NAME_FAMILY_SUPPORT_VALUE = "string_familySupportValue";
	public static final String VARIABLE_NAME_HOME_SERVICE = "string_homeServiceSelected";
	public static final String VARIABLE_NAME_HOME_SERVICE_VALUE = "string_homeServiceValue";
	public static final String VARIABLE_NAME_SOCIAL_HOME_SERVICE = "string_socialHomeServiceSelected";
	public static final String VARIABLE_NAME_SOCIAL_HOME_SERVICE_VALUE = "string_socialHomeServiceValue";
	public static final String VARIABLE_NAME_FUTHER_PERSONAL_SUPPORT = "string_futherPersonalSupportSelected";
	public static final String VARIABLE_NAME_FUTHER_PERSONAL_SUPPORT_VALUE = "string_futherPersonalSupportValue";
	public static final String VARIABLE_NAME_SHORT_TERM = "string_shortTermSelected";
	public static final String VARIABLE_NAME_SHORT_TERM_VALUE = "string_shortTermValue";
	public static final String VARIABLE_NAME_NURSING_HOMES = "string_nursingHomesSelected";
	public static final String VARIABLE_NAME_NURSING_HOMES_VALUE = "string_nursingHomeValue";
	public static final String VARIABLE_NAME_OTHER = "string_otherSelected";
	public static final String VARIABLE_NAME_OTHER_NAME = "string_otherName";
	public static final String VARIABLE_NAME_OTHER_VALUE = "string_otherValue";
	
	public static final String VARIABLE_NAME_SOCIAL_ACTIVITY = "string_engagedSocialActivity";
	public static final String VARIABLE_NAME_AID_RECEIVED_BEFORE = "string_aidReceivedBefore";
	public static final String VARIABLE_NAME_AID_RECEIVED_BEFORE_TIME = "string_aidReceivedBeforeTime";
	public static final String VARIABLE_NAME_NUMBER_OF_TIMES_IN_MONTH = "long_numberOfTimesInMonthHelpIsNeeded";
	public static final String VARIABLE_NAME_NUMBER_OF_MONTH = "long_numberOfMonthWhenHelpIsNeeded";
	public static final String VARIABLE_NAME_TEAM_MAN_STATUS = "string_teamManStatus";
	public static final String VARIABLE_NAME_PREFERENCES_FOR_AGE_AND_GENDER = "string_ageAndGenderPreferencesForHelp";
	public static final String VARIABLE_NAME_PREFERENCES_FOR_TIME = "string_timePreferencesForHelp";
	
	/*
	 * Habitants
	 */
	private Map<String, String> localizedRelations = null;
	
	public Map<String, String> getLocalizedRelations() {
		if (MapUtil.isEmpty(localizedRelations)) {
			localizedRelations = new HashMap<String, String>();
			for (String value : ReykjavikConstants.RELATION_UNLOCALIZED_VALUES) {
				localizedRelations.put(getLocalizedString(value), value);
			}
		}
		
		return localizedRelations;
	}

	private List<RelationData> relations = new ArrayList<RelationData>();
	
	public List<RelationData> getRelations() {
		return relations;
	}

	public String getVariableNameOtherHabitants() {
		return VARIABLE_NAME_OTHER_HABITANTS;
	}
	
	public List<RelationData> getOtherHabitants() {
		Serializable value = getValue(VARIABLE_NAME_OTHER_HABITANTS);
		if (value instanceof List) {
			Gson gson = new Gson();
			List<?> objects = (List<?>) value; 
			for (Object object : objects) {
				this.relations.add(gson.fromJson(object.toString(), RelationData.class));
			}
		}
		
		// Invisible row, if form was not filled
		if (ListUtil.isEmpty(getRelations())) {
			RelationData relation = new RelationData();
			relation.setName(CoreConstants.EMPTY);
			relation.setPersonalId(CoreConstants.EMPTY);
			relation.setRelation(CoreConstants.EMPTY);
			getRelations().add(relation);
		}

		return relations;
	}

	/*
	 * Help info
	 */
	public String getVariableNameHelpInfo() {
		return VARIABLE_NAME_HELP_INFO;
	}
	
	public String getHelpInfo() {
		return getString(VARIABLE_NAME_HELP_INFO);
	}

	/*
	 * Disability
	 */
	
	public String getVariableNameDescriptionOfDisability() {
		return VARIABLE_NAME_DESCRIPTION_OF_DISABILITY;
	}
	
	public String getDescriptionOfDisability() {
		return getString(VARIABLE_NAME_DESCRIPTION_OF_DISABILITY);
	}

	public String getVariableNameDisability() {
		return VARIABLE_NAME_DISABILITY;
	}

	public String getDisability() {
		return getString(VARIABLE_NAME_DISABILITY);
	}

	public String getVariableNameDisabilityPercent() {
		return VARIABLE_NAME_DISABILITY_PERCENT;
	}

	public Number getDisabilityPercent() {
		return getNumber(VARIABLE_NAME_DISABILITY_PERCENT);
	}

	/*
	 * Therapy duration
	 */
	
	public String getVariableNameTherapyDuration() {
		return VARIABLE_NAME_THERAPY_DURATION;
	}

	public String getTherapyDuration() {
		return getString(VARIABLE_NAME_THERAPY_DURATION);
	}

	/*
	 * Followed services
	 */
	
	public String getVariableNameFollowedService() {
		return VARIABLE_NAME_FOLLOWED_SERVICE;
	}
	
	public String getFollowedService() {
		return getString(VARIABLE_NAME_FOLLOWED_SERVICE);
	}
	
	/*
	 * Family support
	 */
	
	public String getVariableNameFamilySupport() {
		return VARIABLE_NAME_FAMILY_SUPPORT;
	}

	public boolean isFamilySupportSelected() {
		return getBoolean(VARIABLE_NAME_FAMILY_SUPPORT);
	}

	public String getVariableNameFamilySupportValue() {
		return VARIABLE_NAME_FAMILY_SUPPORT_VALUE;
	}
	
	public String getFamilySupportValue() {
		return (String) getValue(VARIABLE_NAME_FAMILY_SUPPORT_VALUE);
	}
	
	/*
	 * Home service
	 */
	
	public String getVariableNameHomeService() {
		return VARIABLE_NAME_HOME_SERVICE;
	}

	public boolean isHomeServiceSelected() {
		return getBoolean(VARIABLE_NAME_HOME_SERVICE);
	}
	
	public String getVariableNameHomeServiceValue() {
		return VARIABLE_NAME_HOME_SERVICE_VALUE;
	}
	
	public String getHomeServiceValue() {
		return (String) getValue(VARIABLE_NAME_HOME_SERVICE_VALUE);
	}
	
	/*
	 * Social home Service
	 */
	
	public String getVariableNameSocialHomeService() {
		return VARIABLE_NAME_SOCIAL_HOME_SERVICE;
	}
	
	public boolean isSocialHomeServiceSelected() {
		return getBoolean(VARIABLE_NAME_SOCIAL_HOME_SERVICE);
	}
	
	public String getVariableNameSocialHomeServiceValue() {
		return VARIABLE_NAME_SOCIAL_HOME_SERVICE_VALUE;
	}
	
	public String getSocialHomeServiceValue() {
		return (String) getValue(VARIABLE_NAME_SOCIAL_HOME_SERVICE_VALUE);
	}

	/*
	 * Futher personal support
	 */
	
	public String getVariableNameFutherPersonalSupport() {
		return VARIABLE_NAME_FUTHER_PERSONAL_SUPPORT;
	}
	
	public boolean isFutherPersonalSupportSelected() {
		return getBoolean(VARIABLE_NAME_FUTHER_PERSONAL_SUPPORT);
	}
	
	public String getVariableNameFutherPersonalSupportValue() {
		return VARIABLE_NAME_FUTHER_PERSONAL_SUPPORT_VALUE;
	}
	
	public String getFutherPersonalSupportValue() {
		return (String) getValue(VARIABLE_NAME_FUTHER_PERSONAL_SUPPORT_VALUE);
	}

	/*
	 * Short term
	 */
	
	public String getVariableNameShortTerm() {
		return VARIABLE_NAME_SHORT_TERM;
	}

	public boolean isShortTermSelected() {
		return getBoolean(VARIABLE_NAME_SHORT_TERM);
	}
	
	public String getVariableNameShortTermValue() {
		return VARIABLE_NAME_SHORT_TERM_VALUE;
	}
	
	public String getShortTermValue() {
		return (String) getValue(VARIABLE_NAME_SHORT_TERM_VALUE);
	}
	
	/*
	 * Nursing homes
	 */
	
	public String getVariableNameNursingHomes() {
		return VARIABLE_NAME_NURSING_HOMES;
	}

	public boolean isNursingHomesSelected() {
		return getBoolean(VARIABLE_NAME_NURSING_HOMES);
	}

	public String getVariableNameNursingHomesValue() {
		return VARIABLE_NAME_NURSING_HOMES_VALUE;
	}
	
	public String getNursingHomesValue() {
		return (String) getValue(VARIABLE_NAME_NURSING_HOMES_VALUE);
	}

	/*
	 * Other
	 */
	
	public String getVariableNameOther() {
		return VARIABLE_NAME_OTHER;
	}
	
	public boolean isOtherSelected() {
		return getBoolean(VARIABLE_NAME_OTHER);
	}

	public String getVariableNameOtherName() {
		return VARIABLE_NAME_OTHER_NAME;
	}
	
	public String getOtherName() {
		String name = (String) getValue(VARIABLE_NAME_OTHER_NAME);
		if (!StringUtil.isEmpty(name)) {
			return name;
		}
		
		return getLocalizedString("reykjavik.other");
	}

	public String getVariableNameOtherValue() {
		return VARIABLE_NAME_OTHER_VALUE;
	}
	
	public String getOtherValue() {
		return (String) getValue(VARIABLE_NAME_OTHER_VALUE);
	}
	
	/*
	 * Social Activity
	 */
	
	public String getVariableNameSocialActivity() {
		return VARIABLE_NAME_SOCIAL_ACTIVITY;
	}

	public String getSocialActivity() {
		return getString(VARIABLE_NAME_SOCIAL_ACTIVITY);
	}

	/*
	 * Aid received before
	 */
	
	public String getVariableNameAidReceivedBefore() {
		return VARIABLE_NAME_AID_RECEIVED_BEFORE;
	}

	public String getAidReceivedBefore() {
		return getString(VARIABLE_NAME_AID_RECEIVED_BEFORE);
	}

	public String getVariableNameAidReceivedBeforeTime() {
		return VARIABLE_NAME_AID_RECEIVED_BEFORE_TIME;
	}

	public String getAidReceivedBeforeTime() {
		return getString(VARIABLE_NAME_AID_RECEIVED_BEFORE_TIME);
	}

	/*
	 * Required aid
	 */
	
	public String getVariableNameNumberOfTimesInMonth() {
		return VARIABLE_NAME_NUMBER_OF_TIMES_IN_MONTH;
	}

	public Number getNumberOfTimesInMonth() {
		return getNumber(VARIABLE_NAME_NUMBER_OF_TIMES_IN_MONTH);
	}

	public String getVariableNameNumberOfMonth() {
		return VARIABLE_NAME_NUMBER_OF_MONTH;
	}

	public Number getNumberOfMonth() {
		return getNumber(VARIABLE_NAME_NUMBER_OF_MONTH);
	}
	
	/*
	 * Team man
	 */
	
	public String getVariableNameTeamManStatus() {
		return VARIABLE_NAME_TEAM_MAN_STATUS;
	}
	
	public String getTeamManStatus() {
		return getString(VARIABLE_NAME_TEAM_MAN_STATUS);
	}

	/*
	 * Preferences
	 */
	
	public String getVariableNamePreferencesForAgeAndGender() {
		return VARIABLE_NAME_PREFERENCES_FOR_AGE_AND_GENDER;
	}

	public String getPreferencesForAgeAndGender() {
		return getString(VARIABLE_NAME_PREFERENCES_FOR_AGE_AND_GENDER);
	}
	
	public String getVariableNamePreferencesForTime() {
		return VARIABLE_NAME_PREFERENCES_FOR_TIME;
	}
	
	public String getPreferencesForTime() {
		return getString(VARIABLE_NAME_PREFERENCES_FOR_TIME);
	}
}
