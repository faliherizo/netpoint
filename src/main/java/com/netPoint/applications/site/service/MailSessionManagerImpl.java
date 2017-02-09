/**
 * 
 */
package com.netPoint.applications.site.service;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.persistence.Transient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.directwebremoting.annotations.RemoteProxy;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Faliherizo
 *
 */
@Service("mailspringService")
@RemoteProxy(name = "mailservices")
public class MailSessionManagerImpl implements MailSessionManager {
	
	 public static final Logger logger = LoggerFactory.getLogger(InterfaceServiceImpl.class);
	 private transient Session session;

	 
	 private transient Store   store;


	  @Value("#{cmv_mail['cmv.mail.receiver.port']}")
	  private transient int     port;

	  @Value("#{cmv_mail['cmv.mail.receiver.host']}")
	  private transient String  host;

	  @Value("#{cmv_mail['cmv.mail.userName']}")
	  private transient String  username;

	  @Value("#{cmv_mail['cmv.mail.password']}")
	  private transient String  password;

	  /* (non-Javadoc)
	   * @see com.cmv.service.applicatif.notifications.MailSessionManager#getSession()
	   */
	  @Override
	  public Session getSession() {
	    return this.session;
	  }

	  /* (non-Javadoc)
	   * @see com.cmv.service.applicatif.notifications.MailSessionManager#getStore()
	   */
	  @Override
	  public Store getStore() {
	    return this.store;
	  }

	  public void setSession(Session session) {
	    this.session = session;
	  }

	  public void setStore(Store store) {
	    this.store = store;
	  }
	  
	  /* (non-Javadoc)
	   * @see com.cmv.service.applicatif.notifications.MailSessionManager#connect()
	   */
	  @Override
	  public boolean connect() {
	    boolean res = false;
	    this.logger.info("******************************DEBUT*******************************");
	    try {
	      this.logger.info("Debut connexion vers le serveur mail : " + this.host + ":" + this.port);
	      this.store.connect(this.host, this.port, this.username, this.password);
	      this.logger.info("Fin connexion : Status OK");
	      res = this.store.isConnected();
	    } catch (NoSuchProviderException e) {
	      this.logger.info("Connexion échoué (NoSuchProviderException):" + e.getMessage());
	      res = false;
	    } catch (MessagingException e) {
	      this.logger.info("Connexion échoué (MessagingException):" + e.getMessage());
	      res = false;
	    } finally {
	      this.logger.info("***************************FIN*************************************");
	    }
	    return res;
	  }

	  /* (non-Javadoc)
	   * @see com.cmv.service.applicatif.notifications.MailSessionManager#disconnect()
	   */
	  @Override
	  public void disconnect() {
	    this.logger.info("******************************DEBUT*******************************");
	    try {
	      this.logger.info("Déconnexion du service mail ...");
	      if (this.store.isConnected()) {
	        this.store.close();
	      }
	    } catch (MessagingException e) {
	      this.logger.info("Déconnexion échoué...");
	    } finally {
	      this.logger.info("***************************FIN*************************************");
	    }
	  }
	  
	  /* (non-Javadoc)
	   * @see com.cmv.service.applicatif.notifications.MailSessionManager#isConnected()
	   */
	  @Override
	  public boolean isConnected(){
	    return this.store.isConnected(); 
	  }

}
