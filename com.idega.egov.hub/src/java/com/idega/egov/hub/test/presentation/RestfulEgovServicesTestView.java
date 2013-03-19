package com.idega.egov.hub.test.presentation;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.context.FacesContext;

import com.idega.block.article.component.article_view.ArticleEdit;
import com.idega.block.web2.business.JQuery;
import com.idega.block.web2.business.Web2Business;
import com.idega.egov.hub.HubConstants;
import com.idega.facelets.ui.FaceletComponent;
import com.idega.idegaweb.IWBundle;
import com.idega.idegaweb.IWMainApplication;
import com.idega.idegaweb.IWResourceBundle;
import com.idega.presentation.IWBaseComponent;
import com.idega.presentation.IWContext;
import com.idega.presentation.Layer;
import com.idega.presentation.text.Link;
import com.idega.util.CoreConstants;
import com.idega.util.PresentationUtil;
import com.idega.webface.WFUtil;

public class RestfulEgovServicesTestView extends IWBaseComponent {

	private static final String PARAMETER_TEST_FORM = "res-test-form";

	private static final String PARAMETER_VALUE_ADD_SERVICE = "res-test-add-service";
	private static final String PARAMETER_VALUE_READ_MESSAGE = "res-test-read-message";
	private static final String PARAMETER_VALUE_READ_CASE = "res-test-read-case";

	@Override
	protected void initializeComponent(FacesContext context) {
		super.initializeComponent(context);

		IWContext iwc = IWContext.getIWContext(context);
		IWBundle bundle = iwc.getIWMainApplication().getBundle(HubConstants.IW_BUNDLE_IDENTIFIER);
		IWResourceBundle iwrb = bundle.getResourceBundle(iwc);

		Layer main = new Layer();
		add(main);

		Link link = new Link(iwrb.getLocalizedString("add_service", "Add service"));
		link.addParameter(PARAMETER_TEST_FORM, PARAMETER_VALUE_ADD_SERVICE);
		main.add(link);
		link.setStyleAttribute("display", "block");

		link = new Link(iwrb.getLocalizedString("read_message", "Read message"));
		link.addParameter(PARAMETER_TEST_FORM, PARAMETER_VALUE_READ_MESSAGE);
		main.add(link);
		link.setStyleAttribute("display", "block");

		link = new Link(iwrb.getLocalizedString("read_case", "Read case"));
		link.addParameter(PARAMETER_TEST_FORM, PARAMETER_VALUE_READ_CASE);
		main.add(link);
		link.setStyleAttribute("display", "block");

		FaceletComponent facelet = null;
		String testForm = iwc.getParameter(PARAMETER_TEST_FORM);
		if(PARAMETER_VALUE_ADD_SERVICE.equals(testForm)){
			facelet = (FaceletComponent)iwc.getApplication().createComponent(FaceletComponent.COMPONENT_TYPE);
			facelet.setFaceletURI(bundle.getFaceletURI("test/res-add-service.xhtml"));
		}else if(PARAMETER_VALUE_READ_MESSAGE.equals(testForm)){
			facelet = (FaceletComponent)iwc.getApplication().createComponent(FaceletComponent.COMPONENT_TYPE);
			facelet.setFaceletURI(bundle.getFaceletURI("test/res-read-message.xhtml"));
		}else if(PARAMETER_VALUE_READ_CASE.equals(testForm)){
			facelet = (FaceletComponent)iwc.getApplication().createComponent(FaceletComponent.COMPONENT_TYPE);
			facelet.setFaceletURI(bundle.getFaceletURI("test/res-read-case.xhtml"));
		}

		if(facelet != null){
			main.add(facelet);
		}

		Layer content = new Layer();
		content.setStyleAttribute("border", "solid black 2px");
		content.setStyleAttribute("height", "300px");
		content.setStyleAttribute("overflow", "auto");
		main.add(content);
		content.setStyleClass("test-content");

		Layer contentContainer = new Layer();
		content.add(contentContainer);
		contentContainer.setStyleClass("console-content");
		addFiles(iwc);
	}

	private void addFiles(IWContext iwc){
		List<String> scripts = new ArrayList<String>();
		List<String> styles = new ArrayList<String>();

		scripts.add(CoreConstants.DWR_ENGINE_SCRIPT);
		scripts.add(CoreConstants.DWR_UTIL_SCRIPT);

		Web2Business web2 = WFUtil.getBeanInstance(iwc, Web2Business.SPRING_BEAN_IDENTIFIER);
		if (web2 != null) {
			JQuery  jQuery = web2.getJQuery();
			scripts.add(jQuery.getBundleURIToJQueryLib());

			scripts.add(web2.getBundleUriToHumanizedMessagesScript());
			styles.add(web2.getBundleUriToHumanizedMessagesStyleSheet());

		}else{
			Logger.getLogger(ArticleEdit.class.getName()).log(Level.WARNING, "Failed getting Web2Business no jQuery and it's plugins files were added");
		}

		IWMainApplication iwma = iwc.getApplicationContext().getIWMainApplication();
		IWBundle iwb = iwma.getBundle(HubConstants.IW_BUNDLE_IDENTIFIER);
		scripts.add(iwb.getVirtualPathWithFileNameString("javascript/test/RESHelper.js"));
		scripts.add("/dwr/interface/RestfulEGovServicesTest.js");

		PresentationUtil.addJavaScriptSourcesLinesToHeader(iwc, scripts);
		PresentationUtil.addStyleSheetsToHeader(iwc, styles);
	}
}