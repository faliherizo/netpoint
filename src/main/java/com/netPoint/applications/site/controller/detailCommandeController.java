/**
 * 
 */
package com.netPoint.applications.site.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.netPoint.applications.site.model.Client;
import com.netPoint.applications.site.model.ClientRevendeur;
import com.netPoint.applications.site.model.Commande;
import com.netPoint.applications.site.model.CommandeRevendeur;
import com.netPoint.applications.site.service.IServiceCommande;
import com.netPoint.applications.site.service.IServiceUser;
import com.netPoint.applications.site.util.Parameters;

/**
 * @author Faliherizo
 *
 */
@Controller
@RequestMapping(value = "/detailCommand")
@SessionAttributes({"command"})
public class detailCommandeController {
	public static final Logger logger = LoggerFactory.getLogger(detailCommandeController.class);
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
		 ModelAndView mav = new ModelAndView("commande/detailCommand");
		 Commande command = iServiceCommande.getCommandeById(id);
		 //CommandeRevendeur commanderev= iServiceCommande.findCommandeRev(command);
		// mav.addObject("commanderev", commanderev);
	 	 mav.addObject("command", command);
	 	 return mav;
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
