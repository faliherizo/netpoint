package com.netPoint.applications.site.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.velocity.runtime.parser.node.GetExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.netPoint.applications.site.model.Client;
import com.netPoint.applications.site.model.ClientRevendeur;
import com.netPoint.applications.site.model.Commande;
import com.netPoint.applications.site.model.EtatCommande;
import com.netPoint.applications.site.model.EtatCompte;
import com.netPoint.applications.site.model.Fournisseur;
import com.netPoint.applications.site.model.Revendeur;
import com.netPoint.applications.site.model.SearchCriterieUser;
import com.netPoint.applications.site.model.Societe;
import com.netPoint.applications.site.model.TypeCommande;
import com.netPoint.applications.site.model.forms.RechercheClient;
import com.netPoint.applications.site.model.forms.RechercheClientEssaie;
import com.netPoint.applications.site.model.forms.RechercheCommande;
import com.netPoint.applications.site.service.IServiceCommande;
import com.netPoint.applications.site.service.IServiceUser;
import com.netPoint.applications.site.service.InterfaceService;
import com.netPoint.applications.site.util.Parameters;

@Controller
@RequestMapping(value = "/")
public class CommandeController{
    public static final Logger logger = LoggerFactory.getLogger(CommandeController.class);
    private InterfaceService interfaceService;
    private IServiceCommande serviceCommande;
    @Autowired
	private IServiceUser iServiceUser;
    @Autowired
	private IServiceCommande iServiceCommande;
   
    public void setiServiceCommande(IServiceCommande iServiceCommande) {
		this.iServiceCommande = iServiceCommande;
	}
	/**
	 * @param iServiceUser the iServiceUser to set
	 */
	public void setiServiceUser(IServiceUser iServiceUser) {
		this.iServiceUser = iServiceUser;
	}
    /**
	 * @param serviceCommande the serviceCommande to set
	 */
    @Autowired
	public void setServiceCommande(IServiceCommande serviceCommande) {
		this.serviceCommande = serviceCommande;
	}
    @ModelAttribute("societeList")
    public List<Societe> populateSocieteList() {
      return interfaceService.findAllSociete("client");
    }
	@RequestMapping(value = "/listClients",  method=RequestMethod.GET)
    public String listClient(@ModelAttribute("searchCriteria") RechercheClient searchCriteria, ModelMap model) {
        List<ClientRevendeur> clients = new ArrayList<ClientRevendeur>();
        clients= serviceCommande.getListClient(searchCriteria.getSociete(), searchCriteria.getEtat(), searchCriteria.getEmail(),
        		searchCriteria.getSiret(), searchCriteria.getRcs(), searchCriteria.getNum_client(),
        		searchCriteria.getId_revendeur(), searchCriteria.getNum_client_revendeur(), searchCriteria.getCustomer_key(),
        		searchCriteria.getNom(), searchCriteria.getPrenom(), searchCriteria.getPhone());
        model.addAttribute("clients", clients);
        Commande command= new Commande();
        model.addAttribute("get_duree_list",  iServiceCommande.getListDureeCommande());
        model.addAttribute("get_list_typecmd", interfaceService.getListTypeCommande());
        model.addAttribute("get_frns_list", iServiceUser.getListFournisseur());     
        model.addAttribute("command", command);
        model.addAttribute("listproduit", interfaceService.findAllProduit());
        return "commande/listClients";
    }
    
    @RequestMapping(value = "/listClientsEssai", method = RequestMethod.GET)
    public ModelAndView listClientEnEssai(@ModelAttribute("searchCriteria") RechercheClientEssaie searchCriteria, Model model) {
        List<Commande> clientEnEssaiList = new ArrayList<Commande>();
        clientEnEssaiList=serviceCommande.getListClientEssai(searchCriteria.getSociete(), searchCriteria.getEmail(), searchCriteria.getNum_client(), searchCriteria.getNum_client_revendeur(),
        		searchCriteria.getNom(), searchCriteria.getPhone());
        return new ModelAndView("commande/listClientsEssai", new ModelMap(clientEnEssaiList));
    }
    
    @RequestMapping(value = "/listCommandes",  method=RequestMethod.GET)
    public String listCommande(@ModelAttribute("searchCriteria") RechercheCommande searchCriteria, ModelMap model) {
        List<Commande> commandeList = new ArrayList<Commande>();
        commandeList = serviceCommande.getListCommande(searchCriteria.getNumeroCommande(),searchCriteria.getNum_commande_rev(), searchCriteria.getEmail()
        		,searchCriteria.getSociete(), searchCriteria.getNum_client(),searchCriteria.getEtat(), searchCriteria.getId_fournisseur()
        		, searchCriteria.getDateInf(), searchCriteria.getDateSupp());
        model.addAttribute("commandeList", commandeList);
        return "commande/listCommandes";
    }
    @ModelAttribute("get_list_typecmd")
    public List<TypeCommande> typeCmd() {
      return interfaceService.getListTypeCommande();
    }
    @ModelAttribute("get_frns_list")
	public List<Fournisseur> getFrns() {
		return iServiceUser.getListFournisseur();
	}
    
     /*  
    @RequestMapping(value = "/newClient", method = RequestMethod.GET)
    public ModelAndView newClient() {
        
        return new ModelAndView("commande/newClient");
    }
    */
    @Autowired
    public void setInterfaceService(InterfaceService interfaceService) {
        this.interfaceService = interfaceService;
    }
    @ModelAttribute("listRevendeur")
    public List<Revendeur> populateRevendeurList() {
      return interfaceService.findAllRevendeur();
    }
    
    @ModelAttribute("etatCommande")
    public List<EtatCommande> populateCmd() {
      return interfaceService.findAllEtatCommande();
    }
    @ModelAttribute("etatCompte")
    public List<EtatCompte> listEtatCmpt() {
      return interfaceService.findAllEtatCompte();
    }
    @ModelAttribute("access_admin")
	public boolean get_access_admin() {
		Boolean access= false;
	    if(Parameters.Role_User.equals("SUP") || Parameters.Role_User.equals("ADM") || Parameters.Role_User.equals("GRS") || Parameters.Role_User.equals("REV"))
	    	access=true;
	   return access;
	}
 @ModelAttribute("access_frns")
	public boolean get_access_frns() {
		Boolean access= false;
	    if( Parameters.Role_User.equals("GRS"))
	    	access=true;
	   return access;
	}
    
    
}
