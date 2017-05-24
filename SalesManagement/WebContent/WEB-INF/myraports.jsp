<%@page import="pl.salesmanagement.model.ReportModel"%>
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
    <link href="${pageContext.request.contextPath}/resources/dist/chartist.min.css"  rel="stylesheet" >
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap-datepicker.css" type="text/css" rel="stylesheet" >
    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.12.4.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-migrate-1.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/resources/dist/chartist.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap-datepicker.min.js"></script>	
	<script src="${pageContext.request.contextPath}/resources/js/datepicker-input-select.js"/></script>
	
  </head>
  <body onload="selectElementMethod()">
    
  	<input type="hidden" id="menu-navbar" value="menu-4">
	<%@ include file="../fragment/navbar.jspf" %>
	
	<div align="center">
		<h3 align="center" style="display: inline-block; margin-top: 5px;">Moje raporty</h3>
	</div>
	<hr style="margin: 10px;">
	
	<ol class="breadcrumb">
  		<li class="active">Moje raporty</li>
	</ol>
	
	<c:choose>
    	<c:when test="${not empty listannualreport}">
			<div class="row" style="margin: 10px;">
				<div class="col-sm-6 col-md-12"style="width: 1330px;">
					<div class="thumbnail" style="width: 1300px;">
									
						<div class="input-group" style="padding-left: 11px; padding-top: 11px;">
		 					<select class="form-control" name="type-of-method" id="type-of-method" onchange="selectElementMethod()" style="display: inline-block; width: 190px; height: 47px;">
								<option value="month-method">Rozkład miesięczny</option>
								<option value="year-method">Rozkład roczny</option>
							</select> 
							<select class="form-control" id="date-selection" id="date-selection" onchange="selectElementDate()" style="display: inline-block; width: 160px; height: 47px;">
							</select> 
		  				</div>
								
						<script type="text/javascript">
						function selectElementMethod() {
							var method= document.getElementById('type-of-method').value;
							
							var date= document.getElementById('date-selection');
							if(date.length > 0){
								var i;	
								var length=date.length;
							
								for(i=length-1; i>=0; i--){
									date.remove(i);
								}
							}
							
							if(method == 'month-method'){
								document.getElementById("chart-months").style.display = "inline-table";
								document.getElementById("chart-year").style.display = "none";
								
								<c:forEach var="report" items="${listmonthlyreport}">	
									var option= document.createElement("option");
									option.text = "${report.formatDateToSelect}";
									date.add(option);
								</c:forEach>
								
							}
							else{
								document.getElementById("chart-months").style.display = "none";
								document.getElementById("chart-year").style.display = "inline-table";		
									
								<c:forEach var="report" items="${listannualreport}">		
									var option= document.createElement("option");
									option.text = "${report.formatDateToSelect}";
									date.add(option);
								</c:forEach>
								
							}
							selectElementDate();
						}
						function selectElementDate() {
							var method= document.getElementById('type-of-method').value;
							var date= document.getElementById('date-selection').value;
							
							if(method=='month-method'){
								<c:forEach var="report" items="${listmonthlyreport}">	
									var report= '${report.formatDateToSelect}'
									if(report==date){
										 document.getElementById("togetherMeetings").innerText = '${report.togetherMeetings}';
										 document.getElementById("togetherMeetingsTook").innerText = '${report.togetherMeetingsTook}';
										 document.getElementById("allRotationWithMeetings").innerText= '${ReportModel.moneyFormat(report.allRotationWithMeetings)}';
										 document.getElementById("averageRotationWithMeetings").innerText= '${ReportModel.moneyFormat(report.averageRotationWithMeetings)}';
										 document.getElementById("averageRotationWithAgreement").innerText= '${ReportModel.moneyFormat(report.averageRotationWithAgreement)}';
										 document.getElementById("theBestClient").innerText= '${report.theBestClient}';	 
										 document.getElementById("rotationTheBestClient").innerText= '${ReportModel.moneyFormat(report.rotationTheBestClient)}';
										 
										 new Chartist.Bar('.chart-goals', {
											  labels: ['wstępna rozmowa', 'prezentacja handlowa', 'podpisanie umowy', 'nieokreślone'],
											  series: ['${report.goalList.get(1).intValue()}', '${report.goalList.get(2).intValue()}', '${report.goalList.get(3).intValue()}', '${report.goalList.get(4).intValue()}']
										 }, {
											  distributeSeries: true,
											  axisY: {
												    onlyInteger: true,
												  }
										 });
										 new Chartist.Bar('.chart-effects', {
											  labels: ['spotkanie nie odbyło się', 'spotkanie bez oczekiwanego efektu', 'umówiony na kolejne spotkanie', 'podpisana umowa'],
											  series: ['${report.effectList.get(1).intValue()}', '${report.effectList.get(2).intValue()}', '${report.effectList.get(3).intValue()}', '${report.effectList.get(4).intValue()}']
										 }, {
											  distributeSeries: true,
											  axisY: {
												    onlyInteger: true,
												  }
										 });
									}
								</c:forEach>	
							}
							else if(method=='year-method'){
								<c:forEach var="report" items="${listannualreport}">	
								var report= '${report.formatDateToSelect}'
									if(report==date){
										 document.getElementById("togetherMeetings").innerText = '${report.togetherMeetings}';
										 document.getElementById("togetherMeetingsTook").innerText = '${report.togetherMeetingsTook}';
										 document.getElementById("allRotationWithMeetings").innerText= '${ReportModel.moneyFormat(report.allRotationWithMeetings)}';
										 document.getElementById("averageRotationWithMeetings").innerText= '${ReportModel.moneyFormat(report.averageRotationWithMeetings)}';
										 document.getElementById("averageRotationWithAgreement").innerText= '${ReportModel.moneyFormat(report.averageRotationWithAgreement)}';
										 document.getElementById("theBestClient").innerText= '${report.theBestClient}';	 
										 document.getElementById("rotationTheBestClient").innerText= '${ReportModel.moneyFormat(report.rotationTheBestClient)}';
										
										 new Chartist.Line('.char-year', {
											  labels: ['styczeń', 'luty', 'marzec', 'kwiecień', 'maj', 'czerwiec', 'lipiec', 'sierpień', 'wrzesień', 'październik', 'listopad', 'grudzień', '+' ],
											  series: [
											    [
											    	<c:forEach var="month" items="${report.rotationList}">
											    		 '${month.getValue()}',
											    	</c:forEach>
											    	
											    ]
											  ]
											}, {
											  low: 0,
											  showArea: true
										});
									}
								</c:forEach>	
							}
						}
						</script>
			
						<div class="caption" style="width: 1300px; height: 573px;">
							<div class="panel panel-default" style="display: inline-table; width: 350px; margin: 2px;">	
								<div class="panel-heading">Ogólne parametry:</div>
								<div class="panel-body" style="padding-bottom: 0px;">
								<label >Spotkania</label>
									<ul class="list-group" >
									  <li class="list-group-item" style="border: 0px; padding:2px;">
									  	  <span>Łączna ilość spotkań:</span>
									  	  <span id="togetherMeetings" class="form-control" style="background-color: #eee; color: red;"></span>
									  </li>
									  <li class="list-group-item" style="border: 0px; padding:2px;">
									  	  <span>Łącznie odbytych spotkań:</span>
									  	  <span id="togetherMeetingsTook" class="form-control" style="background-color: #eee; color: red;"></span>
									  </li>
									  <li class="list-group-item" style="border: 0px; padding:2px;">
									  	  <span>Łączny obrót ze spotkań:</span>
									  	  <span id="allRotationWithMeetings" class="form-control" style="background-color: #eee; color: red;"></span>
									  </li>
									  <li class="list-group-item" style="border: 0px; padding:2px;">
									  	  <span>Średni obrót na spotkanie:</span>
									  	  <span id="averageRotationWithMeetings" class="form-control" style="background-color: #eee; color: red;"></span>
									  </li>
									  <li class="list-group-item" style="border: 0px; padding:2px;">
									  	  <span>Średni obrót na umowę:</span>
									  	  <span id="averageRotationWithAgreement" class="form-control" style="background-color: #eee; color: red;"></span>
									  </li>		
								</ul>
								<label >Klient miesiąca</label>
								<ul class="list-group" >				  
									  <li class="list-group-item" style="border: 0px; padding:2px;">
									  	  <span>Imię i nazwisko:</span>
									  	  <span id="theBestClient" class="form-control" style="background-color: #eee; color: red;"></span>
									  </li>
									  <li class="list-group-item" style="border: 0px; padding:2px;">
									  	  <span>Obrót:</span>
									  	  <span id="rotationTheBestClient" class="form-control" style="background-color: #eee; color: red;"></span>
									  </li>
									</ul>
								</div>
							</div>
							
							<div class="panel panel-default" style="display: inline-table; width: 908px; margin: 2px;" id="chart-months" >	
								<div class="panel-heading">Ogólne parametry:</div>
								<div class="panel-body"style="padding-bottom: 0px;">
								<ul class="list-group">
		  							<li class="list-group-item" style="font-weight: bold; height: 236px; border: 0px;">Cele spotkań:
			  							<div class="chart-goals" style="height: 220px;"></div>
		  							</li>
		  							<li class="list-group-item" style="font-weight: bold; height: 236px; border: 0px;">Efekt spotkań:
			  							<div class="chart-effects" style="height: 220px;"></div>
		  							</li>
								</ul>
								</div>
							</div>	
							
							<div class="panel panel-default" style="display:inline-table; width: 908px; margin: 2px;" id="chart-year">	
								<div class="panel-heading">Rozkład roczny sprzedaży:</div>
								<div class="panel-body" style="padding-bottom: 0px;">
									<ul class="list-group">
			  							<li class="list-group-item" style="font-weight: bold; border: 0px; height: 471px;">						
											<div class="char-year" style=" width: 99%; height: 470px;"></div>
										</li>
									</ul>			
								</div>
							</div>		
											
						</div>	
					</div>					
				</div>
			</div>
		</c:when>
	   	<c:otherwise>
	   		<p align="center">Żaden raport nie został wygenerowany. Na chwilę obecną nie ma żadnych, w pełni opisanych spotkań.</p>
	    </c:otherwise>
    </c:choose>

	<%@ include file="../fragment/footer.jspf" %>
	
  </body>
</html>