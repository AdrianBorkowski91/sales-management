package pl.salesmanagement.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.salesmanagement.conditions.ConditionsClient;
import pl.salesmanagement.methods.MethodsClient;
import pl.salesmanagement.methods.SetParam;
import pl.salesmanagement.model.Client;
import pl.salesmanagement.model.Industry;
import pl.salesmanagement.model.Province;
import pl.salesmanagement.service.ClientService;

@WebServlet("/myclients-edit")
public class MyClientsEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public MyClientsEditController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("client")!=null){
			
			Client client= (Client) request.getSession().getAttribute("client");
			request.getSession().removeAttribute("client");
			request.setAttribute("client", client);
			
			if(request.getSession().getAttribute("messageAboutAccept")!=null){
				String message= (String) request.getSession().getAttribute("messageAboutAccept"); 
				request.getSession().removeAttribute("messageAboutAccept");
				request.setAttribute("messageAboutAccept", message);			
			}
			
			request.getRequestDispatcher("WEB-INF/myclients-edit.jsp").forward(request, response);	    
		}
		else if(request.getSession().getAttribute("user")!=null){
			response.sendRedirect("myclients");
		}
		else{
			response.sendRedirect("login");  
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String button= request.getParameter("button");
		
		if(button.equals("accept")){
			response.sendRedirect("myclients");  
		}
		
        long idClient= Long.parseLong(request.getParameter("id-client"));
        String firstname= request.getParameter("firstname");
        String lastname= request.getParameter("lastname");
        String address= request.getParameter("address");
        String city= request.getParameter("city");
        String zipCode= request.getParameter("zip-code");
        String company= request.getParameter("company");
        String numberPhone= request.getParameter("number-phone");
        String email= request.getParameter("email");
        String activity = request.getParameter("activity");
        activity= SetParam.researchValue(activity);
        
		String provinceString= request.getParameter("province");
		long idProvince = Province.findTheProvinceNameAfterName(provinceString);
		
		String industryString= request.getParameter("industry");
		long idIndustry = Industry.findTheIndustryNameAfterName(industryString);
		
        Client client = new Client(idClient, firstname, lastname, idProvince, address, city, zipCode,
          		 idIndustry, company, numberPhone, email, activity); 

		if(button.equals("return")){
		Client clientOld= MethodsClient.findClientAfterId(request, idClient);
        	if(clientOld.toString().equals(client.toString())){
        		response.sendRedirect("myclients");  
        	}
        	else{
	        	request.setAttribute("client", client);
        		request.setAttribute("modal", "modal");
        		request.getRequestDispatcher("WEB-INF/myclients-edit.jsp").forward(request, response);
        	}
		}
		else if(button.equals("save")){

		    if(!ConditionsClient.researchFormCreateClient(request, firstname, lastname, idProvince, city, company, idIndustry, numberPhone, zipCode, email)==true){ 
		        ClientService.updateClient(client);
		        MethodsClient.createNewListClients(request);
		            
		       	request.setAttribute("messageAboutAccept", ConditionsClient.CLIENT_MESSAGE_ACCEPT);
		    }
	        request.setAttribute("client", client);
	        request.getRequestDispatcher("WEB-INF/myclients-edit.jsp").forward(request, response);
		}
		
	}
	
}
