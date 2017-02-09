/**
 * 
 */
package com.netPoint.applications.site.model;

/**
 * @author Faliherizo
 *
 */
public class SearchCriterieUser {
	private String mail;
	private Integer idprofil;
	private Integer idetat;
	public SearchCriterieUser() {
		// TODO Auto-generated constructor stub
	}
	public SearchCriterieUser(Integer idprofilOrEtat){
		this.idprofil= idprofilOrEtat;
		this.idetat=idetat;
	}
	public SearchCriterieUser(Integer idprofil, Integer idetat, String pMail){
		this.mail= pMail;
		this.idprofil= idprofil;
		this.idetat=idetat;
	}
	public SearchCriterieUser(Integer idprofil, Integer idetat){
		this.idprofil= idprofil;
		this.idetat=idetat;
	}
	public SearchCriterieUser(Integer idprofilOrEtat, String pMail){
		this.mail= pMail;
		this.idprofil= idprofil;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public Integer getIdprofil() {
		return idprofil;
	}
	public void setIdprofil(Integer idprofil) {
		this.idprofil = idprofil;
	}
	public Integer getIdetat() {
		return idetat;
	}
	public void setIdetat(Integer idetat) {
		this.idetat = idetat;
	}
}
