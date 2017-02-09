package com.netPoint.applications.site.service;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.osgi.service.application.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.netPoint.applications.site.dao.IDaoCommande;
import com.netPoint.applications.site.dao.InterfaceDao;
import com.netPoint.applications.site.model.Client;
import com.netPoint.applications.site.model.ClientRevendeur;
import com.netPoint.applications.site.model.ClientRevendeurId;
import com.netPoint.applications.site.model.Commande;
import com.netPoint.applications.site.model.CommandeRevendeur;
import com.netPoint.applications.site.model.CompteBancaire;
import com.netPoint.applications.site.model.DureeCommande;
import com.netPoint.applications.site.model.EtatCommande;
import com.netPoint.applications.site.model.MailConfig;
import com.netPoint.applications.site.model.Prix;
import com.netPoint.applications.site.model.Produit;
import com.netPoint.applications.site.model.ProduitRevendeur;
import com.netPoint.applications.site.model.Profil;
import com.netPoint.applications.site.model.Revendeur;
import com.netPoint.applications.site.model.TypeCommande;
import com.netPoint.applications.site.model.User;
import com.netPoint.applications.site.service.notify.Notifier;
import com.netPoint.applications.site.util.Divers;
import com.netPoint.applications.site.util.Parameters;

@Service
public class ServiceCommande implements IServiceCommande{
	public static final Logger logger = LoggerFactory.getLogger(ServiceCommande.class);
	@Autowired
	private IDaoCommande daoCommande;
	@Autowired
	private InterfaceDao interfaceDao;
	public void setInterfaceDao(InterfaceDao interfaceDao) {
		this.interfaceDao = interfaceDao;
	}
	@Autowired
	protected Notifier notifier;
	/**
	 * @param notifier the notifier to set
	 */
	public void setNotifier(Notifier notifier) {
		this.notifier = notifier;
	}
	@Override
	public List<Commande> ListCommandeParMois(int pMois, int pAnnee,
			Revendeur pRevenduer, Produit pProduit) {
		
		return daoCommande.ListCommandeParMois(pMois, pAnnee, pRevenduer, pProduit);
	}
	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<ClientRevendeur> getListClient(Integer societe, Integer etat,
			String email, String siret, String rcs, Integer num_client,
			Integer id_revendeur, String num_client_revendeur,
			String customer_key, String nom, String prenom, Integer phone) {
		
		return daoCommande.getListClient(societe, etat, email, siret, rcs, num_client, id_revendeur, num_client_revendeur, customer_key, nom, prenom, phone);
	}
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Override
	public List<Commande> getListCommande(Integer num_commande,
			Integer num_commande_rev, String email,
			String societe, Integer num_client, Integer etat,
			Integer id_fournisseur, String dateInf, String dateSupp) {
		
		return daoCommande.getListCommande(num_commande, num_commande_rev, email, societe, num_client, etat, id_fournisseur, dateInf, dateSupp);
	}
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Override
	public List<Commande> getListClientEssai(Integer societe, String email,
			Integer num_client, String num_client_revendeur, String nomPrenom,
			Integer phone) {
		
		return daoCommande.getListClientEssai(societe, email, num_client, num_client_revendeur, nomPrenom, phone);
	}
	@SuppressWarnings("unused")
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Commande SaveOrUpdate(Commande pcommande) {
		try {
			//daoCommande.SaveOrUpdate(commande);
			 User currentUser = Parameters.getUserconnect();
			// Revendeur revendeur=daoCommande.SearchRevendeur(currentUser);
			 
					Commande commande = new Commande();
					commande = pcommande;			
						//Find client by mail
					// Client client=commande.getClient();
					 /*if(commande.getClient()!=null && commande.getClient().getUser()!=null){
						  client = findClientByMail(commande.getClient().getUser().getLogin());
					 }
					 else{*/
					Client client = findClientById(commande.getClient().getId());
					 //}
						
					Date date =new Date();
					commande.setCreeLe(date);
					commande.setDateCommande(date);
					commande.setCreePar(currentUser.getNom());
					//TODO calcul date fin de la commande mais pour cela mettre en place calcul prix
					DureeCommande dureeCommande= daoCommande.getDureeCommande(commande.getDureeCommande().getId());
					Date date_fin = addMonths(commande.getDateDebut(),dureeCommande.getDuree());
					commande.setDateFin(date_fin);
					
					//client=entityManager.merge(client);
					commande.setClient(client);
					//commande.getClient().getUser().setProfil(profil);
					
					EtatCommande etatCommande = daoCommande.getEtatCmdById(2);
					
					commande.setEtatCommande(etatCommande);
					//Save Commande
					try {
						ClientRevendeur clientRevendeur= findClientRev(client);
						ProduitRevendeur produitrevendeur= GetProduitRevendeur(commande.getProduit(), clientRevendeur.getRevendeur());
						//TODO Calcul prix
						commande = CalculPrix(produitrevendeur, commande);
						commande.setRevendeur(clientRevendeur.getRevendeur());
						commande.setNumerocommande(daoCommande.selectmaxnumber()+1);
						commande= daoCommande.SaveOrUpdate(commande);
						/*CommandeRevendeur commandeRevendeur = new CommandeRevendeur();
						commandeRevendeur.setCommande(commande);
						//Select max of number and set to num
						commandeRevendeur.setNumeroCommandeRev(daoCommande.selectmaxnumber(clientRevendeur.getRevendeur())+1);
						commandeRevendeur.setRevendeur(clientRevendeur.getRevendeur());
						commandeRevendeur=daoCommande.SaveOrUpdateCmdRev(commandeRevendeur);*/
						//TODO Set num client revendeur
						//int numclientrev= daoCommande.getNumClientRevendeur(clientRevendeur.getRevendeur());
						
						//clientRevendeur.setNumeroClientRevendeur(commande.get);
						daoCommande.SaveCientRev(clientRevendeur);
						 //Envoie des mails
						 for (MailConfig maildtl : clientRevendeur.getRevendeur().getMailhdr().getMaildtl()) 
						 {
							if(commande.getTypeCommande().getCode().equals("cmd")  && maildtl.getActive() && maildtl.getCodemail().equals("m0"))
							{
								notifier.gestionMailNotify(client.getUser().getLogin(), 
										currentUser.getLogin(), maildtl.getDescription(), maildtl.getLogo(), maildtl.getSubject());
								break;
							}
							else if(commande.getTypeCommande().getCode().equals("Essai") && maildtl.getActive() && maildtl.getCodemail().equals("m4") )
							{
								notifier.gestionMailNotify(client.getUser().getLogin(), 
										currentUser.getLogin(), maildtl.getDescription(), maildtl.getLogo(), maildtl.getSubject());
								break;
							}
						 }
						 notifier.notify(client.getUser());
					} catch (Exception e) {
						
						e.getMessage();
						 logger.error(e.getMessage());
					}
					return commande;
		
		} catch (Exception e) {
			e.getMessage();
			 logger.error(e.getMessage());
			return null;
		}
	}
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Override
	public ProduitRevendeur GetProduitRevendeur(Produit produit,
			Revendeur revendeur) {
		
		return daoCommande.GetProduitRevendeur(produit, revendeur);
	}
	public java.util.Date addMonths(java.util.Date aDate, int number){  
	    java.util.Calendar aCalendar = java.util.Calendar.getInstance();  
	    aCalendar.setTime(aDate);  
	    aCalendar.add(java.util.Calendar.MONTH, number);  
	    return aCalendar.getTime();  
	} 
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Override
	public Client findClientById(Integer id) {
		
		return daoCommande.findClientById(id);
	}
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Override
	public ClientRevendeur findClientRev(Client client) {
		return daoCommande.findClientRev(client);
	}
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Override
	public Client findClientByMail(String login) {
		
		return daoCommande.findClientByMail(login);
	}
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Override
	public List<DureeCommande> getListDureeCommande() {
		
		return daoCommande.getListDureeCommande();
	}
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Override
	public List<TypeCommande> getListTypeCmd() {
		return daoCommande.getListTypeCmd();
	}
	@Override
	public Commande getCommandeById(Integer id){
		return daoCommande.getCommandeById(id);
	}
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Override
	public List<Commande> getListCommandeExpire(int nbrejourexpiration,
			Revendeur revendeur) {
		return daoCommande.getListCommandeExpire(nbrejourexpiration, revendeur);
	}
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Client SaveClient(Client client, Revendeur revendeur)
	{
		try {
			 
			//save or update commande
			  	User user= client.getUser();
				user=Divers.generatePassword(user);
				user.setRevendeur(revendeur);
				Profil profil= daoCommande.getProfilById(5);
				user.setProfil(profil);
				user.setGroup(Parameters.group);
				Date _date = new Date();
				user.setDateCreation(_date);
				user.setCreePar(Parameters.userconnect.getNom()+" "+Parameters.userconnect.getPrenom());
				client.setUser(user);
				client.getSociete().setGroup(Parameters.group);
				client.getSociete().setType("client");
				client.setCustomerKey("clt"+Divers.generateAuthCode());
				client.setCodeClient("clt"+Divers.generateAuthCode());
				client =daoCommande.SaveClent(client);
				ClientRevendeur clientRevendeur = new ClientRevendeur();
				 clientRevendeur.setClient(client);
				 clientRevendeur.setRevendeur(revendeur);
				 ClientRevendeurId clientRevendeurId = new ClientRevendeurId();
				 clientRevendeurId.setIdClient(client.getId());
				 clientRevendeurId.setIdRevendeur(revendeur.getId());
				 
				 //get max number client revendeur in table
				 //String _derniernumclientrev= getEndNumber(revendeur);
				 clientRevendeur.setId(clientRevendeurId);
				 //clientRevendeur.setNumeroClientRevendeur(commande.getNumeroCommande());
				 daoCommande.SaveCientRev(clientRevendeur);
		} catch (Exception e) {
			e.getMessage();
			 logger.error(e.getMessage());
		}
		
		return client ;
	}
	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public Revendeur getRevendeurBy(String numeroRevendeur)
	{
		 Revendeur revendeur=null;
		 try {
			User currentUser = Parameters.getUserconnect();
			 revendeur=daoCommande.SearchRevendeur(currentUser);
			 if(revendeur==null){
				 //Get revendeur by code revendeur
				 revendeur=daoCommande.getRevendeurBy(numeroRevendeur);
			 }
		 }catch (Exception e) {
			logger.error(e.getMessage());
		}
		 return revendeur;
	}
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Client SavaOrUpdateClient(Client client)
	{
		User user= client.getUser();
		User _userOld = interfaceDao.findByIdUser(user.getId());
		 if(user.isChangepwd()){
	    	  user = Divers.generatePassword(user);
	      }
	      else{
	    	  user.setPwd(_userOld.getPwd());
	      } 
		return daoCommande.SaveClent(client);
	}
	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public CommandeRevendeur findCommandeRev(Commande commande) 
	{
		return daoCommande.findCommandeRev(commande);
	}
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public Prix addOrUpdatePrix(Prix prix) {
		
		return daoCommande.SaveOrUpdatePrix(prix);
	}
	@Override
	public Commande CalculPrix(ProduitRevendeur prodrev, Commande command)
	{
		
		float prix=0;
		float totalremise =0;
		float prixsansremise= prodrev.getProduit().getPrix()*command.getNombreposte();
		if(prodrev.getTauxRemise()!=null)
		{
			totalremise=(( prodrev.getProduit().getPrix() * prodrev.getTauxRemise())/100)*command.getNombreposte();
			prix=(prodrev.getProduit().getPrix() -totalremise) *command.getNombreposte(); 
			
		}
		else{
			prix = prodrev.getProduit().getPrix()*command.getNombreposte();
		}
		command.setPrixproduit(prix);
		command.setMontant(prix);
		command.setQuantite(command.getNombreposte());
		command.setTotalremise(totalremise);
		command.setPrixsansremise(prixsansremise);
		command.setTauxremise(prodrev.getTauxRemise());
		return command;
	}
	@Override
	public float CalculPrixSansRemise(Commande command)
	{
		float prix=0;
			prix = command.getProduit().getPrix()*command.getNombreposte();
		//command.setPrixproduit(prix);
		return prix;
	}
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Override
	public Produit findProductBy(Integer id) {
		
		return daoCommande.findProductBy(id);
	}
}
