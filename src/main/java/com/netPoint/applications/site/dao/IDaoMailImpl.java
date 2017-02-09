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
import org.springframework.stereotype.Repository;

import com.netPoint.applications.site.model.MailConfig;
import com.netPoint.applications.site.model.MailHdr;
import com.netPoint.applications.site.model.Produit;
import com.netPoint.applications.site.model.Revendeur;
import com.netPoint.applications.site.model.Societe;

/**
 * @author Faliherizo
 *
 */
@Repository
public class IDaoMailImpl implements IDaoMail {
	 public static final Logger logger = LoggerFactory.getLogger(IDaoMailImpl.class);
	 private EntityManager entityManager;
	    @PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
			this.entityManager = entityManager;
		}
	/* (non-Javadoc)
	 * @see com.netPoint.applications.site.dao.IDaoMail#ListMailPersonnalisable()
	 */
	@Override
	public List<MailConfig> ListMailPersonnalisable() {
		List<MailConfig> listmail = new ArrayList<MailConfig>();
        try {
            String s = "select b from MailConfig b ";
            Query query = entityManager.createQuery(s);
            query.setHint("org.hibernate.cacheable", true);
            listmail = query.getResultList();
            return listmail;
        } catch (Exception e) {
            logger.error("Error while loading Produits", e);
            return null;
        }
	}
	@Override
	public MailConfig getMailByCode(String code){
		return null;
		
	}
	@Override
	public MailConfig getMailByCode(Integer id) {
		logger.debug("getting MailConfig instance with id: " + id);
		
			 
				try {
					MailConfig instance = entityManager.find(MailConfig.class, id);
					logger.debug("find MailConfig ById successful");
					return instance;
				} catch (RuntimeException re) {
					logger.error("findById MailConfig failed", re);
					return null;
				}
	}
	@Override
	public MailConfig SaveOrUpdate(MailConfig mail) {
		try {
		return entityManager.merge(mail);
		} catch (RuntimeException re) {
			logger.error("findById MailConfig failed", re);
			return null;
		}
	}
	@Override
	public MailHdr getMailHdrById(Integer id) {
		logger.debug("getting MailConfig instance with id: " + id);
				try {
					MailHdr instance = entityManager.find(MailHdr.class, id);
					logger.debug("find MailConfig ById successful");
					return instance;
				} catch (RuntimeException re) {
					logger.error("findById MailConfig failed", re);
					return null;
				}
	}
	@Override
	public MailHdr SaveOrUpdate(MailHdr mailHdr) {	
		try {
			return entityManager.merge(mailHdr);
		} catch (RuntimeException re) {
			logger.error("save or update mailhdr error", re);
			return null;
		}
	}

}
