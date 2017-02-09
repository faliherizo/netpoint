package com.netPoint.applications.site.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the produit_revendeur database table.
 * 
 */
@Embeddable
public class ProduitRevendeurId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	
	@Column(name="ID_REVENDEUR", unique=true, nullable=false)
	private Integer idRevendeur;
	    
	@Column(name="ID_PRODUIT", unique=true, nullable=false)
	private Integer idProduit;
		/**
	 * @return the idRevendeur
	 */
	public Integer getIdRevendeur() {
		return idRevendeur;
	}
	/**
	 * @param idRevendeur the idRevendeur to set
	 */
	public void setIdRevendeur(Integer idRevendeur) {
		this.idRevendeur = idRevendeur;
	}
	/**
	 * @return the idProduit
	 */
	public Integer getIdProduit() {
		return idProduit;
	}
	/**
	 * @param idProduit the idProduit to set
	 */
	public void setIdProduit(Integer idProduit) {
		this.idProduit = idProduit;
	}
	@Override
	public boolean equals(Object other) {
	        if ((this == other))
	            return true;
	        if ((other == null))
	            return false;
	        if (!(other instanceof ProduitRevendeurId))
	            return false;
	        ProduitRevendeurId produitRevendeur = (ProduitRevendeurId)other;
	        
	        return (this.getIdProduit() == produitRevendeur.getIdProduit()) && (this.getIdProduit() == produitRevendeur.getIdProduit());
	 }
	/*@Override
	    public int hashCode() {
	        int result;
	        result = (revendeur != null ? revendeur.hashCode() : 0);
	        result = 31 * result + (produit != null ? produit.hashCode() : 0);
	        return result;
	    }*/
	@Override
	 public int hashCode() {
	     return this.getIdProduit().hashCode() + this.getIdProduit().hashCode();
		
		/* int result;
	        result = (produit != null ? produit.hashCode() : 0);
	        result = 31 * result + (revendeur != null ? revendeur.hashCode() : 0);
	        return result;*/
	 }

	//@Column(name="ID_REVENDEUR", unique=true, nullable=false)
	/*
	private Integer idRevendeur;

	@Column(name="ID_PRODUIT", unique=true, nullable=false)
	private Integer idProduit;

    public ProduitRevendeurId() {
    }
	
	public Integer getIdRevendeur() {
		return idRevendeur;
	}

	public void setIdRevendeur(Integer idRevendeur) {
		this.idRevendeur = idRevendeur;
	}



	public Integer getIdProduit() {
		return idProduit;
	}



	public void setIdProduit(Integer idProduit) {
		this.idProduit = idProduit;
	}

*/

	/*public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ProduitRevendeurId)) {
			return false;
		}
		ProduitRevendeurId castOther = (ProduitRevendeurId)other;
		return 
			this.idRevendeur.equals(castOther.idRevendeur)
			&& this.idProduit.equals(castOther.idProduit);

    }
    */
	/*@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.revendeur.hashCode();
		hash = hash * prime + this.produit.hashCode();
		
		return hash;
    }
    */
}