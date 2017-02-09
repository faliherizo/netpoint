package com.netPoint.applications.site.dao;

import java.util.List;

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
import com.netPoint.applications.site.util.ListRange;

public interface InterfaceDao {
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
    void UpdateUser(User login);
    List<Revendeur> findAllRevendeur();
    
    Revendeur findRevendeurById(long id);
    
    Revendeur findRevendeurByName(String name);
    
    void saveRevendeur(Revendeur revendeur);
    //Gestion client
    List<Client> findAllClient();
    
    Client findClientById(long id);
    
    Client findClientByName(String name);
    
    void saveOrUpdateClient(Client client);
    
    
   /* Update a User in the database
	 * 
	 * @param detachedUser
	 * @return merged User
	 */
	public User SaveOrUpdateUser(User detachedUser);
    
    public List<User> findAllUser();
    /**
	 * Remove a persisted from the database
	 * 
	 * @param 
	 */
	public void remove(Integer persistanceId);
	/**
	 * Remove a persisted User from the database
	 * 
	 * @param persistentUser
	 */
	public void removeUser(User persistentUser);

	/**
	 * Remove a persisted User from the database
	 * 
	 * @param userId
	 */
	public void removeUser(Integer userId);
	/**
	 * Remove a persisted Revendeur from the database
	 * 
	 * @param persistentRevendeur
	 */
	public void removeRevendeur(Revendeur persistentRevendeur);

	/**
	 * Remove a persisted Commande from the database
	 * 
	 * @param persistentCommande
	 */
	public void removeCommande(Commande persistentCommande);
	/**
	 * Remove a persisted Societe from the database
	 *
	 */
	public void removeSociete(Societe persistentpersistentSociete);
	EtatCompte findByEtatIdEtatCompte(Integer pidetatcompte) ;
	Profil findProfilByidProfil(Integer pidprofil);
	
	/**
	 * Find a User by his Profil, by mail, by etatcompte
	 * 
	 * @return the found User
	 */
	public List<User> findAllUserByProfilMailEtat(Integer pidprofil, String mail, Integer pidetatcompte);
	
	public List<User> findAllUserByProfil(Integer pidprofil);
	public List<User> findAllUserByMail(String mail);
	public List<User> findAllUserByEtat(Integer pidetat);
	public List<User> findAllUserByMailEtat(String mail, Integer pidetatcompte);
	public List<User> findAllUserByProfilMail(Integer pidprofil, String mail);
	public List<User> findAllUserByProfilEtat(Integer pidprofil, Integer pidetatcompte);
	/**
	 * Find a User by id
	 * 
	 * @param id
	 * @return the found User
	 */
	public User findByIdUser(Integer id);
	
	public User findUserByMail(String mail);
	/**
	 * Find a Revendeur by id
	 *
	 * @return the found Revendeur
	 */
	
	void removeRevendeur(Integer IdRevendeur);
	
	public Revendeur SaveOrUpdateRevendeur(Revendeur detachedRevendeur, List<ProduitRevendeur> produitRevendeurs);
	
	//modif mija
  	List<Pays> findAllPays();
	List<ModeReglement> findAllModeReglement();
	List<Devise> findAllDevise();
	User FindUserByUserName(String username);
	List<Societe> findAllSociete(String type);
	List<Revendeur> findAllRevendeurSociete(Integer idSociete,Integer pidetatcompte);
	Societe findSocieteByidSociete(Integer pidSociete);
	List<Revendeur> findRevendeurByEtat(Integer idetat);
	List<Revendeur> findRevendeurBySociete(Integer idSociete);
	List<Revendeur> findRevendeurByEtatAndSociete(Integer idetat, Integer idsociete);
	
	List<Revendeur> findAllRevendeurByMail(String mail);
	/**
	 * 
	 * @param id
	 * @return Revendeur
	 */
	Revendeur findRevendeurById(Integer id);
	/*
	 * Find list produit in database
	 * */
	List<Produit> findAllProduit();
	

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

	ProduitRevendeur SaveProduitRevendeur(ProduitRevendeur produitrev);
	
	/**
	   * VÃ©rifie si un pseudo existe dÃ©jÃ 
	   * @param login Le pseudo Ã  rechercher
	   * @return true si le pseudo existe dÃ©jÃ , false sinon
	   */
	  public boolean loginExists(String login);
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
	List<Profil> findAllDroitUser();

	Revendeur SaveOrUpdateRevendeur(Revendeur detachedRevendeur);

	Fournisseur saveFournisseur(Fournisseur fournisseur);

	Fournisseur getFrnst(User user);

	void removeFrns(Fournisseur fournisseur);

	List<Fournisseur> getListFrnst();

	Fournisseur findFrns(User user);
}
