/**
 * 
 */
package com.netPoint.applications.site.controller;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.netPoint.applications.site.model.User;
import com.netPoint.applications.site.model.forms.MailForm;
import com.netPoint.applications.site.service.InterfaceService;
import com.netPoint.applications.site.service.notify.Notifier;
import com.netPoint.applications.site.util.Parameters;
import com.netPoint.applications.site.validator.MailValidator;
import com.netPoint.applications.site.validator.PwdValidator;

/**
 * @author Faliherizo
 *
 */
@Controller
public class LoginController {
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
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(ModelMap model) {
		/*if(Parameters.userconnect!=null)
		{
	    	return "redirect:/commandesMoisEnCours";
    	}
    	else{*/
    		return "login";
		//}
 
	}
	@RequestMapping(value="/maildrop", method = RequestMethod.GET)
	public String maildrop(ModelMap model) {
		MailForm mailform = new MailForm();
		model.addAttribute("mailform", mailform);
		return "maildrop";
 
	}
	 @RequestMapping(value="/maildrop", method =RequestMethod.POST)
	  	public String newpassword(@ModelAttribute("mailform")MailForm mailform, BindingResult result, SessionStatus status, ModelMap modelMap/*@RequestParam(value="mail",required=true)String email*/) throws MessagingException
	    {
	    		mailvalidator.validate(mailform, result);
	    		
	    		if (result.hasErrors()) {
	                return "/maildrop";
	            } 
				User _user = interfaceService.findUserByMail(mailform.getMail());
	  				notifier.sendUrlToChangeOrPwd(_user);
	  				return "/envoiemailsucces";
	  		
	  	}
	@RequestMapping(value="/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
 
		model.addAttribute("error", "true");
		return "login";
 
	}
	
	/*@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
		Parameters.initialise();
		return "login";
 
	}*/
    @RequestMapping(value ="/changepassword", method = RequestMethod.GET)
  	public String changepassword(ModelMap model)
  	{
  		return "changepassword";
  	}
    @RequestMapping("/erreur")
	public String error(){
		return "errorpage";
	}
    @RequestMapping("/403")
	public String error403(){
		return "403";
	}
    @RequestMapping(value="/accessdenied")
	public String accessdenied(ModelMap map){
		return "accessdenied";
	}
  
}
