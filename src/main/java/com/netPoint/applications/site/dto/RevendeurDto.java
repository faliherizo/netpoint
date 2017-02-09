package com.netPoint.applications.site.dto;

import com.netPoint.applications.site.model.Revendeur;

public class RevendeurDto {
	
	private Integer id;
	private String nom;
	private String prenom;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public RevendeurDto(){
		
	}
	
    public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public RevendeurDto(Integer id, String nom, String prenom) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
	}
	

}
