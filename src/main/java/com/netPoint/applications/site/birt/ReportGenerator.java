/**
 * 
 */
package com.netPoint.applications.site.birt;

import java.io.OutputStream;
import java.util.Map;

import org.eclipse.birt.report.engine.api.HTMLRenderOption;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;
import org.springframework.core.io.ClassPathResource;

/**
 * @author Faliherizo
 *
 */
public class ReportGenerator {
	private IReportEngine reportEngine;

	  /** {@link Map} containing the BIRT render options. */
	private Map renderOptions;
	
	public void generateReport(OutputStream os, String format, String reportDesign) throws Exception {
		//String format = RequestUtils.getStringParameter(request, "format", "html");
		
	    IReportRunnable runnable = reportEngine.openReportDesign(new ClassPathResource(reportDesign).getInputStream());
	    
	    IRunAndRenderTask runAndRenderTask = reportEngine.createRunAndRenderTask(runnable);

	    // Although it's called HtmlRenderOption, it's used for all formats
	    HTMLRenderOption htmlRenderOption = new HTMLRenderOption();
	    htmlRenderOption.setOutputFormat(format);
	    htmlRenderOption.setEmbeddable(true);
	    htmlRenderOption.setOutputStream(os);
	    
	    runAndRenderTask.setAppContext(renderOptions);
	    runAndRenderTask.setRenderOption(htmlRenderOption);
	    /*runAndRenderTask.setParameterValue("driverUrl", "jdbc:oracle:thin:@localhost:1521:VMP");
	    runAndRenderTask.setParameterValue("userName", "vmpuser");
	    runAndRenderTask.setParameterValue("password", "password");*/
	    runAndRenderTask.run();
	    runAndRenderTask.close();
	}

	public void setReportEngine(IReportEngine reportEngine) {
		this.reportEngine = reportEngine;
	}

	public void setRenderOptions(Map renderOptions) {
		this.renderOptions = renderOptions;
	}

}
