/**
 * 
 */
package com.netPoint.applications.site.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.netPoint.applications.site.model.CompteBancaire;
import com.netPoint.applications.site.model.EtatCompte;
import com.netPoint.applications.site.model.Produit;
import com.netPoint.applications.site.model.ProduitRevendeur;
import com.netPoint.applications.site.model.Revendeur;
import com.netPoint.applications.site.model.User;

/**
 * @author Faliherizo
 *
 */
@Repository
public class DaoProductRevendeur implements IDaoProductRevendeur {
	 public static final Logger logger = LoggerFactory.getLogger(DaoProductRevendeur.class);
	    private EntityManager entityManager;
	/* (non-Javadoc)
	 * @see com.netPoint.applications.site.dao.IDaoProduct#getListProduct()
	 */
	@Override
	public List<ProduitRevendeur> getListProduct() {
		List<ProduitRevendeur> produits = new ArrayList<ProduitRevendeur>();
        try {
            String s = "select b from Produit b";
            Query query = entityManager.createQuery(s);
            query.setHint("org.hibernate.cacheable", true);
            produits = query.getResultList();
        } catch (Exception e) {
            logger.error("Error while loading Produit", e);
        }
        return produits;
	}

	/* (non-Javadoc)
	 * @see com.netPoint.applications.site.dao.IDaoProduct#addOrUpdateProduct(com.netPoint.applications.site.model.Produit)
	 */
	@Override
	public void addOrUpdateProduct(ProduitRevendeur produit) {
		logger.debug("merging Product instance");
		try {
			ProduitRevendeur _result = entityManager.merge(produit);
			logger.debug("merge produit successful");
			
		} catch (RuntimeException re) {
			logger.error("merge produit failed", re);
			
		}

	}

	/* (non-Javadoc)
	 * @see com.netPoint.applications.site.dao.IDaoProduct#deleteProduct(com.netPoint.applications.site.model.Produit)
	 */
	@Override
	public void deleteProduct(ProduitRevendeur produitRevendeur) {
		logger.debug("removing produit instance");
		try {
			entityManager.remove(produitRevendeur);
			logger.debug("remove produit successful");
		} catch (RuntimeException re) {
			logger.error("remove produit failed", re);
		}

	}

	/* (non-Javadoc)
	 * @see com.netPoint.applications.site.dao.IDaoProduct#deleteProduct(int)
	 */
	@Override
	public void deleteProduct(int pIdProduct) {
		try {
			ProduitRevendeur produit = findProduitById(pIdProduct);
			
			this.deleteProduct(produit);
			ProduitRevendeur refverifdellete = findProduitById(pIdProduct);
			if(refverifdellete!=null)
				logger.debug("produit non supprimer");
		} catch (Exception e) {
			logger.error("Remove Produit failed", e);
		}
		

	}
	@Override
	public ProduitRevendeur findProduitById(Integer id) {
		//Revendeur revendeur;
	        try {
	        	ProduitRevendeur instance = entityManager.find(ProduitRevendeur.class, id);
	            return instance;
	        } catch (Exception e) {
	            logger.error("Error while loading Produit with clientName " + id, e);
	            return null;
	        }
	}

}
