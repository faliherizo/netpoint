/**
 * 
 */
package com.netPoint.applications.site.validator;


import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.netPoint.applications.site.model.Commande;

/**
 * @author Faliherizo
 *
 */
public class CommandeValidator2 implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Commande.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors){
		
		Commande commande = (Commande) target;
		if(commande.getDateDebut() ==null){
			errors.rejectValue("dateDebut", "dateDebut", "required");
		}
		if(commande.getDureeCommande().getId()==null){
			errors.rejectValue("dureeCommande.id", "dureeCommande.id", "required");
		}
		if(commande.getProduit().getId() ==null){
			errors.rejectValue("produit.id", "produit.id", "required");
		}
		if(commande.getProduit().getFournisseur().getId() ==null){
			errors.rejectValue("produit.fournisseur.id", "produit.fournisseur.id", "required");
		}
		if(commande.getTypeCommande().getId() ==null){
			errors.rejectValue("typeCommande.id", "typeCommande.id", "required");
		}
		if(!StringUtils.hasLength(commande.getNumeroCommande())){
			errors.rejectValue("numeroCommande", "numeroCommande", "required");
		}
		if(commande.getNombreposte()==null){
			errors.rejectValue("nombreposte", "nombreposte", "required");
		}
		if(commande.getDateDebut()==null){
			errors.rejectValue("dateDebut", "dateDebut", "required");
		}
	}

}
