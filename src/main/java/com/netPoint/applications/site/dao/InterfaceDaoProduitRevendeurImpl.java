/**
 * 
 */
package com.netPoint.applications.site.dao;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.netPoint.applications.site.model.ProduitRevendeur;
import com.netPoint.applications.site.model.Revendeur;

/**
 * @author Administrateur
 *
 */
@Repository
public class InterfaceDaoProduitRevendeurImpl implements InterfaceDaoProduitRevendeur{
 
    private EntityManager entityManager;
    public static final Logger logger = LoggerFactory.getLogger(InterfaceDaoProduitRevendeurImpl.class);
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
	@Override
	@SuppressWarnings("unchecked")
	public List<ProduitRevendeur> getAllById(int idRevendeur) {
		List<ProduitRevendeur> list = null;
		Query queryProduit = entityManager.createQuery("SELECT pdR FROM ProduitRevendeur AS pdR" +
											  " LEFT JOIN FETCH pdR.produit AS pd" +
											  " LEFT JOIN pdR.revendeur AS rvd" +
											  " WHERE rvd.idUtilisateur =:idUser");
		queryProduit.setHint("org.hibernate.cacheable", true);
		queryProduit.setParameter("idUser", idRevendeur);
		try{
			list = new ArrayList<ProduitRevendeur>();
			list = queryProduit.getResultList();
		}catch(Exception exception){
			logger.equals("Une erreur"+exception.getMessage());
		}
		return list;
	}

}
