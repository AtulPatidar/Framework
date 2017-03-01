package com.xpanxion.utils;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
//import java.net.PasswordAuthentication;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.PasswordAuthentication;

public class FetchingEmail {

   public static void main(String[] args) {
	      
	      String emailId =  args[0];  //"test1.anthem@gmail.com";	
	      String password =  args[1]; // "Pass2016#";	
	      fetch(  emailId, password);

   }
	 
   public static void fetch(final String user,final String password) {
      try {
    	  
    	  
    	// POP3 

    	 /*String protocolHost ="pop.gmail.com";
         Properties properties = new Properties();
         properties.put("mail.store.protocol", "pop3");
         properties.put("mail.pop3.host", protocolHost);
         properties.put("mail.pop3.port", "995");
         properties.put("mail.pop3.starttls.enable", "true");*/
         //
    	  
    	 String storeType = "imaps";  //"pop3s";  
         String protocolHost = "mail.xpanxion.co.in";//"smtp.gmail.com";
         String port = "587"; //465,587
         
         
         
         Properties properties = new Properties();
         //SMTP
         //properties.put("mail.smtp.user",user );
         properties.put("mail.smtp.host", protocolHost);
         properties.put("mail.smtp.port", port); 
         properties.put("mail.smtp.starttls.enable","true");
        // properties.put("mail.smtp.debug", "true");
         properties.put("mail.smtp.auth", "true");
      //   properties.put("mail.smtp.socketFactory.port", port);
       //  properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        
         
         //pop 3 
        /* properties.put("mail.pop3s.host", "pop.gmail.com");
         properties.put("mail.pop3s.port", "995");
         properties.put("mail.pop3s.starttls.enable", "true");
         properties.put("mail.pop3s.debug", "true");
         properties.put("mail.pop3s.auth", "true");*/

         // Session emailSession = Session.getDefaultInstance(properties);
         
         // Setup authentication, get session
         Session emailSession = Session.getInstance(properties,
            new javax.mail.Authenticator() {
               protected PasswordAuthentication getPasswordAuthentication() {
                  return new PasswordAuthentication(user,password );
               }
            });
         
        
         // emailSession.setDebug(true);
         emailSession.setDebug(true);

         
         
         // create the POP3 store object and connect with the pop server
         Store store = emailSession.getStore(storeType);
         
       //  store.connect(protocolHost, user, password);
         
         store.connect();
         

         // create the folder object and open it
         Folder emailFolder = store.getFolder("INBOX");
         emailFolder.open(Folder.READ_ONLY);
         
         int messageCount = emailFolder.getMessageCount();
         
         System.out.println("message count " + messageCount );

         BufferedReader reader = new BufferedReader(new InputStreamReader(
	      System.in));

         // retrieve the messages from the folder in an array and print it
         Message[] messages = emailFolder.getMessages();
         System.out.println("messages.length---" + messages.length);

         for (int i = 0; i < messages.length; i++) {
            Message message = messages[i];
            System.out.println("---------------------------------");
            writePart(message);
            String line = reader.readLine();
            if ("YES".equals(line)) {
               message.writeTo(System.out);
            } else if ("QUIT".equals(line)) {
               break;
            }
         }

         // close the store and folder objects
         emailFolder.close(false);
         store.close();

      } catch (NoSuchProviderException e) {
         e.printStackTrace();
      } catch (MessagingException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   /*
   * This method checks for content-type 
   * based on which, it processes and
   * fetches the content of the message
   */
   public static void writePart(Part p) throws Exception {
      if (p instanceof Message)
         //Call methos writeEnvelope
         writeEnvelope((Message) p);

      System.out.println("----------------------------");
      System.out.println("CONTENT-TYPE: " + p.getContentType());

      //check if the content is plain text
      if (p.isMimeType("text/plain")) {
         System.out.println("This is plain text");
         System.out.println("---------------------------");
         System.out.println((String) p.getContent());
      } 
      //check if the content has attachment
      else if (p.isMimeType("multipart/*")) {
         System.out.println("This is a Multipart");
         System.out.println("---------------------------");
         Multipart mp = (Multipart) p.getContent();
         int count = mp.getCount();
         for (int i = 0; i < count; i++)
            writePart(mp.getBodyPart(i));
      } 
      //check if the content is a nested message
      else if (p.isMimeType("message/rfc822")) {
         System.out.println("This is a Nested Message");
         System.out.println("---------------------------");
         writePart((Part) p.getContent());
      } 
      //check if the content is an inline image
     /* else if (p.isMimeType("image/jpeg")) {
         System.out.println("--------> image/jpeg");
         Object o = p.getContent();

         InputStream x = (InputStream) o;
         // Construct the required byte array
         System.out.println("x.length = " + x.available());
         while ((i = (int) ((InputStream) x).available()) > 0) {
            int result = (int) (((InputStream) x).read(bArray));
            if (result == -1)
         int i = 0;
         byte[] bArray = new byte[x.available()];

            break;
         }
         FileOutputStream f2 = new FileOutputStream("/tmp/image.jpg");
         f2.write(bArray);
      } 
*/      else if (p.getContentType().contains("image/")) {
         System.out.println("content type" + p.getContentType());
         File f = new File("image" + new Date().getTime() + ".jpg");
         DataOutputStream output = new DataOutputStream(
            new BufferedOutputStream(new FileOutputStream(f)));
            com.sun.mail.util.BASE64DecoderStream test = 
                 (com.sun.mail.util.BASE64DecoderStream) p
                  .getContent();
         byte[] buffer = new byte[1024];
         int bytesRead;
         while ((bytesRead = test.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
         }
      } 
      else {
         Object o = p.getContent();
         if (o instanceof String) {
            System.out.println("This is a string");
            System.out.println("---------------------------");
            System.out.println((String) o);
         } 
         else if (o instanceof InputStream) {
            System.out.println("This is just an input stream");
            System.out.println("---------------------------");
            InputStream is = (InputStream) o;
            is = (InputStream) o;
            int c;
            while ((c = is.read()) != -1)
               System.out.write(c);
         } 
         else {
            System.out.println("This is an unknown type");
            System.out.println("---------------------------");
            System.out.println(o.toString());
         }
      }

   }
   /*
   * This method would print FROM,TO and SUBJECT of the message
   */
   public static void writeEnvelope(Message m) throws Exception {
      System.out.println("This is the message envelope");
      System.out.println("---------------------------");
      Address[] a;

      // FROM
      if ((a = m.getFrom()) != null) {
         for (int j = 0; j < a.length; j++)
         System.out.println("FROM: " + a[j].toString());
      }

      // TO
      if ((a = m.getRecipients(Message.RecipientType.TO)) != null) {
         for (int j = 0; j < a.length; j++)
         System.out.println("TO: " + a[j].toString());
      }

      // SUBJECT
      if (m.getSubject() != null)
         System.out.println("SUBJECT: " + m.getSubject());

   }

}