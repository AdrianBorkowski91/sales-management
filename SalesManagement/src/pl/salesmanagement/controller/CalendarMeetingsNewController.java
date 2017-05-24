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
import pl.salesmanagement.model.User;
import pl.salesmanagement.service.MeetingService;

@WebServlet("/calendarmeetings-new")
public class CalendarMeetingsNewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CalendarMeetingsNewController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("user")!=null){
			request.getRequestDispatcher("WEB-INF/calendarmeetings-new.jsp").forward(request, response);	 
		}
		else{
			response.sendRedirect("login");  
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String button= request.getParameter("button");
		
        if(button.equals("accept")){
        	response.sendRedirect("calendarmeetings");
        }
        
        User user= (User) request.getSession().getAttribute("user");
        long idUser= user.getIdUser();
        
		String client= request.getParameter("client");
		long idClient=0;
		try {
			idClient = Long.parseLong(client);
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
		String timeEndString= request.getParameter("time-end");
		
		Time timeStart = MethodsMeeting.createTime(timeStartString);
		Time timeEnd = MethodsMeeting.createTime(timeEndString);
		
		String goalString= request.getParameter("goal");
		String description= request.getParameter("description");
		
		long idGoal= Goal.findTheGoalNameAfterName(goalString);
		Meeting meeting= new Meeting(idUser, idClient, sqlDate, timeStart, timeEnd, idGoal, description);
		
		if(button.equals("return")){
			if(MethodsMeeting.reserachMeetingEmpty(dateString, timeStartString, timeEndString, idClient, idGoal)){
				response.sendRedirect("calendarmeetings");
			}
			else{
				request.setAttribute("meeting", meeting);
				request.setAttribute("modal", "modal");
				request.getRequestDispatcher("WEB-INF/calendarmeetings-new.jsp").forward(request, response);
			}
		}
		else if(button.equals("save")){
			if(ConditionsMeeting.researchFormCreateMeeting(request, idClient, idGoal, sqlDate, timeStart, timeEnd, 0)){
				request.setAttribute("meeting", meeting);
				request.getRequestDispatcher("WEB-INF/calendarmeetings-new.jsp").forward(request, response);
			}
			else{	
				Client clientResult= MethodsClient.findClientAfterId(request, idClient);
				meeting.setClient(clientResult);
				
				Meeting meetingWithId= MeetingService.addMeeting(meeting);
				request.getSession().setAttribute("meeting", meetingWithId);
				
				MethodsMeeting.createCounterMeetings(request, meetingWithId, user);
				MethodsMeeting.createNewListMeetings(request);
				MethodsClient.modificationObject(request, MethodsClient.MEETING_OBJECT, MethodsClient.ADD_METHOD, meetingWithId.getIdMeeting());
				
				request.getSession().setAttribute("messageAboutAccept", ConditionsMeeting.MEETING_MESSAGE_ACCEPT_TO_EDIT);
	        	response.sendRedirect("calendarmeetings-edit");  
			}
		}		

	}

}
