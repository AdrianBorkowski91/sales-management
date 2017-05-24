package pl.salesmanagement.dao;

import pl.salesmanagement.model.User;

public interface UserDAO extends GenericDAO<User, Long> {
	 
	User getUserByCondition(String condition, int method);
   
}