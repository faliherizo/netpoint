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

import com.netPoint.applications.site.model.User;
import com.netPoint.applications.site.service.InterfaceService;
import com.netPoint.applications.site.util.Divers;
import com.netPoint.applications.site.util.Parameters;
import com.netPoint.applications.site.validator.UserValidator;

/**
 * @author Faliherizo
 *
 */
@Controller
@RequestMapping(value = "/UpdateUser")
@SessionAttributes({"user"})
public class UpdateUserController extends SiteController{
	protected static final Logger logger = LoggerFactory.getLogger(UpdateUserController.class);
	@Autowired
	protected UserValidator userValidator;
    @Autowired
    protected InterfaceService interfaceService;
    //UserValidator userValidator;
    @InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		//Register the DateFormat Property Editor		
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	 @RequestMapping(method=RequestMethod.POST)
	 public String processSubmit(@ModelAttribute("user") User user, BindingResult result, SessionStatus status,Model model)
	 {
			 try {
				// SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
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
					 	return "redirect:listUser";
					 	
			        }
				 	
				 	
				} catch (Exception e) {
					// TODO: handle exception
					return "administration/utilisateurs/UpdateUser?id="+user.getId();
				}
	} 
	 @RequestMapping(method=RequestMethod.GET)
	 public ModelAndView edit(@RequestParam("id")Integer id)
	  {
		 ModelAndView mav = new ModelAndView("administration/utilisateurs/UpdateUser");
		 User _user = interfaceService.findByIdUser(id);
		 _user.setPwd2(_user.getPwd());
	 	  mav.addObject("user", _user);
	 	  return mav;
	  }
	/**
	 * @param userValidator the userValidator to set
	 */
	public void setUserValidator(UserValidator userValidator) {
		this.userValidator = userValidator;
	}
	/**
	 * @param interfaceService the interfaceService to set
	 */
	public void setInterfaceService(InterfaceService interfaceService) {
		this.interfaceService = interfaceService;
	}
	 
}
