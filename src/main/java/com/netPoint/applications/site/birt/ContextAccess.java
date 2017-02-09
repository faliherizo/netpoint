/**
 * 
 */
package com.netPoint.applications.site.birt;

import org.springframework.context.ApplicationContext;

/**
 * @author Faliherizo
 *
 */
public class ContextAccess {
	private static ApplicationContext ctx;
	public static void setApplicationContext(ApplicationContext applicationContext) { 
	ctx = applicationContext; 
	}
	public static ApplicationContext getApplicationContext() {
	return ctx;
	}
}
