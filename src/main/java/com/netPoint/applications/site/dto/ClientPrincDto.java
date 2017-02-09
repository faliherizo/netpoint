/**
 * 
 */
package com.netPoint.applications.site.dto;

/**
 * @author Faliherizo
 *
 */
public class ClientPrincDto {
	

	private String nomSociete;
	private String numClient;
	private String numRdv;
	private String etat;
	private String contact;
	private String codeRevedeur;
	private String nomRevendeurAssocie;
	private String commander;
	private String creeEnEssaie;
	/**
	 * @return the nomRevendeurAssocie
	 */
	public String getNomRevendeurAssocie() {
		return nomRevendeurAssocie;
	}
	/**
	 * @param nomRevendeurAssocie the nomRevendeurAssocie to set
	 */
	public void setNomRevendeurAssocie(String nomRevendeurAssocie) {
		this.nomRevendeurAssocie = nomRevendeurAssocie;
	}
	/**
	 * @return the commander
	 */
	public String getCommander() {
		return commander;
	}
	/**
	 * @param commander the commander to set
	 */
	public void setCommander(String commander) {
		this.commander = commander;
	}
	/**
	 * @return the creeEnEssaie
	 */
	public String getCreeEnEssaie() {
		return creeEnEssaie;
	}
	/**
	 * @param creeEnEssaie the creeEnEssaie to set
	 */
	public void setCreeEnEssaie(String creeEnEssaie) {
		this.creeEnEssaie = creeEnEssaie;
	}
	public String getNomSociete() {
		return nomSociete;
	}
	public void setNomSociete(String nomSociete) {
		this.nomSociete = nomSociete;
	}
	public String getNumClient() {
		return numClient;
	}
	public void setNumClient(String numClient) {
		this.numClient = numClient;
	}
	public String getNumRdv() {
		return numRdv;
	}
	public void setNumRdv(String numRdv) {
		this.numRdv = numRdv;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getCodeRevedeur() {
		return codeRevedeur;
	}
	public void setCodeRevedeur(String codeRevedeur) {
		this.codeRevedeur = codeRevedeur;
	}
	
	public ClientPrincDto(String nomSociete, String numClient, String numRdv,
			String etat, String contact, String codeRevedeur, String nomRevendeurAssocie, String commander,String creeEnEssaie) {
		super();
		this.nomSociete = nomSociete;
		this.numClient = numClient;
		this.numRdv = numRdv;
		this.etat = etat;
		this.contact = contact;
		this.codeRevedeur = codeRevedeur;
		this.nomRevendeurAssocie = nomRevendeurAssocie;
		this.commander = commander;
		this.creeEnEssaie = creeEnEssaie;
	}
	public ClientPrincDto(){}


}
