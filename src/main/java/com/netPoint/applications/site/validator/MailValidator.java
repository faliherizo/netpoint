/**
 * 
 */
package com.netPoint.applications.site.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.netPoint.applications.site.model.User;
import com.netPoint.applications.site.model.forms.MailForm;
import com.netPoint.applications.site.service.InterfaceService;
import com.netPoint.applications.site.util.VerificationMail;

/**
 * @author Faliherizo
 *
 */
public class MailValidator implements Validator{
	 @Autowired
	 protected InterfaceService interfaceService;
	/**
	 * @param interfaceService the interfaceService to set
	 */
	public void setInterfaceService(InterfaceService interfaceService) {
		this.interfaceService = interfaceService;
	}
	@Override
	public boolean supports(Class<?> clazz) {
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		MailForm mail = (MailForm) target;
		User _user = interfaceService.findUserByMail(mail.getMail());
		
		if(!VerificationMail.isValidEmailAddress(mail.getMail()) || _user==null || _user.getId()==null){
			errors.rejectValue("mail", "mail.invalide", "mail invalide");
		}
	}

}
