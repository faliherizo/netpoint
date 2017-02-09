/**
 * 
 */
package com.netPoint.applications.site.service.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.netPoint.applications.site.dao.IDaoCommande;
import com.netPoint.applications.site.dao.InterfaceUserDao;
import com.netPoint.applications.site.model.Client;
import com.netPoint.applications.site.model.ClientRevendeur;
import com.netPoint.applications.site.model.Commande;
import com.netPoint.applications.site.model.MailConfig;
import com.netPoint.applications.site.model.Revendeur;
import com.netPoint.applications.site.model.User;
import com.netPoint.applications.site.service.notify.Notifier;

/**
 * @author Faliherizo
 *
 */
@Component("JobMailService")
public class JobMailService /*extends QuartzJobBean*/ implements IJobMailService {
	@Autowired
	private Notifier mailService;
	@Autowired
	 private InterfaceUserDao interfaceUserDao;
	@Autowired
	private IDaoCommande daoCommande;
	public void setDaoCommande(IDaoCommande daoCommande) {
		this.daoCommande = daoCommande;
	}
	public void setInterfaceUserDao(InterfaceUserDao interfaceUserDao) {
		this.interfaceUserDao = interfaceUserDao;
	}
	@Scheduled(cron = "0 * * * * MON-FRI")
	public void lancerCron(){
		//traitement du cron
		 mailService.sendMail(getUserListInformation());
	}
	@Scheduled(cron = "2 * * * * MON-FRI")
	public void lancercronnewslletre(){
		
		/*List<User> _users= _users = interfaceUserDao.getListUserToSendNewsLettre();
		if(_users !=null && _users.size()!=0)
			mailService.sendMail(_users);*/
	}  
	///
	///lancer crons tous les 15joours
	///
	@Scheduled(fixedRate=1296000000)
	public void lancercron15jours(){
		
	}
	///Lancer tous les jours à 1AM
	@Scheduled(cron = "* * 5 * * ?")
	public void lancercron1jours() throws MessagingException{
		List<Revendeur> revendeurs=interfaceUserDao.getListRevendeur();
		for (Revendeur revendeur : revendeurs) 
		{
			//Send newslettre by client revendeur
			for (ClientRevendeur clientRevendeur : revendeur.getClientRevendeurs()) 
			{
				Client client=clientRevendeur.getClient();
				Date datefin=client.getUser().getDateFin();
				Date datenow= new Date();
				for (Commande commande : client.getListCommande()) {
					if(commande.getDateFin().compareTo(datenow)==30)
					{
						if(commande.getTypeCommande().getCode().equals("cmd"))
						{
							String contenu = null; String subject = null;String logo = null;
							for (MailConfig maildtl : revendeur.getMailhdr().getMaildtl()) 
							{
								if(maildtl.getCodemail().equals("m1"))
								{
									contenu= maildtl.getContenu();
									subject = maildtl.getSubject();
									logo= maildtl.getLogo();
								}
							}
							//Envoie mail aux client ayant des commandes expirant dans 2 jours
							mailService.gestionMailNotify(client.getUser().getLogin(), revendeur.getUser().getLogin(), contenu, logo,subject);
						}
					}
					if(commande.getDateFin().compareTo(datenow)==15)
					{
						if(commande.getTypeCommande().getCode().equals("cmd"))
						{
							//Envoie mail aux client ayant des commandes expirant dans 15 jours
							String contenu = null; String subject = null;String logo = null;
							for (MailConfig maildtl : revendeur.getMailhdr().getMaildtl()) 
							{
								if(maildtl.getCodemail().equals("m2"))
								{
									contenu= maildtl.getContenu();
									subject = maildtl.getSubject();
									logo= maildtl.getLogo();
								}
							}
							mailService.gestionMailNotify(client.getUser().getLogin(), revendeur.getUser().getLogin(), contenu, logo, subject);
						}
						if(commande.getTypeCommande().getCode().equals("Essai"))
						{
							String contenu = null; String subject = null;String logo = null;
							for (MailConfig maildtl : revendeur.getMailhdr().getMaildtl()) 
							{
								if(maildtl.getCodemail().equals("m4"))
								{
									contenu= maildtl.getContenu();
									subject = maildtl.getSubject();
									logo= maildtl.getLogo();
								}
							}
							mailService.gestionMailNotify(client.getUser().getLogin(), revendeur.getUser().getLogin(),contenu, logo, subject);
						}
					}
					if(commande.getDateFin().compareTo(datenow)==2)
					{
						if(commande.getTypeCommande().getCode().equals("cmd"))
						{
							String contenu = null; String subject = null;String logo = null;
							for (MailConfig maildtl : revendeur.getMailhdr().getMaildtl()) 
							{
								if(maildtl.getCodemail().equals("m3"))
								{
									contenu= maildtl.getContenu();
									subject = maildtl.getSubject();
									logo= maildtl.getLogo();
								}
							}
							//Envoie mail aux client ayant des commandes expirant dans 2 jours
							mailService.gestionMailNotify(client.getUser().getLogin(), revendeur.getUser().getLogin(), contenu, logo,subject);
						}
						if(commande.getTypeCommande().getCode().equals("Essai"))
						{
							String contenu = null; String subject = null;String logo = null;
							for (MailConfig maildtl : revendeur.getMailhdr().getMaildtl()) 
							{
								if(maildtl.getCodemail().equals("m3"))
								{
									contenu= maildtl.getContenu();
									subject = maildtl.getSubject();
									logo= maildtl.getLogo();
								}
							}
							mailService.gestionMailNotify(client.getUser().getLogin(), revendeur.getUser().getLogin(),contenu, logo, subject);
						
						}
					}
					
				}
				if(datefin.compareTo(datenow)==30)
				{
					//renouvellement
					String contenu = null; String subject = null;String logo = null;
					for (MailConfig maildtl : revendeur.getMailhdr().getMaildtl()) 
					{
						if(maildtl.getCodemail().equals("m7"))
						{
							contenu= maildtl.getContenu();
							subject = maildtl.getSubject();
							logo= maildtl.getLogo();
						}
					}
					mailService.gestionMailNotify(client.getUser().getLogin(), revendeur.getUser().getLogin(),contenu, logo, subject);
				
				}
				else if(datefin.compareTo(datenow)==60)
				{
					String contenu = null; String subject = null;String logo = null;
					for (MailConfig maildtl : revendeur.getMailhdr().getMaildtl()) 
					{
						if(maildtl.getCodemail().equals("m8"))
						{
							contenu= maildtl.getContenu();
							subject = maildtl.getSubject();
							logo= maildtl.getLogo();
						}
					}
					mailService.gestionMailNotify(client.getUser().getLogin(), revendeur.getUser().getLogin(),contenu, logo, subject);
				
				}
				//mailService.sendMail(getUserListInformation());
			}
		}
	}
	
	 /* @Override	
	  protected void executeInternal(JobExecutionContext context)
	
	   throws JobExecutionException {
	
		  mailService.sendMail(getUserListInformation());
	  }*/
	//Traitement mail
	 /**
	
	  * returns a list of users.
	
	  * @return userList
	
	  */
	
	  private List<User> getUserListInformation() {
	
	    List<User> userList = new ArrayList<User>();
	
	    User user = new User();
	    return userList;
	
	  }
	 /* Setter Methods */
	
	  public void setMailService(Notifier mailService) {
	
	    this.mailService = mailService;
	
	  }


}
