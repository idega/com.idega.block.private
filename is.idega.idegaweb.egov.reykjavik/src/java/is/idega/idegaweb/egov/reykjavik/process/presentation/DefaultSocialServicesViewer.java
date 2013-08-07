/**
 * @(#)DafaultSocialServicesViewer.java    1.0.0 12:38:44 PM
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
package is.idega.idegaweb.egov.reykjavik.process.presentation;

import is.idega.idegaweb.egov.reykjavik.ReykjavikConstants;
import is.idega.idegaweb.egov.reykjavik.beans.ReykjavikFormRequestBean;
import is.idega.idegaweb.egov.reykjavik.data.ReykjavikGeneralData;
import is.idega.idegaweb.egov.reykjavik.process.view.DefaultSocialServiceProcessView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.chiba.web.IWBundleStarter;
import org.springframework.beans.factory.annotation.Autowired;

import com.idega.block.web2.business.JQuery;
import com.idega.block.web2.business.JQueryPlugin;
import com.idega.block.web2.business.Web2Business;
import com.idega.bpm.BPMConstants;
import com.idega.bpm.jsfcomponentview.BPMCapableJSFComponent;
import com.idega.bpm.jsfcomponentview.JSFComponentView;
import com.idega.facelets.ui.FaceletComponent;
import com.idega.idegaweb.IWBundle;
import com.idega.idegaweb.IWMainApplication;
import com.idega.idegaweb.IWResourceBundle;
import com.idega.presentation.IWBaseComponent;
import com.idega.presentation.IWContext;
import com.idega.presentation.PDFRenderedComponent;
import com.idega.util.CoreConstants;
import com.idega.util.PresentationUtil;
import com.idega.util.StringUtil;
import com.idega.util.expression.ELUtil;

/**
 * <p>TODO</p>
 * <p>You can report about problems to: 
 * <a href="mailto:martynas@idega.is">Martynas Stakė</a></p>
 *
 * @version 1.0.0 Jul 10, 2013
 * @author <a href="mailto:martynas@idega.is">Martynas Stakė</a>
 */
public abstract class DefaultSocialServicesViewer extends IWBaseComponent
		implements BPMCapableJSFComponent, PDFRenderedComponent{

	private boolean pdfViewer = false;
	
	@Autowired
	private JQuery jQuery;
	
	@Autowired
	private Web2Business web2Business;

	private DefaultSocialServiceProcessView view;
	
	private IWBundle bundle;

	private Long taskInstanceId;
	
	protected abstract Class<? extends ReykjavikGeneralData> getDataClass();

	@Override
	protected void initializeComponent(FacesContext context) {
		super.initializeComponent(context);
		
		IWContext iwc = IWContext.getIWContext(context);
		
		addFiles(iwc);
		
		UIComponent facelet = getIWMainApplication(iwc)
				.createComponent(FaceletComponent.COMPONENT_TYPE);		
		if (facelet instanceof FaceletComponent) {
			((FaceletComponent) facelet).setFaceletURI(getBundle().getFaceletURI(getFaceletFileName()));
		}

		add(facelet);
		
		getReykjavikFormRequestBean(iwc);
	}
	
	/**
	 * 
	 * <p>TODO</p>
	 * @return
	 * @author <a href="mailto:martynas@idega.com">Martynas Stakė</a>
	 */
	public abstract String getFaceletFileName();
	
	public String getViewId() {
		if (view == null) {
			return null;
		}
		
		return view.getViewId();
	}

	public String getViewType() {
		if (view == null) {
			return null;
		}
		
		return view.getViewType();
	}
	
	public Long getTaskInstanceId() {
		return taskInstanceId;
	}

	public void setTaskInstanceId(Long taskInstanceId) {
		this.taskInstanceId = taskInstanceId;
	}
	
	public DefaultSocialServiceProcessView getView() {
		return view;
	}

	/*
	 * (non-Javadoc)
	 * @see com.idega.bpm.jsfcomponentview.BPMCapableJSFComponent#setView(com.idega.bpm.jsfcomponentview.JSFComponentView)
	 */
	@Override
	public void setView(JSFComponentView view) {
		if (view instanceof DefaultSocialServiceProcessView) {
			this.view = (DefaultSocialServiceProcessView) view;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.idega.bpm.jsfcomponentview.BPMCapableJSFComponent#getDefaultDisplayName()
	 */
	@Override
	public String getDefaultDisplayName() {
		if (view == null) {
			return null;
		}
		
		return view.getDefaultDisplayName();
	}

	/*
	 * (non-Javadoc)
	 * @see com.idega.bpm.jsfcomponentview.BPMCapableJSFComponent#getDisplayName(java.util.Locale)
	 */
	@Override
	public String getDisplayName(Locale locale) {
		if (view == null) {
			return null;
		}
		
		return view.getDisplayName(locale);
	}

	@Override
	public boolean isPdfViewer() {
		return pdfViewer;
	}

	@Override
	public void setPdfViewer(boolean pdfViewer) {
		this.pdfViewer = pdfViewer;
	}
	
	private ReykjavikFormRequestBean bean = null;
	
	protected ReykjavikFormRequestBean getReykjavikFormRequestBean(FacesContext context) {
		if (this.bean != null) {
			return this.bean;
		}
		
		String beanName = null;
		if (getView() == null) {
			beanName = ReykjavikFormRequestBean.BEAN_NAME;
		} else {
			beanName = getView().getProcessBean();
		}
		
		this.bean = ELUtil.getInstance().getBean(beanName);
			
		if (this.bean != null) {
			bean.setTaskInstanceId(getTaskInstanceId());
			bean.setDataClass(getDataClass());
			bean.setView(getView());
			bean.setPdf(isPdfViewer());
			addFiles(IWContext.getIWContext(context));
		}
		
		return this.bean;
	}
	
	@SuppressWarnings("unchecked")
	protected void addFiles(IWContext iwc){
		if(isPdfViewer()){
			IWBundle bundle = iwc.getIWMainApplication().getBundle(BPMConstants.IW_BUNDLE_STARTER);
			String pdfCss = bundle.getVirtualPathWithFileNameString("style/pdf.css");
			List<String> resources = null;
			Object o = iwc.getSessionAttribute(PresentationUtil.ATTRIBUTE_CSS_SOURCE_LINE_FOR_HEADER);
			if (o instanceof List) {
				resources = (List<String>) o;
			} else {
				resources = new ArrayList<String>();
			}
			if (!resources.contains(pdfCss)) {
				resources.add(pdfCss);
				iwc.setSessionAttribute(PresentationUtil.ATTRIBUTE_CSS_SOURCE_LINE_FOR_HEADER, resources);
			}
			iwc.setSessionAttribute(PresentationUtil.ATTRIBUTE_ADD_CSS_DIRECTLY, Boolean.TRUE);
		}
		
		IWMainApplication iwma = iwc.getApplicationContext().getIWMainApplication();
		IWBundle iwb = iwma.getBundle(ReykjavikConstants.IW_BUNDLE_IDENTIFIER);
		// Style
		List<String> styles = new ArrayList<String>();
		styles.add(getWeb2Business().getBundleURIToFancyBoxStyleFile());
		styles.add(getWeb2Business().getBundleUriToHumanizedMessagesStyleSheet());
		String styleSheet = new StringBuilder().append(CoreConstants.WEBDAV_SERVLET_URI).append(IWBundleStarter.SLIDE_STYLES_PATH)
		.append(IWBundleStarter.CHIBA_CSS).toString();
		styles.add(styleSheet);
		styles.add(iwb.getVirtualPathWithFileNameString("style/reykjavik.css"));
		PresentationUtil.addStyleSheetsToHeader(iwc, styles);
	
		// Script
		List<String> scripts = new ArrayList<String>();
		scripts.add(CoreConstants.DWR_ENGINE_SCRIPT);
		scripts.add(CoreConstants.DWR_UTIL_SCRIPT);
		scripts.add("/dwr/interface/ReykjavikServices.js");
		
		scripts.add(jQuery.getBundleURIToJQueryLib());
		scripts.add(getWeb2Business().getBundleUriToHumanizedMessagesScript());
		scripts.addAll(getWeb2Business().getBundleURIsToFancyBoxScriptFiles());
		scripts.addAll(jQuery.getBundleURISToValidation());
		scripts.add(jQuery.getBundleURIToJQueryPlugin(JQueryPlugin.MASKED_INPUT));
		scripts.add(iwb.getVirtualPathWithFileNameString("javascript/ReykjavikForms.js"));
		
		PresentationUtil.addJavaScriptSourcesLinesToHeader(iwc, scripts);
	}
	
	protected String getLocalizedString(FacesContext context, String key, String value) {
		if (StringUtil.isEmpty(key) || StringUtil.isEmpty(value)) {
			return null;
		}

		IWResourceBundle iwResourceBundle = getIWResourceBundle(context, 
				ReykjavikConstants.IW_BUNDLE_IDENTIFIER);
		if (iwResourceBundle == null) {
			return null;
		}
		
		return iwResourceBundle.getLocalizedString(key, value);
	}

	protected Web2Business getWeb2Business() {
		if (this.web2Business == null) {
			ELUtil.getInstance().autowire(this);
		}

		return this.web2Business;
	}

	protected JQuery getJQuery() {
		if (this.jQuery == null) {
			ELUtil.getInstance().autowire(this);
		}

		return this.jQuery;
	}
	
	protected IWBundle getBundle() {
		if (bundle == null) {
			bundle = IWMainApplication.getDefaultIWMainApplication().getBundle(
					ReykjavikConstants.IW_BUNDLE_IDENTIFIER);			
		}
		
		return bundle;
	}
	
	protected IWBundle getChibaBundle() {
		return IWMainApplication.getDefaultIWMainApplication().getBundle(
					org.chiba.web.IWBundleStarter.BUNDLE_IDENTIFIER);			
	}
}
