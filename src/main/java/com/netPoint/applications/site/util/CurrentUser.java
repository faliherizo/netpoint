/**
 * 
 */
package com.netPoint.applications.site.util;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Faliherizo
 *
 */
public class CurrentUser {
	
	public CustomerUser getUser() {
        CustomerUser user = (CustomerUser) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        if(user != null)
            return user;
        else
            return null;
    }

}
