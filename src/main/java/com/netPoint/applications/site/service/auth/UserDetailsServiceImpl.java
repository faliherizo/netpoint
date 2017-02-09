package com.netPoint.applications.site.service.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.eclipse.emf.ecore.util.Switch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;    
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import com.netPoint.applications.site.dao.InterfaceDao;
import com.netPoint.applications.site.util.Parameters;
//static.springsource.org/spring-security/site/docs/3.0.x/reference/springsecurity-single.html
@Service("userDetailsService") 
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private InterfaceDao usersDAO;

  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String username)
      throws UsernameNotFoundException, DataAccessException {

	//get the user by the login
	//because of the ambiguity between openschool User and Spring user I use the exact description
	com.netPoint.applications.site.model.User user = usersDAO.findUserByMail(username);
    
	//String presentedPassword = authentication.getCredentials().toString();
    if (user==null)
      throw new UsernameNotFoundException("user not found");
    //if()
   // throw new BadCredentialsException("Password incorrect");
    Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    for (com.netPoint.applications.site.model.Profil role : usersDAO.findAllDroit()) {
      authorities.add(new GrantedAuthorityImpl(role.getRole()));
    }
    
    //lets get the information from user entity and create a SPRING user
    //String username = user.getLogin();
    String password = user.getPwd();
    boolean enabled = user.getEtatCompte().getCodeetatcompte();
    Date _date = new Date();
    boolean accountNonExpired = true;
  //  boolean accountNonLocked = true;
    if(user.getDateFin().compareTo(_date)<0 && user.getProfil().getId()!=6)
    {
    	accountNonExpired = false;
    }
    boolean accountNonLocked =user.getEtatCompte().getCodeetatcompte();
    boolean credentialsNonExpired = true;
    org.springframework.security.core.userdetails.User springUser;
    springUser = new User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    Parameters.Role_User =  user.getProfil().getCode();
    
    return springUser;
  }
}
