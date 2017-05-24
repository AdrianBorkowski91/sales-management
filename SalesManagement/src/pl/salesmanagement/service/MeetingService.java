package pl.salesmanagement.service;

import java.util.Comparator;
import java.util.List;

import pl.salesmanagement.dao.DAOFactory;
import pl.salesmanagement.dao.MeetingDAO;
import pl.salesmanagement.model.Meeting;

public class MeetingService {
	
	public static Meeting addMeeting(Meeting meeting) {
		DAOFactory factory = DAOFactory.getDAOFactory();
		MeetingDAO meetingDao = factory.getMeetingDAO();
		meeting= meetingDao.create(meeting);
		return meeting;
	}
	
	public static List<Meeting> getAllMeetings(Long idUser) {
		return getAllMeetings(null, idUser);
	}
	    
	public static List<Meeting> getAllMeetings(Comparator<Meeting> comparator, Long idUser) {
		DAOFactory factory = DAOFactory.getDAOFactory();
	    MeetingDAO meetingDao = factory.getMeetingDAO();
	    List<Meeting> meetings = meetingDao.getAll(idUser);
	    if(comparator != null && meetings != null) {
	       meetings.sort(comparator);
	    }
	    return meetings;
	}
	
	public static Meeting updateMeeting(Meeting meeting, Meeting meetingToUpdate) {
	       DAOFactory factory = DAOFactory.getDAOFactory();
	       MeetingDAO meetingDao = factory.getMeetingDAO();
	      
	       if(meetingToUpdate != null) {
	    	   meetingToUpdate.setIdMeeting(meeting.getIdMeeting());
	    	   meetingToUpdate.setDate(meeting.getDate());
	    	   meetingToUpdate.setTimeStart(meeting.getTimeStart());
	    	   meetingToUpdate.setTimeEnd(meeting.getTimeEnd());
	    	   meetingToUpdate.setIdGoal(meeting.getIdGoal());
	    	   meetingToUpdate.setDescription(meeting.getDescription());
	    	   
	    	   meetingDao.update(meetingToUpdate);
	       }
	       return meetingToUpdate;
	}

	public static void updateMeetingChangeActive(Long idMeeting) {
		DAOFactory factory = DAOFactory.getDAOFactory();
	    MeetingDAO meetingDao = factory.getMeetingDAO();
	      
	    Meeting meetingToUpdate= meetingDao.read(idMeeting);
	    if(meetingToUpdate != null) {
	    	meetingToUpdate.setActive("NO");
	    	
	    	meetingDao.updateChangeActive(meetingToUpdate);
	    }
	}
	
	public static boolean deleteMeeting(long idMeeting) {
	    DAOFactory factory = DAOFactory.getDAOFactory();
	    MeetingDAO meetingDao = factory.getMeetingDAO();
	    if(meetingDao.delete(idMeeting)){
	    	   return true;
	    }
	    return false;
	}	


}
