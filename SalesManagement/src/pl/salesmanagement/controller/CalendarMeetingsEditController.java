package pl.salesmanagement.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.salesmanagement.conditions.ConditionsMeeting;
import pl.salesmanagement.methods.MethodsClient;
import pl.salesmanagement.methods.MethodsMeeting;
import pl.salesmanagement.model.Client;
import pl.salesmanagement.model.Goal;
import pl.salesmanagement.model.Meeting;
import pl.salesmanagement.service.MeetingService;

@WebServlet("/calendarmeetings-edit")
public class CalendarMeetingsEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CalendarMeetingsEditController() {
        super();   
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("meeting")!=null){
			Meeting meeting= (Meeting) request.getSession().getAttribute("meeting");
			request.getSession().removeAttribute("meeting");
			request.setAttribute("meeting", meeting);
			
			if(request.getSession().getAttribute("messageAboutAccept")!=null){
				String messageAboutAccept= (String) request.getSession().getAttribute("messageAboutAccept");
				request.getSession().removeAttribute("messageAboutAccept");
				request.setAttribute("messageAboutAccept", messageAboutAccept);
			}
			
			request.getRequestDispatcher("WEB-INF/calendarmeetings-edit.jsp").forward(request, response);	    
		}
		else if(request.getSession().getAttribute("user")!=null){
			response.sendRedirect("calendarmeetings");
		}
		else{
			response.sendRedirect("login");  
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idClientString= request.getParameter("id-client");
		long idClientParam=0;
		try {
			idClientParam = Long.parseLong(idClientString);
		} catch (NumberFormatException e1) {}
		
		String idMeetingString= request.getParameter("id-meeting");
		long idMeetingParam=0;
		try {
			idMeetingParam = Long.parseLong(idMeetingString);
		} catch (NumberFormatException e1) {}
		
		String dateString= request.getParameter("date");
		DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = null;
		java.sql.Date sqlDate = null;
		try {
			date = formatDate.parse(dateString);
			sqlDate = new Date(date.getTime()); 
		} catch (ParseException e) {}
		
		String timeStartString= request.getParameter("time-start");
		Time timeStart = MethodsMeeting.createTime(timeStartString);

		String timeEndString= request.getParameter("time-end");
		Time timeEnd = MethodsMeeting.createTime(timeEndString);
		
		String goalString= request.getParameter("goal");
		long idGoal= Goal.findTheGoalNameAfterName(goalString);
		
		String description= request.getParameter("description");
		
		String button= request.getParameter("button");
		String paramsTab[]= button.split("\\s+");
		Client client= MethodsClient.findClientAfterId(request, idClientParam);
		
		if(button.equals("accept")){
			
			String method= request.getParameter("accept-method");
			
			if(method.equals("edit")){
				String idMeetingStringReturn= request.getParameter("return-id-meeting");
				long idMeeting=0;

				try {
					idMeeting= Long.parseLong(idMeetingStringReturn);
				} catch (NumberFormatException e) {}
						
				Meeting meetingResult= MethodsMeeting.findMeetingAfterId(request, idMeeting);
				request.setAttribute("meeting", meetingResult);
		        
				request.getRequestDispatcher("WEB-INF/calendarmeetings-edit.jsp").forward(request, response);
			}
			else if(method.equals("calendar")){
				response.sendRedirect("calendarmeetings"); 
			}
			else if(method.equals("new")){
				response.sendRedirect("calendarmeetings-new"); 
			}
			else if(method.equals("edit-client")){	
				request.getSession().setAttribute("client", client);
				response.sendRedirect("myclients-edit"); 
			}
		}
		
		Meeting meeting= new Meeting(idMeetingParam, sqlDate, timeStart, timeEnd, idGoal, description, client);
		Meeting meetingPattern = MethodsMeeting.findMeetingAfterId(request, idMeetingParam);
		
		if(paramsTab[0].equals("edit-meeting")){
			
			long idMeeting=0;
			
			try {
				idMeeting= Long.parseLong(paramsTab[1]);
			} catch (NumberFormatException e) {}
			

			if(meeting.equals(meetingPattern)){						
				Meeting meetingResult =MethodsMeeting.findMeetingAfterId(request, idMeeting);
				request.setAttribute("meeting", meetingResult);
				
				request.getRequestDispatcher("WEB-INF/calendarmeetings-edit.jsp").forward(request, response);
			}
			else{						
				request.setAttribute("returnIdMeeting", idMeeting);
				
				request.setAttribute("meeting", meeting);
				request.setAttribute("modal", "modal");
				request.setAttribute("method", "edit");
				
				request.getRequestDispatcher("WEB-INF/calendarmeetings-edit.jsp").forward(request, response);
			}
		}
		if(button.equals("return")){
			if(meeting.equals(meetingPattern)){
				response.sendRedirect("calendarmeetings"); 
			}
			else{	
				request.setAttribute("meeting", meeting);
				request.setAttribute("modal", "modal");
				request.setAttribute("method", "calendar");
				
				request.getRequestDispatcher("WEB-INF/calendarmeetings-edit.jsp").forward(request, response);
			}
		}
		else if(button.equals("save")){
			if(meeting.equals(meetingPattern)){
				request.setAttribute("meeting", meeting);
				
				request.getRequestDispatcher("WEB-INF/calendarmeetings-edit.jsp").forward(request, response);
			}
			else{
				if(ConditionsMeeting.researchFormCreateMeeting(request, idClientParam, idGoal, sqlDate, timeStart, timeEnd, meetingPattern.getIdMeeting())){		
					request.setAttribute("meeting", meeting);
					
					request.getRequestDispatcher("WEB-INF/calendarmeetings-edit.jsp").forward(request, response);
				}
				else{		
					Meeting meetingWithId= MeetingService.updateMeeting(meeting, meetingPattern);
					MethodsMeeting.createNewListMeetings(request);
					
					request.setAttribute("meeting", meetingWithId);
					request.setAttribute("messageAboutAccept", ConditionsMeeting.MEETING_MESSAGE_ACCEPT_TO_EDIT2);
					
					request.getRequestDispatcher("WEB-INF/calendarmeetings-edit.jsp").forward(request, response);	
				}					
			}
		}
		else if(button.equals("edit-client")){
			if(meeting.equals(meetingPattern)){	
				request.getSession().setAttribute("client", client);
				response.sendRedirect("myclients-edit"); 
			}
			else{	
				request.setAttribute("meeting", meeting);
				request.setAttribute("modal", "modal");
				request.setAttribute("method", "edit-client");
				
				request.getRequestDispatcher("WEB-INF/calendarmeetings-edit.jsp").forward(request, response);	
			}			
		}
		else if(button.equals("new")){
			if(meeting.equals(meetingPattern)){
				response.sendRedirect("calendarmeetings-new"); 
			}
			else{		
				request.setAttribute("meeting", meeting);
				request.setAttribute("modal", "modal");
				request.setAttribute("method", "new");
			
				request.getRequestDispatcher("WEB-INF/calendarmeetings-edit.jsp").forward(request, response);
			}
			
		}
	}

}
