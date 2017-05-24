package pl.salesmanagement.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import pl.salesmanagement.model.Client;
import pl.salesmanagement.model.HistoryOfMeeting;
import pl.salesmanagement.model.Meeting;
import pl.salesmanagement.util.ConnectionProvider;

public class HistoryOfMeetingDAOImpl implements HistoryOfMeetingDAO{
	
	private static final String CREATE_HISTORY = 
			"INSERT INTO historyofmeeting (id_user, id_client, id_meeting) VALUES(:id_user, :id_client, :id_meeting);";
    private static final String READ_HISTORY="SELECT client.id_client, client.firstname, client.lastname, client.id_province, "
			+ "client.address, client.city, client.zip_code, client.id_industry, client.company, client.number_phone, client.email, client.activity, meeting.id_meeting, "
			+ "meeting.date, meeting.time_start, meeting.time_end, meeting.id_goal, meeting.description, historyofmeeting.id_user, "
			+ "historyofmeeting.id_historyofmeeting, historyofmeeting.id_effect, historyofmeeting.rotation, historyofmeeting.description FROM historyofmeeting "
			+ "WHERE id_historyofmeeting = :id_historyofmeeting;";
	private static final String READ_HISTORY_JOINT_TABLE_NOT_DESCRIPTIVE="SELECT client.id_client, client.firstname, client.lastname, client.id_province, "
			+ "client.address, client.city, client.zip_code, client.id_industry, client.company, client.number_phone, client.email, client.activity, meeting.id_meeting, "
			+ "meeting.date, meeting.time_start, meeting.time_end, meeting.id_goal, meeting.description, historyofmeeting.id_user, "
			+ "historyofmeeting.id_historyofmeeting, historyofmeeting.id_effect, historyofmeeting.rotation, historyofmeeting.description FROM historyofmeeting "
			+ "INNER JOIN meeting ON historyofmeeting.id_client=meeting.id_client AND historyofmeeting.id_meeting=meeting.id_meeting INNER JOIN "
			+ "client ON meeting.id_client=client.id_client WHERE historyofmeeting.id_user=:id_user AND meeting.active='NO' AND historyofmeeting.status='not-descriptive' "
			+ "ORDER BY meeting.date, meeting.time_start;";
	private static final String READ_HISTORY_JOINT_TABLE_DESCRIPTIVE="SELECT client.id_client, client.firstname, client.lastname, client.id_province, "
			+ "client.address, client.city, client.zip_code, client.id_industry, client.company, client.number_phone, client.email, client.activity, meeting.id_meeting, "
			+ "meeting.date, meeting.time_start, meeting.time_end, meeting.id_goal, meeting.description, historyofmeeting.id_user, "
			+ "historyofmeeting.id_historyofmeeting, historyofmeeting.id_effect, historyofmeeting.rotation, historyofmeeting.description FROM historyofmeeting "
			+ "INNER JOIN meeting ON historyofmeeting.id_client=meeting.id_client and historyofmeeting.id_meeting=meeting.id_meeting INNER JOIN "
			+ "client ON meeting.id_client=client.id_client WHERE historyofmeeting.id_user=:id_user AND meeting.active='NO' AND historyofmeeting.status='descriptive' "
			+ "ORDER BY meeting.date, meeting.time_start;";
    private static final String UPDATE_HISTORY= "UPDATE historyofmeeting SET id_effect = :id_effect , rotation = :rotation, description = :description , "
    		+ "status='descriptive' WHERE  id_historyofmeeting = :id_historyofmeeting;";
    private static final String DELETE_HISTORY=
    		"DELETE FROM historyofmeeting WHERE id_historyofmeeting = :id_historyofmeeting;"; 
    
	private NamedParameterJdbcTemplate template;	
    
    public HistoryOfMeetingDAOImpl() {
        template = new NamedParameterJdbcTemplate(ConnectionProvider.getDataSource());
    }
    
	@Override
	public HistoryOfMeeting create(HistoryOfMeeting historyObject) {
		HistoryOfMeeting history= new HistoryOfMeeting(historyObject);
		KeyHolder holder = new GeneratedKeyHolder();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id_user", history.getIdUser());
        paramMap.put("id_client", history.getIdClient());
        paramMap.put("id_meeting", history.getIdMeeting());
        
        SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
        int update= template.update(CREATE_HISTORY, paramSource, holder);
        if(update > 0) {
        	history.setIdHistoryMeeting((Long)holder.getKey());
        }
		return history;
	}

	@Override
	public HistoryOfMeeting read(Long idHistory) {
	    SqlParameterSource paramSource = new MapSqlParameterSource("id_historyofmeeting", idHistory);
	    HistoryOfMeeting resultHistory = template.queryForObject(READ_HISTORY, paramSource, new HistoryOfMeetingsRowMapper());
	    return resultHistory;
	}

	@Override
	public boolean update(HistoryOfMeeting historyObject) {
        boolean result = false;
        
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id_historyofmeeting", historyObject.getIdHistoryMeeting());
        paramMap.put("id_effect", historyObject.getIdEffect());
        paramMap.put("rotation", historyObject.getRotation());
        paramMap.put("description", historyObject.getDescription());
        
        SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
        int update = template.update(UPDATE_HISTORY, paramSource);
        if(update > 0) {
            result = true;
        }
        return result;
	}

	@Override
	public boolean delete(Long idHistory) {
        boolean result = false;
        
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id_historyofmeeting", idHistory);
        SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
        int delete = template.update(DELETE_HISTORY, paramSource);
        if(delete > 0) {
            result = true;
        }
        return result;
	}


	@Override
	public List<HistoryOfMeeting> getAllNotDescriptive(Long idUser) {
		SqlParameterSource paramSource = new MapSqlParameterSource("id_user", idUser);
        List<HistoryOfMeeting> historyOfMeetings = template.query(READ_HISTORY_JOINT_TABLE_NOT_DESCRIPTIVE, paramSource, new HistoryOfMeetingsRowMapper());
        return historyOfMeetings;
	}

	@Override
	public List<HistoryOfMeeting> getAllDescriptive(Long idUser) {	
		SqlParameterSource paramSource = new MapSqlParameterSource("id_user", idUser);
        List<HistoryOfMeeting> historyOfMeetings = template.query(READ_HISTORY_JOINT_TABLE_DESCRIPTIVE, paramSource, new HistoryOfMeetingsRowMapper());
        return historyOfMeetings;
	}

    private class HistoryOfMeetingsRowMapper implements RowMapper<HistoryOfMeeting> {
     	 
        @Override
        public HistoryOfMeeting mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        	Client client= new Client();
			client.setIdClient(resultSet.getLong("id_client"));
			client.setFirstname(resultSet.getString("firstname"));
			client.setLastname(resultSet.getString("lastname"));
			client.setIdProvince(resultSet.getLong("id_province"));
			client.setAddress(resultSet.getString("address"));
			client.setCity(resultSet.getString("city"));
			client.setZipCode(resultSet.getString("zip_code"));
			client.setIdIndustry(resultSet.getLong("id_industry"));
			client.setCompany(resultSet.getString("company"));
			client.setNumberPhone(resultSet.getString("number_phone"));
			client.setEmail(resultSet.getString("email"));
			client.setActivity(resultSet.getString("activity"));
			
			Meeting meeting = new Meeting();
			meeting.setIdMeeting(resultSet.getLong("id_meeting"));
			meeting.setDate(resultSet.getDate("date"));
			meeting.setTimeStart(resultSet.getTime("time_start"));
			meeting.setTimeEnd(resultSet.getTime("time_end"));
			meeting.setIdGoal(resultSet.getLong("id_goal"));
			meeting.setDescription(resultSet.getString("meeting.description"));
			
			HistoryOfMeeting historyofmeeting= new HistoryOfMeeting();
			historyofmeeting.setIdUser(resultSet.getLong("id_user"));
			historyofmeeting.setIdHistoryMeeting(resultSet.getLong("id_historyofmeeting"));
			historyofmeeting.setIdEffect(resultSet.getLong("id_effect"));
			historyofmeeting.setRotation(resultSet.getFloat("rotation"));
			historyofmeeting.setDescription(resultSet.getString("historyofmeeting.description"));
			historyofmeeting.setClient(client);
			historyofmeeting.setMeeting(meeting);
			
            return historyofmeeting;
        }
     }	
	
	
	
	@Override
	public List<HistoryOfMeeting> getAll(Long key) {
		return null;
	}

}
