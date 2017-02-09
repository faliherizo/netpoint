/**
 * 
 */
package com.netPoint.applications.site.service.notify;

import java.util.List;

import javax.mail.MessagingException;

import com.netPoint.applications.site.model.User;

/**
 * @author Faliherizo
 *
 */
public interface Notifier {
	public void notify(User user);
	
	public void sendNewPassword(User user);
	
	public void sendUrlToChangeOrPwd(User user) throws MessagingException;
	
	public void gestionMailNotify(String to, String send, String bodyMessage, String footerMessage, String subject) throws MessagingException;

	public void sendMail(List<User> userListInformation);

	public void notifyRecupPwd(User user) throws MessagingException;

}
