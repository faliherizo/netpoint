package com.netPoint.applications.site.controller;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.ClassUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import com.netPoint.applications.site.model.Civilite;
import com.netPoint.applications.site.model.Client;
import com.netPoint.applications.site.model.Devise;
import com.netPoint.applications.site.model.EtatCompte;
import com.netPoint.applications.site.model.Fournisseur;
import com.netPoint.applications.site.model.Langue;
import com.netPoint.applications.site.model.ModeReglement;
import com.netPoint.applications.site.model.Pays;
import com.netPoint.applications.site.model.Produit;
import com.netPoint.applications.site.model.ProduitRevendeur;
import com.netPoint.applications.site.model.Profil;
import com.netPoint.applications.site.model.Revendeur;
import com.netPoint.applications.site.model.SearchCriteriaRevendeur;
import com.netPoint.applications.site.model.SearchCriterieUser;
import com.netPoint.applications.site.model.SearchRevByMail;
import com.netPoint.applications.site.model.Societe;
import com.netPoint.applications.site.model.User;
import com.netPoint.applications.site.service.IProductService;
import com.netPoint.applications.site.service.InterfaceService;
import com.netPoint.applications.site.service.notify.Notifier;
import com.netPoint.applications.site.util.Divers;
import com.netPoint.applications.site.util.Parameters;
import com.netPoint.applications.site.validator.UserValidator;

@Controller
public class SiteController{

    protected static final Logger logger = LoggerFactory.getLogger(SiteController.class);
    @Autowired
    protected InterfaceService interfaceService;
    @Autowired
    protected IProductService iProductService;
    /**
	 * @param iProductService the iProductService to set
	 */
	public void setiProductService(IProductService iProductService) {
		this.iProductService = iProductService;
	}
	//UserValidator userValidator;
	@Autowired
	protected Notifier notifier;
	   /**
		 * @param notifier set notification of email
		 */
	public void setNotifier(Notifier notifier) {
			this.notifier = notifier;
	}
	/*@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
			//Register the DateFormat Property Editor		
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			CustomDateEditor editor = new CustomDateEditor(df, false);
			binder.registerCustomEditor(Date.class, editor);
	 }
    */
    @ModelAttribute("civiliteList")
    public List<Civilite> populateCivilieList() {
      return interfaceService.findAllCivilite() ;
    }
    @ModelAttribute("profilListUser")
    public List<Profil> populateDroitListUser() {
      return interfaceService.findAllDroit() ;
    }
    @ModelAttribute("profilList")
    public List<Profil> populateDroitList() {
      return interfaceService.findAllDroits() ;
    }
    @ModelAttribute("profilListRev")
    public List<Profil> profilRevList(){
    	
    	return interfaceService.profilRevList();
    } 
    @ModelAttribute("revendeurList")
    public List<Revendeur> populateRevendeurList() {
      return interfaceService.findAllRevendeur();
    }
    @ModelAttribute("userList")
    public List<User> populateUserList() {
      return interfaceService.findAllUser();
    }

   
    
    @ModelAttribute("etatCompteList")
    public List<EtatCompte> populateEtatCompteList() {
      return interfaceService.findAllEtatCompte();
    }
    
    @ModelAttribute("langueList")
    public List<Langue> populateLangueList() {
      return interfaceService.findAllLangue();
    }
       
    @ModelAttribute("clientList")
    public List<Client> populateClientList() {
      return interfaceService.findAllClient();
    }
    public void setInterfaceService(InterfaceService interfaceService) {
        this.interfaceService = interfaceService;
    }
    
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex) {
        logger.error("Exception occured", ex);
        ModelMap model = new ModelMap();
        model.addAttribute("class", ClassUtils.getShortName(ex.getClass()));
        model.addAttribute("message", ex.getMessage());
        return new ModelAndView("errors/error", model);
    }
    /*@RequestMapping(value="/listUsers" ,method = RequestMethod.POST)
   	public ModelAndView searchContacts(
   			@RequestParam(required= false, defaultValue="") Integer pProfil,
   			@RequestParam(required= false, defaultValue="") String pMail,
   			@RequestParam(required= false, defaultValue="")  Integer pEtatCompte)
   	{
   		ModelAndView _mav = new ModelAndView("listUsers");
   		List<User> _listuser = interfaceService.findAllUserByProfilMailEtat(pProfil, pMail, pEtatCompte);
   		_mav.addObject("SEARCH_USER_RESULTS_KEY", _listuser);
   		return _mav;
   	}*/
    
   
   @InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		//binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));

		binder.registerCustomEditor(List.class, new CustomCollectionEditor(List.class));

		// for spring in gae?
		//binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));

		binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
		binder.registerCustomEditor(Boolean.class, new CustomBooleanEditor(false));
		binder.registerCustomEditor(Long.class, new CustomNumberEditor(Long.class, true));
		
		//
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	}
    @RequestMapping(value = "/listRevendeurs", method = RequestMethod.GET)
   	public String searchRevendeur(@ModelAttribute("searchCriteriaRevendeur") SearchCriteriaRevendeur searchCriteriaRevendeur, Model model) {
       	List<Revendeur> _listRevend= new ArrayList<Revendeur>();
   		if (searchCriteriaRevendeur.getIdSociete()!=null ) {
   			if(searchCriteriaRevendeur.getIdetat()!=null)
   				_listRevend = interfaceService.findRevendeurByEtatAndSociete(searchCriteriaRevendeur.getIdSociete(), searchCriteriaRevendeur.getIdetat());
   			else
   				_listRevend = interfaceService.findRevendeurBySociete(searchCriteriaRevendeur.getIdSociete());
   		}
   		else{
   			if(searchCriteriaRevendeur.getIdetat()!=null)
   				_listRevend = interfaceService.findRevendeurByEtat(searchCriteriaRevendeur.getIdetat());
   			else
   			_listRevend = interfaceService.findAllRevendeur();
   		}
   		
   		model.addAttribute("resultsearchRevendeur", _listRevend);
   		return "administration/revendeurs/listRevendeurs";
   	}
    @RequestMapping(value = "/listEmailAutomatique", method = RequestMethod.GET)
   	public String searchRevendeurByMail(@ModelAttribute("searchCriteriaRevendeurmail") SearchRevByMail searchCriteriaRevendeur, Model model) {
       	List<Revendeur> _listRevend= new ArrayList<Revendeur>();
   		if (searchCriteriaRevendeur.getMail()!=null && !searchCriteriaRevendeur.getMail().isEmpty())
   		{
   			_listRevend = interfaceService.findAllRevendeurByMail(searchCriteriaRevendeur.getMail());
   		}
   		/*else{
   			_listRevend = interfaceService.findAllRevendeur();
   		}*/
   		model.addAttribute("resultsearchRevendeuByMail", _listRevend);
   		return "administration/revendeurs/listEmailAutomatique";
   	}
    
    protected User getCurrentUser(HttpServletRequest request) {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    
  //modif mija   
    
    @ModelAttribute("paysList")
    public List<Pays> populatePaysList() {
      return interfaceService.findAllPays();
    }
    
    @ModelAttribute("modeReglementList")
    public List<ModeReglement> populateModeReglementList() {
      return interfaceService.findAllModeReglement();
    }
    
    @ModelAttribute("deviseList")
    public List<Devise> populateDeviseList() {
      return interfaceService.findAllDevise();
    }
    @ModelAttribute("societeList")
    public List<Societe> populateSocieteList() {
      return interfaceService.findAllSociete("revendeur");
    }

   
	/* @RequestMapping(value="/editUser/{id}", method=RequestMethod.GET)
	 public String editUser(@PathVariable("id")Integer id, Model model)
	 {
	   		//ModelAndView mav = new ModelAndView("newUser");
	   		User _user = interfaceService.findByIdUser(id);		
	   		model.addAttribute("editUser", _user);
	   		return "administration/utilisateurs/UpdateUser";
	}*/
	
	 @RequestMapping(value="/modifUser", method=RequestMethod.POST)
	 public String updateUser(@ModelAttribute("UpdateUser") User user, Model model)
	 {
	   		//ModelAndView mav = new ModelAndView("newUser");
		 	interfaceService.saveLogin(user);	
	   		return "redirect:listUser";
	} 
	 
	 @RequestMapping(value = "/listUser", method = RequestMethod.GET)
	   	public String searchUser(@ModelAttribute("searchCriteria") SearchCriterieUser searchCriteria, Model model) {
	       	List<User> _listUser= new ArrayList<User>();
	   		//if (searchCriteria.getMail()!=null && !searchCriteria.getMail().trim().isEmpty()) {
	   			//if(searchCriteria.getIdetat()!=null)
	   				//if (searchCriteria.getIdprofil()!=null) {
	   					_listUser= interfaceService.findAllUserByProfilMailEtat(searchCriteria.getIdprofil(),
	   							searchCriteria.getMail(), searchCriteria.getIdetat());
	   				/*}
	   				else
	   				_listUser= interfaceService.findAllUserByMailEtat(searchCriteria.getMail(), searchCriteria.getIdetat());
	   			if (searchCriteria.getIdprofil()!=null)
	   				_listUser= interfaceService.findAllUserByProfilMail(searchCriteria.getIdprofil(), searchCriteria.getMail());
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
	   		}*/
	   		model.addAttribute("resultsearchtUser", _listUser);
	   		return "administration/utilisateurs/listUser";
	   	}
	 @RequestMapping(value="/detailUser/{id}", method=RequestMethod.GET)
	 public String detailUser(@PathVariable("id")Integer id, Model model)
	 {
	   		//ModelAndView mav = new ModelAndView("newUser");
	   		User _user = interfaceService.findByIdUser(id);		
	   		//interfaceService.saveLogin(_user);
	   		model.addAttribute("user", _user);
	   		return "administration/utilisateurs/detailUser";
	 }
	 @RequestMapping(value="/newRevendeur/{id}", method=RequestMethod.GET)
		public String editRevendeur(@PathVariable("id")Integer id, ModelMap model)
		{   		
		   		Revendeur _reve = interfaceService.findRevendeurById(id);		
		   		model.addAttribute("revendeur", _reve);
		   		List<ProduitRevendeur> listprodRev = new ArrayList<ProduitRevendeur>(); 
		   		listprodRev = _reve.getProduitRevendeurs();
		   		
		   		/*List<ProduitRevendeur> listprodRev = interfaceService.findAllProduitRevendeur(_reve); 
		   		model.addAttribute("listprodRev", listprodRev);*/
		   		model.addAttribute("listprodRev", listprodRev);
		   		return "administration/revendeurs/UpdateRevendeur";
		}
		
		@RequestMapping(value="/detailRevendeur/{id}", method=RequestMethod.GET)
		public String detailrevendeur(@PathVariable("id")Integer id, ModelMap model)
		{   		
		   		Revendeur _reve = interfaceService.findRevendeurById(id);		
		   		model.addAttribute("editRevendeur", _reve);
		   		List<ProduitRevendeur> listprodRev = new ArrayList<ProduitRevendeur>(); 
		   		listprodRev = _reve.getProduitRevendeurs();
		   		
		   		/*List<ProduitRevendeur> listprodRev = interfaceService.findAllProduitRevendeur(_reve); 
		   		model.addAttribute("listprodRev", listprodRev);*/
		   		model.addAttribute("listprodRev", listprodRev);
		   		return "administration/revendeurs/detailRevendeur";
		}
	@ModelAttribute("listproduitrevendeurbyrevendeur")
	public List<ProduitRevendeur> getListProduitRevendeurs() {
		Authentication auth =SecurityContextHolder.getContext().getAuthentication();
		User currentUser = interfaceService.findUserByMail(auth.getName());
		if(currentUser.getProfil().getId()==3){
			Revendeur revendeurassocie = interfaceService.findRevendeurByCurrentuser(currentUser);
			List<ProduitRevendeur> listproduitrevendeur = interfaceService.findAllProduitRevendeur(revendeurassocie);
			return listproduitrevendeur;
		}
		else
			return interfaceService.findAllProduitRevendeur();

	}
	@ModelAttribute("listproduitrevendeur")
	public List<ProduitRevendeur> getListProduitRevendeur() {
	   List<ProduitRevendeur> listproduitrevendeur = interfaceService.findAllProduitRevendeur();
	   return listproduitrevendeur;
	}
	@ModelAttribute("listproduit")
	public List<Produit> getListProduit() {
	   List<Produit> listproduit = interfaceService.findAllProduit();
	   return listproduit;
	}
	@ModelAttribute("get_frns_list")
	public List<Fournisseur> get_List_Frns() {
		if(Parameters.group!=null)
			return interfaceService.getListFrnst(Parameters.group);
		else
			return interfaceService.getListFrnst();
	}
	/*
	@ModelAttribute("get_frns_list")
	public List<Fournisseur> getListFrns(){
		List<Fournisseur> fournisseurs = interfaceService.getListFrnst(Parameters.group);
		
		return fournisseurs;
		
	}*/
	///@PreAuthorize("isAuthenticated()")
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