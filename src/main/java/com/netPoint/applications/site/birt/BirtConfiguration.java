/**
 * 
 */
package com.netPoint.applications.site.birt;

import java.util.logging.Level;

import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.HTMLActionHandler;
import org.eclipse.birt.report.engine.api.HTMLEmitterConfig;
import org.eclipse.birt.report.engine.api.HTMLServerImageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netPoint.applications.site.controller.SiteController;

/**
 * @author Faliherizo
 *
 */
public class BirtConfiguration {
	protected static final Logger log = LoggerFactory.getLogger(BirtConfiguration.class);
	protected EngineConfig engineConfig;
	private String birtRuntimeLocation;
	private Level logLevel;
	private String logLocation;
	public EngineConfig getEngineConfig() {
	    
		if (engineConfig == null) {
	      if (log.isDebugEnabled()) {
	        log.debug("Creating new instance of EngineConfig");
	      }
	 
	      engineConfig = new EngineConfig();
	      engineConfig.setEngineHome(birtRuntimeLocation);
	      
		engineConfig.setLogConfig(logLocation, logLevel);
	 
	      HTMLEmitterConfig htmlConfig = new HTMLEmitterConfig();
	      htmlConfig.setActionHandler(new HTMLActionHandler());
	       
	      // This allows images to be referenced by url
	      htmlConfig.setImageHandler(new HTMLServerImageHandler());
	 
	      engineConfig.getEmitterConfigs().put("html", htmlConfig);
	    }
	 
	    return engineConfig;
	  }
}
