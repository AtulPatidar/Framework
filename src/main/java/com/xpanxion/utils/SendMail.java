
package com.xpanxion.utils;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String to = args[0];
		String from = args[1];
		
		final String username = from;
	    final String password = args[2];
	    
	    String host =  "mail.xpanxion.co.in"; //"smtp.gmail.com"; 

	    Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.ssl.trust", host); // 
	      
	      
	      Session session = Session.getInstance(props,
	    	      new javax.mail.Authenticator() {
	    	         protected PasswordAuthentication getPasswordAuthentication() {
	    	            return new PasswordAuthentication(username, password);
	    	         }
	    	      });
	      System.out.println("Authentication is Successful....");
	      try {
	          // Create a default MimeMessage object.
	          Message message = new MimeMessage(session);

	          // Set From: header field of the header.
	          message.setFrom(new InternetAddress(from));

	          // Set To: header field of the header.
	          message.setRecipients(Message.RecipientType.TO,
	          InternetAddress.parse(to));

	          // Set Subject: header field
	          message.setSubject("Automatic Email Generation");

	          // Now set the actual message
	          message.setText("Hi,\n This is an automated email...2:) ");
	          
	          System.out.println("Sending Message.... ");

	          // Send message
	          Transport.send(message);

	          System.out.println("Sent message successfully....");

	       } catch (MessagingException e) {
	             System.out.println(e.getMessage());
	       }	      
	}

}
