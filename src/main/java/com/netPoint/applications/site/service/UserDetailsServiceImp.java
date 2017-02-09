package com.netPoint.applications.site.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.stereotype.Service;
import com.netPoint.applications.site.dao.InterfaceDao;
import com.netPoint.applications.site.model.Profil;

public class UserDetailsServiceImp extends JdbcDaoImpl {

	@Autowired
	private InterfaceDao interfacedao;
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		//List<User> _listuser = interfacedao.findAllUserByMail(username);
		com.netPoint.applications.site.model.User user = interfacedao.FindUserByUserName(username) ;
		if (user == null)
			throw new UsernameNotFoundException("Invalid username.");
		@SuppressWarnings("unused")
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (Profil profil : interfacedao.findAllDroit()) {
			authorities.add(new GrantedAuthorityImpl(profil.getLibelle()));
		}
		  //lets get the information from user entity and create a SPRING user
	    //String username = user.getLogin();
	    String password = user.getPwd();
	    boolean enabled = user.getEtatCompte().getCodeetatcompte();
	    boolean accountNonExpired = true;
	    boolean credentialsNonExpired = true;
	    boolean accountNonLocked = true;
	    org.springframework.security.core.userdetails.User springUser;
	    springUser = new User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	    return springUser;
	}

}
