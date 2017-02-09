/**
 * 
 */
package com.netPoint.applications.site.service.propertyeditor;

import java.beans.PropertyEditorSupport;

/**
 * @author Faliherizo
 *
 */
public class CustomIntEditor extends PropertyEditorSupport{
	/**
	 * Constructor
	 */
	public CustomIntEditor() {
		super();
	}

	/* (non-Javadoc)
	 * @see java.beans.PropertyEditorSupport#getAsText()
	 */
	@Override
	public String getAsText() {
		Integer i = (Integer) getValue();
		return i.toString();
	}

	/* (non-Javadoc)
	 * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
	 */
	@Override
	public void setAsText(String arg0) throws IllegalArgumentException {
		if (arg0 == "" || arg0==null) {
			setValue(0);
		}
		else
			setValue( Integer.parseInt(arg0) );
	}
}
