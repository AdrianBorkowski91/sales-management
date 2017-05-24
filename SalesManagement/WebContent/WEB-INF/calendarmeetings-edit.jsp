<%@page import="pl.salesmanagement.model.Province"%>
<%@page import="pl.salesmanagement.model.Client"%>
<%@page import="pl.salesmanagement.model.Industry"%>
<%@page import="pl.salesmanagement.methods.MethodsMeeting"%>
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
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap-iso.css" type="text/css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap-datepicker.css" type="text/css" rel="stylesheet" >
    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.12.4.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-migrate-1.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/dataTables.editor.js"></script>    
   	<script src="${pageContext.request.contextPath}/resources/js/timepicker.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap-datepicker.min.js"></script>	
	<script src="${pageContext.request.contextPath}/resources/js/datepicker-input.js"/></script>
	<script src="${pageContext.request.contextPath}/resources/js/timepicker.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/timepicker-input.js"></script>
	
</head>
<body>

    <c:if test="${not empty modal }">
		<body onLoad="$('#myModal').modal('show');">
  	</c:if>
  	
  	<input type="hidden" id="menu-navbar" value="menu-2">
	<%@ include file="../fragment/navbar.jspf" %>
   	
    <form method="post" action="calendarmeetings-edit">
    
    <input type="hidden" name="id-meeting" value="${meeting.idMeeting}">
    <input type="hidden" name="id-client" value="${meeting.client.idClient}">
    
    <c:if test="${not empty returnIdMeeting }"><input type="hidden" name="return-id-meeting" value="${returnIdMeeting}"></c:if>
    
	<div align="center">
		<h3 align="center" style="display: inline-block; margin-top: 5px;">Edytuj spotkanie: nieodbyte</h3>
	</div>
	<hr style="margin: 10px;">
	
	<ol class="breadcrumb">
		<li><a href="#"><button type="submit" name="button" value="return" style=" background-color: transparent; border: 0px solid;">Kalendarz spotkań</button></a></li>
  		<li class="active">Edytuj spotkanie: nieodbyte</li>
	</ol>
	
	<div class="row" style="margin: 10px; width: 1330px;">
		<div class="col-sm-6 col-md-12">
			<div class="thumbnail">
				<div class="caption">	
				
					<div class="panel panel-primary" style="display: inline-table; width: 32%;  height:650px; margin: 7px;">
						<div class="panel-heading">Opis spotkania:</div>
						<div class="panel-body">
							<span class="input-group">Data:<span style="color: red;">*</span></span>
							<div class="input-group">
		  						<input class="form-control" id="date" name="date" placeholder="YYYY-MM-DD" type="text" data-date-start-date="0d" name="date"  value="${meeting.date }" />
		  						<span class="input-group-addon " style="padding: 0px;"><span class=" glyphicon glyphicon-calendar btn-xs" id="date" style="margin: 0px; margin-left: 10px; margin-right: 10px;" ></span></span>
							</div>	 
							<span class="input-group">Godzina rozpoczęcia:<span style="color: red;">*</span></span>
							<div class="input-group bootstrap-timepicker timepicker">
					            <input id="timepickerP" type="text" class="form-control input-small" name="time-start" value="${meeting.timeStart }" />
					            <span class="input-group-addon " style="padding: 0px;"><span class=" glyphicon glyphicon-time btn-xs" id="timepickerP" style="margin: 0px; margin-left: 10px; margin-right: 10px;" ></span></span>
					        </div>
					        <span class="input-group">Godzina zakończenia:<span style="color: red;">*</span></span>
							<div class="input-group bootstrap-timepicker timepicker">
					            <input id="timepickerZ" type="text" class="form-control input-small" name="time-end" value="${meeting.timeEnd }" />
					            <span class="input-group-addon " style="padding: 0px;"><span class=" glyphicon glyphicon-time btn-xs" id="timepickerZ" style="margin: 0px; margin-left: 10px; margin-right: 10px;" ></span></span>
					        </div>
							<span class="input-group">Cel spotkania:<span style="color: red;">*</span></span>
							<select class="form-control" name="goal" id="goal">
								<option id="0" style="background-color: #eee;">wybierz cel</option>
							  	<option id="1">wstępna rozmowa</option>
							  	<option id="2">prezentacja handlowa</option>
								<option id="3">podpisanie umowy</option>
							   	<option id="4">nieokreślone</option>
							</select>
							<span class="input-group">Opis:</span>		  
							<textarea class="form-control" maxlength="650" style= "resize: none; height: 276px;" name="description" >${meeting.description }</textarea>
							<div align="center" class="input-group" style="width: 30%; margin-top:10px; display: inline-block;" >
							  	<button type="submit" name="button" value="save" class="btn btn-primary" style="width: 200px;"><span class="glyphicon glyphicon-floppy-open" aria-hidden="true"></span>Zapisz</button>
							</div>	
						</div>
					</div>
	
					<div class="panel panel-default" style="display: inline-table; width: 32%; height:650px; margin: 7px;">
						<div class="panel-heading">Dane klienta:</div>
						<div class="panel-body" style="padding-top: 3px;">					
							<ul class="list-group" >
							  <li class="list-group-item" style="border: 0px; padding:2px;">
							  	  <span style="font-weight: bold;" >Imię i nazwisko:</span>
							  	  <span class="form-control" style="background-color: #eee;">${meeting.client.firstname} ${meeting.client.lastname}</span>
							  </li>
							  <li class="list-group-item" style="border: 0px; padding:2px;">
							  	  <span style="font-weight: bold;" >Województwo:</span>
							  	  <span class="form-control" style="background-color: #eee;">${Province.findTheProvinceNameAfterId(meeting.client.idProvince)}</span>
							  </li>
							  <li class="list-group-item" style="border: 0px; padding:2px;">
							  	  <span style="font-weight: bold;" >Adres:</span>
							  	  <span class="form-control" style="background-color: #eee;">${meeting.client.address}</span>
							  </li>
							  <li class="list-group-item" style="border: 0px; padding:2px;">
							  	  <span style="font-weight: bold;" >Miasto:</span>
							  	  <span class="form-control" style="background-color: #eee;">${meeting.client.city}</span>
							  </li>
							  <li class="list-group-item" style="border: 0px; padding:2px;">
							  	  <span style="font-weight: bold;" >Kod pocztowy:</span>
							  	  <span class="form-control" style="background-color: #eee;">${meeting.client.zipCode}</span>
							  </li>
							  <li class="list-group-item" style="border: 0px; padding:2px;">
							  	  <span style="font-weight: bold;" >Nazwa firmy:</span>
							  	  <span class="form-control" style="background-color: #eee;">${meeting.client.company}</span>
							  </li>
							  <li class="list-group-item" style="border: 0px; padding:2px;">
							  	  <span style="font-weight: bold;" >Branża:</span>
							  	  <span class="form-control" style="background-color: #eee;">${Industry.findTheIndustryNameAfterId(meeting.client.idIndustry)}</span>
							  </li>
							  <li class="list-group-item" style="border: 0px; padding:2px;">
							  	  <span style="font-weight: bold;" >Numer telefonu:</span>
							  	  <span class="form-control" style="background-color: #eee;">${meeting.client.numberPhone}</span>
							  </li>
							  <li class="list-group-item" style="border: 0px; padding:2px;">
							  	  <span style="font-weight: bold;" >E-mail:</span>
							  	  <span class="form-control" style="background-color: #eee;">${meeting.client.email}</span>
							  </li>
							</ul>
							<div align="center" class="input-group" style="width: 30%; padding-left: 3px;" >
								<button type="submit" name="button" value="edit-client" class="btn btn-primary" style="width: 200px; background: grey; border: 1px solid #7e7e7e;"><span class="glyphicon glyphicon-edit"></span>Edytuj klienta</button>
							</div>	
						</div>
					</div>

					<div class="panel panel-default" style="display: inline-table; width: 32%; height:650px; margin: 7px;">
						<div class="panel-heading">Inne spotkania:</div>
						<div style="overflow-x: hidden; overflow-y: scroll; height: 520px; border: 1px solid #ececec;">
							<table class="table" > 
						    	<thead>	        
									<tr>
										<th>#</th>
										<th>Spotkanie</th>	
										<th>Klient</th>	
								    </tr>         
								</thead>
								<tbody>
									<% int index=1; %>
									<c:forEach var="meeting" items="${meetings}">									
										<tr style="font-size: 12px;" id="meeting-${meeting.idMeeting}">
											<td><%= index%></td>
												<td>	
													<button type="submit" name="button" value="edit-meeting ${meeting.idMeeting} ${meeting.client.idClient}" style="background-color: transparent; border: 0px solid; color: rgb(51, 122, 183);">
														${meeting.date} ${MethodsMeeting.formTime(meeting.timeStart.getHours())}:${MethodsMeeting.formTime(meeting.timeStart.getMinutes())}
													</button>
												</td>
											<td>${meeting.client.firstname} ${meeting.client.lastname}</td>
										</tr>
										<% index++; %>
				  					</c:forEach>    	     
								</tbody>		           
						  	</table>
					  	</div>
					  	<div >
						  	<div align="center" class="input-group" style="margin:15px; display: inline-block;" >
								<button type="submit" name="button" value="new" class="btn btn-primary" style="width: 200px;"><span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>Nowe spotkanie</button>
							</div>	
						</div>
					</div>

					<c:choose>
						<c:when test="${not empty message0 }">
						<div class="alert alert-danger" role="alert" style="margin: 7px; margin-top: 12px;">
							<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true" style="margin-top:0px;"></span>
							<br>
							${requestScope.message0}<br>
							<c:if test="${not empty message1}">${message1}<br></c:if>
							<c:if test="${not empty message2}">${message2}<br></c:if>
							<c:if test="${not empty message3}">${message3}<br></c:if>
						</div>
						</c:when>
						<c:when test="${not empty messageAboutAccept }">
							<div class="alert alert-success" role="alert" style="margin: 7px; margin-top: 12px;">
								<span class="glyphicon glyphicon-ok" aria-hidden="true" style="margin-top:0px;"></span>
								<br>
								${messageAboutAccept}
							</div>					 
						</c:when>
					</c:choose>
					
				</div>
			</div>			
		</div>
	</div>
	
	 	 <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="margin: 20px;">
			<div class="modal-dialog" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			      	<h4 class="modal-title">Czy chcesz zapisać dane?</h4>
			      </div>
			      <div class="modal-body">
			        Twoje dane nie zostały zapisane. Czy chcesz kontynuować zadane polecenie?
			      </div>
			      <div class="modal-footer">
			      	<input type="hidden" name="accept-method" value="${method}">
			        <button type="submit" name="button" value="accept" class="btn btn-default" >Kontynuuj</button>

			        <button class="btn btn-primary" data-dismiss="modal" aria-label="Close">Powrót</button>
			      </div>
			    </div>
			 </div>
			</div>
	</form>
	
	<%@ include file="../fragment/footer.jspf" %>
	
	<script>
		var idGoal= '${meeting.idGoal}';
		var selectClient= document.getElementById('goal').options.namedItem(idGoal).selected=true;
		
		var idMeeting= '${meeting.idMeeting}';
		var idMeetingSelected= 'meeting-'+idMeeting;
		document.getElementById(idMeetingSelected).style.backgroundColor = "#eee";
	</script>	
	
	<script type="text/javascript">  
	<c:forEach var="parameter" items="${emptyParam}">	
		document.getElementById('${parameter}').style.borderColor = "red";
	</c:forEach>
	</script>
     		     
  </body>
</html>