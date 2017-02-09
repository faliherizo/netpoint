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
 * This is a persistent POJO class mapped to DEVISE database table
 * 
 * @author mija
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "devise")
public class Devise implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID_DEVISE")
    private Integer id;
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
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (!(obj instanceof Devise)) {
            return false;
        }
        Devise other = (Devise)obj;
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
