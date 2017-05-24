package pl.salesmanagement.methods;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import pl.salesmanagement.model.AnnualReport;
import pl.salesmanagement.model.Client;
import pl.salesmanagement.model.HistoryOfMeeting;
import pl.salesmanagement.model.Meeting;
import pl.salesmanagement.model.MonthlyReport;
import pl.salesmanagement.model.User;
import pl.salesmanagement.reports.CreateListAnnualReport;
import pl.salesmanagement.reports.CreateListMonthlyReport;
import pl.salesmanagement.service.HistoryOfMeetingService;

public class MethodsHistoryOfMeeting {
	
	public static int METHOD_NOT_DESCRIPTIVE=1;
	public static int METHOD_DESCRIPTIVE=2;
	
	public static String formMoney(float money){
		String moneyString = String.valueOf(money);
		
		String[] placeNumbers= moneyString.split("\\.");
		
		int decimalPlace =0;
		try {
			decimalPlace = Integer.parseInt(placeNumbers[1]);
		} catch (NumberFormatException e) {}
		
		moneyString="";
		if(decimalPlace<=9){
			moneyString= placeNumbers[0]+","+placeNumbers[1]+"0";
		}
		else{
			moneyString= placeNumbers[0]+","+placeNumbers[1];
		}
		
		return moneyString;
	}
	
	public static HistoryOfMeeting findHistoryOfMeetingAfterId(HttpServletRequest request, long idHistoryParam) {
		
		HistoryOfMeeting historyOfMeetingEdit= new HistoryOfMeeting();
		
		List<HistoryOfMeeting> historiesAll= extractedAll(request);
					
		for (HistoryOfMeeting historyOfMeetingItem : historiesAll) {
			if(historyOfMeetingItem.getIdHistoryMeeting()==idHistoryParam){
				historyOfMeetingEdit=historyOfMeetingItem;
			}
		}
		return historyOfMeetingEdit;
	}
	
	public static Client findClientAfterId(HttpServletRequest request, long idHistory, long idClient) {
		Client clientEdit= new Client();
		
		List<HistoryOfMeeting> historiesAll= extractedAll(request);
		
		for (HistoryOfMeeting historyOfMeeting : historiesAll) {
			if(historyOfMeeting.getIdHistoryMeeting()==idHistory){
				if(historyOfMeeting.getClient().getIdClient()==idClient){
					clientEdit=historyOfMeeting.getClient();
				}
			}
		}
		
		return clientEdit;
	}
	
	public static Meeting findMeetingAfterId(HttpServletRequest request, long idHistory, long idMeeting) {
		Meeting meetingEdit= new Meeting();
		
		List<HistoryOfMeeting> historiesAll= extractedAll(request);
		
		for (HistoryOfMeeting historyOfMeeting : historiesAll) {
			if(historyOfMeeting.getIdHistoryMeeting()==idHistory){
				if(historyOfMeeting.getMeeting().getIdMeeting()==idMeeting){
					meetingEdit=historyOfMeeting.getMeeting();
				}
			}
		}
		
		return meetingEdit;
	}
	
	public static HistoryOfMeeting findNextHistory(HttpServletRequest request) {
		HistoryOfMeeting historyNext=null;
		List<HistoryOfMeeting> historiesNotDescriptive= extractedNotDecriptive(request);	
		
		if(!historiesNotDescriptive.isEmpty()){
			historyNext=historiesNotDescriptive.get(0);
		}
		
		return historyNext;
	}
	
	private static List<HistoryOfMeeting> extractedAll(HttpServletRequest request){
		List<HistoryOfMeeting> historiesAll= new ArrayList<>();
		
		List<HistoryOfMeeting> historiesDescriptive= extractedDecriptive(request);	
		for (HistoryOfMeeting historyOfMeeting : historiesDescriptive) {
			historiesAll.add(historyOfMeeting);
		}
		List<HistoryOfMeeting> historiesNotDescriptive= extractedNotDecriptive(request);			
		for (HistoryOfMeeting historyOfMeeting : historiesNotDescriptive) {
			historiesAll.add(historyOfMeeting);
		}

		
		return historiesAll;
	}
	
	@SuppressWarnings("unchecked")
	private static List<HistoryOfMeeting> extractedDecriptive(HttpServletRequest request) {
		return (List<HistoryOfMeeting>) request.getSession().getAttribute("historydescriptive");
	}
	
	@SuppressWarnings("unchecked")
	private static List<HistoryOfMeeting> extractedNotDecriptive(HttpServletRequest request) {
		return (List<HistoryOfMeeting>) request.getSession().getAttribute("historynotdescriptive");
	}
	
	public static List<HistoryOfMeeting> createListHistoryOfMeetings(HttpServletRequest request, int method) {
		User user= (User) request.getSession().getAttribute("user");
		List<HistoryOfMeeting> historyOfMeetings=null;

		if(method==METHOD_DESCRIPTIVE){
			historyOfMeetings= HistoryOfMeetingService.getAllHistoryOfMeetings(user.getIdUser(), MethodsHistoryOfMeeting.METHOD_DESCRIPTIVE);
			request.getSession().setAttribute("historydescriptive", historyOfMeetings);
			
			CreateListMonthlyReport clmr= new CreateListMonthlyReport(historyOfMeetings);
    		List<MonthlyReport> listMonthlyReporte= clmr.getList();
    		    		
    		CreateListAnnualReport clar= new CreateListAnnualReport(listMonthlyReporte);
    		List<AnnualReport> listAnnualReport= clar.getList();
    		    		
    		request.getSession().setAttribute("listmonthlyreport", listMonthlyReporte);
    		request.getSession().setAttribute("listannualreport", listAnnualReport);		
		}
		else if(method==METHOD_NOT_DESCRIPTIVE){
			historyOfMeetings= HistoryOfMeetingService.getAllHistoryOfMeetings(user.getIdUser(), MethodsHistoryOfMeeting.METHOD_NOT_DESCRIPTIVE);
			request.getSession().setAttribute("historynotdescriptive", historyOfMeetings);
		}
		
		return historyOfMeetings;
	}

	public static void createNewListHistoryOfMeetings(HttpServletRequest request, int method) {
		if(method==METHOD_DESCRIPTIVE){
			if(request.getSession().getAttribute("historydescriptive")!=null){
				request.getSession().removeAttribute("historydescriptive");
				System.out.println("Lista spotkań historycznych z opisem została usunięta.");
			}
			if(request.getSession().getAttribute("listmonthlyreport")!=null){
				request.getSession().removeAttribute("listmonthlyreport");
				System.out.println("Lista raportów miesięcznych została usunięta.");
			}
			if(request.getSession().getAttribute("listannualreport")!=null){
				request.getSession().removeAttribute("listannualreport");
				System.out.println("Lista raportów rocznych została usunięta.");
			}
			createListHistoryOfMeetings(request, METHOD_DESCRIPTIVE);
			
			System.out.println("Nowa lista spotkań historycznych z opisem została wygenerowana.");
			System.out.println("Nowa lista raportów miesięczny została utworzona");  
			System.out.println("Nowa lista raportów rocznych została utworzona");  
		}
		else if(method==METHOD_NOT_DESCRIPTIVE){
			if(request.getSession().getAttribute("historynotdescriptive")!=null){
				request.getSession().removeAttribute("historynotdescriptive");
				System.out.println("Lista spotkań historycznych bez opisu została usunięta.");
			}
			createListHistoryOfMeetings(request, METHOD_NOT_DESCRIPTIVE);
			System.out.println("Nowa lista spotkań historycznych bez opisu została wygenerowana.");
		}
	}



}
