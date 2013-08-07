package is.idega.idegaweb.egov.reykjavik.process.view;

import is.idega.idegaweb.egov.reykjavik.beans.ReykjavikFormRequestBean;
import is.idega.idegaweb.egov.reykjavik.process.presentation.DefaultSocialServicesViewer;
import is.idega.idegaweb.egov.reykjavik.process.presentation.PersonalizedAssistanceViewer;

public class PersonalizedAssistanceView  extends DefaultSocialServiceProcessView {

	private static final long serialVersionUID = 1707187192130078976L;

	public static final String RESOURCE_IDENTIFIER = "personalized_assistance_service_view";
	
	/* (non-Javadoc)
	 * @see is.idega.idegaweb.egov.reykjavik.process.view.DefaultSocialServiceProcessView#getViewerType()
	 */
	@Override
	public Class<? extends DefaultSocialServicesViewer> getViewerType() {
		return PersonalizedAssistanceViewer.class;
	}

	/* (non-Javadoc)
	 * @see is.idega.idegaweb.egov.reykjavik.process.view.DefaultSocialServiceProcessView#getFaceletFilename()
	 */
	@Override
	public String getFaceletFilename() {
		return PersonalizedAssistanceViewer.FACELET_FILENAME;
	}

	/* (non-Javadoc)
	 * @see is.idega.idegaweb.egov.reykjavik.process.view.DefaultSocialServiceProcessView#getProcessName()
	 */
	@Override
	public String getProcessName() {
		return "personalizedAssistance";
	}

	/* (non-Javadoc)
	 * @see is.idega.idegaweb.egov.reykjavik.process.view.DefaultSocialServiceProcessView#getLocalizationKey()
	 */
	@Override
	public String getLocalizationKey() {
		return "personalizedassistance";
	}
	
	@Override
	public String getDefaultDisplayName() {
		return "personalizedassistance";
	}

	/* (non-Javadoc)
	 * @see is.idega.idegaweb.egov.reykjavik.process.view.DefaultSocialServiceProcessView#getProcessBean()
	 */
	@Override
	public String getProcessBean() {
		return ReykjavikFormRequestBean.BEAN_NAME;
	}

}
