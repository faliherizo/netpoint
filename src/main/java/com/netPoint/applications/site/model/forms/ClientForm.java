/**
 * 
 */
package com.netPoint.applications.site.model.forms;

/**
 * @author Faliherizo
 *
 */
public class ClientForm {
	private Integer idSociete; 
	//private Integer idUser;
	private Integer idEtat;
	
	private Integer revendeur;
	private String mail;
	private String siret;
	private String rcs;
	private String numeroclient;
	private String numeroClientRevendeur;
	private String nom;
	private String prenoms;
	private String ConstomerKey;
	private String telephone;
	public ClientForm(){}
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
	 * @return the idSociete
	 */
	public Integer getIdSociete() {
		return idSociete;
	}
	/**
	 * @param idSociete the idSociete to set
	 */
	public void setIdSociete(Integer idSociete) {
		this.idSociete = idSociete;
	}
	/**
	 * @return the idUser
	 */
	/*public Integer getIdUser() {
		return idUser;
	}
	/**
	 * @param idUser the idUser to set
	 */
	/*public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}*/
	/**
	 * @return the idEtat
	 */
	public Integer getIdEtat() {
		return idEtat;
	}
	/**
	 * @param idEtat the idEtat to set
	 */
	public void setIdEtat(Integer idEtat) {
		this.idEtat = idEtat;
	}
	/**
	 * @return the revendeur
	 */
	public Integer getRevendeur() {
		return revendeur;
	}
	/**
	 * @param revendeur the revendeur to set
	 */
	public void setRevendeur(Integer revendeur) {
		this.revendeur = revendeur;
	}
	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}
	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	/**
	 * @return the numeroClientRevendeur
	 */
	public String getNumeroClientRevendeur() {
		return numeroClientRevendeur;
	}
	/**
	 * @param numeroClientRevendeur the numeroClientRevendeur to set
	 */
	public void setNumeroClientRevendeur(String numeroClientRevendeur) {
		this.numeroClientRevendeur = numeroClientRevendeur;
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
	 * @return the prenoms
	 */
	public String getPrenoms() {
		return prenoms;
	}
	/**
	 * @param prenoms the prenoms to set
	 */
	public void setPrenoms(String prenoms) {
		this.prenoms = prenoms;
	}
	/**
	 * @return the constomerKey
	 */
	public String getConstomerKey() {
		return ConstomerKey;
	}
	/**
	 * @param constomerKey the constomerKey to set
	 */
	public void setConstomerKey(String constomerKey) {
		ConstomerKey = constomerKey;
	}
	/**
	 * @return the numeroclient
	 */
	public String getNumeroclient() {
		return numeroclient;
	}
	/**
	 * @param numeroclient the numeroclient to set
	 */
	public void setNumeroclient(String numeroclient) {
		this.numeroclient = numeroclient;
	}
	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}
	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}
