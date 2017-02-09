/**
 * 
 */
package com.netPoint.applications.site.dao;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

import com.netPoint.applications.site.model.Profil;


/**
 * @author Faliherizo
 *
 */
public class AuthenticationJdbcDaoImpl extends JdbcDaoImpl {
	
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
		User userDetails = new User(username, user.getPwd(),
				user.getEtatCompte().getCodeetatcompte(), user.getEtatCompte().getCodeetatcompte(), user.getEtatCompte().getCodeetatcompte(),
				user.getEtatCompte().getCodeetatcompte(), authorities);
		return null;
	}

}
