/**
 * 
 */
package com.netPoint.applications.site.service;

import java.util.List;
import java.util.Map;

import com.netPoint.applications.site.dto.NotificationEmailDto;

/**
 * @author Faliherizo
 *
 */
public interface EnvoyerNotificationSA {
	boolean envoyerNotificationMailBatch(String fromName, String fromAdresse, List<String> addressList, String urlTemplate, Map model, String subject);
	boolean envoyerNotificationMail(String fromName, String fromAdresse, List<String> addressList, String urlTemplate, Map model, String subject);
	boolean envoyerNotificationMailBatch(String fromName, String fromAdresse, List<String> addressList, String message, String subject);
	boolean envoyerNotificationMail(String fromName, String fromAdresse, List<String> addressList, String message, String subject);
	
	void envoyerNotificationAsynchroneToBeEmailed(NotificationEmailDto notificationEmailDto);
	void envoyerNotificationAsynchroneToBeEmailed(String fromName, String fromAddress, List<String> addressList, String urlTemplate, Map model,
			String subject);
	void envoyerNotificationDelayedAsynchroneToBeEmailed(NotificationEmailDto notificationEmailDto);
	void envoyerNotificationBatch(NotificationEmailDto notificationEmailDto) ;
	void envoyerNotification(NotificationEmailDto notificationEmailDto);
	boolean envoyerNotificationMailBatch(NotificationEmailDto notificationEmailDto);
	NotificationEmailDto prepareNotificationEmamilDto(String fromName, String fromAddress, List<String> addressList, String urlTemplate, Map model,
			String subject);
	boolean envoyerNotification(String toAddress, String message);
	
}
