/**
 * 
 */
package com.netPoint.applications.site.model.forms;

/**
 * @author Faliherizo
 *
 */
public class RechercheClient {
	private Integer societe;
	private Integer etat;
	
	private String email;
	private String siret;
	private String rcs; 
	private Integer num_client;
	private	Integer id_revendeur;
	private String num_client_revendeur;
	private String customer_key;
	private String nom; 
	private String prenom; 
	private Integer phone;
	/**
	 * @return the etat
	 */
	public Integer getEtat() {
		return etat;
	}
	/**
	 * @param societe the societe to set
	 */
	public void setSociete(Integer societe) {
		this.societe = societe;
	}
	/**
	 * @return the societe
	 */
	public Integer getSociete() {
		return societe;
	}
	/**
	 * @param etat the etat to set
	 */
	public void setEtat(Integer etat) {
		this.etat = etat;
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
	 * @return the siret
	 */
	public String getSiret() {
		return siret;
	}
	/**
	 * @param siret the siret to set
	 */
	public void setSiret(String siret) {
		this.siret = siret;
	}
	/**
	 * @return the rcs
	 */
	public String getRcs() {
		return rcs;
	}
	/**
	 * @param rcs the rcs to set
	 */
	public void setRcs(String rcs) {
		this.rcs = rcs;
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
	 * @return the id_revendeur
	 */
	public Integer getId_revendeur() {
		return id_revendeur;
	}
	/**
	 * @param id_revendeur the id_revendeur to set
	 */
	public void setId_revendeur(Integer id_revendeur) {
		this.id_revendeur = id_revendeur;
	}
	/**
	 * @return the num_client_revendeur
	 */
	public String getNum_client_revendeur() {
		return num_client_revendeur;
	}
	/**
	 * @param num_client_revendeur the num_client_revendeur to set
	 */
	public void setNum_client_revendeur(String num_client_revendeur) {
		this.num_client_revendeur = num_client_revendeur;
	}
	/**
	 * @return the customer_key
	 */
	public String getCustomer_key() {
		return customer_key;
	}
	/**
	 * @param customer_key the customer_key to set
	 */
	public void setCustomer_key(String customer_key) {
		this.customer_key = customer_key;
	}
	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}
	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	/**
	 * @return the phone
	 */
	public Integer getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(Integer phone) {
		this.phone = phone;
	}

}
