package pl.salesmanagement.reports;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.salesmanagement.model.AnnualReport;
import pl.salesmanagement.model.MonthlyReport;

public class CreateListAnnualReport {

	private List<MonthlyReport> listMonthlyReport;
	
	public CreateListAnnualReport(List<MonthlyReport> listMonthlyReport) {
		this.listMonthlyReport= listMonthlyReport;
	}

	public List<AnnualReport> getList() {
		
		List<AnnualReport> listAnnualReport= new ArrayList<AnnualReport>();
		
		Map<String, List<MonthlyReport>> mapAnnualReport = getMapMonthlyReport(listMonthlyReport);
		
		for(Map.Entry<String, List<MonthlyReport>> entry : mapAnnualReport.entrySet()) {
			String formatDateToSelect= entry.getKey();
			int togetherMeetings= 0;
			int togetherMeetingsTook=0;
			float allRotationWithMeetings=0;
			float averageRotationWithMeetings=0;
			
			float togetherAgreement=0;
			float averageRotationWithAgreement=0;
			
			float rotationTheBestClient=0;
			String theBestClient=null;
			
			Map<Long, Float> rotationList= getRotationList();
			
			for (MonthlyReport monthlyReport : entry.getValue()) {
				togetherMeetings+=monthlyReport.getTogetherMeetings();
				togetherMeetingsTook+=monthlyReport.getTogetherMeetingsTook();
				allRotationWithMeetings+=monthlyReport.getAllRotationWithMeetings();
				
				togetherAgreement+=monthlyReport.getEffectList().get((long)4).intValue();
				
				if(monthlyReport.getRotationTheBestClient()>rotationTheBestClient){
					rotationTheBestClient=monthlyReport.getRotationTheBestClient();
					theBestClient="";
					theBestClient=monthlyReport.getTheBestClient();
				}
				
				String dateTable[]= monthlyReport.getFormatDateToSelect().split("\\.");
				long month= 0;
				try {
					month= Long.parseLong(dateTable[1]);
				} catch (NumberFormatException e) {}
				
				if(rotationList.containsKey(month)){
					rotationList.put(month, monthlyReport.getAllRotationWithMeetings());
				}
			}
			averageRotationWithMeetings= allRotationWithMeetings/togetherMeetingsTook;
			averageRotationWithAgreement= allRotationWithMeetings/togetherAgreement;
			
			rotationList= removeNullElements(rotationList);

			
			AnnualReport ar= new AnnualReport(formatDateToSelect, togetherMeetings, togetherMeetingsTook, allRotationWithMeetings, averageRotationWithMeetings,
					averageRotationWithAgreement, theBestClient, rotationTheBestClient, rotationList);

			listAnnualReport.add(ar);
		}
		
		List<AnnualReport> listAnnualReport2= new ArrayList<AnnualReport>();
		for(int i=listAnnualReport.size()-1; i>=0; i--){
			listAnnualReport2.add(listAnnualReport.get(i));
		}
		
		return listAnnualReport2;
	}


	private Map<Long, Float> removeNullElements(Map<Long, Float> rotationList) {
		Map<Long, Float> rotationListNewVersion= rotationList;
		int index=0;
		
		for(long i=1; i<=12; i++){
			if(rotationList.get(i)!=0){
				if(i!=rotationList.size()){
					if(rotationList.get(i+1)==0){
						index=(int)(i+1);
					}
				}
			}
		}
		
		for(long i=12; i>=index; i--){
			rotationListNewVersion.remove(i);
		}
		
		return rotationListNewVersion;
	}

	private Map<Long, Float> getRotationList() {
		Map<Long, Float> rotationList= new HashMap<>();
		
		rotationList.put( (long) 1, 0.0f);
		rotationList.put( (long) 2, 0.0f);
		rotationList.put( (long) 3, 0.0f);
		rotationList.put( (long) 4, 0.0f);
		rotationList.put( (long) 5, 0.0f);
		rotationList.put( (long) 6, 0.0f);
		rotationList.put( (long) 7, 0.0f);
		rotationList.put( (long) 8, 0.0f);
		rotationList.put( (long) 9, 0.0f);
		rotationList.put( (long) 10, 0.0f);
		rotationList.put( (long) 11, 0.0f);
		rotationList.put( (long) 12, 0.0f);
		
		return rotationList;
	}

	private Map<String, List<MonthlyReport>> getMapMonthlyReport(List<MonthlyReport> listMonthlyReport) {
		Map<String, List<MonthlyReport>> mapAnnualReport = new  HashMap<String, List<MonthlyReport>>();
		
		for (int i=0; i<listMonthlyReport.size(); i++) {		
			String dateTable[]= listMonthlyReport.get(i).getFormatDateToSelect().split("\\.");
			
			if(!mapAnnualReport.containsKey(dateTable[0]) || mapAnnualReport.isEmpty()){
				List<MonthlyReport> values = new ArrayList<MonthlyReport>();
				values.add(listMonthlyReport.get(i));
				
				for(int j=i+1; j<listMonthlyReport.size(); j++){
					String dateTableTwo[]= listMonthlyReport.get(j).getFormatDateToSelect().split("\\.");

					if(dateTable[0].equals(dateTableTwo[0])){
						values.add(listMonthlyReport.get(j));
					}
				}
				mapAnnualReport.put(dateTable[0], values);	
			}
		}
		return mapAnnualReport;
	}
}
