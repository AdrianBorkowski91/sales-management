package pl.salesmanagement.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.salesmanagement.conditions.ConditionsHistoryOfMeeting;
import pl.salesmanagement.methods.MethodsHistoryOfMeeting;
import pl.salesmanagement.model.Client;
import pl.salesmanagement.model.Effect;
import pl.salesmanagement.model.HistoryOfMeeting;
import pl.salesmanagement.model.Meeting;
import pl.salesmanagement.service.HistoryOfMeetingService;

@WebServlet("/historyofmeetings-edit")
public class HistoryOfMeetingsEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HistoryOfMeetingsEditController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("history")!=null){
			HistoryOfMeeting history= (HistoryOfMeeting) request.getSession().getAttribute("history");
			request.getSession().removeAttribute("history");
			request.setAttribute("history", history);
			
			if(request.getSession().getAttribute("methodHistory")!=null){
				String descriptive=(String) request.getSession().getAttribute("methodHistory");
				request.getSession().removeAttribute("methodHistory");
				request.setAttribute("methodHistory", descriptive);
			}
			
			if(request.getSession().getAttribute("messageAboutAccept")!=null){
				String message= (String) request.getSession().getAttribute("messageAboutAccept");
				request.getSession().removeAttribute("messageAboutAccept");
				request.setAttribute("messageAboutAccept", message);
			}
			
			request.getRequestDispatcher("WEB-INF/historyofmeetings-edit.jsp").forward(request, response);	    
		}
		else if(request.getSession().getAttribute("user")!=null){
			response.sendRedirect("historyofmeetings");
		}
		else{
			response.sendRedirect("login");  
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String idHistoryString= request.getParameter("id-history");
		long idHistory=0;
		
		try {
			idHistory= Long.parseLong(idHistoryString);
		} catch (NumberFormatException e) {}
		
		String idEffectString= request.getParameter("effect");
		long idEffect= Effect.findTheEffectIdAfterName(idEffectString);
		
		String rotationString= request.getParameter("rotation");
		float rotation=0;
		
		try {
			rotation= Float.parseFloat(rotationString);
		} catch (NumberFormatException e1) {}
		
		String description= request.getParameter("description");
		
		String idClientString= request.getParameter("id-client");
		long idClient=0;
		
		try {
			idClient= Long.parseLong(idClientString);
		} catch (NumberFormatException e) {}
		
		String idMeetingString= request.getParameter("id-meeting");
		long idMeeting=0;
		
		try {
			idMeeting= Long.parseLong(idMeetingString);
		} catch (NumberFormatException e) {}
			
		Client client= MethodsHistoryOfMeeting.findClientAfterId(request, idHistory, idClient);
		Meeting meeting= MethodsHistoryOfMeeting.findMeetingAfterId(request, idHistory, idMeeting);
		
		String button = request.getParameter("button");
		String paramsTab[]= button.split("\\s+");
		
		if(button.equals("accept")){
			String method= request.getParameter("accept-method");
			if(method.equals("edit")){
				String idHistoryReturnString= request.getParameter("return-id-history");
				long idHistoryReturn=0;
				try {
					idHistoryReturn= Long.parseLong(idHistoryReturnString);
				} catch (NumberFormatException e) {}
				
				HistoryOfMeeting historyEdit= MethodsHistoryOfMeeting.findHistoryOfMeetingAfterId(request, idHistoryReturn);
				request.setAttribute("history", historyEdit);
				
				request.getRequestDispatcher("WEB-INF/historyofmeetings-edit.jsp").forward(request, response);	
			}
			else if(method.equals("historylists")){
				response.sendRedirect("historyofmeetings");
			}
			else if(method.equals("editClient")){
				request.getSession().setAttribute("client", client);
				response.sendRedirect("myclients-edit");
			}
		}

		HistoryOfMeeting history =new HistoryOfMeeting(idHistory, idEffect, rotation, description, client, meeting);
		HistoryOfMeeting historyPattern=null;

		if(request.getParameter("method-history").equals("descriptive")){
			historyPattern= MethodsHistoryOfMeeting.findHistoryOfMeetingAfterId(request, idHistory);
		}
		else{
			historyPattern= MethodsHistoryOfMeeting.findHistoryOfMeetingAfterId(request, idHistory);
		}

		if(paramsTab[0].equals("edit-history")){
			
			long idHistoryParam=0;
			try {
				idHistoryParam= Long.parseLong(paramsTab[1]);
			} catch (NumberFormatException e) {}

			if(historyPattern.equals(history)){
				HistoryOfMeeting historyEdit= MethodsHistoryOfMeeting.findHistoryOfMeetingAfterId(request, idHistoryParam);
				request.setAttribute("history", historyEdit);
				
				request.getRequestDispatcher("WEB-INF/historyofmeetings-edit.jsp").forward(request, response);
			}
			else{
				request.setAttribute("returnIdHistory", idHistoryParam);
				request.setAttribute("history", history);
				request.setAttribute("modal", "modal");
				request.setAttribute("method", "edit");
				
				request.getRequestDispatcher("WEB-INF/historyofmeetings-edit.jsp").forward(request, response);
			}
		}
		else if(button.equals("save")){
			if(ConditionsHistoryOfMeeting.researchFormCreateHistory(request, idEffect)){				
				request.setAttribute("history", history);
				request.getRequestDispatcher("WEB-INF/historyofmeetings-edit.jsp").forward(request, response);	
			}
			else{
				HistoryOfMeetingService.updateMeeting(history, historyPattern);
				MethodsHistoryOfMeeting.createListHistoryOfMeetings(request, MethodsHistoryOfMeeting.METHOD_DESCRIPTIVE);
				MethodsHistoryOfMeeting.createListHistoryOfMeetings(request, MethodsHistoryOfMeeting.METHOD_NOT_DESCRIPTIVE);
				
				HistoryOfMeeting historyNext= MethodsHistoryOfMeeting.findNextHistory(request);			
				if(historyNext!=null){
					request.setAttribute("history", historyNext);
					request.setAttribute("messageAboutAccept", ConditionsHistoryOfMeeting.HISTORY_MESSAGE_ACCEPT_TO_EDIT2);
						
					request.getRequestDispatcher("WEB-INF/historyofmeetings-edit.jsp").forward(request, response);		
				}					
				else{
					response.sendRedirect("historyofmeetings");			
				}
			}
		}	
		else if(button.equals("return")){
			if(historyPattern.equals(history)){
				response.sendRedirect("historyofmeetings");			
			}
			else{
				request.setAttribute("history", history);
				request.setAttribute("modal", "modal");
				request.setAttribute("method", "historylists");
				
				request.getRequestDispatcher("WEB-INF/historyofmeetings-edit.jsp").forward(request, response);
			}
		}
		else if(button.equals("edit-client")){
			if(historyPattern.equals(history)){			
				request.getSession().setAttribute("client", client);
				response.sendRedirect("myclients-edit");
			}
			else{
				request.setAttribute("returnIdClient", client.getIdClient());
				request.setAttribute("history", history);
				request.setAttribute("modal", "modal");
				request.setAttribute("method", "editClient");
				
				request.getRequestDispatcher("WEB-INF/historyofmeetings-edit.jsp").forward(request, response);
			}
		}
	}
}
