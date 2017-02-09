package com.netPoint.applications.site.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.hibernate.annotations.Cache;

/**
 * This is a persistent POJO class mapped to ETAT_COMPTE database table
 * 
 * @author alabied
 */
@Entity
@Table(name = "etat_compte")
public class EtatCompte {
    
	

	@Id
	@GeneratedValue
	@Column(name="ID_ETAT_COMPTE")
    private Integer id;
	@Column(name="CODE_ETAT_COMPTE", length=3)
	private Boolean codeetatcompte;
	@Column(name="LIBELLE")
    private String libelle;
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Boolean getCodeetatcompte() {
		return codeetatcompte;
	}

	public void setCodeetatcompte(Boolean codeetatcompte) {
		this.codeetatcompte = codeetatcompte;
	}
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (!(obj instanceof EtatCompte)) {
            return false;
        }
        EtatCompte other = (EtatCompte)obj;
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
