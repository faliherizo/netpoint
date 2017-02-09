/**
 * 
 */
package com.netPoint.applications.site.model;

/**
 * @author Faliherizo
 *
 */
public class SearchCriteriaRevendeur {

	private Integer idSociete;
	private Integer idetat;
	private String mail;
	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}
	public Integer getIdSociete() {
		return idSociete;
	}
	public void setIdSociete(Integer idSociete) {
		this.idSociete = idSociete;
	}
	public Integer getIdetat() {
		return idetat;
	}
	public void setIdetat(Integer idetat) {
		this.idetat = idetat;
	}
	
}
