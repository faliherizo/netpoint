/**
 * 
 */
package com.netPoint.applications.site.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.exception.SQLGrammarException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.netPoint.applications.site.model.CompteBancaire;
import com.netPoint.applications.site.model.EtatCompte;
import com.netPoint.applications.site.model.Fournisseur;
import com.netPoint.applications.site.model.Produit;
import com.netPoint.applications.site.model.ProduitRevendeur;
import com.netPoint.applications.site.model.Revendeur;
import com.netPoint.applications.site.model.User;

/**
 * @author Faliherizo
 *
 */
@Repository
public class DaoProduct implements IDaoProduct {
	 public static final Logger logger = LoggerFactory.getLogger(DaoProduct.class);
	    private EntityManager entityManager;
	    @Autowired
	    private InterfaceDao interfacedao;
	    
	    @PersistenceContext
	    public void setEntityManager(EntityManager entityManager) {
	        this.entityManager = entityManager;
	    }
	/**
		 * @param interfacedao the interfacedao to set
		 */
		public void setInterfacedao(InterfaceDao interfacedao) {
			this.interfacedao = interfacedao;
		}

	/* (non-Javadoc)
	 * @see com.netPoint.applications.site.dao.IDaoProduct#getListProduct()
	 */
	@Override
	public List<Produit> getListProduct() {
		List<Produit> produits = new ArrayList<Produit>();
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
	public void addOrUpdateProduct(Produit produit) {
		logger.debug("merging Product instance");
		try {
			Produit _result = entityManager.merge(produit);
			logger.debug("merge produit successful");
			
		} catch (RuntimeException re) {
			logger.error("merge produit failed", re);
			
		}

	}

	/* (non-Javadoc)
	 * @see com.netPoint.applications.site.dao.IDaoProduct#deleteProduct(com.netPoint.applications.site.model.Produit)
	 */
	@Override
	public void deleteProduct(Produit produit) {
		logger.debug("removing produit instance");
		try {
			entityManager.remove(produit);
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
			Produit produit = findProduitById(pIdProduct);
			
			this.deleteProduct(produit);
			Produit refverifdellete = findProduitById(pIdProduct);
			if(refverifdellete!=null)
				logger.debug("produit non supprimer");
		} catch (Exception e) {
			logger.error("Remove Produit failed", e);
		}
		

	}
	@Override
	public Produit findProduitById(Integer id) {
		//Revendeur revendeur;
	        try {
	        	Produit instance = entityManager.find(Produit.class, id);
	            return instance;
	        } catch (Exception e) {
	            logger.error("Error while loading Produit with clientName " + id, e);
	            return null;
	        }
	}

	@Override
	public List<Produit> getListProductByFrns() {
		List<Produit> produits = new ArrayList<Produit>();
		Authentication auth =SecurityContextHolder.getContext().getAuthentication();
    	User user = interfacedao.findUserByMail(auth.getName());
    	
    	int idfrns = user.getId();
    	
        try {
            String s = "select b from Produit b where b.id= :idfrns";
            Query query = entityManager.createQuery(s);
            query.setParameter("idfrns", idfrns);
            query.setHint("org.hibernate.cacheable", true);
            produits = query.getResultList();
        } catch (Exception e) {
            logger.error("Error while loading Produit", e);
        }
		
		return produits;
	}

	@Override
	public List<Produit> getProduitByFrns(Integer idfrns) {
		List<Produit> produits = new ArrayList<Produit>();
		Fournisseur fournisseur = getFns(idfrns);
        try {
            String s = "select b from Produit b where b.fournisseur =:fournisseur";
            Query query = entityManager.createQuery(s);
            query.setParameter("fournisseur", fournisseur);
            query.setHint("org.hibernate.cacheable", true);
            produits = query.getResultList();
            return produits;
        } catch (Exception e) {
            logger.error("Error while loading Produit", e);
            return null;
        }
		
		
	}
	@Override
	public Fournisseur getFns(Integer idfrns){
		Fournisseur fournisseur = null;
		try {
            String s = "select b from Fournisseur b where b.id =:idfrns";
            Query query = entityManager.createQuery(s);
            query.setParameter("idfrns", idfrns);
            query.setHint("org.hibernate.cacheable", true);
            fournisseur = (Fournisseur) query.getSingleResult();
            return fournisseur;
        } catch (Exception e) {
            logger.error("Error while loading fournisseur", e);
            return null;
        }
		
	}
	@Override
	public List<Produit> getListProductByRev(Integer rev) {
		List<Produit> produits = new ArrayList<Produit>();
		Revendeur revendeur = getRev(rev);
        try {
            String s = "select b.produit from ProduitRevendeur b where b.revendeur =:revendeur";
            Query query = entityManager.createQuery(s);
            query.setParameter("revendeur", revendeur);
            query.setHint("org.hibernate.cacheable", true);
            produits = query.getResultList();
            return produits;
        } catch (Exception e) {
            logger.error("Error while loading Produits", e);
            return null;
        }
	}
	@Override
	public List<Revendeur> getListRevByIdprod(Integer produit) {
		List<Revendeur> produits = new ArrayList<Revendeur>();
		Produit _produit = findProduitById(produit);
        try {
            String s = "select b.revendeur from ProduitRevendeur b where b.produit =:produit";
            Query query = entityManager.createQuery(s);
            query.setParameter("produit", _produit);
            query.setHint("org.hibernate.cacheable", true);
            produits = query.getResultList();
            return produits;
        } catch (Exception e) {
            logger.error("Error while loading Produits", e);
            return null;
        }
	}
	public Revendeur getRev(Integer idRev){
		Revendeur revendeur = null;
		try {
            String s = "select b from Revendeur b where b.id =:idRev";
            Query query = entityManager.createQuery(s);
            query.setParameter("idRev", idRev);
            query.setHint("org.hibernate.cacheable", true);
            revendeur = (Revendeur) query.getSingleResult();
            return revendeur;
        } catch (Exception e) {
            logger.error("Error while loading revendeur", e);
            return null;
        }
		
	}
	@Override
	public List<Produit> getProduitByFrns(Fournisseur fournisseur, Revendeur revendeur) {
		List<Produit> produits = null;
		try {
            String s = "select b.produit from ProduitRevendeur b inner join b.produit p where b.revendeur= :revendeur and p.fournisseur= :fournisseur";// and b.produit.type = :typecommande";
            Query query = entityManager.createQuery(s);
            query.setParameter("fournisseur", fournisseur);
            query.setParameter("revendeur", revendeur);
            
           // query.setParameter("typecommande", typecommande);
            query.setHint("org.hibernate.cacheable", true);
            produits = query.getResultList();
           
		} catch (Exception e) {
            logger.error("Error while loading List<ProduitRevendeur> ", e);
        }
		 return produits;
	}
	@Override
	public Fournisseur findFrnsByUser(User userconnect) {
		
		Fournisseur fournisseur = null;
		try {
            String s = "select b from Fournisseur b where b.user =:userconnect";
            Query query = entityManager.createQuery(s);
            query.setParameter("userconnect", userconnect);
            query.setHint("org.hibernate.cacheable", true);
            fournisseur = (Fournisseur) query.getSingleResult();
           
        } catch (Exception e) {
            logger.error("Error while loading fournisseur", e);
        }
		 return fournisseur;
	}
	@Override
	public Produit getProductById(Integer id) {
		Produit produit = null;
		try {
            String s = "select b from Produit b where b.id =:id";
            Query query = entityManager.createQuery(s);
            query.setParameter("id", id);
            query.setHint("org.hibernate.cacheable", true);
            produit = (Produit) query.getSingleResult();
           
        } catch (Exception e) {
            logger.error("Error while loading produit", e);
        }
		 return produit;
	}
	
}
