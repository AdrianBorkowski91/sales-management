package pl.salesmanagement.conditions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class ConditionsClient {
	
	private static List<String> emptyParam= new ArrayList<String>();
	
	private static final String CLIENT_MESSAGE1="-Pola oznaczone gwiazdką muszą być uzupełnione.";
	private static final String CLIENT_MESSAGE2="-Nieprawidłowy format kodu pocztowego. Prawidłowy format: [xx-xxx]. ";
	private static final String CLIENT_MESSAGE3="-Nieprawidłowy format numeru telefonu. Prawidłowy format: [xxxxxxxxx]. ";
	private static final String CLIENT_MESSAGE4="-Nieprawidłowy format adresu e-mailowego. ";
	public static final String CLIENT_MESSAGE_ACCEPT="Edycja zakończona pomyślnie";
	public static final String CLIENT_MESSAGE_ACCEPT_TO_EDIT="Operacja zakończona pomyślnie- klient został zapisany. "
			+ "Zostałeś przekierowany do trybu edycji.";
	
	public static boolean researchFormCreateClient(HttpServletRequest request,String firstname, String lastname, long idProvince, String city, String company, long idIndustry, String numberPhone, String zipCode, String email) throws ServletException, IOException {
		String message0= researchIsMessage(request, firstname, lastname, idProvince, city, company, idIndustry, numberPhone, zipCode, email);

		if(message0!=null){
            return true;
		}
        return false;
	}
	
	private static String researchIsMessage(HttpServletRequest request, String firstname, String lastname, long idProvince, String city, String company, long idIndustry, String numberPhone, String zipCode, String email) {       
		if(!emptyParam.isEmpty()){
			emptyParam.clear();
		}
		
		String message0=null;
		
		if(researchEmpty(request, firstname, lastname, idProvince, city, company, idIndustry, numberPhone)){
            request.setAttribute("message1", CLIENT_MESSAGE1);
            message0= messageHead(request, message0);
        }
        if(researchZipCode(zipCode)){
            request.setAttribute("message2", CLIENT_MESSAGE2);
            emptyParam.add("zip-code");
            message0= messageHead(request, message0);
        }
        if(researchNumberPhone(numberPhone)){
        	if(!(numberPhone.equals("") || numberPhone==null)){
        		emptyParam.add("number-phone");
	            request.setAttribute("message3", CLIENT_MESSAGE3);
	            message0= messageHead(request, message0);
        	}
        }
        if(researchEmail(email)){
        	emptyParam.add("email");
            request.setAttribute("message4", CLIENT_MESSAGE4);
            message0= messageHead(request, message0);
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
	
	private static boolean researchEmpty(HttpServletRequest request, String firstname, String lastname, long idProvince, String city, String company, long idIndustry, String numberPhone){ 

		if(firstname.equals("") || firstname==null){
			emptyParam.add("firstname");
		}
		if(lastname.equals("") || lastname==null){
			emptyParam.add("lastname");
		}
		if(idProvince==0){
			emptyParam.add("province");
		}
		if(city.equals("") || city==null){
			emptyParam.add("city");
		}
		if(company.equals("") || company==null){
			emptyParam.add("company");
		}
		if(idIndustry==0){
			emptyParam.add("industry");
		}
		if(numberPhone.equals("") || numberPhone==null){
			emptyParam.add("number-phone");
		}
		
		if(!emptyParam.isEmpty()){
			return true;
		}
		
	return false;
	}
	
	private static boolean researchZipCode(String zipCode){
		   final Pattern formatEmail = Pattern.compile("(\\d{2}-\\d{3})");
		   if(zipCode.length()==0){
	   		  return false;
	   	   }
			
	   	   Matcher mt = formatEmail.matcher(zipCode);	
	   	   	 	
	   	   if(mt.find()){
	   		  if(zipCode.length()==6)
	   			  return false;
	   	   }
		return true;
	}
	
	private static boolean researchNumberPhone(String numberPhone){
		   final Pattern formatEmail = Pattern.compile("\\d{9}");
	   	   Matcher mt = formatEmail.matcher(numberPhone);	
	   	   	 	
	   	   if(mt.find()){
	   		  if(numberPhone.length()==9)
	   			  return false;
	   	   }
		return true;
	}
	
	private static boolean researchEmail(String email){	
	final Pattern formatEmail = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		if(email.length()==0){
   			return false;
   		}
   		Matcher mt = formatEmail.matcher(email);		
   	   	 	
   	   	if(mt.find()){
   	   		return false;
   	   	}

	return true;	
	}          
}
