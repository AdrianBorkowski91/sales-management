package pl.salesmanagement.methods;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import pl.salesmanagement.model.Client;
import pl.salesmanagement.model.ClientModelForm;
import pl.salesmanagement.model.HistoryOfMeeting;
import pl.salesmanagement.model.Meeting;
import pl.salesmanagement.model.User;
import pl.salesmanagement.service.ClientService;

public class MethodsClient {
	
	public static final int MEETING_OBJECT=1;
	public static final int HISTORY_NOT_DESCRIPTIVE_OBJECT=2;
	public static final int HISTORY_DESCRIPTIVE_OBJECT=3;
	
	public static final int DELETE_METHOD=1;
	public static final int ADD_METHOD=2;
	
	public static Client clientEmpty(){
		Client client= new Client();
		return client;
	}
	
	public static Client clientEmpty(long idUser) {
		Client clientEmpty= new Client();
        clientEmpty.setIdUser(idUser);
        clientEmpty.setFirstname("");
        clientEmpty.setLastname("");
        clientEmpty.setAddress("");
        clientEmpty.setCity("");
        clientEmpty.setZipCode("");
        clientEmpty.setCompany("");
        clientEmpty.setNumberPhone("");
        clientEmpty.setEmail("");
		return clientEmpty;
	}
	
	public static Client findClientAfterId(HttpServletRequest request, long idClient) {
		Client clientEdit= new Client();
		
		List<Client> clients=  extracted(request);
		for (Client client : clients) {
			if(client.getIdClient()==idClient){
				clientEdit=client;
			}
		}
		return clientEdit;
	}
	
	@SuppressWarnings("unchecked")
	private static List<Client> extracted(HttpServletRequest request) {
		return (List<Client>) request.getSession().getAttribute("clients");
	}
	
	@SuppressWarnings("unchecked")
	public static void createNewListClients(HttpServletRequest request){
		if(request.getSession().getAttribute("clients")!=null){
			request.getSession().removeAttribute("clients");
			System.out.println("Stara lista klientów została usunięta");
		}
		
    	createListClients(request);
    	
    	List<ClientModelForm> clients= (List<ClientModelForm>) request.getSession().getAttribute("clients");
    	List<Meeting> meetings= (List<Meeting>) request.getSession().getAttribute("meetings");
    	List<HistoryOfMeeting> listHistoryNotDescriptive= (List<HistoryOfMeeting>) request.getSession().getAttribute("historynotdescriptive");
    	List<HistoryOfMeeting> listHistoryDescriptive= (List<HistoryOfMeeting>) request.getSession().getAttribute("historydescriptive");
    	
    	updateListClient(request, clients, meetings, listHistoryNotDescriptive, listHistoryDescriptive);
        System.out.println("Nowa lista klientów została wygenerowana.");
	}

	public static List<ClientModelForm> createListClients(HttpServletRequest request) {
		User user= (User) request.getSession().getAttribute("user");
        List<Client> clientsService= ClientService.getAllClients(user.getIdUser());
        List<ClientModelForm> clients= new ArrayList<ClientModelForm>();
        
        for (Client client : clientsService) {
        	ClientModelForm clientModelForm= new ClientModelForm(client);
			clients.add(clientModelForm);
		}

        request.getSession().setAttribute("clients", clients);
        return clients;
	}	
	
	public static void updateListClient(HttpServletRequest request, List<ClientModelForm> clients,List<Meeting> meetings, List<HistoryOfMeeting> listHistoryNotDescriptive, List<HistoryOfMeeting> listHistoryDescriptive) {
		List<ClientModelForm> newListClients= new ArrayList<>();
		
		for (ClientModelForm clientModelForm : clients) {
			int allMeetings=0;
			for (Meeting meeting : meetings) {
				if(meeting.getClient().getIdClient()==clientModelForm.getIdClient()){
					allMeetings++;
				}
			}
			for (HistoryOfMeeting historyNotDescriptive : listHistoryNotDescriptive) {
				if(historyNotDescriptive.getClient().getIdClient()==clientModelForm.getIdClient()){
					allMeetings++;
				}
			}
			for (HistoryOfMeeting historyDescriptive : listHistoryDescriptive) {
				if(historyDescriptive.getClient().getIdClient()==clientModelForm.getIdClient()){
					allMeetings++;
				}
			}
			ClientModelForm cmf= new ClientModelForm(clientModelForm);
			if(allMeetings==0){
				cmf.setDisabled("");
			}
			else{
				cmf.setDisabled("disabled");
			}
			cmf.setAllMeetings(allMeetings);
			newListClients.add(cmf);
		}
		
		request.getSession().removeAttribute("clients");
		request.getSession().setAttribute("clients", newListClients);
	}
	
	@SuppressWarnings("unchecked") 
	public static void modificationObject(HttpServletRequest request, int object, int method,  long idObject){
		List<ClientModelForm> clients= (List<ClientModelForm>) request.getSession().getAttribute("clients");
		List<ClientModelForm> newListClients= new ArrayList<>();
		
		if(object==MEETING_OBJECT){
			List<Meeting> meetings= (List<Meeting>) request.getSession().getAttribute("meetings");
			for (Meeting meeting : meetings) {
				if(meeting.getIdMeeting()==idObject){
					for (ClientModelForm client : clients) {
						if(client.getIdClient()== meeting.getClient().getIdClient()){
							ClientModelForm cmf= new ClientModelForm(client);
							int allMeetings= client.getAllMeetings();
							if(method==DELETE_METHOD){
								allMeetings--;
							}
							else if(method==ADD_METHOD){
								allMeetings++;
							}
							if(allMeetings==0){
								cmf.setDisabled("");
							}
							else{
								cmf.setDisabled("disabled");
							}
							cmf.setAllMeetings(allMeetings);
							newListClients.add(cmf);
						}
						else{
							newListClients.add(client);
						}
					}
				}
			}
		}
		if(object==HISTORY_NOT_DESCRIPTIVE_OBJECT){
			List<HistoryOfMeeting> histories= (List<HistoryOfMeeting>) request.getSession().getAttribute("historynotdescriptive");
			for (HistoryOfMeeting history : histories) {
				if(history.getIdHistoryMeeting()==idObject){
					for (ClientModelForm client : clients) {
						if(client.getIdClient()== history.getClient().getIdClient()){
							ClientModelForm cmf= new ClientModelForm(client);
							int allMeetings= client.getAllMeetings();
							if(method==DELETE_METHOD){
								allMeetings--;
							}
							else if(method==ADD_METHOD){
								allMeetings++;
							}
							if(allMeetings==0){
								cmf.setDisabled("");
							}
							else{
								cmf.setDisabled("disabled");
							}
							cmf.setAllMeetings(allMeetings);
							newListClients.add(cmf);
						}
						else{
							newListClients.add(client);
						}
					}
				}
			}
		}
		if(object==HISTORY_DESCRIPTIVE_OBJECT){
			List<HistoryOfMeeting> histories= (List<HistoryOfMeeting>) request.getSession().getAttribute("historydescriptive");
			for (HistoryOfMeeting history : histories) {
				if(history.getIdHistoryMeeting()==idObject){
					for (ClientModelForm client : clients) {
						if(client.getIdClient()== history.getClient().getIdClient()){
							ClientModelForm cmf= new ClientModelForm(client);
							int allMeetings= client.getAllMeetings();
							if(method==DELETE_METHOD){
								allMeetings--;
							}
							else if(method==ADD_METHOD){
								allMeetings++;
							}
							if(allMeetings==0){
								cmf.setDisabled("");
							}
							else{
								cmf.setDisabled("disabled");
							}
							cmf.setAllMeetings(allMeetings);
							newListClients.add(cmf);
						}
						else{
							newListClients.add(client);
						}
					}
				}
			}
		}
		request.getSession().removeAttribute("clients");
		request.getSession().setAttribute("clients", newListClients);
	}
}
