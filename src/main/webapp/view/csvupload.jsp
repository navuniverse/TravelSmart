<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<link type="text/css"
	href="${pageContext.request.contextPath}/css/link-button.css"
	rel="stylesheet" />
<link type="text/css"
	href="${pageContext.request.contextPath}/css/format.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css" />
<script src="${pageContext.request.contextPath}/js/main.js"></script>
<script>
	history.forward();
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload CSV File</title>
</head>
<body class="background">
	<br>
	<h3>Schedule Management using CSV File</h3>
	<h4>${message}</h4>
	<h4>
		Upload CSV File with following format only:<br>(ACTION, SCHEDULE
		ID, FLIGHT DATE, SOURCE DEPARTURE TIME, VIA ARRIVAL TIME, VIA
		DEPARTURE TIME, DESTINATION ARRIVAL TIME, FARE SOURCE-DESTINATION,
		DEAL ID, FARE SOURCE-VIA, FARE VIA-DESTINATION, AVAILABILITY ID,
		FLIGHT ID, AVAILABLE SOURCE-VIA, AVAILABLE VIA-DESTINATION, AVAILABLE
		SOURCE-DESTINATION, ROUTE ID, CONNECTOR ID)
	</h4>

	<form action="actionCSVUpload" method="POST"
		enctype="multipart/form-data">
		Select a CSV file to upload <input type="file" name="file"
			accept="text/csv" /> <br> <br> <input type="submit"
			value="Upload CSV" />
	</form>

</body>


<!-- JavaScript code to load dynamic data through AJAX -->
<script>
	$('#csvuploadform').submit(function() { // catch the form's submit event
		$.ajax({ // create an AJAX call...
			data : $(this).serialize(), // get the form data
			type : $(this).attr('method'), // GET or POST
			url : $(this).attr('action'), // the file to call
			success : function(response) { // on success..
				$('#pages').html(response); // update the DIV		
			}
		});
		return false; // cancel original event to prevent form submitting
	});
</script>
</html>