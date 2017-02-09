/**
 * 
 */
package com.netPoint.applications.site.service.notify;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.apache.velocity.app.VelocityEngine;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.quartz.QuartzJobBean;
//import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.core.codec.Base64;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.netPoint.applications.site.model.User;

/**
 * @author Faliherizo
 *
 */
@Service("mailService")
public class EmailNotifier implements Notifier  {
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private SimpleMailMessage templateMessage;
	
	private VelocityEngine velocityEngine;
	
	
	protected String url;
	//private JavaMailSenderImpl  d= 
	

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	@Value("#{cmv_mail['cmv.host.url']}")
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @param mailSender the mailSender to set
	 */
	
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	/**
	 * @param templateMessage the templateMessage to set
	 */
	
	public void setTemplateMessage(SimpleMailMessage templateMessage) {
		this.templateMessage = templateMessage;
	}


	@Override
	public void notify(User user) {
		/*Create a thread safe copy of the template message and customyze it*/
		
		SimpleMailMessage msg=  new SimpleMailMessage(this.templateMessage);
		msg.setTo(user.getLogin());
		msg.setText("Bienvenue sur Portail net point ! Votre compte a été créé avec succès. Login : "+user.getLogin()+" / Mot de passe : "+user.getPasswordNonEncoder());
		try {
			this.mailSender.send(msg);
		} catch (MailException e) {
			System.err.println(e.getMessage());
		}
	}
	@Override
	public void sendNewPassword(User user) {
		SimpleMailMessage msg=  new SimpleMailMessage(this.templateMessage);
		msg.setTo(user.getLogin());
		msg.setText("Suite à votre demande, voici votre nouveau mot de passe pour vous connecter : "+user.getPasswordNonEncoder());
		try {
			this.mailSender.send(msg);
		} catch (MailException e) {
			System.err.println(e.getMessage());
		}
		
	}
	 public  byte[] encode(byte[] b) throws Exception {
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        OutputStream b64os = MimeUtility.encode(baos, "base64");
	        b64os.write(b);
	        b64os.close();
	        return baos.toByteArray();
	 } 
	  public static byte[] decode(byte[] b) throws Exception {
	        ByteArrayInputStream bais = new ByteArrayInputStream(b);
	        InputStream b64is = MimeUtility.decode(bais, "base64");
	        byte[] tmp = new byte[b.length];
	        int n = b64is.read(tmp);
	        byte[] res = new byte[n];
	        System.arraycopy(tmp, 0, res, 0, n);
	        return res;
	 }
	@Override
	public void sendUrlToChangeOrPwd(User user) throws MessagingException {
		//JavaMailSenderImpl sender = new JavaMailSenderImpl();
		
		MimeMessage message = mailSender.createMimeMessage();
		// use the true flag to indicate you need a multipart message
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo(user.getLogin());
		String _url = url +"/netpoint/changepassword/";
		String url_encode ="";
		try {
			byte[] login_encode =Base64.encode(user.getLogin().getBytes());
			url_encode= _url + new String(login_encode);	
			// use the true flag to indicate the text included is HTML
			helper.setText(url_encode);
			mailSender.send(message);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public void gestionMailNotify(String to, String send, String bodyMessage,
			String footerMessage, String subject) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
				
		try {
			
			// use the true flag to indicate the text included is HTML
			helper.setText(bodyMessage + footerMessage);
			
			helper.setSubject(subject);
            helper.setTo(to);
            helper.setFrom(send);
            //helper.setSentDate(subscriber.getDateCreated());
            helper.setText(bodyMessage + footerMessage, true);
			mailSender.send(message);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	    private String defaultFrom;
	 	   private static final String[] MONTHS = { "January", "February", "March",
	 	                 "April", "May", "June", "July", "August", "September", "October",
	 	                 "November", "December" };
	 	 
	   /**
	 	   * Iterates over userList and sends e-mail message to each user based on
	 	   * Velocity templates.
	 	   *
	    * @param userList
	 	   */
	 	  @Override
	    public void sendMail(List<User> userList) {
	      final Calendar calendar = Calendar.getInstance();
	      calendar.setTime(new Date());
	     List<MimeMessagePreparator> preparatorList = new
	                                 ArrayList<MimeMessagePreparator>();
	     for (final User user : userList) {
	 	       MimeMessagePreparator preparator = new MimeMessagePreparator() {
	        public void prepare(MimeMessage mimeMessage) throws Exception {
	          MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
	 	         message.setTo(user.getLogin());
	          message.setFrom(defaultFrom);
	          Map model = new HashMap();
	          model.put("user", user);
	          model.put("month", getMonth(calendar.get(Calendar.MONTH)));
	          String text = VelocityEngineUtils.mergeTemplateIntoString(
	                 velocityEngine, "reminderMail.vm", model);
	 	         message.setSubject("Reminder for timesheet approval");
	          message.setText(text, true);
	        }};
	       preparatorList.add(preparator);
	      }
	      MimeMessagePreparator[] preparatorArray = new
	                               MimeMessagePreparator[userList.size()];
	      preparatorList.toArray(preparatorArray);
	      mailSender.send(preparatorArray);
	    }
	  
	   /**
	    * Gets the month as a String representation.
	 	   *
	 	   * @param month The month
	    * @return monthAsString The month in a String representation.
	    */
	    private String getMonth(int month) {
	      return MONTHS[month];
	    }
	  
	    /* Setter Methods */
	 	public void setVelocityEngine(VelocityEngine velocityEngine) {
	 	     this.velocityEngine = velocityEngine;
	    }
	 	public void setMailSenders(JavaMailSender mailSender) {
	 		   	this.mailSender = mailSender;
	 	}
	 	 
	 	public void setDefaultFrom(String defaultFrom) {
	 		   	this.defaultFrom = defaultFrom;
	 	}

		@Override
		public void notifyRecupPwd(User user) throws MessagingException {
			this.gestionMailNotify( user.getLogin(),"falyflhz@yahoo.fr" , "Récupereation de mot de passe",
					"", "Récuperation de mot de passe");
			
		}
	
	

}
