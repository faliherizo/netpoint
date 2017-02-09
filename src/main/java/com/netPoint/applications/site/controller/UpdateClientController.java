/**
 * 
 */
package com.netPoint.applications.site.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.netPoint.applications.site.model.Client;
import com.netPoint.applications.site.model.Commande;
import com.netPoint.applications.site.model.DureeCommande;
import com.netPoint.applications.site.model.Fournisseur;
import com.netPoint.applications.site.model.TypeCommande;
import com.netPoint.applications.site.model.User;
import com.netPoint.applications.site.service.IProductService;
import com.netPoint.applications.site.service.IServiceCommande;
import com.netPoint.applications.site.service.IServiceUser;
import com.netPoint.applications.site.service.InterfaceServiceClient;
import com.netPoint.applications.site.validator.CommandeValidator1;
import com.netPoint.applications.site.validator.CommandeValidator2;

/**
 * @author Faliherizo
 *
 */
@Controller
@RequestMapping(value = "/UpdateClient")
@SessionAttributes({"command","client"})
public class UpdateClientController extends SiteController{
	public static final Logger logger = LoggerFactory.getLogger(UpdateClientController.class);
	@Autowired
	private InterfaceServiceClient interceInterfaceServiceClient;
	@Autowired
	private IServiceUser iServiceUser;
	@Autowired
	private IServiceCommande iServiceCommande;
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
	public void setClientValidator1(CommandeValidator1 commandeValidator1) {
		this.commandeValidator1 = commandeValidator1;
	}
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
	/**
	 * @param interceInterfaceServiceClient the interceInterfaceServiceClient to set
	 */
	@ModelAttribute("command")
	public Commande createCommande(){
		
		return new Commande();
	}
		 
	public void setInterceInterfaceServiceClient(
			InterfaceServiceClient interceInterfaceServiceClient) {
		this.interceInterfaceServiceClient = interceInterfaceServiceClient;
	}
	
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		//Register the DateFormat Property Editor		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		CustomDateEditor editor = new CustomDateEditor(df, false);
		binder.registerCustomEditor(Date.class, editor);
		
	}
	
	@RequestMapping(method=RequestMethod.GET)
	 public ModelAndView edit(@RequestParam("id")Integer id)
	  {
		 ModelAndView mav = new ModelAndView("commande/updateClient");
		 Client client = iServiceCommande.findClientById(id);
		 client.getUser().setPwd2(client.getUser().getPwd());
	 	  mav.addObject("client", client);
	 	  return mav;
	  }
	@RequestMapping(method=RequestMethod.POST)
	public String processForm(@RequestParam("_page") int page, @ModelAttribute("client")Client client,BindingResult result,
			final ModelMap model,HttpSession session, SessionStatus statut){
		/*---------- page 1-------------------*/
		if(page==2){
			//Validation
			commandeValidator1.validate(client, result);
			if(result.hasErrors()){
				return "/commande/updateClient";
			}
			else{
				//Update Client
				client= iServiceCommande.SavaOrUpdateClient(client);
				Commande command = new Commande();
				
				command.setClient(client);
				model.addAttribute("command", command);
				return "/commande/newClient2";
			}
		}
		else{
			statut.setComplete();
			return "";

		}
		
	}
	
	@RequestMapping(params="_finish")
	public String recapitule(@ModelAttribute("command")Commande command, BindingResult result, SessionStatus statut, ModelMap model){
		commandeValidator2.validate(command, result);
		if(result.hasErrors()){
			return "/commande/newClient2";
		}
		else{
			//Save  commande client
			return "/commande/createclientsuccess";
		}
		
	}
	@RequestMapping(params="_submit")
	public String formSubmit(@ModelAttribute("command")Commande command, BindingResult result, SessionStatus statut, ModelMap model){
		if(result.hasErrors()){
			return "/commande/createclientsuccess";
		}
		else{
			iServiceCommande.SaveOrUpdate(command);

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
	    return "redirect:/listClients";
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
