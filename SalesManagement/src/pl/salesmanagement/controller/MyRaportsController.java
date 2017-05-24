package pl.salesmanagement.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/myraports")
public class MyRaportsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("user")!=null){
			request.getRequestDispatcher("WEB-INF/myraports.jsp").forward(request, response);	 
		}
		else{
			response.sendRedirect("login");  
		}
	}


}
