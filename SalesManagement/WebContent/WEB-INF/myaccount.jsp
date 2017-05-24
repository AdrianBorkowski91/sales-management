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
  <body>

   	<input type="hidden" id="menu-navbar" value="menu-0">
	<%@ include file="../fragment/navbar.jspf" %>
   	
	<div align="center">
		<h3 align="center" style="display: inline-block; margin-top: 5px;">Moje konto</h3>
	</div>
	<hr style="margin: 10px;">
	
	<ol class="breadcrumb">
  		<li class="active">Moje konto</li>
	</ol>

	<div class="row" style="margin: 10px; margin-bottom:0px; width: 1350px;">
	
		<form method="post" action="myaccount">
			<div class="col-sm-6 col-md-4" style="padding:0px; width: 661px;  margin: 6px; display: inline-block;">
				<input type="hidden" name="my-account" value="account-settings"/>
				<div class="panel panel-primary" style="height: 290px;" >
		  			<div class="panel-heading">
		    			<h3 class="panel-title">Ustawienia konta:</h3>
		  			</div>
		  			<div class="panel-body">

						<div class="row" style="margin-bottom: 10px;">
						  <div class="col-lg-6" style="width: 100%;">
						    <div class="input-group">
						      <span class="input-group-addon">
						        <input type="checkbox" aria-label="reminder" name="reminder" ${sessionScope.user.account.reminder}>
						      </span>
						      <span class="form-control" aria-label="reminder">Włącz opcje przypominania o spotkaniach z dziennym wyprzedzeniem.</span>
						    </div>
						  </div>
						</div>
						<div class="row" >
						  <div class="col-lg-6" style="width: 100%;">
						    <div class="input-group">
						      <span class="input-group-addon">
						        <input type="checkbox" aria-label="raport-modified" name="raport-modified" ${sessionScope.user.account.raportModified}>
						      </span>
						      <span class="form-control" aria-label="raport-modified">Włącz zakładkę "Moje raporty".</span>
						    </div>
						  </div>
						</div>				
						<div align="center" class="input-group" style="width: 30%; margin-top:10px; display: inline-block;" >
		  					<button type="submit" class="btn btn-primary" style="width: 200px;"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>Zapisz</button>
						</div>	
			  		</div>
				</div>
			</div>
		</form>
		
		<form method="post" action="myaccount">
			<div class="col-sm-6 col-md-4" style="padding:0px; width: 661px; margin: 6px; display: inline-block;">
				<input type="hidden" name="my-account" value="user-settings"/>
					<div class="panel panel-primary" style="height: 290px;" >
		  			<div class="panel-heading">
		    			<h3 class="panel-title">Zmień hasło:</h3>
		  			</div>
		  			<div class="panel-body">
			         	<span class="input-group">Hasło:<span style="color: red;">*</span></span>
						<div class="input-group">
		  					<input type="password" class="form-control" id="passwordfield1" name="password" value="${userModelForm.oldPassword}">
		  					<span class="input-group-addon " style="padding: 0px;"><span class="glyphicon glyphicon-eye-open btn-xs" id="1" style="margin: 0px; margin-left: 10px; margin-right: 10px;" ></span></span>
						</div>	 
			         	<span class="input-group">Nowe hasło:<span style="color: red;">*</span></span>
						<div class="input-group">
		  					<input type="password" class="form-control" id="passwordfield2" name="newpassword" value="${userModelForm.password}">
		  					<span class="input-group-addon " style="padding: 0px;"><span class="glyphicon glyphicon-eye-open btn-xs" id="2" style="margin: 0px; margin-left: 10px; margin-right: 10px;" ></span></span>
						</div>	 
						<span class="input-group">Nowe hasło ponownie:<span style="color: red;">*</span></span>
						<div class="input-group">
		  					<input type="password" class="form-control" id="passwordfield3" name="repeatnewpassword" value="${userModelForm.repeatPassword}">
		  					<span class="input-group-addon " style="padding: 0px;"><span class="glyphicon glyphicon-eye-open btn-xs" id="3" style="margin: 0px; margin-left: 10px; margin-right: 10px;" ></span></span>
						</div>	  
						<div align="center" class="input-group" style="width: 30%; margin-top:10px; display: inline-block;" >
		  					<button type="submit" class="btn btn-primary" style="width: 200px;"><span class="glyphicon glyphicon-lock" aria-hidden="true"></span>Zmień hasło</button>
						</div>	 				
		  			</div>
				</div>
			</div>
		</form>
	</div>
	
	<c:choose>
		<c:when test="${not empty message }">
			<div class="alert alert-danger" role="alert" style="margin-left: 15px;" >
				<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true" style="margin-top:0px; margin-bottom: 0px;"></span>
				<br>
				${message}
			</div>
		</c:when>
		<c:when test="${not empty messageAboutAccept }">							
			<div class="alert alert-success" role="alert" style="margin-left: 15px;" >
				<span class="glyphicon glyphicon-ok" aria-hidden="true" style="margin-top:0px; margin-bottom: 0px;"></span>
				<br>
				${messageAboutAccept}
			</div>		
		 </c:when>
	</c:choose>		
	
	<%@ include file="../fragment/footer.jspf" %>
	
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
			
  </body>
</html>