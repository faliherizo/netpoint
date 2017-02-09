package com.netPoint.applications.site.model;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * The persistent class for the commande database table.
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name="commande")
public class Commande implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Transient
	private static final long MILLISECONDS_PER_DAY = 1000 * 60 * 60 * 24;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_COMMANDE", unique=true, nullable=false)
	private Integer id;

	@Column(name="nombreposte", nullable=true, length=256)
	private Integer nombreposte;

  
	@Temporal(TemporalType.DATE)
	@Column(name="CREE_LE", nullable=false)
	private Date creeLe;

	@Column(name="CREE_PAR", nullable=false, length=256)
	private String creePar;

    @Temporal(TemporalType.DATE)
	@Column(name="DATE_COMMANDE")
	private Date dateCommande;

    @Temporal( TemporalType.DATE)
	@Column(name="DATE_DEBUT")
	private Date dateDebut;

    @Temporal( TemporalType.DATE)
	@Column(name="DATE_FIN")
	private Date dateFin;

    @Temporal( TemporalType.DATE)
	@Column(name="MODIFIEE_LE")
	private Date modifieeLe;

	@Column(name="MODIFIEE_PAR", length=256)
	private String modifieePar;

	@Column(name="MONTANT", precision=10)
	private float montant;

	@Column(name="NUMERO_COMMANDE", length=32,nullable=true)
	private String numeroCommande;

	@Column(name="QUANTITE")
	private int quantite;
	@Column(name="NUMERO_REVENDEUR",nullable=true)
	private String numeroRevendeur;
	
	@Column(name="numerocommande" ,nullable=true)
	private Integer numerocommande;
	
	@Column(name="prixproduit")
	private float prixproduit;
	@Transient
	private float tauxremise;
	@Transient
	private float prixsansremise;
	@Transient
	private float totalremise;
	
	/**
	 * @return the totalremise
	 */
	public float getTotalremise() {
		return totalremise;
	}


	/**
	 * @param totalremise the totalremise to set
	 */
	public void setTotalremise(float totalremise) {
		this.totalremise = totalremise;
	}


	/**
	 * @return the prixsansremise
	 */
	public float getPrixsansremise() {
		return prixsansremise;
	}


	/**
	 * @param prixsansremise the prixsansremise to set
	 */
	public void setPrixsansremise(float prixsansremise) {
		this.prixsansremise = prixsansremise;
	}

	//bi-directional many-to-one association to EtatCommande
	@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
	@ManyToOne
	@JoinColumn(name="ID_TYPE_COMMANDE", nullable=false)
	private TypeCommande typeCommande;
	@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
    @ManyToOne
	@JoinColumn(name="ID_ETAT_COMMANDE")
	private EtatCommande etatCommande;

	//bi-directional many-to-one association to DureeCommande
    @Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
    @ManyToOne
	@JoinColumn(name="ID_DUREE_COMMANDE", nullable=true)
	private DureeCommande dureeCommande;

	//bi-directional many-to-one association to Revendeur
    @Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
    @ManyToOne
	@JoinColumn(name="ID_REVENDEUR", nullable=false)
	private Revendeur revendeur;

	//bi-directional many-to-one association to Produit
    @Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
    @ManyToOne
	@JoinColumn(name="ID_PRODUIT", nullable=false)
	private Produit produit;
   /* @Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
    @ManyToOne
	@JoinColumn(name="ID_PRIX", nullable=true)
	private Prix prix;
    */
    
	/**
	 * @return the prix
	 */
	/*public Prix getPrix() {
		return prix;
	}


	/**
	 * @param prix the prix to set
	 */
	/*public void setPrix(Prix prix) {
		this.prix = prix;
	}
*/
	//bi-directional many-to-one association to Client
    @ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ID_CLIENT", nullable=false)
	private Client client;
   
    public float getTauxremise() {
		return tauxremise;
	}


	public void setTauxremise(float tauxremise) {
		this.tauxremise = tauxremise;
	}


	/**
	 * @return the numerocommande
	 */
	public Integer getNumerocommande() {
		return numerocommande;
	}


	public float getPrixproduit() {
		return prixproduit;
	}


	public void setPrixproduit(float prixproduit) {
		this.prixproduit = prixproduit;
	}


	public void setNombreDuJour(long nombreDuJour) {
		this.nombreDuJour = nombreDuJour;
	}


	/**
	 * @param numerocommande the numerocommande to set
	 */
	public void setNumerocommande(Integer numerocommande) {
		this.numerocommande = numerocommande;
	}
 
    /**
	 * @return the numeroRevendeur
	 */
	public String getNumeroRevendeur() {
		return numeroRevendeur;
	}


	/**
	 * @param numeroRevendeur the numeroRevendeur to set
	 */
	public void setNumeroRevendeur(String numeroRevendeur) {
		this.numeroRevendeur = numeroRevendeur;
	}

    /**
	 * @return the typeCommande
	 */
	public TypeCommande getTypeCommande() {
		return typeCommande;
	}


	/**
	 * @param typeCommande the typeCommande to set
	 */
	public void setTypeCommande(TypeCommande typeCommande) {
		this.typeCommande = typeCommande;
	}


	/**
  	 * @return the nombreposte
  	 */
  	public Integer getNombreposte() {
  		return nombreposte;
  	}


  	/**
  	 * @param nombreposte the nombreposte to set
  	 */
  	public void setNombreposte(Integer nombreposte) {
  		this.nombreposte = nombreposte;
  	}

    @Transient
    private long nombreDuJour;
    
    @Transient
    private String dateToStringEcheance;

    /**
	 * @return the nombreDuJour
	 */
	public long getNombreDuJour() {
		return returnNombreDuJour(this.dateFin);
	}


	/**
	 * @return the dateToStringEcheance
	 */
	public String getDateToStringEcheance() {
		return dateToString(this.getDateFin());
	}


	public Commande() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreeLe() {
		return this.creeLe;
	}

	public void setCreeLe(Date creeLe) {
		this.creeLe = creeLe;
	}

	public String getCreePar() {
		return this.creePar;
	}

	public void setCreePar(String creePar) {
		this.creePar = creePar;
	}

	public Date getDateCommande() {
		return this.dateCommande;
	}

	public void setDateCommande(Date dateCommande) {
		this.dateCommande = dateCommande;
	}

	public Date getDateDebut() {
		return this.dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return this.dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public Date getModifieeLe() {
		return this.modifieeLe;
	}

	public void setModifieeLe(Date modifieeLe) {
		this.modifieeLe = modifieeLe;
	}

	public String getModifieePar() {
		return this.modifieePar;
	}

	public void setModifieePar(String modifieePar) {
		this.modifieePar = modifieePar;
	}

	public float getMontant() {
		return this.montant;
	}

	public void setMontant(float f) {
		this.montant = f;
	}

	public String getNumeroCommande() {
		return this.numeroCommande;
	}

	public void setNumeroCommande(String numeroCommande) {
		this.numeroCommande = numeroCommande;
	}

	public int getQuantite() {
		return this.quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public EtatCommande getEtatCommande() {
		return this.etatCommande;
	}

	public void setEtatCommande(EtatCommande etatCommande) {
		this.etatCommande = etatCommande;
	}
	
	public DureeCommande getDureeCommande() {
		return this.dureeCommande;
	}

	public void setDureeCommande(DureeCommande dureeCommande) {
		this.dureeCommande = dureeCommande;
	}
	
	public Revendeur getRevendeur() {
		return this.revendeur;
	}

	public void setRevendeur(Revendeur revendeur) {
		this.revendeur = revendeur;
	}
	
	public Produit getProduit() {
		return this.produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	
	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	private long returnNombreDuJour(Date dateDiff){
		Date dateCurrent = new Date();
		long result = 0;
		
		if(dateDiff.compareTo(dateCurrent)==1){
			result = dateDiff.getTime() - dateCurrent.getTime();
		}
		return result / (MILLISECONDS_PER_DAY);
	}
	
	private long returnNombreDuJourEcouler(Date dateDiff){
		Date dateCurrent = new Date();
		long result = 0;
		
		if(dateDiff.compareTo(dateCurrent)==-1){
			result = dateCurrent.getTime()-dateDiff.getTime(); 
		}
		return result / (MILLISECONDS_PER_DAY);
	}
	
	/**
	 * Convertit Date to String pour l'affichager des dates
	 * 
	 * @param Date
	 * @return String
	 */
	private String dateToString(final Date date){
		String dateFormatee = "";
		if(date!=null){
			SimpleDateFormat formatDateJour = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			dateFormatee= formatDateJour.format(date);
		}
		return dateFormatee;
	}

	
}