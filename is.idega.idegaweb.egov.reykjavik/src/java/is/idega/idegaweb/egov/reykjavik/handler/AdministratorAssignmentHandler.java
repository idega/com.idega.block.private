/**
 * @(#)AdministratorAssignmentHandler.java    1.0.0 2:06:30 PM
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
package is.idega.idegaweb.egov.reykjavik.handler;

import is.idega.idegaweb.egov.bpm.business.CasesSubcriberManager;
import is.idega.idegaweb.egov.reykjavik.ReykjavikConstants;
import is.idega.idegaweb.egov.reykjavik.data.ReykjavikGeneralData;
import is.idega.idegaweb.egov.reykjavik.data.dao.SocialServiceCenterDAO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import javax.ejb.FinderException;

import org.jbpm.graph.exe.ExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.idega.block.process.data.Case;
import com.idega.block.process.data.CaseHome;
import com.idega.core.accesscontrol.business.AccessController;
import com.idega.data.IDOAddRelationshipException;
import com.idega.data.IDOLookup;
import com.idega.data.IDOLookupException;
import com.idega.jbpm.bean.VariableInstanceInfo;
import com.idega.jbpm.bean.VariableQuerierData;
import com.idega.jbpm.data.VariableInstanceQuerier;
import com.idega.user.data.User;
import com.idega.util.ListUtil;
import com.idega.util.StringUtil;
import com.idega.util.datastructures.map.MapUtil;
import com.idega.util.expression.ELUtil;

/**
 * <p>Handler for adding subscriptions for handler by their work place</p>
 * <p>You can report about problems to: 
 * <a href="mailto:martynas@idega.is">Martynas Stakė</a></p>
 *
 * @version 1.0.0 Jul 25, 2013
 * @author <a href="mailto:martynas@idega.is">Martynas Stakė</a>
 */
@Service(AdministratorAssignmentHandler.BEAN_NAME)
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class AdministratorAssignmentHandler extends
		ReykjavikProcessRulingHandler implements CasesSubcriberManager {

	private static final long serialVersionUID = -3902040154005693385L;

	public static final String BEAN_NAME = "administratorAssignmentHandler";
	
	@Autowired
	private SocialServiceCenterDAO socialServiceCenterEntityDAO;
	
	@Autowired
	private VariableInstanceQuerier variableInstanceQuerier;
	
	private CaseHome caseHome = null;
	
	protected CaseHome getCaseHome() {
		if (caseHome == null) {
			try {
				caseHome = (CaseHome) IDOLookup.getHome(Case.class);
			} catch (IDOLookupException e) {
				getLogger().log(Level.WARNING, "Unable to get " + CaseHome.class, e);
			}
		}
		
		return caseHome;
	}

	protected SocialServiceCenterDAO getSocialServiceCenterEntityDAO() {
		if (socialServiceCenterEntityDAO == null) {
			ELUtil.getInstance().autowire(this);
		}
		
		return socialServiceCenterEntityDAO;
	}
	
	protected VariableInstanceQuerier getVariableInstanceQuerier() {
		if (variableInstanceQuerier == null) {
			ELUtil.getInstance().autowire(this);
		}
		
		return variableInstanceQuerier;
	}
	
	@Override
	public void execute(ExecutionContext ectx) throws Exception {
		Long parentProcessInstanceId = (Long) ectx.getVariable(
				ReykjavikConstants.VARIABLE_NAME_PARENT_PROCESS_INSTANCE_ID);
		if (parentProcessInstanceId == null) {
			getLogger().warning("No process instance id given!");
			return;
		}
				
		Case theCase = getCase(parentProcessInstanceId);
		if (theCase == null) {
			getLogger().warning("Failed get " + Case.class + 
					" for process instance id: " + parentProcessInstanceId);
			return;
		}
		
		ectx.getJbpmContext().getSession().flush();
		getLogger().info("Variables are flushed!");
		
		String applicantPostalCode = (String) ectx.getVariable(
				ReykjavikGeneralData.VARIABLE_NAME_USER_POSTAL_CODE);
		if (StringUtil.isEmpty(applicantPostalCode)) {
			Collection<VariableInstanceInfo> collection = getVariableInstanceQuerier().getVariableByProcessInstanceIdAndVariableName(parentProcessInstanceId, "string_userPostalCode");
			if (ListUtil.isEmpty(collection)) {
				getLogger().warning("Nothing found by process instance id: " + parentProcessInstanceId);
				return;
			}
			
			applicantPostalCode = (String) collection.iterator().next().getValue();
		}
		
		if (StringUtil.isEmpty(applicantPostalCode)) {
			getLogger().warning("No postal code found!");
			return;
		}

		List<User> bpmHandlers = getSocialServiceCenterEntityDAO()
				.getUsers(applicantPostalCode);
		if (ListUtil.isEmpty(bpmHandlers)) {
			getLogger().warning("No handlers found for postal code: " + applicantPostalCode);
			return;
		}

		for (User bpmHandler: bpmHandlers) {
			theCase.addSubscriber(bpmHandler);
		}

		theCase.store();
		super.execute(ectx);
	}

	@Override
	public boolean doEnsureUserIsSubscribed(User user) {
		if (user == null || user.getPrimaryKey() == null) {
			return false;
		}		

		AccessController accessController = getApplication().getAccessController();
		if (accessController == null) {
			getLogger().warning("Failed to get " + AccessController.class);
			return false;
		}
		
		// Checking if social center handler
		boolean hasRole = false;
		for (String roleName: ReykjavikConstants.ROLE_NAMES) {
			if (accessController.hasRole(user, roleName)) {
				hasRole = true;
			}
		}
		
		if (!hasRole) {
			getLogger().info(User.class + "by name: " + user.getName() + 
					" does not have role for access!");
			return false;
		}
		
		// We going query for this...
		Map<String, VariableQuerierData> variablesWithValues = new HashMap<String, VariableQuerierData>();
		variablesWithValues.put(
				ReykjavikGeneralData.VARIABLE_NAME_USER_POSTAL_CODE, 
				new VariableQuerierData(
						ReykjavikGeneralData.VARIABLE_NAME_USER_POSTAL_CODE, 
						getSocialServiceCenterEntityDAO().getValuesOfPostalCodes(user)));
		
		// Searching for process instance id by postal codes
		Map<Long, Map<String, VariableInstanceInfo>> instances = getVariableInstanceQuerier()
				.getVariablesByNamesAndValuesAndExpressionsByProcesses(
						variablesWithValues, 
						Arrays.asList(ReykjavikConstants.VARIABLE_NAME_PARENT_PROCESS_INSTANCE_ID), 
						Arrays.asList(ReykjavikConstants.PROCESS_DEFINITION_NAMES), 
						null, 
						null);
		if (MapUtil.isEmpty(instances)) {
			getLogger().info("No process instances found for user: " + user.getName());
			return false;
		}
		
		// Fetching process instances id's of parent process
		List<Long> processInstanceIds = new ArrayList<Long>();
		for (Long processInstanceId : instances.keySet()) {
			Map<String, VariableInstanceInfo> variablesMap = instances.get(processInstanceId);
			if (MapUtil.isEmpty(variablesMap)) {
				continue;
			}
			
			VariableInstanceInfo info = variablesMap.get(ReykjavikConstants.VARIABLE_NAME_PARENT_PROCESS_INSTANCE_ID);
			if (info == null) {
				continue;
			}
			
			processInstanceIds.add(Long.valueOf(info.getValue().toString()));
		}

		// Creating cases id's
		Collection<Long> ids = getCasesDAO().getCaseIdsByProcessInstanceIds(processInstanceIds);
		if (ListUtil.isEmpty(ids)) {
			getLogger().info("No cases found for user by name: " + user.getName());
			return false;
		}
		
		// Creep conversion form long to integer
		Collection<Integer> casesIds = new ArrayList<Integer>(ids.size());
		for (Long caseId : ids) {
			casesIds.add(caseId.intValue());
		}
		
		// Searching for cases;
		Collection<Case> cases = null;
		try {
			cases = getCaseHome().getCasesByIds(casesIds);
		} catch (FinderException e1) {
			getLogger().log(Level.WARNING, 
					"Failed to get " + Case.class + " cause of: ", e1);
		}

		if (ListUtil.isEmpty(cases)) {
			return false;
		}
		
		// Re-subscribing cases
		for (Case theCase : cases) {
			try {
				theCase.addSubscriber(user);
				theCase.store();
			} catch (IDOAddRelationshipException e) {
				return false;
			}
		}

		return true;
	}
}
