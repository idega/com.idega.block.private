/**
 * @(#)FakeSocialServiceCentersCreator.java    1.0.0 2:27:46 PM
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
package is.idega.idegaweb.egov.reykjavik.test;

import is.idega.idegaweb.egov.reykjavik.ReykjavikConstants;
import is.idega.idegaweb.egov.reykjavik.data.SocialServiceCenter;
import is.idega.idegaweb.egov.reykjavik.data.dao.SocialServiceCenterDAO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;

import javax.ejb.CreateException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.idega.business.IBOLookup;
import com.idega.business.IBOLookupException;
import com.idega.core.accesscontrol.business.AccessController;
import com.idega.core.business.DefaultSpringBean;
import com.idega.core.contact.data.Phone;
import com.idega.core.contact.data.PhoneHome;
import com.idega.core.location.business.AddressBusiness;
import com.idega.core.location.business.CommuneBusiness;
import com.idega.core.location.data.Address;
import com.idega.core.location.data.AddressHome;
import com.idega.core.location.data.Commune;
import com.idega.core.location.data.CommuneHome;
import com.idega.core.location.data.PostalCode;
import com.idega.core.location.data.PostalCodeHome;
import com.idega.data.IDOAddRelationshipException;
import com.idega.data.IDOLookup;
import com.idega.data.IDOLookupException;
import com.idega.idegaweb.IWMainSlideStartedEvent;
import com.idega.presentation.IWContext;
import com.idega.user.business.GroupBusiness;
import com.idega.user.business.UserApplicationEngine;
import com.idega.user.data.Group;
import com.idega.util.CoreUtil;
import com.idega.util.ListUtil;
import com.idega.util.StringUtil;
import com.idega.util.expression.ELUtil;

/**
 * <p>Creates fake {@link SocialServiceCenter}s for testing/working on 
 * problems with it.</p>
 * <p>You can report about problems to: 
 * <a href="mailto:martynas@idega.is">Martynas Stakė</a></p>
 *
 * @version 1.0.0 Jul 25, 2013
 * @author <a href="mailto:martynas@idega.is">Martynas Stakė</a>
 */
@Service
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class FakeSocialServiceCentersCreator extends DefaultSpringBean
		implements ApplicationListener {

	private IWContext iwc;

	private UserApplicationEngine userApplicationEngine;

	@Autowired
	private SocialServiceCenterDAO socialServiceCenterEntityDAO;

	private AddressBusiness addressBusiness;
	
	private CommuneBusiness communeBusiness;

	private GroupBusiness groupBusiness;

	private AddressHome addressHome;
	
	private CommuneHome communeHome;
	
	private PhoneHome phoneHome;
	
	private PostalCodeHome postalCodeHome;
	
	protected IWContext getIWContext() {
		if (iwc == null) {
			iwc = CoreUtil.getIWContext();
		}
		
		return iwc;
	}
	
	protected AddressBusiness getAddressBusiness() throws IBOLookupException {
		if (addressBusiness == null) {
			addressBusiness = IBOLookup.getServiceInstance(
					getIWContext(), 
					AddressBusiness.class);
		}

		return addressBusiness;
	}
	
	protected CommuneBusiness getCommuneBusiness() throws IBOLookupException {
		if (communeBusiness == null) {
			communeBusiness = IBOLookup.getServiceInstance(
					getIWContext(), 
					CommuneBusiness.class);
		}
		
		return communeBusiness;
	}
	
	protected GroupBusiness getGroupBusiness() throws IBOLookupException {
		if (groupBusiness == null) {
			groupBusiness = IBOLookup.getServiceInstance(
					getIWContext(), 
					GroupBusiness.class);
		}
		
		return groupBusiness;
	}
	
	protected AddressHome getAddressHome() throws IDOLookupException {
		if (addressHome == null) {
			addressHome = (AddressHome) IDOLookup.getHome(Address.class);
		}

		return addressHome;
	}
	
	protected CommuneHome getCommuneHome() throws IDOLookupException {
		if (communeHome == null) {
			communeHome = (CommuneHome) IDOLookup.getHome(Commune.class);
		}
		
		return communeHome;
	}
	
	protected PhoneHome getPhoneHome() throws IDOLookupException {
		if (phoneHome == null) {
			phoneHome = (PhoneHome) IDOLookup.getHome(Phone.class);
		}

		return phoneHome;
	}
	
	protected PostalCodeHome getPostalCodeHome() throws IDOLookupException {
		if (postalCodeHome == null) {
			postalCodeHome = (PostalCodeHome) IDOLookup.getHome(PostalCode.class);
		}
		
		return postalCodeHome;
	}
	
	protected UserApplicationEngine getUserApplicationEngine() {
		if (userApplicationEngine == null) {
			userApplicationEngine =  ELUtil.getInstance().getBean(UserApplicationEngine.class);
		}
		
		return userApplicationEngine;
	}
	
	protected SocialServiceCenterDAO getSocialServiceCenterEntityDAO() {
		if (socialServiceCenterEntityDAO == null) {
			ELUtil.getInstance().autowire(this);
		}
		
		return socialServiceCenterEntityDAO;
	}

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
	 */
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof IWMainSlideStartedEvent && isDevelopementState()) {
			getLogger().info("Creating/Updating fake " + SocialServiceCenter.class +
					"\n");

			if (!ListUtil.isEmpty(getSocialServiceCenterEntityDAO().find())) {
				getLogger().info("Table not empty, not creating new instances.");
				return;
			}

			if (createAll()) {
				getLogger().info(SocialServiceCenter.class + " created successfully");
			} else {
				getLogger().info("No " + SocialServiceCenter.class);
			}
		}
	}

	protected boolean createAll() {
		List<Group> createdGroups = null;
		try {
			createdGroups = createGroups();
		} catch (Exception e) {
			getLogger().log(Level.WARNING, "Failed to created test data: ", e);
			return false;
		}
		
		for (int i = 0; i < ReykjavikConstants.POSTAL_CODES.length; i++) {
			for (String code : ReykjavikConstants.POSTAL_CODES[i]) {
				PostalCode postalCode = null;
				try {
					postalCode = getPostalCodeHome().create();
				} catch (Exception e) {
					return false;
				}

				postalCode.setPostalCode(code);
				postalCode.store();
				
				getSocialServiceCenterEntityDAO().update(null, 
						getByName(createdGroups, ReykjavikConstants.GROUPS_DATA[i][0]), 
						postalCode);
			}
		}
		
		return true;
	}
	
	protected Group getByName(List<Group> groups, String name) {
		if (ListUtil.isEmpty(groups) || StringUtil.isEmpty(name)) {
			return null;
		}
		
		for (Group group: groups) {
			if (name.equals(group.getName())) {
				return group;
			}
		}
		
		return null;
	}

	protected List<Group> createGroups() throws RemoteException, CreateException, IDOAddRelationshipException {
		List<Group> groups = new ArrayList<Group>(ReykjavikConstants.GROUPS_DATA.length);
		for (String[] groupData : ReykjavikConstants.GROUPS_DATA) {
			
			Group group = null;
			Collection<Group> groupsInDatasource = getGroupBusiness().getGroupsByGroupName(groupData[0]);
			if (!ListUtil.isEmpty(groupsInDatasource)) {
				group = groupsInDatasource.iterator().next();
			}
			
			if (group == null) {
				group = getGroupBusiness().createGroup(groupData[0]);
				
				Address address = getAddressHome().create();
				address.setCity(ReykjavikConstants.SOCIAL_SERVICE_CENTERS_CITY);
				address.setStreetName(groupData[1]);
				address.setStreetNumber(Integer.valueOf(groupData[2]));
				address.store();
				group.addAddress(address);
				
				Phone phone = getPhoneHome().create();
				phone.setNumber(groupData[3]);
				phone.store();
				group.addPhone(phone);
							
				group.store();
			}
			
			groups.add(group);
		}
		
		AccessController accessController = getApplication().getAccessController();
		if (accessController == null) {
			return groups;
		}
		
		for (Group group: groups) {
			for (String role : ReykjavikConstants.ROLE_NAMES) {
				accessController.addRoleToGroup(role, group, getApplication().getIWApplicationContext());
			}
		}
		
		return groups;
	}

}
