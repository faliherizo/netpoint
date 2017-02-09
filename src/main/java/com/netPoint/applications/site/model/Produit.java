package com.netPoint.applications.site.model;

import java.io.Serializable;
import javax.persistence.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Set;


/**
 * The persistent class for the produit database table.
 * 
 */
@Entity
@Table(name="produit")
public class Produit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_PRODUIT", unique=true, nullable=false)
	private Integer id;

	@Column(name="ETAT_PRODUIT", length=1)
	private String etatProduit;
	@Column(name="NOM_PRODUIT", length=256)
	private String nomProduit;
	
    @ManyToOne
    @JoinColumn(name="ID_FOURNISSEUR", unique=true, nullable=false)
	private Fournisseur fournisseur;
    @Column(name="PRIX")
	private float prix;
    @Column(name="SKU")
   	private String sku;
    @Column(name="URL")
   	private String url;
    @Column(name="CODE_PRODUIT")
   	private String codeproduit;
	/**
	 * @return the codeproduit
	 */
	public String getCodeproduit() {
		return codeproduit;
	}



	/**
	 * @param codeproduit the codeproduit to set
	 */
	public void setCodeproduit(String codeproduit) {
		this.codeproduit = codeproduit;
	}



	/**
	 * @return the sku
	 */
	public String getSku() {
		return sku;
	}



	/**
	 * @param sku the sku to set
	 */
	public void setSku(String sku) {
		this.sku = sku;
	}



	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}



	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}



	/**
	 * @return the prix
	 */
	public float getPrix() {
		return prix;
	}



	/**
	 * @param prix the prix to set
	 */
	public void setPrix(float prix) {
		this.prix = prix;
	}



	/**
	 * @return the fournisseur
	 */
	public Fournisseur getFournisseur() {
		return fournisseur;
	}



	/**
	 * @param fournisseur the fournisseur to set
	 */
	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}



	//bi-directional many-to-one association to Commande
	/*@OneToMany(mappedBy="produit")
	private Set<Commande> commandes;

	//bi-directional many-to-one association to Prix
	@OneToMany(mappedBy="produit")
	private Set<Prix> prixs;

	//bi-directional many-to-one association to ProduitRevendeur
	@OneToMany(mappedBy="produit")
	private Set<ProduitRevendeur> produitRevendeurs;
*/
    public Produit() {
    }



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getEtatProduit() {
		return this.etatProduit;
	}

	public void setEtatProduit(String etatProduit) {
		this.etatProduit = etatProduit;
	}

	public String getNomProduit() {
		return this.nomProduit;
	}

	public void setNomProduit(String nomProduit) {
		this.nomProduit = nomProduit;
	}
	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (!(obj instanceof Produit)) {
            return false;
        }
        Produit other = (Produit)obj;
        return new EqualsBuilder().append(this.getId(), other.getId()).isEquals();
    }
    
	 @Override
	 public int hashCode() {
	        return new HashCodeBuilder().append(this.getId()).toHashCode();
	 }
/*
	public Set<Commande> getCommandes() {
		return this.commandes;
	}

	public void setCommandes(Set<Commande> commandes) {
		this.commandes = commandes;
	}
	
	public Set<Prix> getPrixs() {
		return this.prixs;
	}

	public void setPrixs(Set<Prix> prixs) {
		this.prixs = prixs;
	}
	
	public Set<ProduitRevendeur> getProduitRevendeurs() {
		return this.produitRevendeurs;
	}

	public void setProduitRevendeurs(Set<ProduitRevendeur> produitRevendeurs) {
		this.produitRevendeurs = produitRevendeurs;
	}
	*/
}