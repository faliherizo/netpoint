/**
 * 
 */
package com.netPoint.applications.site.dto;

import java.util.List;

import org.springframework.stereotype.Component;

/**
 * @author Faliherizo
 *
 */
@Component
public class NotificationEmailDtoFactoryImpl implements NotificationEmailDtoFactory {

  @Override
  public NotificationEmailDto getInstance() {
    return new NotificationEmailDto();
  }

  @Override
  public NotificationEmailDto getInstance(String fromName, String fromAddress, List<String> addressList, String message, String subject, boolean isHtmlMessage) {
    NotificationEmailDto dto = this.getInstance();
    dto.setAddressListTo(addressList);
    dto.setFromAdresse(fromAddress);
    dto.setFromName(fromName);
    dto.setReplyTo(fromAddress);
    dto.setHtmlMessage(isHtmlMessage);
    if (!message.startsWith("<")) {
      message = message.substring(1);
    }
    dto.setMessage(message);
    dto.setSubject(subject);
    return dto;
  }
  
}
