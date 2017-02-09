/**
 * 
 */
package com.netPoint.applications.site.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.birt.report.engine.api.HTMLRenderOption;
import org.eclipse.birt.report.engine.api.HTMLServerImageHandler;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.netPoint.applications.site.birt.BirtEngine;
import com.netPoint.applications.site.model.EtatCommande;
import com.netPoint.applications.site.service.InterfaceService;

/**
 * @author Faliherizo
 *
 */

public class BirtController extends AbstractController {
	
	 public static final Logger logger = LoggerFactory.getLogger(BirtController.class);
	 @Autowired
	private InterfaceService interfaceService;
	 private static IReportEngine reportEngine;
	    @InitBinder
		protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
			//Register the DateFormat Property Editor		
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			CustomDateEditor editor = new CustomDateEditor(df, false);
			binder.registerCustomEditor(Date.class, editor);
			
		}
	
	
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {

			String reportName = request.getParameter("vente.rptdesign");
			ServletContext sc = request.getSession().getServletContext();
			reportEngine = BirtEngine.getBirtEngine(sc);
			IReportRunnable runnable = null;
			runnable = reportEngine.openReportDesign( sc.getRealPath("/Reports")+"/"+reportName );
			IRunAndRenderTask runAndRenderTask = reportEngine.createRunAndRenderTask(runnable);
		
			HTMLRenderOption options = new HTMLRenderOption();
			options.setOutputFormat("html");
			options.setOutputStream(response.getOutputStream());
			options.setImageHandler(new HTMLServerImageHandler());
			options.setBaseImageURL(request.getContextPath()+"/images");
			options.setImageDirectory(sc.getRealPath("/images"));
			runAndRenderTask.setRenderOption(options);
			runAndRenderTask.run();

	return null;
	}

}
