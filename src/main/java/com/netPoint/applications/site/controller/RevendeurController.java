/**
 * 
 */
package com.netPoint.applications.site.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.netPoint.applications.site.model.ProduitRevendeur;
import com.netPoint.applications.site.model.ProduitRevendeurId;
import com.netPoint.applications.site.model.Revendeur;
import com.netPoint.applications.site.model.SearchCriteriaRevendeur;
import com.netPoint.applications.site.model.User;
import com.netPoint.applications.site.util.Divers;
import com.netPoint.applications.site.util.Parameters;
import com.netPoint.applications.site.validator.RevendeurValidator;
import com.netPoint.applications.site.validator.UserValidator;



/**
 * @author Faliherizo
 *
 */
@Controller
@RequestMapping(value = "/newRevendeur")
@SessionAttributes({"revendeur"})
public class RevendeurController extends SiteController {
    public static final Logger logger = LoggerFactory.getLogger(RevendeurController.class);   
    
    RevendeurValidator revendeurValidator;
    
    @Autowired
    public RevendeurController(RevendeurValidator revendeurValidator) {
        this.revendeurValidator = revendeurValidator;
    }
    @InitBinder
   	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
   		//Register the DateFormat Property Editor		
       	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
   		dateFormat.setLenient(false);
   		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
   	}
    @RequestMapping(method = RequestMethod.POST)
    public String processSubmit(@ModelAttribute("revendeur") Revendeur revendeur, BindingResult result,ModelMap model, SessionStatus status) {
	    	revendeurValidator.validate(revendeur, result);
	    	Boolean access= true;
			model.addAttribute("access_admin", access);
			model.addAttribute("droitcreation", true);
	        if (result.hasErrors()) {
	            return "administration/revendeurs/newRevendeur";
	        } else {
	        	try {
					
				
	        	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date _datef = new Date();
				if(revendeur.getUser().getDateCreation()==null)
					revendeur.getUser().setDateCreation(_datef);
				if(revendeur.getUser().getDateFinChar()!=null)
					revendeur.getUser().setDateFin(sdf.parse(revendeur.getUser().getDateFinChar()));
	            Date _date = new Date();
	            revendeur.setDateCreation(_date);
	            revendeur.setCodeRevendeur(Divers.generateAuthCode());
	          if(revendeur.getRevendeur().getId()==null){
	        	  revendeur.setRevendeur(null);
	        	  revendeur.getUser().setRevendeur(null);
	          }
	        	  
		          else
		        	  revendeur.getUser().setRevendeur(revendeur.getRevendeur());
		        User user =Divers.generatePassword(revendeur.getUser());
		        
				if(revendeur.getUser().getPwd() != null && revendeur.getUser().getLogin() != null){
						notifier.notify(user);	
				}
			
	            interfaceService.SaveOrUpdateRevendeur(revendeur);
	            logger.trace("revendeur create success"+user.getLogin()+" name: "+user.getNom()+" "+user.getPrenom()+" "+" society:"+revendeur.getSociete().getNom());
	            status.setComplete();
	            return "administration/revendeurs/revendeurSuccess";
	        	} catch (Exception e) {
	        		return "administration/revendeurs/newRevendeur";
				}
	        }
    }
    @RequestMapping(method = RequestMethod.GET)
    public String initForm(ModelMap model) {
        Revendeur revend = new Revendeur();
        User userconnect=Parameters.userconnect;
        Boolean droitcreationrev= false;
    	if(userconnect.getProfil().getId()==3)
    	{
    		Revendeur revendeur2=interfaceService.findRevendeurByCurrentuser(userconnect);
    		if(revendeur2.getDroitCreationRevendeur()=="1"){
    			droitcreationrev=true;
    		}
    	}
    	if(userconnect.getProfil().getId()==1 || userconnect.getProfil().getId()==2)
    	{
    		droitcreationrev=true;
    	}
    	
        revend.setDateCreation(Calendar.getInstance().getTime());
        model.addAttribute("revendeur", revend);
        model.addAttribute("droitcreation", droitcreationrev);
        return "administration/revendeurs/newRevendeur";
    }
   
   
}
