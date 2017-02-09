/**
 * 
 */
package com.netPoint.applications.site.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.netPoint.applications.site.service.InterfaceService;
import com.netPoint.applications.site.service.notify.Notifier;
import com.netPoint.applications.site.util.Parameters;

/**
 * @author Faliherizo
 *
 */
@Controller
public class GestionRevendeurController {
	/**
	 * @param interfaceService the interfaceService to set
	 */
	public void setInterfaceService(InterfaceService interfaceService) {
		this.interfaceService = interfaceService;
	}
	protected static final Logger logger = LoggerFactory.getLogger(GestionRevendeurController.class);
    @Autowired
    protected InterfaceService interfaceService;
    //UserValidator userValidator;
	@Autowired
	protected Notifier notifier;
	   /**
		 * @param notifier set notification of email
		 */
	public void setNotifier(Notifier notifier) {
			this.notifier = notifier;
	}
	
	@RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.GET)
  	public String deleteUser(@PathVariable("id")Integer userId)
  	{
  		//ModelAndView mav = new ModelAndView("redirect:userList");
		try {
			interfaceService.RemoveUser(userId);
			
		} catch (Exception e) {
			logger.debug("remove user failed");
		}
  		
  		
  		return "redirect:/listUser";
  	}
	@RequestMapping(value = "/deleteRevendeur/{id}", method = RequestMethod.GET)
	public String deleteRevendeur(@PathVariable("id")Integer revendeurId)
	{
	  		//ModelAndView mav = new ModelAndView("redirect:userList");
		try {
			interfaceService.removeRevendeur(revendeurId);
			logger.debug("Revendeur delete success");
			
		} catch (Exception e) {
			logger.debug("Remove revendeur failed");
		}
	  		
	  		
	  		return "redirect:/listRevendeurs";
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
