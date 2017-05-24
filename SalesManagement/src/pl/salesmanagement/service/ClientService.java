package pl.salesmanagement.service;

import java.util.Comparator;
import java.util.List;

import pl.salesmanagement.dao.ClientDAO;
import pl.salesmanagement.dao.DAOFactory;
import pl.salesmanagement.model.Client;

public class ClientService {
	
	public static Client addClient(Client client) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        ClientDAO clientDao = factory.getClientDAO();
        client= clientDao.create(client);
        return client;
    }
	
   public static List<Client> getAllClients(Long idUser) {
       return getAllClients(null, idUser);
   }
    
   public static List<Client> getAllClients(Comparator<Client> comparator, Long idUser) {
       DAOFactory factory = DAOFactory.getDAOFactory();
       ClientDAO clientDao = factory.getClientDAO();
       List<Client> clients = clientDao.getAll(idUser);
       if(comparator != null && clients != null) {
    	   clients.sort(comparator);
       }
       return clients;
   }	

   public static Client updateClient(Client client) {
       DAOFactory factory = DAOFactory.getDAOFactory();
       ClientDAO clientDao = factory.getClientDAO();

       Client clientToUpdate = clientDao.read(client.getIdClient());
       if(clientToUpdate != null) {
       	clientToUpdate.setIdClient(client.getIdClient());
       	clientToUpdate.setFirstname(client.getFirstname());
       	clientToUpdate.setLastname(client.getLastname());
       	clientToUpdate.setIdProvince(client.getIdProvince());
       	clientToUpdate.setAddress(client.getAddress());
       	clientToUpdate.setCity(client.getCity());
       	clientToUpdate.setZipCode(client.getZipCode());
       	clientToUpdate.setCompany(client.getCompany());
       	clientToUpdate.setIdIndustry(client.getIdIndustry());
       	clientToUpdate.setNumberPhone(client.getNumberPhone());
       	clientToUpdate.setEmail(client.getEmail());
       	clientToUpdate.setActivity(client.getActivity());
       	
       	clientDao.update(clientToUpdate);
       }
       return clientToUpdate;
   }
   
   public static boolean deleteClient(Long idClient){
       DAOFactory factory = DAOFactory.getDAOFactory();
       ClientDAO clientDao = factory.getClientDAO();
       if(clientDao.delete(idClient)){
    		   return true;
       }
       return false;
   }
}
