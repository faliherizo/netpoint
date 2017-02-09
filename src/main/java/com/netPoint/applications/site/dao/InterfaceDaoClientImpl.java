/**
 * 
 */
package com.netPoint.applications.site.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netPoint.applications.site.model.Client;
import com.netPoint.applications.site.model.ClientRevendeur;
import com.netPoint.applications.site.model.ClientRevendeurId;
import com.netPoint.applications.site.model.Commande;
import com.netPoint.applications.site.model.CompteBancaire;
import com.netPoint.applications.site.model.EtatCommande;
import com.netPoint.applications.site.model.Revendeur;
import com.netPoint.applications.site.model.User;
import com.netPoint.applications.site.service.notify.Notifier;

/**
 * @author Faliherizo
 *
 */
@Repository
public class InterfaceDaoClientImpl implements  InterfaceDaoClient{
	  public static final Logger logger = LoggerFactory.getLogger(InterfaceDaoImpl.class);
	    private EntityManager entityManager;
		@Autowired
		private Notifier notifier;

		/**
		 * @param notifier the notifier to set
		 */
		public void setNotifier(Notifier notifier) {
			this.notifier = notifier;
		}

		@PersistenceContext
	    public void setEntityManager(EntityManager entityManager) {
	        this.entityManager = entityManager;
	    }

		@Override
		public List<Client> findAllClient() {
			List<Client> clients = new ArrayList<Client>();
	        try {
	            String s = "select b from Client b";
	            Query query = entityManager.createQuery(s);
	            query.setHint("org.hibernate.cacheable", true);
	            clients = query.getResultList();
	            return clients;
	        } catch (Exception e) {
	            logger.error("Error while loading Client", e);
	            return new ArrayList<Client>();
	        }
		}



		@Override
		public List<Client> findAllClientBy(Integer IdSociete, Integer IdEtat,
				String mail, String siret, String rcs, String numero,
				Integer IdRevendeur, String NumClientRev, String customerkey,
				String nom, String prenoms, String telephone) {
			List<Client> clients = new ArrayList<Client>();
	       /* try {
	            String queryString = "select b from Client b where 1";
	            
	            if(IdEtat!=null)
	            	queryString +="AND b.user.id =:IdEtat";
	            if(IdSociete!=null){
	            queryString +=" AND b.societe.id =:IdSociete" ;
	            }
	            if(!mail.isEmpty()){
	            	 queryString +=" AND b.user.login =:mail";
	            }
	            if(!siret.isEmpty())
	            	queryString +=" AND b.siret=:siret ";
	            if(!rcs.isEmpty())
	            	queryString +=" AND b.rcs=:rcs ";
	            if(!numero.isEmpty()){
	            	queryString +=" AND b.codeClient=:codeClient ";
	            }
	            if (IdRevendeur!=null) {
	            	queryString +="AND b.revendeur.id =:IdRevendeur";
				}
	            if(!NumClientRev.isEmpty()){
	            	//queryString +=" AND b.codeClient=:codeClient ";
	            	
	            }
	            if(!customerkey.isEmpty()){
	            	queryString +="AND b.customerKey =:customerKey";
	            }
	            if (!nom.isEmpty()) {
					queryString +="AND b.user.nom =:nom";
				}	
	            if(!prenoms.isEmpty()){
	            	queryString +="AND b.user.prenom =:prenom";
	            }
	            if (!telephone.isEmpty()) {
					queryString += "AND b.user.telephone =:telephone";
				}
	            
	            Query query = entityManager.createQuery(queryString);
	            if(IdEtat!=null)
	            	query.setParameter("IdEtat", IdEtat);
	            if(IdSociete!=null)
	            	query.setParameter("IdSociete", IdSociete);
	            if(!mail.isEmpty())
	            	query.setParameter("mail", mail);
	            if(!siret.isEmpty())
	            	query.setParameter("siret", siret);
	            if (!rcs.isEmpty())
	            	query.setParameter("rcs", rcs);
	            
	            if (!numero.isEmpty())
		            query.setParameter("codeClient", numero);
	            
	            if(IdRevendeur!=null)
	            	query.setParameter("IdRevendeur", IdRevendeur);
	            if(!customerkey.isEmpty())
	            	query.setParameter("customerKey", customerkey);
	            if (!nom.isEmpty())
	            	query.setParameter("nom", nom);
	            if(!prenoms.isEmpty())
	            	query.setParameter("prenom", prenoms);
	            
	            if (!telephone.isEmpty())
	            	query.setParameter("telephone", telephone);
	            
	            if(!NumClientRev.isEmpty()){
	            	//query.setParameter("NumClientRev", NumClientRev);
	            	}
	            query.setHint("org.hibernate.cacheable", true);
	            clients = query.getResultList();
	            return clients;
	        } catch (Exception e) {
	            logger.error("Error while loading Client", e);
	            return new ArrayList<Client>();
	        }*/
			return clients;
		}

		@Override
		public Client saveOrUpdateClient(Client client, CompteBancaire comptebancaire, User user) {
			logger.debug("merging client instance");
			try {
				CompteBancaire compte= entityManager.merge(comptebancaire);
				User usersave= entityManager.merge(user);
				client.setUser(usersave);
				//client.setCompteBancaire(compte);
				entityManager.merge(client);
				return client;		
			} catch (RuntimeException re) {
				logger.error("merge client failed", re);
				return null;
			}
			
		}

		@Override
		public ClientRevendeur saveOrUpdateClientRev(
				ClientRevendeur clientRevendeur, Revendeur revendeur) {
			//Ajout du client
			Client client = clientRevendeur.getClient();
			//Ajout du code bancaire
			/*CompteBancaire compteBancaire = client.getCompteBancaire();
			compteBancaire = entityManager.merge(compteBancaire);
			client.setCompteBancaire(compteBancaire);*/
			client  = entityManager.merge(client);
			
			clientRevendeur.setClient(client);
			clientRevendeur.setRevendeur(revendeur);
			//Ajout du client du revendeur
			clientRevendeur= entityManager.merge(clientRevendeur);
			return clientRevendeur;
		}
		@Override
		public Revendeur getRevendeurConnect(User user) {
			logger.debug("getting revendeur instance connecté");
			Integer iduser= user.getId();
			try {
				String s = "select b from Revendeur b where b.user =:user";
				
				Query query = entityManager.createQuery(s);
				query.setParameter("user", user);
	            query.setHint("org.hibernate.cacheable", true);
	            Revendeur revendeur=(Revendeur) query.getSingleResult();  
	           
	            return revendeur;
			}
			catch (Exception e) {
				logger.error("Error while loading revendeur failed coonecté ", e);
	            return null;
			}
		}

		@Override
		public Commande getClientRevById(int id) {
			
			//Get id ClientRevendeurId
			
			return null;
		}
}
