/**
 * @(#)DefaultSocialServiceProcessView.java    1.0.0 12:25:39 PM
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
package is.idega.idegaweb.egov.reykjavik.process.view;

import is.idega.idegaweb.egov.reykjavik.ReykjavikConstants;
import is.idega.idegaweb.egov.reykjavik.process.presentation.DefaultSocialServicesViewer;

import java.io.InputStream;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.component.UIComponent;

import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;

import com.idega.bpm.jsfcomponentview.JSFComponentView;
import com.idega.bpm.xformsview.XFormsView;
import com.idega.idegaweb.IWBundle;
import com.idega.idegaweb.IWMainApplication;
import com.idega.idegaweb.IWResourceBundle;
import com.idega.jbpm.exe.BPMFactory;
import com.idega.jbpm.exe.TaskInstanceW;
import com.idega.jbpm.view.View;
import com.idega.util.IOUtil;
import com.idega.util.StringUtil;
import com.idega.util.expression.ELUtil;
import com.idega.util.xml.XmlUtil;
import com.idega.xformsmanager.business.DocumentManagerFactory;

/**
 * <p>TODO</p>
 * <p>You can report about problems to: 
 * <a href="mailto:martynas@idega.is">Martynas Stakė</a></p>
 *
 * @version 1.0.0 Jul 10, 2013
 * @author <a href="mailto:martynas@idega.is">Martynas Stakė</a>
 */
public abstract class DefaultSocialServiceProcessView extends JSFComponentView {

	protected static final Logger LOGGER = Logger.getLogger(DefaultSocialServiceProcessView.class.getName());
	
	private static final long serialVersionUID = -5634873424406581964L;

	@Autowired
	private BPMFactory bpmFactory;

	@Autowired
	private DocumentManagerFactory documentManagerFactory;
	
	private Map<String,String> parameters;

	@Override
	public String getViewType() {
		return ReykjavikViewFactory.VIEW_TYPE;
	}

	@Override
	public UIComponent getViewForDisplay(boolean pdfViewer) {
		if (pdfViewer) {
			TaskInstanceW tiW = getBPMFactory().getTaskInstanceW(getTaskInstanceId());
			View view = tiW.loadView();
			if (view instanceof XFormsView) {
				Document xform = getXForm();
				if (xform == null) {
					throw new RuntimeException("XForm " + getFaceletFilename() + " can not be found!");
				}
				com.idega.xformsmanager.business.Document formDocument = getDocumentManagerFactory()
						.newDocumentManager(IWMainApplication.getDefaultIWMainApplication()).openForm(xform);
				((XFormsView) view).setFormDocument(formDocument);
			}
//			return view.getViewForDisplay(pdfViewer);
		}

		return getViewForWeb();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.idega.bpm.jsfcomponentview.JSFComponentView#getDisplayName(java.util.Locale)
	 */
	@Override
	public String getDisplayName(Locale locale) {
		return getLocalizedString(locale, getLocalizationKey(), getDefaultDisplayName());
	}

	protected Document getXForm() {
		InputStream stream = null;
		
		// XXX: What nonsense is this?
		String pathWithinBundle = "resources/processes/" + getProcessName() + 
				"/forms/" + getFaceletFilename();
		try {
			stream = getIWBundle().getResourceInputStream(pathWithinBundle);
			return XmlUtil.getXMLDocument(stream);
		} catch (Exception e) {
			Logger.getLogger(getClass().getName()).log(
					Level.WARNING, "Error getting XForm from " + pathWithinBundle, e);
		} finally {
			IOUtil.close(stream);
		}

		return null;
	}

	protected String getLocalizedString(Locale locale, String key, String returnValue) {
		IWResourceBundle bundle = getIWResourceBundle(locale);
		if (bundle == null || StringUtil.isEmpty(key)) {
			return null;
		}
		
		return bundle.getLocalizedString(locale, key, returnValue);
	}
	
	protected IWResourceBundle getIWResourceBundle(Locale locale) {
		if (locale == null) {
			return null;
		}
		
		IWBundle bundle = getIWBundle();
		if (bundle == null) {
			return null;
		}
		
		return bundle.getResourceBundle(locale);
	}
	
	protected IWBundle getIWBundle() {
		return IWMainApplication.getDefaultIWMainApplication().getBundle(
				ReykjavikConstants.IW_BUNDLE_IDENTIFIER);
	}
	
	protected BPMFactory getBPMFactory() {
		if (bpmFactory == null)
			ELUtil.getInstance().autowire(this);
		return bpmFactory;
	}
	
	protected DocumentManagerFactory getDocumentManagerFactory() {
		if (documentManagerFactory == null)
			ELUtil.getInstance().autowire(this);
		return documentManagerFactory;
	}
	
	protected static <T extends DefaultSocialServicesViewer> T getInstantiatedObject(
			java.lang.Class<T> type) {
		if (type == null) {
			return null;
		}
		
		try {
			return type.newInstance();
		} catch (InstantiationException e) {
			LOGGER.log(Level.WARNING, "Failed to create object of: " + type);
		} catch (IllegalAccessException e) {
			LOGGER.log(Level.WARNING, "Class: " + type + " is not accessible.");
		}
		
		return null;
	}

	protected UIComponent getViewForWeb() {
		DefaultSocialServicesViewer ui = getInstantiatedObject(getViewerType());
		ui.setTaskInstanceId(getTaskInstanceId());
		ui.setView(this);
		return ui;
	}
	
	/**
	 * 
	 * <p>Give class type of class implementing {@link DefaultSocialServicesViewer}.</p>
	 * @return class type for this view;
	 * @author <a href="mailto:martynas@idega.com">Martynas Stakė</a>
	 */
	public abstract java.lang.Class<? extends DefaultSocialServicesViewer> getViewerType();
	
	/**
	 * 
	 * @return filename of application form, defined in /facelets directory;;
	 * @author <a href="mailto:martynas@idega.com">Martynas Stakė</a>
	 */
	public abstract String getFaceletFilename();
	
	/**
	 * 
	 * @return folder name of process in resources/processes folder;
	 * @author <a href="mailto:martynas@idega.com">Martynas Stakė</a>
	 */
	public abstract String getProcessName();
	
	/**
	 * 
	 * @return localization key for form name;
	 * @author <a href="mailto:martynas@idega.com">Martynas Stakė</a>
	 */
	public abstract String getLocalizationKey();

	/**
	 * 
	 * @return bean name, which contains variables, required for process
	 * @author <a href="mailto:martynas@idega.com">Martynas Stakė</a>
	 */
	public abstract String getProcessBean();

	@Override
	public Map<String, String> resolveParameters() {
		return parameters;
	}
}
