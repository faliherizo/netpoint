/**
 * 
 */
package com.netPoint.applications.site.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.netPoint.applications.site.model.Fournisseur;
import com.netPoint.applications.site.model.Produit;
import com.netPoint.applications.site.model.Revendeur;
import com.netPoint.applications.site.model.SearchCriterieUser;
import com.netPoint.applications.site.model.User;
import com.netPoint.applications.site.service.IProductService;
import com.netPoint.applications.site.util.Parameters;
import com.netPoint.applications.site.validator.UserValidator;

/**
 * @author Faliherizo
 *
 */
@Controller
@RequestMapping(value = "/newProduct")
@SessionAttributes({"produit","fournisseur"})
public class ProductController extends SiteController
{
	 public static final Logger logger = LoggerFactory.getLogger(ProductController.class);   
	 @Autowired
	 private IProductService productService;
	 
	/**
	 * @param productService the productService to set
	 */
	public void setProductService(IProductService productService) {
		this.productService = productService;
	}
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		//Register the DateFormat Property Editor		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		CustomDateEditor editor = new CustomDateEditor(df, false);
		binder.registerCustomEditor(Date.class, editor);
		
	}
	@RequestMapping(value="/newProduct", method = RequestMethod.POST)
    public String processSubmit(@ModelAttribute("produit") Produit produit, BindingResult result, final ModelMap model, SessionStatus status) {
		try {
			Fournisseur fournisseur = productService.findFrnsByUser(Parameters.userconnect);
			produit.setFournisseur(fournisseur);
			productService.addOrUpdateProduct(produit);
		} catch (Exception e) {
			logger.error("erreur lors de l'enregistrement du produit", e.getMessage());
		}
		return "redirect:/listProduct";
	}
	
	 @RequestMapping(method = RequestMethod.GET)
	    public String initForm(ModelMap model) {
	        Produit produit = new Produit();
	       
	        model.addAttribute("produit", produit);
	        return "produit/newProduct";
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
