/**
 * 
 */
package com.netPoint.applications.site.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.netPoint.applications.site.model.Revendeur;
import com.netPoint.applications.site.model.User;
import com.netPoint.applications.site.service.InterfaceService;

/**
 * @author Faliherizo
 *
 */
public class RevendeurValidator implements Validator {


	@Override
	public boolean supports(Class<?> arg0) {
		return Revendeur.class.isAssignableFrom(arg0);
	}
	 @Autowired
	 protected InterfaceService interfaceService;
	/**
	 * @param interfaceService the interfaceService to set
	 */
	public void setInterfaceService(InterfaceService interfaceService) {
		this.interfaceService = interfaceService;
	}

	@Override
	public void validate(Object arg0, Errors errors) {
		Revendeur revend =(Revendeur) arg0;
		
		try {
			if(revend.getUser().getDateFinChar()==null && revend.getUser().getDateFin()==null){
		    	   errors.rejectValue("user.dateFinChar", "user.dateFinChar", "date format is invalide");
		       }
		       if(revend.getUser().getDateFinChar()!=null){
			       if (!isValidDate(revend.getUser().getDateFinChar())) {
			            errors.rejectValue("user.dateFinChar", "user.dateFinChar", "date format is invalide");
			        }
		       }
			if (revend.getCompteBancaire().getDevise().getId()==null) {
	            errors.rejectValue("compteBancaire.devise.id", "compteBancaire.devise.id", "required");
	        }
			if (revend.getCompteBancaire().getModeReglement().getId()==null) {
	            errors.rejectValue("compteBancaire.modeReglement.id", "compteBancaire.modeReglement.id", "required");
	        }
			if (revend.getCompteBancaire().getNumeroCompte().isEmpty()) {
	            errors.rejectValue("compteBancaire.numeroCompte", "compteBancaire.numeroCompte", "required");
	        }
			if (revend.getCompteBancaire().getCodeGuichet().isEmpty()) {
	            errors.rejectValue("compteBancaire.codeGuichet", "compteBancaire.codeGuichet", "required");
	        }
			if (revend.getCompteBancaire().getCodeBanque().isEmpty()) {
	            errors.rejectValue("compteBancaire.codeBanque", "compteBancaire.codeBanque", "required");
	        }
			if (revend.getCompteBancaire().getBanque().isEmpty()) {
	            errors.rejectValue("compteBancaire.banque", "compteBancaire.banque", "required");
	        }
			if (revend.getCompteBancaire().getCleRIB().isEmpty()) {
	            errors.rejectValue("compteBancaire.cleRIB", "compteBancaire.cleRIB", "required");
	        }
			if (revend.getSociete().getPays().getId()==null) {
	            errors.rejectValue("societe.pays.id", "societe.pays.id", "required");
	        }
			if (!StringUtils.hasLength(revend.getSociete().getSiret())) {
	            errors.rejectValue("societe.siret", "societe.siret", "required");
	        }
		 
			 if (!StringUtils.hasLength(revend.getSociete().getNom())) {
		            errors.rejectValue("societe.nom", "societe.nom", "required");
		        }
			 
			 if (!StringUtils.hasLength(revend.getSociete().getAdresse1())) {
		            errors.rejectValue("societe.adresse1", "societe.adresse1", "required");
		        }
			 
			 if (!StringUtils.hasLength(revend.getSociete().getCodePostal())) {
		            errors.rejectValue("societe.codePostal", "societe.codePostal", "required");
		        }
			 if (revend.getSociete().getCodePostal().length()>5) {
				 errors.rejectValue("societe.codePostal", "societe.codePostal", "length <5");
			 }
			 if (!StringUtils.hasLength(revend.getSociete().getVille())) {
		            errors.rejectValue("societe.ville", "societe.ville", "required");
		        }
			  if (revend.getUser().getCivilite().getId() ==null) {
		            errors.rejectValue("user.civilite.id", "user.civilite.id", "required");
		        }
			 if (!StringUtils.hasLength(revend.getUser().getNom())) {
		            errors.rejectValue("user.nom", "user.nom", "required");
		        }
			 
			 if (!StringUtils.hasLength(revend.getUser().getPrenom())) {
		            errors.rejectValue("user.prenom", "user.prenom", "required");
		        }
			 if (revend.getUser().getLangue().getId() ==null) {
		            errors.rejectValue("user.langue.id", "user.langue.id", "required");
		        }
			 if (!StringUtils.hasLength(revend.getUser().getPortable())) {
		            errors.rejectValue("user.telephone", "user.telephone", "required");
		        }
			 if (revend.getUser().getLangue().getId() ==null) {
		            errors.rejectValue("user.langue.id", "user.langue.id", "required");
		        }
			 if (revend.getUser().getEtatCompte().getId() ==null) {
		            errors.rejectValue("user.etatCompte.id", "user.etatCompte.id", "required");
		      }
			 if (!StringUtils.hasLength(revend.getPartnerCode())) {
		            errors.rejectValue("partnerCode", "partnerCode", "required");
		        }
			if (revend.getCompteBancaire().getCleRIB().length() >2 && revend.getCompteBancaire().getCleRIB().length() <97 ) {
		            errors.rejectValue("clerib", "clerib", "Cles rib entre dois comprise entre 01 à 97");
		    }
			User existuser=interfaceService.findUserByMail(revend.getUser().getLogin());
			if(existuser!=null && revend.getUser().getId()==null){
				errors.rejectValue("user.login","adresse_mail.notValid","L'adresse mail existe déjà");
			}
			if(!isValidEmailAddress(revend.getUser().getLogin())){
				errors.rejectValue("user.login","adresse_mail.notValid","L'adresse mail n'est pas valide");
			}
			
		} catch (Exception e) {
			
		}
		

		 
		
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
}
