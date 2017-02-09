/**
 * 
 */
package com.netPoint.applications.site.model.forms;

import java.util.Date;

/**
 * @author Faliherizo
 *
 */
public class RechercheCommande {
	private Integer numeroCommande;
	private Integer num_commande_rev;
	private Integer type_vente; 
	private String email;
	private String societe;
	private Integer num_client;
	private Integer etat;
	private Integer id_fournisseur;
	private String dateInf; 
	private String dateSupp;
	/**
	 * @return the numeroCommande
	 */
	public Integer getNumeroCommande() {
		return numeroCommande;
	}
	/**
	 * @param numeroCommande the numeroCommande to set
	 */
	public void setNumeroCommande(Integer numeroCommande) {
		this.numeroCommande = numeroCommande;
	}
	/**
	 * @return the num_commande_rev
	 */
	public Integer getNum_commande_rev() {
		return num_commande_rev;
	}
	/**
	 * @param num_commande_rev the num_commande_rev to set
	 */
	public void setNum_commande_rev(Integer num_commande_rev) {
		this.num_commande_rev = num_commande_rev;
	}
	/**
	 * @return the type_vente
	 */
	public Integer getType_vente() {
		return type_vente;
	}
	/**
	 * @param type_vente the type_vente to set
	 */
	public void setType_vente(Integer type_vente) {
		this.type_vente = type_vente;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the societe
	 */
	public String getSociete() {
		return societe;
	}
	/**
	 * @param societe the societe to set
	 */
	public void setSociete(String societe) {
		this.societe = societe;
	}
	/**
	 * @return the num_client
	 */
	public Integer getNum_client() {
		return num_client;
	}
	/**
	 * @param num_client the num_client to set
	 */
	public void setNum_client(Integer num_client) {
		this.num_client = num_client;
	}
	/**
	 * @return the etat
	 */
	public Integer getEtat() {
		return etat;
	}
	/**
	 * @param etat the etat to set
	 */
	public void setEtat(Integer etat) {
		this.etat = etat;
	}
	/**
	 * @return the id_fournisseur
	 */
	public Integer getId_fournisseur() {
		return id_fournisseur;
	}
	/**
	 * @param id_fournisseur the id_fournisseur to set
	 */
	public void setId_fournisseur(Integer id_fournisseur) {
		this.id_fournisseur = id_fournisseur;
	}
	/**
	 * @return the dateInf
	 */
	public String getDateInf() {
		return dateInf;
	}
	/**
	 * @param dateInf the dateInf to set
	 */
	public void setDateInf(String dateInf) {
		this.dateInf = dateInf;
	}
	/**
	 * @return the dateSupp
	 */
	public String getDateSupp() {
		return dateSupp;
	}
	/**
	 * @param dateSupp the dateSupp to set
	 */
	public void setDateSupp(String dateSupp) {
		this.dateSupp = dateSupp;
	}

}
