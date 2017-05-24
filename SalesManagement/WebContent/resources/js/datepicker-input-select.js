 
$(document).ready(function(){
      var date_input=$('input[name="date"]'); //our date input has the name "date"
      var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
      var options={
        format: 'yyyy-mm-dd',
        container: container,
        todayHighlight: false,
        autoclose: true,
        startView: 1,
        minViewMode: 1,
        maxViewMode: 1,
        beforeShowMonth: function (date){
            if (date.getFullYear() == 2007) {
                return false;
              }
            },	 
        beforeShowYear: function (date){
            if (date.getFullYear() == 2017) {
                return false;
              }
            },
      };
      date_input.datepicker(options);
    })
    
$.fn.datepicker.dates['en'] = {
    days: ["Niedziela", "Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek", "Sobota"],
    daysShort: ["Nie", "Pon", "Wto", "Śro", "Czw", "Pią", "Sob"],
    daysMin: ["N", "Pn", "Wt", "Śr", "Cz", "Pt", "So"],
    months: ["Styczeń", "Luty", "Marzec", "Kwiecień", "Maj", "Czerwiec", "Lipiec", "Sierpień", "Wrzesień", "Październik", "Listopad", "Grudzień"],
    monthsShort: ["Sty", "Lut", "Mar", "Kwi", "Maj", "Cze", "Lip", "Sie", "Wrz", "Paź", "Lis", "Gru"],
    today: "Dziś",
    clear: "Wyczyść",
    format: 'yyyy-mm-dd',
    titleFormat: "yyyy MM", /* Leverages same syntax as 'format' */
    weekStart: 0
};
	
