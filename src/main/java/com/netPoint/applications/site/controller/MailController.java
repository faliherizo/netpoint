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
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.netPoint.applications.site.model.MailConfig;
import com.netPoint.applications.site.model.Produit;
import com.netPoint.applications.site.model.User;
import com.netPoint.applications.site.service.IServiceMailConfig;
import com.netPoint.applications.site.util.Parameters;

/**
 * @author Faliherizo
 *
 */
@Controller
@RequestMapping(value = "/editmail")
public class MailController extends SiteController {
    public static final Logger logger = LoggerFactory.getLogger(MailController.class);
    @Autowired
    private IServiceMailConfig iservicemail;
	public void setIservicemail(IServiceMailConfig iservicemail) {
		this.iservicemail = iservicemail;
	}
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		//Register the DateFormat Property Editor		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		CustomDateEditor editor = new CustomDateEditor(df, false);
		binder.registerCustomEditor(Date.class, editor);
		
	}
	@RequestMapping(method = RequestMethod.POST)
    public String processSubmit(@ModelAttribute("mail") MailConfig mail, BindingResult result, SessionStatus status, ModelMap modelMap) {
		iservicemail.SaveOrUpdate(mail);
		return "emailing/gestion-emailing";
	}
	@RequestMapping(method=RequestMethod.GET)
	 public ModelAndView edit(@RequestParam("id")Integer id)
	  {
		 ModelAndView mav = new ModelAndView("emailing/editmail");
		 MailConfig mail = iservicemail.getMailByCode(id);
	 	 mav.addObject("mail", mail);
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
