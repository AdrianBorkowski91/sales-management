package pl.salesmanagement.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/*")
public class SessionsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

	@Override
    public void doFilter(ServletRequest request, ServletResponse response,
        FilterChain chain) throws IOException, ServletException {
    	
    	
    	request.setCharacterEncoding("UTF-8");
    	response.setCharacterEncoding("UTF-8");

    	HttpServletRequest req=(HttpServletRequest)request;

    	if(req.getParameter("sessionRemove")!=null && req.getSession().getAttribute("user")!=null){
    		req.getSession().removeAttribute("user");
    		req.getSession().removeAttribute("clients");
    		req.getSession().removeAttribute("meetings");
    		req.getSession().removeAttribute("historynotdescriptive");
    		req.getSession().removeAttribute("historydescriptive");
    		req.getSession().removeAttribute("listmontlyreport");
    		req.getSession().removeAttribute("listannualreport");
    		req.logout();
    		System.out.println("Sesja została usunięta. Użytkownik znowu musi się zalogować.");
    	}
    	
    	if(req.getSession().getAttribute("user")!=null){
    		System.out.println("Zalogowany użytkownik");
        	chain.doFilter(request, response);  	
        } 
        else { 
        	System.out.println("Niezalogowany użytkownik");
        	chain.doFilter(request, response);  	
        }
    }
 
    @Override
    public void destroy() {
    }
 
}
