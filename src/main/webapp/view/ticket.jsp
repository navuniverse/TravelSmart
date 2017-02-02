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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script>
	history.forward();
</script>
<title>Booking Confirmation</title>
</head>
<body class="background">
	<div>
		<br> 
		<h3>Congratulation!! Your Ticket has been booked successfully.</h3>
		<h4>${message}</h4>
		<h4>Your Ticket Details are</h4>
		<table border="1" style="color: #3b5998; padding: 5px;">
			<tr>
				<td>Flight Id: ${flight.flightId.flightId}</td>
				<td>Provider: ${flight.flightId.provider}</td>

			</tr>
			<tr>
				<td>Travel Date: ${travelDate}</td>
				<td>No. of Passengers: ${adult+children+infant}</td>
			</tr>


			<c:choose>
				<c:when
					test="${flight.routeId.source==fromCity && flight.routeId.destination==toCity}">
					<tr>
						<td>Source: ${fromCity}</td>
						<td>Departure Time: ${flight.scheduleId.sourceDepartureTime}</td>
					</tr>
					<tr>
						<td>Destination: ${toCity}</td>
						<td>Arrival Time: ${flight.scheduleId.destinationArrivalTime}</td>
					</tr>
					<tr>
						<td colspan="2">Total Fare: &#8377;${ticketdata.totalPrice}</td>
					</tr>
				</c:when>
				<c:when
					test="${flight.routeId.source==fromCity && flight.routeId.via==toCity }">
					<tr>
						<td>Source: ${fromCity}</td>
						<td>Departure Time: ${flight.scheduleId.sourceDepartureTime}</td>
					</tr>
					<tr>
						<td>Destination: ${toCity}</td>
						<td>Arrival Time: ${flight.scheduleId.viaArrivalTime}</td>
					</tr>
					<tr>
						<td>Total Fare: &#8377;${ticketdata.totalPrice}</td>
					</tr>
				</c:when>
				<c:when
					test="${flight.routeId.via==fromCity && flight.routeId.destination==toCity}">
					<tr>
						<td>Source: ${fromCity}</td>
						<td>Departure Time: ${flight.scheduleId.viaDepartureTime}</td>
					</tr>
					<tr>
						<td>Destination: ${toCity}</td>
						<td>Arrival Time: ${flight.scheduleId.destinationArrivalTime}</td>
					</tr>
					<tr>
						<td>Total Fare: &#8377;${ticketdata.totalPrice}</td>
					</tr>
				</c:when>
			</c:choose>
			<c:choose>
				<c:when test="${flight.routeId.via != null}">
					<tr>
						<td colspan="2">Route: ${flight.routeId.source} &#8594;
							${flight.routeId.via} &#8594; ${flight.routeId.destination}</td>

					</tr>
				</c:when>
				<c:otherwise>
					<td colspan="2">Route: ${flight.routeId.source} &#8594;
						${flight.routeId.destination}</td>
				</c:otherwise>
			</c:choose>

		</table>
		<br> <br>
		<button onclick="window.open('<c:url value="createTicketPDF" />');">Print
			or Download</button>
		<br> <br>
	</div>
</body>
</html>