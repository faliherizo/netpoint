/**
 * 
 */
package com.netPoint.applications.site.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.netPoint.applications.site.model.Client;
import com.netPoint.applications.site.model.ClientRevendeur;
import com.netPoint.applications.site.model.ClientRevendeurId;
import com.netPoint.applications.site.model.Commande;
import com.netPoint.applications.site.model.CommandeRevendeur;
import com.netPoint.applications.site.model.CompteBancaire;
import com.netPoint.applications.site.model.DureeCommande;
import com.netPoint.applications.site.model.EtatCommande;
import com.netPoint.applications.site.model.Langue;
import com.netPoint.applications.site.model.Prix;
import com.netPoint.applications.site.model.Produit;
import com.netPoint.applications.site.model.ProduitRevendeur;
import com.netPoint.applications.site.model.ProduitRevendeurId;
import com.netPoint.applications.site.model.Profil;
import com.netPoint.applications.site.model.Revendeur;
import com.netPoint.applications.site.model.TypeCommande;
import com.netPoint.applications.site.model.User;
import com.netPoint.applications.site.service.notify.Notifier;
import com.netPoint.applications.site.util.Divers;

/**
 * @author Faliherizo
 *
 */
@Repository
public class DaoCommande implements IDaoCommande {
	 public static final Logger logger = LoggerFactory.getLogger(DaoCommande.class);
	    private EntityManager entityManager;
	    @Autowired
	    private InterfaceDao interfaceDao;
	/**
		 * @param interfaceDao the interfaceDao to set
		 */
		public void setInterfaceDao(InterfaceDao interfaceDao) {
			this.interfaceDao = interfaceDao;
		}
		@PersistenceContext
		public void setEntityManager(EntityManager entityManager) {
			this.entityManager = entityManager;
		}
	@Override
	public List<Commande> ListCommandeParMois(int pMois, int pAnnee, Revendeur pRevenduer,
			Produit pProduit) {
		Date pdateDebut= getMinDateOfMonth(pAnnee, pMois);
		Date pdatefinmois= getLastDateOfMonth(pAnnee, pMois);
		List<Commande> commandes = new ArrayList<Commande>();
		logger.debug("Search Commandes, ventes du mois:");
		try {
			 String s = "select b from Commande b where b.revendeur =:pRevenduer and b.produit=:pProduit";
		if(pMois!=0 && pAnnee!= 0){
			 s +=" and b.dateDebut <=:pdatefinmois and b.dateDebut >=:pdateDebut";
		}
			 	Query query = entityManager.createQuery(s);
	            query.setParameter("pRevenduer", pRevenduer);
	            if(pMois!=0 && pAnnee!= 0){
		            query.setParameter("pdateDebut", pdateDebut);
		            query.setParameter("pdatefinmois", pdatefinmois);
	            }
	            query.setHint("org.hibernate.cacheable", true);
	            commandes = (List<Commande>) query.getResultList();
	            return commandes;
		} catch (RuntimeException re) {
			logger.error("find Commandes failed", re);
			return new ArrayList<Commande>();
		}
		
	}
	private Date getLastDateOfMonth(int year, int month) {
	    Calendar calendar = new GregorianCalendar(year, month, Calendar.DAY_OF_MONTH);
	    calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
	    return calendar.getTime();
	}
	private Date getMinDateOfMonth(int year, int month) {
	    Calendar calendar = new GregorianCalendar(year, month, Calendar.DAY_OF_MONTH);
	    calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
	    return calendar.getTime();
	}
	@Override
	public List<TypeCommande> getListTypeCmd() {
		List<TypeCommande> typecmds = new ArrayList<TypeCommande>();
        try {
            String s = "select b from TypeCommande b";
            Query query = entityManager.createQuery(s);
            query.setHint("org.hibernate.cacheable", true);
            typecmds = query.getResultList();
            
        } catch (Exception e) {
            logger.error("Error while loading typecmds", e);
        }
        return typecmds;
	}
	@Override
	public List<ClientRevendeur> getListClient(Integer societe, Integer etat,
			String email, String siret, String rcs, Integer num_client,
			Integer id_revendeur, String num_client_revendeur, String customer_key,
			String nom, String prenom, Integer phone) {
		List<ClientRevendeur> cmds = new ArrayList<ClientRevendeur>();
        try {
            String s = "select b from ClientRevendeur b where b.client.id > 0";
            //if(societe!=null || etat || email!=null ||  siret!=null || )
           // s+="";
            if(societe!=null)
            	s+=" AND b.client.societe.id =:societe";
            if(etat!=null)
            	s+=" AND b.client.user.etatCompte.id =:etat";
            if(!email.isEmpty())
            	s+=" AND b.client.user.login LIKE '%"+email+"%' ";
            if(!nom.isEmpty())
            	s+=" AND b.client.user.nom LIKE '%"+nom+"%' ";
            if(!prenom.isEmpty())
            	s+=" AND b.client.user.prenom LIKE '%"+prenom+"%' ";
            if(phone!=null)
            	s+=" AND b.client.user.telephone LIKE '%"+phone+"%' ";
            if(!siret.isEmpty())
            	s+=" AND b.client.siret =:siret ";
            if(!rcs.isEmpty())
            	s+=" AND b.client.rcs =:rcs ";
            if(num_client!=null)
            	s+=" AND b.client.id =:num_client ";
            if(id_revendeur!=null)
            	s+=" AND b.revendeur.id =:id_revendeur ";
            if(!num_client_revendeur.isEmpty())
            	s+=" AND b.numeroClientRevendeur =:num_client_revendeur ";
            if(!customer_key.isEmpty())
            	s+="";
            Query query = entityManager.createQuery(s);
            if(societe!=null)
            query.setParameter("societe", societe);
            if(id_revendeur!=null)
            query.setParameter("id_revendeur", id_revendeur);
            if(etat!=null)
            query.setParameter("etat", etat);
            /*if(!email.isEmpty())
            query.setParameter("email", email);*/
            if(!siret.isEmpty())
            query.setParameter("siret", siret);
            if(!rcs.isEmpty())
            query.setParameter("rcs", rcs);
            if(num_client!=null)
            query.setParameter("num_client", num_client);
            if(!num_client_revendeur.isEmpty())
            query.setParameter("num_client_revendeur", num_client_revendeur);
            query.setHint("org.hibernate.cacheable", true);
            cmds = query.getResultList();
        } catch (Exception e) {
            logger.error("Error while loading typecmds", e);
        }
        return cmds;
	}
	@Override
	public List<Commande> getListCommande(Integer numeroCommande,
			Integer num_commande_rev,String email,
			String societe, Integer num_client, Integer etat,
			Integer id_fournisseur, String dateInf, String dateSupp) {

		 Date dateinf = null;
		 Date datesup =null;
		 if(dateInf!=null && dateSupp!=null && !dateInf.isEmpty() && !dateSupp.isEmpty()){
			 DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		  try {
               dateinf = df.parse(dateInf);
               datesup = df.parse(dateSupp);
		  } catch (ParseException e) {
              e.printStackTrace();
          }
		 }
		List<Commande> cmds = new ArrayList<Commande>();
        try {
            String s = "select b from Commande b where b.id > 0";
            if(numeroCommande!=null)
            	s +=" AND b.id =:numerocmd";   
            if(num_commande_rev!=null)
            	s+=" AND b.numeroCommande =:num_commande_rev";
           if(!societe.isEmpty())
        	   s+=" AND b.client.societe.nom LIKE '%"+societe+"%'";
           if(num_client!=null)
        	   s+=" AND b.client.id =:num_client";
           if(etat!=null)
        	   s+=" AND b.etatCommande.id =:etat";
           if(id_fournisseur!=null)
        	   s+=" AND b.produit.fournisseur.id =:id_fournisseur";
           if(dateInf!=null && dateSupp!=null && !dateInf.isEmpty() && !dateSupp.isEmpty())
        	   s+=" AND b.dateDebut >=:dateInf AND b.dateDebut <=:dateSupp ";
           Query query = entityManager.createQuery(s);
          /* if(!societe.isEmpty())
        	   query.setParameter("societe", societe);*/
           if(numeroCommande!=null)
        	   query.setParameter("numerocmd", numeroCommande);
           if(num_commande_rev!=null)
        	   query.setParameter("num_commande_rev", num_commande_rev);
           if(num_client!=null)
        	   query.setParameter("num_client", num_client);
           if(etat!=null)
        	   query.setParameter("etat", etat);
           if(id_fournisseur!=null)
        	   query.setParameter("id_fournisseur", id_fournisseur);
           if(dateInf!=null && dateSupp!=null && !dateInf.isEmpty() && !dateSupp.isEmpty()){
                   query.setParameter("dateInf", dateinf);
            	   query.setParameter("dateSupp", datesup);      
           }
           query.setHint("org.hibernate.cacheable", true);
           cmds = query.getResultList();
           
        }catch (Exception e) {
			e.getMessage();
			
		}
		return cmds;
	}
	@Override
	public List<Commande> getListClientEssai(Integer societe, String email,
			Integer num_client, String num_client_revendeur, String nomPrenom,
			Integer phone) {
		
		List<Commande> cmds = new ArrayList<Commande>();
        try {
            String s = "select b from Commande b where b.typeCommande.id=2";
            //if(societe!=null || etat || email!=null ||  siret!=null || )
           // s+="";
            if(societe!=null)
            	s+="AND b.client.societe.id =:societe ";
        
            if(!email.isEmpty())
            	s+="AND b.client.user.login LIKE '%"+email+"%' ";
            if(!nomPrenom.isEmpty())
            	s+=" AND b.client.user.nom LIKE '%"+nomPrenom+"%' OR  b.client.user.prenom LIKE '%"+nomPrenom+"%'";
            
            if(!phone.toString().isEmpty())
            	s+=" AND b.client.user.telephone LIKE '%"+phone+"%' ";
            if(num_client!=null)
            	s+="AND b.client.id =:num_client ";
          
            if(num_client_revendeur!=null)
            	s+="AND b.numeroCommande =:num_client_revendeur ";
           
            Query query = entityManager.createQuery(s);
            if(societe!=null)
            query.setParameter("societe", societe);
            
            /*if(email!=null)
            query.setParameter("email", email);
         */
            if(num_client!=null)
            query.setParameter("num_client", num_client);
            if(num_client_revendeur!=null)
            query.setParameter("num_client_revendeur", num_client_revendeur);
            
            query.setHint("org.hibernate.cacheable", true);
            
            cmds = query.getResultList();
        } catch (Exception e) {
            logger.error("Error while loading typecmds", e);
        }
        return cmds;
	}
	
	@Override
	public Commande SaveOrUpdate(Commande pcommande) {
		return entityManager.merge(pcommande);
	}
	@Override
	public CommandeRevendeur SaveOrUpdateCmdRev(CommandeRevendeur commandeRevendeur) {
		return entityManager.merge(commandeRevendeur);
	}
	@Override
	public Profil getProfilById(Integer idprofil){
		
		Profil profil = null;
		try {
            String s = "select b from Profil b where b.id =:idprofil";
            Query query = entityManager.createQuery(s);
            query.setParameter("idprofil", idprofil);
            profil= (Profil) query.getSingleResult();
            
		}
        catch (Exception e) {
			e.getMessage();
		}
		return profil;
	}
	@Override
	public int selectmaxnumber(){
		int max=1;
		try {
            String s = "select max(b.numerocommande) from Commande b";
            Query query = entityManager.createQuery(s);
            max= (Integer) query.getSingleResult();
		}
        catch (Exception e) {
			e.getMessage();
			
		}
		return max;
	}
	@Override
	public EtatCommande getEtatCmdById(Integer idetatcmd){
		
		EtatCommande etatcmd = new EtatCommande();
		try {
            String s = "select b from EtatCommande b where b.id =:idetatcmd";
            Query query = entityManager.createQuery(s);
            query.setParameter("idetatcmd", idetatcmd);
            etatcmd= (EtatCommande) query.getSingleResult();
            return etatcmd;
		}
        catch (Exception e) {
			e.getMessage();
			return null;
		}
	}
	
	//recherche de revendeur by user
	@Override
	public Revendeur SearchRevendeur(User user){
		Revendeur revendeur= new Revendeur();
        try {
            String s = "select b from Revendeur b where b.user =:user";
            Query query = entityManager.createQuery(s);
            query.setParameter("user", user);
            revendeur= (Revendeur) query.getSingleResult();
            return revendeur;
		}
        catch (Exception e) {
			e.getMessage();
			return null;
		}
	}
	@Override
	public List<DureeCommande> getListDureeCommande() {
		List<DureeCommande> commandes = new ArrayList<DureeCommande>();
		try {
            String s = "select b from DureeCommande b order by b.id";
            Query query = entityManager.createQuery(s);
           
            commandes= query.getResultList();
            return commandes;
		}
        catch (Exception e) {
			e.getMessage();
			return null;
		}
	}
	@Override
	public List<TypeCommande> getListTypeCommande() {
		List<TypeCommande> commandes = new ArrayList<TypeCommande>();
		try {
            String s = "select b from TypeCommande b";
            Query query = entityManager.createQuery(s);
           
            commandes= query.getResultList();
            return commandes;
		}
        catch (Exception e) {
			e.getMessage();
			return null;
		}
	}
	@Override
	public String getEndNumber(Revendeur revendeur){
		ClientRevendeur clientRevendeur= new ClientRevendeur();
		try {
            String s = "select b from ClientRevendeur b where b.revendeur =:revendeur";
            Query query = entityManager.createQuery(s);
           query.setParameter("revendeur", revendeur);
           clientRevendeur= (ClientRevendeur) query.getSingleResult();
            return clientRevendeur.getNumeroClientRevendeur();
		}
        catch (Exception e) {
			e.getMessage();
			return "0";
		}
	}
	@Override
	public Commande getCommandeById(Integer id){
		
		Commande commande = new Commande();
		try {
            String s = "select b from Commande b where b.id =:id";
            Query query = entityManager.createQuery(s);
            query.setParameter("id", id);
            commande= (Commande) query.getSingleResult();
            return commande;
		}
        catch (Exception e) {
			e.getMessage();
			return null;
		}
	}
	@Override
	public List<Commande> getListCommandeExpire(int nbrejourexpiration, Revendeur revendeur) {
		DateTime date_expire= new DateTime();
		date_expire.plusDays(nbrejourexpiration);
		try {
            String s = "select b from Commande b where b.revendeur =:revendeur and b.dateFin <= :date_expire";
            Query query = entityManager.createQuery(s);
            query.setParameter("revendeur", revendeur);
            query.setParameter("date_expire", date_expire);
            List<Commande> commandes= query.getResultList();
            return commandes;
		}
        catch (Exception e) {
			e.getMessage();
			return null;
		}
	}
	@Override
	public ClientRevendeur SaveCientRev(ClientRevendeur clientRevendeur) {
		return entityManager.merge(clientRevendeur);
	}
	@Override
	public Client findClientByMail(String mail) {
		Client client=null;
		try {
            String s ="select b from Client b inner join b.user a where a.login= :mail";
            Query query = entityManager.createQuery(s);
            query.setParameter("mail", mail);
            client= (Client) query.getSingleResult();
            return client;
		}
        catch (Exception e) {
			e.getMessage();
			return null;
		}
	}
	@Override
	public Client SaveClent(Client client)
	{
		try {
			return entityManager.merge(client);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}
	@Override
	public Revendeur getRevendeurBy(String coderev) {
		Revendeur  revendeur = null;
		try {
            String s ="select b from Revendeur b where b.codeRevendeur= :coderev";
            Query query = entityManager.createQuery(s);
            query.setParameter("coderev", coderev);
            revendeur= (Revendeur) query.getSingleResult();
		}
        catch (Exception e) {
			e.getMessage();
		}
		return revendeur;
	}
	@Override
	public ClientRevendeur findClientRev(Client client) {
		ClientRevendeur  clientRevendeur = null;
		try {
            String s ="select b from ClientRevendeur b where b.client= :client";
            Query query = entityManager.createQuery(s);
            query.setParameter("client", client);
            clientRevendeur= (ClientRevendeur) query.getSingleResult();
		}
        catch (Exception e) {
			e.getMessage();
		}
		return clientRevendeur;
	}
	@Override
	public Client findClientById(Integer id) {
		
		Client client=null;
		try {
            String s ="select b from Client b where b.id= :id";
            Query query = entityManager.createQuery(s);
            query.setParameter("id", id);
            client= (Client) query.getSingleResult(); 
		}
        catch (Exception e) {
			e.getMessage();
		}
		return client;
	}
	@Override
	public CommandeRevendeur findCommandeRev(Commande commande) 
	{
		CommandeRevendeur commandeRevendeur=null;
		try {
            String s ="select b from CommandeRevendeur b where b.commande= :commande";
            Query query = entityManager.createQuery(s);
            query.setParameter("commande", commande);
            commandeRevendeur= (CommandeRevendeur) query.getSingleResult(); 
		}
        catch (Exception e) {
			e.getMessage();
		}
		return commandeRevendeur;
	}
	@Override
	public Prix SaveOrUpdatePrix(Prix prix) {
		return entityManager.merge(prix);
	}
	@Override
	public Produit findProductBy(Integer id) {
		Produit produit=null;
		try {
            String s ="select b from Produit b where b.id= :id";
            Query query = entityManager.createQuery(s);
            query.setParameter("id", id);
            produit= (Produit) query.getSingleResult(); 
		}
        catch (Exception e) {
			e.getMessage();
		}
		return produit;
	}
	@Override
	public ProduitRevendeur GetProduitRevendeur(Produit produit,
			Revendeur revendeur) {
		
		ProduitRevendeur produitRevendeur=null;
		try {
            String s ="select b from ProduitRevendeur b where b.produit= :produit and b.revendeur= :revendeur";
            Query query = entityManager.createQuery(s);
            query.setParameter("revendeur", revendeur);
            query.setParameter("produit", produit);
            produitRevendeur= (ProduitRevendeur) query.getSingleResult(); 
		}
        catch (Exception e) {
			e.getMessage();
		}
		return produitRevendeur;
	}
	@Override
	public DureeCommande getDureeCommande(Integer id) {
		
		DureeCommande dureeCommande=null;
		try {
            String s ="select b from DureeCommande b where b.id= :id";
            Query query = entityManager.createQuery(s);
            query.setParameter("id", id);
            dureeCommande= (DureeCommande) query.getSingleResult(); 
		}
        catch (Exception e) {
			e.getMessage();
		}
		return dureeCommande;
	}
	@Override
	public int getNumClientRevendeur(Revendeur revendeur) {
		
		int max=1;
		try {
            String s = "select max(b.numeroClientRevendeur) from ClientRevendeur b where b.revendeur=:revendeur";
            Query query = entityManager.createQuery(s);
            query.setParameter("revendeur", revendeur);
            max= (Integer) query.getSingleResult();
		}
        catch (Exception e) {
			e.getMessage();
			
		}
		return max;
	}
}
