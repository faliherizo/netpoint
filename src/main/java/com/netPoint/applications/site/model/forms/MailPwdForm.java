/**
 * 
 */
package com.netPoint.applications.site.model.forms;

/**
 * @author Faliherizo
 *
 */
public class MailPwdForm {
	private String mail;
	private String password;
	private String password2;
	private String messageerror;
	/**
	 * @return the messageerror
	 */
	public String getMessageerror() {
		return messageerror;
	}

	/**
	 * @param messageerror the messageerror to set
	 */
	public void setMessageerror(String messageerror) {
		this.messageerror = messageerror;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

}
