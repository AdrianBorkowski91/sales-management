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
import pl.salesmanagement.util.ConnectionProvider;

public class ClientDAOImpl implements ClientDAO {

	private static final String CREATE_CLIENT = 
			"INSERT INTO client(id_user, firstname, lastname, id_province, address, city, zip_code, id_industry,"
			+ " company, number_phone, email) VALUES(:id_user, :firstname, :lastname, :id_province, :address, :city, "
			+ ":zip_code, :id_industry, :company, :number_phone, :email);";
    private static String READ_CLIENT = 
  	        "SELECT id_user, id_client, firstname, lastname, id_province, address, city, zip_code, company, id_industry, number_phone, "
  	        + "email, activity FROM client WHERE id_client = :id_client;";
    private static final String READ_ALL_CLIENTS = 
    		"SELECT id_client, id_user, firstname, lastname, id_province, address, city, zip_code, id_industry, company, number_phone,"
    		+ " email, activity FROM client WHERE id_user = :id_user ORDER BY firstname, lastname;";
    private static final String UPDATE_CLIENT=
    		"UPDATE client SET firstname = :firstname, lastname = :lastname, id_province = :id_province, "
    		+ "address = :address, city = :city, zip_code = :zip_code, company = :company, id_industry = :id_industry, "
    		+ "number_phone = :number_phone, email = :email, activity = :activity WHERE id_client = :id_client;";
    private static final String DELETE_CLIENT=
    		"DELETE FROM client WHERE id_client = :id_client;";    

    private NamedParameterJdbcTemplate template;
     
    public ClientDAOImpl() {
        template = new NamedParameterJdbcTemplate(ConnectionProvider.getDataSource());
    }
    
	@Override
	public Client create(Client newClient) {
		Client resultClient= new Client(newClient);
		KeyHolder holder = new GeneratedKeyHolder();
        Map<String, Object> paramMap = new HashMap<String, Object>();    
        paramMap.put("id_user", resultClient.getIdUser());
        paramMap.put("firstname", resultClient.getFirstname());
        paramMap.put("lastname", resultClient.getLastname());
        paramMap.put("id_province", resultClient.getIdProvince());
        paramMap.put("address", resultClient.getAddress());
        paramMap.put("city", resultClient.getCity());
        paramMap.put("zip_code", resultClient.getZipCode());
        paramMap.put("id_industry", resultClient.getIdIndustry());
        paramMap.put("company", resultClient.getCompany());
        paramMap.put("number_phone", resultClient.getNumberPhone());
        paramMap.put("email", resultClient.getEmail());   
        
        SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
        int update= template.update(CREATE_CLIENT, paramSource, holder);
        if(update > 0) {
        	resultClient.setIdClient((Long)holder.getKey());
        }

        return resultClient;
	}
	
	@Override
	public Client read(Long idClient) {
	    SqlParameterSource paramSource = new MapSqlParameterSource("id_client", idClient);
	    Client resultClient = template.queryForObject(READ_CLIENT, paramSource, new ClientRowMapper());
	    return resultClient;
	}

	@Override
	public boolean update(Client updateObject) {
        boolean result = false;
        
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id_client", updateObject.getIdClient());
        paramMap.put("firstname", updateObject.getFirstname());
        paramMap.put("lastname", updateObject.getLastname());
        paramMap.put("id_province", updateObject.getIdProvince());
        paramMap.put("address", updateObject.getAddress());
        paramMap.put("city", updateObject.getCity());
        paramMap.put("zip_code", updateObject.getZipCode());
        paramMap.put("company", updateObject.getCompany());       
        paramMap.put("id_industry", updateObject.getIdIndustry());
        paramMap.put("number_phone", updateObject.getNumberPhone());
        paramMap.put("email", updateObject.getEmail());
        paramMap.put("activity", updateObject.getActivity());
        
        SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
        int update = template.update(UPDATE_CLIENT, paramSource);
        if(update > 0) {
            result = true;
        }
        return result;
	}

	@Override
	public boolean delete(Long idClient) {
        boolean result = false;
        
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id_client", idClient);
        SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
        int delete = template.update(DELETE_CLIENT, paramSource);
        if(delete > 0) {
            result = true;
        }
        return result;
	}
	
	@Override
	public List<Client> getAll(Long idUser) {
		SqlParameterSource paramSource = new MapSqlParameterSource("id_user", idUser);
        List<Client> clients = template.query(READ_ALL_CLIENTS, paramSource, new ClientRowMapper());
        return clients;
	}
	
    private class ClientRowMapper implements RowMapper<Client> {
        @Override
        public Client mapRow(ResultSet resultSet, int row) throws SQLException {
        	Client client = new Client();
        	client.setIdClient(resultSet.getLong("id_client"));
        	client.setIdUser(resultSet.getLong("id_user"));
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
            return client;
        }

    }

}
