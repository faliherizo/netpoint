package com.netPoint.applications.site.dao;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.netPoint.applications.site.model.Client;
import com.netPoint.applications.site.model.Commande;
import com.netPoint.applications.site.model.Fournisseur;
import com.netPoint.applications.site.model.Revendeur;
import com.netPoint.applications.site.model.User;
import com.netPoint.applications.site.util.Parameters;

@Repository
public class InterfaceUserDaoImpl implements InterfaceUserDao {

	private EntityManager entityManager; 
	public static final Logger logger = LoggerFactory.getLogger(InterfaceUserDaoImpl.class);
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
	this.entityManager = entityManager;
	}
	
	@Override
	public User findByLogin(final String login) {
		User user = null;
		try{
			Query query = entityManager.createQuery("SELECT user FROM User AS user " +
	         "WHERE user.login=:login");
			query.setHint("org.hibernate.cacheable", true);
			query.setParameter("login", login);
			user = (User)query.getSingleResult();
		}
		catch(Exception exception){
			logger.equals("Une erreur"+exception.getMessage());
		}
		return user;
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<User> getListUserById(List<Integer> listId){
		List<User> user = null;
		try{
			Query query = entityManager.createQuery("SELECT user FROM User AS user " +
	         "WHERE user.id IN (:id)");
			query.setHint("org.hibernate.cacheable", true);
			query.setParameter("id", listId);
			user = query.getResultList();
		}
		catch(Exception exception){
			logger.equals("Une erreur"+exception.getMessage());
		}
		return user;
	}

	@Override
	public List<User> getListFrnsByUserConnect(User user) {
		List<User> listuser = null;
		try{
			Query query = entityManager.createQuery("SELECT user FROM User AS user " +
	         "WHERE user.id IN (:id)");
			query.setHint("org.hibernate.cacheable", true);
			//query.setParameter("id", listId);
			listuser = query.getResultList();
		}
		catch(Exception exception){
			logger.equals("Une erreur"+exception.getMessage());
		}
		return listuser;
	}
	private InterfaceDao interfaceDao;
	@Autowired
	public void setInterfaceDao(InterfaceDao interfaceDao) {
		this.interfaceDao = interfaceDao;
	}

	/**
	 * Get List frns to user connect
	 */
	@Override
	public List<Fournisseur> getListFournisseur() {
		List<Fournisseur> listuser = null;
		try{
			Authentication auth =SecurityContextHolder.getContext().getAuthentication();
	    	User user = interfaceDao.findUserByMail(auth.getName());
			Query query = entityManager.createQuery("SELECT f FROM Fournisseur f where f.user.group = :group");
			query.setParameter("group", Parameters.group);
			query.setHint("org.hibernate.cacheable", true);
			listuser = query.getResultList();
		}
		catch(Exception exception){
			logger.equals("Une erreur"+exception.getMessage());
		}
		return listuser;
	}
	/**
	 * Get List frns to user connect
	 */
	@Override
	public List<Revendeur> getListRevendeur() {
		List<Revendeur> listRevendeur = null;
		try{
			Query query = entityManager.createQuery("SELECT f FROM Revendeur f");
			query.setHint("org.hibernate.cacheable", true);
			listRevendeur = query.getResultList();
		}
		catch(Exception exception){
			logger.equals("Une erreur"+exception.getMessage());
			
		}
		return listRevendeur;
	}
/*	@Override
	public List<Revendeur> getListClientByRevendeur() {
		List<Revendeur> listRevendeur = null;
		try{
			Query query = entityManager.createQuery("SELECT f FROM Revendeur f");
			query.setHint("org.hibernate.cacheable", true);
			listRevendeur = query.getResultList();
		}
		catch(Exception exception){
			logger.equals("Une erreur"+exception.getMessage());
			
		}
		return listRevendeur;
	}*/
	@Override
	public List<User> getListUserToSendNewsLettre() {
		List<User> listuser = null;
		try{
			Query query = entityManager.createQuery("SELECT user FROM User AS user where user.newslettre==1");
			query.setHint("org.hibernate.cacheable", true);
			//query.setParameter("id", listId);
			listuser = query.getResultList();
			return listuser;
		}
		catch(Exception exception){
			logger.equals("Une erreur"+exception.getMessage());
			return null;
		}
		
		
	}

	@Override
	public int getMaxValueGroup() {
		int maxvalgroup=0;
		try{
			Query query = entityManager.createQuery("SELECT MAX(user.group) FROM User AS user");
			query.setHint("org.hibernate.cacheable", true);
			Integer value=  (Integer) query.getSingleResult();
			maxvalgroup=value;
			return maxvalgroup;
		}
		catch(Exception exception){
			logger.equals("Une erreur"+exception.getMessage());
			return maxvalgroup;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
    public List<Commande> findAllCommande(Client client) {
        List<Commande> commandes = new ArrayList<Commande>();
        try {
            String s = "select b from Commande b where client= :client";
            Query query = entityManager.createQuery(s);
            query.setParameter("client", client);
            query.setHint("org.hibernate.cacheable", true);
            commandes = query.getResultList();
        } catch (Exception e) {
            logger.error("Error while loading commandes by client", e);
        }
        return commandes;
    }
	
	
	
}
