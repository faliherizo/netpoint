/**
 * 
 */
package com.netPoint.applications.site.service.soap.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.annotate.JsonAutoDetect;
/**
 * @author Faliherizo
 *
 */
@JsonAutoDetect
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Customer", propOrder = {
    "telephone",
    "email",
    "name",
    "lastname",
    "adress",
    "societe",
    "codeclient",
    "pays",
    "siret",
    "rcs",
    "langue"
    
})
public class Customer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlElement
	private String telephone;
	@XmlElement
	private String email;
	@XmlElement
	private String name;
	@XmlElement
	private String lastname;
	@XmlElement
	private String adress;
	@XmlElement
	private String societe;
	@XmlElement
	private String codeclient;
	@XmlElement
	private String pays;
	@XmlElement
	private String siret;
	@XmlElement
	private String rcs;
	@XmlElement
	private String langue;

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}


	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	/**
	 * @return the adress
	 */
	public String getAdress() {
		return adress;
	}


	/**
	 * @param adress the adress to set
	 */
	public void setAdress(String adress) {
		this.adress = adress;
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
	 * @return the codeclient
	 */
	public String getCodeclient() {
		return codeclient;
	}


	/**
	 * @param codeclient the codeclient to set
	 */
	public void setCodeclient(String codeclient) {
		this.codeclient = codeclient;
	}


	/**
	 * @return the pays
	 */
	public String getPays() {
		return pays;
	}


	/**
	 * @param pays the pays to set
	 */
	public void setPays(String pays) {
		this.pays = pays;
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
	 * @return the langue
	 */
	public String getLangue() {
		return langue;
	}


	/**
	 * @param langue the langue to set
	 */
	public void setLangue(String langue) {
		this.langue = langue;
	}


	public Customer() {
		
	}
	
}
