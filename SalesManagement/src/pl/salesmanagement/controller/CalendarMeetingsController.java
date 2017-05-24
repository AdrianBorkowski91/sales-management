package pl.salesmanagement.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.salesmanagement.methods.MethodsClient;
import pl.salesmanagement.methods.MethodsHistoryOfMeeting;
import pl.salesmanagement.methods.MethodsMeeting;
import pl.salesmanagement.model.HistoryOfMeeting;
import pl.salesmanagement.model.Meeting;
import pl.salesmanagement.service.MeetingService;

@WebServlet("/calendarmeetings")
public class CalendarMeetingsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CalendarMeetingsController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("user")!=null){
			request.getRequestDispatcher("WEB-INF/calendarmeetings.jsp").forward(request, response);	 
		}
		else{
			response.sendRedirect("login");  
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("paramEventMeeting")!=null && (!request.getParameter("paramEventMeeting").equals(""))){
			String idMeetingInCalendarString= request.getParameter("paramEventMeeting");
			
			long idMeetingInCalendar=0;
			try{
				idMeetingInCalendar= Long.parseLong(idMeetingInCalendarString);
			}catch(NullPointerException e){}
			
			Meeting meetingResultInCalendar=null;
	        if(idMeetingInCalendar!=0){
	        	meetingResultInCalendar = MethodsMeeting.findMeetingAfterId(request, idMeetingInCalendar);
	        }
	        
	        request.getSession().setAttribute("meeting", meetingResultInCalendar);
        	response.sendRedirect("calendarmeetings-edit");  	
		}
		else if(request.getParameter("paramEventHistoryNotDescriptive")!=null && (!request.getParameter("paramEventHistoryNotDescriptive").equals(""))){
			String idHistoryNotDescriptive= request.getParameter("paramEventHistoryNotDescriptive");
			long idHistory=0;
			
			try{
				idHistory= Long.parseLong(idHistoryNotDescriptive);
			}catch(NullPointerException e){}
			
			HistoryOfMeeting history=null;
			if(idHistory!=0){
				history= MethodsHistoryOfMeeting.findHistoryOfMeetingAfterId(request, idHistory);
			}
			
			request.getSession().setAttribute("history", history);
			response.sendRedirect("historyofmeetings-edit");
		}
		else if(request.getParameter("paramEventHistoryDescriptive")!=null && (!request.getParameter("paramEventHistoryDescriptive").equals(""))){
			String idHistoryDescriptive= request.getParameter("paramEventHistoryDescriptive");
			long idHistory=0;
			
			try{
				idHistory= Long.parseLong(idHistoryDescriptive);
			}catch(NullPointerException e){}
			
			HistoryOfMeeting history=null;
			if(idHistory!=0){
				history= MethodsHistoryOfMeeting.findHistoryOfMeetingAfterId(request, idHistory);
			}
			
			request.getSession().setAttribute("history", history);
			response.sendRedirect("historyofmeetings-preview");
		}
		else{
			String button= request.getParameter("button");
			
	        String[] paramsTab= button.split("\\s+");
	        
	        long idMeeting=0;
	        try {
	        	idMeeting= Long.parseLong(paramsTab[1]);
			} catch (Exception e) {}
	        
	        Meeting meetingResult=null;
	        if(idMeeting!=0){
	        	meetingResult = MethodsMeeting.findMeetingAfterId(request, idMeeting);
	        }
	        
			if(button.equals("new")){
				if(request.getParameter("date-input")!=null || (!request.getParameter("date-input").equals(""))){
					DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
					java.util.Date date = null;
					java.sql.Date sqlDate = null;
					
					try {
					date = formatDate.parse(request.getParameter("date-input"));
					sqlDate = new Date(date.getTime()); 
					} catch (ParseException e) {}
					
					Meeting meeting= new Meeting();
					meeting.setDate(sqlDate);
					
					request.getSession().setAttribute("meeting", meeting);
				}
			response.sendRedirect("calendarmeetings-new");
			}
			else if(paramsTab[0].equals("edit")){	
				request.getSession().setAttribute("meeting", meetingResult);	
	        	response.sendRedirect("calendarmeetings-edit");  	
			}
			else if(paramsTab[0].equals("delete")){
	        	if(MeetingService.deleteMeeting(idMeeting)){
	        		MethodsClient.modificationObject(request, MethodsClient.MEETING_OBJECT, MethodsClient.DELETE_METHOD, idMeeting);
	        		System.out.println("Spotkanie usunięte o id: "+idMeeting);
	        	}
	        	MethodsMeeting.createNewListMeetings(request);
				request.getRequestDispatcher("WEB-INF/calendarmeetings.jsp").forward(request, response);	
			}
		}
	}

}
