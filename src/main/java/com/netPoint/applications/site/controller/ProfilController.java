/**
 * 
 */
package com.netPoint.applications.site.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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

import com.netPoint.applications.site.model.User;
import com.netPoint.applications.site.util.Divers;
import com.netPoint.applications.site.util.Parameters;
import com.netPoint.applications.site.validator.UserValidator;

/**
 * @author Faliherizo
 *
 */
@Controller
@RequestMapping(value = "/myProfil")
@SessionAttributes({"user"})
public class ProfilController  extends SiteController {
    public static final Logger logger = LoggerFactory.getLogger(ProfilController.class);
    @Autowired
	protected UserValidator userValidator;
    @InitBinder
   	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
   		//Register the DateFormat Property Editor		
       	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
   		dateFormat.setLenient(false);
   		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
   	}
	/**
	 * @param userValidator the userValidator to set
	 */
	public void setUserValidator(UserValidator userValidator) {
		this.userValidator = userValidator;
	}
	 @RequestMapping(method=RequestMethod.GET)
    public ModelAndView myAccount(ModelMap model){
		Authentication auth =SecurityContextHolder.getContext().getAuthentication();
    	User user = interfaceService.findUserByMail(auth.getName());
    	user.setPwd2(user.getPwd());
    	model.addAttribute("user", user);
    	return new ModelAndView("administration/utilisateurs/myProfil", model);
    }
	 
	 @RequestMapping(method=RequestMethod.POST)
	 public String processSubmit(@ModelAttribute("user") User user, BindingResult result, SessionStatus status,Model model)
	 {
			 try {
				 userValidator.validateUserUpdate(user, result);
				 if (result.hasErrors()) {
			            return "redirect:UpdateUser?id="+user.getId();
			        }else{
			        	if(user.getRevendeur().getId()==null)
							user.setRevendeur(null);
			        	if(user.isChangepwd())
			        		user= Divers.generatePassword(user);
		        		interfaceService.updateUser(user);	
		        		status.setComplete();
		        		User userconnect = Parameters.userconnect;
		        		return "redirect:myProfil";
			        }
				 	
				} catch (Exception e) {
					return "administration/utilisateurs/UpdateUser?id="+user.getId();
				}
	} 
}
