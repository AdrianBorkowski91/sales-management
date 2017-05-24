package pl.salesmanagement.dao;

import pl.salesmanagement.model.Account;

public interface AccountDAO extends  GenericDAO<Account, Long> {
	
	Account getAccountById(Long idAccount);
  
}
