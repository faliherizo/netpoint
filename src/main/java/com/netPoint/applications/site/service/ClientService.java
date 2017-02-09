/**
 * 
 */
package com.netPoint.applications.site.service;

import java.util.List;

import com.netPoint.applications.site.dto.ClientDto;

/**
 * @author Rolf
 *
 */

public interface ClientService {

	List<ClientDto> rechercherClientEnEssai(final List<String> aParam);
}
