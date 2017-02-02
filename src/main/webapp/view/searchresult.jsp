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
<script src="${pageContext.request.contextPath}/js/main.js"></script>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>


<script src="${pageContext.request.contextPath}/js/jquery.dataTables.js"></script>

<script>
	$(document).ready(function() {
		$('#availableflights').dataTable({
			"bProcessing" : true, // Enable processing indicator
			"bServerSide" : false, // Configure data option - server or client
			"bSort" : true, // Enable sorting
			"bFilter" : true,
			"bAutoWidth" : true,
			"sPaginationType" : "full_numbers", // Paginate Type -> two_button (Previous && Next)
			//               -> full_numbers (First, Previous, 1, 2, Next, Last)    

			"iDisplayLength" : 4, //Number of result per page
			/* "sDom" : 'prt' //Only Pagination,Processing and table   */
			sDom : '<"H"f>t<"F"rpi>' //filter,processing, length change  table  information pagination
		//sDom : '<"H"l>t<"F"frip>'
		});
		$("#availableflights").css("width:100%");
	});
</script>

<style>
/* for table grid */
#availableflights_paginate {
	text-align: center;
	font-weight: bold;
	margin-right: 30px;
	margin-bottom: 10px;
}

#availableflights_info {
	text-align: center;
	font-weight: bold;
	margin-left: :10px;
}

#availableflights_filter {
	text-align: center;
	margin-right: 10px;
}

#availableflights_filter input {
	text-align: left;
	margin-top: 10px;
	margin-bottom: 10px;
	border-radius: 5px;
	padding: 8px 10px 5px 10px;
	margin-right: 10px;
	font-weight: 200;
	font-size: 14px;
	font-family: Verdana;
	box-shadow: 1px 1px 5px #CCC;
	width: 150px;
	height: 20px;
	border: 1px solid #CCC;
}

#availableflights_paginate a {
	padding-left: 10px;
	padding-right: 10px;
	margin-left: 5px;
	margin-right: 5px;
	box-shadow: 2px 2px 2px #888888;
	background-color: #3b5998;
	border-radius: 5px;
	color: white;
}
/* table grid ends */
</style>



<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script>
	history.forward();
</script>

<title>Search Result</title>
</head>

<body class="background">
	<div>
		<c:if test="${result.size()==0}">
			<h3>No flight available from ${fromCity} to ${toCity} on
				${travelDate}</h3>
			<h4>Please Search again.</h4>
		</c:if>
		<c:if test="${result.size()>0}">
			<form action="actionBook" method="POST" id="resultform">
				<%-- <h3>${result.size()} flight(s) available from ${fromCity} to
					${toCity} on ${travelDate}</h3> --%>
				<table class="datagrid" id="availableflights">
					<thead>
						<tr>
							<th>Flight Details</th>
							<th>Departure Time</th>
							<th>Arrival Time</th>
							<th>Duration</th>
							<th>Seats Availability</th>
							<th>Ticket Price</th>
							<th>Type &#38; Route</th>
							<th>Available Deal</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${result}" var="element">
							<tr>
								<td><img
									src="<c:url value ="/images/${element.flightId.provider}.jpg"/>"
									alt="" /><br>${element.flightId.provider}<br>${element.flightId.flightId}</td>

								<c:choose>
									<c:when
										test="${element.routeId.source==fromCity && element.routeId.destination==toCity}">
										<td>${element.routeId.source}<br>${element.scheduleId.sourceDepartureTime}
										</td>
										<td>${element.routeId.destination}<br>${element.scheduleId.destinationArrivalTime}
										</td>
										<td>${element.scheduleId.duration}</td>
										<td>${element.availId.availSourceDestination}</td>
										<c:set var="availability"
											value="${element.availId.availSourceDestination}"
											scope="session" />
										<td>&#8377;${element.scheduleId.fareSourceDestination}</td>
									</c:when>
									<c:when
										test="${element.routeId.source==fromCity && element.routeId.via==toCity }">
										<td>${element.routeId.source}<br>${element.scheduleId.sourceDepartureTime}
										</td>
										<td>${element.routeId.via}<br>${element.scheduleId.viaArrivalTime}
										</td>
										<td>${element.scheduleId.duration}</td>
										<td>${element.availId.availSourceVia}</td>
										<c:set var="availability"
											value="${element.availId.availSourceVia}" scope="session" />
										<td>&#8377;${element.scheduleId.fareSourceVia}</td>
									</c:when>
									<c:when
										test="${element.routeId.via==fromCity && element.routeId.destination==toCity}">
										<td>${element.routeId.via}<br>${element.scheduleId.viaDepartureTime}
										</td>
										<td>${element.routeId.destination}<br>${element.scheduleId.destinationArrivalTime}
										</td>
										<td>${element.scheduleId.duration}</td>
										<td>${element.availId.availViaDestination}</td>
										<c:set var="availability"
											value="${element.availId.availViaDestination}"
											scope="session" />
										<td>&#8377;${element.scheduleId.fareViaDestination}</td>
									</c:when>
								</c:choose>
								<c:choose>
									<c:when test="${element.routeId.via != null}">
										<td>STOP<br>${element.routeId.source} &#8594;
											${element.routeId.via} &#8594; ${element.routeId.destination}
										</td>
									</c:when>
									<c:otherwise>
										<td>NON-STOP<br>${element.routeId.source} &#8594;
											${element.routeId.destination}
										</td>
									</c:otherwise>
								</c:choose>
								<td>${element.dealId.details }</td>
								<c:if test="${availability >= (adult+children) }">
									<td><a href="searchresult/${element.connectorId}">Book</a></td>
								</c:if>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<br> <br>
			</form>
		</c:if>
	</div>
</body>

<!-- Javascript code to apply filter on search results -->
<script src="${pageContext.request.contextPath}/js/search.js"></script>

<!-- JavaScript code to load dynamic data through AJAX -->
<script>
	$('#resultform').submit(function() { // catch the form's submit event
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