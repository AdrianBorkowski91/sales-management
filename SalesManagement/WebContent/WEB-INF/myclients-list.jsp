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
  	  	
  	<input type="hidden" id="menu-navbar" value="menu-1">
	<%@ include file="../fragment/navbar.jspf" %>
	
	<div align="center">
		<h3 align="center" style="display: inline-block; margin-top: 5px;">Lista klientów</h3>
	</div>
	<hr style="margin: 10px;">
	
	<ol class="breadcrumb">
  		<li class="active">Lista klientów</li>
	</ol>

	<form method="post" action="myclients" id="form-myclients-list">
	
    	  <button type="button" onclick="window.location.href='../SalesManagement/myclients-new'" class="btn btn-primary" style="width: 200px; float:left; margin: 5px;  display: inline-block;" data-toggle="modal">Nowy klient<span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span></button>
			<table id="example" class="table table-striped table-hover"  class="display" >			
		        <thead>	        
		            <tr>
						<th>#</th>
						<th>Imię i nazwisko</th>
						<th>Firma</th>
					    <th>Województwo</th>
					    <th>Miasto</th>		       	
						<th>Łączny obrót</th>
						<th>Ostatnie spotkanie</th>
						<th>Aktywność</th>
						<th>Edytuj</th>
						<th>Usuń</th>	
		            </tr>         
		        </thead>
		        <tfoot>
		            <tr>
						<th>#</th>
						<th>Imię i nazwisko</th>
						<th>Firma</th>
					    <th>Województwo</th>
					    <th>Miasto</th>		       	
						<th>Łączny obrót</th>
						<th>Ostatnie spotkanie</th>
						<th>Aktywność</th>
						<th>Edytuj</th>
						<th>Usuń</th>	
		            </tr>
		        </tfoot>
		        <tbody>
		           <c:forEach var="client" items="${clients}">	  
		           <input type="hidden" name="id-client" value="${client.idClient}">
		                 <tr>
		                	<td></td>
		                    <td >${client.firstname} ${client.lastname}</td>
		                    <td >${client.company}</td>
		                    <td >${client.provinceName}</td>
		                    <td >${client.city}</td>
		                    <td >TEST </td>
		                    <td >TEST </td>
		                    <td ><input type="checkbox" ${client.activity} disabled></td>  
		                    <td ><button class="btn btn-default btn-xs" name="method" value="edit ${client.idClient}" style="width:35px; height: 35px; "><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></button></td>         
		                    <td ><button class="btn btn-default btn-xs" name="method" value="delete ${client.idClient}" style="width:35px; height: 35px; " ${client.disabled}><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></button></td>          	                 
		                 </tr>	          	     
		           </c:forEach>
		        </tbody>
		    </table>  
	</form>
	
	<%@ include file="../fragment/footer.jspf" %>
    
	<script>
		var form = document.getElementById("form-myclients-list");
		
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