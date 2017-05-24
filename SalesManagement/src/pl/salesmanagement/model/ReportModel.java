package pl.salesmanagement.model;

public class ReportModel {
	
	protected String formatDateToSelect;
	protected int togetherMeetings;
	protected int togetherMeetingsTook;
	protected float allRotationWithMeetings;
	protected float averageRotationWithMeetings;
	protected float averageRotationWithAgreement;
	protected String theBestClient;
	protected float rotationTheBestClient;
	
	public ReportModel(String formatDateToSelect, int togetherMeetings, int togetherMeetingsTook,
			float allRotationWithMeetings, float averageRotationWithMeetings, float averageRotationWithAgreement,
			String theBestClient, float rotationTheBestClient) {
		this.formatDateToSelect = formatDateToSelect;
		this.togetherMeetings = togetherMeetings;
		this.togetherMeetingsTook = togetherMeetingsTook;
		this.allRotationWithMeetings = allRotationWithMeetings;
		this.averageRotationWithMeetings = averageRotationWithMeetings;
		this.averageRotationWithAgreement = averageRotationWithAgreement;
		this.theBestClient = theBestClient;
		this.rotationTheBestClient = rotationTheBestClient;
	}
	
	public ReportModel() {}
	
	public static String moneyFormat(float money){
		String moneyText=String.format("%.2f", money).replace('.', ',');
		return moneyText;
	}

	public String getFormatDateToSelect() {
		return formatDateToSelect;
	}

	public void setFormatDateToSelect(String formatDateToSelect) {
		this.formatDateToSelect = formatDateToSelect;
	}

	public int getTogetherMeetings() {
		return togetherMeetings;
	}

	public void setTogetherMeetings(int togetherMeetings) {
		this.togetherMeetings = togetherMeetings;
	}

	public int getTogetherMeetingsTook() {
		return togetherMeetingsTook;
	}

	public void setTogetherMeetingsTook(int togetherMeetingsTook) {
		this.togetherMeetingsTook = togetherMeetingsTook;
	}

	public float getAllRotationWithMeetings() {
		return allRotationWithMeetings;
	}

	public void setAllRotationWithMeetings(float allRotationWithMeetings) {
		this.allRotationWithMeetings = allRotationWithMeetings;
	}

	public float getAverageRotationWithMeetings() {
		return averageRotationWithMeetings;
	}

	public void setAverageRotationWithMeetings(float averageRotationWithMeetings) {
		this.averageRotationWithMeetings = averageRotationWithMeetings;
	}

	public float getAverageRotationWithAgreement() {
		return averageRotationWithAgreement;
	}

	public void setAverageRotationWithAgreement(float averageRotationWithAgreement) {
		this.averageRotationWithAgreement = averageRotationWithAgreement;
	}

	public String getTheBestClient() {
		return theBestClient;
	}

	public void setTheBestClient(String theBestClient) {
		this.theBestClient = theBestClient;
	}

	public float getRotationTheBestClient() {
		return rotationTheBestClient;
	}

	public void setRotationTheBestClient(float rotationTheBestClient) {
		this.rotationTheBestClient = rotationTheBestClient;
	}

	@Override
	public String toString() {
		return "ReportModel [formatDateToSelect=" + formatDateToSelect + ", togetherMeetings=" + togetherMeetings
				+ ", togetherMeetingsTook=" + togetherMeetingsTook + ", allRotationWithMeetings="
				+ allRotationWithMeetings + ", averageRotationWithMeetings=" + averageRotationWithMeetings
				+ ", averageRotationWithAgreement=" + averageRotationWithAgreement + ", theBestClient=" + theBestClient
				+ ", rotationTheBestClient=" + rotationTheBestClient + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(allRotationWithMeetings);
		result = prime * result + Float.floatToIntBits(averageRotationWithAgreement);
		result = prime * result + Float.floatToIntBits(averageRotationWithMeetings);
		result = prime * result + ((formatDateToSelect == null) ? 0 : formatDateToSelect.hashCode());
		result = prime * result + Float.floatToIntBits(rotationTheBestClient);
		result = prime * result + ((theBestClient == null) ? 0 : theBestClient.hashCode());
		result = prime * result + togetherMeetings;
		result = prime * result + togetherMeetingsTook;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReportModel other = (ReportModel) obj;
		if (Float.floatToIntBits(allRotationWithMeetings) != Float.floatToIntBits(other.allRotationWithMeetings))
			return false;
		if (Float.floatToIntBits(averageRotationWithAgreement) != Float
				.floatToIntBits(other.averageRotationWithAgreement))
			return false;
		if (Float.floatToIntBits(averageRotationWithMeetings) != Float
				.floatToIntBits(other.averageRotationWithMeetings))
			return false;
		if (formatDateToSelect == null) {
			if (other.formatDateToSelect != null)
				return false;
		} else if (!formatDateToSelect.equals(other.formatDateToSelect))
			return false;
		if (Float.floatToIntBits(rotationTheBestClient) != Float.floatToIntBits(other.rotationTheBestClient))
			return false;
		if (theBestClient == null) {
			if (other.theBestClient != null)
				return false;
		} else if (!theBestClient.equals(other.theBestClient))
			return false;
		if (togetherMeetings != other.togetherMeetings)
			return false;
		if (togetherMeetingsTook != other.togetherMeetingsTook)
			return false;
		return true;
	}

}
