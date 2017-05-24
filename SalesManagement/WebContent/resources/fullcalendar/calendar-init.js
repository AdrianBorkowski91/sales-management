function run() {
	var selectClient = document.getElementById("client-select");
	var valueClient =selectClient.options[selectClient.selectedIndex].value;
	return valueClient;
}

function date(start){
	var date = start.format();
	var today = new Date();
	
	var ddT = today.getDate();
	var mmT = today.getMonth()+1; 
	var yyyyT = today.getFullYear();
	
	var ddC = date.substring(8, 10); 
	var mmC = date.substring(5, 7); 
	var yyyyC = date.substring(0, 4);
	
	if(ddT<10){
	    ddT='0'+ddT;
	} 
	if(mmT<10){
	    mmT='0'+mmT;
	} 

	var todayF = yyyyT+'-'+mmT+'-'+ddT;
	var chosenF = yyyyC+'-'+mmC+'-'+ddC;
	
	
	if (yyyyT < yyyyC){
		document.getElementById('dateInput').value = start.format();
		document.getElementById('dateInputHidden').value = start.format();
	}
	else if(yyyyT == yyyyC){
		if(mmT < mmC){
			document.getElementById('dateInput').value = start.format();
			document.getElementById('dateInputHidden').value = start.format();
		}
		else if(mmT == mmC){
			if(ddT <= ddC){
				document.getElementById('dateInput').value = start.format();
				document.getElementById('dateInputHidden').value = start.format();
			}
			else{
				document.getElementById('dateInput').value = todayF;
				document.getElementById('dateInputHidden').value = todayF;
			}
		}
		else{
			document.getElementById('dateInput').value = todayF;
			document.getElementById('dateInputHidden').value = todayF;
		}
	}
	else{
		document.getElementById('dateInput').value = todayF;
		document.getElementById('dateInputHidden').value = todayF;
	}
}



