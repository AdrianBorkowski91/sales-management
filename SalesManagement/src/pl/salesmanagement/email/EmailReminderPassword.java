package pl.salesmanagement.email;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailReminderPassword {

	    private final static String MYUSERNAME = "salesmanagementcrew";  
	    private final static String MYPASSWORD = "salesmanagement"; 
	    private static String EMAIL;
	    private static String PASSWORD;
	    
	    public static int EMAIL_PASSWORD_REMINDER= 1;
	    public static int EMAIL_PASSWORD_CHANGE=2;
	    
	    private static String SUBJECT_1= "Przypominanie hasła";
	    private static String SUBJECT_2= "Zmiana hasła";
	    
	    public EmailReminderPassword(String password, String email) {
	    	PASSWORD= password;
			EMAIL= email;	
		}

		public void sendFromGMail(int method) throws AddressException, MessagingException {
	        Properties props = System.getProperties();
	        String host = "smtp.gmail.com";
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.host", host);
	        props.put("mail.smtp.user", MYUSERNAME);
	        props.put("mail.smtp.password", MYPASSWORD);
	        props.put("mail.smtp.port", "587");
	        props.put("mail.smtp.auth", "true");

	        Session session = Session.getDefaultInstance(props);
	        MimeMessage message = new MimeMessage(session);
	        
	        String[] to = { EMAIL };
	        
	        try {
	            message.setFrom(new InternetAddress(MYUSERNAME));
	            InternetAddress[] toAddress = new InternetAddress[to.length];

	            for( int i = 0; i < to.length; i++ ) {
	                toAddress[i] = new InternetAddress(to[i]);
	            }

	            for( int i = 0; i < toAddress.length; i++) {
	                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
	            }
	            String body=null;
	            
	            if(method==EMAIL_PASSWORD_REMINDER){
		            body=String.format("Witaj Serdecznie \n\nPoproszono o wysłanie hasła dla twojego konta na Sales-Management. \nTwoje hasło to: %s.", PASSWORD);
		            message.setSubject(SUBJECT_1);
		            message.setText(body);
	            }
	            else if(method==EMAIL_PASSWORD_CHANGE){
		            body=String.format("Witaj Serdecznie \n\nWłaśnie zmieniłeś swoje hasło na Sales-Management. \nTwoje hasło to: %s.", PASSWORD);
		            message.setSubject(SUBJECT_2);
		            message.setText(body);
	            }
	            
	            Transport transport = session.getTransport("smtp");
	            transport.connect(host, MYUSERNAME, MYPASSWORD);
	            transport.sendMessage(message, message.getAllRecipients());
	            transport.close();
	        }
	        catch (AddressException ae) {
	            ae.printStackTrace();
	        }
	        catch (MessagingException me) {
	            me.printStackTrace();
	        }
	    }
	}