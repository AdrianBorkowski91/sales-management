package pl.salesmanagement.service;

import pl.salesmanagement.dao.AccountDAO;
import pl.salesmanagement.dao.DAOFactory;
import pl.salesmanagement.model.Account;

public class AccountService {
	
	public static Account addAccount() {
        Account account = new Account();
        DAOFactory factory = DAOFactory.getDAOFactory();
        AccountDAO accountDao = factory.getAccountDAO();
        account= accountDao.create(account);
        
        return account;
    }
	
    public static Account getAccountById(Long accountId) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        AccountDAO accountDao = factory.getAccountDAO();
        Account account = accountDao.read(accountId);
        return account;
    }
	
    public static Account updateAccount(Long idAccount, String reminder, String raportModified) {
    	DAOFactory factory = DAOFactory.getDAOFactory();
        AccountDAO accountDao = factory.getAccountDAO();
        Account accountToUpdate = accountDao.read(idAccount);

        if(accountToUpdate != null) {
        	accountToUpdate.setIdAccount(idAccount);
        	accountToUpdate.setReminder(reminder);
        	accountToUpdate.setRaportModified(raportModified);
        	
        	accountDao.update(accountToUpdate);
        }
        return accountToUpdate;
    }

}

