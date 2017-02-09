/**
 * 
 */
package com.netPoint.applications.site.service;

import java.util.List;

import org.osgi.service.application.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.netPoint.applications.site.controller.MailHdrController;
import com.netPoint.applications.site.dao.IDaoMail;
import com.netPoint.applications.site.model.MailConfig;
import com.netPoint.applications.site.model.MailHdr;
import com.netPoint.applications.site.service.notify.Notifier;

/**
 * @author Faliherizo
 *
 */
@Service
public class ServiceMailConfig implements IServiceMailConfig {
	public static final Logger logger = LoggerFactory.getLogger(ServiceMailConfig.class);
	@Autowired
	private IDaoMail daoMail;
	@Autowired
	private Notifier notify;
	/**
	 * @param notify the notify to set
	 */
	public void setNotify(Notifier notify) {
		this.notify = notify;
	}
	public void setDaoMail(IDaoMail daoMail) {
		this.daoMail = daoMail;
	}
	@Override
	public List<MailConfig> ListMailPersonnalisable() {
		
		return daoMail.ListMailPersonnalisable();
	}
	@Override
	public MailConfig getMailByCode(String code) {
		
		return daoMail.getMailByCode(code);
	}
	@Override
	public MailConfig getMailByCode(Integer id) {
		
		return daoMail.getMailByCode(id);
	}
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public MailConfig SaveOrUpdate(MailConfig mail) {
		
		return daoMail.SaveOrUpdate(mail);
	}
	@Override
	public MailHdr getMailHdrById(Integer id) {
		return daoMail.getMailHdrById(id);
	}
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public MailHdr SaveOrUpdate(MailHdr mailhdr) {
		try {
			for (MailConfig mail : mailhdr.getMaildtl() ) {
				//mail.setMailhdr(mailhdr);
				//daoMail.SaveOrUpdate(mail);
				//send mail
				/*if(mail.getActive())
					notify.gestionMailNotify(mailhdr.getMailreception(), "faliherizo@gmail.com", mail.getDescription(), "footer message", mail.getSubject());
				*/
				mail.setMailhdr(mailhdr);
				daoMail.SaveOrUpdate(mail);
			}
		} catch (Exception e) {
			logger.error("Error lors d'envoie des mails", e);
			throw new ApplicationContextException(e.getMessage());
		}
		return daoMail.SaveOrUpdate(mailhdr);
	}
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void TestSendMail(MailHdr mailhdr){
		try {
			for (MailConfig mail : mailhdr.getMaildtl() ) {
				if(mail.getEnvoie())
					notify.gestionMailNotify(mailhdr.getMailreception(), "faliherizo@gmail.com", mail.getDescription(), "footer message", mail.getSubject());
			}
		} catch (Exception e) {
			logger.error("Error lors d'envoie des mails", e);
			throw new ApplicationContextException(e.getMessage());
		}
	}
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public MailHdr Save(MailHdr mailhdr) {
		try {
			try {
				for (MailConfig mail : mailhdr.getMaildtl() ) {
					mail.setMailhdr(mailhdr);
					daoMail.SaveOrUpdate(mail);
				}
			} catch (Exception e) {
				throw new ApplicationContextException(e.getMessage());
			}	
			return daoMail.SaveOrUpdate(mailhdr);
		} catch (Exception e) {
			logger.error("Error lors d'envoie des mails", e);
			throw new ApplicationContextException(e.getMessage());
		}
		
	}

}
