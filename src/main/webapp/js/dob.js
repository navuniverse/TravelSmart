$(function() {
	$("#dob").datepicker({
		maxDate : "-18Y",
		changeMonth: true,
	    changeYear: true,
	    yearRange : "-100y:c+nn"
	});
});