package com.netPoint.applications.site.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.Set;


/**
 * The persistent class for the duree_commande database table.
 * 
 */
@Entity
@Table(name="duree_commande")
public class DureeCommande implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_DUREE_COMMANDE", unique=true, nullable=false)
	private Integer id;

	@Column(name="DESCRIPTION", length=256)
	private String description;

	@Column(name="DUREE", nullable=false)
	private Integer duree;

	//bi-directional many-to-one association to Commande
	/*@OneToMany(mappedBy="dureeCommande")
	private Set<Commande> commandes;
*/
	//bi-directional many-to-one association to Prix
	/*@OneToMany(mappedBy="dureeCommande")
	private Set<Prix> prixs;
*/
    public DureeCommande() {
    }

	

	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getDuree() {
		return this.duree;
	}

	public void setDuree(Integer duree) {
		this.duree = duree;
	}

	/*public Set<Commande> getCommandes() {
		return this.commandes;
	}

	public void setCommandes(Set<Commande> commandes) {
		this.commandes = commandes;
	}
	
	public Set<Prix> getPrixs() {
		return this.prixs;
	}

	public void setPrixs(Set<Prix> prixs) {
		this.prixs = prixs;
	}*/
	
}