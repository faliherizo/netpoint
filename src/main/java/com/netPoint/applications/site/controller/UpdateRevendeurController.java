/**
 * 
 */
package com.netPoint.applications.site.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.netPoint.applications.site.model.Revendeur;
import com.netPoint.applications.site.model.User;
import com.netPoint.applications.site.service.InterfaceService;
import com.netPoint.applications.site.util.Parameters;
import com.netPoint.applications.site.validator.RevendeurValidator;
import com.netPoint.applications.site.validator.UserValidator;

/**
 * @author Faliherizo
 *
 */
@Controller
@RequestMapping(value = "/UpdateRevendeur")
@SessionAttributes({"revendeur"})
public class UpdateRevendeurController extends SiteController{
	protected static final Logger logger = LoggerFactory.getLogger(SiteController.class);
	@Autowired
	protected RevendeurValidator revendeurValidator;
    @Autowired
    protected InterfaceService interfaceService;
    @InitBinder
   	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
   		//Register the DateFormat Property Editor		
       	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
   		dateFormat.setLenient(false);
   		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
   	}
    //UserValidator userValidator;
	 @RequestMapping(method=RequestMethod.POST)
	 public String processSubmit(@ModelAttribute("revendeur") Revendeur revendeur, BindingResult result, SessionStatus status,Model model)
	 {
			 try {
				 //revendeurValidator.validate(revendeur, result);
				 //if (result.hasErrors()) {
			           // return "administration/revendeurs/UpdateRevendeur?id="+revendeur.getId();
			       // }else{
					//ModelAndView mav = new ModelAndView("newUser");
				 	if(revendeur.getRevendeur()!=null && revendeur.getRevendeur().getId()==null)
						revendeur.setRevendeur(null);
				 	interfaceService.SaveOrUpdateRevendeur(revendeur);	
				 	status.setComplete();
			   		return "redirect:listRevendeurs";
				} catch (Exception e) {
					// TODO: handle exception
					return "administration/revendeurs/UpdateRevendeur?id="+revendeur.getId();
				}
	} 
	 @RequestMapping(method=RequestMethod.GET)
	 public ModelAndView edit(@RequestParam("id")Integer id, Model model)
	  {
		 ModelAndView mav = new ModelAndView("administration/revendeurs/UpdateRevendeur");
		 Revendeur revendeur = interfaceService.findRevendeurById(id);
		 User userconnect=Parameters.userconnect;
	        Boolean droitcreationrev= false;
	    	if(userconnect.getProfil().getId()==3)
	    	{
	    		Revendeur revendeur2=interfaceService.findRevendeurByCurrentuser(userconnect);
	    		if(revendeur2.getDroitCreationRevendeur()=="1" || revendeur.getId()==revendeur2.getId()){
	    			droitcreationrev=true;
	    		}
	    	}
	    	if(userconnect.getProfil().getId()==1 || userconnect.getProfil().getId()==2)
	    	{
	    		droitcreationrev=true;
	    	}
	      model.addAttribute("droitcreation", droitcreationrev);
	 	  mav.addObject("revendeur", revendeur);
	 	  return mav;
	  }
	/**
	 * @param userValidator the userValidator to set
	 */
	public void setRevendeurValidator(RevendeurValidator revendeurValidator) {
		this.revendeurValidator = revendeurValidator;
	}
	/**
	 * @param interfaceService the interfaceService to set
	 */
	public void setInterfaceService(InterfaceService interfaceService) {
		this.interfaceService = interfaceService;
	}
	 

}
