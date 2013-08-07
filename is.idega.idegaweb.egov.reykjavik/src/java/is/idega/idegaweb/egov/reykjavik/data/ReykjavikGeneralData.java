/**
 * @(#)User.java    1.0.0 10:01:33 AM
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
package is.idega.idegaweb.egov.reykjavik.data;

import is.idega.idegaweb.egov.reykjavik.beans.ReykjavikServices;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJBException;

import com.idega.core.contact.data.Email;
import com.idega.core.contact.data.Phone;
import com.idega.core.location.data.Address;
import com.idega.core.location.data.PostalCode;
import com.idega.jbpm.variables.BinaryVariable;
import com.idega.user.bean.UserDataBean;
import com.idega.util.CoreConstants;
import com.idega.util.ListUtil;
import com.idega.util.expression.ELUtil;

/**
 * <p>Data store for info about {@link com.idega.user.data.User}</p>
 * <p>You can report about problems to: 
 * <a href="mailto:martynas@idega.is">Martynas Stakė</a></p>
 *
 * @version 1.0.0 Jul 16, 2013
 * @author <a href="mailto:martynas@idega.is">Martynas Stakė</a>
 */
public class ReykjavikGeneralData extends JBPMVariablesData {
	
	public static final String VARIABLE_NAME_USER_ID = "userId"; 
	
	public static final String VARIABLE_NAME_USER_NAME = "string_userName";
	
	public static final String VARIABLE_NAME_USER_PERSONAL_ID = "string_userPersonalId";
	
	public static final String VARIABLE_NAME_USER_ADDRESS = "string_userAddress";
	
	public static final String VARIABLE_NAME_USER_POSTAL_CODE = "string_userPostalCode";
	
	public static final String VARIABLE_NAME_USER_PHONE = "string_userPhone";
	
	public static final String VARIABLE_NAME_USER_EMAIL = "string_userEmail";
	
	public static final String VARIABLE_NAME_CURRENT_SERVICES = "list_currentServices";
	
	public static final String VARIABLE_NAME_ATTACHMENTS = "files_attachments";
	
	public List<BinaryVariable> attachments;

	public ReykjavikGeneralData() {};
	
	public ReykjavikGeneralData(UserDataBean userDataBean) {
		setUserDataBean(userDataBean);
	}
	
	public ReykjavikGeneralData(com.idega.user.data.User user) throws EJBException, RemoteException {
		super();
		setId(user.getPrimaryKey().toString());
		setName(user.getName());
		setAddress(user.getUsersMainAddress());
		setEmail(user.getUsersEmail());
		setPersonalId(user.getPersonalID());
		setPhone(user.getUsersHomePhone());
	}
	
	public ReykjavikGeneralData(String id, String name, String personalId, String address,
			String postalCode, String phone, String email) {
		super();
		setId(id);
		setName(name);
		setAddress(address);
		setEmail(email);
		setPersonalId(personalId);
		setPhone(phone);
		setPostalCode(postalCode);
	}
	
	
	public String getId() {
		return (String) getValue(VARIABLE_NAME_USER_ID);
	}
	
	public void setId(String id) {
		add(VARIABLE_NAME_USER_ID, id);
	}

	public String getName() {
		return (String) getValue(VARIABLE_NAME_USER_NAME);
	}

	public void setName(String name) {
		add(VARIABLE_NAME_USER_NAME, name);
	}

	public String getPersonalId() {
		return (String) getValue(VARIABLE_NAME_USER_PERSONAL_ID);
	}

	public void setPersonalId(String personalId) {
		add(VARIABLE_NAME_USER_PERSONAL_ID, personalId);
	}

	public String getAddress() {
		return (String) getValue(VARIABLE_NAME_USER_ADDRESS);
	}

	public void setAddress(String address) {
		add(VARIABLE_NAME_USER_ADDRESS, address);
	}
	
	public void setAddress(Address address) {
		if (address != null) {
			setAddress(address.getName());
			setPostalCode(address.getPostalCode());
		}
	}

	public void setPostalCode(PostalCode postalCode) {
		if (postalCode != null) {
			setPostalCode(postalCode.getPostalCode());
		}
	}

	public String getPostalCode() {
		return (String) getValue(VARIABLE_NAME_USER_POSTAL_CODE);
	}

	public void setPostalCode(String postalCode) {
		add(VARIABLE_NAME_USER_POSTAL_CODE, postalCode);
	}

	public String getPhone() {
		return (String) getValue(VARIABLE_NAME_USER_PHONE);
	}

	public void setPhone(String phone) {
		add(VARIABLE_NAME_USER_PHONE, phone);
	}
	
	public void setPhone(Phone usersHomePhone) {
		if (usersHomePhone != null) {
			setPhone(usersHomePhone.getNumber());
		}
	}

	public String getEmail() {
		return (String) getValue(VARIABLE_NAME_USER_EMAIL);
	}

	public void setEmail(String email) {
		add(VARIABLE_NAME_USER_EMAIL, email);
	}
	
	public void setEmail(Email usersEmail) {
		if (usersEmail != null) {
			setEmail(usersEmail.getEmailAddress());
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getCurrentServices() {
		List<String> variable = (List<String>) getValue(VARIABLE_NAME_CURRENT_SERVICES);
		return variable == null ? Collections.EMPTY_LIST : variable;
	}
	public void setCurrentServices(List<String> currentServices) {
		Serializable variable;
		if(currentServices == null){
			variable = new ArrayList<String>(0);
		}
		if(currentServices instanceof Serializable){
			variable = (Serializable) currentServices;
		}else{
			variable = new ArrayList<String>(currentServices);
		}
		add(VARIABLE_NAME_CURRENT_SERVICES, variable);
	}
	public List<Map<String, Object>> getAllServices(){
		@SuppressWarnings("unchecked")
		List<String> currentServices = (List<String>) getValue(VARIABLE_NAME_CURRENT_SERVICES);
		if(ListUtil.isEmpty(currentServices)){
			currentServices =  Collections.emptyList();
		}
		ReykjavikServices reykjavikServices = ELUtil.getInstance().getBean(ReykjavikServices.BEAN_NAME);
		List<String> socialServices = reykjavikServices.getSocialServices();
		List<Map<String, Object>> services = new ArrayList<Map<String,Object>>(socialServices.size());
		for(String serviceKey : socialServices){
			Map<String, Object> service = new HashMap<String, Object>();
			services.add(service);
			service.put("id",serviceKey);
			Boolean current = currentServices.remove(serviceKey);
			service.put("selected", current);
			service.put("name", serviceKey);
		}
		return services;
	}
	
	public void setUserDataBean(UserDataBean userDataBean) {
		if(userDataBean != null){
			setId(String.valueOf(userDataBean.getUserId()));
			setName(userDataBean.getName());

			StringBuilder address = new StringBuilder();
			String countryName = userDataBean.getCountryName();
			if(countryName != null){
				address.append(countryName).append(CoreConstants.SPACE);
			}

			String city = userDataBean.getCity();
			if(city != null){
				address.append(city).append(CoreConstants.SPACE);
			}

			String streetAndNumber = userDataBean.getStreetNameAndNumber();
			if(streetAndNumber != null){
				address.append(streetAndNumber);
			}

			setAddress(address.toString());
			setEmail(userDataBean.getEmail());
			setPersonalId(userDataBean.getPersonalId());
			setPhone(userDataBean.getPhone());
			setPostalCode(userDataBean.getPostalCodeId());
		}
	}
	
	public List<BinaryVariable> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<BinaryVariable> attachments) {
		this.attachments = attachments;
	}
}
