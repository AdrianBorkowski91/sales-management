package pl.salesmanagement.conditions;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import pl.salesmanagement.methods.MethodsMeeting;
import pl.salesmanagement.model.Meeting;

public class ConditionsMeeting {
	
	private static List<String> emptyParam= new ArrayList<String>();
	
	private static String TIME_TO_MESSAGE=null;
	
	private static final String MEETING_MESSAGE1="-Pola oznaczone gwiazdką muszą być uzupełnione.";
	private static final String MEETING_MESSAGE2="-Czas rozpoczęcia spotkania nie może być większy lub taki sam jak czas zakończenia.";
	private static final String FORMAT_MEETING_MESSAGE3= "-Zmień godziny spotkania. Istnieje już spotkanie, które ma się odbyć w godzinach %s.";
	public static final String MEETING_MESSAGE_ACCEPT_TO_EDIT = "Operacja zakończona pomyślnie- spotkanie zostało zapisane. "
			+ "Zostałeś przekierowany do trybu edycji.";
	public static final String MEETING_MESSAGE_ACCEPT_TO_EDIT2 = "Operacja zakończona pomyślnie- spotkanie zostało zapisane. ";
		
	public static boolean researchFormCreateMeeting(HttpServletRequest request, long idClient, long idGoal, Date date, Time timeStart, Time timeEnd, long idMeeting) throws ServletException, IOException {
		String message0= researchIsMessage(request, idClient, idGoal, date, timeStart, timeEnd, idMeeting);

		if(message0!=null){
            return true;
		}
        return false;
	}
	
	private static String researchIsMessage(HttpServletRequest request, long idClient, long idGoal, Date date, Time timeStart, Time timeEnd, long idMeeting) {
		if(!emptyParam.isEmpty()){
			emptyParam.clear();
		}
		
		String message0=null;
		
		if(idClient==0 || idGoal==0){
            request.setAttribute("message1", MEETING_MESSAGE1);
            message0= messageHead(request, message0);
            
            if(idGoal==0){
            	emptyParam.add("goal");
            }
            if(idClient==0){
            	emptyParam.add("client");
            }
		}
		if(researchTimeMeetingFormat(timeStart, timeEnd)==true){
			request.setAttribute("message2", MEETING_MESSAGE2);
			message0= messageHead(request, message0);
			
			emptyParam.add("timepickerP");
			emptyParam.add("timepickerZ");
		}
		if(researchTimeMeetingRepeat(request, date, timeStart, timeEnd, idMeeting)==true){
			String MEETING_MESSAGE3= String.format(FORMAT_MEETING_MESSAGE3, TIME_TO_MESSAGE);
			request.setAttribute("message3", MEETING_MESSAGE3);
			message0= messageHead(request, message0);
			
			emptyParam.add("date");
			emptyParam.add("timepickerP");
			emptyParam.add("timepickerZ");
		}
		
		if(!emptyParam.isEmpty()){
			request.setAttribute("emptyParam", emptyParam);
		}
		
		return message0;
	}

	private static String messageHead(HttpServletRequest request, String message0) {
		if(message0==null){
			message0="Wystąpiły błędy w formularzu: ";
			request.setAttribute("message0", message0);
		}
		return message0;
	}
	
	@SuppressWarnings("deprecation")
	private static boolean researchTimeMeetingFormat(Time timeStart, Time timeEnd) {
		int hoursStart= timeStart.getHours();
		int minutesStart = timeStart.getMinutes();
		int hoursEnd= timeEnd.getHours();
		int minutesEnd= timeEnd.getMinutes();
		
		if(hoursStart<hoursEnd){
			return false;
		}
		else if(hoursStart==hoursEnd){
			if(minutesStart<minutesEnd){
				return false;
			}
			else
				return true;
		}
		return true;
	}
	
	@SuppressWarnings("deprecation")
	private static boolean researchTimeMeetingRepeat(HttpServletRequest request, Date date, Time timeStart,  Time timeEnd, long idMeeting) {
		
		List<Meeting> listMeetings=MethodsMeeting.createListMeetings(request);
		
	    Calendar calMeetingNew = Calendar.getInstance();
	    calMeetingNew.setTime(date);
	    int daysIntMeetingNew = calMeetingNew.get(Calendar.DAY_OF_MONTH);
	    
		if(date!=null){
			for (Meeting meeting : listMeetings) {
				if(idMeeting==0 || meeting.getIdMeeting()!=idMeeting){
					
	        	    Calendar calMeeting = Calendar.getInstance();
	        	    calMeeting.setTime(meeting.getDate());
	        	    int daysIntMeeting = calMeeting.get(Calendar.DAY_OF_MONTH);
					
					if(meeting.getDate().getYear()==date.getYear() && meeting.getDate().getMonth()==date.getMonth() && daysIntMeeting==daysIntMeetingNew){
						if(meeting.getTimeStart().before(timeStart) && meeting.getTimeEnd().getHours()>timeStart.getHours()){
							getTimeToMessage(meeting);
							return true;
						}
						if(timeStart.before(meeting.getTimeStart()) && timeEnd.after((meeting.getTimeStart()))){
							getTimeToMessage(meeting);
							return true;
						}
						if(timeStart.after(meeting.getTimeStart()) && timeStart.before(meeting.getTimeEnd())){
							getTimeToMessage(meeting);
							return true;
						}
						if(timeStart.getHours()==meeting.getTimeStart().getHours() && timeStart.getMinutes()==meeting.getTimeStart().getMinutes()){
							getTimeToMessage(meeting);
							return true;
						}
						if(timeEnd.getHours()==meeting.getTimeEnd().getHours() && timeEnd.getMinutes()==meeting.getTimeEnd().getMinutes()){
							getTimeToMessage(meeting);
							return true;
						}							
					}
				}
			}
		}
		else{
			emptyParam.add("date");
		}
		return false;
	}
	
	@SuppressWarnings("deprecation")
	private static void getTimeToMessage(Meeting meeting){
		String hoursStart= MethodsMeeting.formTime(meeting.getTimeStart().getHours());
		String hoursEnd= MethodsMeeting.formTime(meeting.getTimeEnd().getHours());
		String minutesStart= MethodsMeeting.formTime(meeting.getTimeStart().getMinutes());
		String minutesEnd= MethodsMeeting.formTime(meeting.getTimeEnd().getMinutes());
		
		TIME_TO_MESSAGE= hoursStart+":"+minutesStart+"-"+hoursEnd+":"+minutesEnd;	
	}


}
