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
import pl.salesmanagement.model.Meeting;
import pl.salesmanagement.util.ConnectionProvider;

public class MeetingDAOImpl  implements MeetingDAO{

	private static final String CREATE_MEETING = 
			"INSERT INTO meeting(id_user, id_client, date, time_start, time_end, id_goal, description) VALUES(:id_user, "
			+ ":id_client, :date, :time_start, :time_end, :id_goal, :description);";
	private static String READ_MEETING = 
			"SELECT id_client, id_meeting, date, time_start, time_end, id_goal, description FROM meeting WHERE "
			+ "id_meeting = :id_meeting;";
	private static final String READ_MEETING_JOINT_CLIENT="SELECT client.id_client, client.firstname, client.lastname, "
			+ "client.id_province, client.address, client.city, client.zip_code, client.id_industry, client.company, "
			+ "client.number_phone, client.email, meeting.id_user, meeting.id_meeting, meeting.date, meeting.time_start, meeting.time_end, "
			+ "meeting.id_goal, meeting.description FROM meeting LEFT JOIN client ON meeting.id_client=client.id_client "
			+ "WHERE client.id_user=:id_user AND meeting.active='YES' ORDER BY meeting.date, meeting.time_start;";
    private static final String UPDATE_MEETING=
    		"UPDATE meeting SET date = :date, time_start = :time_start, time_end = :time_end, id_goal = :id_goal, "
    		+ "description = :description WHERE id_meeting = :id_meeting;";
    private static final String UPDATE_MEETING_CHANGE=
    		"UPDATE meeting SET active = :active WHERE id_meeting = :id_meeting;";
    private static final String DELETE_MEETING=
    		"DELETE FROM meeting WHERE id_meeting = :id_meeting;"; 
    
    private NamedParameterJdbcTemplate template;
	
    
    public MeetingDAOImpl() {
        template = new NamedParameterJdbcTemplate(ConnectionProvider.getDataSource());
    }
    
	@Override
	public Meeting create(Meeting newMeeting) {
		Meeting resultMeeting= new Meeting(newMeeting);
		KeyHolder holder = new GeneratedKeyHolder();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id_user",resultMeeting.getIdUser());
        paramMap.put("id_client", resultMeeting.getIdClient());      
        paramMap.put("date", resultMeeting.getDate());
        paramMap.put("time_start", resultMeeting.getTimeStart());
        paramMap.put("time_end", resultMeeting.getTimeEnd());       
        paramMap.put("id_goal", resultMeeting.getIdGoal());
        paramMap.put("description", resultMeeting.getDescription());
        
        SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
        int update= template.update(CREATE_MEETING, paramSource, holder);
        if(update > 0) {
        	resultMeeting.setIdMeeting((Long)holder.getKey());
        }

        return resultMeeting;
	}

	@Override
	public Meeting read(Long idMeeting) {
	    SqlParameterSource paramSource = new MapSqlParameterSource("id_meeting", idMeeting);
	    Meeting resultMeeting= template.queryForObject(READ_MEETING, paramSource, new MeetingRowMapper());
	    return resultMeeting;
	}
	
	@Override
	public boolean update(Meeting updateMeeting) {
        boolean result = false;
        
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id_meeting", updateMeeting.getIdMeeting());
        paramMap.put("date", updateMeeting.getDate());
        paramMap.put("time_start", updateMeeting.getTimeStart());
        paramMap.put("time_end", updateMeeting.getTimeEnd());
        paramMap.put("id_goal", updateMeeting.getIdGoal());
        paramMap.put("description", updateMeeting.getDescription());
        
        SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
        int update = template.update(UPDATE_MEETING, paramSource);
        if(update > 0) {
            result = true;
        }
        return result;
	}

	@Override
	public boolean updateChangeActive(Meeting updateMeeting) {
        boolean result = false;
        
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id_meeting", updateMeeting.getIdMeeting());
        paramMap.put("active", updateMeeting.getActive());
        
        SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
        int update = template.update(UPDATE_MEETING_CHANGE, paramSource);
        if(update > 0) {
            result = true;
        }
        return result;
	}
	
	@Override
	public boolean delete(Long idMeeting) {
        boolean result = false;
        
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id_meeting", idMeeting);
        SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
        int delete = template.update(DELETE_MEETING, paramSource);
        if(delete > 0) {
            result = true;
        }
        return result;
	}

	@Override
	public List<Meeting> getAll(Long idUser) {
		SqlParameterSource paramSource = new MapSqlParameterSource("id_user", idUser);
        List<Meeting> clientsToMeeting = template.query(READ_MEETING_JOINT_CLIENT, paramSource, new MeetingRowMapperWithClient());
        return clientsToMeeting;
	}

    private class MeetingRowMapperWithClient implements RowMapper<Meeting> {
      	 
        @Override
        public Meeting mapRow(ResultSet resultSet, int rowNum) throws SQLException {

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
			
			Meeting meeting = new Meeting();
			meeting.setIdUser(resultSet.getLong("id_user"));
			meeting.setIdMeeting(resultSet.getLong("id_meeting"));
			meeting.setDate(resultSet.getDate("date"));
			meeting.setTimeStart(resultSet.getTime("time_start"));
			meeting.setTimeEnd(resultSet.getTime("time_end"));
			meeting.setIdGoal(resultSet.getLong("id_goal"));
			meeting.setDescription(resultSet.getString("description"));
			meeting.setClient(client);

            return meeting;
        }
     }
    
    private class MeetingRowMapper implements RowMapper<Meeting> {
     	 
        @Override
        public Meeting mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			
			Meeting meeting = new Meeting();
			meeting.setIdMeeting(resultSet.getLong("id_meeting"));
			meeting.setIdClient(resultSet.getLong("id_meeting"));
			meeting.setDate(resultSet.getDate("date"));
			meeting.setTimeStart(resultSet.getTime("time_start"));
			meeting.setTimeEnd(resultSet.getTime("time_end"));
			meeting.setIdGoal(resultSet.getLong("id_goal"));
			meeting.setDescription(resultSet.getString("description"));

            return meeting;
        }
     }


}
