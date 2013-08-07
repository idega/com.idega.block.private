package is.idega.idegaweb.egov.reykjavik.process.presentation;

import is.idega.idegaweb.egov.reykjavik.data.ParentSupportServiceData;
import is.idega.idegaweb.egov.reykjavik.data.ReykjavikGeneralData;

public class ParentSupportServiceViewer extends DefaultSocialServicesViewer {

	public static final String FACELET_FILENAME = "parent-support-service.xhtml";
	
	@Override
	public String getFaceletFileName() {
		return FACELET_FILENAME;
	}

	@Override
	protected Class<? extends ReykjavikGeneralData> getDataClass() {
		return ParentSupportServiceData.class;
	}
}