<%@page import="pl.salesmanagement.model.Meeting"%>
<%@page import="pl.salesmanagement.methods.MethodsClient"%>
<%@page import="pl.salesmanagement.model.Client"%>
<%@page import="pl.salesmanagement.model.Province"%>
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
	
  </head>
  <body>
  	  	
  	<input type="hidden" id="menu-navbar" value="menu-3">
	<%@ include file="../fragment/navbar.jspf" %>
   
	<div align="center">
		<h3 align="center" style="display: inline-block; margin-top: 5px;">Historia sprzedaży</h3>
	</div>
	<hr style="margin: 10px;">
	
	<ol class="breadcrumb">
  		<li class="active">Historia sprzedaży</li>
	</ol>
	
	<c:if test="${not empty sessionScope.historynotdescriptive}">
	
		<form method="post" action="historyofmeetings">
		<input type="hidden" name="type-list" value="notdescriptive">
		
		<div class="panel panel-default" style="margin-top:15px; display: inline-block; width: 100%;">	
			<div role="separator" class="divider">
			
		 	<h4 align="center">Spotkania odbyte: nieopisane</h4>
		 		<h6 align="center">Poniższe spotkania już odbyły się. Wejdź w tryb edycji, aby dokonać opisu ich efektu.</h6>
		 		<hr style="margin: 10px; margin-top: 0px; margin-bottom: 5px;">
					<table id="exampleTwo" class="table table-striped table-hover" class="display">			
				        <thead>	              
				            <tr>
								<th width="2%x">#</th>
								<th width="10%">Data</th>
								<th width="10%">Godzina</th>
								<th width="16%">Imię i nazwisko</th>
							    <th width="12%">Województwo</th>
							    <th >Miasto</th>		       	
								<th >Adres</th>
								<th >Status spotkania</th>
								<th width="5%">Edycja</th>
								<th width="5%">Usuń</th>	
				            </tr>         
				        </thead>
				        <tbody>
				        	 <% int indeks=1; %>
				        	 <c:forEach var="history" items="${historynotdescriptive}">	  
				                 <tr>
				                	<td><%=indeks %></td>
				                    <td >${history.meeting.date}</td>
				                    <td >${MethodsMeeting.formTime(history.meeting.timeStart.getHours())}:${MethodsMeeting.formTime(history.meeting.timeStart.getMinutes())}</td>
				                    <td >${history.client.firstname} ${history.client.lastname}</td>
				                    <td >${Province.findTheProvinceNameAfterId(history.client.idProvince)}</td>
				                    <td >${history.client.city}</td>
				                    <td >${history.client.address}</td>
				                    <td >${Goal.findTheGoalNameAfterId(history.meeting.idGoal)}</td>  
				                    <td ><button class="btn btn-default btn-xs" name="button" value="edit ${history.idHistoryMeeting}" style="width:35px; height: 35px;"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></button></td>         
				                    <td ><button class="btn btn-default btn-xs" name="button" value="delete ${history.idHistoryMeeting}" style="width:35px; height: 35px; "><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></button></td>          	                 
				                 </tr>	  
				             <% indeks++; %> 
				             </c:forEach>       	     	        
				       </tbody>
				 	</table>  
				</div>
			</div>
		</form>
	</c:if>
	
	<form method="post" action="historyofmeetings" id="form-historyofmeetings" >
	<input type="hidden" name="type-list" value="descriptive">
	
		<div class="panel panel-default" style="margin-top:15px; display: inline-block; width: 100%;">	
			<div role="separator" class="divider">
		 	<h4 align="center">Spotkania odbyte: opisane</h4>
		 		<h6 align="center">Poniższe spotkania już odbyły się i ich efekt został opisany.</h6>
		 		<hr style="margin: 10px; margin-top: 0px; margin-bottom: 5px;">
					<table id="example" class="table table-striped table-hover"  class="display" >			
				        <thead>	        
				            <tr>
								<th width="2%">#</th>
								<th width="10%">Data</th>
								<th width="10%">Godzina</th>
							    <th width="16%">Imię i nazwisko</th>
							    <th width="12%">Województwo</th>		       	
								<th >Status spotkania</th>
								<th >Obrót ze spotkania</th>
								<th width="5%">Zobacz</th>
								<th width="5%">Usuń</th>
				            </tr>         
				        </thead> 
				        <tfoot>
				            <tr>
								<th width="2%">#</th>
								<th width="10%">Data</th>
								<th width="10%">Godzina</th>
							    <th width="16%">Imię i nazwisko</th>
							    <th width="12%">Województwo</th>		       	
								<th >Status spotkania</th>
								<th >Obrót ze spotkania</th>
								<th width="5%">Zobacz</th>
								<th width="5%">Usuń</th>	
				            </tr>
				        </tfoot>
				        <tbody>
				        	<c:forEach var="history" items="${historydescriptive}">	 
				                 <tr>
				                	<td></td>
				                    <td >${history.meeting.date}</td>
				                    <td >${MethodsMeeting.formTime(history.meeting.timeStart.getHours())}:${MethodsMeeting.formTime(history.meeting.timeStart.getMinutes())}</td>
				                    <td >${history.client.firstname} ${history.client.lastname}</td>
			                    	<td >${Province.findTheProvinceNameAfterId(history.client.idProvince)}</td>
				                    <td >${Effect.findTheEffectNameAfterId(history.idEffect)}</td>
				                    <td >${MethodsHistoryOfMeeting.formMoney(history.rotation)}</td>
				                    <td ><button class="btn btn-default btn-xs" name="button" value="edit ${history.idHistoryMeeting}" style="width:35px; height: 35px; "><span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span></button></td>         
				                    <td ><button class="btn btn-default btn-xs" name="button" value="delete ${history.idHistoryMeeting}" style="width:35px; height: 35px; "><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></button></td>          	                 
				                 </tr>	
				            </c:forEach>          	     
				        </tbody>
				</table>  
	 		</div>
	 	</div>
	</form>

   <%@ include file="../fragment/footer.jspf" %>
   
	<script>
		var form = document.getElementById("form-historyofmeetings");
		
		document.getElementById("edit-form").addEventListener("click", function () {
		  form.submit();
		});
	</script>
	
	<script>
	$(document).ready(function() {
	    var t = $('#example').DataTable( {
	        "columnDefs": [ {
	            "searchable": false,
	            "orderable": false,
	            "targets": 0
	        } ],
	        "order": [[ 1, 'asc' ]]
	    } );
	 
	    t.on( 'order.dt search.dt', function () {
	        t.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
	            cell.innerHTML = i+1;
	        } );
	    } ).draw();
	} );	
	</script>	
	
  </body>
</html>

