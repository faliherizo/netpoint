/**
 * 
 */
package com.netPoint.applications.site.dto;
import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * @author Faliherizo
 *
 */
@XmlRootElement(name="notificationEmailDto")
@XmlAccessorType(XmlAccessType.FIELD)
public class NotificationEmailDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6945128479380973075L;
	@XmlElement(required=true)
	private String fromName;
	@XmlElement(required=true)
	private String fromAdresse;
	@XmlElement(name="addressListTos" ,required=true)
	private List<String> addressListTo;
	@XmlElement
	@XmlMimeType("text/xml")
	private String message;
	@XmlElement(required=true)
	private String subject;
	@XmlElement(required=true)
	private Integer numberRepeatedMessage= Integer.valueOf(0);
	@XmlElement(nillable=true,required=false)
	private String replyTo;
	@XmlElement(required=true)
	private boolean isHtmlMessage=true;
	@XmlElement(required=false)
	private String errorMessageNotSend;
	

	
	
	/**
	 * @return the numberRepeatedMessage
	 */
	public Integer getNumberRepeatedMessage() {
		return numberRepeatedMessage;
	}
	/**
	 * @param numberRepeatedMessage the numberRepeatedMessage to set
	 */
	public void setNumberRepeatedMessage(Integer numberRepeatedMessage) {
		this.numberRepeatedMessage = numberRepeatedMessage;
	}
	/**
	 * @return the fromName
	 */
	public String getFromName() {
		return fromName;
	}
	/**
	 * @param fromName the fromName to set
	 */
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	/**
	 * @return the fromAdresse
	 */
	public String getFromAdresse() {
		return fromAdresse;
	}
	/**
	 * @param fromAdresse the fromAdresse to set
	 */
	public void setFromAdresse(String fromAdresse) {
		this.fromAdresse = fromAdresse;
	}
	/**
	 * @return the addressListTo
	 */
	public List<String> getAddressListTo() {
		return addressListTo;
	}
	/**
	 * @param addressListTo the addressListTo to set
	 */
	public void setAddressListTo(List<String> addressListTo) {
		this.addressListTo = addressListTo;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @param replyTo the replyTo to set
	 */
	public void setReplyTo(String replyTo) {
		this.replyTo = replyTo;
	}
	/**
	 * @return the replyTo
	 */
	public String getReplyTo() {
		return replyTo;
	}
	/**
	 * @param isHtmlMessage the isHtmlMessage to set
	 */
	public void setHtmlMessage(boolean isHtmlMessage) {
		this.isHtmlMessage = isHtmlMessage;
	}
	/**
	 * @return the isHtmlMessage
	 */
	public boolean getIsHtmlMessage() {
		return isHtmlMessage;
	}
	/**
	 * @param errorMessageNotSend the errorMessageNotSend to set
	 */
	public void setErrorMessageNotSend(String errorMessageNotSend) {
		this.errorMessageNotSend = errorMessageNotSend;
	}
	/**
	 * @return the errorMessageNotSend
	 */
	public String getErrorMessageNotSend() {
		return errorMessageNotSend;
	}
	
	
	
	
}
