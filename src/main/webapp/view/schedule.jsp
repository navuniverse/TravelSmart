<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
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
<title>Flight Schedule</title>
</head>
<body class="background">
	<br> ${message}
	<h3>Schedule of flight ${flightId}</h3>
	<c:if test="${schedule.size()==0}">
		<h4>No Schedule available for this flight.</h4>
	</c:if>
	<c:if test="${schedule.size() > 0}">
		<table class="datagrid">
			<tr>
				<th>Schedule ID</th>
				<th>Date</th>
				<th>Route</th>
				<th>Time</th>
			</tr>


			<c:forEach items="${schedule}" var="element">
				<tr>
					<td>${element.scheduleId.scheduleId}</td>
					<td>${element.scheduleId.flightDate}</td>
					<c:if test="${element.routeId.via==null}">
						<td>${element.routeId.source} &#8594;
							${element.routeId.destination}</td>
						<td>${element.scheduleId.sourceDepartureTime} &#8594;
							${element.scheduleId.destinationArrivalTime}</td>
					</c:if>
					<c:if test="${element.routeId.via!=null}">
						<td>${element.routeId.source} &#8594; ${element.routeId.via}
							&#8594; ${element.routeId.destination}</td>
						<td>${element.scheduleId.sourceDepartureTime} &#8594;
							${element.scheduleId.viaArrivalTime} &#8594;
							${element.scheduleId.viaDepartureTime} &#8594;
							${element.scheduleId.destinationArrivalTime}</td>
					</c:if>
				</tr>
			</c:forEach>

		</table>
	</c:if>
	<br>
	<br>
	<button onclick="window.open('<c:url value="createSchedulePDF" />');">Print
		or Download</button>
	<br>
	<br>
</body>
</html>