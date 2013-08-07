package is.idega.idegaweb.egov.reykjavik.process.presentation;

import is.idega.idegaweb.egov.reykjavik.data.ReykjavikGeneralData;
import is.idega.idegaweb.egov.reykjavik.data.dao.TransportServicesData;


public class TransportServicesForSeniorsForm extends DefaultSocialServicesViewer {

	public static final String FACELET_FILENAME = "transport-services-for-seniors.xhtml";
	
	@Override
	public String getFaceletFileName() {
		return FACELET_FILENAME;
	}

	@Override
	protected Class<? extends ReykjavikGeneralData> getDataClass() {
		return TransportServicesData.class;
	}
	
	
	
}