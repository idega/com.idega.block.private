/**
 * @(#)SocialServiceCenterDAO.java    1.0.0 12:28:59 PM
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
package is.idega.idegaweb.egov.reykjavik.data.dao;

import is.idega.idegaweb.egov.reykjavik.data.SocialServiceCenter;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.idega.core.location.data.PostalCode;
import com.idega.core.persistence.GenericDao;
import com.idega.user.data.Group;
import com.idega.user.data.User;

/**
 * <p>Data access object for {@link SocialServiceCenter}</p>
 * <p>You can report about problems to: 
 * <a href="mailto:martynas@idega.is">Martynas Stakė</a></p>
 *
 * @version 1.0.0 Jul 25, 2013
 * @author <a href="mailto:martynas@idega.is">Martynas Stakė</a>
 */
public interface SocialServiceCenterDAO extends GenericDao {

	public static final String BEAN_NAME = "socialServiceCenterDAO";
	
	/**
	 * 
	 * <p>Updates or creates entity in data source;</p>
	 * @param entity to create or update, not <code>null</code>;
	 * @return update or created entity or <code>null</code> on failure;
	 * @author <a href="mailto:martynas@idega.is">Martynas Stakė</a>
	 */
	public SocialServiceCenter update(SocialServiceCenter entity);
	
	/**
	 * 
	 * @param entityId is {@link SocialServiceCenter#getId()}, 
	 * not <code>null</code>;
	 * @return {@link SocialServiceCenter} by given id or <code>null</code>
	 * on failure;
	 * @author <a href="mailto:martynas@idega.is">Martynas Stakė</a>
	 */
	public SocialServiceCenter find(Long entityId);
	
	/**
	 * 
	 * @param group of {@link User}s, working in {@link SocialServiceCenter}, 
	 * not <code>null</code>;
	 * @return {@link SocialServiceCenter}s, where given {@link Group} of
	 * {@link User}s works or {@link Collections#emptyList()} on failure;
	 * @author <a href="mailto:martynas@idega.is">Martynas Stakė</a>
	 */
	public List<SocialServiceCenter> find(Group group);
	
	/**
	 * 
	 * @param user who is in {@link Group} working on required 
	 * {@link SocialServiceCenter}, not <code>null</code>;
	 * @return {@link SocialServiceCenter}s, where given {@link Group} is 
	 * working;
	 * @author <a href="mailto:martynas@idega.is">Martynas Stakė</a>
	 */
	public List<SocialServiceCenter> find(User user);
	
	/**
	 * 
	 * @param postalCode to search by, not <code>null</code>;
	 * @return {@link SocialServiceCenter}s serving required region or 
	 * {@link Collections#emptyList()} on failure;
	 * @author <a href="mailto:martynas@idega.is">Martynas Stakė</a>
	 */
	public List<SocialServiceCenter> find(PostalCode postalCode);
	
	/**
	 * 
	 * @return all {@link SocialServiceCenter}s from data source or 
	 * {@link Collections#emptyList()} on failure;
	 * @author <a href="mailto:martynas@idega.is">Martynas Stakė</a>
	 */
	public List<SocialServiceCenter> find();

	/**
	 * 
	 * <p>Creates/Updates entity in data source.</p>
	 * @param id is {@link SocialServiceCenter#getId()}, 
	 * not <code>null</code>;
	 * @param group which should have employees of given service center, 
	 * not <code>null</code>;
	 * @param postalCode of region, which is serviced by given center,
	 * not <code>null</code>
	 * @return updated or created entity or <code>null</code> on failure;
	 * @author <a href="mailto:martynas@idega.is">Martynas Stakė</a>
	 */
	public SocialServiceCenter update(Long id, Group group, PostalCode postalCode);

	/**
	 * 
	 * @param postal is {@link PostalCode#getPostalCode()}, not 
	 * <code>null</code>;
	 * @return {@link Group}s connected to given 
	 * {@link PostalCode} or {@link Collections#emptyList()} on failure;
	 * @author <a href="mailto:martynas@idega.is">Martynas Stakė</a>
	 */
	public List<Group> getGroups(String postalCode);

	/**
	 * 
	 * @param postal is {@link PostalCode#getPostalCode()}, not 
	 * <code>null</code>;
	 * @return {@link User}s of {@link Group}s connected to given 
	 * {@link PostalCode} or {@link Collections#emptyList()} on failure;
	 * @author <a href="mailto:martynas@idega.is">Martynas Stakė</a>
	 */
	public List<User> getUsers(String postalCode);

	/**
	 * 
	 * @param user to search {@link PostalCode}s by, not <code>null</code>;
	 * @return {@link PostalCode}s of {@link SocialServiceCenter}s, where 
	 * given user belong or {@link Collections#emptyList()} on failure;
	 * @author <a href="mailto:martynas@idega.is">Martynas Stakė</a>
	 */
	public List<PostalCode> getPostalCodes(User user);

	/**
	 * 
	 * @param user to search {@link PostalCode}s by, not <code>null</code>;
	 * @return {@link List} of {@link PostalCode#getPostalCode()} of 
	 * {@link SocialServiceCenter}s, where 
	 * given user belong or {@link Collections#emptyList()} on failure;
	 * @author <a href="mailto:martynas@idega.is">Martynas Stakė</a>
	 */
	public List<Serializable> getValuesOfPostalCodes(User user);

	/**
	 * 
	 * @param postalCode to search by, not empty;
	 * @return {@link List} of {@link SocialServiceCenter}s, connected to this 
	 * or similar postalcodes or {@link Collections#emptyList()};
	 * @author <a href="mailto:martynas@idega.is">Martynas Stakė</a>
	 */
	public List<SocialServiceCenter> find(Collection<PostalCode> postalCode);

	/**
	 * 
	 * @param originalPostalCode to search duplicates for, not <code>null</code>;
	 * @return found similar {@link PostalCode}s or {@link Collections#emptyList()}
	 * on failure;
	 * @author <a href="mailto:martynas@idega.is">Martynas Stakė</a>
	 */
	public Collection<PostalCode> getSimilarPostalCodes(PostalCode originalPostalCode);
}
