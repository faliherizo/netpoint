/**
 * 
 */
package com.netPoint.applications.site.service.soap.endpoint;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.netPoint.applications.site.controller.UserController;
import com.netPoint.applications.site.model.Client;
import com.netPoint.applications.site.service.InterfaceService;
import com.netPoint.applications.site.service.soap.endpoint.GetOrders.GetOrdersRequest;
import com.netPoint.applications.site.service.soap.endpoint.GetOrders.GetOrdersResponse;
import com.netPoint.applications.site.service.soap.model.Customer;
import com.netPoint.applications.site.service.soap.model.ObjectFactory;


/**
 * @author Faliherizo
 *
 */
@Endpoint  
public class OrderServiceEndpoints {
	public static final Logger logger = LoggerFactory.getLogger(OrderServiceEndpoints.class);   
	private static final String GET_TARGET_NAMESPACE = "http://com/netPoint/GetOrders";
	//private static final String SAVE_TARGET_NAMESPACE = "http://com/javaguys/webservices/saveUserServices";
	private final ObjectFactory JAXB_OBJECT_FACTORY = new ObjectFactory();
	
	@Autowired
	private InterfaceService interfaceService;

	/**
	 * @param interfaceService the interfaceService to set
	 */
	public void setInterfaceService(InterfaceService interfaceService) {
		this.interfaceService = interfaceService;
	}
	@PayloadRoot(localPart ="GetOrdersRequest", namespace = GET_TARGET_NAMESPACE)    
    public @ResponsePayload GetOrdersResponse getOrderAllByClient(@RequestPayload GetOrdersRequest getOrdersRequest)    
    {    
		try {
			 System.out.println("Get Orders !");  
			  GetOrdersResponse  response = new GetOrdersResponse (); 
			  Client client = interfaceService.findClientByName(getOrdersRequest.getCodeClient());
			  response.setCustomer(copyClient(client));
			  response.setOrders(interfaceService.getOrderList(client));
			  return response; 
		} catch (Exception e) {
			logger.error("Error GetOrdersRequest", e);
			return null;
		}
		   
    }  
	 @PayloadRoot(localPart = "placeOrderRequest", namespace = GET_TARGET_NAMESPACE)
	 public GetOrdersResponse getOrder(GetOrdersRequest placeOrderRequest) {
		// GetOrdersResponse response = JAXB_OBJECT_FACTORY.createGetOrderResponse();
	      /*response.setRefNumber(orderService.placeOrder(placeOrderRequest
	                .getOrder()));

	        return new JAXBElement<PlaceOrderResponse>(new QName(
	                "http://www.liverestaurant.com/OrderService/schema",
	                "placeOrderResponse"), PlaceOrderResponse.class, response);
	               } */
	 		return null;
	    }

	/***
	 * retourne Header client
	 * @param client
	 * @return
	 */
	private Customer copyClient(Client client){
		Customer customer = new Customer();
		customer.setAdress(client.getSociete().getAdresse1());
		customer.setCodeclient(client.getCodeClient());
		customer.setEmail(client.getUser().getLogin());
		customer.setLangue(client.getUser().getLangue().getLibelle());
		customer.setLastname(client.getUser().getPrenom());
		customer.setName(client.getUser().getNom());
		customer.setPays(client.getSociete().getPays().getLibelle());
		customer.setRcs(client.getRcs());
		customer.setSiret(client.getSiret());
		customer.setSociete(client.getSociete().getNom());
		customer.setTelephone(client.getUser().getTelephone());
		return customer;
	}

}
