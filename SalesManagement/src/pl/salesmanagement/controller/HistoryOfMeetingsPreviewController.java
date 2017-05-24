package pl.salesmanagement.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.salesmanagement.conditions.ConditionsHistoryOfMeeting;
import pl.salesmanagement.methods.MethodsClient;
import pl.salesmanagement.methods.MethodsHistoryOfMeeting;
import pl.salesmanagement.model.Client;
import pl.salesmanagement.model.HistoryOfMeeting;

@WebServlet("/historyofmeetings-preview")
public class HistoryOfMeetingsPreviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HistoryOfMeetingsPreviewController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("history")!=null){
			HistoryOfMeeting history= (HistoryOfMeeting) request.getSession().getAttribute("history");
			request.getSession().removeAttribute("history");
			request.setAttribute("history", history);
			
			request.getRequestDispatcher("WEB-INF/historyofmeetings-preview.jsp").forward(request, response);	    
		}
		else if(request.getSession().getAttribute("user")!=null){
			response.sendRedirect("historyofmeetings");
		}
		else{
			response.sendRedirect("login");  
		}	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String button = request.getParameter("button");

		
		if(button.equals("return")){
			response.sendRedirect("historyofmeetings");
		}
		else if(button.equals("edit-client")){
			String idClientString = request.getParameter("id-client");
			long idClient=0;
			
			try {
				idClient = Long.parseLong(idClientString);
			} catch (NumberFormatException e) {}
			
			Client client= MethodsClient.findClientAfterId(request, idClient);
			request.getSession().setAttribute("client", client);

			response.sendRedirect("myclients-edit");
		}
		else if(button.equals("edit-return")){
			String idHistoryString = request.getParameter("id-history");
			long idHistory=0;
			
			try {
				idHistory = Long.parseLong(idHistoryString);
			} catch (NumberFormatException e) {}
			
			HistoryOfMeeting history= MethodsHistoryOfMeeting.findHistoryOfMeetingAfterId(request, idHistory);
			request.getSession().setAttribute("history", history);
			request.getSession().setAttribute("methodHistory", "descriptive");
			request.getSession().setAttribute("messageAboutAccept", ConditionsHistoryOfMeeting.HISTORY_MESSAGE_ACCEPT_TO_EDIT);
			
			response.sendRedirect("historyofmeetings-edit");
		}
	}

}
