package pl.salesmanagement.dao;

import java.util.List;

import pl.salesmanagement.model.HistoryOfMeeting;

public interface HistoryOfMeetingDAO extends  GenericDAO<HistoryOfMeeting, Long> {
	
	List<HistoryOfMeeting> getAllNotDescriptive(Long idUser);
	List<HistoryOfMeeting> getAllDescriptive(Long idUser);
}
