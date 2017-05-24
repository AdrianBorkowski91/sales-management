package pl.salesmanagement.reports;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.salesmanagement.model.Effect;
import pl.salesmanagement.model.Goal;
import pl.salesmanagement.model.HistoryOfMeeting;
import pl.salesmanagement.model.MonthlyReport;

public class CreateListMonthlyReport {
	
	private List<HistoryOfMeeting> historyOfMeetings;
	
	public CreateListMonthlyReport(List<HistoryOfMeeting> historyOfMeetings) {
		this.historyOfMeetings= historyOfMeetings;
	}
	
	public List<MonthlyReport> getList() {
		
		List<MonthlyReport> listMonthlyReport= new ArrayList<MonthlyReport>();
		
		Map<String, List<HistoryOfMeeting>> mapMonthlyReport = getMapHistory(historyOfMeetings);
		
		for (Map.Entry<String, List<HistoryOfMeeting>> entry : mapMonthlyReport.entrySet()){
			String formatDateToSelect= entry.getKey();
			int togetherMeetings= entry.getValue().size();
			int togetherMeetingsTook=0;
			float allRotationWithMeetings=0;
			float averageRotationWithMeetings=0;
			
			float togetherAgreement=0;
			float averageRotationWithAgreement=0;
			
			float rotationTheBestClient=0;
			String theBestClient=null;
			
			Map<Long, Integer> goalList= getGoalList();
			Map<Long, Integer> effectList= getEffectList();
			
			for (HistoryOfMeeting historyOfMeeting : entry.getValue()) {

				if(historyOfMeeting.getIdEffect()!=1){
					togetherMeetingsTook+=1;
				}
				
				allRotationWithMeetings+=historyOfMeeting.getRotation();
				
				if(historyOfMeeting.getIdEffect()==4){
					togetherAgreement+=1;
				}
				
				if(historyOfMeeting.getRotation()>rotationTheBestClient){
					rotationTheBestClient=historyOfMeeting.getRotation();
					theBestClient="";
					theBestClient=historyOfMeeting.getClient().getFirstname()+" "+ historyOfMeeting.getClient().getLastname();
				}
				
				if(goalList.containsKey(historyOfMeeting.getMeeting().getIdGoal())){
					int valueNew= goalList.get(historyOfMeeting.getMeeting().getIdGoal());
					valueNew=valueNew+1;
					goalList.put(historyOfMeeting.getMeeting().getIdGoal(), valueNew);
				}
				
				if(effectList.containsKey(historyOfMeeting.getIdEffect())){
					int valueNew= effectList.get(historyOfMeeting.getIdEffect());
					valueNew=valueNew+1;
					effectList.put(historyOfMeeting.getIdEffect(), valueNew);
				}
				
			}
			averageRotationWithMeetings= allRotationWithMeetings/togetherMeetingsTook;
			averageRotationWithAgreement= allRotationWithMeetings/togetherAgreement;
			
			if(averageRotationWithAgreement==Float.NEGATIVE_INFINITY || averageRotationWithAgreement==Float.POSITIVE_INFINITY){
				averageRotationWithAgreement=0;
			}
			
			if(rotationTheBestClient==0){
				theBestClient="Brak";
			}
			
			
			MonthlyReport mr= new MonthlyReport(formatDateToSelect, togetherMeetings, togetherMeetingsTook, allRotationWithMeetings, averageRotationWithMeetings,
					averageRotationWithAgreement, theBestClient, rotationTheBestClient, goalList, effectList);
			listMonthlyReport.add(mr);
			
		}
		List<MonthlyReport> listMonthlyReport2= new ArrayList<MonthlyReport>();
		
		for(int i=listMonthlyReport.size()-1; i>=0; i--){
			listMonthlyReport2.add(listMonthlyReport.get(i));
		}
	
		return listMonthlyReport2;
	}
	
	@SuppressWarnings("deprecation")
	private Map<String, List<HistoryOfMeeting>> getMapHistory(List<HistoryOfMeeting> historyOfMeetings){
		Map<String, List<HistoryOfMeeting>> mapMonthlyReport = new HashMap<String, List<HistoryOfMeeting>>();
		
		for (int i=0; i<historyOfMeetings.size(); i++) {
		
			Date dateMeeting= historyOfMeetings.get(i).getMeeting().getDate();
			Date dateStart= new Date(dateMeeting.getYear(), dateMeeting.getMonth(), 1);
			Date dateEnd= new Date(dateMeeting.getYear(), dateMeeting.getMonth(), daysInMonth(dateMeeting));
			
			if((dateMeeting.after(dateStart) || dateStart.equals(dateMeeting)) && (dateMeeting.before(dateEnd) || dateEnd.equals(dateMeeting))){
				String formatDateToSelect="20"+(dateMeeting.getYear()-100)+"."+monthFormat(dateMeeting);
				
				if(!mapMonthlyReport.containsKey(formatDateToSelect) || mapMonthlyReport.isEmpty()){
					List<HistoryOfMeeting> values = new ArrayList<HistoryOfMeeting>();
					values.add(historyOfMeetings.get(i));
					
					for(int j=i+1; j<historyOfMeetings.size(); j++){
						
						Date dateMeetingTwo= historyOfMeetings.get(j).getMeeting().getDate();	
						Date dateStartTwo= new Date(dateMeetingTwo.getYear(), dateMeetingTwo.getMonth(), 1);
						Date dateEndTwo= new Date(dateMeetingTwo.getYear(), dateMeetingTwo.getMonth(), daysInMonth(dateMeetingTwo));
						
						if((dateMeetingTwo.after(dateStartTwo) || dateStartTwo.equals(dateMeetingTwo)) && (dateMeetingTwo.before(dateEndTwo) || dateEndTwo.equals(dateMeetingTwo))){
							String formatDateToSelectTwo="20"+(dateMeetingTwo.getYear()-100)+"."+monthFormat(dateMeetingTwo);
							
							if(formatDateToSelect.equals(formatDateToSelectTwo)){
								values.add(historyOfMeetings.get(j));
							}
						}	
					}
					mapMonthlyReport.put(formatDateToSelect, values);	
				}	
			}
		}
		return mapMonthlyReport;		
	}
	
	private Map<Long, Integer> getGoalList(){
		Map<Long, Integer> goalList= new HashMap<>();
		
		goalList.put((long) Goal.NIEOKRESLONE.getIdGoal(), 0);
		goalList.put((long) Goal.PODPISANIE_UMOWY.getIdGoal(), 0);
		goalList.put((long) Goal.PREZENTACJA_HANDLOWA.getIdGoal(), 0);
		goalList.put((long) Goal.WSTEPNA_ROZMOWA.getIdGoal(), 0);
		
		return goalList;
	}
	
	private Map<Long, Integer> getEffectList(){		
		Map<Long, Integer> effectList= new HashMap<>();
		
		effectList.put((long) Effect.KOLEJNE_SPOTKANIE.getIdEffect(), 0);
		effectList.put((long) Effect.PODPISANA_UMOWA.getIdEffect(), 0);
		effectList.put((long) Effect.SPOTKANIE_BEZ_EFEKTU.getIdEffect(), 0);
		effectList.put((long) Effect.SPOTKANIE_NIEODBYTE.getIdEffect(), 0);
		
		return effectList;
	}
	
	@SuppressWarnings("deprecation")
	private String monthFormat(Date dateMeeting) {
		String month=null;
		if(dateMeeting.getMonth()<=9){
			month="0"+(dateMeeting.getMonth()+1);
		}
		else{
			month=""+(dateMeeting.getMonth()+1);
		}
		return month;
	}

	@SuppressWarnings("deprecation")
	private int daysInMonth(Date dateMeeting) {	
		int year = dateMeeting.getYear();
		int month = dateMeeting.getMonth();
		int day = 1;
		
		Calendar mycal = new GregorianCalendar(year, month, day);
		int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
		return daysInMonth;
	}

}
