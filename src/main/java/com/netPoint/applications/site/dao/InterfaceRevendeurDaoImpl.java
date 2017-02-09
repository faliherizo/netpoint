package com.netPoint.applications.site.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.netPoint.applications.site.model.Commande;
import com.netPoint.applications.site.model.Revendeur;
import com.netPoint.applications.site.util.Parameters;

@Repository
public class InterfaceRevendeurDaoImpl implements InterfaceRevendeurDao{

	public static final Logger logger = LoggerFactory.getLogger(InterfaceRevendeurDaoImpl.class);
	
    private EntityManager entityManager; 
	
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    /**
     * Select the current revendeur par rappaort current User
     * @param idUser 
     * @return Revendeur
     */
	@Override
	public Revendeur getByIdUser(int idUser) {
		Revendeur revendeur = null;
		try{
			revendeur = new Revendeur();
			Query query = entityManager.createQuery(""+
					"SELECT rdv FROM Revendeur AS rdv" +
					" LEFT JOIN rdv.user us " +
					" WHERE us.id =:idUser");
			query.setParameter("idUser", idUser);
			revendeur  = (Revendeur)query.getSingleResult();
		}
		catch(Exception exception){	
			logger.equals("Une erreur"+exception.getMessage());
		}
		return revendeur;
	}
	/**
	 * revendeur list associate of the cuurent revendeur in current User
	 * @param id  
     * @return Revendeur
	 */
    @SuppressWarnings("unchecked")
    @Override
    public List<Revendeur> getAllRevendeurPerimetre(final Integer id){
    	List<Revendeur> revendeurs = null;
		try{
			revendeurs  = new ArrayList<Revendeur>();
			Query query = entityManager.createQuery(""+
					" SELECT rdv FROM Revendeur AS rdv" +
					" LEFT JOIN rdv.revendeur sousrdv " +
					" WHERE sousrdv.id =:idRevendeur");
			query.setParameter("idRevendeur", id);
			revendeurs  = query.getResultList();
		}
		catch(Exception exception){	
			logger.equals("Une erreur"+exception.getMessage());
		}
		return revendeurs;
    }
    /**
     * 
     */
    @SuppressWarnings("unchecked")
	@Override
	public List<Commande> findAllCommandeByProduitAndRevendeur(Integer IdUser, boolean moisCourant) {
		logger.debug("getting list Commande instance with user current");
		List<Commande> listCommande = null;
		Revendeur revendeur = this.getByIdUser(IdUser);//revendeur Current
		List<Revendeur> revendeurs = this.getAllRevendeurPerimetre(revendeur.getId());
		revendeurs.add(revendeur);
		List<Integer> listIdRev = new ArrayList<Integer>();
		for (Revendeur item : revendeurs) {
			listIdRev.add(item.getId());
		}
		Date dateCurrent = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateCurrent);
		int numMoth = calendar.get(Calendar.MONTH)+1;
		int annee = calendar.get(Calendar.YEAR);
		try { 
			listCommande = new ArrayList<Commande>();
			String queryString  = "SELECT cmd"
					+ " FROM Commande cmd"
					+ " LEFT JOIN cmd.revendeur rvd";
			if(moisCourant){
				queryString += " WHERE MONTH(cmd.dateFin)=:mois AND YEAR(cmd.dateFin) =:annee AND cmd.dateFin >:datecurrent AND rvd.id IN (:idRevendeur)";
			//queryString += " WHERE rvd.id IN (:idRevendeur)";
				
				  Query query = entityManager.createQuery(queryString);
				  query.setParameter("datecurrent", dateCurrent);
				  query.setParameter("annee", annee);
				  query.setParameter("mois", numMoth);
				  query.setParameter("idRevendeur", listIdRev);
				  listCommande = query.getResultList();
			}
			else{
				
				  queryString += " WHERE cmd.dateFin <=:datecurrent AND rvd.id IN (:idRevendeur)";
				  Query query = entityManager.createQuery(queryString);
				  query.setParameter("datecurrent", dateCurrent);
				  query.setParameter("idRevendeur", listIdRev);
				  listCommande = query.getResultList();
			}
				 //query.setParameter("idRevendeur", listIdRev);
				//  listCommande = query.getResultList();
	            return listCommande;/***/
		} catch (RuntimeException re) {
			logger.error("Error find listAllCommande search", re);
			return null;
		}
	}
    
    
    /**
     * 
     */
    @SuppressWarnings("unchecked")
	@Override
	public List<Commande> findAllCommandeByProduitAndRevendeur(Integer idProduit,Integer IdUser, boolean moisCourant) {
		logger.debug("getting list Commande instance with user current");
		List<Commande> listCommande = null;
		Revendeur revendeur = this.getByIdUser(IdUser);//revendeur Current
		List<Revendeur> revendeurs = this.getAllRevendeurPerimetre(revendeur.getId());
		revendeurs.add(revendeur);
		List<Integer> listIdRev = new ArrayList<Integer>();
		for (Revendeur item : revendeurs) {
			listIdRev.add(item.getId());
		}
		Date dateCurrent = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateCurrent);
		int numMoth = calendar.get(Calendar.MONTH)+1;
		int annee = calendar.get(Calendar.YEAR);
		try { 
			listCommande = new ArrayList<Commande>();
			String queryString  = "SELECT cmd"
					+ " FROM Commande cmd LEFT JOIN cmd.produit pdr"
					+ " LEFT JOIN cmd.revendeur rvd";
			if(moisCourant){
				queryString += " WHERE MONTH(cmd.dateFin)=:mois AND YEAR(cmd.dateFin) =:annee AND cmd.dateFin >:datecurrent AND rvd.id IN (:idRevendeur) AND pdr.id=:idProduit";
				  Query query = entityManager.createQuery(queryString);
				  query.setParameter("annee", annee);
				  query.setParameter("datecurrent", dateCurrent);
				  query.setParameter("mois", numMoth);
				  query.setParameter("idRevendeur", listIdRev);
				  query.setParameter("idProduit", idProduit);
				  listCommande = query.getResultList();
			}
			else{
				
				  queryString += " WHERE cmd.dateFin <=:datecurrent AND rvd.id IN (:idRevendeur)  AND pdr.id=:idProduit";
				  Query query = entityManager.createQuery(queryString);
				  query.setParameter("datecurrent", dateCurrent);
				  query.setParameter("idRevendeur", listIdRev);
				  query.setParameter("idProduit", idProduit);
				  listCommande = query.getResultList();
			}
				  //listCommande = query.getResultList();
	            return listCommande;/***/
		} catch (RuntimeException re) {
			logger.error("Error find listAllCommande search findAllCommandeByProduitAndRevendeur", re);
			return null;
		}
	}
    /**
     * 
     */
    @SuppressWarnings("unchecked")
	@Override
	public List<Commande> findAllCommandeByProduitAndRevend(Integer idRevendeur,Integer IdUser,  boolean moisCourant) {
		logger.debug("getting list Commande instance with user current");
		List<Commande> listCommande = null;
		Date dateCurrent = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateCurrent);
		int numMoth = calendar.get(Calendar.MONTH)+1;
		int annee = calendar.get(Calendar.YEAR);
		try { 
			listCommande = new ArrayList<Commande>();
			String queryString  = "SELECT cmd"
					+ " FROM Commande cmd LEFT JOIN cmd.produit pdr"
					+ " LEFT JOIN cmd.revendeur rvd";
			if(moisCourant){
				queryString += " WHERE MONTH(cmd.dateFin)=:mois AND YEAR(cmd.dateFin) =:annee AND cmd.dateFin >:datecurrent AND rvd.id =:idRevendeur";
				  Query query = entityManager.createQuery(queryString);
				  query.setParameter("datecurrent", dateCurrent);
				  query.setParameter("annee", annee);
				  query.setParameter("mois", numMoth);
				  query.setParameter("idRevendeur", idRevendeur);
				  listCommande = query.getResultList();
			}else{
				  queryString += " WHERE cmd.dateFin <=:datecurrent AND rvd.id =:idRevendeur  AND pdr.id=:idProduit";
				  Query query = entityManager.createQuery(queryString);
				  query.setParameter("datecurrent", dateCurrent);
				  query.setParameter("idRevendeur", idRevendeur);
				  listCommande = query.getResultList();
			}
	            return listCommande;/***/
		} catch (RuntimeException re) {
			logger.error("Error find listAllCommande search", re);
			return null;
		}
	}
    /**
     * 
     */
    @SuppressWarnings("unchecked")
	@Override
	public List<Commande> findAllCommandeByProduitAndRevend(Integer idRevendeur,Integer idProduit,Integer IdUser,  boolean moisCourant) {
		logger.debug("getting list Commande instance with user current");
		List<Commande> listCommande = null;
		Date dateCurrent = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateCurrent);
		int numMoth = calendar.get(Calendar.MONTH)+1;
		int annee = calendar.get(Calendar.YEAR);
		try { 
			listCommande = new ArrayList<Commande>();
			String queryString  = "SELECT cmd"
					+ " FROM Commande cmd LEFT JOIN cmd.produit pdr"
					+ " LEFT JOIN cmd.revendeur rvd";
			if(moisCourant){
				queryString += " WHERE MONTH(cmd.dateFin)=:mois AND YEAR(cmd.dateFin) =:annee AND cmd.dateFin >:datecurrent";
				if(idRevendeur!=null)
				queryString += " AND rvd.id =:idRevendeur";
				if(idProduit!=null)
				queryString +=	" AND pdr.id=:idProduit";
				  Query query = entityManager.createQuery(queryString);
				  query.setParameter("annee", annee);
				  query.setParameter("datecurrent", dateCurrent);
				  query.setParameter("mois", numMoth);
				  if(idRevendeur!=null)
				  query.setParameter("idRevendeur", idRevendeur);
				  if(idProduit!=null)
				  query.setParameter("idProduit", idProduit);
				  listCommande = query.getResultList();
				  
			}else{
				
				  queryString += " WHERE cmd.dateFin <=:datecurrent";
				  if(idRevendeur!=null)
						queryString += " AND rvd.id =:idRevendeur";
						if(idProduit!=null)
						queryString +=" AND pdr.id=:idProduit";
				  Query query = entityManager.createQuery(queryString);
				  query.setParameter("datecurrent", dateCurrent);
				  if(idRevendeur!=null)
				  query.setParameter("idRevendeur", idRevendeur);
				  if(idProduit!=null)
				  query.setParameter("idProduit", idProduit);
				  listCommande = query.getResultList();
			}
	            return listCommande;/***/
		} catch (RuntimeException re) {
			logger.error("Error find listAllCommande search", re);
			return null;
		}
	}

	@Override
	public List<Revendeur> findAllRevendeur() {
		  List<Revendeur> revendeurs = new ArrayList<Revendeur>();
	        try {
	            String s = "select b from Revendeur b where";
	            Query query = entityManager.createQuery(s);
	            query.setHint("org.hibernate.cacheable", true);
	            revendeurs = query.getResultList();
	        } catch (Exception e) {
	            logger.error("Error while loading revendeurs", e);
	        }
	        return revendeurs;
	}
}
