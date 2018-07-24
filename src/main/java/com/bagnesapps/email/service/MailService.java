package com.bagnesapps.email.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.bagnesapps.common.model.ClientApp;

@Service
public class MailService {

	private Properties mailServerProperties;
	private Session mailSession;
	private MimeMessage generatedMailMessage;
	
	public void init() {
		mailServerProperties = System.getProperties();
//		mailServerProperties.put("mail.smtp.port", "587");
//		mailServerProperties.put("mail.smtp.auth", "true");
//		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		
		mailServerProperties.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
		mailServerProperties.put("mail.smtp.socketFactory.port", "465"); //SSL Port
		mailServerProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
		mailServerProperties.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
		mailServerProperties.put("mail.smtp.port", "465"); //SMTP Port
	}
	
	public void sendEmail(String to, String cc, String subject, String body, String replyTo, ClientApp clientApp) throws AddressException, MessagingException {
		this.init();
		
		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(clientApp.getEmail(), clientApp.getPassword());
			}
		};
		
		mailSession = Session.getDefaultInstance(mailServerProperties, auth);
		generatedMailMessage = new MimeMessage(mailSession);
		generatedMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		if(StringUtils.isNoneBlank(cc)) {
			generatedMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
		}		
		if(StringUtils.isNoneBlank(replyTo)) {
			generatedMailMessage.setReplyTo(new InternetAddress[] {(new InternetAddress(replyTo))});
		}		
		generatedMailMessage.setSubject(subject);
		generatedMailMessage.setContent(body, "text/html");
//		Transport transport = mailSession.getTransport("smtp");
//		transport.connect("smtp.gmail.com", clientApp.getEmail(), clientApp.getPassword());
//		transport.sendMessage(generatedMailMessage, generatedMailMessage.getAllRecipients());
//		transport.close();
		Transport.send(generatedMailMessage, generatedMailMessage.getAllRecipients());
	}
}
