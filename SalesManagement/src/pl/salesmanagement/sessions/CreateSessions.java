package pl.salesmanagement.sessions;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import pl.salesmanagement.methods.MethodsClient;
import pl.salesmanagement.methods.MethodsHistoryOfMeeting;
import pl.salesmanagement.methods.MethodsMeeting;
import pl.salesmanagement.model.ClientModelForm;
import pl.salesmanagement.model.HistoryOfMeeting;
import pl.salesmanagement.model.Meeting;
import pl.salesmanagement.model.User;
import pl.salesmanagement.service.HistoryOfMeetingService;
import pl.salesmanagement.service.MeetingService;
import pl.salesmanagement.service.UserService;

public class CreateSessions {
	
	@SuppressWarnings("deprecation")
	public static void create(HttpServletRequest request, User user){
		
		User userSession= UserService.getUserWithAccount(user.getIdUser());	    
      	request.getSession().setAttribute("user", userSession);
      	List<ClientModelForm> clients=null;
      	
        	if(request.getSession().getAttribute("clients")==null){
        		clients= MethodsClient.createListClients(request);        		
	            System.out.println("Lista klientów została wygenerowana.");
        	}
        	if(request.getSession().getAttribute("meetings")==null){
        		List<Meeting> meetings= MethodsMeeting.createListMeetings(request);
        		System.out.println("Lista spotkań została wygenerowana.");
        		
        		Date dateToday= new Date();
        		dateToday.setHours(0); dateToday.setMinutes(0); dateToday.setSeconds(0);
        		
        		Date dateTomorrow = new Date(dateToday.getTime() + (1000 * 60 * 60 * 24));
        		String months, days, monthsTommorow, daysTommorow=null;

        		if(dateToday.getMonth()<=9){
        			months="0"+(dateToday.getMonth()+1);
        		}
        		else{
        			months=""+(dateToday.getMonth()+1);
        		}
        		
        		if(dateTomorrow.getMonth()<=9){
        			monthsTommorow="0"+(dateTomorrow.getMonth()+1);
        		}
        		else{
        			monthsTommorow=""+(dateTomorrow.getMonth()+1);
        		}
        		
        	    Calendar calToday = Calendar.getInstance();
        	    calToday.setTime(dateToday);
        	    int daysInt = calToday.get(Calendar.DAY_OF_MONTH);
        	  
        		if(daysInt<=9){
        			days="0"+daysInt;
        		}
        		else{
        			days=""+daysInt;
        			
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
        		
        		String dataTodayString= (dateToday.getYear()+1900)+"-"+months+"-"+days;
        		String dataTommorowString= (dateTomorrow.getYear()+1900)+"-"+monthsTommorow+"-"+daysTommorow;

        		int counterMeetings=0;
        		
        		boolean isCheckedCounterMeeting=false;
        		
        		if("checked".equals(userSession.getAccount().getReminder())){
        			isCheckedCounterMeeting=true;
        		}
        		else{
        			isCheckedCounterMeeting=false;
        		}
        		
        		for (int i=0; i<meetings.size(); i++) {
					Date dateMeeting = meetings.get(i).getDate();
					
					if(dateMeeting.before(dateToday) && (!dataTodayString.equals(dateMeeting.toString()))){
						MeetingService.updateMeetingChangeActive(meetings.get(i).getIdMeeting());
						HistoryOfMeeting history= new HistoryOfMeeting(meetings.get(i));
						HistoryOfMeetingService.addHistoryOfMeeting(history);

						meetings.remove(i);
						i--;
						System.out.println("Wykryto spotkanie, które uległo przedawnieniu. Przypisano je do spotkania historycznego.");

					}
				
					if(isCheckedCounterMeeting){
						if(dataTommorowString.equals(dateMeeting.toString())){
							counterMeetings++;
						}
					}
					
				}
        		request.getSession().setAttribute("counterMeetings", counterMeetings);
        		
	        	List<HistoryOfMeeting> listHistoryNotDescriptive= MethodsHistoryOfMeeting.createListHistoryOfMeetings(request, MethodsHistoryOfMeeting.METHOD_NOT_DESCRIPTIVE);
	       		System.out.println("Lista spotkań historycznych bez opisu została wygenerowana.");
	        	
	       		List<HistoryOfMeeting> listHistoryDescriptive= MethodsHistoryOfMeeting.createListHistoryOfMeetings(request, MethodsHistoryOfMeeting.METHOD_DESCRIPTIVE);
	       		System.out.println("Lista spotkań historycznych z opisem została wygenerowana.");     
	       		System.out.println("Lista raportów miesięcznych została wygenerowana.");  
	       		System.out.println("Lista raportów rocznych została wygenerowana.");      
	       		
	       		MethodsClient.updateListClient(request, clients, meetings, listHistoryNotDescriptive, listHistoryDescriptive);
	        }
        	
	}


	
}
