package com.netPoint.applications.site.model;

import java.io.Serializable;

import javassist.expr.NewArray;

import javax.persistence.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.math.BigInteger;


/**
 * The persistent class for the produit_revendeur database table.
 * 
 */
@Entity
@Table(name="produit_revendeur")
public class ProduitRevendeur implements Serializable {
	private static final long serialVersionUID = 1L;

		@EmbeddedId
	    private ProduitRevendeurId id;
	    @ManyToOne
	    @JoinColumn(name = "ID_REVENDEUR",updatable=false, insertable = false)
	    private Revendeur revendeur;
	    @ManyToOne
	    @JoinColumn(name = "ID_PRODUIT",updatable=false, insertable = false)
	    private Produit produit;
	    @Column(name="NOM_PRODUIT_REVENDEUR")
	    private String nomProduitRevendeur;
	    @Column(name="TAUX_REMISE")
	    private Integer tauxRemise;
	    @Column(name="QUANTITE_REMISE")
	    private Integer quantiteRemise;
	    @Transient
	    private float prixht;
	    public float getPrixht() {
	    	if(tauxRemise!=null || tauxRemise!=0)
	    		prixht=prixht- (prixht*tauxRemise)/100;
			return prixht;
		}

		public void setPrixht(float prixht) {
			this.prixht = prixht;
		}

		/** default constructor */
	    public ProduitRevendeur() {
	    }
	    
	    /**
	     * @return the id
	     */
	    public ProduitRevendeurId getId() {
	        return id;
	    }
	    
	    public void setId(ProduitRevendeurId id) {
	        this.id = id;
	    }
	    
	    public Revendeur getRevendeur() {
	        return revendeur;
	    }
	    
	    public void setRevendeur(Revendeur revendeur) {
	        this.revendeur = revendeur;
	    }
	    
	    public Produit getProduit() {
	        return produit;
	    }
	    
	    public void setProduit(Produit produit) {
	        this.produit = produit;
	    }
	    @Column(name = "nom_produit_revendeur", nullable = true, length = 255)
	    public String getNomProduitRevendeur() {
	        return nomProduitRevendeur;
	    }
	    
	    public void setNomProduitRevendeur(String nomProduitRevendeur) {
	        this.nomProduitRevendeur = nomProduitRevendeur;
	    }
	    
	    @Column(name = "taux_remise", nullable = true, length = 11)
	    public Integer getTauxRemise() {
	        return tauxRemise;
	    }
	    
	    public void setTauxRemise(Integer tauxRemise) {
	        this.tauxRemise = tauxRemise;
	    }
	    
	    @Column(name = "quantite_remise", nullable = true, length = 11)
	    public Integer getQuantiteRemise() {
	        return quantiteRemise;
	    }
	    
	    public void setQuantiteRemise(Integer quantiteRemise) {
	        this.quantiteRemise = quantiteRemise;
	    }
	    
	    @Override
	    public boolean equals(Object obj) {
	        if (this == obj) {
	            return true;
	        } else if (obj == null) {
	            return false;
	        } else if (!(obj instanceof ProduitRevendeur)) {
	            return false;
	        }
	        ProduitRevendeur other = (ProduitRevendeur)obj;
	        return new EqualsBuilder().append(this.getId(), other.getId()).isEquals();
	    }
	    
	    @Override
	    public int hashCode() {
	        return new HashCodeBuilder().append(this.getId()).toHashCode();
	    }
	    
	    @Override
	    public String toString() {
	        return new ReflectionToStringBuilder(this).toString();
	    }
}