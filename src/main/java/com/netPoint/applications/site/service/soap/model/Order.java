/**
 * 
 */
package com.netPoint.applications.site.service.soap.model;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * @author Faliherizo
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Order", propOrder = {
    "codeclient",
    "date_fin",
    "nombreposte",
    "orderDate",
    "numeroCommande",
    "quantity",
    "numeroRevendeur",
    "product"
})
public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*@XmlElement(name="customer", required = true)
	private Customer customer;*/
	//liste de commande du client
	@XmlElement
	private String codeclient;
	@XmlElement
	protected XMLGregorianCalendar date_fin;
	@XmlElement
	private Integer nombreposte;
	@XmlElement
	private XMLGregorianCalendar orderDate;
	@XmlElement
	private String numeroCommande;
	@XmlElement
	private int quantity;
	@XmlElement
	private String numeroRevendeur;
	@XmlElement
	private String product;
	
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	/**
	 * @return the date_commande
	 */
	public XMLGregorianCalendar getDate_commande() {
		return orderDate;
	}
	/**
	 * @param date_commande the date_commande to set
	 */
	public void setDate_commande(XMLGregorianCalendar orderDate) {
		this.orderDate = orderDate;
	}
	/**
	 * @return the date_fin
	 */
	public XMLGregorianCalendar getDate_fin() {
		return date_fin;
	}
	/**
	 * @param date_fin the date_fin to set
	 */
	public void setDate_fin(XMLGregorianCalendar date_fin) {
		this.date_fin = date_fin;
	}
	/**
	 * @return the client_id
	 */
	public String getCodeclient() {
		return codeclient;
	}
	/**
	 * @param client_id the client_id to set
	 */
	public void setCodeClient(String codeclient) {
		this.codeclient = codeclient;
	}

	/**
	 * @return the nombreposte
	 */
	public Integer getNombreposte() {
		return nombreposte;
	}
	/**
	 * @param nombreposte the nombreposte to set
	 */
	public void setNombreposte(Integer nombreposte) {
		this.nombreposte = nombreposte;
	}
	/**
	 * @return the numeroCommande
	 */
	public String getNumeroCommande() {
		return numeroCommande;
	}
	/**
	 * @param numeroCommande the numeroCommande to set
	 */
	public void setNumeroCommande(String numeroCommande) {
		this.numeroCommande = numeroCommande;
	}
	/**
	 * @return the quantity
	 */
	public int getquantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setquantity(int quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return the numeroRevendeur
	 */
	public String getNumeroRevendeur() {
		return numeroRevendeur;
	}
	/**
	 * @param numeroRevendeur the numeroRevendeur to set
	 */
	public void setNumeroRevendeur(String numeroRevendeur) {
		this.numeroRevendeur = numeroRevendeur;
	}

	/*public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}*/
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void setCodeclient(String codeclient) {
		this.codeclient = codeclient;
	}
	
}
