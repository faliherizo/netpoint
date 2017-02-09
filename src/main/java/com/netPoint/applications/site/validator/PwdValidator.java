/**
 * 
 */
package com.netPoint.applications.site.validator;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.netPoint.applications.site.model.forms.MailPwdForm;

/**
 * @author Faliherizo
 *
 */
public class PwdValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		MailPwdForm mailPwdForm = (MailPwdForm)target;
		if (!StringUtils.hasLength(mailPwdForm.getPassword())) {
            errors.rejectValue("password", "password.required", "required");
        }
		if (!StringUtils.hasLength(mailPwdForm.getPassword2())) {
            errors.rejectValue("password2", "password2.required", "required");
        }
        if(!mailPwdForm.getPassword2().equals(mailPwdForm.getPassword())){
        	errors.rejectValue("password2", "password.different", "password different");
        }
	}

}
