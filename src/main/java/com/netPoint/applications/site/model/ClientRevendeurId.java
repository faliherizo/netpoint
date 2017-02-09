package com.netPoint.applications.site.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the client_revendeur database table.
 * 
 */
@Embeddable
public class ClientRevendeurId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ID_CLIENT", unique=true, nullable=false)
	private Integer idClient;

	@Column(name="ID_REVENDEUR", unique=true, nullable=false)
	private Integer idRevendeur;

    /**
	 * @return the idClient
	 */
	public Integer getIdClient() {
		return idClient;
	}

	/**
	 * @param idClient the idClient to set
	 */
	public void setIdClient(Integer idClient) {
		this.idClient = idClient;
	}

	/**
	 * @return the idRevendeur
	 */
	public Integer getIdRevendeur() {
		return idRevendeur;
	}

	/**
	 * @param idRevendeur the idRevendeur to set
	 */
	public void setIdRevendeur(Integer idRevendeur) {
		this.idRevendeur = idRevendeur;
	}

	public ClientRevendeurId() {
    }
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ClientRevendeurId)) {
			return false;
		}
		ClientRevendeurId castOther = (ClientRevendeurId)other;
		return 
			this.idClient.equals(castOther.idClient)
			&& this.idRevendeur.equals(castOther.idRevendeur);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idClient.hashCode();
		hash = hash * prime + this.idRevendeur.hashCode();
		
		return hash;
    }
}