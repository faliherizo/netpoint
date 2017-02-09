/*
 * Created on 19 juil. 2011
 */
package com.netPoint.applications.site.util;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Class Which give you log object.
 */
public class Log {

	public static final String DELIMITER = " � ";
	/**
	 * @param o Object to log.
	 * @param level
	 * @return Logger
	 */
	public static Logger getLog(Object o, Level level) {
		//PropertyConfigurator.configure("log4j.properties");
		Logger log = Logger.getLogger(o.getClass());
		log.setLevel(level);
		return log;
	}

	/**
	 * Set WARN level by default.
	 * @param o
	 * @return Logger
	 */
	public static Logger getLog(Object o) {
		return getLog(o, Level.DEBUG);
	}
}
