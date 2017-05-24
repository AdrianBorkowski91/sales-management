package pl.salesmanagement.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.salesmanagement.conditions.ConditionsClient;
import pl.salesmanagement.methods.MethodsClient;
import pl.salesmanagement.model.Client;
import pl.salesmanagement.model.Industry;
import pl.salesmanagement.model.Province;
import pl.salesmanagement.model.User;
import pl.salesmanagement.service.ClientService;

@WebServlet("/myclients-new")
public class MyClientsNewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MyClientsNewController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("user")!=null){
			request.getRequestDispatcher("WEB-INF/myclients-new.jsp").forward(request, response);	 
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
        
        User user= (User) request.getSession().getAttribute("user");
        long idUser= user.getIdUser();
        
        String firstname= request.getParameter("firstname");
        String lastname= request.getParameter("lastname"); 
        String address= request.getParameter("address");
        String city= request.getParameter("city");
        String zipCode= request.getParameter("zip-code");
        String company= request.getParameter("company");
        String numberPhone= request.getParameter("number-phone");
        String email= request.getParameter("email");

		String provinceString= request.getParameter("province");
		long idProvince = Province.findTheProvinceNameAfterName(provinceString);
		
		String industryString= request.getParameter("industry");
		long idIndustry = Industry.findTheIndustryNameAfterName(industryString);

        Client client = new Client(idUser, firstname, lastname, idProvince, address, city, zipCode,
       		 idIndustry, company, numberPhone, email);
        Client clientEmpty = MethodsClient.clientEmpty(idUser);

        if(button.equals("return")){
        	if(clientEmpty.toString().equals(client.toString())){
        		response.sendRedirect("myclients");  
        	}
        	else{
	        	request.setAttribute("client", client);
        		request.setAttribute("modal", "modal");
        		request.getRequestDispatcher("WEB-INF/myclients-new.jsp").forward(request, response);
        	}
        }
        else if(button.equals("save")){
	        if(ConditionsClient.researchFormCreateClient(request, firstname, lastname, idProvince, city, company, idIndustry, numberPhone, zipCode, email)==true){ 
	        	request.setAttribute("client", client);
	        	
	        	request.getRequestDispatcher("WEB-INF/myclients-new.jsp").forward(request, response);
	        }
	        else{
	        	Client clientResult=ClientService.addClient(client);
	            MethodsClient.createNewListClients(request);
	            
	            clientResult.setActivity("checked");
	            clientResult.setIdClient(clientResult.getIdClient());
	            
	            request.getSession().setAttribute("messageAboutAccept", ConditionsClient.CLIENT_MESSAGE_ACCEPT_TO_EDIT);
	            
	        	request.getSession().setAttribute("client", clientResult);
	        	response.sendRedirect("myclients-edit");  
	        }
        }
	}
	
}
