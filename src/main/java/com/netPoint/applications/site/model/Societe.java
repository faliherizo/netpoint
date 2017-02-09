package com.netPoint.applications.site.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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

/**
 * This is a persistent POJO class mapped to SOCIETE database table
 * 
 * @author alabied
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "societe")
public class Societe implements Serializable{
    
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID_SOCIETE")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "ID_PAYS")
    private Pays pays;
   /* @ManyToOne
    @JoinColumn(name = "ID_LANGUE", nullable=true)
    private Langue langue;*/
    
    @Column(name="CODE_SOCIETE")
    private String codeSociete;
    @Column(name="NOM")
    private String nom;
    @Column(name="SIRET")
    private String siret;
    @Column(name="ADRESSE1")
    private String adresse1;
    @Column(name="ADRESSE2")
    private String adresse2;
    @Column(name="CODE_POSTAL")
    private String codePostal;	
    @Column(name="VILLE")
    private String ville;
    @Column(name="TVA_INTRA_COMMUNAUTAIRE")
    private String tvaIntracommunautaire;
    @Column(name="groupe")
    private Integer group;
    @Column(name="TYPE")
    private String type;
    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCodeSociete() {
        return codeSociete;
    }
    
    public void setCodeSociete(String codeSociete) {
        this.codeSociete = codeSociete;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Pays getPays() {
        return pays;
    }
    
    public void setPays(Pays pays) {
        this.pays = pays;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getAdresse1() {
        return adresse1;
    }
    
    public void setAdresse1(String adresse1) {
        this.adresse1 = adresse1;
    }
    
    public String getAdresse2() {
        return adresse2;
    }
    
    public void setAdresse2(String adresse2) {
        this.adresse2 = adresse2;
    }
    
    @Column(name = "code_postal", nullable = false, length = 5)
    public String getCodePostal() {
        return codePostal;
    }
    
    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }
    
    public String getVille() {
        return ville;
    }
    
    public void setVille(String ville) {
        this.ville = ville;
    }
    
    
    public String getTvaIntracommunautaire() {
        return tvaIntracommunautaire;
    }
    
    public void setTvaIntracommunautaire(String tvaIntracommunautaire) {
        this.tvaIntracommunautaire = tvaIntracommunautaire;
    }
    
 /*   public Langue getLangue() {
        return langue;
    }
    
    public void setLangue(Langue langue) {
        this.langue = langue;
    }
    */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (!(obj instanceof Societe)) {
            return false;
        }
        Societe other = (Societe)obj;
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

	public String getSiret() {
		return siret;
	}

	public void setSiret(String siret) {
		this.siret = siret;
	}

	public Integer getGroup() {
		return group;
	}

	public void setGroup(Integer group) {
		this.group = group;
	}
    
}