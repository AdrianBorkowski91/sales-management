package pl.salesmanagement.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.salesmanagement.conditions.ConditionsRegister;
import pl.salesmanagement.conditions.ConditionsUserAccount;
import pl.salesmanagement.model.UserModelForm;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/register.jsp").forward(request, response); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
        String username= request.getParameter("username");
        String email = request.getParameter("email");
        String password= request.getParameter("password");
        String repeatPassword= request.getParameter("repeat-password");
        
        UserModelForm userModelForm= new UserModelForm(username, email, password, repeatPassword, null);
       
        if(ConditionsRegister.researchFormRegistration(request, userModelForm)==true){
        	request.setAttribute("userModelForm", userModelForm);
            request.getRequestDispatcher("WEB-INF/register.jsp").forward(request, response);
        }
        else{                 
        	ConditionsUserAccount.registerNewUserAndAccount(username, email, password);
        	
        	request.getSession().setAttribute("userModelForm", userModelForm);
            response.sendRedirect("login");  
        }
	}

}
