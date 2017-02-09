/**
 * 
 */
package com.netPoint.applications.site.validator;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.netPoint.applications.site.model.CompteBancaire;

/**
 * @author Faliherizo
 *
 */
public class CompteBancaireValidator implements Validator {

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object arg0, Errors errors) {
		CompteBancaire comptebanque = (CompteBancaire) arg0;
		if (!StringUtils.hasLength(comptebanque.getCodeBanque())) {
            errors.rejectValue("codebanque", "comptebanque", "required");
        }
		if (comptebanque.getCleRIB().length() >2 && comptebanque.getCleRIB().length() <97 ) {
            errors.rejectValue("clerib", "clerib", "Cles rib entre 01 à 97");
        }

	}

}
