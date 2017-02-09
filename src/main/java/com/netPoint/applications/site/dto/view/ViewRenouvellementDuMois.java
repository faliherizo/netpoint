/**
 * 
 */
package com.netPoint.applications.site.dto.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Administrateur
 *
 */
public class ViewRenouvellementDuMois implements Serializable {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	final long MILLISECONDS_PER_DAY = 1000 * 60 * 60 * 24; 
	
    private Integer id;
	
	private String nomSociete;
	
	private BigDecimal montant;
	
	private String deviceNom;
	
	private Integer quantite;
	
	private String produitNom;
	
	private Date dateEcheance;
	
	private long nombreDuJours;
	
	private String nomRevendeur;
	
	private String dateEcheanceString;
	
	private Integer idUser;
	
	private long nombreJourEcouler;
	
	
	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public String getDateEcheanceString() {
		return dateEcheanceString;
	}

	public void setDateEcheanceString(String dateEcheanceString) {
		this.dateEcheanceString = dateEcheanceString;
	}

	private List<String> commandes;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomSociete() {
		return nomSociete;
	}

	public void setNomSociete(String nomSociete) {
		this.nomSociete = nomSociete;
	}

	public BigDecimal getMontant() {
		return montant;
	}

	public void setMontant(BigDecimal montant) {
		this.montant = montant;
	}

	public String getDeviceNom() {
		return deviceNom;
	}

	public void setDeviceNom(String deviceNom) {
		this.deviceNom = deviceNom;
	}

	public Integer getQuantite() {
		return quantite;
	}

	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}

	public String getProduitNom() {
		return produitNom;
	}

	public void setProduitNom(String produitNom) {
		this.produitNom = produitNom;
	}

	public Date getDateEcheance() {
		return dateEcheance;
	}

	public void setDateEcheance(Date dateEcheance) {
		this.dateEcheance = dateEcheance;
	}

	public long getNombreDuJours() {
		return nombreDuJours;
	}

	public void setNombreDuJours(long nombreDuJours) {
		this.nombreDuJours = nombreDuJours;
	}

	public String getNomRevendeur() {
		return nomRevendeur;
	}

	public void setNomRevendeur(String nomRevendeur) {
		this.nomRevendeur = nomRevendeur;
	}

	public ViewRenouvellementDuMois() {
		super();
	}

	public ViewRenouvellementDuMois(Integer id, String nomSociete,
			BigDecimal montant, String deviceNom, Integer quantite,
			String produitNom, Date dateEcheance,
			Integer idUser) {
		super();
		this.id = id;
		this.nomSociete = nomSociete;
		this.montant = montant;
		this.deviceNom = deviceNom;
		this.quantite = quantite;
		this.produitNom = produitNom;
		this.dateEcheance = dateEcheance;
		nombreDuJours=returnNombreDuJour(dateEcheance);
		dateEcheanceString = dateToString(dateEcheance);
		this.nombreJourEcouler = returnNombreDuJourEcouler(dateEcheance);
		this.idUser = idUser;
	}

	public List<String> getCommandes() {
		return commandes;
	}

	public void setCommandes(List<String> commandes) {
		this.commandes = commandes;
	}
	
	private long returnNombreDuJour(Date dateDiff){
		Date dateCurrent = new Date();
		long result = 0;
		
		if(dateDiff.compareTo(dateCurrent)==1){
			result = dateDiff.getTime() - dateCurrent.getTime();
		}
		return result / (MILLISECONDS_PER_DAY);
	}
	
	private long returnNombreDuJourEcouler(Date dateDiff){
		Date dateCurrent = new Date();
		long result = 0;
		
		if(dateDiff.compareTo(dateCurrent)==-1){
			result = dateCurrent.getTime()-dateDiff.getTime(); 
		}
		return result / (MILLISECONDS_PER_DAY);
	}
	
	/**
	 * Convertit Date to String pour l'affichager des dates
	 * 
	 * @param Date
	 * @return String
	 */
	private String dateToString(final Date date){
		String dateFormatee = "";
		if(date!=null){
			SimpleDateFormat formatDateJour = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			dateFormatee= formatDateJour.format(date);
		}
		return dateFormatee;
	}

	public long getNombreJourEcouler() {
		return nombreJourEcouler;
	}

	public void setNombreJourEcouler(long nombreJourEcouler) {
		this.nombreJourEcouler = nombreJourEcouler;
	}
}
