package com.netPoint.applications.site.dao;

import java.util.List;

import com.netPoint.applications.site.model.Client;
import com.netPoint.applications.site.model.Commande;
import com.netPoint.applications.site.model.Fournisseur;
import com.netPoint.applications.site.model.Revendeur;
import com.netPoint.applications.site.model.User;

public interface InterfaceUserDao {

	User findByLogin(final String login);

	List<User> getListUserById(List<Integer> listId);

	List<User> getListFrnsByUserConnect(User user);
	
	List<Fournisseur> getListFournisseur();
	
	List<User> getListUserToSendNewsLettre();

	List<Revendeur> getListRevendeur();
	int getMaxValueGroup();

	List<Commande> findAllCommande(Client client);
}
