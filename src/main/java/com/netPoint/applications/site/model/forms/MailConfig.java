/**
 * 
 */
package com.netPoint.applications.site.model.forms;

/**
 * @author Faliherizo
 *
 */
public class MailConfig {
	private String mailFrom;
	private String phone;
	private boolean[] relance;
	/**
	 * @return the mailFrom
	 */
	public String getMailFrom() {
		return mailFrom;
	}
	/**
	 * @param mailFrom the mailFrom to set
	 */
	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the relance
	 */
	public boolean[] isRelance() {
		return relance;
	}
	/**
	 * @param relance the relance to set
	 */
	public void setRelance(boolean[] relance) {
		this.relance = relance;
	}
	

}
