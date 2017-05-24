package pl.salesmanagement.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.salesmanagement.conditions.ConditionsLogin;
import pl.salesmanagement.model.User;
import pl.salesmanagement.model.UserModelForm;
import pl.salesmanagement.service.UserService;
import pl.salesmanagement.sessions.CreateSessions;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {   
    	if(request.getSession().getAttribute("userModelForm")!=null){
    		
    		UserModelForm userModelForm= (UserModelForm) request.getSession().getAttribute("userModelForm");
    		request.getSession().removeAttribute("userModelForm");
    		request.setAttribute("userModelForm", userModelForm);
    	}
    	request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response); 
    	
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
        String writtenUsername= request.getParameter("username");
        String writtenPassword= request.getParameter("password");
        
        User user= UserService.getUserByUsername(writtenUsername);       
        
   		if(request.getParameter("sessionRemove").equals("logout")){
            request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response); 
   		}	
   		else{
	       	if(user!=null){	
		       	if(ConditionsLogin.researchFormLogin(request, user, writtenUsername, writtenPassword)){
	       			UserModelForm userModelForm= new UserModelForm(writtenUsername, null, writtenPassword, null, null);
	       	        request.setAttribute("userModelForm", userModelForm);
	       	        
		            request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);          
		       	}
		       	else{
		       		try{
		       			request.login(writtenUsername, writtenPassword);
		       		}catch(ServletException ex) {
		       			ex.getMessage();
		            }
		       		
		       		CreateSessions.create(request, user);  
		       		
		       		response.sendRedirect("myaccount");      
		       	}
	       	}
	       	else{
	            request.setAttribute("message", ConditionsLogin.MESSAGE_LOGIN_WRONG);
	      		UserModelForm userModelForm= new UserModelForm(writtenUsername, null, writtenPassword, null, null);
	            request.setAttribute("userModelForm", userModelForm);  
	            
	            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);  
	       	}
   		}
	}
	
}
