<%@page import="pl.salesmanagement.model.Meeting"%>
<%@page import="pl.salesmanagement.methods.MethodsClient"%>
<%@page import="pl.salesmanagement.model.Client"%>
<%@page import="pl.salesmanagement.model.Province"%>
<%@page import="pl.salesmanagement.model.Goal"%>
<%@page import="pl.salesmanagement.methods.MethodsMeeting"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
  <head>
    <title>Sales-Menagement</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />  
    <link rel="icon" href="${pageContext.request.contextPath}/icon/icon.png">
	<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" type="text/css" rel="stylesheet">
  	<link href='${pageContext.request.contextPath}/resources/fullcalendar/fullcalendar.min.css' rel='stylesheet' />
	<link href='${pageContext.request.contextPath}/resources/fullcalendar/fullcalendar.print.min.css' rel='stylesheet' media='print' />
	<link href='${pageContext.request.contextPath}/resources/css/style-calendar.css' rel='stylesheet' />
    <script src="${pageContext.request.contextPath}/resources/jquery-1.12.4.js"></script>
    <script src="${pageContext.request.contextPath}/resources/jquery-migrate-1.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script> 
	<script src='${pageContext.request.contextPath}/resources/fullcalendar/lib/moment.min.js'></script>
	<script src='${pageContext.request.contextPath}/resources/fullcalendar/lib/jquery.min.js'></script>
	<script src='${pageContext.request.contextPath}/resources/fullcalendar/fullcalendar.min.js'></script>
	<script src='${pageContext.request.contextPath}/resources/fullcalendar/locale/pl.js'></script>
	<script src='${pageContext.request.contextPath}/resources//fullcalendar/calendar-init.js'></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/dataTables.editor.js"></script>    
 
  </head>
  <body>
  	  	
  	<input type="hidden" id="menu-navbar" value="menu-2">
	<%@ include file="../fragment/navbar.jspf" %>
    
	<div align="center">
		<h3 align="center" style="display: inline-block; margin-top:5px;">Kalendarz spotkań</h3>
	</div>
	<hr style="margin: 10px;">
	
	<ol class="breadcrumb">
  		<li class="active">Kalendarz spotkań</li>
	</ol>
	
   <form method="post" action="calendarmeetings" id="form-calendarmeetings-list">
	   
	   	<input type="hidden" id="dateInputHidden" name="date-input">
	   	<input type="hidden" id="paramEventHistoryDescriptive" name="paramEventHistoryDescriptive">
	   	<input type="hidden" id="paramEventHistoryNotDescriptive" name="paramEventHistoryNotDescriptive">
	   	<input type="hidden" id="paramEventMeeting" name="paramEventMeeting">
	   
	   	<div  class="panel panel-default" id='calendar' onclick="allEvents()" ></div>
	
	   	<div align="center" style="width: 100%; margin-top:10px;" >
			<button type="submit" name="button" value="new" class="btn btn-primary" style="width: 200px;"><span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>Nowe spotkanie</button>
			<input type="text" id="dateInput" disabled="disabled">
		</div>
	
	 	<div class="panel panel-default" style="margin-top:15px; display: inline-block; width: 100%;">	
				<table id="example" class="table table-striped table-hover" class="display">			
			        <thead>	        
			            <tr>
							<th>#</th>
							<th>Data</th>
							<th>Godzina</th>
							<th>Imię i nazwisko</th>
						    <th>Województwo</th>
						    <th>Miasto</th>		       	
							<th>Adres</th>
							<th>Status spotkania</th>
							<th>Edycja</th>
							<th>Usuń</th>	
			            </tr>         
			        </thead>
			        <tbody>
			           <c:forEach var="meeting" items="${meetings}">	  
			           <input type="hidden" name="id-meeting" value="${meeting.idMeeting}">
			                 <tr>
			                	<td></td>
			                    <td >${meeting.date}</td>
			                    <td >${MethodsMeeting.formTime(meeting.timeStart.getHours())}:${MethodsMeeting.formTime(meeting.timeStart.getMinutes())}</td>
			                    <td >${meeting.client.firstname} ${meeting.client.lastname}</td>
			                    <td >${Province.findTheProvinceNameAfterId(meeting.client.idProvince)}</td>
			                    <td >${meeting.client.city}</td>
			                    <td >${meeting.client.address}</td>
			                    <td >${Goal.findTheGoalNameAfterId(meeting.idGoal)}</td>  
			                    <td ><button class="btn btn-default btn-xs" name="button" value="edit ${meeting.idMeeting}" style="width:35px; height: 35px; "><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></button></td>         
			                    <td ><button class="btn btn-default btn-xs" name="button" value="delete ${meeting.idMeeting}" style="width:35px; height: 35px; "><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></button></td>          	                 
			                 </tr>	          	     
			           </c:forEach>		        
			        </tbody>
			 </table>  
		</div>	
	</form>
	
	<%@ include file="../fragment/footer.jspf" %>
	    
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
	<script type="text/javascript">
	$(document).ready(function() {
		
		$('#calendar').fullCalendar({
			header: {
				left: 'prev,next, today, title',
				right: 'month, agendaDay'
			},
			
			navLinks: true, 
			selectable: true,
			selectHelper: true,
			
			select: function(start, end) {
				date(start);	
			},
		    eventClick: function(calEvent, jsEvent, view) {
		    	
		    	var className= calEvent.className;
		    	
		    	if(className=='historyDescriptive'){
			    	var idHistory= calEvent.id;
					document.getElementById('paramEventHistoryDescriptive').value = idHistory;
					document.getElementById('paramEventHistoryNotDescriptive').value ='';
					document.getElementById('paramEventMeeting').value = '';
					document.getElementById("form-calendarmeetings-list").submit();
		    	}
		    	else if(className=='historyNotDescriptive'){
			    	var idHistory= calEvent.id;
			    	document.getElementById('paramEventHistoryDescriptive').value = '';
					document.getElementById('paramEventHistoryNotDescriptive').value = idHistory;
					document.getElementById('paramEventMeeting').value = '';
					document.getElementById("form-calendarmeetings-list").submit();
		    	}		    	
		    	else if(className=='meeting'){
			    	var idMeeting= calEvent.id;
			    	document.getElementById('paramEventHistoryDescriptive').value = '';
			    	document.getElementById('paramEventHistoryNotDescriptive').value ='';
					document.getElementById('paramEventMeeting').value = idMeeting;
					document.getElementById("form-calendarmeetings-list").submit();
		    	}

		    },
			editable: false,
			eventLimit: true,
			events: [
				
		    	<c:forEach var="historyDescriptive" items="${historydescriptive}">
		        {
		        	className : 'historyDescriptive',
		        	id     : '${historyDescriptive.idHistoryMeeting}',
	                title  : '${historyDescriptive.client.firstname} ${historyDescriptive.client.lastname}',
	                start  : '${historyDescriptive.meeting.date}T${historyDescriptive.meeting.timeStart}',
		            end    : '${historyDescriptive.meeting.date}T${historyDescriptive.meeting.timeEnd}',
		            allDay : false,
		            color  : 'grey',
		        },
		        </c:forEach>
				
		    	<c:forEach var="historyNotDescriptive" items="${historynotdescriptive}">
		        {
		        	className : 'historyNotDescriptive',
		        	id     : '${historyNotDescriptive.idHistoryMeeting}',
	                title  : '${historyNotDescriptive.client.firstname} ${historyNotDescriptive.client.lastname}',
	                start  : '${historyNotDescriptive.meeting.date}T${historyNotDescriptive.meeting.timeStart}',
		            end    : '${historyNotDescriptive.meeting.date}T${historyNotDescriptive.meeting.timeEnd}',
		            allDay : false,
		            color  : 'orange',
		        },
		        </c:forEach>
		        
		    	<c:forEach var="meeting" items="${meetings}">
		        {
		        	className : 'meeting',
		        	id     : '${meeting.idMeeting}',
	                title  : '${meeting.client.firstname} ${meeting.client.lastname}',
	                start  : '${meeting.date}T${meeting.timeStart}',
		            end    : '${meeting.date}T${meeting.timeEnd}',
		            allDay : false,	         
		        },
		        </c:forEach>
		    ]
			
		});
		
	});
	</script>

  </body>
</html>