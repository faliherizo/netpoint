/**
 * 
 */
package com.netPoint.applications.site.service;

import java.util.List;

import com.netPoint.applications.site.dto.ProduitRevendeurDto;
import com.netPoint.applications.site.dto.RevendeurDto;
import com.netPoint.applications.site.dto.view.ViewRenouvellementDuMois;
import com.netPoint.applications.site.model.Commande;
import com.netPoint.applications.site.model.ProduitRevendeur;
import com.netPoint.applications.site.model.Revendeur;
import com.netPoint.applications.site.model.User;

/**
 * @author Administrateur
 *
 */
public interface RenouvellementMoteurSA {

	/**
	 * Recuperer la liste des Revendeur appartenant a utilisateur
	 *
	 * @return
	 */
	List<RevendeurDto> getAllByIdUser();
	
	/**
	 * Recuperer la liste de produit selon idRevendeur
	 * @return
	 */
	List<ProduitRevendeurDto> getAllById();

	List<ViewRenouvellementDuMois> getRenouvellementDuMois(final String critereProduit,final String critereRevedeur);

	List<ViewRenouvellementDuMois> getRenouvellementEchu(String critereProduit,
			String critereRevedeur);
	

    /**
     * Search commad by revendeur and product
     * @param IdUser
     * @return
     */
	  public List<Commande> findAllCommandeByProduitAndRevendeur( Integer IdUser, boolean moisCourant);
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

	User findCurrentUser();
    
	List<Revendeur> getPerimetreRevendeur(int IdCurrent);


		

}
