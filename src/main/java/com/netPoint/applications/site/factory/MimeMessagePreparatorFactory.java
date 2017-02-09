/**
 * 
 */
package com.netPoint.applications.site.factory;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import com.netPoint.applications.site.dto.NotificationEmailDto;

/**
 * @author Faliherizo
 *
 */
@Component
public interface MimeMessagePreparatorFactory {
	
	void prepare(MimeMessage mimeMessage);
	MimeMessagePreparator getInstance(final String fromName, final String fromAddress, final String to, final String messages, final String subject,
		      final boolean isHtmlText);
	MimeMessagePreparator getInstance(final String fromName, final String fromAddress, final String[] to, final String messages, final String subject,
		      final String replyToAdress, final boolean isHtmlText);
	MimeMessagePreparator getInstance(final String fromName, final String fromAddress, final String[] to, final String subject, final String messages);
	MimeMessagePreparator getInstance(final String fromName, final String fromAddress, final String to, final String subject, final String messages);
	MimeMessagePreparator[] getInstance(NotificationEmailDto notificationEmailDto);
	MimeMessagePreparator getInstance(String fromName, String fromAdresse,
			String[] array, String encode, String subject, boolean isHtmlText);
}
