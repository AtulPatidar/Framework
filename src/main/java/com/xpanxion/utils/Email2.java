package com.xpanxion.utils;


import java.io.IOException;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Send an email
 */

/**
 * @author surendrane
 *
 */
public class Email2 {

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
	    String storeType = "imaps"; //"pop3s";  // "imaps";  //
	    String protocol =  "pop3";//"smtp"; //"smtp"; // 
	    
	     
	         // emailSession.setDebug(true);

	     /* Session session = Session.getInstance(props,
	    	      new javax.mail.Authenticator() {
	    	         protected PasswordAuthentication getPasswordAuthentication() {
	    	            return new PasswordAuthentication(username, password);
	    	         }
	    	      });*/
	      
	      
	    
	      try {
	    	  
	    	  Properties props = new Properties();
		      props.put("mail.store.protocol", protocol);
		      props.put("mail."+protocol+".host", host);
		      //props.put("mail."+protocol+".port", "995");
		     // props.put("mail."+protocol+".starttls.enable", "true");
		      //props.put("mail."+protocol+".ssl.trust", host);
		      
		     /* props.put("mail."+protocol+".auth", "true");
		      props.put("mail."+protocol+".host", host);
		      props.put("mail."+protocol+".ssl.trust", host);
				props.put("mail.smtp.port", "587");*/
		      
		      //Session emailSession = Session.getDefaultInstance(props);
	          
		      
		      Session emailSession = Session.getInstance(props,
		    	      new javax.mail.Authenticator() {
		    	         protected PasswordAuthentication getPasswordAuthentication() {
		    	            return new PasswordAuthentication(username, password);
		    	         }
		    	      });

	          // create the POP3 store object and connect with the pop server
	          Store store = emailSession.getStore("pop3s");

	          store.connect(host, to, password);

	          // create the folder object and open it
	          Folder emailFolder = store.getFolder("INBOX");
	          emailFolder.open(Folder.READ_ONLY);

	         // BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	          // retrieve the messages from the folder in an array and print it
	          Message[] messages = emailFolder.getMessages();
	          System.out.println("messages.length---" + messages.length);

	         /* for (int i = 0; i < messages.length; i++) {
	             Message message = messages[i];
	             System.out.println("---------------------------------");
	             writePart(message);
	             String line = reader.readLine();
	             if ("YES".equals(line)) {
	                message.writeTo(System.out);
	             } else if ("QUIT".equals(line)) {
	                break;
	             }
	          }*/

	          // close the store and folder objects
	          emailFolder.close(false);
	          store.close();

	       } catch (NoSuchProviderException e) {
	          e.printStackTrace();
	       } catch (MessagingException e) {
	          e.printStackTrace();
	       } /*catch (IOException e) {
	          e.printStackTrace();
	       } */catch (Exception e) {
	          e.printStackTrace();
	       }
	    } 
	      
	      
	     /* try{
	    	  
			 store = session.getStore(storeType);
			 store.connect();
			 //store.connect(host, username, password);
			 Folder emailFolder = null ;
		
			emailFolder = store.getFolder("INBOX");
			emailFolder.open(Folder.READ_ONLY);
			
			int messageCount = emailFolder.getMessageCount();
			
			System.out.println("message count " + messageCount );
			
		  }	
		    catch (NoSuchProviderException e) {
	         e.printStackTrace();
	      } catch (MessagingException e) {
	         e.printStackTrace();
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
		*/
	      


}
