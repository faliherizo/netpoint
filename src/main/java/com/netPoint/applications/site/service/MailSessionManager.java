/**
 * 
 */
package com.netPoint.applications.site.service;

import javax.mail.Session;
import javax.mail.Store;

/**
 * @author Faliherizo
 *
 */
public interface MailSessionManager {

	Session getSession();

	Store getStore();

	boolean connect();

	void disconnect();

	boolean isConnected();

}
