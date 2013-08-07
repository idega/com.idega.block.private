package is.idega.idegaweb.egov.reykjavik.process.view;

import is.idega.idegaweb.egov.reykjavik.beans.ReykjavikFormRequestBean;
import is.idega.idegaweb.egov.reykjavik.process.presentation.DefaultSocialServicesViewer;
import is.idega.idegaweb.egov.reykjavik.process.presentation.ParentSupportServiceViewer;

public class ParentSupportServiceView  extends DefaultSocialServiceProcessView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8883882405096992832L;
	
	public static final String RESOURCE_IDENTIFIER = "parent_support_service_view";
	
	/* (non-Javadoc)
	 * @see is.idega.idegaweb.egov.reykjavik.process.view.DefaultSocialServiceProcessView#getViewerType()
	 */
	@Override
	public Class<? extends DefaultSocialServicesViewer> getViewerType() {
		return ParentSupportServiceViewer.class;
	}

	/* (non-Javadoc)
	 * @see is.idega.idegaweb.egov.reykjavik.process.view.DefaultSocialServiceProcessView#getFaceletFilename()
	 */
	@Override
	public String getFaceletFilename() {
		return ParentSupportServiceViewer.FACELET_FILENAME;
	}

	/* (non-Javadoc)
	 * @see is.idega.idegaweb.egov.reykjavik.process.view.DefaultSocialServiceProcessView#getProcessName()
	 */
	@Override
	public String getProcessName() {
		return "parentsupportservice";
	}

	/* (non-Javadoc)
	 * @see is.idega.idegaweb.egov.reykjavik.process.view.DefaultSocialServiceProcessView#getLocalizationKey()
	 */
	@Override
	public String getLocalizationKey() {
		return "parentsupportservice";
	}
	
	@Override
	public String getDefaultDisplayName() {
		return "parentsupportservice";
	}

	/* (non-Javadoc)
	 * @see is.idega.idegaweb.egov.reykjavik.process.view.DefaultSocialServiceProcessView#getProcessBean()
	 */
	@Override
	public String getProcessBean() {
		return ReykjavikFormRequestBean.BEAN_NAME;
	}

}
