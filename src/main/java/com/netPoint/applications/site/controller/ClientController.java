/**
 * 
 */
package com.netPoint.applications.site.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.netPoint.applications.site.dao.IDaoProduct;
import com.netPoint.applications.site.model.Civilite;
import com.netPoint.applications.site.model.Client;
import com.netPoint.applications.site.model.ClientRevendeur;
import com.netPoint.applications.site.model.ClientRevendeurId;
import com.netPoint.applications.site.model.Commande;
import com.netPoint.applications.site.model.Devise;
import com.netPoint.applications.site.model.DureeCommande;
import com.netPoint.applications.site.model.EtatCompte;
import com.netPoint.applications.site.model.Fournisseur;
import com.netPoint.applications.site.model.Langue;
import com.netPoint.applications.site.model.ModeReglement;
import com.netPoint.applications.site.model.Pays;
import com.netPoint.applications.site.model.Produit;
import com.netPoint.applications.site.model.ProduitRevendeur;
import com.netPoint.applications.site.model.Profil;
import com.netPoint.applications.site.model.Revendeur;
import com.netPoint.applications.site.model.Societe;
import com.netPoint.applications.site.model.TypeCommande;
import com.netPoint.applications.site.model.User;
import com.netPoint.applications.site.service.IProductService;
import com.netPoint.applications.site.service.IServiceCommande;
import com.netPoint.applications.site.service.IServiceUser;
import com.netPoint.applications.site.service.InterfaceService;
import com.netPoint.applications.site.service.InterfaceServiceClient;
import com.netPoint.applications.site.service.notify.Notifier;
import com.netPoint.applications.site.util.Parameters;
import com.netPoint.applications.site.validator.CommandeValidator1;
import com.netPoint.applications.site.validator.CommandeValidator2;

/**
 * @author Faliherizo
 *
 */
@Controller
@RequestMapping("/newClient")
@SessionAttributes({"command","client"})
public class ClientController extends SiteController{
	public static final Logger logger = LoggerFactory.getLogger(ClientController.class);
	@Autowired
	private InterfaceServiceClient interceInterfaceServiceClient;
	@Autowired
	private IServiceUser iServiceUser;
	@Autowired
	private IServiceCommande iServiceCommande;
	/**
	 * @param iServiceCommande the iServiceCommande to set
	 */
	public void setiServiceCommande(IServiceCommande iServiceCommande) {
		this.iServiceCommande = iServiceCommande;
	}

	/*
	@Autowired
    protected InterfaceService interfaceService;
    @Autowired
    protected IProductService iProductService;
    */
    /**
	 * @param iProductService the iProductService to set
	 */
	/*
	public void setiProductService(IProductService iProductService) {
		this.iProductService = iProductService;
	}
	//UserValidator userValidator;
	@Autowired
	protected Notifier notifier;
	   /**
		 * @param notifier set notification of email
		 */
	/*
	public void setNotifier(Notifier notifier) {
			this.notifier = notifier;
	}
	*/
	/**
	 * @param iServiceUser the iServiceUser to set
	 */
	public void setiServiceUser(IServiceUser iServiceUser) {
		this.iServiceUser = iServiceUser;
	}
	@Autowired
	private IProductService productService;
	private Commande command;
	@Autowired
	CommandeValidator1 commandeValidator1; 
	@Autowired
	CommandeValidator2 commandeValidator2;
	/**
	 * @param commandeValidator2 the commandeValidator2 to set
	 */
	public void setCommandeValidator2(CommandeValidator2 commandeValidator2) {
		this.commandeValidator2 = commandeValidator2;
	}

	/**
	 * @param clientValidator1 the clientValidator1 to set
	 */
	
	public void setClientValidator1(CommandeValidator1 commandeValidator1) {
		this.commandeValidator1 = commandeValidator1;
	}

	/**
	 * @param interceInterfaceServiceClient the interceInterfaceServiceClient to set
	 */
	/*@ModelAttribute("command")
	public Commande createCommande(){	
		return new Commande();
	}*/
		 
	public void setInterceInterfaceServiceClient(
			InterfaceServiceClient interceInterfaceServiceClient) {
		this.interceInterfaceServiceClient = interceInterfaceServiceClient;
	}
	
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
			//Register the DateFormat Property Editor		
	    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			dateFormat.setLenient(false);
			binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	@RequestMapping(method=RequestMethod.GET) 
	public String getInitialPage(HttpServletRequest request,HttpServletResponse reponse,ModelMap model ){
		 ModelAndView mav = new ModelAndView("commande/newClient");
		 Client client = new Client();
		 //session.setAttribute("client", client);
	 	  mav.addObject("client", client);
	 	 HttpSession session = request.getSession();
			session.setAttribute("client", client);
	 	 // return mav;
		
		model.addAttribute("client",client);
		return "/commande/newClient";
	}
	@RequestMapping(method=RequestMethod.POST)
	public String processForm(@RequestParam("_page") int page, @ModelAttribute("client")Client client,BindingResult result,
			final ModelMap model,HttpServletRequest request,HttpServletResponse reponse, SessionStatus statut){
		//Detection de update ou non
		Integer idcommande;
		Integer idclient;
		/*---------- page 1-------------------*/
		if(page==2){
			//Validation
			commandeValidator1.validate(client, result);
			if(result.hasErrors()){
				return "/commande/newClient";
			}
			
			else{
				try {
					Revendeur revendeur = iServiceCommande.getRevendeurBy(client.getNumeroRevendeur());
					Parameters.revendeurUse = revendeur;
					client=iServiceCommande.SaveClient(client, revendeur);
					Commande command = new Commande();
					//model.addAttribute("produitrevendeurs", revendeur.getProduitRevendeurs());
					command.setClient(client);
					//HttpSession session = request.getSession();
					//session.setAttribute("command", command);
					model.addAttribute("command", command);
			//		session.setAttribute("command", command);
				} catch (Exception e) {
					logger.error("Error saving client", e);
				}
				
				return "/commande/newClient2";
				
			}
		}
		else{
			statut.setComplete();
			return "";

		}
		
	}
	@RequestMapping(params="_boncommande")
	public String boncommande(@ModelAttribute("command")Commande command, BindingResult result,final ModelMap model, HttpServletRequest request,HttpServletResponse reponse, SessionStatus statut){
		
		
		try {
			//TODO Validation
			commandeValidator2.validate(command, result);
			if(result.hasErrors()){
				return "/commande/newClient2";
			}
			else{
				Produit produit = productService.getProductById(command.getProduit().getId());
				command.setProduit(produit);
				ProduitRevendeur produitRevendeur = iServiceCommande.GetProduitRevendeur(produit, Parameters.revendeurUse);
				command= iServiceCommande.CalculPrix(produitRevendeur, command);
				//float prixsansremise= iServiceCommande.CalculPrixSansRemise(command);
				model.addAttribute("produit", produit);
				model.addAttribute("command", command);
				/*HttpSession session = request.getSession();
				session.setAttribute("command", command);
				session.setAttribute("produit", produit);*/
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "/commande/boncommande";
	}
	@RequestMapping(params="_finish")
	public String recapitule(@ModelAttribute("command")Commande command, BindingResult result, SessionStatus statut, ModelMap model, HttpServletRequest request,HttpServletResponse reponse){
		//Validate
		HttpSession session = request.getSession();
		session.setAttribute("command", command);
		commandeValidator2.validate(command, result);
		if(result.hasErrors()){
			return "/commande/newClient2";
		}
		else{
			
			return "/commande/createclientsuccess";
		}
	}
	
	
	@RequestMapping(params="_submit")
	public String formSubmit(@ModelAttribute("command")Commande commande, BindingResult result, SessionStatus statut, ModelMap model ,HttpServletRequest request,HttpServletResponse reponse){
		HttpSession session = request.getSession();
		session.setAttribute("command", command);
		if(result.hasErrors()){
			return "/commande/createclientsuccess";
		}
		else{
			iServiceCommande.SaveOrUpdate(commande);
			statut.setComplete();
			return "redirect:/listClients";
		}
	}
	@RequestMapping(params="_cancel")
	public String cancelled(SessionStatus status,@ModelAttribute("command")Commande commande){
		
		status.setComplete();
		return "redirect:/listClients";
		
	}
	@ExceptionHandler(HttpSessionRequiredException.class)
	public String restartFlow() {
	    return "redirect:/commande/newClient";
	}
	
	@ModelAttribute("get_duree_list")
	public List<DureeCommande> getListDuree(){
		return iServiceCommande.getListDureeCommande();
	}
	@ModelAttribute("get_frns_list")
	public List<Fournisseur> getFrns() {
		return iServiceUser.getListFournisseur();
	}
	 @ModelAttribute("get_list_typecmd")
	    public List<TypeCommande> typeCmd() {
	      return interfaceService.getListTypeCommande();
	 }
	 

}
