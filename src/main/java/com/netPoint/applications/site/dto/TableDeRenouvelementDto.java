package com.netPoint.applications.site.dto;

import java.util.Date;

public class TableDeRenouvelementDto {
	
	private Integer id;
	
	private String nomSociete;
	
	private Double montant;
	
	private String deviceNom;
	
	private Integer quantite;
	
	private String produitNom;
	
	private Date dateEcheance;
	
	private String DateStringEcheance;
	
	private Double nombreDuJours;
	
	private String nomRevendeur;
	
	

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

	public Double getMontant() {
		return montant;
	}

	public void setMontant(Double montant) {
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

	public String getDateStringEcheance() {
		return DateStringEcheance;
	}

	public void setDateStringEcheance(String dateStringEcheance) {
		DateStringEcheance = dateStringEcheance;
	}

	public Double getNombreDuJours() {
		return nombreDuJours;
	}

	public void setNombreDuJours(Double nombreDuJours) {
		this.nombreDuJours = nombreDuJours;
	}

	public String getNomRevendeur() {
		return nomRevendeur;
	}

	public void setNomRevendeur(String nomRevendeur) {
		this.nomRevendeur = nomRevendeur;
	}
	
}
