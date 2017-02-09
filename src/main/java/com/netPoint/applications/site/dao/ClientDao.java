/**
 * 
 */
package com.netPoint.applications.site.dao;

import java.util.List;

import com.netPoint.applications.site.model.Client;
import com.netPoint.applications.site.model.ClientRevendeur;

/**
 * @author Rolf
 *
 */
public interface ClientDao {

	List<ClientRevendeur> rechercheClient(List<String> aParam);
	/**
	 * List des clients
	 * @param aParam
	 * @return list client revendeur
	 */
	List<ClientRevendeur> rechercheClientPrinc(List<String> aParam);
}
