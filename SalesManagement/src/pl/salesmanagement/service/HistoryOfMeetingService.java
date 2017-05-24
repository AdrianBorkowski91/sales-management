package pl.salesmanagement.service;

import java.util.Comparator;
import java.util.List;

import pl.salesmanagement.dao.DAOFactory;
import pl.salesmanagement.dao.HistoryOfMeetingDAO;
import pl.salesmanagement.methods.MethodsHistoryOfMeeting;
import pl.salesmanagement.model.HistoryOfMeeting;

public class HistoryOfMeetingService {
	
	public static HistoryOfMeeting addHistoryOfMeeting(HistoryOfMeeting history) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        HistoryOfMeetingDAO historyDao = factory.getHistoryOfMeetingDAO();
        history= historyDao.create(history);
        return history;
    }
	
	public static List<HistoryOfMeeting> getAllHistoryOfMeetings(Long idUser, int method) {
		if(method==MethodsHistoryOfMeeting.METHOD_NOT_DESCRIPTIVE){
			return getAllHistoryOfMeetings(null, idUser, MethodsHistoryOfMeeting.METHOD_NOT_DESCRIPTIVE);
		}
		else if(method==MethodsHistoryOfMeeting.METHOD_DESCRIPTIVE){
			return getAllHistoryOfMeetings(null, idUser, MethodsHistoryOfMeeting.METHOD_DESCRIPTIVE);
		}
		return null;
	}
	    
	public static List<HistoryOfMeeting> getAllHistoryOfMeetings(Comparator<HistoryOfMeeting> comparator, Long idUser, int method) {
	    DAOFactory factory = DAOFactory.getDAOFactory();
	    HistoryOfMeetingDAO historyOfMeetingDao = factory.getHistoryOfMeetingDAO();
	    List<HistoryOfMeeting> historyOfMeetings =null;
	    
	    if(method==MethodsHistoryOfMeeting.METHOD_NOT_DESCRIPTIVE){
	    	historyOfMeetings = historyOfMeetingDao.getAllNotDescriptive(idUser);
	    }
	    else if(method==MethodsHistoryOfMeeting.METHOD_DESCRIPTIVE){
	    	historyOfMeetings = historyOfMeetingDao.getAllDescriptive(idUser);
	    }
	    
	    if(comparator != null && historyOfMeetings != null) {
	    	historyOfMeetings.sort(comparator);
	    }
	    return historyOfMeetings;
	}
	
	public static HistoryOfMeeting updateMeeting(HistoryOfMeeting history, HistoryOfMeeting historyToUpdate) {
	       DAOFactory factory = DAOFactory.getDAOFactory();
	       HistoryOfMeetingDAO historyOfMeetingDao = factory.getHistoryOfMeetingDAO();
	       
	       if(historyToUpdate != null) {
	    	   historyToUpdate.setIdEffect(history.getIdEffect());
	    	   historyToUpdate.setRotation(history.getRotation());
	    	   historyToUpdate.setDescription(history.getDescription());
	    	   
	    	   historyOfMeetingDao.update(historyToUpdate);
	       }
	       return historyToUpdate;
	}
	
	public static boolean deleteHistoryOfMeeting(long idHistoryParam) {
	       DAOFactory factory = DAOFactory.getDAOFactory();
	       HistoryOfMeetingDAO historyOfMeetingDao = factory.getHistoryOfMeetingDAO();
	       if(historyOfMeetingDao.delete(idHistoryParam)){
	    		return true;
	       }
	       return false;
	}


}
