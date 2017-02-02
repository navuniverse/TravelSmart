<%@page import="com.travelportal.model.UserModel"%>
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
<script>
	history.forward();
</script>
<title>TravelSmart</title>
</head>
<body class="background">
	<div id="container">
		<div id="headercontent">
			<div id="mainheading">
				<h1>Welcome To TravelSmart</h1>
			</div>
			<br />
			<div id='homemenu' style="clear: both;">
				<ul>
					<li><a id="search"><span>Home</span></a></li>
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

		<div id="pages" align="center" style="margin-bottom: 10px;">
			<jsp:include page="search.jsp"></jsp:include>
		</div>
		<hr>
		<div id="result" align="center" style="margin-bottom: 10px;"></div>
	</div>
</body>

<!-- Javascript code for drop down city list -->
<script src="${pageContext.request.contextPath}/js/search.js"></script>

<!-- Javascript code for datepicker -->
<script src="${pageContext.request.contextPath}/js/datepicker.js"></script>

</html>