/**
 * 
 */
package com.netPoint.applications.site.service;

import java.util.List;

import com.netPoint.applications.site.model.Fournisseur;
import com.netPoint.applications.site.model.User;

/**
 * @author Faliherizo
 *
 */
public interface IServiceUser {
	List<Fournisseur> getListFournisseur();
	List<User> getListUserToSendNewsLettre();
	int getMaxValueGroup();

}
