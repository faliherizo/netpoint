/**
 * 
 */
package com.netPoint.applications.site.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.netPoint.applications.site.model.Client;
import com.netPoint.applications.site.model.Commande;
import com.netPoint.applications.site.service.IServiceCommande;
import com.netPoint.applications.site.service.IServiceUser;
import com.netPoint.applications.site.service.InterfaceServiceClient;
import com.netPoint.applications.site.util.Parameters;

/**
 * @author Faliherizo
 *
 */
@Controller
@RequestMapping("/newCommand")
public class AddCommandController {
	public static final Logger logger = LoggerFactory.getLogger(AddCommandController.class);
	@Autowired
	private IServiceCommande iServiceCommande;
	
	@RequestMapping(method = RequestMethod.POST)
	public String formSubmit(@ModelAttribute("command")Commande commande, BindingResult result, SessionStatus statut, ModelMap model){
		try {
			iServiceCommande.SaveOrUpdate(commande);

			statut.setComplete();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}	
		return "redirect:/listClients";
		
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
