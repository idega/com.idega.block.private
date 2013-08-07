/**
 * @(#)ServiceDayBean.java    1.0.0 10:35:27 AM
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



/**
 * <p>Data career for info about services at selected date.</p>
 * <p>You can report about problems to: 
 * <a href="mailto:martynas@idega.is">Martynas Stakė</a></p>
 *
 * @version 1.0.0 Jul 11, 2013
 * @author <a href="mailto:martynas@idega.is">Martynas Stakė</a>
 */
public class WeekServiceData extends ReykjavikGeneralData {

	public static final String DAY_SELECTED = "string_daySelected";
	public static final String DAY_TIME_FROM = "string_dayFrom";
	public static final String DAY_TIME_TO = "string_dayTo";
	// Sunday
	public static final String VARIABLE_NAME_SUNDAY = DAY_SELECTED+0;
	public static final String VARIABLE_NAME_SUNDAY_START_TIME = DAY_TIME_FROM+0;
	public static final String VARIABLE_NAME_SUNDAY_END_TIME = DAY_TIME_TO+0;	
	
	// Monday
	public static final String VARIABLE_NAME_MONDAY = DAY_SELECTED+1;
	public static final String VARIABLE_NAME_MONDAY_START_TIME = DAY_TIME_FROM+1;
	public static final String VARIABLE_NAME_MONDAY_END_TIME = DAY_TIME_TO+1;	
	
	// Tuesday
	public static final String VARIABLE_NAME_TUESDAY = DAY_SELECTED+2;
	public static final String VARIABLE_NAME_TUESDAY_START_TIME = DAY_TIME_FROM+2;
	public static final String VARIABLE_NAME_TUESDAY_END_TIME = DAY_TIME_TO+2;	
		
	// Wednesday
	public static final String VARIABLE_NAME_WEDNESDAY = DAY_SELECTED+3;
	public static final String VARIABLE_NAME_WEDNESDAY_START_TIME = DAY_TIME_FROM+3;
	public static final String VARIABLE_NAME_WEDNESDAY_END_TIME = DAY_TIME_TO+3;	
	
	// Thursday
	public static final String VARIABLE_NAME_THURSDAY = DAY_SELECTED+4;
	public static final String VARIABLE_NAME_THURSDAY_START_TIME = DAY_TIME_FROM+4;
	public static final String VARIABLE_NAME_THURSDAY_END_TIME = DAY_TIME_TO+4;
	
	// Friday
	public static final String VARIABLE_NAME_FRIDAY = DAY_SELECTED+5;
	public static final String VARIABLE_NAME_FRIDAY_START_TIME = DAY_TIME_FROM+5;
	public static final String VARIABLE_NAME_FRIDAY_END_TIME = DAY_TIME_TO+5;
	
	// Saturday
	public static final String VARIABLE_NAME_SATURDAY = DAY_SELECTED+6;
	public static final String VARIABLE_NAME_SATURDAY_START_TIME = DAY_TIME_FROM+6;
	public static final String VARIABLE_NAME_SATURDAY_END_TIME = DAY_TIME_TO+6;

	/*
	 * Sunday
	 */
	public String getSundaySelected() {
		return (String) getValue(VARIABLE_NAME_SUNDAY);
	}
	
	public String getStartTimeAtSunday() {
		return (String) getValue(VARIABLE_NAME_SUNDAY_START_TIME);
	}

	public String getEndTimeAtSunday() {
		return (String) getValue(VARIABLE_NAME_SUNDAY_END_TIME);
	}

	public String getVariableNameSundaySelected() {
		return VARIABLE_NAME_SUNDAY;
	}

	public String getVariableNameStartTimeAtSunday() {
		return VARIABLE_NAME_SUNDAY_START_TIME;
	}

	public String getVariableNameEndTimeAtSunday() {
		return VARIABLE_NAME_SUNDAY_END_TIME;
	}
	
	/*
	 * Monday
	 */
	public String getMondaySelected() {
		return (String) getValue(VARIABLE_NAME_MONDAY);
	}
	
	public String getStartTimeAtMonday() {
		return (String) getValue(VARIABLE_NAME_MONDAY_START_TIME);
	}

	public String getEndTimeAtMonday() {
		return (String) getValue(VARIABLE_NAME_MONDAY_END_TIME);
	}

	public String getVariableNameMondaySelected() {
		return VARIABLE_NAME_MONDAY;
	}

	public String getVariableNameStartTimeAtMonday() {
		return VARIABLE_NAME_MONDAY_START_TIME;
	}

	public String getVariableNameEndTimeAtMonday() {
		return VARIABLE_NAME_MONDAY_END_TIME;
	}
	
	/*
	 * Tuesday
	 */
	public String getTuesdaySelected() {
		return (String) getValue(VARIABLE_NAME_TUESDAY);
	}
	
	public String getStartTimeAtTuesday() {
		return (String) getValue(VARIABLE_NAME_TUESDAY_START_TIME);
	}

	public String getEndTimeAtTuesday() {
		return (String) getValue(VARIABLE_NAME_TUESDAY_END_TIME);
	}

	public String getVariableNameTuesdaySelected() {
		return VARIABLE_NAME_TUESDAY;
	}

	public String getVariableNameStartTimeAtTuesday() {
		return VARIABLE_NAME_TUESDAY_START_TIME;
	}

	public String getVariableNameEndTimeAtTuesday() {
		return VARIABLE_NAME_TUESDAY_END_TIME;
	}
	
	/*
	 * Wednesday
	 */
	public String getWednesdaySelected() {
		return (String) getValue(VARIABLE_NAME_WEDNESDAY);
	}
	
	public String getStartTimeAtWednesday() {
		return (String) getValue(VARIABLE_NAME_WEDNESDAY_START_TIME);
	}

	public String getEndTimeAtWednesday() {
		return (String) getValue(VARIABLE_NAME_WEDNESDAY_END_TIME);
	}

	public String getVariableNameWednesdaySelected() {
		return VARIABLE_NAME_WEDNESDAY;
	}

	public String getVariableNameStartTimeAtWednesday() {
		return VARIABLE_NAME_WEDNESDAY_START_TIME;
	}

	public String getVariableNameEndTimeAtWednesday() {
		return VARIABLE_NAME_WEDNESDAY_END_TIME;
	}
	
	/*
	 * Thursday
	 */
	public String getThursdaySelected() {
		return (String) getValue(VARIABLE_NAME_THURSDAY);
	}
	
	public String getStartTimeAtThursday() {
		return (String) getValue(VARIABLE_NAME_THURSDAY_START_TIME);
	}

	public String getEndTimeAtThursday() {
		return (String) getValue(VARIABLE_NAME_THURSDAY_END_TIME);
	}

	public String getVariableNameThursdaySelected() {
		return VARIABLE_NAME_THURSDAY;
	}

	public String getVariableNameStartTimeAtThursday() {
		return VARIABLE_NAME_THURSDAY_START_TIME;
	}

	public String getVariableNameEndTimeAtThursday() {
		return VARIABLE_NAME_THURSDAY_END_TIME;
	}
	
	/*
	 * Friday
	 */
	public String getFridaySelected() {
		return (String) getValue(VARIABLE_NAME_FRIDAY);
	}
	
	public String getStartTimeAtFriday() {
		return (String) getValue(VARIABLE_NAME_FRIDAY_START_TIME);
	}

	public String getEndTimeAtFriday() {
		return (String) getValue(VARIABLE_NAME_FRIDAY_END_TIME);
	}

	public String getVariableNameFridaySelected() {
		return VARIABLE_NAME_FRIDAY;
	}

	public String getVariableNameStartTimeAtFriday() {
		return VARIABLE_NAME_FRIDAY_START_TIME;
	}

	public String getVariableNameEndTimeAtFriday() {
		return VARIABLE_NAME_FRIDAY_END_TIME;
	}
	
	/*
	 * Saturday
	 */
	public String getSaturdaySelected() {
		return (String) getValue(VARIABLE_NAME_SATURDAY);
	}
	
	public String getStartTimeAtSaturday() {
		return (String) getValue(VARIABLE_NAME_SATURDAY_START_TIME);
	}

	public String getEndTimeAtSaturday() {
		return (String) getValue(VARIABLE_NAME_SATURDAY_END_TIME);
	}

	public String getVariableNameSaturdaySelected() {
		return VARIABLE_NAME_SATURDAY;
	}

	public String getVariableNameStartTimeAtSaturday() {
		return VARIABLE_NAME_SATURDAY_START_TIME;
	}

	public String getVariableNameEndTimeAtSaturday() {
		return VARIABLE_NAME_SATURDAY_END_TIME;
	}
}
