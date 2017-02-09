package com.netPoint.applications.site.dao;

import java.util.ArrayList;
import java.util.List;

import com.netPoint.applications.site.model.Client;
import com.netPoint.applications.site.model.ClientRevendeur;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.netPoint.applications.site.dto.view.ViewRenouvellementDuMois;
import com.netPoint.applications.site.model.Revendeur;


@Repository
public class ClientDaoImp implements ClientDao {

	private EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClientRevendeur> rechercheClient(List<String> aParam) {
		String jPql ="SELECT clrdv FROM ClientRevendeur clrdv ";
		List<ClientRevendeur> clients = new ArrayList<ClientRevendeur>();
		try{
			String queryString = jPql+getWhereClause(aParam);
			Query query  = null;
			query= entityManager.createQuery(queryString);
			clients = query.getResultList();
		}
		catch(Exception exception){
			exception.getMessage();
		}
		return clients;
	}
  
	private String getWhereClause(final List<String> aParam){
		String where ="";
		//String ejbql = "SELECT i FROM Item i WHERE i.name LIKE '\\_%' ESCAPE '\\'";
		//String test = "SELECT i FROM Item i WHERE i.name LIKE '\\\\_%' ESCAPE '\\\\'";
		if(aParam!=null && aParam.size()>0){
		Integer[] keys = {0,1,2,3,4,5};
			for (Integer item : keys) {
				switch (item) {
				   case 0: //Societe
					if(!aParam.get(item).trim().isEmpty()){
						where+=where.trim().isEmpty() ? "WHERE " : "AND ";
						where+="clrdv.client.societe.nom LIKE '%"+aParam.get(item)+"%' ";
					}
				   break;
				   case 1: //Email
					 if(!aParam.get(item).trim().isEmpty()){
						 where+=where.trim().isEmpty() ? "WHERE " : "AND "; 
						 where+="clrdv.client.user.login = '"+aParam.get(item)+"' ";
					 }
				   break;
				   case 2: //Telephone
						if(!aParam.get(item).trim().isEmpty()){
							where+=where.trim().isEmpty() ? "WHERE " : "AND "; 
							where+="clrdv.client.user.telephone = '"+aParam.get(item)+"' ";
						}
				   break;
				   case 3: //NumClientRevendeur
						if(!aParam.get(item).trim().isEmpty()){
							where+=where.trim().isEmpty() ? "WHERE " : "AND "; 
							//where+="cl.id = (SELECT clRdv.client.id FROM ClientRevendeur clRdv WHERE clRdv.numeroClientRevendeur = "+aParam.get(item)+") ";
							where+= "clRdv.numeroClientRevendeur = '"+aParam.get(item)+"' ";
						}
				   break;
				   case 4: //NumClient
						if(!aParam.get(item).trim().isEmpty()){
							where+=where.trim().isEmpty() ? "WHERE " : "AND "; 
							where+="clRdv.client.codeClient = '"+aParam.get(item)+"' ";
						}
				   break;
				   case 5: //Contact
						if(!aParam.get(item).trim().isEmpty()){
							where+=where.trim().isEmpty() ? "WHERE " : "AND "; 
							where+="clrdv.client.user.nom LIKE '%"+aParam.get(item)+"%' OR clrdv.client.user.prenom LIKE '%"+aParam.get(item)+ "%' ";
						}
				   break;
				default:
					break;
				}
			}
		}
		return where;
	}

	@Override
	public List<ClientRevendeur> rechercheClientPrinc(List<String> aParam) {
		String jPql ="SELECT clrdv FROM ClientRevendeur clrdv ";
		List<ClientRevendeur> clients = new ArrayList<ClientRevendeur>();
		try{
			String queryString = jPql+getWhereClause(aParam);
			Query query  = null;
			query= entityManager.createQuery(queryString);
			clients = query.getResultList();
		}
		catch(Exception exception){
			exception.getMessage();
		}
		return clients;
	}
	
	
}
