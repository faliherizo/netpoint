package com.netPoint.applications.site.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * This is a persistent POJO class mapped to COMPTE_BANCAIRE database table
 * 
 * @author alabied
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "compte_bancaire")
public class CompteBancaire implements Serializable {
    
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_COMPTE_BANCAIRE" )
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "ID_REGLEMENT")
    private ModeReglement modeReglement;
    @ManyToOne
    @JoinColumn(name = "ID_DEVISE")
    private Devise devise;
	@Column(name="CODE_COMPTE_BANQUE")
    private String codeCompteBancaire;
	@Column(name="BANQUE")
    private String banque;
	@Column(name="CODE_BANQUE")
    private String codeBanque;
	@Column(name="CODE_GUICHET")
    private String codeGuichet;
	@Column(name="NUMERO_COMPTE")
    private String numeroCompte;
	@Column(name="CLE_RIB")
    private String cleRIB;
    
    public CompteBancaire() {
        
    }
    
    public CompteBancaire(String banque, String codeBanque, String codeGuichet, String numeroCompte, String cleRIB) {
        this.banque = banque;
        this.codeBanque = codeBanque;
        this.codeGuichet = codeGuichet;
        this.numeroCompte = numeroCompte;
        this.cleRIB = cleRIB;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getCodeCompteBancaire() {
        return codeCompteBancaire;
    }
    
    public void setCodeCompteBancaire(String codeCompteBancaire) {
        this.codeCompteBancaire = codeCompteBancaire;
    }
    
    public String getBanque() {
        return banque;
    }
    
    public void setBanque(String banque) {
        this.banque = banque;
    }
    
    public String getCodeBanque() {
        return codeBanque;
    }
    
    public void setCodeBanque(String codeBanque) {
        this.codeBanque = codeBanque;
    }
    
    public String getCodeGuichet() {
        return codeGuichet;
    }
    
    public void setCodeGuichet(String codeGuichet) {
        this.codeGuichet = codeGuichet;
    }
    
    public String getNumeroCompte() {
        return numeroCompte;
    }
    
    public void setNumeroCompte(String numeroCompte) {
        this.numeroCompte = numeroCompte;
    }
    
    public String getCleRIB() {
        return cleRIB;
    }
    
    public void setCleRIB(String cleRIB) {
        this.cleRIB = cleRIB;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (!(obj instanceof CompteBancaire)) {
            return false;
        }
        CompteBancaire other = (CompteBancaire)obj;
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

	public ModeReglement getModeReglement() {
		return modeReglement;
	}

	public void setModeReglement(ModeReglement modeReglement) {
		this.modeReglement = modeReglement;
	}

	public Devise getDevise() {
		return devise;
	}

	public void setDevise(Devise devise) {
		this.devise = devise;
	}
    
}
