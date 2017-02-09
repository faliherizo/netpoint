/**
 * 
 */
package com.netPoint.applications.site.dao;

import java.util.List;

import com.netPoint.applications.site.model.Commande;
import com.netPoint.applications.site.model.Revendeur;

/**
 * @author Administrateur
 *
 */
public interface InterfaceRevendeurDao {
   /**
    * Recuperer la liste des Revendeur
    * appartenant a utilisateur
    * @param idUser
    * @return
    */
	Revendeur getByIdUser(final int idUser);
    /**
    * 
    * @param id
    * @return
    */
    List<Revendeur> getAllRevendeurPerimetre(Integer id);
    
    /**
     * Search commad by revendeur and product
     * @param IdUser
     * @return
     */
	  public List<Commande> findAllCommandeByProduitAndRevendeur(Integer IdUser, boolean moisCourant);
	 /**
	  *  
	  * @param idProduit
	  * @param IdUser
	  * @return
	  */
	 List<Commande> findAllCommandeByProduitAndRevendeur(Integer idProduit,
			Integer IdUser, boolean moisCourant);
	 /**
	  * 
	  * @param idRevendeur
	  * @param IdUser
	  * @return
	  */
	List<Commande> findAllCommandeByProduitAndRevend(Integer idRevendeur,
			Integer IdUser, boolean moisCourant);
	
	/**
	 * 
	 * @param idRevendeur
	 * @param idProduit
	 * @param IdUser
	 * @return
	 */
	List<Commande> findAllCommandeByProduitAndRevend(Integer idRevendeur,
			Integer idProduit, Integer IdUser, boolean moisCourant);
	
	List<Revendeur> findAllRevendeur();
}
