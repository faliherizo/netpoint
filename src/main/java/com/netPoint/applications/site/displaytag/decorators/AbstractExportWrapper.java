package com.netPoint.applications.site.displaytag.decorators;

public abstract class AbstractExportWrapper extends TotalTemplateWrapper {

	@Override
	public void writeToExporter(String[] rowValues) {
		// TODO Auto-generated method stub
		doWriteToExporter(rowValues);
	}
	
	public abstract void doWriteToExporter(String[] rowValues);
}
