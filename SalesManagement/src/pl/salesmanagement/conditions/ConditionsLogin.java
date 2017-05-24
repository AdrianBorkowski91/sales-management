package pl.salesmanagement.conditions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import pl.salesmanagement.model.User;

public class ConditionsLogin {
	
	private static final String MESSAGE_LOGIN_RESEARCHACCOUNT_USERNAME="Wystąpiły błędy w formularzu. Użytkownik z podaną nazwą nie istnieje!";
	private static final String MESSAGE_LOGIN_RESERACHACCOUNT_PASSWORD="Wystąpiły błędy w formularzu. Twoje hasło jest "
			+ "nieprawidłowe. Spróbuj ponownie bądź kliknij na opcję przypominania hasła.";
	public static final String MESSAGE_LOGIN_WRONG="Wystąpiły błędy w formularzu. Użytkownik z podaną nazwą nie istnieje!";
	
	public static boolean researchFormLogin(HttpServletRequest request, User user, String writtenUsername, String writtenPassword) throws ServletException, IOException {
        
		if(ConditionsLogin.researchAccountAfterUsername(user.getUsername(), writtenUsername)){
           request.setAttribute("message", MESSAGE_LOGIN_RESEARCHACCOUNT_USERNAME);
	       return true;
	    }
	    if(ConditionsLogin.researchAccountAfterPassword(user.getPassword(), writtenPassword)){
	       request.setAttribute("message", MESSAGE_LOGIN_RESERACHACCOUNT_PASSWORD);
	       return true;
		}
        return false;
	}
	
	static boolean researchAccountAfterUsername(String username, String writtenUsername){
		if(writtenUsername!=null &&  writtenUsername!="" ){
			
			if(username.equals(writtenUsername)){
				return false;	
			}
			else
				return true;
			}
		return true;
	}
	
	static boolean researchAccountAfterPassword(String password, String writtenPassword){
		if(writtenPassword!=null &&  writtenPassword!=""){
			
			if(password.equals(writtenPassword)){
				return false;	
			}
			else
				return true;
		}
		return true;
	}
}
