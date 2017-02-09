package com.netPoint.applications.site.service;

import java.util.List;

import org.springframework.web.bind.annotation.ModelAttribute;

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
import com.netPoint.applications.site.model.Profil;
import com.netPoint.applications.site.model.Revendeur;
import com.netPoint.applications.site.model.Societe;
import com.netPoint.applications.site.model.TypeCommande;
import com.netPoint.applications.site.model.User;
import com.netPoint.applications.site.service.soap.model.Order;
import com.netPoint.applications.site.util.ListRange;

public interface InterfaceService {
    
    //GESTION DES COMMANDES
    
    List<EtatCommande> findAllEtatCommande();
    
    void saveCommande(Commande commande);
    
    List<Commande> findAllCommande();
    
    List<Commande> findCommandesByClient(Client client);
    
    List<EtatCompte> findAllEtatCompte();
    
    List<Civilite> findAllCivilite();
    
    List<Profil> findAllDroit();
    
    List<Langue> findAllLangue();
    
    
    
    User saveLogin(User login);
    public List<User> findAllUser();
    public ListRange findAllUserZ(int start, int count, String orderBy, String filtre);
    
    List<Revendeur> findAllRevendeur();
    
    Revendeur findRevendeurById(long id);
    
    Revendeur findRevendeurByName(String name);
    
    void saveRevendeur(Revendeur revendeur);
    
    List<Client> findAllClient();
    
    Client findClientById(long id);
    
    Client findClientByName(String name);
    
    void saveClient(Client client);
    public ListRange findAllRevendeurZ(int start, int count, String orderBy, String filtre);
    
    List<User> findAllUserByProfilMailEtat(Integer pIdProfil, String pMail,
			Integer pIdEtatCompte);
    void RemoveUser(Integer userId);
    void RemoveUser(User user);
    User SaveOrUpdateUser(User detachedUser);
    public User findByIdUser(Integer id);
    
    /* 
     *
     */
    void removeRevendeur(Integer IdRevendeur);
    
    public void SaveOrUpdateRevendeur(Revendeur detachedRevendeur);
    
    List<User> findAllUserByProfil(Integer pidprofil);
    
    List<User> findAllUserByMail(String mail) ;
    
    List<User> findAllUserByEtat(Integer pidetatcompte);
    List<User> findAllUserByMailEtat(String mail, Integer pidetatcompte);
    List<User> findAllUserByProfilMail(Integer pidprofil, String mail);
    
    List<User> findAllUserByProfilEtat(Integer pidprofil,
			Integer pidetatcompte);
    
    User findUserByMail(String mail);

    //modif mija
    List<Pays> findAllPays();
    List<ModeReglement> findAllModeReglement();      
    List<Devise> findAllDevise();
    List<Societe> findAllSociete(String type);
    List<Revendeur> findAllRevendeurSociete(Integer idSociete, Integer pidetatcompte);
    
    
    List<Revendeur> findRevendeurByEtat(Integer idetat);
    
    List<Revendeur> findRevendeurBySociete(Integer idSociete);
    List<Revendeur> findRevendeurByEtatAndSociete(Integer idetat,
			Integer idsociete);
    /*
     * rerecherche de revendeur par mail
     */
    List<Revendeur> findAllRevendeurByMail(String mail);
    /*
	 * Find list produitrevendeur in database
	 * */
	List<ProduitRevendeur> findAllProduitRevendeur();
	/*
	 * Find list produit by revendeur
	 * */
	List<ProduitRevendeur> findAllProduitRevendeur(Revendeur revendeurassocie);
	/*
	 * find revendeur associate a current user
	 * */
	Revendeur findRevendeurByCurrentuser(User currentUser);
	/*
	 *Fin list produit in database 
	 * */
	List<Produit> findAllProduit();
	
	void SaveListProduitRevendeur(Revendeur revendeur,
			List<ProduitRevendeur> produitRevendeurs);
	
	/**
	   * VÃ©rifie si un pseudo existe dÃ©jÃ 
	   * @param login Le pseudo Ã  rechercher
	   * @return true si le pseudo existe dÃ©jÃ , false sinon
	   */
	  public boolean loginExists(String login);
	  
	  /**
	   * 
	   * @param id
	   * @return
	   */
	  Revendeur findRevendeurById(Integer id);
	  
	  
	  /**
	   * Return list profil of revendeur
	   * @return
	   */
	  List<Profil> profilRevList();
	  /**
	   * Return revendeur connecté
	   * @param user
	   * @return revendeur
	   */
	  Revendeur getRevendeurConnect(User user);

	List<Fournisseur> getListFrnst(int group);
	List<TypeCommande> getListTypeCommande();
	
	List<Profil> findAllDroits();

	List<Fournisseur> getListFrnst();

	List<Order> getOrderList(Client client);

	Fournisseur SaveFournisseur(User user);

	User updateUser(User user);
}
