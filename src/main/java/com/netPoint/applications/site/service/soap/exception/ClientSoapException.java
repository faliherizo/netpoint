/**
 * 
 */
package com.netPoint.applications.site.service.soap.exception;

/**
 * @author Faliherizo
 *
 */
public class ClientSoapException extends Exception{
	/**
	 * Constructor
	 */
	public ClientSoapException() {}

	/**
	 * Constructor.
	 * 
	 * @param message
	 */
	public ClientSoapException(String message) {
		super(message);
	}

	/**
	 * Constructor.
	 * 
	 * @param cause
	 */
	public ClientSoapException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor.
	 * 
	 * @param message
	 * @param cause
	 */
	public ClientSoapException(String message, Throwable cause) {
		super(message, cause);
	}
}
