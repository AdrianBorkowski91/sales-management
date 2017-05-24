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
    
  </head>
  <body style="background: #f0ebeb;">

	<div align="center" style="width:400px; margin:0 auto;">
		<div align="center" class="col-sm-6 col-md-4" style="width: 400px;" >
	    <ul id="menu-product" style="margin-bottom: 0px;">
		   <li><a href="/SalesManagement/login">Zaloguj się</a></li>
		   <li><a href="/SalesManagement/register" >Zarejestruj się</a></li>
		</ul>
	      <div class="thumbnail" style="background: #d6d6d6; -webkit-box-shadow: 0 1px 1px rgba(0, 0, 0, .05); box-shadow: 0 1px 1px rgba(0, 0, 0, .05); border: 1px solid #aeaeae;">
			<div class="overlap" id="log-in">
			  <form class="form-signin" method="post" action="login">
 			  <input type="hidden" name="sessionRemove" value="sessionRemove"/>
		 	 	<span class="input-group">Nazwa użytkownika:<span style="color: red;">*</span></span>
		  		<input type="text" name="username" class="form-control" value="${userModelForm.username}" required autofocus />
		 	 	<span class="input-group">Hasło:<span style="color: red;">*</span></span>
		 	 	<div class="input-group">
		  			<input type="password" class="form-control" id="passwordfield1" name="password" value="${userModelForm.password}" required autofocus />
		  			<span class="input-group-addon " style="padding: 0px;"><span class="glyphicon glyphicon-eye-open btn-xs" id="1" style="margin: 0px; margin-left: 10px; margin-right: 10px;" ></span></span>
				</div>	 
				<br>
		  		<button type="submit" class="btn btn-default" style="width: 200px; margin-bottom: 10px;">Zaloguj się</button>		  		
			  </form>
			  <span>Nie pamiętam hasła: <a href="/SalesManagement/remiderpassword">Przypomnij hasło</a></span>
			</div>
			
			<c:if test="${not empty message }">
				<div class="alert alert-danger" role="alert" style="margin-bottom: 0px; margin-top: 5px;">
			  		<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true" ></span>
			  		<br>
			  		${message}
				</div>
			</c:if>				
	    	</div>		  	  
		</div>
	</div>
	
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
	
  </body>
</html>