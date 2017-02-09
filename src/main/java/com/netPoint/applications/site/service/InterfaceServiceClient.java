/**
 * 
 */
package com.netPoint.applications.site.service;

import java.util.List;

import com.netPoint.applications.site.model.Client;
import com.netPoint.applications.site.model.ClientRevendeur;
import com.netPoint.applications.site.model.ClientRevendeurId;
import com.netPoint.applications.site.model.Commande;
import com.netPoint.applications.site.model.Revendeur;

/**
 * @author Faliherizo
 *
 */
public interface InterfaceServiceClient {
	/**
	 * find all client
	 * @return list client
	 */
	List<Client> findAllClient();
	
	/**
	 * find all client by societe , by etat, by mail, by rcs, by siret, 
	 * by numero, by revendeur,  by numero client revendeur by customer key , by nom, by prenom, by telephone
	 * @param IdSociete
	 * @param IdEtat
	 * @param mail
	 * @param siret
	 * @param rcs
	 * @param numero
	 * @param IdRevendeur
	 * @param NumClientRev
	 * @param customerkey
	 * @param nom
	 * @param prenoms
	 * @param telephone
	 * @return List client
	 */
	List<Client> findAllClientBy(Integer IdSociete, Integer IdEtat, 
			String mail, String siret, String rcs, String numero, Integer IdRevendeur,
			String NumClientRev, String customerkey, String nom, String prenoms, String telephone);
	/**
	 * Save a client of the revendeur
	 * @param clientRevendeur
	 * @param revendeur
	 * @return
	 */
	ClientRevendeur saveOrUpdateClientRev(ClientRevendeur clientRevendeur, Revendeur revendeur);
	/**
	 * 
	 * @param id
	 * @return
	 */
	Commande getClientRevById(int id);
	
	

}
