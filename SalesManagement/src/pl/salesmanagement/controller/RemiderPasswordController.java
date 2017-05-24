package pl.salesmanagement.controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.salesmanagement.conditions.ConditionsPassword;
import pl.salesmanagement.email.EmailReminderPassword;
import pl.salesmanagement.model.User;

@WebServlet("/remiderpassword")
public class RemiderPasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/passwordreminder.jsp").forward(request, response); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        
		String hiddenParam=request.getParameter("remider-method");
		 
		if(hiddenParam.equals("username-method")){
			String username= request.getParameter("username");
			User user= ConditionsPassword.researchAccountAfterUsername(request, username);
			
			EmailReminderPassword erp= new EmailReminderPassword(user.getPassword(), user.getEmail());
			
			try {
				erp.sendFromGMail(EmailReminderPassword.EMAIL_PASSWORD_REMINDER);
			} catch (AddressException e) {
				e.printStackTrace();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			
			request.getRequestDispatcher("WEB-INF/passwordreminder.jsp").forward(request, response);       
		}
		else if(hiddenParam.equals("email-method")){
			String email= request.getParameter("email");
			User user= ConditionsPassword.researchAccountAfterEmail(request, email);
			
			EmailReminderPassword erp= new EmailReminderPassword(user.getPassword(), email);
			
			try {
				erp.sendFromGMail(EmailReminderPassword.EMAIL_PASSWORD_REMINDER);
			} catch (AddressException e) {
				e.printStackTrace();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			
			request.getRequestDispatcher("WEB-INF/passwordreminder.jsp").forward(request, response);
		}		
	}
	
}
