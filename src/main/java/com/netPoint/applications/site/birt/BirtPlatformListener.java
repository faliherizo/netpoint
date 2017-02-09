/**
 * 
 */
package com.netPoint.applications.site.birt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.core.framework.PlatformConfig;
import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.HTMLRenderOption;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportEngineFactory;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Faliherizo
 *
 */
public class BirtPlatformListener {
	private EngineConfig engineConfig;
	private IReportEngine reportEngine;
	private IReportEngine engine;
	public void start() {
		  try {
		    Platform.startup(engineConfig);
		  } catch (BirtException be) {
		    throw new IllegalArgumentException("Failure starting BIRT platform", be);
		  }
	}
		 
	public void shutdown() {
		  if (reportEngine != null) {
		    reportEngine.shutdown();
		  }
		 
		  // Just call shutdown
		  Platform.shutdown();
	}
		 
	public IReportEngine getReportEngine() {
		  if (reportEngine == null) {
		    IReportEngineFactory factory = (IReportEngineFactory)
		    Platform.createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
		 
		    reportEngine = factory.createReportEngine(engineConfig);
		  }
		 
		  return reportEngine;
	}
	 
}
