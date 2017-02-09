package com.netPoint.applications.site.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.codec.Base64;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.netPoint.applications.site.model.Client;
import com.netPoint.applications.site.model.ClientRevendeur;
import com.netPoint.applications.site.model.DureeCommande;
import com.netPoint.applications.site.model.EtatCommande;
import com.netPoint.applications.site.model.Fournisseur;
import com.netPoint.applications.site.model.MailConfig;
import com.netPoint.applications.site.model.MailHdr;
import com.netPoint.applications.site.model.Produit;
import com.netPoint.applications.site.model.ProduitRevendeur;
import com.netPoint.applications.site.model.Revendeur;
import com.netPoint.applications.site.model.SearchCriterieUser;
import com.netPoint.applications.site.model.User;
import com.netPoint.applications.site.model.forms.NewPwd;
import com.netPoint.applications.site.service.IProductService;
import com.netPoint.applications.site.service.IServiceCommande;
import com.netPoint.applications.site.service.IServiceMailConfig;
import com.netPoint.applications.site.service.IServiceUser;
import com.netPoint.applications.site.service.InterfaceService;
import com.netPoint.applications.site.util.Divers;
import com.netPoint.applications.site.util.Parameters;

import dojox.cometd.Listener;

@Controller
@RequestMapping(value = "/")
public class AdministrationController extends SiteController {
    public static final Logger logger = LoggerFactory.getLogger(AdministrationController.class);
    @Autowired
    private IProductService iProductService;
    
	@Autowired
	private IServiceUser iServiceUser;
    @Autowired
	private IProductService productService;
    
    @Autowired
	private IServiceCommande iServiceCommande;
	/**
	 * @param iServiceCommande the iServiceCommande to set
	 */
	public void setiServiceCommande(IServiceCommande iServiceCommande) {
		this.iServiceCommande = iServiceCommande;
	}
	/**
	 * @param productService the productService to set
	 */
	public void setProductService(IProductService productService) {
		this.productService = productService;
	}
	/**
	 * @param iServiceUser the iServiceUser to set
	 */
	public void setiServiceUser(IServiceUser iServiceUser) {
		this.iServiceUser = iServiceUser;
	}
    /**
	 * @param iProductService the iProductService to set
	 */
	public void setiProductService(IProductService iProductService) {
		this.iProductService = iProductService;
	}
	
	
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		//Register the DateFormat Property Editor		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		CustomDateEditor editor = new CustomDateEditor(df, false);
		binder.registerCustomEditor(Date.class, editor);
		
	}
	
    @RequestMapping(value = "/accueil", method = RequestMethod.GET)
    public ModelAndView accueil() {
        List<EtatCommande> etatCommandeList = new ArrayList<EtatCommande>();
        etatCommandeList = interfaceService.findAllEtatCommande();
        return new ModelAndView("accueil", new ModelMap(etatCommandeList));
    }
    @RequestMapping(value = "/get_produit_list",  method = RequestMethod.GET)
	public @ResponseBody List<Produit> getProduitFrns(@RequestParam(value="fournisseur", required = true) Integer fournisseur) {
			List<Produit> produits = productService.getListProductByFrns(fournisseur) ;
			 
			return produits;
	}
    @RequestMapping(value = "/get_list_produit",  method = RequestMethod.GET)
   	public @ResponseBody List<Produit> getProduitFrns(@RequestParam(value="fournisseur", required = true) Integer fournisseur,@RequestParam(value="client", required = true)  Integer client) {
   			List<Produit> produits =null ;
   			//Revendeur revendeur = Parameters.revendeurUse;
   			Client clientmodif= iServiceCommande.findClientById(client);
   			ClientRevendeur clientRevendeur = iServiceCommande.findClientRev(clientmodif);
   			produits=productService.getListProductByFrns(fournisseur,clientRevendeur.getRevendeur()) ;
   			 
   			return produits;
   	}
    @RequestMapping(value = "/get_list_duree",  method = RequestMethod.GET)
   	public @ResponseBody List<DureeCommande> getListDuree(@RequestParam(value="typecommande", required = true) Integer typecommande) {
    	List<DureeCommande> durees= null; 
    	if(typecommande!=2)
    		durees= iServiceCommande.getListDureeCommande();
    	else
    		durees.add(iServiceCommande.getListDureeCommande().get(0));
    	return durees;
    }
    
    @RequestMapping(value = "/get_rev_list",  method = RequestMethod.GET)
   	public @ResponseBody List<Revendeur> getRevendeurList(@RequestParam(value="produit", required = true) Integer produit) {
    	List<Revendeur> revendeurs = new ArrayList<Revendeur>();
    	revendeurs = productService.getListRevByIdprod(produit) ;
   			 
   			return revendeurs;
   	}
    @RequestMapping(value = "/get_produit_list_rev",  method = RequestMethod.GET)
	public @ResponseBody List<Produit> getProduitRev(@RequestParam(value="revendeur", required = true) Integer revendeur) {
			List<Produit> produits = productService.getListProductByRev(revendeur) ;
			 
			return produits;
	}
   /* @RequestMapping(value = "/get_duree_list",  method = RequestMethod.GET)
   	public @ResponseBody List<Produit> getDureeProduitFrns(@RequestParam(value="product", required = true) Integer product) {
   			List<Produit> produits = productService.getListProductByFrns(fournisseur) ;
   			 
   			return produits;
   	}*/
    @RequestMapping(value = "/mailconfig", method = RequestMethod.GET)
   	public String mailconfig(@RequestParam String id,Model model){
    	
    	
		return "";
    	
    }
    
    
   
  /*  @RequestMapping(value = "/rapportBirt", method = RequestMethod.GET)
   	public String rapportBirt(@RequestParam(value="rapport", required = true) String rapport,@RequestParam(value="produit", required = true)  int produit,@RequestParam(value="revendeur", required = true) int revendeur, Model model) {		
   		String nomFichierBirt="WEB-INF/reports/";
   		if(rapport.equals("vente")) {
   			nomFichierBirt += "vente.rptdesign";
   		}
   		else if(rapport.equals("evolutionvente")) {
   			nomFichierBirt += "evolutionvente.rptdesign";
   		}
   		model.addAttribute("nomFichierBirt", nomFichierBirt);
   		model.addAttribute("renvendeur", revendeur);
   		model.addAttribute("produit", produit);
   		
   		return "vente/vente";
   	}
    */
    @RequestMapping(value = "/renouvellementDuMois", method = RequestMethod.GET)
    public ModelAndView renouvellementDuMois() {
        List<EtatCommande> etatCommandeList = new ArrayList<EtatCommande>();
        etatCommandeList = interfaceService.findAllEtatCommande();
        return new ModelAndView("accueil/renouvellementDuMois", new ModelMap(etatCommandeList));
    }
    
    @RequestMapping(value = "/renouvellementEchus", method = RequestMethod.GET)
    public ModelAndView renouvellementEchus() {
        List<EtatCommande> etatCommandeList = new ArrayList<EtatCommande>();
        etatCommandeList = interfaceService.findAllEtatCommande();
        return new ModelAndView("accueil/renouvellementEchus", new ModelMap(etatCommandeList));
    }
    
  
    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public String select(@ModelAttribute("idfrns") int idfrns, BindingResult result, SessionStatus status, ModelMap modelMap){
    	interfaceService.findAllProduit();
    	
    	//modelMap.addAttribute("listproduct", )
    	
    	return"/accueil/select";
    	
    }
   /* @RequestMapping(value = "/newRevendeur", method = RequestMethod.GET)
    public ModelAndView newRevendeur() {
        List<Revendeur> listes = new ArrayList<Revendeur>();
        listes = interfaceService.findAllRevendeur();
        return new ModelAndView("administration/revendeurs/newRevendeur", new ModelMap(listes));
    }
    */
    @RequestMapping(value = "/listRevendeurs", method = RequestMethod.GET)
    public ModelAndView listRevendeur() {
        List<Revendeur> listes = new ArrayList<Revendeur>();
        listes = interfaceService.findAllRevendeur();
        return new ModelAndView("administration/revendeurs/listRevendeurs", new ModelMap(listes));
    }
    
       
    @RequestMapping(value = "/listEmailAutomatique", method = RequestMethod.GET)
    public ModelAndView listEmailAutomatique() {
        return new ModelAndView("administration/revendeurs/listEmailAutomatique");
    }

   /* @RequestMapping(value = "/myProfil", method = RequestMethod.GET)
    public ModelAndView myProfil() {
    	User auth = (User) SecurityContextHolder.getContext().getAuthentication();
    	//User user = interfaceService.findUserByMail(auth.getLogin());
    	Map<String, Object> model = new HashMap<String, Object>();
    	model.put("user", auth);
    	//return new ModelAndView("myAccount", model);
        return new ModelAndView("myProfil" , model);
    }
    */
    @RequestMapping(value = "/aide", method = RequestMethod.GET)
    public ModelAndView aide() {
        return new ModelAndView("aide");
    } 
    
    @RequestMapping(value = "/newslettre", method = RequestMethod.GET)
    public ModelAndView newslettre() {
        return new ModelAndView("newslettre");
    } 

    @RequestMapping(value = "/newslettre", method = RequestMethod.POST)
    public ModelAndView modifnewslettre() {
    	
        return new ModelAndView("newslettre");
    } 
 /*   @ModelAttribute("get_frns_list")
	 public List<Fournisseur> get_List_Frns() {
			return iServiceUser.getListFournisseur();
	 }*/
	@RequestMapping(value="/ajaxlistuser", method=RequestMethod.POST)
	public String detailrevendeur(@ModelAttribute("searchCriteria") SearchCriterieUser searchCriteria, Model model)
	{   List<User> _listUser= new ArrayList<User>();
   		if (searchCriteria.getMail()!=null && !searchCriteria.getMail().trim().isEmpty()) {
   			if(searchCriteria.getIdetat()!=null)
   				if (searchCriteria.getIdprofil()!=null) {
   					_listUser= interfaceService.findAllUserByProfilMailEtat(searchCriteria.getIdprofil(),
   							searchCriteria.getMail(), searchCriteria.getIdetat());
   				}
   				else
   				_listUser= interfaceService.findAllUserByMailEtat(searchCriteria.getMail(), searchCriteria.getIdetat());
   			else
   				_listUser= interfaceService.findAllUserByMail(searchCriteria.getMail());
   			
   		}
   		else{ 
   			if(searchCriteria.getIdetat()!=null)
   				if(searchCriteria.getIdprofil()!=null)
   					_listUser= interfaceService.findAllUserByProfilEtat(searchCriteria.getIdprofil(), 
   						searchCriteria.getIdetat());
   				else
   					_listUser= interfaceService.findAllUserByEtat(searchCriteria.getIdetat());
   			else
   				if(searchCriteria.getIdprofil()!=null)
   					_listUser =interfaceService.findAllUserByProfil(searchCriteria.getIdprofil());
   				else
   				_listUser= interfaceService.findAllUser();
   		}
   		model.addAttribute("resultsearchtUser", _listUser);		
	   		return "administration/utilisateurs/ajaxlistuser";
	}
   
    @RequestMapping
    public String IsAuthentifie(){
    	Authentication auth =SecurityContextHolder.getContext().getAuthentication();
    	if(auth!=null)
    		return "redirect:/commandesMoisEnCours";
    		else
    		return "redirect:/login";	
    }
    
    @RequestMapping(value = "/listProduct", method = RequestMethod.GET)
	public String listProduct(ModelMap model) {
	     List<Produit> _listProduit= new ArrayList<Produit>();
	     _listProduit= productService.getListProduct();
	       	model.addAttribute("listProduct", _listProduit);
	     return "produit/listProduit";
	 }
    @Autowired
    private IServiceMailConfig iservicemail;
	public void setIservicemail(IServiceMailConfig iservicemail) {
		this.iservicemail = iservicemail;
	}
    @RequestMapping(value = "/personnalisation-mail/{id}", method = RequestMethod.GET)
	public String listmail(@PathVariable("id") int id,ModelMap model) {
	     List<MailConfig> _listmail= new ArrayList<MailConfig>();
	    // _listmail= iservicemail.ListMailPersonnalisable(int id);
	    MailHdr _mail= iservicemail.getMailHdrById(id);
	    _listmail = _mail.getMaildtl();
	       	model.addAttribute("listTypemail", _listmail);
	     return "emailing/gestion-emailing";
	 }
    @RequestMapping(value = "/cron-mail/{id}", method = RequestMethod.GET)
   	public String listcronmail(@PathVariable("id") int id,ModelMap model) {
    	List<MailConfig> _listmail= new ArrayList<MailConfig>();
 	    // _listmail= iservicemail.ListMailPersonnalisable(int id);
 	    MailHdr _mail= iservicemail.getMailHdrById(id);
 	    _listmail = _mail.getMaildtl();
 	       	model.addAttribute("listTypemail", _listmail);
   	     return "emailing/cron-mail";
   	 }
     @RequestMapping(value="/deleteUser/{id}", method=RequestMethod.GET)
	 public String deleteUser(@PathVariable("id")Integer id, Model model)
	 {
    	 try {
    		 List<User> _listUser= new ArrayList<User>();
     		 interfaceService.RemoveUser(id);
 	   		return "redirect:/listUser";
		} catch (Exception e) {
			logger.error("error pendant la suppression de l'utilisateur", e.getMessage());
			return "administration/utilisateurs/listUser";
		}
    	
	}
   /* @RequestMapping(value = "/tester-mail/{id}", method = RequestMethod.GET)
   	public String testcronmail(@PathVariable("id") int id,ModelMap model) {
    	 Set<MailConfig> _listmail= new HashSet<MailConfig>();
 	    // _listmail= iservicemail.ListMailPersonnalisable(int id);
 	    MailHdr mailHdr= iservicemail.getMailHdrById(id);
 	    //_listmail = _mail.getMaildtl();
 	       	model.addAttribute("mailhdr", mailHdr);
 	     return "emailing/test-mail";
   	 }*/
}
