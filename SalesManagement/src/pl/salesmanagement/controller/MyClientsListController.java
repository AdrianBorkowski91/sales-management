package pl.salesmanagement.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.salesmanagement.methods.MethodsClient;
import pl.salesmanagement.model.Client;
import pl.salesmanagement.service.ClientService;

@WebServlet("/myclients")
public class MyClientsListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MyClientsListController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("user")!=null){
			request.getRequestDispatcher("WEB-INF/myclients-list.jsp").forward(request, response);	 
		}
		else{
			response.sendRedirect("login");  
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	
        String param= request.getParameter("method");
        
        String[] paramsTab= param.split("\\s+");
        String method= paramsTab[0];
        long idClient=0;
        try {
        	idClient= Long.parseLong(paramsTab[1]);
		} catch (NumberFormatException e) {}
        
        if(method.equals("edit")){
        	Client clientResult = MethodsClient.findClientAfterId(request, idClient);
        	request.getSession().setAttribute("client", clientResult);
        	response.sendRedirect("myclients-edit");  
        }
        else if(method.equals("delete")){
        	if(ClientService.deleteClient(idClient)){
        		System.out.println("Klient usunięty o id: "+idClient);
        	}
        	MethodsClient.createNewListClients(request);
        	request.getRequestDispatcher("WEB-INF/myclients-list.jsp").forward(request, response);	 
        }
        else{
        	request.getRequestDispatcher("WEB-INF/myclients-list.jsp").forward(request, response);	 
        }
	}


}
