package pl.salesmanagement.service;

import pl.salesmanagement.dao.DAOFactory;
import pl.salesmanagement.dao.UserDAO;
import pl.salesmanagement.dao.UserDAOImpl;
import pl.salesmanagement.model.User;

public class UserService {
	
    public User addUser(User user) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        UserDAO userDao = factory.getUserDAO();
        user= userDao.create(user);
        return user;
    }
     
  //MethodsUserAccount, ServiceRegister
    public static User getUserByUsername(String username) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        UserDAO userDao = factory.getUserDAO();
        User user = userDao.getUserByCondition(username, UserDAOImpl.USER_METHOD);
        return user;
    } 
    
    //MethodsUserAccount, ServiceRegister
    public static User getUserByEmail(String email) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        UserDAO userDao = factory.getUserDAO();
        User user = userDao.getUserByCondition(email, UserDAOImpl.EMAIL_METHOD);
        return user;
    } 

    //LoginController, MyAccountController
    public static User getUserWithAccount(Long idAccount) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        UserDAO userDao = factory.getUserDAO();
        User user = userDao.getUserByCondition(""+idAccount, UserDAOImpl.WITHACCOUNT_METHOD);
        return user;
    } 
    
    public static User getUserById(Long idUser) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        UserDAO userDao = factory.getUserDAO();
        User user = userDao.getUserByCondition(""+idUser, UserDAOImpl.ID_METHOD);
        return user;
    } 
    
    public static User updateUser(Long idUser, String newPassword) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        UserDAO userDao = factory.getUserDAO();
        User userToUpdate = userDao.getUserByCondition(""+idUser, UserDAOImpl.ID_METHOD);
        if(userToUpdate != null) {
        	userToUpdate.setPassword(newPassword);
        	userDao.update(userToUpdate);
        }
        return userToUpdate;
    }
}