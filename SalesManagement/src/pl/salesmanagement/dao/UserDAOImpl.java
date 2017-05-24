package pl.salesmanagement.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import pl.salesmanagement.model.Account;
import pl.salesmanagement.model.User;
import pl.salesmanagement.util.ConnectionProvider;
 
public class UserDAOImpl implements UserDAO {

	public static final int USER_METHOD=1;
	public static final int EMAIL_METHOD=2;
	public static final int ID_METHOD=3;
	public static final int WITHACCOUNT_METHOD=4;
	
	private static final String CREATE_USER = 
		    "INSERT INTO user(username, email, password, id_account) VALUES(:username, :email, :password, :id_account);";
    private static String READ_USER = 
    	      "SELECT id_user, username, email, password, id_account FROM user WHERE ";
    private static String READ_USER_JOINT_ACCOUNT="SELECT user.id_account, id_user, username, email, password, "
    		+ "reminder, raport_modified FROM account LEFT JOIN user ON account.id_account=user.id_account WHERE"
    		+ " id_user=:id_user ;";
    private static String UPDATE_USER=
    		"UPDATE user SET password=:password WHERE id_user=:id_user ;";
    				
	private NamedParameterJdbcTemplate template;
		     
    public UserDAOImpl() {
        template = new NamedParameterJdbcTemplate(ConnectionProvider.getDataSource());
    }
	
    @Override
    public User create(User newUser) {
    	User resultUser= new User(newUser);
    	KeyHolder holder= new GeneratedKeyHolder();
    	
        Map<String, Object> paramMap = new HashMap<String, Object>();  
        paramMap.put("username", resultUser.getUsername());
        paramMap.put("password", resultUser.getPassword());
        paramMap.put("email", resultUser.getEmail());
        paramMap.put("id_account", resultUser.getIdAccount());
        
        SqlParameterSource paramSource= new MapSqlParameterSource(paramMap);
        int update= template.update(CREATE_USER, paramSource, holder);
        if(update > 0) {
        	resultUser.setIdUser((Long)holder.getKey());
        }

        return resultUser;
    }
 
    @Override
    public User read(Long idUser) {
        User resultUser = null;
        SqlParameterSource paramSource = new MapSqlParameterSource("id_user", idUser);
        resultUser = template.queryForObject(READ_USER, paramSource, new UserRowMapper());
        return resultUser;
    }
 
    @Override
    public boolean update(User updateUser) {
        boolean result = false;
        
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("password", updateUser.getPassword());
        paramMap.put("id_user", updateUser.getIdUser());

        SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
        int update = template.update(UPDATE_USER, paramSource);
        if(update > 0) {
            result = true;
        }
        return result;
    }
 
    @Override
    public boolean delete(Long idUser) {
        return false;
    }
 
    @Override
    public User getUserByCondition(String condition, int method) {
        User resultUser = null;
        
        String query=null;
        SqlParameterSource paramSource=null;
        
        if(method==USER_METHOD){
        	query= READ_USER+"username = :username;";
        	paramSource= new MapSqlParameterSource("username", condition);
        }
        if(method==EMAIL_METHOD){
        	query= READ_USER+"email = :email;";
        	paramSource= new MapSqlParameterSource("email", condition);
        }
        if(method==ID_METHOD){
        	query= READ_USER+"id_user = :id_user;";
        	paramSource= new MapSqlParameterSource("id_user", condition);
        }
        if(method==WITHACCOUNT_METHOD){
        	paramSource= new MapSqlParameterSource("id_user", condition);
        	
            try {
    			resultUser = template.queryForObject(READ_USER_JOINT_ACCOUNT, paramSource, new UserRowMapperWithAccount());
    		} catch (org.springframework.dao.EmptyResultDataAccessException e) {
    			
    			resultUser=null;
    		}
            return resultUser;
        }
        
        try {
			resultUser = template.queryForObject(query, paramSource, new UserRowMapper());
		} catch (org.springframework.dao.EmptyResultDataAccessException e) {
			
			resultUser=null;
		}

        return resultUser;
    }
	
    private class UserRowMapper implements RowMapper<User> {
        	 
       @Override
       public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
           User user = new User();
           user.setIdUser(resultSet.getLong("id_user"));
           user.setUsername(resultSet.getString("username"));
           user.setEmail(resultSet.getString("email"));
           user.setPassword(resultSet.getString("password"));
           user.setIdAccount(resultSet.getLong("id_account"));

           return user;
       }
    }

    private class UserRowMapperWithAccount implements RowMapper<User> {
   	 
        @Override
        public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            User user = new User();
            user.setIdUser(resultSet.getLong("id_user"));
            user.setUsername(resultSet.getString("username"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            user.setIdAccount(resultSet.getLong("id_account"));
            
            Account account= new Account();
            account.setIdAccount(resultSet.getLong("id_account"));
            account.setReminder(resultSet.getString("reminder"));
            account.setRaportModified(resultSet.getString("raport_modified"));        
            user.setAccount(account);
            return user;
        }
     }
    
	@Override
	public List<User> getAll(Long idUser) {
		// TODO Auto-generated method stub
		return null;
	}

}