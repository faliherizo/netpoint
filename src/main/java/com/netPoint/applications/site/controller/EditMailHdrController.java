/**
 * 
 */
package com.netPoint.applications.site.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

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

import com.netPoint.applications.site.model.MailConfig;
import com.netPoint.applications.site.model.MailHdr;
import com.netPoint.applications.site.service.IServiceMailConfig;

/**
 * @author Faliherizo
 *
 */
@Controller
@RequestMapping(value = "/cron-mail")
public class EditMailHdrController extends SiteController {
    public static final Logger logger = LoggerFactory.getLogger(EditMailHdrController.class);
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
    public String processSubmit(@ModelAttribute("mailhdr") MailHdr mailhdr, BindingResult result, SessionStatus status, ModelMap modelMap) {
		try {
			iservicemail.Save(mailhdr);
		} catch (Exception e) {
			logger.error("save or update mail hdr failled", e);
		}
		return "emailing/gestion-emailing";
	}
	@RequestMapping(method=RequestMethod.GET)
	 public ModelAndView edit(@RequestParam("id")Integer id)
	  {
		ModelAndView mav = new ModelAndView("emailing/cron-mail");
		 Set<MailConfig> _listmail= new HashSet<MailConfig>();
	 	    // _listmail= iservicemail.ListMailPersonnalisable(int id);
	 	    MailHdr mailHdr= iservicemail.getMailHdrById(id);
	 	    //_listmail = _mail.getMaildtl();
	 	   mav.addObject("mailhdr", mailHdr);
	 	     return mav;
	  }
}
