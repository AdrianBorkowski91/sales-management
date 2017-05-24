package pl.salesmanagement.controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.salesmanagement.conditions.ConditionsUserAccount;
import pl.salesmanagement.email.EmailReminderPassword;
import pl.salesmanagement.methods.SetParam;
import pl.salesmanagement.model.User;
import pl.salesmanagement.model.UserModelForm;
import pl.salesmanagement.service.AccountService;
import pl.salesmanagement.service.UserService;

@WebServlet("/myaccount")
public class MyAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MyAccountController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("user")!=null){
			request.getRequestDispatcher("WEB-INF/myaccount.jsp").forward(request, response);	 
		}
		else{
			response.sendRedirect("login");  
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        
		String hiddenParam=request.getParameter("my-account");
		User user= (User) request.getSession().getAttribute("user");

		if(hiddenParam.equals("user-settings")){
			String currentPassword= user.getPassword();
			String oldPassword= request.getParameter("password");
			String newPassword= request.getParameter("newpassword");	
			String repeatNewPassword= request.getParameter("repeatnewpassword");

	        UserModelForm userModelForm= new UserModelForm(null, null, newPassword, repeatNewPassword, oldPassword);
 
	        if(ConditionsUserAccount.researchFormUserSettings(request, userModelForm, currentPassword) == true){
	        	request.setAttribute("userModelForm", userModelForm);
	        }
	        else{    
	        	request.setAttribute("messageAboutAccept", ConditionsUserAccount.MESSAGE_NEWPASSWORD_ACCEPT);
	            UserService.updateUser(user.getIdUser(), newPassword);
	            
				EmailReminderPassword erp= new EmailReminderPassword(newPassword, user.getEmail());
				
				try {
					erp.sendFromGMail(EmailReminderPassword.EMAIL_PASSWORD_CHANGE);
				} catch (AddressException e) {
					e.printStackTrace();
				} catch (MessagingException e) {
					e.printStackTrace();
				}
	        }			
		}
		else if(hiddenParam.equals("account-settings")){
			
			String reminder= request.getParameter("reminder");
			String raportModified= request.getParameter("raport-modified");
			
			reminder= SetParam.researchValue(reminder);
			raportModified= SetParam.researchValue(raportModified);
			
			AccountService.updateAccount(user.getIdAccount(), reminder, raportModified);
			
			request.removeAttribute("user");
			User userSession= UserService.getUserWithAccount(user.getIdUser());	    
          	request.getSession().setAttribute("user", userSession);	
		}
		request.getRequestDispatcher("WEB-INF/myaccount.jsp").forward(request, response);
	}

}	
