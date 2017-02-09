/**
 * 
 */
package com.netPoint.applications.site.dto;

import java.util.List;


/**
 * @author Faliherizo
 *
 */
public interface NotificationEmailDtoFactory {
	 NotificationEmailDto getInstance();
	 NotificationEmailDto getInstance(String fromName, String fromAddress, List<String> addressList, String message, String subject, boolean isHtmlMessage);
}
