/**
 * 
 */
package com.netPoint.applications.site.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.hibernate.annotations.Cache;

/**
 * @author Faliherizo
 *
 */
@SuppressWarnings("serial")
@Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_ONLY)
@Entity
@Table(name="type_commande")
public class TypeCommande implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
    private Integer id;
	@Column(name="CODE")
    private String code;
	@Column(name="LIBELLE")
    private String libelle;
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}
	/**
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	 public int compareTo(TypeCommande obj) {
	        return obj.getId().compareTo(this.getId());
	 }
	 @Override
	 public boolean equals(Object obj) {
	        if (this == obj) {
	            return true;
	        } else if (obj == null) {
	            return false;
	        } else if (!(obj instanceof Profil)) {
	            return false;
	        }
	        TypeCommande other = (TypeCommande)obj;
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
