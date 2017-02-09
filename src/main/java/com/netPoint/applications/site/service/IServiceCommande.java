/**
 * 
 */
package com.netPoint.applications.site.service;

import java.util.Date;
import java.util.List;

import com.netPoint.applications.site.model.Client;
import com.netPoint.applications.site.model.ClientRevendeur;
import com.netPoint.applications.site.model.Commande;
import com.netPoint.applications.site.model.CommandeRevendeur;
import com.netPoint.applications.site.model.DureeCommande;
import com.netPoint.applications.site.model.Prix;
import com.netPoint.applications.site.model.Produit;
import com.netPoint.applications.site.model.ProduitRevendeur;
import com.netPoint.applications.site.model.Revendeur;
import com.netPoint.applications.site.model.TypeCommande;

/**
 * @author Faliherizo
 *
 */
public interface IServiceCommande {
	List<Commande> ListCommandeParMois(int pMois, int pAnnee, Revendeur pRevenduer,
			Produit pProduit);
	List<ClientRevendeur> getListClient(Integer societe, Integer etat, String email, String siret, String rcs, Integer num_client, 
			Integer id_revendeur, String num_client_revendeur, String customer_key, String nom, String prenom, Integer phone);
	List<Commande> getListCommande(Integer num_commande, Integer num_commande_rev, 
		 String email, String societe, Integer num_client, Integer etat, 
			Integer id_fournisseur, String dateInf, String dateSupp);
	List<Commande> getListClientEssai(Integer societe, String email,
			Integer num_client, String num_client_revendeur, String nomPrenom,
			Integer phone);
	/*
	 * Save or update command
	 */
	Commande SaveOrUpdate(Commande commande);
	List<DureeCommande> getListDureeCommande();
	/*
	 * get list of type cmd
	 */
	List<TypeCommande> getListTypeCmd();
	Commande getCommandeById(Integer id);
	List<Commande> getListCommandeExpire(int nbrejourexpiration,
			Revendeur revendeur);
	Client SaveClient(Client client, Revendeur revendeur);
	Client findClientByMail(String login);
	ClientRevendeur findClientRev(Client client);
	Client findClientById(Integer id);
	Client SavaOrUpdateClient(Client client);
	CommandeRevendeur findCommandeRev(Commande commande);
	Prix addOrUpdatePrix(Prix prix);
	Produit findProductBy(Integer id);
	Revendeur getRevendeurBy(String numeroRevendeur);
	ProduitRevendeur GetProduitRevendeur(Produit produit, Revendeur revendeur);
	Commande CalculPrix(ProduitRevendeur prodrev, Commande command);
	float CalculPrixSansRemise(Commande command);
}
