/**
 * @(#)SocialServiceCenterDAOImpl.java    1.0.0 12:36:09 PM
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
package is.idega.idegaweb.egov.reykjavik.data.dao.impl;

import is.idega.idegaweb.egov.reykjavik.data.SocialServiceCenter;
import is.idega.idegaweb.egov.reykjavik.data.dao.SocialServiceCenterDAO;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

import javax.ejb.EJBException;
import javax.ejb.FinderException;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.idega.business.IBOLookup;
import com.idega.business.IBOLookupException;
import com.idega.core.location.data.PostalCode;
import com.idega.core.location.data.PostalCodeHome;
import com.idega.core.persistence.Param;
import com.idega.core.persistence.impl.GenericDaoImpl;
import com.idega.data.IDOLookup;
import com.idega.data.IDOLookupException;
import com.idega.presentation.IWContext;
import com.idega.user.business.GroupBusiness;
import com.idega.user.business.UserBusiness;
import com.idega.user.data.Group;
import com.idega.user.data.User;
import com.idega.util.CoreUtil;
import com.idega.util.ListUtil;
import com.idega.util.StringUtil;

/**
 * @see SocialServiceCenterDAO
 * <p>You can report about problems to: 
 * <a href="mailto:martynas@idega.is">Martynas Stakė</a></p>
 *
 * @version 1.0.0 Jul 25, 2013
 * @author <a href="mailto:martynas@idega.is">Martynas Stakė</a>
 */
@Repository(SocialServiceCenterDAO.BEAN_NAME)
@Transactional(readOnly = false)
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class SocialServiceCenterDAOImpl extends GenericDaoImpl implements
		SocialServiceCenterDAO {
	
	private GroupBusiness groupBusiness;
	
	private UserBusiness userBusiness;
	
	private PostalCodeHome postalCodeHome;
	
	private IWContext iwc;
	
	protected UserBusiness getUserBusiness() throws IBOLookupException {
		if (userBusiness == null) {
			userBusiness = IBOLookup.getServiceInstance(
					getIWContext(), 
					UserBusiness.class);
		}
		
		return userBusiness;
	}
	
	protected PostalCodeHome getPostalCodeHome() throws IDOLookupException {
		if (postalCodeHome == null) {
			postalCodeHome = (PostalCodeHome) IDOLookup.getHome(PostalCode.class);
		}
		
		return postalCodeHome;
	}
	
	protected GroupBusiness getGroupBusiness() throws IBOLookupException {
		if (groupBusiness == null) {
			groupBusiness = IBOLookup.getServiceInstance(
					getIWContext(), 
					GroupBusiness.class);
		}
		
		return groupBusiness;
	}
	
	protected IWContext getIWContext() {
		if (iwc == null) {
			iwc = CoreUtil.getIWContext();
		}
		
		return iwc;
	}
	
	@Override
	public SocialServiceCenter update(Long id, Group group, PostalCode postalCode) {
		if (group == null || postalCode == null) {
			return null;
		}
		SocialServiceCenter ssce = null;
		if (id != null) {
			ssce = find(id);
		}
		
		if (ssce == null) {
			ssce = new SocialServiceCenter();
		}
		
		ssce.setGroup(group);
		ssce.setPostalCode(postalCode);
		return update(ssce);
	}
	
	/* (non-Javadoc)
	 * @see is.idega.idegaweb.egov.reykjavik.data.dao.SocialServiceCenterEntityDAO#update(is.idega.idegaweb.egov.reykjavik.data.SocialServiceCenterEntity)
	 */
	@Override
	public SocialServiceCenter update(SocialServiceCenter entity) {
		if (entity == null) {
			return null;
		}
		
		if (find(entity.getId()) == null) {
			persist(entity);
			
			if (entity.getId() == null) {
				getLogger().warning("Failed to create entity: " + SocialServiceCenter.class);
				return null;
			} else {
				getLogger().info("Entity: " + entity + " created!");
			}
		} else {
			entity = merge(entity);
			getLogger().info("Entity: " + entity + " updated");
		}
		
		return entity;
	}

	/* (non-Javadoc)
	 * @see is.idega.idegaweb.egov.reykjavik.data.dao.SocialServiceCenterEntityDAO#find(java.lang.Long)
	 */
	@Override
	public SocialServiceCenter find(Long entityId) {
		if (entityId == null) {
			return null;
		}
		
		return getSingleResult(
				SocialServiceCenter.GET_BY_ID, 
				SocialServiceCenter.class,
				new Param(SocialServiceCenter.idProp, entityId));
	}

	/* (non-Javadoc)
	 * @see is.idega.idegaweb.egov.reykjavik.data.dao.SocialServiceCenterEntityDAO#find(com.idega.user.data.Group)
	 */
	@Override
	public List<SocialServiceCenter> find(Group group) {
		if (group == null || group.getPrimaryKey() == null) {
			return Collections.emptyList();
		}

		return getResultList(
				SocialServiceCenter.GET_BY_GROUP_ID, 
				SocialServiceCenter.class, 
				new Param(SocialServiceCenter.groupIdProp, 
						Long.valueOf(group.getPrimaryKey().toString())));
	}
	
	/* (non-Javadoc)
	 * @see is.idega.idegaweb.egov.reykjavik.data.dao.SocialServiceCenterEntityDAO#find(com.idega.core.location.data.PostalCode)
	 */
	@Override
	public List<SocialServiceCenter> find(PostalCode postalCode) {
		if (postalCode == null || postalCode.getPrimaryKey() == null) {
			return Collections.emptyList();
		}

		return getResultList(
				SocialServiceCenter.GET_BY_POSTAL_CODE_ID, 
				SocialServiceCenter.class, 
				new Param(SocialServiceCenter.postalCodeIdProp, 
						Long.valueOf(postalCode.getPrimaryKey().toString())));
	}
	
	/*
	 * (non-Javadoc)
	 * @see is.idega.idegaweb.egov.reykjavik.data.dao.SocialServiceCenterDAO#find(java.util.Collection)
	 */
	@Override
	public List<SocialServiceCenter> find(Collection<PostalCode> postalCodes) {
		if (ListUtil.isEmpty(postalCodes)) {
			return Collections.emptyList();
		}
		
		List<Long> postalCodeIds = new ArrayList<Long>(postalCodes.size());
		for (PostalCode postalCode : postalCodes) {
			postalCodeIds.add(Long.valueOf(postalCode.getPrimaryKey().toString()));
		}

		return getResultList(
				SocialServiceCenter.GET_BY_POSTAL_CODES, 
				SocialServiceCenter.class, 
				new Param(SocialServiceCenter.postalCodeIdProp, 
						postalCodeIds));
	}

	/* (non-Javadoc)
	 * @see is.idega.idegaweb.egov.reykjavik.data.dao.SocialServiceCenterEntityDAO#findAll()
	 */
	@Override
	public List<SocialServiceCenter> find() {
		return getResultList(
				SocialServiceCenter.GET_ALL, 
				SocialServiceCenter.class);
	}

	protected Collection<PostalCode> getPostalCode(String postalCode) {
		if (StringUtil.isEmpty(postalCode)) {
			return null;
		}
		
		try {
			return getPostalCodeHome().findByPostalCode(Arrays.asList(postalCode));
		} catch (IDOLookupException e) {
			getLogger().log(
					Level.WARNING, 
					"Failed to find idega data object: ", e);
		} catch (FinderException e) {
			getLogger().log(
					Level.WARNING, "Failed to find postal code!", e);
		}
		
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see is.idega.idegaweb.egov.reykjavik.data.dao.SocialServiceCenterEntityDAO#getGroups(com.idega.core.location.data.PostalCode)
	 */
	@Override
	public List<Group> getGroups(String postalCode) {
		List<SocialServiceCenter> centers = find(getPostalCode(postalCode));
		if (ListUtil.isEmpty(centers)) {
			return Collections.emptyList();
		}

		List<Group> groups = new ArrayList<Group>(centers.size());
		for (SocialServiceCenter center : centers) {
			try {
				groups.add(center.getGroup());
			} catch (Exception e) {
				continue;
			}
		}
		
		return groups;
	}
	
	/*
	 * (non-Javadoc)
	 * @see is.idega.idegaweb.egov.reykjavik.data.dao.SocialServiceCenterEntityDAO#getUsers(com.idega.core.location.data.PostalCode)
	 */
	@Override
	public List<User> getUsers(String postalCode) {
		List<Group> groups = getGroups(postalCode);
		if (ListUtil.isEmpty(groups)) {
			return Collections.emptyList();
		}

		List<User> users = new ArrayList<User>();
		for (Group group: groups) {
			Collection<User> groupUsers = null;
			try {
				groupUsers = getGroupBusiness().getUsersRecursive(Integer.valueOf(group.getPrimaryKey().toString()));
			} catch (Exception e) {
				getLogger().warning("No user ifound in group: " + group.getName());
			}
			
			if (ListUtil.isEmpty(groupUsers)) {
				continue;
			}
			
			users.addAll(groupUsers);
		}
		
		return users;
	}

	/*
	 * (non-Javadoc)
	 * @see is.idega.idegaweb.egov.reykjavik.data.dao.SocialServiceCenterDAO#getSimilarPostalCodes(com.idega.core.location.data.PostalCode)
	 */
	@Override
	public Collection<PostalCode> getSimilarPostalCodes(PostalCode originalPostalCode) {
		if (originalPostalCode == null) {
			return Collections.emptyList();
		}
		
		try {
			return getPostalCodeHome().findByPostalCode(Arrays.asList(originalPostalCode.getPostalCode()));
		} catch (IDOLookupException e) {
			getLogger().log(Level.WARNING, 
					"Failed to find IDO entity, cause of:", e);
		} catch (FinderException e) {
			getLogger().log(Level.WARNING, "Failed to find postal codes!");
		}
		
		return Collections.emptyList();
	}
	
	/*
	 * (non-Javadoc)
	 * @see is.idega.idegaweb.egov.reykjavik.data.dao.SocialServiceCenterEntityDAO#getPostalCodes(com.idega.user.data.User)
	 */
	@Override
	public List<PostalCode> getPostalCodes(User user) {
		// Checking which service centers handler belongs to
		List<SocialServiceCenter> serviceCenters = find(user);
		if (ListUtil.isEmpty(serviceCenters)) {
			return Collections.emptyList();
		}
		
		// Creating list of postal codes
		List<PostalCode> postalCodes = new ArrayList<PostalCode>();
		for (SocialServiceCenter serviceCenter : serviceCenters) {
			try {
				postalCodes.addAll(getSimilarPostalCodes(serviceCenter.getPostalCode()));
			} catch (Exception e) {
				getLogger().log(Level.WARNING, 
						"Failed to find " + PostalCode.class + " by id: " + 
						serviceCenter.getPostalCodeId() + 
						" cause of: ", e);
			}
		}
		
		return postalCodes;
	}
	
	/*
	 * (non-Javadoc)
	 * @see is.idega.idegaweb.egov.reykjavik.data.dao.SocialServiceCenterEntityDAO#getValuesOfPostalCodes(com.idega.user.data.User)
	 */
	@Override
	public List<Serializable> getValuesOfPostalCodes(User user) {
		List<PostalCode> postalCodes = getPostalCodes(user);
		if (ListUtil.isEmpty(postalCodes)) {
			return Collections.emptyList();
		}
		
		List<Serializable> values = new ArrayList<Serializable>(postalCodes.size());
		for (PostalCode postalCode: postalCodes) {
			values.add(postalCode.getPostalCode());
		}
		
		return values;
	}
	
	@Override
	public List<SocialServiceCenter> find(User user) {
		if (user == null) {
			return null;
		}
		
		Collection<Group> groups = null;
		try {
			groups = getUserBusiness().getUserGroups(user);
		} catch (IBOLookupException e) {
			getLogger().log(Level.WARNING, "failed to find idega data object: ", e);
		} catch (EJBException e) {
			getLogger().log(Level.WARNING, "", e);
		} catch (RemoteException e) {
			getLogger().log(Level.WARNING, "Failed to connect datasource: ", e);
		}
		
		if (ListUtil.isEmpty(groups)) {
			return null;
		}
		
		List<Long> groupsIds = new ArrayList<Long>(groups.size());
		for (Group group : groups) {
			groupsIds.add(Long.valueOf(group.getPrimaryKey().toString()));
		}
		
		return getResultList(
				SocialServiceCenter.GET_BY_GROUPS, 
				SocialServiceCenter.class,
				new Param(SocialServiceCenter.groupIdProp, groupsIds));
	}
}
