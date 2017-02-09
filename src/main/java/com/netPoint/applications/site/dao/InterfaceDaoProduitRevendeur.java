/**
 * 
 */
package com.netPoint.applications.site.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.netPoint.applications.site.model.ProduitRevendeur;

/**
 * @author Administrateur
 *
 */
@Repository
public interface InterfaceDaoProduitRevendeur {
	/**
	 * Recuperer la liste de produit selon idRevendeur
	 * @return
	 */
	List<ProduitRevendeur> getAllById(final int idRevendeur);

}
