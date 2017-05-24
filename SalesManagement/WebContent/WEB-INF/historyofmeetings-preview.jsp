<%@page import="pl.salesmanagement.model.Meeting"%>
<%@page import="pl.salesmanagement.methods.MethodsClient"%>
<%@page import="pl.salesmanagement.model.Client"%>
<%@page import="pl.salesmanagement.model.Province"%>
<%@page import="pl.salesmanagement.model.Industry"%>
<%@page import="pl.salesmanagement.model.Goal"%>
<%@page import="pl.salesmanagement.model.Effect"%>
<%@page import="pl.salesmanagement.methods.MethodsMeeting"%>
<%@page import="pl.salesmanagement.methods.MethodsHistoryOfMeeting"%>
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
    <script src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/dataTables.editor.js"></script>    
   	<script src="${pageContext.request.contextPath}/resources/js/timepicker.js"></script>
   	<script src="${pageContext.request.contextPath}/resources/js/timepicker-input.js"></script>
	
  </head>
  <body>

  	<input type="hidden" id="menu-navbar" value="menu-3">
	<%@ include file="../fragment/navbar.jspf" %>
   	
    <form method="post" action="historyofmeetings-preview">
    				
	<input type="hidden" name="id-history" value="${history.idHistoryMeeting}">
	<input type="hidden" name="id-client" value="${history.client.idClient}">
	
	<div align="center">
		<h3 align="center" style="display: inline-block; margin-top: 5px;">Podgląd spotkania</h3>
	</div>
	<hr style="margin: 10px;">
	
	<ol class="breadcrumb">
		<li><a href="#"><button type="submit" name="button" value="return" style=" background-color: transparent; border: 0px solid;">Historia spotkań</button></a></li>
  		<li class="active">Podgląd spotkania</li>
	</ol>
	
	<div class="row" style="margin: 10px; width: 1330px;">
		<div class="col-sm-6 col-md-12">
			<div class="thumbnail">
				<div class="caption">
						
					<div class="panel panel-default" style="display: inline-table; width: 32%; height:650px; margin: 7px;">		
						<div class="panel-heading">Rezultat spotkania:</div>
						<div class="panel-body">
								<span class="input-group">Efekt spotkania:</span>
								<input class="form-control" type="text" name="effect" value="${Effect.findTheEffectNameAfterId(history.idEffect)}" disabled>
			  					<span class="input-group">Obrót ze spotkania:</span>
								<div class="input-group">
			  						<input class="form-control"  type="text" name="rotation" value='${MethodsHistoryOfMeeting.formMoney(history.rotation)}' disabled>
			  						<span class="input-group-addon" style="padding: 0px;"><span class="glyphicon glyphicon-piggy-bank btn-xs" style="margin: 0px; margin-left: 10px; margin-right: 10px;" ></span></span>
								</div>	 
								<span class="input-group">Opis:</span>
			  					<textarea class="form-control" maxlength="650" style= "resize: none; height: 276px;" name="description" disabled>${history.description}</textarea>
								<div align="center" class="input-group" style="width: 30%; margin-top:115px; display: inline-block;" >
								  	<button type="submit" name="button" value="edit-return" class="btn btn-default" style="width: 200px; background: grey; border: 1px solid #7e7e7e; margin-bottom: 10px;"><span class="glyphicon glyphicon-share"></span>Przerzuć do edycji</button>
								</div>	
						</div>
					</div>
					
					<div class="panel panel-default" style="display: inline-table; width: 32%; height:650px; margin: 7px;">
						<div class="panel-heading">Opis spotkania:</div>
						<div class="panel-body">
							<span class="input-group">Data:</span>
							<div class="input-group">
		  						<input class="form-control" id="date" name="date" placeholder="YYYY-MM-DD" type="text" data-date-start-date="0d" name="date"  value="${history.meeting.date}" disabled>
		  						<span class="input-group-addon " style="padding: 0px;"><span class=" glyphicon glyphicon-calendar btn-xs" id="date" style="margin: 0px; margin-left: 10px; margin-right: 10px;" ></span></span>
							</div>	 
							<span class="input-group">Godzina rozpoczęcia:</span>
							<div class="input-group bootstrap-timepicker timepicker">
					            <input id="timepickerP" type="text" class="form-control input-small" name="time-start" value="${history.meeting.timeStart}" disabled>
					            <span class="input-group-addon " style="padding: 0px;"><span class=" glyphicon glyphicon-time btn-xs" id="timepickerP" style="margin: 0px; margin-left: 10px; margin-right: 10px;" ></span></span>
					        </div>
					        <span class="input-group">Godzina zakończenia:</span>
							<div class="input-group bootstrap-timepicker timepicker">
					            <input id="timepickerZ" type="text" class="form-control input-small" name="time-end" value="${history.meeting.timeEnd}" disabled>
					            <span class="input-group-addon " style="padding: 0px;"><span class=" glyphicon glyphicon-time btn-xs" id="timepickerZ" style="margin: 0px; margin-left: 10px; margin-right: 10px;" ></span></span>
					        </div>
							<span class="input-group">Cel spotkania:</span>
								<input type="text" class="form-control" name="goal" value="${Goal.findTheGoalNameAfterId(history.meeting.idGoal)}" disabled>
							<span class="input-group">Opis:</span>		  
							<textarea class="form-control" maxlength="650" style= "resize: none; height: 276px;" name="description" disabled>${history.meeting.description}</textarea>
						</div>
					</div>
	
					<div class="panel panel-default" style="display: inline-table; width: 32%; height:650px; margin: 7px;">
						<div class="panel-heading">Dane klienta:</div>
						<div class="panel-body" style="padding-top: 3px;">
							<ul class="list-group" >
							  <li class="list-group-item" style="border: 0px; padding:2px;">
							  	  <span style="font-weight: bold;" >Imię i nazwisko:</span>
							  	  <span class="form-control" style="background-color: #eee;">${history.client.firstname} ${history.client.lastname}</span>
							  </li>
							  <li class="list-group-item" style="border: 0px; padding:2px;">
							  	  <span style="font-weight: bold;" >Województwo:</span>
							  	  <span class="form-control" style="background-color: #eee;">${Province.findTheProvinceNameAfterId(history.client.idProvince)}</span>
							  </li>
							  <li class="list-group-item" style="border: 0px; padding:2px;">
							  	  <span style="font-weight: bold;" >Adres:</span>
							  	  <span class="form-control" style="background-color: #eee;">${history.client.address}</span>
							  </li>
							  <li class="list-group-item" style="border: 0px; padding:2px;">
							  	  <span style="font-weight: bold;" >Miasto:</span>
							  	  <span class="form-control" style="background-color: #eee;">${history.client.city}</span>
							  </li>
							  <li class="list-group-item" style="border: 0px; padding:2px;">
							  	  <span style="font-weight: bold;" >Kod pocztowy:</span>
							  	  <span class="form-control" style="background-color: #eee;">${history.client.zipCode}</span>
							  </li>
							  <li class="list-group-item" style="border: 0px; padding:2px;">
							  	  <span style="font-weight: bold;" >Nazwa firmy:</span>
							  	  <span class="form-control" style="background-color: #eee;">${history.client.company}</span>
							  </li>
							  <li class="list-group-item" style="border: 0px; padding:2px;">
							  	  <span style="font-weight: bold;" >Branża:</span>
							  	  <span class="form-control" style="background-color: #eee;">${Industry.findTheIndustryNameAfterId(history.client.idIndustry)}</span>
							  </li>
							  <li class="list-group-item" style="border: 0px; padding:2px;">
							  	  <span style="font-weight: bold;" >Numer telefonu:</span>
							  	  <span class="form-control" style="background-color: #eee;">${history.client.numberPhone}</span>
							  </li>
							  <li class="list-group-item" style="border: 0px; padding:2px;">
							  	  <span style="font-weight: bold;" >E-mail:</span>
							  	  <span class="form-control" style="background-color: #eee;">${history.client.email}</span>
							  </li>
							</ul>
							<div align="center" class="input-group" style="width: 30%; padding-left: 3px;" >
								<button type="submit" name="button" value="edit-client" class="btn btn-primary" style="width: 200px; background: grey; border: 1px solid #7e7e7e;"><span class="glyphicon glyphicon-edit"></span>Edytuj klienta</button>
							</div>	
						</div>
					</div>
					
				</div>
			</div>					
		</div>
	</div>
	</form>
	
   <%@ include file="../fragment/footer.jspf" %>  
      
  </body>
</html>