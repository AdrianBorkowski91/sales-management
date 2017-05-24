package pl.salesmanagement.methods;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import pl.salesmanagement.model.Meeting;
import pl.salesmanagement.model.User;
import pl.salesmanagement.service.MeetingService;

public class MethodsMeeting {
	
	@SuppressWarnings("deprecation")
	public static Time createTime(String timeString) {
		String[] timeSplit= timeString.split("[\u003A]");
		int hours=0;
		try {
			hours= Integer.parseInt(timeSplit[0]);
		} catch (NumberFormatException e) {}
		
		int minutes=0;
		try {
			minutes= Integer.parseInt(timeSplit[1]);
		} catch (NumberFormatException e) {}
		
		Time time = new Time(hours, minutes, 0);
		return time;
	}
	
	public static boolean reserachMeetingEmpty(String dateString, String timeStartString, String timeEndString, long idClient,
			long idGoal) {
		if(!dateString.equals("")){
			return false;
		}
		if(!timeStartString.equals("8:00")){
			return false;
		}
		if(!timeEndString.equals("9:00")){
			return false;
		}
		if(idClient!=0){
			return false;
		}
		if(idGoal!=0){
			return false;
		}
		return true;
	}

	public static String formTime(int time){
		String timeString = String.valueOf(time);
		
		if(time<=9){
			timeString= "0"+timeString;
		}
		
		return timeString;
	}
	
	public static Meeting findMeetingAfterId(HttpServletRequest request, long idMeeting) {
		Meeting meetingEdit= new Meeting();
		
		List<Meeting> meetings=  extracted(request);
		for (Meeting meeting : meetings) {
			if(meeting.getIdMeeting()==idMeeting){
				meetingEdit=meeting;
			}
		}
		return meetingEdit;
	}
	
	@SuppressWarnings("unchecked")
	private static List<Meeting> extracted(HttpServletRequest request) {
		return (List<Meeting>) request.getSession().getAttribute("meetings");
	}
	
	public static List<Meeting> createListMeetings(HttpServletRequest request) {
		User user= (User) request.getSession().getAttribute("user");
		List<Meeting> meetings= MeetingService.getAllMeetings(user.getIdUser());
		request.getSession().setAttribute("meetings", meetings);
		
		return meetings;
	}
	
	@SuppressWarnings("deprecation")
	public static void createCounterMeetings(HttpServletRequest request, Meeting meetings, User user){

    		boolean isCheckedCounterMeeting=false;
    		
    		if("checked".equals(user.getAccount().getReminder())){
    			isCheckedCounterMeeting=true;
    		}
    		else{
    			isCheckedCounterMeeting=false;
    		}
    		
        	Date dateToday= new Date();
        	dateToday.setHours(0); dateToday.setMinutes(0); dateToday.setSeconds(0);
        		
       		Date dateTomorrow = new Date(dateToday.getTime() + (1000 * 60 * 60 * 24));
       		String monthsTommorow, daysTommorow=null;
        		
       		if(dateTomorrow.getMonth()<=9){
       			monthsTommorow="0"+(dateTomorrow.getMonth()+1);
       		}
       		else{
       			monthsTommorow=""+(dateTomorrow.getMonth()+1);
       		}

            Calendar calTomorrow = Calendar.getInstance();
       	    calTomorrow.setTime(dateTomorrow);
       	    int daysTomorrowInt = calTomorrow.get(Calendar.DAY_OF_MONTH);
       	    
       		if(daysTomorrowInt<=9){
       			daysTommorow="0"+daysTomorrowInt;       			
       		}
        	else{
        		daysTommorow=""+daysTomorrowInt;
       		}
        		
       		String dataTommorowString= (dateTomorrow.getYear()+1900)+"-"+monthsTommorow+"-"+daysTommorow;
       		
			if(isCheckedCounterMeeting){

				if(dataTommorowString.equals(meetings.getDate().toString())){
					int counterMeetings= (int) request.getSession().getAttribute("counterMeetings");
					request.getSession().removeAttribute("counterMeetings");
					counterMeetings++;
					request.getSession().setAttribute("counterMeetings", counterMeetings);
				}
			}		
	}

	public static void createNewListMeetings(HttpServletRequest request) {
		if(request.getSession().getAttribute("meetings")!=null){
			request.getSession().removeAttribute("meetings");
			System.out.println("Stara lista spotkań została usunięta");
		}
		createListMeetings(request);
		System.out.println("Nowa lista spotkań została wygenerowana.");
	}

}
