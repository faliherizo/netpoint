/**
 * 
 */
package com.netPoint.applications.site.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.codec.Base64;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.ClassUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.SessionStatus;
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
import com.netPoint.applications.site.model.forms.MailForm;
import com.netPoint.applications.site.model.forms.MailPwdForm;
import com.netPoint.applications.site.service.IProductService;
import com.netPoint.applications.site.service.InterfaceService;
import com.netPoint.applications.site.service.notify.Notifier;
import com.netPoint.applications.site.util.Divers;
import com.netPoint.applications.site.util.Parameters;
import com.netPoint.applications.site.validator.MailValidator;
import com.netPoint.applications.site.validator.PwdValidator;

/**
 * @author Faliherizo
 *
 */
@Controller
public class GestionUserController {
	 protected static final Logger logger = LoggerFactory.getLogger(GestionUserController.class);
	 @Autowired
	 protected Notifier notifier;
	 @Autowired
	 MailValidator mailvalidator;
	 @Autowired
	 PwdValidator pwdvalidator;
		   /**
			 * @param notifier set notification of email
			 */
		public void setNotifier(Notifier notifier) {
				this.notifier = notifier;
		}
	 	@Autowired
	    protected InterfaceService interfaceService;
		/**
		 * @param interfaceService the interfaceService to set
		 */
		public void setInterfaceService(InterfaceService interfaceService) {
			this.interfaceService = interfaceService;
		}
		@InitBinder
		protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
			//Register the DateFormat Property Editor		
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			CustomDateEditor editor = new CustomDateEditor(df, false);
			binder.registerCustomEditor(Date.class, editor);
			
		}
		/* @ModelAttribute("civiliteList")
		    public List<Civilite> populateCivilieList() {
		      return interfaceService.findAllCivilite() ;
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
		    }*/
		    
		   
		    
		    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
		    @ExceptionHandler(Exception.class)
		    public ModelAndView handleException(Exception ex) {
		        logger.error("Exception occured", ex);
		        ModelMap model = new ModelMap();
		        model.addAttribute("class", ClassUtils.getShortName(ex.getClass()));
		        model.addAttribute("message", ex.getMessage());
		        return new ModelAndView("errors/error", model);
		    }
		   /* @ModelAttribute("paysList")
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
		  /*  @ModelAttribute("societeList")
		    public List<Societe> populateSocieteList() {
		      return interfaceService.findAllSociete();
		    }*/
		   
		    @RequestMapping(value ="/changepassword/{login}", method = RequestMethod.GET)
		  	public String getchangepassword(@PathVariable("login") String login, ModelMap modelmap)
		  	{
		    	String mail =new String(Base64.decode(login.getBytes())); 
		    	//User user= interfaceService.findUserByMail(mail);
		    	//modelmap.addAttribute("user",user);
		    	MailPwdForm mailpwdfrm = new MailPwdForm();
		    	mailpwdfrm.setMail(mail);
		    	modelmap.addAttribute("mailpwdfrm", mailpwdfrm);
		  		return "/changepassword";
		  	}
		    @RequestMapping(value ="/changepassword/{login}", method = RequestMethod.POST)
		    public String getnewPassword(@ModelAttribute("mailpwdfrm")MailPwdForm mailpwdfrm,BindingResult result, SessionStatus status, ModelMap modelMap
		    		/*@RequestParam(value="mail",required=true)String email,@RequestParam(value="password", required=true) String password, @RequestParam(value="con_password", required=true) String con_password*/)throws MessagingException
		    {
		    	pwdvalidator.validate(mailpwdfrm, result);
	    		if (result.hasErrors()) {
	    			modelMap.addAttribute("messageerror", "error");
	                return "redirect:/changepassword/"+new String(Base64.encode(mailpwdfrm.getMail().getBytes()));
	            }else{ 
				     try {
						User user = interfaceService.findUserByMail(mailpwdfrm.getMail());
						 user.setPasswordNonEncoder(mailpwdfrm.getPassword());
						 user.setPwd(mailpwdfrm.getPassword());
						 user.setPwd2(mailpwdfrm.getPassword());
						 user.setChangepwd(true);
						 user= Divers.generatePassword(user);
						interfaceService.updateUser(user);
						if(user.getPwd() != null && user.getLogin() != null){
							notifier.notifyRecupPwd(user);	
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    	}
		    	return "redirect:/login";	
		    }
		    @RequestMapping(value ="/envoiemailsucces", method = RequestMethod.GET)
		    public String envoiemailsucces()
		    {
		    	return "/envoiemailsucces";
		    }
		  /*  @ModelAttribute("access_admin")
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
		*/
}