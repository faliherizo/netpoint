package com.netPoint.applications.site.model;

import java.io.Serializable;
import javax.persistence.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.util.AutoPopulatingList;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


/**
 * The persistent class for the revendeur database table.
 * 
 */
@Entity
@Table(name="revendeur")
public class Revendeur implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_REVENDEUR", nullable = false)
	private Integer id;

	@Column(name="CODE_REVENDEUR", length=32)
	private String codeRevendeur;
	//private String nomrevendeur;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="DATE_CREATION")
	private Date dateCreation;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="DATE_FIN")
	private Date dateFin;

	@Column(name="DROIT_CREATION_REVENDEUR", length=1)
	private String droitCreationRevendeur;

	@Column(name="EMAIL_AUTOMATIQUE", length=1)
	private String emailAutomatique;

	@Column(name="ETAT", length=1)
	private String etat;

	

	@Column(name="NIVEAU")
	private BigInteger niveau;

	@Column(name="PARTNER_CODE", length=60)
	private String partnerCode;

	@Column(name="PARTNER_PWD", length=60)
	private String partnerPwd;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "ID_MAIL_HDR")
    private MailHdr mailhdr;
	//bi-directional many-to-one association to ClientRevendeur
    @JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="revendeur")
	private Set<ClientRevendeur> clientRevendeurs;

	//bi-directional many-to-one association to Commande
    @JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="revendeur")
	private Set<Commande> commandes;

	//bi-directional many-to-one association to ProduitRevendeur //private Set<ProduitRevendeur> produitRevendeurs;
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade =CascadeType.ALL, mappedBy="revendeur")
	@JoinColumn(name="ID_REVENDEUR")
	public List<ProduitRevendeur> produitRevendeurs =new LinkedList<ProduitRevendeur>();
	
	//private Set<ProduitRevendeur> produitRevendeurs = new HashSet<ProduitRevendeur>(0);
	//bi-directional many-to-one association to Societe
    @ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ID_SOCIETE")
	private Societe societe;

	//bi-directional many-to-one association to CompteBancaire
    @ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ID_COMPTE_BANCAIRE")
	private CompteBancaire compteBancaire;

	//bi-directional many-to-one association to Revendeur
    @Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
    @ManyToOne
	@JoinColumn(name="REV_ID_REVENDEUR")
	private Revendeur revendeur;
    
    //@JsonIgnore
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="ID_UTILISATEUR")
	private User user;

	//bi-directional many-to-one association to Revendeur
    @JsonIgnore
	@OneToMany(mappedBy="revendeur")
	private Set<Revendeur> revendeurs;

	//bi-directional many-to-one association to Utilisateur
    @JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="revendeur")
	private List<User> utilisateurs= new ArrayList<User>();


	
    public MailHdr getMailhdr() {
		return mailhdr;
	}


	public void setMailhdr(MailHdr mailhdr) {
		this.mailhdr = mailhdr;
	}


	public Revendeur() {
    }

	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
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
	public String getCodeRevendeur() {
		return this.codeRevendeur;
	}

	public void setCodeRevendeur(String codeRevendeur) {
		this.codeRevendeur = codeRevendeur;
	}

	public Date getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Date getDateFin() {
		return this.dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public String getDroitCreationRevendeur() {
		return this.droitCreationRevendeur;
	}

	public void setDroitCreationRevendeur(String droitCreationRevendeur) {
		this.droitCreationRevendeur = droitCreationRevendeur;
	}

	public String getEmailAutomatique() {
		return this.emailAutomatique;
	}

	public void setEmailAutomatique(String emailAutomatique) {
		this.emailAutomatique = emailAutomatique;
	}

	public String getEtat() {
		return this.etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public BigInteger getNiveau() {
		return this.niveau;
	}

	public void setNiveau(BigInteger niveau) {
		this.niveau = niveau;
	}

	public String getPartnerCode() {
		return this.partnerCode;
	}

	public void setPartnerCode(String partnerCode) {
		this.partnerCode = partnerCode;
	}

	public String getPartnerPwd() {
		return this.partnerPwd;
	}

	public void setPartnerPwd(String partnerPwd) {
		this.partnerPwd = partnerPwd;
	}

	public Set<ClientRevendeur> getClientRevendeurs() {
		return this.clientRevendeurs;
	}

	public void setClientRevendeurs(Set<ClientRevendeur> clientRevendeurs) {
		this.clientRevendeurs = clientRevendeurs;
	}
	
	public Set<Commande> getCommandes() {
		return this.commandes;
	}

	public void setCommandes(Set<Commande> commandes) {
		this.commandes = commandes;
	}
	/*public Set<ProduitRevendeur> getProduitRevendeurs() {
		return this.produitRevendeurs;
	}

	public void setProduitRevendeurs(Set<ProduitRevendeur> produitRevendeurs) {
		this.produitRevendeurs = produitRevendeurs;
	}
*/
	public Societe getSociete() {
		return this.societe;
	}

	/**
	 * @return the produitRevendeurs
	 */
	
	public List<ProduitRevendeur> getProduitRevendeurs() {
		return produitRevendeurs;
	}


	/**
	 * @param produitRevendeurs the produitRevendeurs to set
	 */
	public void setProduitRevendeurs(List<ProduitRevendeur> produitRevendeurs) {
		this.produitRevendeurs = produitRevendeurs;
	}


	public void setSociete(Societe societe) {
		this.societe = societe;
	}
	
	public CompteBancaire getCompteBancaire() {
		return this.compteBancaire;
	}

	public void setCompteBancaire(CompteBancaire compteBancaire) {
		this.compteBancaire = compteBancaire;
	}
	
	public Revendeur getRevendeur() {
		return this.revendeur;
	}

	public void setRevendeur(Revendeur revendeur) {
		this.revendeur = revendeur;
	}
	
	public Set<Revendeur> getRevendeurs() {
		return this.revendeurs;
	}

	public void setRevendeurs(Set<Revendeur> revendeurs) {
		this.revendeurs = revendeurs;
	}
	
	public List<User> getUtilisateurs() {
		return this.utilisateurs;
	}

	public void setUtilisateurs(List<User> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}
	 @Override
	    public boolean equals(Object obj) {
	        if (this == obj) {
	            return true;
	        } else if (obj == null) {
	            return false;
	        } else if (!(obj instanceof Revendeur)) {
	            return false;
	        }
	        Revendeur other = (Revendeur)obj;
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