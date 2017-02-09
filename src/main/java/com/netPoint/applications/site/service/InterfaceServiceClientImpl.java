/**
 * 
 */
package com.netPoint.applications.site.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netPoint.applications.site.dao.InterfaceDaoClient;
import com.netPoint.applications.site.model.Client;
import com.netPoint.applications.site.model.ClientRevendeur;
import com.netPoint.applications.site.model.ClientRevendeurId;
import com.netPoint.applications.site.model.Commande;
import com.netPoint.applications.site.model.Revendeur;

/**
 * @author Faliherizo
 *
 */
@Service
public class InterfaceServiceClientImpl implements InterfaceServiceClient {

	@Autowired
	private InterfaceDaoClient interfaceDaoClient;
	/**
	 * @param interfaceDaoClient the interfaceDaoClient to set
	 */
	public void setInterfaceDaoClient(InterfaceDaoClient interfaceDaoClient) {
		this.interfaceDaoClient = interfaceDaoClient;
	}

	@Override
	public List<Client> findAllClient() {
		
		return interfaceDaoClient.findAllClient();
	}

	@Override
	public List<Client> findAllClientBy(Integer IdSociete, Integer IdEtat,
			String mail, String siret, String rcs, String numero,
			Integer IdRevendeur, String NumClientRev, String customerkey,
			String nom, String prenoms, String telephone) {
		
		return interfaceDaoClient.findAllClientBy(IdSociete, IdEtat, mail, siret, rcs, numero, IdRevendeur, NumClientRev, customerkey, nom, prenoms, telephone);
	}

	@Override
	public ClientRevendeur saveOrUpdateClientRev(
			ClientRevendeur clientRevendeur, Revendeur revendeur) {
		return interfaceDaoClient.saveOrUpdateClientRev(clientRevendeur, revendeur);
	}

	@Override
	public Commande getClientRevById(int id) {
		
		return interfaceDaoClient.getClientRevById(id);
	}

}
