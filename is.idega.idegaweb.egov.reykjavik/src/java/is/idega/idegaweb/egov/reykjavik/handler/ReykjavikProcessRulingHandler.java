/**
 * @(#)ReykjavikProcessRulingHandler.java    1.0.0 3:24:31 PM
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
 *     Licensee shall also mean the individual using or installing 
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
package is.idega.idegaweb.egov.reykjavik.handler;

import is.idega.idegaweb.egov.bpm.cases.actionhandlers.CasesStatusHandler;
import is.idega.idegaweb.egov.bpm.cases.manager.BPMCasesRetrievalManagerImpl;
import is.idega.idegaweb.egov.cases.business.CasesBusiness;
import is.idega.idegaweb.egov.reykjavik.ReykjavikConstants;
import is.idega.idegaweb.egov.reykjavik.data.ReykjavikGeneralData;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;

import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.graph.exe.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.idega.block.process.business.CasesRetrievalManager;
import com.idega.block.process.data.Case;
import com.idega.block.process.data.CaseStatus;
import com.idega.bpm.process.messages.SendMessagesHandler;
import com.idega.business.IBOLookup;
import com.idega.business.IBOLookupException;
import com.idega.core.contact.data.Email;
import com.idega.idegaweb.IWApplicationContext;
import com.idega.idegaweb.IWMainApplication;
import com.idega.idegaweb.IWResourceBundle;
import com.idega.idegaweb.egov.bpm.data.CaseProcInstBind;
import com.idega.idegaweb.egov.bpm.data.dao.CasesBPMDAO;
import com.idega.jbpm.data.VariableInstanceQuerier;
import com.idega.jbpm.data.dao.BPMDAO;
import com.idega.jbpm.identity.BPMUser;
import com.idega.user.data.User;
import com.idega.util.CoreUtil;
import com.idega.util.ListUtil;
import com.idega.util.StringHandler;
import com.idega.util.StringUtil;
import com.idega.util.expression.ELUtil;

/**
 * <p>Handler for sending notifications via mail on "Funding process".</p>
 * <p>You can report about problems to: 
 * <a href="mailto:martynas@idega.is">Martynas Stakė</a></p>
 *
 * @version 1.0.0 Feb 11, 2013
 * @author <a href="mailto:martynas@idega.is">Martynas Stakė</a>
 */
@Service(ReykjavikProcessRulingHandler.BEAN_NAME)
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class ReykjavikProcessRulingHandler extends CasesStatusHandler {

	private static final long serialVersionUID = 1955270058075469522L;
	
	public static final String BEAN_NAME = "reykjavikProcessRulingHandler";
	
	@Autowired
	private BPMDAO bpmDAO;
	
	@Autowired
	@Qualifier(BPMCasesRetrievalManagerImpl.beanIdentifier)
	private CasesRetrievalManager casesRetrievalManager;
	
	@Autowired
	private VariableInstanceQuerier variableInstanceQuerier;
	
	@Autowired
	private CasesBPMDAO casesBPMDAO;
	
	private CasesBusiness casesBusiness = null;
	
	private SendMessagesHandler sendMessagesHandler = null;
	
	protected SendMessagesHandler getMessagesHandler() {
		if (this.sendMessagesHandler == null) {
			this.sendMessagesHandler = ELUtil.getInstance().getBean(SendMessagesHandler.BEAN_NAME);
		}
		
		return this.sendMessagesHandler;
	}
	
	protected CasesRetrievalManager getCasesRetrievalManager() {
		if (this.casesRetrievalManager == null) {
			ELUtil.getInstance().autowire(this);
		}
		
		return this.casesRetrievalManager;
	}
	
	protected VariableInstanceQuerier getVariableInstanceQuerier() {
		if (this.variableInstanceQuerier == null) {
			ELUtil.getInstance().autowire(this);
		}
		
		return this.variableInstanceQuerier;
	}
	
	protected CasesBusiness getCasesBusiness(IWApplicationContext iwac) {
		if (this.casesBusiness != null) {
			return this.casesBusiness;
		}
		
		try {
			this.casesBusiness = IBOLookup.getServiceInstance(iwac, CasesBusiness.class);
		} catch (IBOLookupException ile) {
			getLogger().log(
					Level.WARNING, "Failed to find " + CasesBusiness.class);
		}
		
		return this.casesBusiness;
	}
	
	protected CasesBPMDAO getCasesDAO() {
		if (this.casesBPMDAO == null) {
			ELUtil.getInstance().autowire(this);
		}
		
		return casesBPMDAO;
	}

	/**
	 * 
	 * <p>Stores new status or current {@link ProcessInstance} 
	 * to data source.</p>
	 * @param ectx is context, containing {@link ProcessInstance#getId()}, not
	 * <code>null</code>;
	 * @param status to store, not <code>null</code>;
	 * @author <a href="mailto:martynas@idega.is">Martynas Stakė</a>
	 */
	protected void update(ExecutionContext ectx, CaseStatus status) {
		if (ectx == null || status == null) {
			return;
		}
		
		Case theCase = getCase(ectx.getProcessInstance().getId());
		if (theCase == null) {
			setCaseStatus(status.getStatus());
			return;		
		}

		theCase.setCaseStatus(status);
		theCase.store();
	}
	
	public BPMDAO getBPMDAO() {
		if (bpmDAO == null) {
			ELUtil.getInstance().autowire(this);
		}
		
		return bpmDAO;
	}
	
	/**
	 * 
	 * @param processInstanceId is {@link ProcessInstance#getId()} to find 
	 * {@link Case} by, not <code>null</code>;
	 * @return binded {@link Case} to given {@link ProcessInstance} or 
	 * <code>null</code> on failure;
	 * @author <a href="mailto:martynas@idega.is">Martynas Stakė</a>
	 */
	protected Case getCase(Long processInstanceId) {
		CaseProcInstBind bind = getBPMDAO().find(CaseProcInstBind.class, processInstanceId);
		if (bind == null) {
			return null;
		}
		
		try {
			return getCaseHome().findByPrimaryKey(bind.getCaseId());
		} catch(Exception e) {
			getLogger().log(Level.WARNING, 
					"Error getting case by id: " + bind.getCaseId(), e);
		}
		
		return null;
	}
	
	protected void sendNotification(ExecutionContext ectx, String status) throws Exception {
    	SendMessagesHandler messagesHandler = getMessagesHandler();
    	if (messagesHandler == null) {
    		getLogger().warning("Bean " + SendMessagesHandler.class + " is undefined!");
    		return;
    	}

    	String localizedMessage = null;
    	
    	// Case identifier
    	Object caseIdentifier = ectx.getVariable(ReykjavikConstants.VARIABLE_NAME_PROJECT_CASE_IDENTIFIER);
    	
    	//	Subject
    	Locale locale = getCurrentLocale();
    	Map<String, String> inlineSubject = new HashMap<String, String>();
    	IWResourceBundle iwrb = getBundle(ReykjavikConstants.IW_BUNDLE_IDENTIFIER).getResourceBundle(locale);
    	String key = "your_application_has_been_changed";
    	String defaultValue = "Your application '$case_identifier$' has been changed";
    	localizedMessage = iwrb.getLocalizedString(key, defaultValue);
    	localizedMessage = StringHandler.replace(localizedMessage, "$case_identifier$", (String) caseIdentifier);

    	inlineSubject.put(locale.toString(), localizedMessage);
    	messagesHandler.setInlineSubject(inlineSubject);

    	//	Project name
    	Object projectName = ectx.getVariable(ReykjavikConstants.VARIABLE_NAME_PROJECT_NAME);

    	//	New status of application
    	CasesBusiness casesBusines = getCasesBusiness(IWMainApplication.getDefaultIWApplicationContext());
    	CaseStatus caseStatus = casesBusines.getCaseStatus(status);
    	String localizedStatus = casesBusines.getLocalizedCaseStatusDescription(caseStatus, locale);
    	
    	// Owner of case
    	User owner = getCasesRetrievalManager().getCaseOwner(ectx.getVariable(
    			ReykjavikConstants.VARIABLE_NAME_PARENT_PROCESS_INSTANCE_ID));
    	String ownerPrimaryKey = owner.getPrimaryKey().toString();
    	Integer ownerPrimaryKeyInInteger = null;
    	if (!StringUtil.isEmpty(ownerPrimaryKey)) {
    		try {
    			ownerPrimaryKeyInInteger = Integer.valueOf(ownerPrimaryKey);
    		} catch (NumberFormatException e) {
    			getLogger().log(Level.WARNING, 
    					"Unable to convert: " + ownerPrimaryKey + 
    					" to " + Integer.class);
    		}
    	}
    	
    	// Link of application
    	String link = null;
    	if (ownerPrimaryKeyInInteger != null) {
    		BPMUser bpmUser = getBpmFactory().getBpmUserFactory().getBPMUser(
    				ownerPrimaryKeyInInteger
    				);
    		bpmUser.setProcessInstanceId(ectx.getProcessInstance().getId());
    		
    		try {
    			link = bpmUser.getUrlToTheProcess();
    		} catch (java.lang.RuntimeException e) {
    			getLogger().log(Level.WARNING, "Unable to get page for " + User.class);
    		}
    	}
    	
    	//	Message
    	Map<String, String> inlineMessage = new HashMap<String, String>();
    	key = "your_application_to_for_funding_has_been_changed";
    	defaultValue = "Your application for funding project '$project_name$' was changed to '$application_status$'. ";
    	localizedMessage = iwrb.getLocalizedString(key, defaultValue);
    	localizedMessage = StringHandler.replace(localizedMessage, "$project_name$", (String) projectName);
    	localizedMessage = StringHandler.replace(localizedMessage, "$application_status$", localizedStatus.toLowerCase(locale));
    	
    	if (!StringUtil.isEmpty(link)) {
        	localizedMessage = localizedMessage + iwrb.getLocalizedString(
        			"you_can_follow_it_by_link", 
        			"You can follow it by link: ") + link;
    	}
    	
    	inlineMessage.put(locale.toString(), localizedMessage);
    	messagesHandler.setInlineMessage(inlineMessage);

    	//	Message receiver
    	Object responsiblePersonID = ectx.getVariable(
    			ReykjavikGeneralData.VARIABLE_NAME_USER_PERSONAL_ID);    
    	String emailAddress = getMailByPersonalID(ectx, (String) responsiblePersonID);
    	if (StringUtil.isEmpty(emailAddress)) {
    		getLogger().log(Level.WARNING, "Failed to get email by id: " + emailAddress);
    	}
    	
    	messagesHandler.setSendToEmails(Arrays.asList(emailAddress));
    	messagesHandler.execute(ectx);
    }
	
	/**
	 * 
	 * <p>Searches for user or company by primary key or personal id in 
	 * ic_user, ic_company, jbpm_variableinstance tables.</p>
	 * @param ectx is context, which has {@link ProcessInstance#getId()}, 
	 * not <code>null</code>;
	 * @param personalID is personal id or primary key of {@link User}
	 * or {@link Company}, not <code>null</code>;
	 * @return {@link Email#getEmailAddress()} by given criteria or 
	 * <code>null</code> on failure.
	 * @author <a href="mailto:martynas@idega.is">Martynas Stakė</a>
	 */
	protected String getMailByPersonalID(ExecutionContext ectx, String personalID) {
		if (StringUtil.isEmpty(personalID)) {
			return null;
		}
				
		/* Searching for user email locally: */
		User user = getUser(personalID);
		if (user != null) {
			try {
				return user.getUsersEmail().getEmailAddress();
			} catch (Exception e) {
				getLogger().log(Level.WARNING, 
						"Failed to get " + Email.class.getName() + 
						"for user: " + user);
			}
		}
		
		/* Variable instances by process instance id: */
		Long processInstanceId = null;
		if (ectx != null) {
			processInstanceId = ectx.getProcessInstance().getId();
		}
		
		List<Serializable> emails = getVariableInstanceQuerier().findJBPMVariableValues(
				ReykjavikGeneralData.VARIABLE_NAME_USER_EMAIL,
				ReykjavikGeneralData.VARIABLE_NAME_USER_PERSONAL_ID, 
				personalID, 
				processInstanceId);
		if (!ListUtil.isEmpty(emails)) {
			return (String) emails.iterator().next();
		}

		return null;
	}
	
	/**
	 * 
	 * @param id is {@link User#getPersonalID()} or 
	 * {@link User#getPrimaryKey()}, not <code>null</code>;
	 * @return {@link User} when found, <code>null</code> otherwise
	 * @author <a href="mailto:martynas@idega.is">Martynas Stakė</a>
	 */
	protected User getUser(String id) {
		try {
			return getUserBusiness(CoreUtil.getIWContext()).getUser(id);
		} catch (Exception e) {
			getLogger().log(Level.WARNING, "No " + User.class.getName() + 
					" by personal id: " + id);
		}
		
		try {
			return getUserBusiness(CoreUtil.getIWContext())
					.getUser(Integer.valueOf(id));
		} catch (Exception e) {
			getLogger().log(Level.WARNING, "No " + User.class.getName() + 
					" by id: " + id);
		}
		
		return null;
	}
}
