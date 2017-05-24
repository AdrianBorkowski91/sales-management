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

import pl.salesmanagement.model.Account;
import pl.salesmanagement.util.ConnectionProvider;

public class AccountDAOImpl implements AccountDAO {
	
	private static final String CREATE_ACCOUNT = 
		    "INSERT INTO account() VALUES();";
    private static String READ_ACCOUNT = 
  	        "SELECT id_account, reminder, raport_modified FROM account WHERE id_account = :id_account;";
    private static String UPDATE_ACCOUNT=
    		"UPDATE account SET reminder = :reminder, raport_modified = :raport_modified WHERE id_account = :id_account;";
    
	private NamedParameterJdbcTemplate template;
		     
    public AccountDAOImpl() {
        template = new NamedParameterJdbcTemplate(ConnectionProvider.getDataSource());
    }
    
	@Override
	public Account create(Account newAccount) {
		Account resultAccount= new Account (newAccount);
		KeyHolder holder = new GeneratedKeyHolder();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        
        SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
        int update= template.update(CREATE_ACCOUNT, paramSource, holder);
        if(update > 0) {
        	resultAccount.setIdAccount((Long)holder.getKey());
        }

        return resultAccount;
	}

	@Override
	public Account read(Long idAccount) {
	    SqlParameterSource paramSource = new MapSqlParameterSource("id_account", idAccount);
	    Account resultAccount = template.queryForObject(READ_ACCOUNT, paramSource, new AccountRowMapper());
	    return resultAccount;
	}

	@Override
	public boolean update(Account updateAccount) {
        boolean result = false;
        
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id_account", updateAccount.getIdAccount());
        paramMap.put("reminder", updateAccount.getReminder());
        paramMap.put("raport_modified", updateAccount.getRaportModified());
        
        SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
        int update = template.update(UPDATE_ACCOUNT, paramSource);
        if(update > 0) {
            result = true;
        }
        return result;
	}

	@Override
	public boolean delete(Long idAccount) {
		return false;
	}
	
	@Override
	public Account getAccountById(Long idAccount) {
		Account resultAccount = null;  
	    SqlParameterSource paramSource=null;
	    paramSource= new MapSqlParameterSource("id_account", idAccount);
		resultAccount = template.queryForObject(READ_ACCOUNT, paramSource, new AccountRowMapper());
	    return resultAccount;
	}
	
    private class AccountRowMapper implements RowMapper<Account> {
   	 
        @Override
        public Account mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Account account = new Account();
            account.setIdAccount(resultSet.getLong("id_account"));
            account.setReminder(resultSet.getString("reminder"));
            account.setRaportModified(resultSet.getString("raport_modified"));
            return account;
        }
     }

	@Override
	public List<Account> getAll(Long idAccount) {
		// TODO Auto-generated method stub
		return null;
	}

}
