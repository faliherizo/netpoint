/**
 * 
 */
package com.netPoint.applications.site.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Faliherizo
 *
 */
@Entity
@Table(name="commande_revendeur")
public class CommandeRevendeur {
private static final long serialVersionUID = 1L;
	
	@Transient
	private static final long MILLISECONDS_PER_DAY = 1000 * 60 * 60 * 24;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	private Integer id;
	//bi-directional many-to-one association to Revendeur
    @ManyToOne
	@JoinColumn(name="ID_REVENDEUR", nullable=false)
	private Revendeur revendeur;
    
  //bi-directional many-to-one association to Revendeur
    @ManyToOne
	@JoinColumn(name="ID_COMMANDE", nullable=false)
	private Commande commande;
    
	@Column(name="NUMERO_COMMANDE_REV", length=32)
	private Integer numeroCommandeRev;

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
	 * @return the revendeur
	 */
	public Revendeur getRevendeur() {
		return revendeur;
	}

	/**
	 * @param revendeur the revendeur to set
	 */
	public void setRevendeur(Revendeur revendeur) {
		this.revendeur = revendeur;
	}

	/**
	 * @return the commande
	 */
	public Commande getCommande() {
		return commande;
	}

	/**
	 * @param commande the commande to set
	 */
	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	/**
	 * @return the numeroCommandeRev
	 */
	public Integer getNumeroCommandeRev() {
		return numeroCommandeRev;
	}

	/**
	 * @param numeroCommandeRev the numeroCommandeRev to set
	 */
	public void setNumeroCommandeRev(Integer numeroCommandeRev) {
		this.numeroCommandeRev = numeroCommandeRev;
	}

    
    
	
}
