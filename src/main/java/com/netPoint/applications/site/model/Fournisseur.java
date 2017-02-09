/**
 * 
 */
package com.netPoint.applications.site.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * @author Faliherizo
 *
 */
@Entity
@Table(name="fournisseur")
public class Fournisseur implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_FOURNISSUER", unique=true, nullable=false)
	private Integer id;
	@Column(name="NOM_FOURNISSEUR", length=1)
	private String nom;
	@Column(name="TYPE_FOURNISSEUR", length=1)
	private String Type;
	@Column(name="DESCRIPTION", length=1)
	private String Description;
	@ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="ID_UTILISATEUR")
	private User user;
	@JsonIgnore
	@OneToMany(mappedBy = "fournisseur", cascade=CascadeType.ALL)
	private Collection<Produit> listProduit;
	/**
	 * @return the listProduit
	 */
	public Collection<Produit> getListProduit() {
		return listProduit;
	}
	/**
	 * @param listProduit the listProduit to set
	 */
	public void setListProduit(Collection<Produit> listProduit) {
		this.listProduit = listProduit;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
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
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * @param nom the nom to set
	 */
	public void setNom(String noms) {
		nom = noms;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return Type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		Type = type;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return Description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		Description = description;
	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (!(obj instanceof Produit)) {
            return false;
        }
        Produit other = (Produit)obj;
        return new EqualsBuilder().append(this.getId(), other.getId()).isEquals();
    }
    
	 @Override
	 public int hashCode() {
	        return new HashCodeBuilder().append(this.getId()).toHashCode();
	 }
}
