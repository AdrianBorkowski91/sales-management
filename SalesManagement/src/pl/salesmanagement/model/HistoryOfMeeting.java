package pl.salesmanagement.model;

public class HistoryOfMeeting {
	
	private long idHistoryMeeting;
	private long idUser;
	private long idClient;
	private long idMeeting;
	private long idEffect;
	private String description;
	private float rotation;
	private Client client;
	private Meeting meeting;
	
	public HistoryOfMeeting(){}

	//DAO
	public HistoryOfMeeting(HistoryOfMeeting history) {
		this.idUser = history.idUser;
		this.idClient = history.idClient;
		this.idMeeting = history.idMeeting;
	}
	
	//FilterServlet
	public HistoryOfMeeting(Meeting meeting) {
		this.idUser = meeting.getIdUser();
		this.idClient = meeting.getClient().getIdClient();
		this.idMeeting = meeting.getIdMeeting();
		this.description="";
	}
	
	//HistoryOfMeetingsEditController
	public HistoryOfMeeting(long idHistoryMeeting, long idEffect, float rotation, String description,
			Client client, Meeting meeting){
		this.idHistoryMeeting= idHistoryMeeting;
		this.idEffect= idEffect;
		this.rotation= rotation;
		this.description= description;
		this.client= client;
		this.meeting= meeting;
	}

	public long getIdHistoryMeeting() {
		return idHistoryMeeting;
	}

	public void setIdHistoryMeeting(long idHistoryMeeting) {
		this.idHistoryMeeting = idHistoryMeeting;
	}

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public long getIdClient() {
		return idClient;
	}

	public void setIdClient(long idClient) {
		this.idClient = idClient;
	}

	public long getIdMeeting() {
		return idMeeting;
	}

	public void setIdMeeting(long idMeeting) {
		this.idMeeting = idMeeting;
	}

	public long getIdEffect() {
		return idEffect;
	}

	public void setIdEffect(long idEffect) {
		this.idEffect = idEffect;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getRotation() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Meeting getMeeting() {
		return meeting;
	}

	public void setMeeting(Meeting meeting) {
		this.meeting = meeting;
	}

	@Override
	public String toString() {
		return "HistoryOfMeeting [idHistoryMeeting=" + idHistoryMeeting + ", idUser=" + idUser + ", idClient="
				+ idClient + ", idMeeting=" + idMeeting + ", idEffect=" + idEffect + ", description=" + description
				+ ", rotation=" + rotation + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + (int) (idEffect ^ (idEffect >>> 32));
		result = prime * result + Float.floatToIntBits(rotation);
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
		HistoryOfMeeting other = (HistoryOfMeeting) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (idEffect != other.idEffect)
			return false;
		if (Float.floatToIntBits(rotation) != Float.floatToIntBits(other.rotation))
			return false;
		return true;
	}

}
