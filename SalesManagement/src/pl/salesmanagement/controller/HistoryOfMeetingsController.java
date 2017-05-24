package pl.salesmanagement.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.salesmanagement.methods.MethodsClient;
import pl.salesmanagement.methods.MethodsHistoryOfMeeting;
import pl.salesmanagement.methods.MethodsMeeting;
import pl.salesmanagement.model.HistoryOfMeeting;
import pl.salesmanagement.service.HistoryOfMeetingService;
import pl.salesmanagement.service.MeetingService;

@WebServlet("/historyofmeetings")
public class HistoryOfMeetingsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HistoryOfMeetingsController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("user")!=null){
			request.getRequestDispatcher("WEB-INF/historyofmeetings.jsp").forward(request, response);	 
		}
		else{
			response.sendRedirect("login");  
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String typeList= request.getParameter("type-list");
		
		if(typeList.equals("notdescriptive")){
			String button= request.getParameter("button");
			String[] params= button.split("\\s+");
			
			long idHistoryParam=0;
			try {
				idHistoryParam = Long.parseLong(params[1]);
			} catch (NumberFormatException e1) {}
			
			HistoryOfMeeting history= MethodsHistoryOfMeeting.findHistoryOfMeetingAfterId(request, idHistoryParam);
			
			if(params[0].equals("edit")){
				request.getSession().setAttribute("history", history);
				response.sendRedirect("historyofmeetings-edit");
			}
			else if(params[0].equals("delete")){
	        	if(HistoryOfMeetingService.deleteHistoryOfMeeting(idHistoryParam)){
	        		MethodsClient.modificationObject(request, MethodsClient.HISTORY_NOT_DESCRIPTIVE_OBJECT, MethodsClient.DELETE_METHOD, idHistoryParam);
	        		System.out.println("Spotkanie przeszłe zostało usunięte o id: "+idHistoryParam);
	        	}
	        	MethodsHistoryOfMeeting.createNewListHistoryOfMeetings(request, MethodsHistoryOfMeeting.METHOD_NOT_DESCRIPTIVE);
	        	
	        	long idMeeting= history.getMeeting().getIdMeeting();
	        	if(MeetingService.deleteMeeting(idMeeting)){
	        		System.out.println("Spotkanie zostało usunięte o id: "+idMeeting);
	        	}
	        	MethodsMeeting.createNewListMeetings(request);
	        	
	        	request.getRequestDispatcher("WEB-INF/historyofmeetings.jsp").forward(request, response);	
			}
		}
		else if(typeList.equals("descriptive")){
			String button= request.getParameter("button");
			String[] params= button.split("\\s+");
			
			long idHistoryParam=0;
			try {
				idHistoryParam = Long.parseLong(params[1]);
			} catch (NumberFormatException e1) {}
			
			HistoryOfMeeting history= MethodsHistoryOfMeeting.findHistoryOfMeetingAfterId(request, idHistoryParam);
			
			if(params[0].equals("edit")){
				request.getSession().setAttribute("history", history);
				response.sendRedirect("historyofmeetings-preview");
			}
			else if(params[0].equals("delete")){
	        	if(HistoryOfMeetingService.deleteHistoryOfMeeting(idHistoryParam)){
	        		MethodsClient.modificationObject(request, MethodsClient.HISTORY_DESCRIPTIVE_OBJECT, MethodsClient.DELETE_METHOD, idHistoryParam);
	        		System.out.println("Spotkanie przeszłe, z opisem zostało usunięte o id: "+idHistoryParam);
	        	}
	        	MethodsHistoryOfMeeting.createNewListHistoryOfMeetings(request, MethodsHistoryOfMeeting.METHOD_DESCRIPTIVE);
	        	
	        	long idMeeting= history.getMeeting().getIdMeeting();
	        	if(MeetingService.deleteMeeting(idMeeting)){
	        		System.out.println("Spotkanie zostało usunięte o id: "+idMeeting);
	        	}
	        	MethodsMeeting.createNewListMeetings(request);
	        	
	        	request.getRequestDispatcher("WEB-INF/historyofmeetings.jsp").forward(request, response);		
			}		
		}		
	}
}
