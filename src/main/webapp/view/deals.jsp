<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TravelSmart Deals</title>
</head>
<body class="background">
	
	<h3>TravelSmart Top Deals</h3>
	<h4>${message}</h4><br>
	<c:if test="${deals.size()==0}">
		<h3>No Deal is currently Available.</h3>
	</c:if>
	<c:if test="${deals.size()>0}">
		<c:forEach items="${deals}" var="deals">
			Deal: ${deals.details}<br>
			<br>
			Start Date: ${deals.startDate}<br>
			<br>
			End Date: ${deals.endDate}<br>
			<br>
			<hr width="100">
			<br>
			<br>
		</c:forEach>
	</c:if>
</body>
</html>