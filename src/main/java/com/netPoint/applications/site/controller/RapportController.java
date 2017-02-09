package com.netPoint.applications.site.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.netPoint.applications.site.model.EtatCommande;
import com.netPoint.applications.site.model.Fournisseur;
import com.netPoint.applications.site.model.Produit;
import com.netPoint.applications.site.model.Revendeur;
import com.netPoint.applications.site.service.IProductService;
import com.netPoint.applications.site.service.IServiceUser;
import com.netPoint.applications.site.service.InterfaceService;
import com.netPoint.applications.site.util.Parameters;

@Controller
@RequestMapping(value = "/")
public class RapportController{
	
    public static final Logger logger = LoggerFactory.getLogger(RapportController.class);
    @Autowired
    protected InterfaceService interfaceService;
	@Autowired
	private IServiceUser iServiceUser;
    /**
	 * @param iServiceUser the iServiceUser to set
	 */
	public void setiServiceUser(IServiceUser iServiceUser) {
		this.iServiceUser = iServiceUser;
	}
	/**
	 * @param interfaceService the interfaceService to set
	 */
	public void setInterfaceService(InterfaceService interfaceService) {
		this.interfaceService = interfaceService;
	}

	@Autowired
   	private IProductService productService;
   	/**
   	 * @param productService the productService to set
   	 */
   	public void setProductService(IProductService productService) {
   		this.productService = productService;
   	}
   	@ModelAttribute("revendeurList")
    public List<Revendeur> populateRevendeurList() {
      return interfaceService.findAllRevendeur();
    }
   	@ModelAttribute("listproduit")
	public List<Produit> getListProduit() {
	   List<Produit> listproduit = interfaceService.findAllProduit();
	   return listproduit;
	}
   	@ModelAttribute("get_frns_list")
	public List<Fournisseur> getFrns() {
		return iServiceUser.getListFournisseur();
	}
    @RequestMapping(value = "/nombreEssais", method = RequestMethod.GET)
    public ModelAndView listEssai(ModelMap model) {
    	List<Produit> produits= productService.getListProductByFrns();
    	model.addAttribute("produits", produits);
    	
        List<EtatCommande> etatCommandeList = new ArrayList<EtatCommande>();
        etatCommandeList = interfaceService.findAllEtatCommande();
        
        return new ModelAndView("rapport/nombreEssais", new ModelMap(etatCommandeList));
        //return new ModelAndView("rapport/nombreEssais");
    }
    
    @RequestMapping(value = "/triVentes", method = RequestMethod.GET)
    public ModelAndView listTriVente(ModelMap model) {
    	List<Produit> produits= productService.getListProductByFrns();
    	model.addAttribute("produits", produits);
    	
        List<EtatCommande> etatCommandeList = new ArrayList<EtatCommande>();
        etatCommandeList = interfaceService.findAllEtatCommande();
        
        return new ModelAndView("rapport/triVentes", new ModelMap(etatCommandeList));
        //return new ModelAndView("rapport/triVentes");
    }
    
      
    @RequestMapping(value = "/evolutionsVentes", method = RequestMethod.GET)
    public ModelAndView evolutionVentes(ModelMap model) {
    	List<Produit> produits= productService.getListProductByFrns();
    	model.addAttribute("produits", produits);
    	
        List<EtatCommande> etatCommandeList = new ArrayList<EtatCommande>();
        etatCommandeList = interfaceService.findAllEtatCommande();
        
        return new ModelAndView("rapport/evolutionsVentes", new ModelMap(etatCommandeList));
        //return new ModelAndView("rapport/evolutionsVentes");
    }
    
    @RequestMapping(value = "/ajax/rapport_evolution_vente", method = RequestMethod.GET)
   	public String rapportBirt(@RequestParam String rapport,@RequestParam Integer produit,@RequestParam Integer revendeur,@RequestParam String month, @RequestParam Integer year, Model model) {		
   		String nomFichierBirt="WEB-INF/birt/";

   			nomFichierBirt += "evolutionvente.rptdesign";
   		
   		model.addAttribute("nomFichierBirt", nomFichierBirt);
   		if(revendeur!=null)
   		model.addAttribute("revendeur", revendeur);
   		if(produit!=null)
   		model.addAttribute("produit", produit);
   		if(Parameters.group!=null)
   	   		model.addAttribute("groupe", Parameters.group);
   		model.addAttribute("month", month);
   		model.addAttribute("year", year);
   		return "vente/evolutionvente";
   	}
    @RequestMapping(value = "/ajax/rapport_birt", method = RequestMethod.GET)
   	public String rapportBirtVente(@RequestParam String rapport,@RequestParam Integer produit,@RequestParam Integer revendeur,@RequestParam Integer year, @RequestParam Integer month, Model model) {		
   		String nomFichierBirt="WEB-INF/birt/";
   		if(rapport.equals("vente")) {
   			nomFichierBirt += "vente.rptdesign";
   		}
   		model.addAttribute("nomFichierBirt", nomFichierBirt);
   		if(revendeur!=null)
   			model.addAttribute("revendeur", revendeur);
   			if(produit!=null)
   			model.addAttribute("produit", produit);
   			Integer groupe=Parameters.group; 
   			if(Parameters.group!=null)
   	   	   		model.addAttribute("groupe", groupe);
   		model.addAttribute("month", month);
   		model.addAttribute("year", year);
   		return "vente/vente";
   	}
    @RequestMapping(value = "/ajax/rapport_birt_essaie", method = RequestMethod.GET)
   	public String rapportBirtEssaie(@RequestParam String rapport,@RequestParam Integer produit,@RequestParam String date_debut,@RequestParam String date_fin,Model model) {		
   		String nomFichierBirt="WEB-INF/birt/nombreEssaie.rptdesign";
   		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
   		Date dateDebut=null;
   		Date dateFin=null;
   	    try {
			 dateDebut = dateFormat.parse(date_debut);
			 dateFin =  dateFormat.parse(date_fin);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
   		
   			//nomFichierBirt += "nombreEssaie.rptdesign";
   		model.addAttribute("nomFichierBirt", nomFichierBirt);
   		model.addAttribute("date_debut", dateDebut);
   		model.addAttribute("date_fin", dateFin);
   		if(produit!=null)
   		model.addAttribute("produit", produit);
   		if(Parameters.group!=null)
   	   		model.addAttribute("groupe", Parameters.group);
   		return "vente/nbreessaie";
   	}
    @RequestMapping(value = "/ajax/rapport_tri_vente", method = RequestMethod.GET)
   	public String rapportBirtTriVente(@RequestParam String rapport,@RequestParam Integer produit,@RequestParam Integer revendeur,@RequestParam String dateDebut, @RequestParam String dateFin, Model model) {		
   		String nomFichierBirt="WEB-INF/birt/";

   			nomFichierBirt += "trivente.rptdesign";
   		
   		model.addAttribute("nomFichierBirt", nomFichierBirt);
   		if(revendeur!=null)
   		model.addAttribute("revendeur", revendeur);
   		if(produit!=null)
   		model.addAttribute("produit", produit);
   		model.addAttribute("dateDebut", dateDebut);
   		model.addAttribute("dateFin", dateFin);
   		if(Parameters.group!=null)
   	   		model.addAttribute("groupe", Parameters.group);
   		return "vente/triventemois";
   	}
    @ModelAttribute("get_frns_list")
	public List<Fournisseur> get_frns_list(){
		List<Fournisseur> fournisseurs = interfaceService.getListFrnst(Parameters.group);
		
		return fournisseurs;
		
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
