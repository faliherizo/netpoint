/**
 * 
 */
package com.netPoint.applications.site.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Faliherizo
 *
 */
public class VerificationMail {
	public static boolean isValidEmailAddress(String emailAddress){  
		   String  expression="^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";  
		   CharSequence inputStr = emailAddress;
		   Pattern pattern = Pattern.compile(expression,Pattern.CASE_INSENSITIVE);  
		   Matcher matcher = pattern.matcher(inputStr);  
		   return matcher.matches();
	} 
}
