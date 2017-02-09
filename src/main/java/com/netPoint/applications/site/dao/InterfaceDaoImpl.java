package com.netPoint.applications.site.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.netPoint.applications.site.dto.view.ViewRenouvellementDuMois;
import com.netPoint.applications.site.model.Civilite;
import com.netPoint.applications.site.model.Client;
import com.netPoint.applications.site.model.Commande;
import com.netPoint.applications.site.model.CompteBancaire;
import com.netPoint.applications.site.model.Devise;
import com.netPoint.applications.site.model.EtatCommande;
import com.netPoint.applications.site.model.EtatCompte;
import com.netPoint.applications.site.model.Fournisseur;
import com.netPoint.applications.site.model.Langue;
import com.netPoint.applications.site.model.MailConfig;
import com.netPoint.applications.site.model.MailHdr;
import com.netPoint.applications.site.model.ModeReglement;
import com.netPoint.applications.site.model.Pays;
import com.netPoint.applications.site.model.Produit;
import com.netPoint.applications.site.model.ProduitRevendeur;
import com.netPoint.applications.site.model.ProduitRevendeurId;
import com.netPoint.applications.site.model.Profil;
import com.netPoint.applications.site.model.Revendeur;
import com.netPoint.applications.site.model.Societe;
import com.netPoint.applications.site.model.TypeCommande;
import com.netPoint.applications.site.model.User;
import com.netPoint.applications.site.service.notify.Notifier;
import com.netPoint.applications.site.util.Parameters;

@Repository
public class InterfaceDaoImpl implements InterfaceDao {
    
    public static final Logger logger = LoggerFactory.getLogger(InterfaceDaoImpl.class);
    private EntityManager entityManager;
	@Autowired
	private Notifier notifier;

	/**
	 * @param notifier the notifier to set
	 */
	public void setNotifier(Notifier notifier) {
		this.notifier = notifier;
	}

	@PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<EtatCommande> findAllEtatCommande() {
        List<EtatCommande> etats = new ArrayList<EtatCommande>();
        try {
            String s = "select b from EtatCommande b order by b.libelle";
            Query query = entityManager.createQuery(s);
            query.setHint("org.hibernate.cacheable", false);
            etats = query.getResultList();
        } catch (Exception e) {
            logger.error("Error while loading EtatCommande", e);
        }
        return etats;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<EtatCompte> findAllEtatCompte() {
        List<EtatCompte> etats = new ArrayList<EtatCompte>();
        try {
            String s = "select b from EtatCompte b order by b.libelle";
            Query query = entityManager.createQuery(s);
            query.setHint("org.hibernate.cacheable", false);
            etats = query.getResultList();
        } catch (Exception e) {
            logger.error("Error while loading EtatCompte", e);
        }
        return etats;
    }
    
    @SuppressWarnings("unchecked")
    public List<Civilite> findAllCivilite() {
        List<Civilite> civilites = new ArrayList<Civilite>();
        try {
            String s = "select b from Civilite b order by b.libelle";
            Query query = entityManager.createQuery(s);
            query.setHint("org.hibernate.cacheable", false);
            civilites = query.getResultList();
            logger.debug("civilites.size()" + civilites.size());
        } catch (Exception e) {
            logger.error("Error while loading Civilite", e);
        }
        return civilites;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Profil> findAllDroit() {
        List<Profil> _listdroits = new ArrayList<Profil>();
        try {
            String s = "select b from Profil b order by b.libelle ";
            Query query = entityManager.createQuery(s);
            query.setHint("org.hibernate.cacheable", false);
            _listdroits = query.getResultList();
            logger.debug("droits.size()" + _listdroits.size());
        } catch (Exception e) {
            logger.error("Error while loading Profil", e);
        }
        return _listdroits;
    }
    @Override
    @SuppressWarnings("unchecked")
    public List<Profil> findAllDroitUser() {
        List<Profil> _listdroits = new ArrayList<Profil>();
        try {
            String s = "select b from Profil b where b.code !="+"'SUP'"+" and b.libelle !="+"'Client'"+" and b.libelle !="+"'Revendeur'";
           // if(Parameters.group!=null)
            //	s +=" and b.libelle !="+"'Administrateur'";
            s +=" order by b.libelle ";
            Query query = entityManager.createQuery(s);
            query.setHint("org.hibernate.cacheable", false);
            _listdroits = query.getResultList();
            logger.debug("droits.size()" + _listdroits.size());
        } catch (Exception e) {
            logger.error("Error while loading Profil", e);
        }
        return _listdroits;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Langue> findAllLangue() {
        List<Langue> langues = new ArrayList<Langue>();
        try {
            String s = "select b from Langue b order by b.libelle";
            Query query = entityManager.createQuery(s);
            query.setHint("org.hibernate.cacheable", false);
            langues = query.getResultList();
        } catch (Exception e) {
            logger.error("Error while loading Langues", e);
        }
        return langues;
    }
    
    public User saveLogin(User login) {
        try {
           return entityManager.merge(login);
        } catch (Exception e) {
        	
            logger.error("Error while saving Login with id login " + login.getId(), e);
            return null;
        }
    }
    public void UpdateUser(User login) {
        try {
            entityManager.merge(login);
        } catch (Exception e) {
            logger.error("Error while update Login with id login " + login.getId(), e);
        }
    }
    
    /* (non-Javadoc)
     * @see com.netPoint.applications.site.dao.InterfaceDao#findAllUser()
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<User> findAllUser() {
        List<User> users = new ArrayList<User>();
        try {
            String s = "select b from User b where b.profil.libelle!="+"'Revendeur'";
            if(Parameters.group!=null)
            	s += " and b.group =:group";
            Query query = entityManager.createQuery(s);
            if(Parameters.group!=null)
            	query.setParameter("group", Parameters.group);
            query.setHint("org.hibernate.cacheable", false);
            users = query.getResultList();
        } catch (Exception e) {
            logger.error("Error while loading User", e);
        }
        return users;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Client> findAllClient() {
        List<Client> clients = new ArrayList<Client>();
        try {
            String s = "select b from Client b";
            if(Parameters.group!=null)
            	s +=" where b.user.group= :group";
            s +=" order by b.codeClient";
            Query query = entityManager.createQuery(s);
            if(Parameters.group!=null)
            	query.setParameter("group", Parameters.group);
            query.setHint("org.hibernate.cacheable", false);
            clients = query.getResultList();
        } catch (Exception e) {
            logger.error("Error while loading Client", e);
        }
        return clients;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Commande> findAllCommande() {
        List<Commande> commandes = new ArrayList<Commande>();
        try {
            String s = "select b from Commande b";
            Query query = entityManager.createQuery(s);
            query.setHint("org.hibernate.cacheable", false);
            commandes = query.getResultList();
        } catch (Exception e) {
            logger.error("Error while loading commandes", e);
        }
        return commandes;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Revendeur> findAllRevendeur() {
        List<Revendeur> revendeurs = new ArrayList<Revendeur>();
        try {
            String s = "select b from Revendeur b";
            if(Parameters.group!=null)
            	s+=" where b.user.group= :group";
            Query query = entityManager.createQuery(s);
            if(Parameters.group!=null)
            	query.setParameter("group", Parameters.group);
            query.setHint("org.hibernate.cacheable", false);
            revendeurs = query.getResultList();
        } catch (Exception e) {
            logger.error("Error while loading revendeurs", e);
        }
        return revendeurs;
    }
    
    @Override
    public Client findClientById(long idClient) {
        Client client;
        try {
            String s = "select b from Client b where b.id= :idClient";
            Query query = entityManager.createQuery(s);
            query.setParameter("idClient", idClient);
            query.setHint("org.hibernate.cacheable", false);
            client = (Client)query.getSingleResult();
        } catch (Exception e) {
            logger.error("Error while loading Client with idClient " + idClient, e);
            client = null;
        }
        return client;
    }
    
    @Override
    public Client findClientByName(String codeClient) {
        Client client;
        try {
            String s = "select b from Client b where b.codeClient = :codeClient";
            Query query = entityManager.createQuery(s);
            query.setParameter("codeClient", codeClient);
            query.setHint("org.hibernate.cacheable", false);
            client = (Client)query.getSingleResult();
        } catch (Exception e) {
            logger.error("Error while loading Client with clientName " + codeClient, e);
            client = null;
        }
        return client;
    }
    
    @Override
    public List<Commande> findCommandesByClient(Client client) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Revendeur findRevendeurById(long id) {
    	logger.debug("getting Revendeur instance with id: " + id);
		try {
			Revendeur instance = entityManager.find(Revendeur.class, id);
			logger.debug("findById successful");
			return instance;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			return null;
		}
    }
    
    @Override
    public Revendeur findRevendeurByName(String name) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public void saveOrUpdateClient(Client client) {
    	logger.debug("save client");
    	 try {
             entityManager.merge(client);
         } catch (Exception e) {
             logger.error("Error while saving Login with id login " + client.getId(), e);
         }
        
    }
    
    @Override
    public void saveCommande(Commande commande) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void saveRevendeur(Revendeur revendeur) {
    	logger.debug("save Revendeur");
   	 try {
            entityManager.merge(revendeur);
        } catch (Exception e) {
            logger.error("Error while saving Login with id login " + revendeur.getId(), e);
        }
        
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        return super.clone();
    }
    
    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        return super.equals(obj);
    }
    
    @Override
    protected void finalize() throws Throwable {
        // TODO Auto-generated method stub
        super.finalize();
    }
    
    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return super.hashCode();
    }
    
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }

    @Override
    @SuppressWarnings("unchecked")
	public void remove(Integer persistanceId) {
		// TODO Auto-generated method stub
		
	}

    @Override
    @SuppressWarnings("unchecked")
	public void removeUser(User persistentUser) {
    	logger.debug("removing User instance");
		try {
			
			entityManager.remove(entityManager.merge(persistentUser));
			logger.debug("remove User successful");
		} catch (RuntimeException re) {
			logger.error("remove User failed", re);
		}
		
	}

    
    @Override
	@SuppressWarnings("unchecked")
	public void removeCommande(Commande persistentCommande) {
		logger.debug("removing Commande instance");
		try {
			entityManager.remove(persistentCommande);
			logger.debug("remove Commande successful");
		} catch (RuntimeException re) {
			logger.error("remove Commande failed", re);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public void removeSociete(Societe persistentSociete) {
		logger.debug("removing Societe instance");
		try {
			entityManager.remove(persistentSociete);
			logger.debug("remove Societe successful");
		} catch (RuntimeException re) {
			logger.error("remove Societe failed", re);
		}
	}

	@Override
	public User SaveOrUpdateUser(User detachedUser) {
		logger.debug("merging User instance");
		try {
			User _result = entityManager.merge(detachedUser);
			logger.debug("merge user successful");
			return _result;
		} catch (RuntimeException re) {
			logger.error("merge user failed", re);
			return null;
		}
	}


	@Override
	public void removeUser(Integer userId) {
		logger.debug("merging User instance");
		try {
			User user=this.findByIdUser(userId);
			this.removeUser(user);
			logger.debug("delete user successful");
		}
		catch (RuntimeException re) {
			logger.debug("error delete user");
		}
	}
	@Override
	public void removeFrns(Fournisseur fournisseur){
		try {
			entityManager.remove(fournisseur);
			
		} catch (Exception e) {
			logger.debug("error delete frns", e.getMessage());
		}
		
	}
	@Override
	public Fournisseur getFrnst(User user) {
		logger.debug("getting frns list");
		Fournisseur _frns= null;
		try {
			String s = "select b from Fournisseur b where b.user =:user";				
			Query query = entityManager.createQuery(s);
			query.setParameter("user", user);
            query.setHint("org.hibernate.cacheable", false);
            _frns= (Fournisseur) query.getSingleResult();  	           
            return _frns;
		}
		catch (Exception e) {
			logger.error("Error while loading revendeur failed coonecté ", e);
            return null;
		}
	}
	@Override
	public User findByIdUser(Integer id) {
		logger.debug("getting User instance with id: " + id);
		try {
			User instance = entityManager.find(User.class, id);
			logger.debug("findById successful");
			return instance;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			return null;
		}
	}
	@Override
    @SuppressWarnings("unchecked")
	public void removeRevendeur(Revendeur persistentRevendeur) {
    	logger.debug("removing Revendeur instance");
		try {
			
			try {
				entityManager.remove(persistentRevendeur);
			//	Revendeur rev= findRevendeurById(id)
			} catch (Exception e) {
				logger.debug("remove revendeur failed");
			}
			
			
			logger.debug("remove Revendeur successful");
		} catch (RuntimeException re) {
			logger.error("remove Revendeur failed", re);
		}
		
	}

	@Override
	public void removeRevendeur(Integer revendeurId) {
		try {
			Revendeur rev = findRevendeurById(revendeurId);
			CompteBancaire comptebk = rev.getCompteBancaire();
			if(comptebk!=null)
				logger.debug("CompteBancaire non null"+ comptebk.getId());
			else
				logger.debug("CompteBancaire null");
			this.removeRevendeur(rev);
			Revendeur refverifdellete = findRevendeurById(revendeurId);
			if(refverifdellete!=null)
				logger.debug("revendeur non supprimer");
		} catch (Exception e) {
			logger.error("Remove revendeur failed", e);
		}
		
	}
	@Override
	public Revendeur SaveOrUpdateRevendeur(Revendeur detachedRevendeur){
		detachedRevendeur=entityManager.merge(detachedRevendeur);
		return detachedRevendeur;
		
	}
	@Override
	public Revendeur SaveOrUpdateRevendeur(Revendeur detachedRevendeur, List<ProduitRevendeur> produitRevendeurs) {
		Revendeur _result = null;
		String[] _mailcronsujet={"Comfirmation de commande", "Relance 30jours avant l'expiration de commande",
				"Relance 15jours avant l'expiration de commande", "Relance 2jours avant l'expiration de commande", "Confirmation essaie",
				"Relance 15jours avant l'expiration de l'essaie", "Relance 2jours avant l'expiration de l'essaie", 
				"Liste des renouvellement 30 jours avant l'expiration","Liste des renouvellement 60jours avant l'expiration"} ;
		logger.debug("merging Revendeur instance");
		try {
			MailHdr _mail= new MailHdr();
			_mail.setCode(detachedRevendeur.getUser().getNom());
			_mail = entityManager.merge(_mail);
			//_mail.setMaildtl(maildtl);
			
			Date date =new Date();
			detachedRevendeur.setMailhdr(_mail);
			_result = entityManager.merge(detachedRevendeur);
			List<MailConfig> _mailConfigs = new ArrayList<MailConfig>();
			for (int i = 0; i < 9; i++) {
				MailConfig _mailconf = new MailConfig();
				_mailconf.setCodemail("m"+i);
				_mailconf.setSubject(_mailcronsujet[i]);
				_mailconf.setMailhdr(_mail);
				_mailconf.setActive(false);
				_mailconf.setSociete(detachedRevendeur.getSociete());
				_mailconf=entityManager.merge(_mailconf);
				_mailConfigs.add(_mailconf);
			}
			_mail.setMaildtl(_mailConfigs);
			User user = _result.getUser();
			//user.setRevendeur(_result);
			user.setDateCreation(date);
			user.setGroup(Parameters.getGroup());
			entityManager.merge(user);
			for (ProduitRevendeur produitRevendeur : produitRevendeurs) {
				if(produitRevendeur.getProduit().getId()!=null){
					ProduitRevendeurId id=new ProduitRevendeurId();
					id.setIdProduit(produitRevendeur.getProduit().getId());
					id.setIdRevendeur(_result.getId());
					produitRevendeur.setId(id);
					produitRevendeur.setRevendeur(_result);
					entityManager.merge(produitRevendeur);
				
				}
				logger.debug("merge ProduitRevendeur successful"+ produitRevendeur.getProduit().getId() );
			}
			logger.debug("merge Revendeur successful  id  "+ _result.getId() + "sizelist " +detachedRevendeur.getProduitRevendeurs().size());
			return _result;
		} catch (RuntimeException re) {
			logger.error("merge Revendeur failed", re);
			return null;
		}
	}

	@Override
	public List<User> findAllUserByProfilMailEtat(Integer pidprofil,
			String mail, Integer pidetatcompte) {
		List<User> _userList = new ArrayList<User>();
		Profil _profil=null;
		 EtatCompte _etatcompte=null;
		logger.debug("Search user by droit,email, etat:" + mail);
		try {
			 String s = "select b from User b where  b.profil.code!='REV' and b.profil.code!='SUP' and b.profil.code!='CLT'";
			 if(mail!=null && StringUtils.hasLength(mail) )	
			 s+=" and (b.login LIKE '%"+mail+"%') " ;
			 if(pidetatcompte!=null){
				 _etatcompte= findByEtatIdEtatCompte(pidetatcompte);
				 s+=" and (b.etatCompte =:etatCompte) " ;
			 }
			 if(pidprofil!=null){
				 _profil = findProfilByidProfil(pidprofil);
				 s+=" and (b.profil =:profil)";
			 }
			 if(Parameters.group!=null){
				 s+=" and b.group =:group";
			 }
	          Query query = entityManager.createQuery(s);
	          //  query.setParameter("mail", mail);
	          if(Parameters.group!=null){
	        	  query.setParameter("group", Parameters.group);
	          }
	          	if(_profil!=null)
	          		query.setParameter("profil", _profil);
	            if(pidetatcompte!=null)
	            	query.setParameter("etatCompte", _etatcompte);
	            query.setHint("org.hibernate.cacheable", false);
	            _userList = (List<User>) query.getResultList();
	            return _userList;
		} catch (RuntimeException re) {
			logger.error("find user by mail, profil, etat failed", re);
			return new ArrayList<User>();
		}

	}
	@Override
	public Profil findProfilByidProfil(Integer pidprofil) {
		logger.debug("getting Profil instance with id: " + pidprofil);
		try {
			Profil instance = entityManager.find(Profil.class, pidprofil);
			logger.debug("find profil ById successful");
			return instance;
		} catch (RuntimeException re) {
			logger.error("findById profil failed", re);
			return null;
		}

	}
	@Override
	public EtatCompte findByEtatIdEtatCompte(Integer pidetatcompte) {
		logger.debug("getting EtatCompte instance with id: " + pidetatcompte);
		try {
			EtatCompte instance = entityManager.find(EtatCompte.class, pidetatcompte);
			logger.debug("find EtatCompte ById successful");
			return instance;
		} catch (RuntimeException re) {
			logger.error("find ById EtatCompte failed", re);
			return null;
		}

	}
	@Override
	public List<User> findAllUserByProfil(Integer pidprofil) {
		List<User> _userList = new ArrayList<User>();
		Profil _profil = findProfilByidProfil(pidprofil);
		logger.debug("Search user by droit:" + _profil.getLibelle());
		try {
			 String s = "select b from User b where b.profil =:profil order by b.login";
			 if(Parameters.group!=null){
				 s+=" and b.group =:group";
			 }
	          Query query = entityManager.createQuery(s);
	          if(Parameters.group!=null){
	        	  query.setParameter("group", Parameters.group);
	          }
	            query.setParameter("profil", _profil);
	            query.setHint("org.hibernate.cacheable", false);
	            _userList = (List<User>) query.getResultList();
	            return _userList;
		} catch (RuntimeException re) {
			logger.error("find user by profil failed", re);
			return new ArrayList<User>();
		}
	}
	@Override
	public List<User> findAllUserByMail(String mail) {
		List<User> _userList = new ArrayList<User>();
		logger.debug("Search user by email:" );
		try {
			 String s = "select b from User b where b.login LIKE '%"+mail+"%' and b.profil.code!='REV' and b.profil.code!='SUP' and b.profil.code!='CLT'";
			 if(Parameters.group!=null){
				 s+=" and b.group =:group";
			 }
			 s +=" order by b.login";
			 Query query = entityManager.createQuery(s);
	          if(Parameters.group!=null){
	        	  query.setParameter("group", Parameters.group);
	          }
	            query.setHint("org.hibernate.cacheable", false);
	            _userList = (List<User>) query.getResultList();
	            return _userList;
		} catch (RuntimeException re) {
			logger.error("find user by mail failed", re);
			return null;
		}
	}

	@Override
	public List<User> findAllUserByEtat(Integer pidetatcompte) {
		List<User> _userList = new ArrayList<User>();
		EtatCompte etatCompte = findByEtatIdEtatCompte(pidetatcompte);
		logger.debug("Search user by droit:" + etatCompte.getLibelle());
		try {
			 String s = "select b from User b where b.etatCompte =:etatCompte AND b.profil.libelle!="+"'Revendeur'";
			 if(Parameters.group!=null){
				 s+=" and b.group =:group";
			 }
			 s +=" order by b.login";
	          Query query = entityManager.createQuery(s);
	          if(Parameters.group!=null){
	        	  query.setParameter("group", Parameters.group);
	          }
	            query.setParameter("etatCompte", etatCompte);
	            query.setHint("org.hibernate.cacheable", false);
	            _userList = (List<User>) query.getResultList();
	            return _userList;
		} catch (RuntimeException re) {
			logger.error("find user by etatCompte failed", re);
			return new ArrayList<User>();
		}
	}

	@Override
	public List<User> findAllUserByMailEtat(String mail, Integer pidetatcompte) {
			List<User> _userList = new ArrayList<User>();
			EtatCompte _etatcompte= findByEtatIdEtatCompte(pidetatcompte);
			logger.debug("Search user by email, etat:" + mail);
			try {
				 String s = "select b from User b where (b.login LIKE '%"+mail+"%') and (b.etatCompte =:etatCompte) AND b.profil.libelle!="+"'Revendeur'";
				 if(Parameters.group!=null){
					 s+=" and b.group =:group";
				 }
				 s+=" order by b.login";
				 Query query = entityManager.createQuery(s);
		          if(Parameters.group!=null){
		        	  query.setParameter("group", Parameters.group);
		          }
		            query.setParameter("etatCompte", _etatcompte);
		            query.setHint("org.hibernate.cacheable", false);
		            _userList = (List<User>) query.getResultList();
		            return _userList;
			} catch (RuntimeException re) {
				logger.error("find user by mail, etat failed", re);
				return new ArrayList<User>();
			}
	}

	@Override
	public List<User> findAllUserByProfilMail(Integer pidprofil, String mail) {
			List<User> _userList = new ArrayList<User>();
			Profil _profil = findProfilByidProfil(pidprofil);
			logger.debug("Search user by droit,email:" + mail);
			try {
				 String s = "select b from User b where b.login LIKE '%"+mail+"%' and (b.profil =:profil)";
				 if(Parameters.group!=null){
					 s+=" and b.group =:group";
				 } 
				 s +=" order by b.login";
				 Query query = entityManager.createQuery(s);
				 if(Parameters.group!=null){
		        	  query.setParameter("group", Parameters.group);
		          }
		            query.setParameter("profil", _profil);
		            
		            query.setHint("org.hibernate.cacheable", false);
		            _userList = (List<User>) query.getResultList();
		            return _userList;
			} catch (RuntimeException re) {
				logger.error("find user by mail, profil  failed", re);
				return new ArrayList<User>();
			}
	}

	@Override
	public List<User> findAllUserByProfilEtat(Integer pidprofil,
			Integer pidetatcompte) {
			List<User> _userList = new ArrayList<User>();
			EtatCompte _etatcompte= findByEtatIdEtatCompte(pidetatcompte);
			Profil _profil = findProfilByidProfil(pidprofil);
			logger.debug("Search user by droit, etat");
			try {
				 String s = "select b from User b where (b.etatCompte =:etatCompte) and (b.profil =:profil) order by b.login";
				 if(Parameters.group!=null){
					 s+=" and b.group =:group";
				 } 
		          Query query = entityManager.createQuery(s);
		          if(Parameters.group!=null){
		        	  query.setParameter("group", Parameters.group);
		          }
		            query.setParameter("profil", _profil);
		            query.setParameter("etatCompte", _etatcompte);
		            query.setHint("org.hibernate.cacheable", false);
		            _userList = (List<User>) query.getResultList();
		            return _userList;
			} catch (RuntimeException re) {
				logger.error("find user by  profil, etat failed", re);
				return new ArrayList<User>();
			}
	}
	
	//modif mija
			@Override
		public List<Pays> findAllPays() {
			List<Pays> pays = new ArrayList<Pays>();
	        try {
	            String s = "select b from Pays b order by b.libelle";
	            Query query = entityManager.createQuery(s);
	            query.setHint("org.hibernate.cacheable", false);
	            pays = query.getResultList();
	        } catch (Exception e) {
	            logger.error("Error while loading revendeurs", e);
	        }
	        return pays;
		}

		@Override
		public List<ModeReglement> findAllModeReglement() {
			List<ModeReglement> mode = new ArrayList<ModeReglement>();
	        try {
	            String s = "select b from ModeReglement b order by b.libelle";
	            Query query = entityManager.createQuery(s);
	            query.setHint("org.hibernate.cacheable", false);
	            mode = query.getResultList();
	        } catch (Exception e) {
	            logger.error("Error while loading revendeurs", e);
	        }
	        return mode;
		}
	
		@Override
		public List<Devise> findAllDevise() {
			// TODO Auto-generated method stub
			List<Devise> dev = new ArrayList<Devise>();
	        try {
	            String s = "select b from Devise b order by b.libelle";
	            //select b from Profil b order by b.libelle
	            Query query = entityManager.createQuery(s);
	            query.setHint("org.hibernate.cacheable", false);
	            dev = query.getResultList();
	        } catch (Exception e) {
	            logger.error("Error while loading revendeurs", e);
	        }
	        return dev;
		}

		@Override
		public User FindUserByUserName(String username) {
			User _user = new User();
			logger.debug("Search user by email:" );
			try {
				 String s = "select b from User b where b.login LIKE '%"+username+"%'";
				 if(Parameters.group!=null){
					 s+=" and b.group =:group";
				 } 
				 s += " order by b.login";
		          Query query = entityManager.createQuery(s);
		          if(Parameters.group!=null){
		        	  query.setParameter("group", Parameters.group);
		          }
		            query.setHint("org.hibernate.cacheable", false);
		            _user =  (User) query.getSingleResult();
		            return _user;
			} catch (RuntimeException re) {
				logger.error("find user by mail failed", re);
				return  null;
			}
			
		}

		@Override
		public User findUserByMail(String mail) {
			User _user = null;
			logger.debug("Search user by email:" );
			try {
				 String s = "select b from User b where b.login LIKE '%"+mail+"%'"; 
		          Query query = entityManager.createQuery(s);
		            query.setHint("org.hibernate.cacheable", false);
		            _user = (User) query.getSingleResult();
		            if(_user.getId()!=null)
		            	return _user;
		            else
		            	return null;
			} catch (RuntimeException re) {
				logger.error("find user by mail failed", re);
				return null;
			}
		}
		@Override
		public List<Revendeur> findAllRevendeurByMail(String mail) {
			List<Revendeur> _listRevendeur= new ArrayList<Revendeur>();
			logger.debug("Search Revendeur by user email:" );
			try {
				String s = "select b from Revendeur b where b.user.login LIKE '%"+mail+"%'";
				 if(Parameters.group!=null){
					 s+=" and b.user.group =:group";
				 } 
		          Query query = entityManager.createQuery(s);
		          if(Parameters.group!=null){
		        	  query.setParameter("group", Parameters.group);
		          }
		            query.setHint("org.hibernate.cacheable", false);
		            _listRevendeur = (List<Revendeur> )query.getResultList();
		            return _listRevendeur;
			} catch (RuntimeException re) {
				logger.error("find revendeur by user mail failed", re);
				return new ArrayList<Revendeur>();
			}
		}
		///Mija
		@Override
		public List<Revendeur> findAllRevendeurSociete(Integer idSociete,
				Integer pidetatcompte) {
			List<Revendeur> revendList = new ArrayList<Revendeur>();
			EtatCompte _etatcompte= findByEtatIdEtatCompte(pidetatcompte);			
			Societe _societe = findSocieteByidSociete(idSociete);
			logger.debug("Search revendeur by societe, droit");
			return revendList;
		}

		@Override
		public List<Revendeur> findRevendeurByEtat(Integer idetat) {
			List<Revendeur> _revList = new ArrayList<Revendeur>();
			EtatCompte etatCompte = findByEtatIdEtatCompte(idetat);
			logger.debug("Search revendeur by droit:" + etatCompte.getLibelle());
			try {
				 String s = "select b from Revendeur b where b.user.etatCompte =:etatCompte";
				 if(Parameters.group!=null){
					 s+=" and b.user.group =:group";
				 } 
		          Query query = entityManager.createQuery(s);
		          if(Parameters.group!=null){
		        	  query.setParameter("group", Parameters.group);
		          }
		            query.setParameter("etatCompte", etatCompte);
		            query.setHint("org.hibernate.cacheable", false);
		            _revList = (List<Revendeur>) query.getResultList();
		            return _revList;
			} catch (RuntimeException re) {
				logger.error("find user by etatCompte failed", re);
				return new ArrayList<Revendeur>();
			}
		}

		@Override
		public List<Revendeur> findRevendeurBySociete(Integer idSociete) {
			List<Revendeur> _revList = new ArrayList<Revendeur>();
			Societe societe = findSocieteByidSociete(idSociete);
			logger.debug("Search revendeur by societe:" + societe.getCodeSociete());
			try {
				 String s = "select b from Revendeur b where b.societe =:societe";
				 if(Parameters.group!=null){
					 s+=" and b.user.group =:group";
				 } 
		          Query query = entityManager.createQuery(s);
		          if(Parameters.group!=null){
		        	  query.setParameter("group", Parameters.group);
		          }
		            query.setParameter("societe", societe);
		            query.setHint("org.hibernate.cacheable", false);
		            _revList = (List<Revendeur>) query.getResultList();
		            return _revList;
			} catch (RuntimeException re) {
				logger.error("find user by etatCompte failed", re);
				return new ArrayList<Revendeur>();
			}
		}

		@Override
		public List<Revendeur> findRevendeurByEtatAndSociete(Integer idetat,
				Integer idsociete) {
			List<Revendeur> _listrevendeur = new ArrayList<Revendeur>();
			EtatCompte _etatcompte= findByEtatIdEtatCompte(idetat);
			Societe societe = findSocieteByidSociete(idsociete);
			logger.debug("Search user by droit, societe");
			try {
				 String s = "select b from Revendeur b where (b.user.etatCompte =:etatCompte) and (b.societe =:societe)";
				 if(Parameters.group!=null){
					 s+=" and b.user.group =:group";
				 } 
		          Query query = entityManager.createQuery(s);
		          if(Parameters.group!=null){
		        	  query.setParameter("group", Parameters.group);
		          }
		            query.setParameter("societe", societe);
		            query.setParameter("etatCompte", _etatcompte);
		            query.setHint("org.hibernate.cacheable", false);
		            _listrevendeur = (List<Revendeur>) query.getResultList();
		            return _listrevendeur;
			} catch (RuntimeException re) {
				logger.error("find revendeur by  societe, etat failed", re);
				return new ArrayList<Revendeur>();
			}
			
		}
		@Override
		public List<Societe> findAllSociete(String type) {
			List<Societe> soc = new ArrayList<Societe>();
	        try {
	            String s = "select b from Societe b ";
	            if(Parameters.group!=null)
	            	s +=" where b.type= :client";
	            s +=" order by b.nom";
	            Query query = entityManager.createQuery(s);
	            if(Parameters.group!=null)
	            	query.setParameter("client", type);
	            query.setHint("org.hibernate.cacheable", false);
	            soc = query.getResultList();
	        } catch (Exception e) {
	            logger.error("Error while loading revendeurs", e);
	        }
	        return soc;
		}

		@Override
		public Societe findSocieteByidSociete(Integer pidSociete) {
			logger.debug("getting Societe instance with id: " + pidSociete);
			try {
				Societe instance = entityManager.find(Societe.class, pidSociete);
				logger.debug("find Societe ById successful");
				return instance;
			} catch (RuntimeException re) {
				logger.error("findById profil failed", re);
				return null;
			}

		}

		@Override
		public List<ProduitRevendeur> findAllProduitRevendeur(Revendeur revendeurassocie) {
			
			logger.debug("getting list ProduitRevendeur instance with id: ");
			try {
				List<ProduitRevendeur> listproduitrevendeur = new ArrayList<ProduitRevendeur>();
				String s = "select b from ProduitRevendeur b where (b.revendeur =:revendeurassocie)";
		          Query query = entityManager.createQuery(s);
		            query.setParameter("revendeurassocie", revendeurassocie);
		            query.setHint("org.hibernate.cacheable", false);
		            listproduitrevendeur = (List<ProduitRevendeur>) query.getResultList();
		            return listproduitrevendeur;
			} catch (RuntimeException re) {
				logger.error("find list ProduitRevendeur failed", re);
				return null;
			}
		}

		@Override
		public Revendeur findRevendeurByCurrentuser(User currentUser) {
			logger.debug("getting list Produitevendeur instance with user current");
			try {
				String s = "select b from Revendeur b where (b.user =:currentUser)";
		          Query query = entityManager.createQuery(s);
		            query.setParameter("currentUser", currentUser);
		            query.setHint("org.hibernate.cacheable", false);
		            Revendeur revendeur = (Revendeur) query.getSingleResult();
		            return revendeur;
			} catch (RuntimeException re) {
				logger.error("list Produitevendeur instance with user current failed", re);
				return null;
			}
		}

		@Override
		public List<ProduitRevendeur> findAllProduitRevendeur() {
			logger.debug("getting list Produitevendeur instance");
			try {
				String s = "select b from ProduitRevendeur b";
				 if(Parameters.group!=null){
					 //Revendeur revendeur= this.findRevendeurByCurrentuser(Parameters.userconnect);
					 //if(revendeur!=null)
					 s+=" where b.revendeur.user.group= :group";
				 } 
		          Query query = entityManager.createQuery(s);
		          if(Parameters.group!=null){
		        	  //Revendeur revendeur= this.findRevendeurByCurrentuser(Parameters.userconnect);
		        	  //if(revendeur!=null)
		        	  query.setParameter("group", Parameters.group);
		          }
	            query.setHint("org.hibernate.cacheable", false);
	            List<ProduitRevendeur> produitrevendeurs = (List<ProduitRevendeur>) query.getResultList();
	            return produitrevendeurs;
			} catch (RuntimeException re) {
				logger.error("Revendeur instance with user current failed", re);
				return null;
			}
		}

		@Override
		public List<Produit> findAllProduit() {
			logger.debug("getting Poduit instance");
			try {
				String s = "select b from Produit b";
				if(Parameters.group!=null)
					s +=" where b.fournisseur.user.group =:group";
		          Query query = entityManager.createQuery(s);
		          if(Parameters.group!=null)
		        	  query.setParameter("group", Parameters.group);
	            query.setHint("org.hibernate.cacheable", false);
	            List<Produit> produits = (List<Produit>) query.getResultList();
	            return produits;
			} catch (RuntimeException re) {
				logger.error("Produit instance with failed", re);
				return null;
			}
		}

		@Override
		public ProduitRevendeur SaveProduitRevendeur(ProduitRevendeur produitRev) {
			ProduitRevendeur _result;
			logger.debug("merging ProduitRevendeur instance");
			try {
				_result = entityManager.merge(produitRev);
				//entityManager.flush();
				
				logger.debug("merge ProduitRevendeur successful");
				return _result;
			} catch (RuntimeException re) {
				logger.error("merge ProduitRevendeur failed"+" ProduitRevendeur", re);
				return null;
			}
			
		}

		@Override
		public boolean loginExists(String login) {
			return false;
		}

		@Override
		public Revendeur findRevendeurById(Integer id) {
			//Revendeur revendeur;
		        try {
		        	Revendeur instance = entityManager.find(Revendeur.class, id);
		            return instance;
		        } catch (Exception e) {
		            logger.error("Error while loading Client with clientName " + id, e);
		            return null;
		        }
		}

		@Override
		public List<Profil> profilRevList() {
			logger.debug("getting list profil revendeur instance");
			try {
				String s = "select b from Profil b where b.libelle='Revendeur'";
				Query query = entityManager.createQuery(s);
	            query.setHint("org.hibernate.cacheable", false);
	            List<Profil> profils= query.getResultList();
	            
	            return profils;
			}
			catch (Exception e) {
				logger.error("Error while loading list revendeur failed ", e);
	            return null;
			}
		}

		@Override
		public Revendeur getRevendeurConnect(User user) {
			logger.debug("getting revendeur instance connecté");
			Integer iduser= user.getId();
			try {
				String s = "select b from Revendeur b where b.user =:user";
				
				Query query = entityManager.createQuery(s);
				query.setParameter("user", user);
	            query.setHint("org.hibernate.cacheable", false);
	            Revendeur revendeur=(Revendeur) query.getSingleResult();  
	           
	            return revendeur;
			}
			catch (Exception e) {
				logger.error("Error while loading revendeur failed coonecté ", e);
	            return null;
			}
		}
		@Override
		public List<Fournisseur> getListFrnst(int group) {
			logger.debug("getting frns list");
			List<Fournisseur> _listfrns= null;
			try {
				String s = "select b from Fournisseur b";	
				if(Parameters.group!=null){
		        	  s +=" where b.user.group =:group";
		        }
				Query query = entityManager.createQuery(s);
				if(Parameters.group!=null){
		        	  query.setParameter("group", Parameters.group);
		          }
	            query.setHint("org.hibernate.cacheable", false);
	            _listfrns= query.getResultList();  	           
	            return _listfrns;
			}
			catch (Exception e) {
				logger.error("Error while loading revendeur failed coonecté ", e);
	            return null;
			}
		}
		@Override
		public List<Fournisseur> getListFrnst() {
			logger.debug("getting frns list");
			List<Fournisseur> _listfrns= null;
			try {
				String s = "select b from Fournisseur b";	
				if(Parameters.group!=null){
		        	  s +=" where b.user.group =:group";
		        }
				Query query = entityManager.createQuery(s);
				if(Parameters.group!=null){
		        	  query.setParameter("group", Parameters.group);
		          }
	            query.setHint("org.hibernate.cacheable", false);
	            _listfrns= query.getResultList();  	           
	            return _listfrns;
			}
			catch (Exception e) {
				logger.error("Error while loading revendeur failed coonecté ", e);
	            return null;
			}
		}
		@Override
		public List<TypeCommande> getListTypeCommande() {
			List<TypeCommande> commandes = new ArrayList<TypeCommande>();
			try {
	            String s = "select b from TypeCommande b";
	            Query query = entityManager.createQuery(s);
	           
	            commandes= query.getResultList();
	            return commandes;
			}
	        catch (Exception e) {
				e.getMessage();
				return null;
			}
		}

		@Override
		public Fournisseur saveFournisseur(Fournisseur fournisseur) {
			logger.debug("merging fournisseur instance");
			try {
				Fournisseur _result = entityManager.merge(fournisseur);
				logger.debug("merge fournisseur successful");
				return _result;
			} catch (RuntimeException re) {
				logger.error("merge fournisseur failed", re);
				return null;
			}
			
		}

		@Override
		public Fournisseur findFrns(User user) {
			logger.debug("getting frns");
			Fournisseur _frns= null;
			try {
				String s = "select b from Fournisseur b where b.user= :user ";	
				Query query = entityManager.createQuery(s);
				query.setParameter("user", user);
	            query.setHint("org.hibernate.cacheable", false);
	            _frns= (Fournisseur) query.getSingleResult();  	           
	            return _frns;
			}catch (Exception e) {
				logger.error("merge fournisseur failed", e);
				return null;
			}
		}

		
	
}
