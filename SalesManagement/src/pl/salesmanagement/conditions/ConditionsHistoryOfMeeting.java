package pl.salesmanagement.conditions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class ConditionsHistoryOfMeeting {
	
	private static List<String> emptyParam= new ArrayList<String>();
	
	private static final String HISTORY_MESSAGE1="-Pola oznaczone gwiazdką muszą być uzupełnione.";
	public static final String HISTORY_MESSAGE_ACCEPT_TO_EDIT = "Operacja zakończona pomyślnie- Twoje spotkanie zostało przekierowane do trybu edycji. ";
	public static final String HISTORY_MESSAGE_ACCEPT_TO_EDIT2 = "Operacja zakończona pomyślnie- spotkanie zostało zapisane. ";
	
	public static boolean researchFormCreateHistory(HttpServletRequest request, long idEffect) throws ServletException, IOException {
		String message0= researchIsMessage(request, idEffect);

		if(message0!=null){
            return true;
		}
        return false;
	}
	
	private static String researchIsMessage(HttpServletRequest request, long idEffect) {
		if(!emptyParam.isEmpty()){
			emptyParam.clear();
		}
		
		String message0=null;

		if(idEffect==0){
            request.setAttribute("message1", HISTORY_MESSAGE1);
            message0= messageHead(request, message0);
            emptyParam.add("effect");
		}
		
		if(!emptyParam.isEmpty()){
			request.setAttribute("emptyParam", emptyParam);
		}
		
		return message0;
	}
	
	private static String messageHead(HttpServletRequest request, String message0) {
		if(message0==null){
			message0="Wystąpiły błędy w formularzu: ";
			request.setAttribute("message0", message0);
		}
		return message0;
	}
	
	
}
