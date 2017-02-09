package com.netPoint.applications.site.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the client_revendeur database table.
 * 
 */
@Entity
@Table(name="client_revendeur")
public class ClientRevendeur implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ClientRevendeurId id;

	@Column(name="NUMERO_CLIENT_REVENDEUR", length=32)
	private String numeroClientRevendeur;

	//bi-directional many-to-one association to Client
    @ManyToOne
	@JoinColumn(name="ID_CLIENT", nullable=false, insertable=false, updatable=false)
	private Client client;

	//bi-directional many-to-one association to Revendeur
    @ManyToOne
	@JoinColumn(name="ID_REVENDEUR", nullable=false, insertable=false, updatable=false)
	private Revendeur revendeur;
/*
    @ManyToOne
    @JoinColumn(name="ID_PRODUIT", nullable=false, insertable=false, updatable=false)
    private Produit produit;
    
    /**
	 * @return the produit
	 */
	/*public Produit getProduit() {
		return produit;
	}

	/**
	 * @param produit the produit to set
	 */
	/*public void setProduit(Produit produit) {
		this.produit = produit;
	}
*/
	public ClientRevendeur() {
    }

	public ClientRevendeurId getId() {
		return this.id;
	}

	public void setId(ClientRevendeurId id) {
		this.id = id;
	}
	
	public String getNumeroClientRevendeur() {
		return this.numeroClientRevendeur;
	}

	public void setNumeroClientRevendeur(String numeroClientRevendeur) {
		this.numeroClientRevendeur = numeroClientRevendeur;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	public Revendeur getRevendeur() {
		return this.revendeur;
	}

	public void setRevendeur(Revendeur revendeur) {
		this.revendeur = revendeur;
	}
	
}