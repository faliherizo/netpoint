package com.netPoint.applications.site.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.netPoint.applications.site.dao.ClientDao;
import com.netPoint.applications.site.dto.ClientDto;
import com.netPoint.applications.site.model.ClientRevendeur;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientServiceImpl implements ClientService {
	@Autowired
	private ClientDao clientDao;
	
	public List<ClientDto> rechercherClientEnEssai(final List<String> aParam) {
		List<ClientRevendeur>  clients =clientDao.rechercheClient(aParam);
		List<ClientDto> clientDtos = new ArrayList<ClientDto>();
		for (ClientRevendeur item : clients) {
			ClientDto clientDto = new ClientDto(item.getClient().getUser().getNom(), item.getClient().getCodeClient(), item.getNumeroClientRevendeur(), item.getClient().getUser().getEtatCompte().getLibelle(), item.getClient().getUser().getNom()+" "+item.getClient().getUser().getPrenom(), item.getRevendeur().getCodeRevendeur());
			clientDtos.add(clientDto);
		}
		return clientDtos;
	}

	
}
