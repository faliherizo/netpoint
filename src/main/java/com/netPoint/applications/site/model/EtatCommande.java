package com.netPoint.applications.site.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the etat_commande database table.
 * 
 */
@Entity
@Table(name="etat_commande")
public class EtatCommande implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_ETAT_COMMANDE", unique=true, nullable=false)
	private Integer id;

	@Column(name="CODE_ETAT_COMMANDE", nullable=false, length=10)
	private String codeEtatCommande;

	@Column(name="LIBELLE", length=256)
	private String libelle;

	//bi-directional many-to-one association to Commande
	/*@OneToMany(mappedBy="etatCommande")
	private Set<Commande> commandes;
*/
    public EtatCommande() {
    }

	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getCodeEtatCommande() {
		return this.codeEtatCommande;
	}

	public void setCodeEtatCommande(String codeEtatCommande) {
		this.codeEtatCommande = codeEtatCommande;
	}

	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/*public Set<Commande> getCommandes() {
		return this.commandes;
	}

	public void setCommandes(Set<Commande> commandes) {
		this.commandes = commandes;
	}*/
	
}