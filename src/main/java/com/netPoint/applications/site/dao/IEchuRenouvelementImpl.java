package com.netPoint.applications.site.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netPoint.applications.site.dto.view.ViewRenouvellementDuMois;
import com.netPoint.applications.site.model.Revendeur;

@Repository
public class IEchuRenouvelementImpl implements IEchuRenouvelement {
	
	
	@Autowired
	private InterfaceRevendeurDao interfaceRevendeurDao;
  
	private static Date dateCurrent = new Date();
	
	private EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ViewRenouvellementDuMois> ListgetRenouvellementByCritere(
		final String critereProduit,final String critereRevedeur, final Integer idUser) {
		List<ViewRenouvellementDuMois> list = null;
		/*
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateCurrent);
		int numMoth = calendar.get(Calendar.MONTH);
		try {
		List<Integer> listIdRevendeur = this.getListId(idUser);
		Query query  = null;
		String queryString  = "SELECT "
			+ " DISTINCT new com.netPoint.applications.site.dto.view.ViewRenouvellementDuMois(rvd.id,sc.nom,"
			+ " cmd.montant,dv.libelle, cmd.quantite,"
			+ " pdr.nomProduit, cmd.dateFin, rvd.idUtilisateur)"
			+ " FROM Commande cmd LEFT JOIN cmd.produit pdr"
			+ " LEFT JOIN cmd.revendeur rvd"
			+ " LEFT JOIN rvd.compteBancaire cpB"
			+ " LEFT JOIN cpB.devise dv "
			+ " LEFT JOIN rvd.societe sc";
		queryString += " WHERE MONTH(cmd.dateCommande)=:mois AND cmd.dateCommande < :dateDuJour";
		
		if(critereRevedeur.equals("0") && critereProduit.equals("0")){// tous produit + tous revendeur
			queryString += " AND rvd.id IN (:idRevendeur)";
			query= entityManager.createQuery(queryString);
			query.setParameter("idRevendeur", listIdRevendeur);
		}
		if(critereRevedeur!=null && !critereRevedeur.trim().equals("") && Integer.decode(critereRevedeur)>0){ // revendeur seullement
			queryString += " AND rvd.id=:idRdv";
			query= entityManager.createQuery(queryString);
			query.setParameter("idRdv",Integer.decode(critereRevedeur));
		}
		if(critereProduit!=null && !critereProduit.trim().equals("") && Integer.decode(critereProduit)>0){// produit seullement
			queryString += " AND rvd.id IN (:idRevendeur)";
			queryString += " AND pdr.id=:id";
			query= entityManager.createQuery(queryString);
			query.setParameter("idRevendeur", listIdRevendeur);
			query.setParameter("id",Integer.decode(critereProduit));
		}
		if(critereProduit==null || critereProduit.trim().equals("") && critereRevedeur.equals("0") && Integer.decode(critereProduit)>0){//tous revendeurs
			queryString += " AND rvd.id IN (:idRevendeur)";
			query= entityManager.createQuery(queryString);
			query.setParameter("idRevendeur", listIdRevendeur);
			//query.setParameter("id",Integer.decode(critereProduit));
		}
		if(critereRevedeur==null || critereRevedeur.trim().equals("") && critereRevedeur.equals("0") && Integer.decode(critereRevedeur)>0){//tous produit
			queryString += " AND rvd.id IN (:idRevendeur)";
			query= entityManager.createQuery(queryString);
			query.setParameter("idRevendeur", listIdRevendeur);
			//query.setParameter("id",Integer.decode(critereProduit));
		}
		if(critereProduit!=null && !critereProduit.trim().equals("") && Integer.decode(critereProduit)>0){//produit seulement 
			queryString += " AND rvd.id IN (:idRevendeur)";
			queryString += " AND pdr.id=:id";
			query= entityManager.createQuery(queryString);
			query.setParameter("idRevendeur", listIdRevendeur);
			query.setParameter("id",Integer.decode(critereProduit));
		}
		if(critereProduit!=null &&critereRevedeur!=null  && Integer.decode(critereProduit)>0  && Integer.decode(critereRevedeur)>0){// produit revendeur
			queryString += " AND rvd.id =:idRevendeur";
			queryString += " AND pdr.id=:id";
			query= entityManager.createQuery(queryString);
			query.setParameter("idRevendeur",Integer.decode(critereRevedeur));
			query.setParameter("id",Integer.decode(critereProduit));
		}
		if(critereProduit!=null &&critereRevedeur!=null  && Integer.decode(critereProduit)==0  && Integer.decode(critereRevedeur)>0){// produit revendeur
			queryString += " AND rvd.id =:idRevendeur";
			query= entityManager.createQuery(queryString);
			query.setParameter("idRevendeur",Integer.decode(critereRevedeur));
		}
		// tous produit + revendeur
		
		
//		if(critereProduit!=null &&critereRevedeur!=null  && Integer.decode(critereProduit)>0  && Integer.decode(critereRevedeur)>0){// produit revendeur
//			queryString += " AND rvd.id =:idRevendeur";
//			queryString += " AND pdr.id=:id";
//			query.setParameter("idRevendeur",Integer.decode(critereRevedeur));
//			query.setParameter("id",Integer.decode(critereProduit));
//		}
		if(critereProduit==null && critereRevedeur==null || critereProduit.equals("") || critereRevedeur.equals("")){
			return list = null;
		}
		// tous revendeur + produit
		// param null null 
		query.setParameter("mois",numMoth);
		query.setParameter("dateDuJour",dateCurrent);
		list = new ArrayList<ViewRenouvellementDuMois>();
		list = query.getResultList();
		} catch (Exception exception) {
			exception.getMessage();
		}*/
		return null;
	}
	
	/*private List<Integer> getListId(final Integer idUser) {
		List<Revendeur> list = null;
		list = interfaceRevendeurDao.getAllByIdUser(idUser);
		List<Integer> listId = new ArrayList<Integer>();
		if (list != null) {
			for (Revendeur revendeur : list) {
				listId.add(revendeur.getId());
			}
		}
		return listId;
	}*/

}
