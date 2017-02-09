/**
 * 
 */
package com.netPoint.applications.site.dao;

import java.util.Date;
import java.util.List;

import com.netPoint.applications.site.model.Client;
import com.netPoint.applications.site.model.ClientRevendeur;
import com.netPoint.applications.site.model.Commande;
import com.netPoint.applications.site.model.CommandeRevendeur;
import com.netPoint.applications.site.model.DureeCommande;
import com.netPoint.applications.site.model.EtatCommande;
import com.netPoint.applications.site.model.Prix;
import com.netPoint.applications.site.model.Produit;
import com.netPoint.applications.site.model.ProduitRevendeur;
import com.netPoint.applications.site.model.Profil;
import com.netPoint.applications.site.model.Revendeur;
import com.netPoint.applications.site.model.TypeCommande;
import com.netPoint.applications.site.model.User;

/**
 * @author Faliherizo
 *
 */
public interface IDaoCommande {
	/**
	 * Liste de Commande
	 * @param pDate
	 * @param pRevenduer
	 * @param pProduit
	 * @return Liste de Commande
	 */
	List<Commande> ListCommandeParMois(int pMois, int pAnnee, Revendeur pRevenduer,
			Produit pProduit);
	/**
	 * get list type of the commande
	 * @return list
	 */
	List<TypeCommande> getListTypeCmd();
	/**
	 * 
	 * @param societe
	 * @param etat
	 * @param email
	 * @param siret
	 * @param rcs
	 * @param num_client
	 * @param id_revendeur
	 * @param num_client_revendeur
	 * @param customer_key
	 * @param nom
	 * @param prenom
	 * @param phone
	 * @return
	 */
	List<ClientRevendeur> getListClient(Integer societe, Integer etat, String email, String siret, String rcs, Integer num_client, 
			Integer id_revendeur, String num_client_revendeur, String customer_key, String nom, String prenom, Integer phone);
	/**
	 * 
	 * @param num_commande
	 * @param num_commande_rev
	 * @param type_vente
	 * @param email
	 * @param societe
	 * @param num_client
	 * @param etat
	 * @param id_fournisseur
	 * @param dateInf
	 * @param dateSupp
	 * @return
	 */
	List<Commande> getListCommande(Integer num_commande, Integer num_commande_rev, 
			String email, String societe, Integer num_client, Integer etat, 
			Integer id_fournisseur, String dateInf, String dateSupp);
	/**
	 * 
	 * @param societe
	 * @param email
	 * @param num_client
	 * @param num_client_revendeur
	 * @param nomPrenom
	 * @param phone
	 * @return
	 */
	List<Commande> getListClientEssai(Integer societe, String email, Integer num_client, 
			String num_client_revendeur, String nomPrenom, Integer phone);
	/*
	 * Save or update command
	 */
	Commande SaveOrUpdate(Commande commande);
	/*
	 * Get list duree commande
	 */
	List<DureeCommande> getListDureeCommande();
	/*
	 * get list of type of commande
	 */
	List<TypeCommande> getListTypeCommande();
	Commande getCommandeById(Integer id);
	/**
	 * get list command expired in @param nbrejourexpiration days
	 * @param nbrejourexpiration
	 * @param revendeur
	 * @return
	 */
	List<Commande> getListCommandeExpire(int nbrejourexpiration,
			Revendeur revendeur);
	Revendeur SearchRevendeur(User user);
	Profil getProfilById(Integer idprofil);
	EtatCommande getEtatCmdById(Integer idetatcmd);
	ClientRevendeur SaveCientRev(ClientRevendeur clientRevendeur);
	Client findClientByMail(String mail);
	Client SaveClent(Client client);
	Revendeur getRevendeurBy(String coderev);
	ClientRevendeur findClientRev(Client client);
	Client findClientById(Integer id);
	CommandeRevendeur SaveOrUpdateCmdRev(CommandeRevendeur commandeRevendeur);
	CommandeRevendeur findCommandeRev(Commande commande);
	int selectmaxnumber();
	Prix SaveOrUpdatePrix(Prix prix);
	Produit findProductBy(Integer id);
	ProduitRevendeur GetProduitRevendeur(Produit produit, Revendeur revendeur);
	DureeCommande getDureeCommande(Integer id);
	int getNumClientRevendeur(Revendeur revendeur);
	String getEndNumber(Revendeur revendeur);
}
