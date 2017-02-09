package com.netPoint.applications.site.dto;

import com.netPoint.applications.site.model.ProduitRevendeur;

public class ProduitRevendeurDto {
	private Integer idProduit;
	private String name;
	
	
	public Integer getIdProduit() {
		return idProduit;
	}
	public void setIdProduit(Integer idProduit) {
		this.idProduit = idProduit;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
   
	public ProduitRevendeurDto(){
		
	}
    public ProduitRevendeurDto(ProduitRevendeur produitRevendeur){
    	setIdProduit(produitRevendeur.getProduit().getId());
    	setName(produitRevendeur.getProduit().getNomProduit());
	}
	
	
   
}
