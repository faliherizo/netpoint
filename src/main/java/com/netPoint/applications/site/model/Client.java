package com.netPoint.applications.site.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


/**
 * This is a persistent POJO class mapped to CLIENT database table
 * 
 * @author alabied
 */
@Entity
@Table(name = "client")
public class Client {
    
	@Id
    @GeneratedValue
    @Column(name="ID_CLIENT")
    private Integer id;
	@Column(name="CODE_CLIENT")
    private String codeClient;
	@Column(name="SIRET")
    private String siret;
    @Column(name="RCS")
    private String rcs;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "ID_SOCIETE")
    private Societe societe;
    /*@ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "ID_COMPTE_BANCAIRE")
    private CompteBancaire compteBancaire;*/
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "ID_UTILISATEUR")
    private User user;
    @JsonIgnore
	@OneToMany(mappedBy = "client", cascade=CascadeType.ALL)
	private Collection<Commande> listCommande;

	@Column(name="CUSTOMER_KEY")
    private String customerKey;
	@Column(name="NumeroRevendeur")
    private String numeroRevendeur;
    public String getNumeroRevendeur() {
		return numeroRevendeur;
	}

	public void setNumeroRevendeur(String numeroRevendeur) {
		this.numeroRevendeur = numeroRevendeur;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Collection<Commande> getListCommande() {
		return listCommande;
	}

	public void setListCommande(Collection<Commande> listCommande) {
		this.listCommande = listCommande;
	}
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
     public String getCodeClient() {
        return codeClient;
    }
    
    public void setCodeClient(String codeClient) {
        this.codeClient = codeClient;
    }
    
    public String getSiret() {
        return siret;
    }
    
    public void setSiret(String siret) {
        this.siret = siret;
    }
    
    public String getRcs() {
        return rcs;
    }
    
    public void setRcs(String rcs) {
        this.rcs = rcs;
    }
    
    public Societe getSociete() {
        return societe;
    }
    
    public void setSociete(Societe societe) {
        this.societe = societe;
    }
    
   /* public CompteBancaire getCompteBancaire() {
        return compteBancaire;
    }
    
    public void setCompteBancaire(CompteBancaire compteBancaire) {
        this.compteBancaire = compteBancaire;
    }
    */
    public String getCustomerKey() {
        return customerKey;
    }
    
    public void setCustomerKey(String customerKey) {
        this.customerKey = customerKey;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (!(obj instanceof Client)) {
            return false;
        }
        Client other = (Client)obj;
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
