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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login to TravelSmart</title>
</head>
<body class="background">

	<br>
	<h3>Login to your TravelSmart Account</h3>
	<h4>${message}</h4>
	<form method="POST" action="actionlogin">
		<table style="text-align: center;">
			<tr>
				<td><br> <input type="email" name="username"
					placeholder="User ID"
					pattern="^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"
					title="Enter email id in format: user@example.com"
					required="required" autofocus="autofocus" /></td>
			</tr>
			<tr>
				<td><br> <input type="password" name="password"
					placeholder="Password" required="required" /></td>
			</tr>
			<tr>
				<td><a href="forgetpassword">Forget Password</a></td>
			</tr>
			<tr>
				<td><br> <input type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form>
</body>

</html>