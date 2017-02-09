package com.netPoint.applications.site.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.hibernate.annotations.Cache;

/**
 * This is a persistent POJO class mapped to PAYS database table
 * 
 * @author alabied
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "pays")
@Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_ONLY)
public class Pays implements Serializable{
    
	@Id
    @GeneratedValue
    @Column(name="ID_PAYS")
    private Integer id;
	@Column(name="LIBELLE")
    private String libelle;
    @Column(name="CODE_PAYS")
    private String code;
    @Column(name="TVA")
    private Float tva;
    
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

	public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
        
    public Float getTva() {
        return tva;
    }

    public void setTva(Float tva) {
        this.tva = tva;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (!(obj instanceof Pays)) {
            return false;
        }
        Pays other = (Pays)obj;
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
