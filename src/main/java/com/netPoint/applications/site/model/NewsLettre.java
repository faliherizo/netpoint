/**
 * 
 */
package com.netPoint.applications.site.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.joda.time.DateTime;

/**
 * @author Faliherizo
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "newslettre")
public class NewsLettre {
	@Id
    @GeneratedValue
    @Column(name="ID")
	private int Id;
	@Column(name="labelle") 	
	private String Labelle;
	@Column(name="relance")
	private boolean relance;
	@Column(name="delais")
	private Date Delais;
	/**
	 * @return the id
	 */
	public int getId() {
		return Id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		Id = id;
	}
	/**
	 * @return the labelle
	 */
	public String getLabelle() {
		return Labelle;
	}
	/**
	 * @param labelle the labelle to set
	 */
	public void setLabelle(String labelle) {
		Labelle = labelle;
	}
	/**
	 * @return the relance
	 */
	public boolean isRelance() {
		return relance;
	}
	/**
	 * @param relance the relance to set
	 */
	public void setRelance(boolean relance) {
		this.relance = relance;
	}
	/**
	 * @return the delais
	 */
	public Date getDelais() {
		return Delais;
	}
	/**
	 * @param delais the delais to set
	 */
	public void setDelais(Date delais) {
		Delais = delais;
	}
	

}
