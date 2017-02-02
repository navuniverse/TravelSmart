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

<script>
	history.forward();
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Flight</title>
</head>
<body class="background">
	<br> 
	<h3>Update Flight</h3>
	<h4>${message}</h4>
	<form action="actionUpdate" method="POST" id="updateflightform">
		<table>
			<tr>
				<td>Flight ID:</td>
				<td><input type="text" name="flightId" required="required"
					autofocus="autofocus" placeholder="Flight ID" /></td>
			</tr>
			<tr>
				<td>Flight Capacity:</td>
				<td><input type="number" name="capacity" required="required"
					pattern="[0-9]{2,3}" placeholder="Capacity" maxlength="3" /></td>
			</tr>
			<tr>
				<td>Provider Name:</td>
				<td><input type="text" name="provider" required="required"
					placeholder="Provider"
					pattern="^([a-zA-Z]+(_[a-zA-Z]+)*)(\s([a-zA-Z]+(_[a-zA-Z]+)*))*$" /></td>
			</tr>
			<tr>
				<td>Flight Type</td>
				<td><select id="combobox" name="flightType" style="width:150px;">
						<option value="NONSTOP">NON-STOP</option>
						<option value="STOP">STOP</option>
				</select></td>
			</tr>

		</table>
		<br><input type="submit" value="Update Flight" />
	</form>

</body>


<!-- JavaScript code to load dynamic data through AJAX -->
<script>
	$('#updateflightform').submit(function() { // catch the form's submit event
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