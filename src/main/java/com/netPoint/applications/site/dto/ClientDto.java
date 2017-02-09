/**
 * 
 */
package com.netPoint.applications.site.dto;

/**
 * @author Rolf
 *
 */
public class ClientDto {
	
	private String nomSociete;
	private String numClient;
	private String numRdv;
	private String etat;
	private String contact;
	private String codeRevedeur;
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
	public ClientDto(String nomSociete, String numClient, String numRdv,
			String etat, String contact, String codeRevedeur) {
		super();
		this.nomSociete = nomSociete;
		this.numClient = numClient;
		this.numRdv = numRdv;
		this.etat = etat;
		this.contact = contact;
		this.codeRevedeur = codeRevedeur;
	}
	public ClientDto(){}

}
