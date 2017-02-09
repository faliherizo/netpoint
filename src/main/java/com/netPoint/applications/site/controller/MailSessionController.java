/**
 * 
 */
package com.netPoint.applications.site.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ClassUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.netPoint.applications.site.model.MailConfig;
import com.netPoint.applications.site.service.MailSessionManager;
import com.netPoint.applications.site.service.notify.Notifier;
import com.netPoint.applications.site.util.Parameters;
/**
 * @author Faliherizo
 *
 */
@Controller
public class MailSessionController {
	protected static final Logger logger = LoggerFactory.getLogger(MailSessionController.class);
    @Autowired
	protected MailSessionManager mailsessionmanager;
    @Autowired
	protected Notifier notifier;
    public void setMailsessionmanager(MailSessionManager mailsessionmanager) {
		this.mailsessionmanager = mailsessionmanager;
	}
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex) {
        logger.error("Exception occured", ex);
        ModelMap model = new ModelMap();
        model.addAttribute("class", ClassUtils.getShortName(ex.getClass()));
        model.addAttribute("message", ex.getMessage());
        return new ModelAndView("errors/error", model);
    }
	@RequestMapping(value = "/_ah/mail/{address}", method = RequestMethod.POST)
	public void createSwagItemFromIncomingEmail(@PathVariable("address")
	String address, HttpServletRequest request, HttpServletResponse response) {
		String fromEmail = null;
		try {
			// Create MimeMessage from request
			// From Appengine docs:
			// http://code.google.com/appengine/docs/java/mail/receiving.html
			Properties props = new Properties();
			Session session = Session.getDefaultInstance(props, null);
			MimeMessage mimeMessage = new MimeMessage(session, request
					.getInputStream());

			String mailSubject = mimeMessage.getSubject();

			fromEmail = ((InternetAddress) mimeMessage.getFrom()[0])
					.getAddress();
			logger.debug("Receieved message from " + fromEmail + " subject "
					+ mailSubject);

			// get body and attachment 
			// from http://jeremyblythe.blogspot.com/2009/12/gae-128-fixes-mail-but-not-jaxb.html
			Object content = mimeMessage.getContent();

			String bodyText = "";
			byte[] imageData = null;
			if (content instanceof String) {
				bodyText = (String) content;
			} else if (content instanceof Multipart) {
				Multipart multipart = (Multipart) content;
				Part part = multipart.getBodyPart(0);
				Object partContent = part.getContent();
				if (partContent instanceof String) {
					bodyText = (String) partContent;
				}
				// extract attached image if any
				//imageData = getMailAttachmentBytes(multipart);
			}

			// create item from mail
			/*SwagItem swagItem = new SwagItem();
			swagItem.setOwner(fromEmail);
			swagItem.setName(mailSubject);
			swagItem.setDescription(bodyText);
			swagItem.setImageBytes(imageData);
			itemService.save(swagItem);
			sendItemAddedSuccessfullyEmail(fromEmail, swagItem);*/
		} catch (Exception e) {
			/*log.error("Problem with incoming message from " + fromEmail, e);
			// report error to sender
			sendItemAddExceptionEmail(fromEmail, e);
			// report error to Sam
			sendItemAddExceptionEmailToAdmin(e);*/
		} finally {
			// this is for if this method is called from TaskQueues (it's not
			// right now)
			// always send status OK or Appengine Task Queues will keep retrying
			//response.setStatus(HttpServletResponse.SC_OK);
		}
	}
	/**
	 * From
	 * http://java.sun.com/developer/onlineTraining/JavaMail/contents.html#JavaMailMessage
	 * 
	 * @param attachmentInputStream
	 * @param mimeMultipart
	 * @return image data from attachment or null if there is none
	 * @throws MessagingException
	 * @throws IOException
	 */
	/*private byte[] getMailAttachmentBytes(Multipart mimeMultipart)
			throws MessagingException, IOException {
		InputStream attachmentInputStream = null;
		try {
			for (int i = 0, n = mimeMultipart.getCount(); i < n; i++) {
				String disposition = mimeMultipart.getBodyPart(i)
						.getDisposition();
				if (disposition == null) {
					continue;
				}
				if ((disposition.equals(Part.ATTACHMENT) || (disposition
						.equals(Part.INLINE)))) {
					attachmentInputStream = mimeMultipart.getBodyPart(i)
							.getInputStream();
					byte[] imageData = getImageDataFromInputStream(attachmentInputStream);
					return imageData;
				}
			}
		} finally {
			try {
				if (attachmentInputStream != null)
					attachmentInputStream.close();
			} catch (Exception e) {
			}
		}
		return null;
	}*/
	@RequestMapping(value = "/emailauto", method = RequestMethod.POST)
	public void emailautomatique(@ModelAttribute("mail") MailConfig mail,BindingResult result, SessionStatus status, ModelMap modelMap){
		
		
	}
	
	 @ModelAttribute("access_admin")
		public boolean get_access_admin() {
			Boolean access= false;
		    if(Parameters.Role_User.equals("SUP") || Parameters.Role_User.equals("ADM") || Parameters.Role_User.equals("GRS") || Parameters.Role_User.equals("REV"))
		    	access=true;
		   return access;
		}
	 @ModelAttribute("access_frns")
		public boolean get_access_frns() {
			Boolean access= false;
		    if( Parameters.Role_User.equals("GRS"))
		    	access=true;
		   return access;
		}
}
