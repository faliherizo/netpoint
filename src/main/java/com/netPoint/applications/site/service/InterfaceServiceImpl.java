package com.netPoint.applications.site.service;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.directwebremoting.annotations.RemoteProxy;
import org.eclipse.birt.report.engine.api.script.element.IList;
import org.hibernate.mapping.Array;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.netPoint.applications.site.dao.IDaoCommande;
import com.netPoint.applications.site.dao.InterfaceDao;
import com.netPoint.applications.site.dao.InterfaceUserDao;
import com.netPoint.applications.site.model.Civilite;
import com.netPoint.applications.site.model.Client;
import com.netPoint.applications.site.model.Commande;
import com.netPoint.applications.site.model.Devise;
import com.netPoint.applications.site.model.EtatCommande;
import com.netPoint.applications.site.model.EtatCompte;
import com.netPoint.applications.site.model.Fournisseur;
import com.netPoint.applications.site.model.Langue;
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
import com.netPoint.applications.site.service.soap.model.Order;
import com.netPoint.applications.site.util.Divers;
import com.netPoint.applications.site.util.ListRange;
import com.netPoint.applications.site.util.Parameters;
import com.netPoint.collection.CollectionComparator;

@Service("springService")
@RemoteProxy(name = "services")
public class InterfaceServiceImpl implements InterfaceService {
    
    public static final Logger logger = LoggerFactory.getLogger(InterfaceServiceImpl.class);
	@Autowired
	private Notifier notifier;
	@Autowired
	private IDaoCommande daoCommande;
	public void setDaoCommande(IDaoCommande daoCommande) {
		this.daoCommande = daoCommande;
	}
	@Autowired
	private InterfaceDao interfaceDao;
	@Autowired
	private InterfaceUserDao interfaceUserDao;
	/**
	 * @param interfaceUserDao the interfaceUserDao to set
	 */
	public void setInterfaceUserDao(InterfaceUserDao interfaceUserDao) {
		this.interfaceUserDao = interfaceUserDao;
	}
	/**
	 * @param notifier the notifier to set
	 */
	public void setNotifier(Notifier notifier) {
		this.notifier = notifier;
	}
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<EtatCommande> findAllEtatCommande() {
        return interfaceDao.findAllEtatCommande();
    }
    
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<EtatCompte> findAllEtatCompte() {
        return interfaceDao.findAllEtatCompte();
    }
    
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Civilite> findAllCivilite() {
        return interfaceDao.findAllCivilite();
    }
    
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Profil> findAllDroit() {
        return interfaceDao.findAllDroitUser();
    }
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Profil> findAllDroits() {
        return interfaceDao.findAllDroit();
    }
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Langue> findAllLangue() {
        return interfaceDao.findAllLangue();
    }
    
    @Autowired
    public void setInterfaceDao(InterfaceDao interfaceDao) {
        this.interfaceDao = interfaceDao;
    }
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public User SaveOrUpdateUser(User user) {	
    	try {
			if(user.getId() !=null){
			  User _userOld = findByIdUser(user.getId());
			  user.setDateCreation(_userOld.getDateCreation());
		      if(user.isChangepwd()){
		    	  user = Divers.generatePassword(user);
		      }
		      else{
		    	  user.setPwd(_userOld.getPwd());
		      }
		      user.setGroup(_userOld.getGroup());
		      Date _date = new Date();
				//user.setModifieeLe(_date.toString());
				user.setModifieePar(Parameters.userconnect.getNom()+" "+Parameters.userconnect.getPrenom());
				interfaceDao.saveLogin(user);
				if(user.getProfil().getId()==2)
	    		{
					
	    			Fournisseur fournisseur = interfaceDao.findFrns(user);
	    			fournisseur.setNom(user.getNom()+" " +user.getPrenom());
	    			interfaceDao.saveFournisseur(fournisseur);
	    		}
			}
			return user;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public User updateUser(User user){
    	interfaceDao.saveLogin(user);
    	return user;
    }
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public User saveLogin(User user) 
    {
    	try {
    		int maxgroup =interfaceUserDao.getMaxValueGroup();
    		if(user.getProfil().getId()==1 && user.getId()==null)
    		{
    			user.setGroup(maxgroup + 1);
    			user.setNewslettre(false);
    		}
    		else
    		{
    				user.setGroup(Parameters.getGroup());
    		}
			user = Divers.generatePassword(user);
			Date _date = new Date();
			//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			user.setModifieeLe(_date);
			user.setModifieePar(Parameters.userconnect.getNom()+" "+Parameters.userconnect.getPrenom());
			user=interfaceDao.saveLogin(user);
			if(user.getProfil().getId()==2)
    		{
    			SaveFournisseur(user);
    		}
			return user;
		} catch (Exception e) {
			logger.info(e.getMessage());
			e.printStackTrace();
			return null;
		}
    }
    
    /*
     * Save fournisseur 
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Fournisseur SaveFournisseur(User user){
    	
    	Fournisseur fournisseur = new Fournisseur();
		fournisseur.setUser(user);
		fournisseur.setNom(user.getNom()+" " +user.getPrenom());
		interfaceDao.saveFournisseur(fournisseur);
    	return fournisseur;
    	
    }
    
    /* (non-Javadoc)
     * @see com.netPoint.applications.site.service.InterfaceService#findAllClient()
     */
    @Override
    public List<Client> findAllClient() {
        // TODO Auto-generated method stub
        return interfaceDao.findAllClient();
    }
    
    /* (non-Javadoc)
     * @see com.netPoint.applications.site.service.InterfaceService#findAllCommande()
     */
    @Override
    public List<Commande> findAllCommande() {
        // TODO Auto-generated method stub
        return null;
    }
    
    /* (non-Javadoc)
     * @see com.netPoint.applications.site.service.InterfaceService#findAllRevendeur()
     */

    @Override
    public List<Revendeur> findAllRevendeur() {
        return interfaceDao.findAllRevendeur();
    }
    
    @Override
    public ListRange findAllRevendeurZ(int start, int count, String orderBy, String filtre) {
        ListRange A;
        
        try {
            A = new ListRange(findAllRevendeur().size(), findAllRevendeur(), start, count);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        return A;
    }
    
    /* (non-Javadoc)
     * @see com.netPoint.applications.site.service.InterfaceService#findClientById(long)
     */
    @Override
    public Client findClientById(long id) {
        return interfaceDao.findClientById(id);
    }
    
    /* (non-Javadoc)
     * @see com.netPoint.applications.site.service.InterfaceService#findClientByName(java.lang.String)
     */
    @Override
    public Client findClientByName(String CodeClient) {
        return interfaceDao.findClientByName(CodeClient);
    }

    /* (non-Javadoc)
     * @see com.netPoint.applications.site.service.InterfaceService#findCommandesByClient(com.netPoint.applications.site.model.Client)
     */
    @Override
    public List<Commande> findCommandesByClient(Client client) {
        // TODO Auto-generated method stub
        return null;
    }
    
    /* (non-Javadoc)
     * @see com.netPoint.applications.site.service.InterfaceService#findRevendeurById(long)
     */
    @Override
    public Revendeur findRevendeurById(long id) {
        return interfaceDao.findRevendeurById(id);
    }
    
    /* (non-Javadoc)
     * @see com.netPoint.applications.site.service.InterfaceService#findRevendeurByName(java.lang.String)
     */
    @Override
    public Revendeur findRevendeurByName(String name) {
        // TODO Auto-generated method stub
        return null;
    }
    
    /* (non-Javadoc)
     * @see com.netPoint.applications.site.service.InterfaceService#saveClient(com.netPoint.applications.site.model.Client)
     */
    @Override
    public void saveClient(Client client) {
        // TODO Auto-generated method stub
        
    }
    
    /* (non-Javadoc)
     * @see com.netPoint.applications.site.service.InterfaceService#saveCommande(com.netPoint.applications.site.model.Commande)
     */
    @Override
    public void saveCommande(Commande commande) {
        // TODO Auto-generated method stub
        
    }
    
    /* (non-Javadoc)
     * @see com.netPoint.applications.site.service.InterfaceService#saveRevendeur(com.netPoint.applications.site.model.Revendeur)
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveRevendeur(Revendeur revendeur) {
        interfaceDao.saveRevendeur(revendeur);
        
    }
    
    /* (non-Javadoc)
     * @see com.netPoint.applications.site.service.InterfaceService#findAllUser()
     */
    @Override
    public List<User> findAllUser() {
        return interfaceDao.findAllUser();
    }
    
    /* (non-Javadoc)
     * @see com.netPoint.applications.site.service.InterfaceService#findAllUserZ(int, int, java.lang.String, java.lang.String)
     */
    @Override
    public ListRange findAllUserZ(int start, int count, String orderBy, String filtre) {
        ListRange A;
        
        try {
            A = new ListRange(findAllUser().size(), findAllUser(), start, count);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        return A;
    }
    
    @SuppressWarnings({
        "unchecked", "unused"
    })
    private ListRange filtreEtRange(List liste, int start, int count, String filtre, String orderBy) {
        liste = filtreEtTrieList(liste, filtre, orderBy);
        return new ListRange(liste.size(), liste, start, count);
    }
    
    @SuppressWarnings("unchecked")
    private List filtreEtTrieList(List liste, String filtre, String orderBy) {
        // Filtrage des données
        liste = filtreListe(liste, filtre);
        
        // Ordonnancement des données
        trieListe(liste, orderBy);
        
        return liste;
    }
    
    @SuppressWarnings("unchecked")
    private void trieListe(List listeATrier, String orderBy) {
        if (orderBy != null && orderBy.trim().length() > 0) {
            Collections.sort(listeATrier, new CollectionComparator(orderBy));
        }
    }
    
    @SuppressWarnings("unchecked")
    private List filtreListe(List listeAFiltrer, String filtre) {
        if (!StringUtils.isEmpty(filtre)) {
            Hashtable<String, String> filterMap = getFiltre(filtre);
            
            if (!filterMap.isEmpty()) {
                for (Iterator<Object> iterator = listeAFiltrer.iterator(); iterator.hasNext();) {
                    Object currentObject = iterator.next();
                    for (Enumeration<String> enumKeys = filterMap.keys(); enumKeys.hasMoreElements();) {
                        String key = enumKeys.nextElement();
                        try {
                            String searchValue = filterMap.get(key).toLowerCase();
                            String currentValue = BeanUtils.getProperty(currentObject, key);
                            if (null == currentValue || !StringUtils.contains(currentValue.toLowerCase(), searchValue)) {
                                // DKR 21/03/2008: Ajout du test de nullité afin de ne pas ramener de données erronées
                                iterator.remove();
                                break;
                            }
                        } catch (Exception e) {
                            if (logger.isInfoEnabled())
                                logger.info(e.getMessage());
                        }
                    }
                }
            }
        }
        return listeAFiltrer;
    }
    
    private Hashtable<String, String> getFiltre(String filtre) {
        Hashtable<String, String> filterMap = new Hashtable<String, String>();
        
        String filtreFamille[] = filtre.split("---");
        for (int i = 0; i < filtreFamille.length; i++) {
            String currentFamille = filtreFamille[i];
            String filtreAll[] = currentFamille.split(":::");
            if (filtreAll.length == 2) {
                String filtreName = filtreAll[0];
                String filtreValue = filtreAll[1];
                filterMap.put(filtreName, filtreValue);
            }
        }
        return filterMap;
    }
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<User> findAllUserByProfilMailEtat(Integer pIdProfil, String pMail,
			Integer pIdEtatCompte) {
		return interfaceDao.findAllUserByProfilMailEtat(pIdProfil, pMail, pIdEtatCompte);
	}
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void RemoveUser(Integer userId) {
		//interfaceDao.removeUser(userId);
		User user = this.findByIdUser(userId);
		this.RemoveUser(user);
	}
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void RemoveUser(User user) {
		try {
			if(user.getProfil().getId()==2)
			{
				Fournisseur fournisseur=interfaceDao.getFrnst(user);
				if(fournisseur!=null && fournisseur.getId()!=null)
					interfaceDao.removeFrns(fournisseur);
				if(user!=null && user.getId()!=null)
					interfaceDao.removeUser(user);	
			}
			else{
				interfaceDao.removeUser(user);	
			}	
		} catch (Exception e) {
			logger.error("error enregistrement utilisateur", e.getMessage());
		}
		
	}
	
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public User findByIdUser(Integer id) {
		return interfaceDao.findByIdUser(id);
	}
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void removeRevendeur(Integer IdRevendeur) {
		interfaceDao.removeRevendeur(IdRevendeur);
		
	}
    //@PreAuthorize("hasRole('ROLE_ADMIN, ROLE_REVENDEUR, ROLE_GROSSISTE, ROLE_SUP')")
	@Override
	@SuppressWarnings("unused")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void SaveOrUpdateRevendeur(Revendeur detachedRevendeur) {
			try {
				List<ProduitRevendeur> revendeuProdlist= detachedRevendeur.getProduitRevendeurs();
				Revendeur _revendeur= detachedRevendeur;			
				_revendeur.setProduitRevendeurs(null);	
				_revendeur.getSociete().setGroup(Parameters.group);
				_revendeur.getSociete().setType("revendeur");
				Profil profil= daoCommande.getProfilById(3);
				_revendeur.getUser().setProfil(profil);
				if(_revendeur.getId()==null)
				{
					Revendeur _rev = interfaceDao.SaveOrUpdateRevendeur(_revendeur, revendeuProdlist);
				}
				else{
					Revendeur revendeurold = findRevendeurById(_revendeur.getId());
					//if(_revendeur.getUser().getPwd() !=revendeurold.getUser().getPwd()){
						_revendeur.getUser().setPwd(revendeurold.getUser().getPwd());
						_revendeur.getUser().setGroup(revendeurold.getUser().getGroup());
					//}
					//else{
						_revendeur.setRevendeur(revendeurold.getRevendeur());
					//}
					//_revendeur.getUser().setEtatCompte(revendeurold.getUser().getEtatCompte());
					//_revendeur.getUser().setProfil(revendeurold.getUser().getProfil());
					_revendeur.setCompteBancaire(revendeurold.getCompteBancaire());
					_revendeur.getUser().setDateCreation(revendeurold.getUser().getDateCreation());
					_revendeur.getUser().setDateFin(revendeurold.getUser().getDateFin());
					
					Revendeur _rev = interfaceDao.SaveOrUpdateRevendeur(_revendeur);
				}
			} catch (Exception e) {
				logger.error("", e.getMessage());
				
			}
	}
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void SaveListProduitRevendeur(Revendeur revendeur, List<ProduitRevendeur> produitRevendeurs) {
		for (ProduitRevendeur produitRevendeur : produitRevendeurs) {
			produitRevendeur.setRevendeur(revendeur);
			interfaceDao.SaveProduitRevendeur(produitRevendeur);
		}
	}
	private Revendeur getRevendeurSansList(Revendeur detachedRevendeur) {
		detachedRevendeur.getProduitRevendeurs().clear();
		Revendeur _rev = detachedRevendeur;
		return _rev;
	}
	@Override
	public List<User> findAllUserByProfil(Integer pidprofil) {
		return interfaceDao.findAllUserByProfil(pidprofil);
	}

	@Override
	public List<User> findAllUserByMail(String mail) {
		return interfaceDao.findAllUserByMail(mail);
	}

	@Override
	public List<User> findAllUserByEtat(Integer pidetatcompte) {
		return interfaceDao.findAllUserByEtat(pidetatcompte);
	}

	@Override
	public List<User> findAllUserByMailEtat(String mail, Integer pidetatcompte) {
		return interfaceDao.findAllUserByMailEtat(mail, pidetatcompte);
	}

	@Override
	public List<User> findAllUserByProfilMail(Integer pidprofil, String mail) {
		return interfaceDao.findAllUserByProfilMail(pidprofil, mail);
	}

	@Override
	public List<User> findAllUserByProfilEtat(Integer pidprofil,
			Integer pidetatcompte) {
		return interfaceDao.findAllUserByProfilEtat(pidprofil, pidetatcompte);
	}
	
	//modif mija
	@Override
	public List<Pays> findAllPays() {
		// TODO Auto-generated method stub
		return interfaceDao.findAllPays();
	}

	@Override
	public List<ModeReglement> findAllModeReglement() {
		// TODO Auto-generated method stub
		return interfaceDao.findAllModeReglement();
	}
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Override
	public List<Societe> findAllSociete(String type) {
		/*if(Parameters.group!=null)
		{
			List<Revendeur> revenderus=interfaceDao.findAllRevendeur();
			List<Societe> societes= new ArrayList<Societe>();
			for (Revendeur revendeur :revenderus ) {
				societes.add(revendeur.getSociete());
			}
			return societes;
		}
		else*/
		return interfaceDao.findAllSociete(type);
	}
	
	@Override
	public List<Revendeur> findAllRevendeurSociete(Integer idSociete,
			Integer pidetatcompte) {
		// TODO Auto-generated method stub
		return interfaceDao.findAllRevendeurSociete(idSociete,pidetatcompte);
	}
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	@Override
	public List<Revendeur> findRevendeurByEtat(Integer idetat) {
		// TODO Auto-generated method stub
		return interfaceDao.findRevendeurByEtat(idetat);
	}
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	@Override
	public List<Revendeur> findAllRevendeurByMail(String mail) {
		// TODO Auto-generated method stub
		return interfaceDao.findAllRevendeurByMail(mail);
	}
	@Override
	public List<Revendeur> findRevendeurBySociete(Integer idSociete) {
		// TODO Auto-generated method stub
		return interfaceDao.findRevendeurBySociete(idSociete);
	}

	@Override
	public List<Revendeur> findRevendeurByEtatAndSociete(Integer idetat,
			Integer idsociete) {
		// TODO Auto-generated method stub
		return interfaceDao.findRevendeurByEtatAndSociete(idetat, idsociete);
	}
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	@Override
	public User findUserByMail(String mail) {
		
		return interfaceDao.findUserByMail(mail);
	}
	
	@Override
	public List<Devise> findAllDevise() {
		// TODO Auto-generated method stub
		return interfaceDao.findAllDevise();
	}
	@Override
	public List<ProduitRevendeur> findAllProduitRevendeur(
			Revendeur revendeurassocie) {
		
		return interfaceDao.findAllProduitRevendeur(revendeurassocie);
	}
	@Override
	public Revendeur findRevendeurByCurrentuser(User currentUser) {
		
		return interfaceDao.findRevendeurByCurrentuser(currentUser);
	}
	@Override
	public List<ProduitRevendeur> findAllProduitRevendeur() {
		
		return interfaceDao.findAllProduitRevendeur();
	}
	@Override
	public List<Produit> findAllProduit() {
		/*if(Parameters.group!=null){
			List<Produit> _listProduit= new ArrayList<Produit>();
			for (Fournisseur frns : this.getListFrnst(Parameters.group)) {
				_listProduit.addAll(frns.getListProduit());
			}
			return _listProduit; 
		}
		else*/
			return interfaceDao.findAllProduit();
	
	}
	@Override
	public boolean loginExists(String login) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Revendeur findRevendeurById(Integer id) {
		
		return interfaceDao.findRevendeurById(id);
	}
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Profil> profilRevList() {
		return interfaceDao.profilRevList();
	}	
	@Override
	public Revendeur getRevendeurConnect(User user) {
			return interfaceDao.getRevendeurConnect(user);
	}
	@Override
	public List<Fournisseur> getListFrnst(int group){
		
		return interfaceDao.getListFrnst(group);
	}
	@Override
	public List<TypeCommande> getListTypeCommande() {
		
		return interfaceDao.getListTypeCommande();
	}
	@Override
	public List<Fournisseur> getListFrnst(){
		
		return interfaceDao.getListFrnst();
	}
	@Override
	public List<Order> getOrderList(Client client){
		List<Commande> commandes=(List<Commande>) client.getListCommande(); //interfaceDao.findCommandesByClient(client);
		List<Order> commandelines = copyPropriety(commandes); 
		return commandelines;
	}
	private List<Order> copyPropriety(List<Commande> commandes){
		List<Order> clients= new ArrayList<Order>();
		for (Commande commande : commandes) {
			Order client = new Order();
			client.setCodeClient(commande.getClient().getCodeClient());
			client.setDate_commande(asXMLGregorianCalendar(commande.getDateCommande()));
			client.setDate_fin(asXMLGregorianCalendar(commande.getDateFin()));
			client.setNombreposte(commande.getNombreposte());
			client.setNumeroCommande(commande.getNumeroCommande());
			client.setNumeroRevendeur(commande.getNumeroRevendeur());
			client.setquantity(commande.getQuantite());
			client.setProduct(commande.getProduit().getNomProduit());
			clients.add(client);
			
		}
		return clients;
	}
	 /**
     * Converts a java.util.Date into an instance of XMLGregorianCalendar
     *
     * @param date Instance of java.util.Date or a null reference
     * @return XMLGregorianCalendar instance whose value is based upon the
     *  value in the date parameter. If the date parameter is null then
     *  this method will simply return null.
     */
    public XMLGregorianCalendar asXMLGregorianCalendar(java.util.Date date) {
    	DatatypeFactory df = null;
        if (date == null) {
            return null;
        } else {
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTimeInMillis(date.getTime());
            return df.newXMLGregorianCalendar(gc);
        }
    }
}
