/**
 * 
 */
package com.netPoint.applications.site.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netPoint.applications.site.dao.InterfaceUserDao;
import com.netPoint.applications.site.model.Fournisseur;
import com.netPoint.applications.site.model.User;

/**
 * @author Faliherizo
 *
 */
@Service
public class ServiceUserImpl implements IServiceUser {
	public static final Logger logger = LoggerFactory.getLogger(ServiceUserImpl.class);
	@Autowired
	 private InterfaceUserDao interfaceUserDao;
	//
	public List<Fournisseur> getListFournisseur() {
		
		return interfaceUserDao.getListFournisseur();
	}
	@Override
	public List<User> getListUserToSendNewsLettre() {
		
		return interfaceUserDao.getListUserToSendNewsLettre();
	}
	@Override
	public int getMaxValueGroup() {
		
		return interfaceUserDao.getMaxValueGroup();
	}

}
