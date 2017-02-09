package com.netPoint.applications.site.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.netPoint.applications.site.model.EtatCompte;
import com.netPoint.applications.site.model.Profil;
import com.netPoint.applications.site.model.SearchCriterieUser;
import com.netPoint.applications.site.model.User;
import com.netPoint.applications.site.service.notify.Notifier;
import com.netPoint.applications.site.util.Divers;
import com.netPoint.applications.site.validator.UserValidator;

@Controller
@RequestMapping(value = "/newUser")
@SessionAttributes({"user"})
public class UserController extends SiteController {
    public static final Logger logger = LoggerFactory.getLogger(UserController.class);   
    UserValidator userValidator;
    @InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		//Register the DateFormat Property Editor		
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
    @Autowired
    public UserController(UserValidator userValidator) {
        this.userValidator = userValidator;
    }
	@RequestMapping(method = RequestMethod.POST)
    public String processSubmit(@ModelAttribute("user") User user0, BindingResult result, SessionStatus status, ModelMap modelMap) {
		User user = new User();
		user = user0;	
		userValidator.validate(user, result);
        
        if (result.hasErrors()) {
            return "administration/utilisateurs/newUser";
        } else {
            try {
            	Authentication auth =SecurityContextHolder.getContext().getAuthentication();
        		User currentUser = interfaceService.findUserByMail(auth.getName());
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date _date = new Date();
				if(user.getDateCreation()==null)
					user.setDateCreation(_date);
				if(user.getDateFinChar()!=null)
					user.setDateFin(sdf.parse(user.getDateFinChar()));
				user.setCreePar(currentUser.getNom()+" "+currentUser.getPrenom());
				if( user.getDateFin().toString()==null){
					user.setDateFin(null);
				}
				if(user.getRevendeur().getId()==null)
					user.setRevendeur(null);
				interfaceService.saveLogin(user);
				//Notification de login et mot de passe par mail
				if(user.getPwd() != null && user.getLogin() != null){
					notifier.notify(user);	
				}
				modelMap.addAttribute("profil", user.getProfil().getLibelle());
				logger.trace("user create success"+user.getLogin()+" name: "+user.getNom()+" "+user.getPrenom());
				status.setComplete();
				return "administration/utilisateurs/userSuccess";
			} catch (Exception e) {
				logger.error(e.getMessage());
				e.printStackTrace();
				return "/administration/utilisateurs/newUser";
			}
            
        }
    }
  
    @RequestMapping(method = RequestMethod.GET)
    public String initForm(ModelMap model) {
        User user = new User();
        user.setDateCreation(Calendar.getInstance().getTime());
        model.addAttribute("user", user);
        return "administration/utilisateurs/newUser";
    }
    

	
}
