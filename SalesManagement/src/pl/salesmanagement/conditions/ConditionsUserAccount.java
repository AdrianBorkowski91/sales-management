package pl.salesmanagement.conditions;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import pl.salesmanagement.model.Account;
import pl.salesmanagement.model.User;
import pl.salesmanagement.model.UserModelForm;
import pl.salesmanagement.service.AccountService;
import pl.salesmanagement.service.UserService;

public class ConditionsUserAccount {

	private static final String MESSAGE_NEWPASSWORD_WRONGPASSWORD="Wystąpiły błędy w formularzu. Twoje hasło jest nieprawidłowe.";
	private static final String MESSAGE_NEWPASSWORD_WRONGFORM="Wystąpiły błędy w formularzu. Hasło powinno składać się z "
			+ "przynajmniej 7 znaków (liter, cyfr lub znaków specjalnych).";
	private static final String MESSAGE_NEWPASSWORD_WRONGCOMPATIBLE="Wystąpiły błędy w formularzu. Podane hasła muszą być takie same.";
	public static final String MESSAGE_NEWPASSWORD_ACCEPT="Operacja zmiany hasła zakończona pomyślnie! Na Twoją "
			+ "skrzynkę pocztową wysłano informacje.";
	
	public static void registerNewUserAndAccount(String username, String email, String password) {
		Account account= AccountService.addAccount();
		long idAccount= account.getIdAccount();
		UserService userService = new UserService();
		User user= new User();
	    user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setIdAccount(idAccount);	
		
		userService.addUser(user);
	}

	public static boolean researchFormUserSettings(HttpServletRequest request, UserModelForm userModelForm, String currentPassword) throws ServletException, IOException {
        if(researchOldPassword(currentPassword, userModelForm.getOldPassword())){
            request.setAttribute("message", MESSAGE_NEWPASSWORD_WRONGPASSWORD);
            return true;  
        }
        if(researchNewPassword(userModelForm.getPassword())){
            request.setAttribute("message", MESSAGE_NEWPASSWORD_WRONGFORM);  
            return true;  
        }
        if(researchPasswordIsCompatible(userModelForm.getPassword(), userModelForm.getRepeatPassword())){
            request.setAttribute("message", MESSAGE_NEWPASSWORD_WRONGCOMPATIBLE); 
            return true;  
        }
        return false;
	}
	
	  static boolean researchOldPassword(String currentPassword, String oldPassword){
	   	 	if(currentPassword.equals(oldPassword)){
		        return false;
	   	 	}
			return true;	
		}
		
		static boolean researchNewPassword(String newPassword){
	   	 	if(newPassword!=null && newPassword!=""){
		        if(newPassword.length()>=7){
		   	   	 	final Pattern formatPassword = Pattern.compile("\\w+");
		   	   	 	Matcher mt = formatPassword.matcher(newPassword);	
		   	   	 	
		   	   	 	if(mt.find()){
		   	   	 		return false;
		   	   	 	}
		        }
	   	 	}
			return true;	
		}
		
		static boolean researchPasswordIsCompatible(String newPassword, String repeatNewPassword){
	   	 	if(newPassword.equals(repeatNewPassword)){
		        return false;
	   	 	}
			return true;	
		}	
}
