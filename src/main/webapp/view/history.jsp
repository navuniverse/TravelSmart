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
<link type="text/css"
	href="${pageContext.request.contextPath}/css/menu.css" rel="stylesheet" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>

<!-- Javascript code for drop down city list -->
<script src="${pageContext.request.contextPath}/js/search.js"></script>

<!-- Javascript code for datepicker -->
<script src="${pageContext.request.contextPath}/js/datepicker.js"></script>

<script>
	history.forward();
</script>

<script src="${pageContext.request.contextPath}/js/jquery.dataTables.js"></script>

<script>
	$(document).ready(function() {
		$('#userhistory').dataTable({
			"bProcessing" : true, // Enable processing indicator
			"bServerSide" : false, // Configure data option - server or client
			"bSort" : true, // Enable sorting
			"bFilter" : true,
			"bAutoWidth" : true,
			"sPaginationType" : "full_numbers", // Paginate Type -> two_button (Previous && Next)
			//               -> full_numbers (First, Previous, 1, 2, Next, Last)    

			"iDisplayLength" : 6, //Number of result per page
			/* "sDom" : 'prt' //Only Pagination,Processing and table   */
			sDom : '<"H"f>t<"F"rpi>' //filter,processing, length change  table  information pagination
		//sDom : '<"H"l>t<"F"frip>'
		});
		$("#userhistory").css("width:100%");
	});
</script>

<style>
/* for table grid */
#userhistory_paginate {
	text-align: center;
	font-weight: bold;
	margin-right: 30px;
	margin-bottom: 10px;
}

#userhistory_info {
	text-align: center;
	font-weight: bold;
	margin-left: :10px;
}

#userhistory_filter {
	text-align: center;
	margin-right: 10px;
}

#userhistory_filter input {
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

#userhistory_paginate a {
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

<title>TravelSmart</title>
</head>

<body class="background">
	<div id="container">
		<div id="headercontent">
			<div id="mainheading">
				<h1 style="padding-top: 15px;">Welcome To TravelSmart</h1>
			</div>
			<br />
			<div id='homemenu' style="clear: both;">
				<ul>
					<li><a href="welcome"><span>Home</span></a></li>
					<li><a id="deals"><span>Top Deals</span></a></li>
					<c:if test="${user.role!='ADMIN'}">
						<li><a id="admin"><span>Administration</span></a></li>
					</c:if>
					<c:if test="${user.role=='ADMIN'}">
						<li><a href="admin"><span>Administration</span></a></li>
					</c:if>
					<li><a id="about"><span>About</span></a></li>
					<li><a id="contact"><span>Contact Us</span></a></li>
					<li class='has-sub'><a href='#'><span>${user.firstname}
								${user.lastname}</span></a>
						<ul>
							<li><a id="updateprofile"><span>Profile</span></a></li>
							<li><a href='history'><span>Travel History</span></a></li>
							<li class='last'><a href='logout'><span>Sign Out</span></a></li>
						</ul></li>
				</ul>
			</div>
		</div>

		<div id="pages" align="center">

			<h3>Your Travel History</h3>
			<c:if test="${ticket.size()==0}">
				<h3>
					You have not booked any ticket.<br> Please book the ticket the
					see your travel history.
				</h3>
			</c:if>
			<c:if test="${ticket.size() > 0}">
				<table class="datagrid" id="userhistory">
					<thead>
						<tr>
							<th>Ticket ID</th>
							<th>Travel Date</th>
							<th>Source</th>
							<th>Destination</th>
							<th>No. of Travellers</th>
							<th>Total Fare</th>
							<th>Status</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ticket}" var="element">
							<tr>
								<td>${element.ticketId}</td>
								<td>${element.travelDate}</td>
								<td>${element.source}</td>
								<td>${element.destination}</td>
								<td>${element.noOfPassenger}</td>
								<td>${element.totalPrice}</td>
								<td>${element.status}</td>
								<c:choose>
									<c:when test="${element.status=='BOOKED'}">
										<td><a href="history/${element.ticketId}">Cancel
												Ticket</a></td>
									</c:when>
									<c:when test="${element.status=='CANCELLED'}">
										<td>CANCELLED</td>
									</c:when>
									<c:otherwise>
										<td>TRAVELLED</td>
									</c:otherwise>
								</c:choose>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<br>
				<br>
				<button onclick="window.open('<c:url value="createUserPDF" />');">Print
					or Download</button>

				<br>
				<br>
			</c:if>

		</div>
		<hr>
		<div id="result" align="center"></div>
	</div>
</body>

</html>