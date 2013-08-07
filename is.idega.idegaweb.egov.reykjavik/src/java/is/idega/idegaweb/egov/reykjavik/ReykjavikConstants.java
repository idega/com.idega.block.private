package is.idega.idegaweb.egov.reykjavik;


/**
 * 
 * @version 1.0.0 Jul 9, 2013
 * @author <a href="mailto:martynas@idega.is">Martynas Stakė</a>
 */
public interface ReykjavikConstants {

	public static final String IW_BUNDLE_IDENTIFIER = "is.idega.idegaweb.egov.reykjavik";
	
	public static final String LOCALIZATION_PREFIX = "reykjavik";
	
	public static final String POSTFIX_SELECTED = "_selected";
	public static final String DATA_PREFIX = "re_";

	public static final String VARIABLE_NAME_PROJECT_CASE_IDENTIFIER = "string_caseIdentifier";
	public static final String VARIABLE_NAME_PROJECT_NAME = "string_caseDescription";
	public static final String VARIABLE_NAME_PARENT_PROCESS_INSTANCE_ID = "mainProcessInstanceId";
	
	public static final String[][] GROUPS_DATA = {
		{"Þjónustumiðstöð Miðborgar og Hlíða", "Skúlagötu", "21", "411 1600"},
		{"Þjónustumiðstöð Laugardals, Háaleitis og Bústaða", "Síðumúla", "39", "411 1500"},
		{"Þjónustumiðstöð Vesturbæjar – Vesturgarður", "Hjarðarhaga", "45", "411 1700"},
		{"Þjónustumiðstöð Breiðholts", "Álfabakka", "12", "411 1300"},
		{"Þjónustumiðst Árbæjar og Grafarholts", "Bæjarhálsi", "1", "411 1400"},
		{"Þjónustumiðstöð Grafarvogs og Kjalarness - Miðgarður", "Langarima", "21", "411 1400"}
	};
	
	public static final String[][] POSTAL_CODES = {
		{"101", "105"},
		{"103", "104", "108"},
		{"107"},
		{"109", "111"},
		{"110", "113"},
		{"112", "116"}
	};
	
	public static final String SOCIAL_SERVICE_CENTERS_CITY = "Reykjavík";
	
	public static final String[] ROLE_NAMES = {
		"bpm_social_service_handler",
		"bpm_social_service_manager"
	};
	
	public static final String[] PROCESS_DEFINITION_NAMES = {
		"ApartmentServices",
		"AssistanceServices",
		"EveningAndWeekendServices",
		"FoodDeliveryServices",
		"HomeServices",
		"HousingBenefitsServices",
		"SpecialHousingBenefitsServices",
		"TransportationServices"
	};
	
	public static final String[] RELATION_UNLOCALIZED_VALUES = {
		"reykjavik.parent",
		"reykjavik.sibling",
		"reykjavik.spouse",
		"reykjavik.child",
		"reykjavik.other"
	};
}
