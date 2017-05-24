package pl.salesmanagement.conditions;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import pl.salesmanagement.model.User;
import pl.salesmanagement.model.UserModelForm;
import pl.salesmanagement.service.UserService;

public class ConditionsRegister {
	
	private static final String MESSAGE_REGISTER_RESEARCHUSERNAME="Wystąpiły błędy w formularzu. Nazwa użytkownika powinna "
			+ "składać się z przynajmniej 7 znaków (liter, cyfr lub znaków specjalnych).";
	private static final String MESSAGE_REGISTER_RESEARCHACCOUNT_USERNAME="Konto z podaną nazwą użytkownika już istnieje! "
			+ "Zmień swoją nazwę pamiętając, że nazwa użytkownika powinna składać się z przynajmniej 7 znaków (liter, cyfr lub znaków specjalnych).";
	private static final String MESSAGE_REGISTER_RESEARCHEMAIL= "Wystąpiły błędy w formularzu. Format adresu e-mail jest "
			+ "nieprawidłowy.";	
	private static final String MESSAGE_REGISTER_RESEARCHACCOUNT_EMAIL="Konto z podanym adresem e-mail już istnieje! Jeżeli "
			+ "nie pamiętasz swojego hasła, kliknij w formularzu logowania opcje przypominania hasła.";
	private static final String MESSAGE_REGISTER_RESEARCHPASSWORD="Wystąpiły błędy w formularzu. Hasło powinno składać się z "
			+ "przynajmniej 7 znaków (liter, cyfr lub znaków specjalnych).";
	private static final String MESSAGE_REGISTER_RESEARCHCOMPATIBLE="Wystąpiły błędy w formularzu. Podane hasła muszą "
			+ "być takie same.";
	
	public static boolean researchFormRegistration(HttpServletRequest request, UserModelForm userModelForm) throws ServletException, IOException {
        if(ConditionsRegister.researchUsername(userModelForm.getUsername())){
            request.setAttribute("message", MESSAGE_REGISTER_RESEARCHUSERNAME);
            return true;
        }
        if(ConditionsRegister.researchAccountAfterUsername(userModelForm.getUsername())){
            request.setAttribute("message", MESSAGE_REGISTER_RESEARCHACCOUNT_USERNAME);
            return true;
        }
        if(ConditionsRegister.researchEmail(userModelForm.getEmail())){
            request.setAttribute("message", MESSAGE_REGISTER_RESEARCHEMAIL);
            return true;
        }
        if(ConditionsRegister.researchAccountAfterEmail(userModelForm.getEmail())){       
            request.setAttribute("message", MESSAGE_REGISTER_RESEARCHACCOUNT_EMAIL);
            return true;
        }
        if(ConditionsRegister.researchPassword(userModelForm.getPassword())){
            request.setAttribute("message", MESSAGE_REGISTER_RESEARCHPASSWORD);
            return true;
        }
        if(ConditionsRegister.researchPasswordIsCompatible(userModelForm.getPassword(), userModelForm.getRepeatPassword())){
            request.setAttribute("message", MESSAGE_REGISTER_RESEARCHCOMPATIBLE);
            return true;
        }
		return false; 	
	}
	
	static boolean researchUsername(String username){
		if(username!=null && username!=""){
	        if(username.length()>=7){
	   	   	 	final Pattern formatUsername = Pattern.compile("\\w+");
	   	   	 	Matcher mt = formatUsername.matcher(username);	
	   	   	 	
	   	   	 	if(mt.find()){
	   	   	 		return false;
	   	   	 	}
	        }
		}
		return true;	
	}
	
	static boolean researchAccountAfterUsername(String username){
		User userResearch= UserService.getUserByUsername(username);
		
		if(userResearch==null){
			return false;	
		}
		return true;
	}
	
	static boolean researchEmail(String email){
   	 	if(email!=null && email!=""){
   	   	 	final Pattern formatEmail = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
   	   	 	Matcher mt = formatEmail.matcher(email);	
   	   	 	
   	   	 	if(mt.find()){
   	   	 		return false;
   	   	 	}
   	 	}
		return true;	
	}	
	
	static boolean researchAccountAfterEmail(String email){
		User userResearch= UserService.getUserByEmail(email);
		
		if(userResearch==null){
			return false;	
		}
		return true;
	}	
	
	static boolean researchPassword(String password){
   	 	if(password!=null && password!=""){
	        if(password.length()>=7){
	   	   	 	final Pattern formatPassword = Pattern.compile("\\w+");
	   	   	 	Matcher mt = formatPassword.matcher(password);	
	   	   	 	
	   	   	 	if(mt.find()){
	   	   	 		return false;
	   	   	 	}
	        }
   	 	}
		return true;	
	}
	
	static boolean researchPasswordIsCompatible(String password, String repeatPassword){
   	 	if(password.equals(repeatPassword)){
	        return false;
   	 	}
		return true;	
	}

}
