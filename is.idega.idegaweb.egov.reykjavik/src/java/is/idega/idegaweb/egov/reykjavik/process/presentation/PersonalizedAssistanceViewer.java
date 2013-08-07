package is.idega.idegaweb.egov.reykjavik.process.presentation;

import is.idega.idegaweb.egov.reykjavik.data.PersonalizedAssistanceData;
import is.idega.idegaweb.egov.reykjavik.data.ReykjavikGeneralData;

public class PersonalizedAssistanceViewer extends DefaultSocialServicesViewer {

	public static final String FACELET_FILENAME = "personalized-assistance-service.xhtml";
	
	@Override
	public String getFaceletFileName() {
		return FACELET_FILENAME;
	}

	@Override
	protected Class<? extends ReykjavikGeneralData> getDataClass() {
		return PersonalizedAssistanceData.class;
	}
}