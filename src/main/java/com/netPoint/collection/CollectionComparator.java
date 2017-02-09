package com.netPoint.collection;

import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.Date;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.log4j.Logger;

@SuppressWarnings("unchecked")
public class CollectionComparator implements Comparator {
	private static final Logger logger = Logger.getLogger(CollectionComparator.class
			.getName());
	
	private String orderBy;
	private String ordre;

	public CollectionComparator(String orderBy) {
		this.orderBy = orderBy.split(" ")[0];
		this.ordre = orderBy.split(" ")[1];
	}

	public int compare(Object arg0, Object arg1) {
		Object property1;
		Object property2;
		
		// R�cup�ration de la propri�r� sur l'objet 1 en appelant la m�thode getter appropri�e
		property1 = getProperty(arg0, orderBy);
		// R�cup�ration de la propri�r� sur l'objet 2 en appelant la m�thode getter appropri�e
		property2 = getProperty(arg1, orderBy);
		
		if (property1 != null && property2 == null) {
			int returnTri = 1;
			if(ordre.equalsIgnoreCase("DESC"))
				returnTri = returnTri * (-1);
			
			return returnTri;
		}
		
		if (property1 == null && property2 != null) {
			int returnTri = -1;
			if(ordre.equalsIgnoreCase("DESC"))
				returnTri = returnTri * (-1);
			
			return returnTri;
		}

		if(property1 instanceof String && property2 instanceof String) {
			String value1 = (String) property1;
			String value2 = (String) property2;
			int returnTri = value1.compareToIgnoreCase(value2);
			if(ordre.equalsIgnoreCase("DESC"))
				returnTri = returnTri * (-1);
				
			return returnTri;
		} else if (property1 instanceof Date && property2 instanceof Date) {
			Date value1 = (Date) property1;
			Date value2 = (Date) property2;
			
			int returnTri = value1.compareTo(value2);
			if(ordre.equalsIgnoreCase("DESC"))
				returnTri = returnTri * (-1);

			return returnTri;
		}else if (property1 instanceof Double && property2 instanceof Double) {
			Double value1 = (Double) property1;
			Double value2 = (Double) property2;
			
			int returnTri = value1.compareTo(value2);
			if(ordre.equalsIgnoreCase("DESC"))
				returnTri = returnTri * (-1);

			return returnTri;
		} else if (property1 instanceof Integer && property2 instanceof Integer) {
			Integer value1 = (Integer) property1;
			Integer value2 = (Integer) property2;
			
			int returnTri = value1.compareTo(value2);
			if(ordre.equalsIgnoreCase("DESC"))
				returnTri = returnTri * (-1);

			return returnTri;
		}else if (property1 instanceof Boolean && property2 instanceof Boolean) {
			Boolean value1 = (Boolean) property1;
			Boolean value2 = (Boolean) property2;
			
			int returnTri = value1.compareTo(value2);
			if(ordre.equalsIgnoreCase("DESC"))
				returnTri = returnTri * (-1);

			return returnTri;
		}

		return 0;
	}
	
	/**
	 * getProperty(Object object, String property)
	 * return Object
	 */
	private Object getProperty(Object object, String property) {
		Object returnValue = null;
		StringBuffer realProperty = null;
		
		realProperty = new StringBuffer("get");
		realProperty = realProperty.append(property.substring(0,1).toUpperCase() + property.substring(1));
		
		try {
			
			returnValue = MethodUtils.invokeExactMethod(object, realProperty.toString(), null);
			
		} catch (NoSuchMethodException e) {
			
			realProperty = new StringBuffer("is");
			realProperty = realProperty.append(property.substring(0,1).toUpperCase() + property.substring(1));
			
			try {
				returnValue = MethodUtils.invokeExactMethod(object, realProperty.toString(), null);
			} catch (NoSuchMethodException e2) {
				if (logger.isDebugEnabled()) {
					logger.error("NoSuchMethodException : " + e2);
				}
			} catch (IllegalAccessException e2) {
				if (logger.isDebugEnabled()) {
					logger.error("IllegalAccessException : " + e2);
				}
			} catch (InvocationTargetException e2) {
				if (logger.isDebugEnabled()) {
					logger.error("InvocationTargetException : " + e2);
				}
			}
			
		} catch (IllegalAccessException e) {
			if (logger.isDebugEnabled()) {
				logger.error("IllegalAccessException : " + e);
			}
		} catch (InvocationTargetException e) {
			if (logger.isDebugEnabled()) {
				logger.error("InvocationTargetException : " + e);
			}
		}
		
		return returnValue;
	}
	
}
