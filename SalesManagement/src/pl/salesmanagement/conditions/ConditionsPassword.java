package pl.salesmanagement.conditions;

import javax.servlet.http.HttpServletRequest;

import pl.salesmanagement.model.User;
import pl.salesmanagement.service.UserService;

public class ConditionsPassword {
	
	private static User user;
	
	private static final int USERNAME_METHOD=1;
	private static final int EMAIL_METHOD=2;
	
	private static final String MESSAGE_REMIDERER_EMAILACCEPT="Operacja przypominania hasła zakończona pomyślnie! Hasło zostało "
			+ "wysłane na Twoją skrzynkę pocztową.";
	private static final String MESSAGE_REMIDERER_EMAILNOTACCEPT="Użytkownik z podanym adresem e-mailowym nie istnieje! "
			+ "Spróbuj zmienić hasło podając swoją nazwę użytkownika, jeżeli nie pamiętasz swój adres e-mail";
	private static final String MESSAGE_REMIDERER_USERNAMEACCEPT="Operacja przypominania hasła zakończona pomyślnie! Hasło "
			+ "zostało wysłane na Twoją skrzynkę pocztową.";
	private static final String MESSAGE_REMIDERER_USERNAMENOTACCEPT="Użytkownik z podaną nazwą nie istnieje! Spróbuj zmienić "
			+ "hasło podając swój adres e-mail, jeżeli nie pamiętasz nazwy użytkownika";	

	public static User researchAccountAfterEmail(HttpServletRequest request, String email){
		if(researchAccount(email, EMAIL_METHOD)){
			request.setAttribute("messageAboutAccept", MESSAGE_REMIDERER_EMAILACCEPT);   
			return user;
		}
		else{
	        request.setAttribute("message", MESSAGE_REMIDERER_EMAILNOTACCEPT);
			request.setAttribute("emailreturn", email);
			return null;
		}
	}
	
	public static User researchAccountAfterUsername(HttpServletRequest request, String username){
		if(researchAccount(username, USERNAME_METHOD)){
			request.setAttribute("messageAboutAccept", MESSAGE_REMIDERER_USERNAMEACCEPT); 
			return user;
		}
		else{		
	        request.setAttribute("message", MESSAGE_REMIDERER_USERNAMENOTACCEPT);       
			request.setAttribute("usernamereturn", username);			
			return null;
		}
	}
	
	private static boolean researchAccount(String param, int method){
		User userResearch=null;
		
		if(method==USERNAME_METHOD){
			userResearch= UserService.getUserByUsername(param);
			user= userResearch;
		}
		else if(method==EMAIL_METHOD){
			userResearch= UserService.getUserByEmail(param);
			user= userResearch;
		}
		
		if(userResearch==null){
			return false;	
		}
		return true;
	}
	
  
}
