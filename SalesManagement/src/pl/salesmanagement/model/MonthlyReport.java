package pl.salesmanagement.model;

import java.util.Map;

public class MonthlyReport extends ReportModel{

	private Map<Long, Integer> goalList;
	private Map<Long, Integer> effectList;
	
	public MonthlyReport(String formatDateToSelect, int togetherMeetings, int togetherMeetingsTook,
			float allRotationWithMeetings, float averageRotationWithMeetings, float averageRotationWithAgreement,
			String theBestClient, float rotationTheBestClient, Map<Long, Integer> goalList,
			Map<Long, Integer> effectList) {
		super(formatDateToSelect, togetherMeetings, togetherMeetingsTook, allRotationWithMeetings,
				averageRotationWithMeetings, averageRotationWithAgreement, theBestClient, rotationTheBestClient);
		this.goalList = goalList;
		this.effectList = effectList;
	}

	public MonthlyReport(){}

	public Map<Long, Integer> getGoalList() {
		return goalList;
	}

	public void setGoalList(Map<Long, Integer> goalList) {
		this.goalList = goalList;
	}

	public Map<Long, Integer> getEffectList() {
		return effectList;
	}

	public void setEffectList(Map<Long, Integer> effectList) {
		this.effectList = effectList;
	}

	@Override
	public String toString() {
		return "MonthlyReport [goalList=" + goalList + ", effectList=" + effectList + ", formatDateToSelect="
				+ formatDateToSelect + ", togetherMeetings=" + togetherMeetings + ", togetherMeetingsTook="
				+ togetherMeetingsTook + ", allRotationWithMeetings=" + allRotationWithMeetings
				+ ", averageRotationWithMeetings=" + averageRotationWithMeetings + ", averageRotationWithAgreement="
				+ averageRotationWithAgreement + ", theBestClient=" + theBestClient + ", rotationTheBestClient="
				+ rotationTheBestClient + "]";
	}

}
