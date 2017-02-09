package com.netPoint.applications.site.model;

import java.io.Serializable;

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
 * This is a persistent POJO class mapped to CIVILITE database table
 * 
 * @author alabied
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "civilite")
@Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_ONLY)
public class Civilite implements Serializable{
    
    @Id
    @GeneratedValue
    @Column(name="ID_CIVILITE")
    private Integer id;
    @Column(name="CODE_CIVILITE")
    private String code;
    @Column(name="LIBELLE")
    private String libelle;
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }
    
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (!(obj instanceof Civilite)) {
            return false;
        }
        Civilite other = (Civilite)obj;
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
