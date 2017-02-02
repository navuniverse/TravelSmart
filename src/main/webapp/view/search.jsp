<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
<link type="text/css"
	href="${pageContext.request.contextPath}/css/menu.css" rel="stylesheet" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<style type="text/css">
div.ui-datepicker {
	font-size: 13px;
}
</style>


<!-- Javascript code for datepicker -->
<script src="${pageContext.request.contextPath}/js/datepicker.js"></script>

<script type="text/javascript">
	$(document).ready(function() {

		$.getJSON('${pageContext.request.contextPath}/findSource.action', {}, function(data4) {
			$("#fromCity").autocomplete({
				source : data4
			});
		});
		$("#fromCity").blur(function() {

			var fromCity = $("#fromCity").val();

			if (fromCity.length > 3) {

				$.getJSON('${pageContext.request.contextPath}/findDest.action', {
					fromCity : fromCity
				}, function(data) {

					$("#toCity").autocomplete({
						source : data
					});

				});
			}
		});

	});
</script>



<title>Flight Search</title>
</head>
<body>

	<div>
		<h4>${message}</h4>
		<c:if test="${message == NULL}">
			<h3>Search Domestic Flights</h3>
			<br>

			<form method="POST" action="actionSearch" id="searchform">
				<table>
					<tr>
						<td><input type="text" name="fromCity"
							placeholder="Source City" title="Travel From City" id="fromCity"
							required="required" autofocus="autofocus" /><br></td>

						<td><input style="margin-left: 50px;" type="text"
							name="toCity" placeholder="Destination City"
							title="Travel To City" id="toCity" required="required" /><br></td>
					</tr>
					<tr>
						<td colspan="2"><br> <input style="margin-left: 135px;"
							type="text" name="travelDate" id="datepicker"
							placeholder="Travel Date" title="Travel Date" required="required"
							 /></td>
					</tr>

				</table>
				<hr style="width: 20%;">
				<br>Adult(12+)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Children(2-12)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Infant(0-2)
				<br> <select size="1" id="combobox" name="adult">
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
				</select><select size="1" id="combobox" name="children"
					style="margin-left: 25px;">
					<option value="0">0</option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
				</select><select size="1" id="combobox" name="infant"
					style="margin-left: 25px;">
					<option value="0">0</option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
				</select><br> <br> <input type="submit" value="Search">
			</form>
		</c:if>
	</div>
</body>

<!-- JavaScript code to load dynamic data through AJAX -->
<script>
	$('#searchform').submit(function() { // catch the form's submit event
		$.ajax({ // create an AJAX call...
			data : $(this).serialize(), // get the form data
			type : $(this).attr('method'), // GET or POST
			url : $(this).attr('action'), // the file to call
			success : function(response) { // on success..
				$('#result').html(response); // update the DIV		
			}
		});
		return false; // cancel original event to prevent form submitting
	});
</script>
</html>