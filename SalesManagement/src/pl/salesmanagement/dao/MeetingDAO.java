package pl.salesmanagement.dao;

import pl.salesmanagement.model.Meeting;

public interface MeetingDAO extends GenericDAO<Meeting, Long> {

	boolean updateChangeActive(Meeting updateObject);

}