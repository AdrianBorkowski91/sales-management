package pl.salesmanagement.model;

import java.util.Map;

public class AnnualReport extends ReportModel{
	
	private Map<Long, Float> rotationList;

	public AnnualReport(String formatDateToSelect, int togetherMeetings, int togetherMeetingsTook,
			float allRotationWithMeetings, float averageRotationWithMeetings, float averageRotationWithAgreement,
			String theBestClient, float rotationTheBestClient, Map<Long, Float> rotationList) {
		super(formatDateToSelect, togetherMeetings, togetherMeetingsTook, allRotationWithMeetings,
				averageRotationWithMeetings, averageRotationWithAgreement, theBestClient, rotationTheBestClient);
		this.rotationList = rotationList;
	}

	public AnnualReport(){}

	public Map<Long, Float> getRotationList() {
		return rotationList;
	}

	public void setRotationList(Map<Long, Float> rotationList) {
		this.rotationList = rotationList;
	}

	@Override
	public String toString() {
		return "AnnualReport [rotationList=" + rotationList + ", formatDateToSelect=" + formatDateToSelect
				+ ", togetherMeetings=" + togetherMeetings + ", togetherMeetingsTook=" + togetherMeetingsTook
				+ ", allRotationWithMeetings=" + allRotationWithMeetings + ", averageRotationWithMeetings="
				+ averageRotationWithMeetings + ", averageRotationWithAgreement=" + averageRotationWithAgreement
				+ ", theBestClient=" + theBestClient + ", rotationTheBestClient=" + rotationTheBestClient + "]";
	}
	
}
