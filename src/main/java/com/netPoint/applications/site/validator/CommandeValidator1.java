/**
 * 
 */
package com.netPoint.applications.site.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.netPoint.applications.site.dao.IDaoCommande;
import com.netPoint.applications.site.model.Client;
import com.netPoint.applications.site.model.Revendeur;
import com.netPoint.applications.site.model.User;
import com.netPoint.applications.site.service.InterfaceService;
import com.netPoint.applications.site.util.Parameters;

/**
 * @author Faliherizo
 *
 */
public class CommandeValidator1 implements Validator{
	 @Autowired
	 protected InterfaceService interfaceService;
	/**
	 * @param interfaceService the interfaceService to set
	 */
	public void setInterfaceService(InterfaceService interfaceService) {
		this.interfaceService = interfaceService;
	}

	@Autowired
	private IDaoCommande daoCommande;
	/**
	 * @param daoclient the daoclient to set
	 */
	public void setDaoclient(IDaoCommande daoclient) {
		this.daoCommande = daoclient;
	}
	@Override
	 public void validate(Object objet, Errors errors) {
		Client client = (Client) objet;
		 User currentUser = Parameters.getUserconnect();
		 Revendeur revendeur=daoCommande.SearchRevendeur(currentUser);
		 if(revendeur==null){
			 //Get revendeur by code revendeur
			 revendeur=daoCommande.getRevendeurBy(client.getNumeroRevendeur());
			 if(revendeur==null){
				 errors.rejectValue("numeroRevendeur", "numeroRevendeur", "invalide");
			 }
		 }
		 if(client.getSociete().getCodePostal().length() >5){
	        	errors.rejectValue("societe.codePostal", "client.codePostalInvalide", "code postal moin de 5 lettres");
	        }
	        User existuser=interfaceService.findUserByMail(client.getUser().getLogin());
			if(existuser!=null && client.getUser().getId()==null){
				errors.rejectValue("user.login","adresse_mail.notValid","L'adresse mail existe déjà");
			}
			if(!isValidEmailAddress(client.getUser().getLogin())){
				errors.rejectValue("user.login","adresse_mail.notValid","L'adresse mail n'est pas valide");
			}
			/*if (!StringUtils.hasLength(client.getCompteBancaire().getBanque())) {
	            errors.rejectValue("compteBancaire.banque", "compteBancaire.banque", "required");
	        }*/
		 	if (!StringUtils.hasLength(client.getSociete().getNom())) {
	            errors.rejectValue("societe.nom", "societe.nom", "required");
	        }
		 	if (client.getUser().getEtatCompte()==null) {
	            errors.rejectValue("user.etatCompte", "user.etatCompte", "required");
	        }
		 	/*if (!StringUtils.hasLength(client.getSociete().getSiret())) {
		            errors.rejectValue("societe.siret", "societe.siret", "required");
		    }*/
		 	if (!StringUtils.hasLength(client.getSociete().getAdresse1())) {
	            errors.rejectValue("societe.adresse1", "societe.adresse1", "required");
	        }
	        if (!StringUtils.hasLength(client.getSociete().getCodePostal())) {
	            errors.rejectValue("societe.codePostal", "societe.codePostal", "required");
	        }
	        if (!StringUtils.hasLength(client.getSociete().getVille())) {
	            errors.rejectValue("societe.ville", "societe.ville", "required");
	        }
	        if (client.getSociete().getPays().getId() ==null) {
	            errors.rejectValue("societe.pays.id", "societe.pays.id", "required");
	        }
	        if (client.getUser().getLangue().getId()==null) {
	            errors.rejectValue("user.langue.id", "user.langue.id", "required");
	        }
	        if(!isValidEmailAddress(client.getUser().getLogin())){
				errors.rejectValue("user.login","user.login","L'adresse mail n'est pas valide");
			}
	        if (!StringUtils.hasLength(client.getUser().getPwd())) {
	            errors.rejectValue("user.pwd", "user.pwd", "required");
	        }
	        if (!StringUtils.hasLength(client.getUser().getPwd2())) {
	            errors.rejectValue("user.pwd2", "user.pwd2", "required");
	        }
	        if(!client.getUser().getPwd2().equals(client.getUser().getPwd())){
	        	errors.rejectValue("user.pwd2", "user.pwd2", "password different");
	        }
	        if (client.getUser().getEtatCompte().getId()==null) {
	            errors.rejectValue("user.etatCompte.id", "user.etatCompte.id", "required");
	        }
	        if (!StringUtils.hasLength(client.getNumeroRevendeur())) {
	            errors.rejectValue("numeroRevendeur", "numeroRevendeur", "required");
	        }
	        if (client.getUser().getCivilite().getId()==null) {
	            errors.rejectValue("user.civilite.id", "user.civilite.id", "required");
	        }
	        if (!StringUtils.hasLength(client.getUser().getPrenom())) {
	            errors.rejectValue("user.prenom", "user.prenom", "required");
	        }
	        if (!StringUtils.hasLength(client.getUser().getNom())) {
	            errors.rejectValue("user.nom", "user.nom", "required");
	        }
	        /*if (!StringUtils.hasLength(client.getCodeClient())) {
	            errors.rejectValue("codeclient", "user.", "required");
	        }*/
	        /*if (!StringUtils.hasLength(client.getRcs())) {
	            errors.rejectValue("rcs", "rcs", "required");
	        }*/
	        if (!StringUtils.hasLength(client.getUser().getTelephone())) {
	            errors.rejectValue("user.telephone", "user.telephone", "required");
	        }
	        /*if (client.getCompteBancaire().getModeReglement()==null) {
	            errors.rejectValue("compteBancaire.modeReglement.id", "compteBancaire.modeReglement.id", "required");
	        }
	        if (client.getCompteBancaire().getDevise()==null) {
	            errors.rejectValue("compteBancaire.devise.id", "compteBancaire.devise.id", "required");
	        }
	        
	        if(!StringUtils.hasLength(client.getCompteBancaire().getCodeGuichet())){
	        	errors.rejectValue("compteBancaire.codeGuichet", "compteBancaire.codeGuichet", "required");
	        }
	        
	        if(client.getCompteBancaire().getCleRIB().length() != 2){
	        	errors.rejectValue("compteBancaire.cleRIB", "compteBancaire.cleRIB", "Cle rib doit avoir 2 lettres");
	        }
	        if(!StringUtils.hasLength(client.getCompteBancaire().getNumeroCompte())){
	        	errors.rejectValue("compteBancaire.numeroCompte", "compteBancaire.numeroCompte", "required");
	        }
	        if(!StringUtils.hasLength(client.getCompteBancaire().getCodeBanque())){
	        	errors.rejectValue("compteBancaire.codeBanque", "compteBancaire.codeBanque", "required");
	        }
	        if(client.getCompteBancaire().getCodeBanque().length() !=5){
	        	errors.rejectValue("compteBancaire.codeBanque", "compteBancaire.codeBanque", "code banque doit contenir 5 lettres");
	        }
	       */
	    }
	 public boolean isValidEmailAddress(String emailAddress){  
		   String  expression="^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";  
		   CharSequence inputStr = emailAddress;
		   Pattern pattern = Pattern.compile(expression,Pattern.CASE_INSENSITIVE);  
		   Matcher matcher = pattern.matcher(inputStr);  
		   return matcher.matches();
	} 
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Client.class.isAssignableFrom(clazz);
	}

	

}
