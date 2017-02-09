package com.netPoint.applications.site.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


/**
 * This is a persistent POJO class mapped to PRIX database table
 * 
 * @author alabied
 */
@Entity
@Table(name = "PRIX")
public class Prix {
    

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", unique=true, nullable=false)
    private Integer id;
	@Column(name="CODE_PRODUIT", nullable=true, length=100)
    private String codeProduit;
	@Temporal( TemporalType.DATE)
	@Column(name = "DATE_DEBUT")
    private Date dateDebut;
	@Temporal( TemporalType.DATE)
	@Column(name = "DATE_FIN")
    private Date dateFin;
	@Column(name = "QTE_INF")
    private Integer quantiteInf;
	@Column(name = "QTE_SUP")
    private Integer quantiteSup;
	@Column(name = "DESCRIPTION")
    private String description;
	@Column(name = "TARIF")
    private Float tarif;
	@Column(name = "CODE_PRODUIT_SAGE")
    private String codeProduitSage;
	@Column(name = "DUREE")
    private int duree;
    

	@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
    @ManyToOne
    @JoinColumn(name = "duree_commande")
    private DureeCommande dureeCommande;
    
    @Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
    @ManyToOne
    @JoinColumn(name = "produit")
    private Produit produit;
    
        
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * @return the duree
	 */
	public int getDuree() {
		return duree;
	}

	/**
	 * @param duree the duree to set
	 */
	public void setDuree(int duree) {
		this.duree = duree;
	}
  
    public String getCodeProduit() {
        return codeProduit;
    }
    
    public void setCodeProduit(String codeProduit) {
        this.codeProduit = codeProduit;
    }
    
    public Date getDateDebut() {
        return dateDebut;
    }
    
    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }
    public Date getDateFin() {
        return dateFin;
    }
    
    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
    public Integer getQuantiteInf() {
        return quantiteInf;
    }
    
    public void setQuantiteInf(Integer quantiteInf) {
        this.quantiteInf = quantiteInf;
    }
    public Integer getQuantiteSup() {
        return quantiteSup;
    }
    
    public void setQuantiteSup(Integer quantiteSup) {
        this.quantiteSup = quantiteSup;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Float getTarif() {
        return tarif;
    }
    
    public void setTarif(Float tarif) {
        this.tarif = tarif;
    }
    public String getCodeProduitSage() {
        return codeProduitSage;
    }
    
    public void setCodeProduitSage(String codeProduitSage) {
        this.codeProduitSage = codeProduitSage;
    }
    
  
    public DureeCommande getDureeCommande() {
        return dureeCommande;
    }

    public void setDureeCommande(DureeCommande dureeCommande) {
        this.dureeCommande = dureeCommande;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

        
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (!(obj instanceof Prix)) {
            return false;
        }
        
        Prix other = (Prix)obj;
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
