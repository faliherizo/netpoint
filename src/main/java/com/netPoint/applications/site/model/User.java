package com.netPoint.applications.site.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


/**
 * This is a persistent POJO class mapped to LOGIN database table
 * 
 * @author alabied
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "utilisateur")
public class User implements Serializable, Comparable<User> {
    
    
    static String getMd5Digest(String input)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1,messageDigest);
            return number.toString(16);
        }
        catch(NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);
        }
    }
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID_UTILISATEUR", nullable = false)
    private Integer id;

    @Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
    @ManyToOne
    @JoinColumn(name = "ID_PROFIL", nullable = false)
    private Profil profil;
    
    
    @Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
    @ManyToOne
    @JoinColumn(name = "ID_REVENDEUR")
    private Revendeur revendeur;
    
    @Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
    @ManyToOne
    @JoinColumn(name = "ID_CIVILITE")
    private Civilite civilite;
    @Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
    @ManyToOne
    @JoinColumn(name = "ID_ETAT_COMPTE")
    private EtatCompte etatCompte;
    
    @Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
    @ManyToOne
    @JoinColumn(name ="ID_LANGUE")
    private Langue langue;

    @Column(name="LOGIN")
    private String login;
    @Column(name="PASSWORD")
    private String pwd;
    @Column(name="NEWSLETTRE")
    private boolean newslettre;
    @Column(name="GROUPE")
    private Integer group;

	@Column(name="NOM")	
    private String nom;
    @Column(name="PRENOM")
    private String prenom;
    @Column(name="TELEPHONE")
    private String telephone;
    @Column(name="PORTABLE")
    private String portable;
    @Column(name="FONCTION")
    private String fonction;
    @Column(name="FAX")
    private String fax;
    @Temporal( TemporalType.DATE)
    @Column(name = "DATE_CREATION")
    private Date dateCreation;
    @Temporal( TemporalType.DATE)
    @Column(name = "DATE_FIN")
    private Date dateFin;
    @Column(name = "CREE_PAR")
    private String creePar;
    @Temporal( TemporalType.DATE)
    @Column(name = "MODIFIEE_LE")
    private Date modifieeLe;
    @Column(name = "MODIFIEE_PAR")
    private String modifieePar;
    @Transient
    private boolean changepwd;
    
	/**
	 * @return the group
	 */
	public Integer getGroup() {
		return group;
	}
	
	/**
	 * @param group the group to set
	 */
	public void setGroup(Integer group) {
		this.group = group;
	}
    /**
	 * @return the changepwd
	 */
	public boolean isChangepwd() {
		return changepwd;
	}

	/**
	 * @param changepwd the changepwd to set
	 */
	public void setChangepwd(boolean changepwd) {
		this.changepwd = changepwd;
	}

	/**
	 * @return the passwordNotEncoded
	 */
	@Transient
    private String PasswordNonEncoder;
	
	@Transient
	private String pwd2;
	@Transient
    private String dateFinChar;
	/**
	 * @return the dateFinChar
	 */
	public String getDateFinChar() {
		return dateFinChar;
	}

	/**
	 * @param dateFinChar the dateFinChar to set
	 */
	public void setDateFinChar(String dateFinChar) {
		this.dateFinChar = dateFinChar;
	}

	/**
	 * @return the pwd2
	 */
	public String getPwd2() {
		return pwd2;
	}

	/**
	 * @param pwd2 the pwd2 to set
	 */
	public void setPwd2(String pwd2) {
		this.pwd2 = pwd2;
	}
   public String getPasswordNonEncoder() {
		return PasswordNonEncoder;
	}

	public void setPasswordNonEncoder(String passwordNonEncoder) {
		PasswordNonEncoder = passwordNonEncoder;
	}

	/* public String getPwd2() {
        return pwd2;
    }

    public void setPwd2(String pwd2) {
        this.pwd2 = pwd2;
    }
*/
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getLogin() {
        return login;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }
    
    public String getPwd() {
        //return getMd5Digest(pwd);
        return pwd;
    }
    
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    
    public EtatCompte getEtatCompte() {
        return etatCompte;
    }
    
    public void setEtatCompte(EtatCompte etatCompte) {
        this.etatCompte = etatCompte;
    }
     public Revendeur getRevendeur() {
        return revendeur;
    }
    
    public void setRevendeur(Revendeur revendeur) {
        this.revendeur = revendeur;
    }
    public Profil getProfil() {
        return profil;
    }
    
    public void setProfil(Profil pProfil) {
        this.profil = pProfil;
    }
     public Civilite getCivilite() {
        return civilite;
    }
    
    public void setCivilite(Civilite civilite) {
        this.civilite = civilite;
    }
    
    public Langue getLangue() {
        return langue;
    }
    
    public void setLangue(Langue langue) {
        this.langue = langue;
    }
    

    public Date getDateCreation() {
        return dateCreation;
    }
    
    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }
    
    public Date getDateFin() {
        return dateFin;
    }
    
    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
    
   
    public String getNom() {
        return nom;
    }

    public void setNom(String nomUser) {
        this.nom = nomUser;
    }

    public String getPrenom() {
        return prenom;
    }
    
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public String getTelephone() {
        return telephone;
    }
    
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    public String getPortable() {
        return portable;
    }
    
    public void setPortable(String portable) {
        this.portable = portable;
    }
    
    public String getFonction() {
        return fonction;
    }
    
    public void setFonction(String fonction) {
        this.fonction = fonction;
    }
    
    public String getFax() {
        return fax;
    }
    
    public void setFax(String fax) {
        this.fax = fax;
    }
    
   
    
    public int compareTo(User obj) {
        return obj.getLogin().compareTo(this.getLogin());
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (!(obj instanceof User)) {
            return false;
        }
        User other = (User)obj;
        return new EqualsBuilder().append(this.getId(), other.getId()).isEquals();
    }
    
    @Override
    public int hashCode() {
    	if(id==null){
			return super.hashCode();
		}else{
			return new HashCodeBuilder().append(this.getId()).toHashCode();
        }
    }
    
    @Override
    public String toString() {
        return new ReflectionToStringBuilder(this).toString();
    }
	public String getCreePar() {
		return creePar;
	}

	public void setCreePar(String creePar) {
		this.creePar = creePar;
	}

	public Date getModifieeLe() {
		return modifieeLe;
	}

	public void setModifieeLe(Date modifieeLe) {
		this.modifieeLe = modifieeLe;
	}

	public String getModifieePar() {
		return modifieePar;
	}

	public void setModifieePar(String modifieePar) {
		this.modifieePar = modifieePar;
	}
	public boolean isNewslettre() {
			return newslettre;
	}

		public void setNewslettre(boolean newslettre) {
			this.newslettre = newslettre;
	}



		/*@Column(name="enable")
	    private boolean enable;
	    
		@OneToMany(mappedBy = "utilisateur", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
		private Collection<Profil> roles;


		public Collection<Profil> getRoles() {
			return roles;
		}


		public void setRoles(Collection<Profil> roles) {
			this.roles = roles;
		}


		public boolean isEnable() {
			return enable;
		}
	    
	   
		public void setEnable(boolean enable) {
			this.enable = enable;
		}
	*/
}
