package com.netPoint.applications.site.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.netPoint.applications.site.model.User;
import com.netPoint.applications.site.service.InterfaceService;

/**
 * <code>Validator</code> for <code>User</code> forms.
 * 
 * @author  Ali LABIED
 */
public class UserValidator implements Validator {
    
	 @Autowired
	 protected InterfaceService interfaceService;
	/**
	 * @param interfaceService the interfaceService to set
	 */
	public void setInterfaceService(InterfaceService interfaceService) {
		this.interfaceService = interfaceService;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		
		return User.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors errors) {
		User user = (User) arg0;
		User existuser=interfaceService.findUserByMail(user.getLogin());
		if(existuser!=null && user.getId()==null){
			errors.rejectValue("login","adresse_mail.notValid","L'adresse mail existe déjà");
		}
		if(!isValidEmailAddress(user.getLogin())){
			errors.rejectValue("login","adresse_mail.notValid","L'adresse mail n'est pas valide");
		}
		/*if (!StringUtils.hasLength(user.getLogin())) {
            errors.rejectValue("login", "login", "required");
        }*/
        if (!StringUtils.hasLength(user.getPwd())) {
            errors.rejectValue("pwd", "pwd", "required");
        }
        if (!StringUtils.hasLength(user.getPwd2())) {
            errors.rejectValue("pwd2", "pwd2", "required");
        }
        if(!user.getPwd2().equals(user.getPwd())){
        	errors.rejectValue("pwd2", "pwd2", "password different");
        }
        if (!StringUtils.hasLength(user.getPrenom())) {
            errors.rejectValue("prenom", "prenom", "required");
        }
        if (!StringUtils.hasLength(user.getNom())) {
            errors.rejectValue("nom", "nom", "required");
        }
        /*if (user.getRevendeur().getId() ==null) {
            errors.rejectValue("revendeur.id", "revendeur.id", "required");
        }*/
        if (user.getProfil().getId() ==null) {
            errors.rejectValue("profil.id", "profil.id", "required");
        }
        if (user.getLangue().getId() ==null) {
            errors.rejectValue("langue.id", "langue.id", "required");
        }
        if (user.getCivilite().getId() ==null) {
            errors.rejectValue("civilite.id", "civilite.id", "required");
        }
        if (user.getEtatCompte().getId() ==null) {
            errors.rejectValue("etatCompte.id", "etatCompte.id", "required");
        }
       if(user.getDateFinChar()==null && user.getDateFin()==null){
    	   errors.rejectValue("dateFinChar", "dateFinChar", "date format is invalide");
       }
       if(user.getDateFinChar()!=null){
	       if (!isValidDate(user.getDateFinChar())) {
	            errors.rejectValue("dateFinChar", "dateFinChar", "date format is invalide");
	        }
       }
       /* String portable = user.getPortable();
        if (!StringUtils.hasLength(portable)) {
            errors.rejectValue("portable", "portable", "required");
        }else {
            for (int i = 0; i < portable.length(); ++i) {
                if ((Character.isDigit(portable.charAt(i))) == false) {
                    errors.rejectValue("portable", "nonNumeric", "non-numeric");
                    break;
                }
            }
        }
        
        
        String fax =user.getFax();
        if (!StringUtils.hasLength(fax)) {
            errors.rejectValue("fax", "fax", "required");
        }else {
            for (int i = 0; i < fax.length(); ++i) {
                if ((Character.isDigit(fax.charAt(i))) == false) {
                    errors.rejectValue("fax", "nonNumeric", "non-numeric");
                    break;
                }
            }
        }
        
        String telephone = user.getTelephone();
        if (!StringUtils.hasLength(telephone)) {
            errors.rejectValue("telephone", "telephone", "required");
        } else {
            for (int i = 0; i < telephone.length(); ++i) {
                if ((Character.isDigit(telephone.charAt(i))) == false) {
                    errors.rejectValue("telephone", "nonNumeric", "non-numeric");
                    break;
                }
            }
        }*/
		
	}
	public boolean isValidEmailAddress(String emailAddress){  
		   String  expression="^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";  
		   CharSequence inputStr = emailAddress;
		   Pattern pattern = Pattern.compile(expression,Pattern.CASE_INSENSITIVE);  
		   Matcher matcher = pattern.matcher(inputStr);  
		   return matcher.matches();
	} 
	// date validation using SimpleDateFormat

	// it will take a string and make sure it's in the proper
	// format as defined by you, and it will also make sure that
	// it's a legal date
	public boolean isValidDate(String date)
	{
	    // set date format, this can be changed to whatever format
	    // you want, MM-dd-yyyy, MM.dd.yyyy, dd.MM.yyyy etc.
	    // you can read more about it here:
	    // http://java.sun.com/j2se/1.4.2/docs/api/index.html

	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
  // declare and initialize testDate variable, this is what will hold
	    // our converted string
	
	    Date testDate = null;

	    // we will now try to parse the string into date form

	    String errorMessage;
		try
	    {
	      testDate = sdf.parse(date);
	    }
	    // if the format of the string provided doesn't match the format we
	    // declared in SimpleDateFormat() we will get an exception
	    catch (ParseException e)
	    {
	      errorMessage = "the date you provided is in an invalid date" +
	                              " format.";
	      return false;
	    }

	 
	    // dateformat.parse will accept any date as long as it's in the format	    
	    // you defined, it simply rolls dates over, for example, december 32
	    // becomes jan 1 and december 0 becomes november 30
	    // This statement will make sure that once the string
	    // has been checked for proper formatting that the date is still the
	    // date that was entered, if it's not, we assume that the date is invalid

	    if (!sdf.format(testDate).equals(date))
	    {
	      errorMessage = "The date that you provided is invalid.";
	      return false;
	    }
	     
	    // if we make it to here without getting an error it is assumed that
	    // the date was a valid one and that it's in the proper format
	 
	    return true;
	} // end isValidDate

	public void validateUserUpdate(Object arg0, Errors errors) {
		User user = (User) arg0;
		User existuser=interfaceService.findUserByMail(user.getLogin());
		/*if (!StringUtils.hasLength(user.getLogin())) {
            errors.rejectValue("login", "login", "required");
        }*/
        if (!StringUtils.hasLength(user.getPwd())) {
            errors.rejectValue("pwd", "pwd", "required");
        }
        if (!StringUtils.hasLength(user.getPwd2())) {
            errors.rejectValue("pwd2", "pwd2", "required");
        }
        if(!user.getPwd2().equals(user.getPwd())){
        	errors.rejectValue("pwd2", "pwd2", "password different");
        }
        if (!StringUtils.hasLength(user.getPrenom())) {
            errors.rejectValue("prenom", "prenom", "required");
        }
        if (!StringUtils.hasLength(user.getNom())) {
            errors.rejectValue("nom", "nom", "required");
        }
        /*if (user.getRevendeur().getId() ==null) {
            errors.rejectValue("revendeur.id", "revendeur.id", "required");
        }*/
        if (user.getProfil().getId() ==null) {
            errors.rejectValue("profil.id", "profil.id", "required");
        }
        if (user.getLangue().getId() ==null) {
            errors.rejectValue("langue.id", "langue.id", "required");
        }
        if (user.getCivilite().getId() ==null) {
            errors.rejectValue("civilite.id", "civilite.id", "required");
        }
        if (user.getEtatCompte().getId() ==null) {
            errors.rejectValue("etatCompte.id", "etatCompte.id", "required");
        }
       if(user.getDateFinChar()==null && user.getDateFin()==null){
    	   errors.rejectValue("dateFinChar", "dateFinChar", "date format is invalide");
       }
       if(user.getDateFinChar()!=null){
	       if (!isValidDate(user.getDateFinChar())) {
	            errors.rejectValue("dateFinChar", "dateFinChar", "date format is invalide");
	        }
       }
       /* String portable = user.getPortable();
        if (!StringUtils.hasLength(portable)) {
            errors.rejectValue("portable", "portable", "required");
        }else {
            for (int i = 0; i < portable.length(); ++i) {
                if ((Character.isDigit(portable.charAt(i))) == false) {
                    errors.rejectValue("portable", "nonNumeric", "non-numeric");
                    break;
                }
            }
        }
        
        
        String fax =user.getFax();
        if (!StringUtils.hasLength(fax)) {
            errors.rejectValue("fax", "fax", "required");
        }else {
            for (int i = 0; i < fax.length(); ++i) {
                if ((Character.isDigit(fax.charAt(i))) == false) {
                    errors.rejectValue("fax", "nonNumeric", "non-numeric");
                    break;
                }
            }
        }
        
        String telephone = user.getTelephone();
        if (!StringUtils.hasLength(telephone)) {
            errors.rejectValue("telephone", "telephone", "required");
        } else {
            for (int i = 0; i < telephone.length(); ++i) {
                if ((Character.isDigit(telephone.charAt(i))) == false) {
                    errors.rejectValue("telephone", "nonNumeric", "non-numeric");
                    break;
                }
            }
        }*/
		
	}

}
