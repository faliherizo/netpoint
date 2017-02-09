/**
 * 
 */
package com.netPoint.applications.site.util;

import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;

import com.netPoint.applications.site.model.User;

/**
 * @author Faliherizo
 *
 */
public class Divers {
	/**
	 * 
	 * Génération auto de mot de passe
	 * @param user
	 * @return new password
	 */
	public static User generatePassword(User user)
	{
			String password = user.getPwd();
		    PasswordEncoder encoder = new Md5PasswordEncoder();
		    String result = encoder.encodePassword(password, null);
		    user.setPasswordNonEncoder(password);
			user.setPwd(result);
		return user;
	}
	  //Java code to generate 8 or 9 digit alphanumeric code
    public static String generateAuthCode() {
        //getting the current time in nanoseconds
        long decimalNumber=System.nanoTime();
        //To convert time stamp to alphanumeric code.
        //We need to convert base10(decimal) to base36
        String strBaseDigits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String strTempVal = "";
        int mod = 0;
        // String concat is costly, instead we could have use stringbuffer or stringbuilder
        //but here it wont make much difference.
        while(decimalNumber!= 0){
            mod=(int) (decimalNumber % 36);
            strTempVal=strBaseDigits.substring(mod,mod+1)+strTempVal;
            decimalNumber=decimalNumber/36;
        }
        return strTempVal;
 
    }
	

}
