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
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />

<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
</head>
<body class="background">

	<br>
	<h3>Register for TravelSmart Account</h3>
	<h4>${message}</h4>
	<form method="POST" action="actionRegister">
		<table style="text-align: center;">

			<tr>
				<td>First Name<span class="red"> *</span></td>
				<td><input type="text" name="firstname" autofocus="autofocus"
					placeholder="First Name" size="30" required="required"
					title="Enter your first name"
					pattern="^([a-zA-Z]+(_[a-zA-Z]+)*)(\s([a-zA-Z]+(_[a-zA-Z]+)*))*$" /></td>
			</tr>

			<tr>
				<td>Last Name</td>
				<td><input type="text" name="lastname" placeholder="Last Name"
					size="30" title="Enter your first name"
					pattern="^([a-zA-Z]+(_[a-zA-Z]+)*)(\s([a-zA-Z]+(_[a-zA-Z]+)*))*$" /></td>
			</tr>

			<tr>
				<td>E-mail ID<span class="red"> *</span></td>
				<td><input type="email" name="username" id="username"
					placeholder="E-mail ID(User ID)" size="30" required="required"
					pattern="^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"
					onblur="availCheck()"
					title="Enter email id in format: user@example.com" /></td>
				<td id="err"></td>
			</tr>

			<tr>
				<td>Password<span class="red"> *</span></td>
				<td><input type="password" name="password" id="pwd"
					placeholder="Password" size="30" required="required"
					title="Enter your password" /></td>
				<td id="password-strength" style="color: red;"></td>
			</tr>

			<tr>
				<td>Confirm Password<span class="red"> *</span></td>
				<td><input type="password" name="confPassword" id="rpwd"
					placeholder="Confirm Password" size="30" required="required"
					title="Enter same password again" onblur="check()" /></td>
				<td id="confirm-password-strength" style="color: red;"></td>

			</tr>

			<tr>
				<td>Mobile No.<span class="red"> *</span></td>
				<td><input type="text" name="mobileno" placeholder="Mobile No."
					required="required" pattern="[0-9]{10}" maxlength="10"
					title="Enter 10 digit number only" /></td>
			</tr>

			<tr>
				<td>Date of Birth<span class="red"> *</span></td>
				<td><input type="text" name="dob" id="dob"
					placeholder="Date Of Birth" required="required"
					title="Select your date of birth" /></td>
			</tr>
			<tr>
				<td colspan="2"><span class="red">(Fields marked with *
						are mandatory)</span></td>
			</tr>

			<tr>
				<td colspan="2"><br> <input type="submit" id="register"
					value="Register"></td>
			</tr>
		</table>
	</form>
</body>

<!-- Javascript code for datepicker -->
<script src="${pageContext.request.contextPath}/js/dob.js"></script>

<!-- Javascript code for password strength -->
<script src="${pageContext.request.contextPath}/js/passwordStrength.js"></script>

<!-- Javascript code for username availability -->
<script src="${pageContext.request.contextPath}/js/availabilityCheck.js"></script>
</html>