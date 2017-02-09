/**
 * 
 
package com.netPoint.applications.site.service.soap.security;

import org.apache.ws.security.WSSecurityException;
import org.apache.ws.security.handler.RequestData;
import org.apache.ws.security.message.token.UsernameToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Faliherizo
 *
 */
/*
public class UsernameTokenValidator extends org.apache.ws.security.validate.UsernameTokenValidator {
	private final AuthenticationManager authenticationManager;
	 
	  public UsernameTokenValidator(AuthenticationManager authenticationManager) {
	    this.authenticationManager = authenticationManager;
	  }
	 
	  @Override
	  protected void verifyPlaintextPassword(UsernameToken usernameToken,
	          RequestData data) throws WSSecurityException {
	    String user = usernameToken.getName();
	    String password = usernameToken.getPassword();
	    Authentication authentication =
	            new UsernamePasswordAuthenticationToken(user, password);
	    authentication = authenticationManager.authenticate(authentication);
	    if (!authentication.isAuthenticated()) {
	      throw new WSSecurityException(
	              WSSecurityException.FAILED_AUTHENTICATION);
	    }
	 
	    SecurityContextHolder.getContext().setAuthentication(authentication);
	    }
}
*/