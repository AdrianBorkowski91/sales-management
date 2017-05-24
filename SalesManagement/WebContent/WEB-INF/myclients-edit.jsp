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
  <body>

	  <c:if test="${not empty modal }">
		<body onLoad="$('#myModal').modal('show');">
	  </c:if>
	  
  	<input type="hidden" id="menu-navbar" value="menu-1">
	<%@ include file="../fragment/navbar.jspf" %>
   	
    <form method="post" action="myclients-edit">
	<div align="center">
		<h3 align="center" style="display: inline-block; margin-top: 5px;">Edytuj klienta</h3>
	</div>
	<hr style="margin: 10px;">
	
	<ol class="breadcrumb">
		<li><a href="#"><button type="submit" name="button" value="return" style=" background-color: transparent; border: 0px solid;">Lista klientów</button></a></li>
  		<li class="active">Edytuj klienta</li>
	</ol>
	
	<div class="row" style="margin: 10px; width: 1330px;">
		<div class="col-sm-6 col-md-12">
			<div class="panel panel-primary" >
				<div class="panel-heading">
		    		<h3 class="panel-title">Dane klienta:</h3>
		  		</div>
				<div class="caption">
		 		<input type="hidden" name="id-client" value="${client.idClient}">
			  	<div style="display: inline-table; width: 32%; margin: 5px;">
					<div class="input-group" style="width: 100%">	
					<label style="margin-bottom: 9px;">Dane osobowe</label>	
					  <span class="input-group">Imię:<span style="color: red;">*</span></span>
					  <input type="text" class="form-control" name="firstname" id="firstname" value="${client.firstname}"/>
					  <span class="input-group">Nazwisko:<span style="color: red;">*</span></span>
					  <input type="text" class="form-control" name="lastname" id="lastname" value="${client.lastname}"/>
					  <span class="input-group">Województwo:<span style="color: red;">*</span></span>
					  <select class="form-control" id="province" name="province" >
							<option id="0" style="background: #e3e3e3;">Wybierz wyjewództwo</option>
				  			<option id="1">dolnośląskie</option>
						 	<option id="2">kujawsko-pomorskie</option>
							<option id="3">lubelskie</option>
							<option id="4">lubuskie</option>		
							<option id="5">łódzkie</option>	
							<option id="6">małopolskie</option>	
							<option id="7">mazowieckie</option>	
							<option id="8">opolskie</option>	
							<option id="9">podkarpackie</option>		
							<option id="10">podlaskie</option>		
							<option id="11">pomorskie</option>		
							<option id="12">śląskie</option>	
				  			<option id="13">świętokrzyskie</option>		
				  			<option id="14">warmińsko-mazurskie</option>	
							<option id="15">wielkopolskie</option>		
				  			<option id="16">zachodniopomorskie</option>	  						  					  				
					  </select>
					  <span class="input-group">Miasto:<span style="color: red;">*</span></span>
					  <input type="text" class="form-control" name="city" id="city" value="${client.city}"/>  	
					  <span class="input-group">Adres:</span>
					  <input type="text" class="form-control" name="address" value="${client.address}">
					  <span class="input-group">Kod pocztowy:</span>
					  <input type="text" class="form-control" placeholder="xx-xxx" name="zip-code" id="zip-code" value="${client.zipCode}">
					</div>
				</div>
				
			  	<div style="display: inline-table; width: 32%; margin: 5px;">
				  	<div style="display: block;">
					<label style="margin-bottom: 9px;">Firma</label>
					  <span class="input-group">Nazwa firmy:<span style="color: red;">*</span></span>
					  <input type="text" class="form-control" name="company" id="company" value="${client.company}"/>
					  <span class="input-group">Branża:<span style="color: red;">*</span></span>
					  <select class="form-control" name="industry" id="industry">
						<option id="0" style="background: #e3e3e3;">Wybierz branże</option>
					  	<option id="1">przemysł</option>		
					  	<option id="2">budownictwo</option>		
					  	<option id="3">rolnictwo</option>		
					  	<option id="4">leśnictwo</option>
					  	<option id="5">transport</option>	
					  	<option id="6">łączność</option>	
					  	<option id="7">handel</option>	
					  	<option id="8">nieruchomości</option>	
					  	<option id="9">nauka i rozwój techniki</option>			
					  	<option id="10">oświata i wychowanie</option>		
					  	<option id="11">kultura i sztuka</option>		
					  	<option id="12">medycyna</option>		
					  	<option id="13">turystyka</option>		
					  	<option id="14">małe branże usługowe</option>  	
					  	<option id="15">usługi finansowe</option>  
					  	<option id="16">inne</option>  						  				
					</select>
					</div>
				</div>
			
			  	<div style="display: inline-table; width: 33%; margin: 5px; ">
				<label style="margin-bottom: 9px;">Dane kontaktowe</label>
				  <span class="input-group">Telefon:<span style="color: red;">*</span></span>
				  <input type="text" class="form-control"  name="number-phone" id="number-phone" placeholder="xxxxxxxxx (9 cyfr)" value="${client.numberPhone}"/>
				  <span class="input-group">E-mail:</span>
				  <input type="text" class="form-control" name="email" id="email" value="${client.email}"><br>
				  <div class="input-group">
						<span class="input-group-addon">
							<input type="checkbox" aria-label="activity" name="activity" ${client.activity}>
						</span>
						<span class="form-control" aria-label="activity">Aktywny klient</span>
				  </div>
				  <br>
				  <div align="center" class="input-group" style="width: 30%; margin-top:10px; display: inline-block;" >
					  <button type="submit" name="button" value="save"  class="btn btn-primary" style="width: 200px;"><span class="glyphicon glyphicon-floppy-open" aria-hidden="true"></span>Zapisz</button>
				  </div>	
				</div>	
		
				<c:choose>
					<c:when test="${not empty message0 }">
						<div class="alert alert-danger" role="alert" style="margin: 5px;">
					  		<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true" style="margin-top:0px;"></span>
					  		<br>
					  		${message0}<br>
					  		<c:if test="${not empty message1}">${message1}<br></c:if>
					  		<c:if test="${not empty message2}">${message2}<br></c:if>
					  		<c:if test="${not empty message3}">${message3}<br></c:if>
					  		<c:if test="${not empty message4}">${message4}<br></c:if>
						</div>
					</c:when>
					<c:when test="${not empty messageAboutAccept }">
						<div class="alert alert-success" role="alert" style="margin: 5px;">
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
		        <button type="submit" name="button" value="accept" class="btn btn-default" >Kontynuuj</button>
		        <button class="btn btn-primary" data-dismiss="modal" aria-label="Close">Powrót</button>
		      </div>
		    </div>
		 </div>
	   </div>
	 </form>

	 <%@ include file="../fragment/footer.jspf" %>
	 
	 <script>
		var idIndustry= '${client.idIndustry }';
		document.getElementById('industry').options.namedItem(idIndustry).selected=true;
		
		var idProvince= '${client.idProvince }';
		document.getElementById('province').options.namedItem(idProvince).selected=true;
		
	</script>
	
	<script type="text/javascript">  
	<c:forEach var="parameter" items="${emptyParam}">	
		document.getElementById('${parameter}').style.borderColor = "red";
	</c:forEach>
	</script>
	
  </body>
</html>