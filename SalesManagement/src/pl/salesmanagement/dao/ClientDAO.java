package pl.salesmanagement.dao;

import java.util.List;

import pl.salesmanagement.model.Client;

public interface ClientDAO extends  GenericDAO<Client, Long> {
	
	List<Client> getAll(Long idUser);
}
