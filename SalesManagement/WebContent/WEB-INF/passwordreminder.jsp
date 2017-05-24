<%@page import="pl.salesmanagement.model.UserModelForm"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Sales-Menagement</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="${pageContext.request.contextPath}/icon/icon.png">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/editor.bootstrap.css" type="text/css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.12.4.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-migrate-1.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
  
  </head>
  <body style="background: #f0ebeb;">

	<div align="center" style="width:400px; margin:0 auto;">
		<div align="center" class="col-sm-6 col-md-4" style="width: 100%;" >
	    <ul id="menu-product" style="margin-bottom: 0px;">
		   <li><a href="/SalesManagement/login">Zaloguj się</a></li>
		   <li><a href="/SalesManagement/register" >Zarejestruj się</a></li>
		</ul>
	      <div class="thumbnail" style="background: #d6d6d6; -webkit-box-shadow: 0 1px 1px rgba(0, 0, 0, .05); box-shadow: 0 1px 1px rgba(0, 0, 0, .05); border: 1px solid #aeaeae;">
			<div class="overlap" id="log-in">
			    <h4>Przypominanie hasła</h4>
			  	<div style="margin-bottom: 10px;">
			  		<span style="font-size: 11px;">Aby zresetować hasło, wprowadź poniżej swoją nazwę użytkownika lub adres e-mail. Jeżeli uda nam się znaleźć Twoje dane w bazie danych, zostanie wysłana wiadomość na Twój adres e-mail z instrukcją jak uzyskać ponownie dostęp.</span>	 	 	
		 	 	</div>
		 	 	
		 	 	<form method="post" action="remiderpassword">
		 	 	<input type="hidden" name="sessionRemove" value="sessionRemove"/>
		 	 		<input type="hidden" name="remider-method" value="username-method"/>
		 	 		<span class="input-group">Wyszukaj po nazwie użytkownika:</span>
		  			<input style="margin-bottom: 5px;" type="text" name="username" class="form-control" value="${usernamereturn}"/>
		  			<button type="submit" class="btn btn-default" style="width: 200px; margin-bottom: 10px;">Wyszukaj</button>
		 	 	</form>
		 	 	
		 	 	<form method="post" action="remiderpassword">
		 	 	<input type="hidden" name="sessionRemove" value="sessionRemove"/>
		 	 		<input type="hidden" name="remider-method" value="email-method"/>
		 	 		<span class="input-group">Wyszukaj po adresie e-mail:</span>
		  			<input style="margin-bottom: 5px;" type="email" name="email" class="form-control" value="${emailreturn}">
		  			<button type="submit" class="btn btn-default" style="width: 200px; margin-bottom: 10px;">Wyszukaj</button>
				</form>
			</div>
			
			<c:choose>
				<c:when test="${not empty message}">
					<div class="alert alert-danger" role="alert" style="margin-bottom: 0px; margin-top: 5px;">
		  				<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
		  				<br>
		  				${message}
					</div>
				</c:when>	
				<c:when test="${not empty messageAboutAccept}">
					<div class="alert alert-success" role="alert" style="margin-bottom: 0px; margin-top: 5px;">
						<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
						<br>
						${messageAboutAccept}
					</div>
				</c:when>				
			</c:choose>	
					
	       </div>		  	  
		</div>
	</div>

  </body>
</html>