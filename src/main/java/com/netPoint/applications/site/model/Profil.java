package com.netPoint.applications.site.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.hibernate.annotations.Cache;

/**
 * This is a persistent POJO class mapped to DROIT database table
 * 
 * @author alabied
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "profil")
@Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_ONLY)
public class Profil implements Serializable{
    
	@Id
	@GeneratedValue
	@Column(name="ID_PROFIL")
    private Integer id;
	@Column(name="CODE_PROFIL")
    private String code;
	@Column(name="LIBELLE")
    private String libelle;
	@Column(name="ROLE_USER")
	private String role;
	/*@ManyToOne( fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_UTILISATEUR")
	private User user;

	
	public User getUser() {
		return user;
	}

	
	public void setUser(User user) {
		this.user = user;
	}*/

	public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
     
    public String getLibelle() {
        return libelle;
    }
    
   public String getCode() {
        return code;
    }

    public void setCode(String codeProfil) {
        this.code = codeProfil;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    
    public int compareTo(Profil obj) {
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
        Profil other = (Profil)obj;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
    
}
