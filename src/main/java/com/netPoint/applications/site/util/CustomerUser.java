/**
 * 
 */
package com.netPoint.applications.site.util;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * @author Faliherizo
 *
 */
public class CustomerUser extends User {
	
	public CustomerUser(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
		// TODO Auto-generated constructor stub
	}
	
	private Object userInfo;
   
    public Object getUserInfo() {
        return userInfo;
    }
    
    public void setUserInfo(Object userInfo) {
        this.userInfo = userInfo;
    }

}
