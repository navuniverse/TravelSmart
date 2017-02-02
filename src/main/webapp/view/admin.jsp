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
<link type="text/css"
	href="${pageContext.request.contextPath}/css/menu.css" rel="stylesheet" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>

<script src="${pageContext.request.contextPath}/js/main.js"></script>
<script>
	history.forward();
</script>
<title>TravelSmart Administration</title>
</head>
<body class="background">
	<c:if test="${user.role!='ADMIN'}">
		<h3>
			Ooops!!!! You are not an admin to view this this page.<br>
		</h3>
	</c:if>
	<c:if test="${user.role=='ADMIN'}">
		<div id="container">
			<div id="headercontent">
				<div id="mainheading">
					<h1>Welcome To TravelSmart</h1>
				</div>
				<br />
				<div id='homemenu' style="clear: both;">
					<ul>
						<li><a href="welcome"><span>Home</span></a></li>
						<li class='has-sub'><a href='#'><span>Manage
									Flights</span></a>
							<ul>
								<li><a id='addflight'><span>Add Flight</span></a></li>
								<li><a id='updateflight'><span>Update Flight</span></a></li>
								<li><a id='deleteflight'><span>Delete Flight</span></a></li>
							</ul></li>
						<li class='has-sub'><a href='#'><span>Manage Deals</span></a>
							<ul>
								<li><a id='adddeal'><span>Add Deal</span></a></li>
								<li><a id='updatedeal'><span>Update Deal</span></a></li>
								<li><a id='deletedeal'><span>Delete Deal</span></a></li>
							</ul></li>
						<li class='has-sub'><a href='#'><span>Manage
									Schedules</span></a>
							<ul>
								<li><a id="schedule"><span>Upload Schedule CSV</span></a></li>
								<li><a id='showschedule'><span>Show Schedule</span></a></li>
							</ul></li>
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

			<div id="pages" align="center" style="margin-bottom: 10px;">		
				<jsp:include page="addflight.jsp"></jsp:include>
			</div>
			<hr>
			<div id="result" align="center" style="margin-bottom: 10px;"></div>
		</div>
	</c:if>
</body>
</html>