/**
 * 
 */
package com.netPoint.applications.site.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Faliherizo
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "mailconfig")
public class MailConfig implements Serializable, Comparable<MailConfig> {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", unique=true, nullable=false)
	private int Id;
	@Column(name="mailfrom")
	private String mailFrom;
	@Column(name="phone")
	private String phone;
	@Column(name="subject")
	private String subject;
	@Column(name="website")
	private String website;
	@Column(name="contenu")
	private String contenu;
	@Column(name="logo")
	private String logo;
	@Column(name="type")
	private String type;
	@Column(name="description")
	private String description;
	@Column(name="code_mail")
	private String codemail;
	@Column(name="active")
	private Boolean active;
	@Transient
	private Boolean envoie;
	
	@ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "id_societe")
    private Societe societe;
	@ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "ID_MAIL_HDR")
    private MailHdr mailhdr;
	
	public Boolean getEnvoie() {
		return envoie;
	}
	public void setEnvoie(Boolean envoie) {
		this.envoie = envoie;
	}
	/**
	 * @return the mailhdr
	 */
	public MailHdr getMailhdr() {
		return mailhdr;
	}
	/**
	 * @param mailhdr the mailhdr to set
	 */
	public void setMailhdr(MailHdr mailhdr) {
		this.mailhdr = mailhdr;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the active
	 */
	public Boolean getActive() {
		return active;
	}
	/**
	 * @param active the active to set
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}
	public String getCodemail() {
		return codemail;
	}
	public void setCodemail(String codemail) {
		this.codemail = codemail;
	}
	
	
	/**
	 * @return the societe
	 */
	public Societe getSociete() {
		return societe;
	}
	/**
	 * @param societe the societe to set
	 */
	public void setSociete(Societe societe) {
		this.societe = societe;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the mailFrom
	 */
	public String getMailFrom() {
		return mailFrom;
	}
	/**
	 * @param mailFrom the mailFrom to set
	 */
	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public int compareTo(MailConfig o) {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
