package pl.salesmanagement.model;

import java.sql.Date;
import java.sql.Time;

public class Meeting {

	private long idMeeting;
	private long idUser;
	private long idClient;
	private Date date;
	private Time timeStart;
	private Time timeEnd;
	private long idGoal;
	private String description;
	private Client client;
	private String active;
	
	public Meeting(){}
	
	//CalendarMeetingsNewController
	public Meeting(long idUser, long idClient, Date date, Time timeStart, Time timeEnd, long idGoal,
			String description) {
		this.idUser = idUser;
		this.idClient = idClient;
		this.date = date;
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
		this.idGoal = idGoal;
		this.description = description;
	}	
	
	//Update Filter
	public Meeting(long idUser, long idClient, Date date, Time timeStart, Time timeEnd, long idGoal,
			String description, String active) {
		this.idUser = idUser;
		this.idClient = idClient;
		this.date = date;
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
		this.idGoal = idGoal;
		this.description = description;
		this.setActive(active);
	}	
	
	//CalendarMeetingsEditController
	public Meeting(long idMeeting, Date date, Time timeStart, Time timeEnd, long idGoal,
			String description, Client client) {
		this.idMeeting= idMeeting;
		this.date = date;
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
		this.idGoal = idGoal;
		this.description = description;
		this.client = client;
	}	
	
	//DAO
	public Meeting(Meeting meeting) {
		this.idMeeting = meeting.getIdMeeting();
		this.idUser = meeting.getIdUser();
		this.idClient = meeting.getIdClient();
		this.date = meeting.getDate();
		this.timeStart = meeting.getTimeStart();
		this.timeEnd = meeting.getTimeEnd();
		this.idGoal = meeting.getIdGoal();
		this.description = meeting.getDescription();
		this.client= meeting.getClient();
	}
	
	public long getIdMeeting() {
		return idMeeting;
	}

	public void setIdMeeting(long idMeeting) {
		this.idMeeting = idMeeting;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(Time timeStart) {
		this.timeStart = timeStart;
	}

	public Time getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(Time timeEnd) {
		this.timeEnd = timeEnd;
	}

	public long getIdGoal() {
		return idGoal;
	}

	public void setIdGoal(long idGoal) {
		this.idGoal = idGoal;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Meeting [idMeeting=" + idMeeting + ", idUser=" + idUser + ", idClient=" + idClient + ", date=" + date
				+ ", timeStart=" + timeStart + ", timeEnd=" + timeEnd + ", idGoal=" + idGoal + ", description="
				+ description + ", client=" + client + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + (int) (idGoal ^ (idGoal >>> 32));
		result = prime * result + (int) (idMeeting ^ (idMeeting >>> 32));
		result = prime * result + ((timeEnd == null) ? 0 : timeEnd.hashCode());
		result = prime * result + ((timeStart == null) ? 0 : timeStart.hashCode());
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
		Meeting other = (Meeting) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (idGoal != other.idGoal)
			return false;
		if (idMeeting != other.idMeeting)
			return false;
		if (timeEnd == null) {
			if (other.timeEnd != null)
				return false;
		} else if (!timeEnd.equals(other.timeEnd))
			return false;
		if (timeStart == null) {
			if (other.timeStart != null)
				return false;
		} else if (!timeStart.equals(other.timeStart))
			return false;
		return true;
	}

}
