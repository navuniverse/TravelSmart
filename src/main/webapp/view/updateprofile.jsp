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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="${pageContext.request.contextPath}/js/main.js"></script>

<!-- Javascript code for password strength -->
<script src="${pageContext.request.contextPath}/js/passwordStrength.js"></script>

<title>Update Profile</title>
</head>
<body class="background">

	<div>
		<br> 
		<h3>Update Your Profile</h3>
		<h4>${message}</h4>
		<form action="actionProfile" method="POST" id="profileform">
			<table style="text-align: center;">
				<tr>
					<td>User ID</td>
					<td><input type="email" name="username" readonly="readonly"
						value="${user.username}" /></td>
				</tr>
				<tr>
					<td>First Name</td>
					<td><input type="text" name="firstname" readonly="readonly"
						value="${user.firstname}" /></td>
				</tr>
				<tr>
					<td>Last Name</td>
					<td><input type="text" name="lastname" readonly="readonly"
						value="${user.lastname}" /></td>
				</tr>
				<tr>
					<td>DOB</td>
					<td><input type="text" readonly="readonly" value="${dob}" /></td>
				</tr>
				<tr>
					<td>Mobile No.</td>
					<td><input type="number" name="mobileno" required="required"
						pattern="[0-9]{10}" maxlength="10"
						title="Enter 10 digit number only" value="${user.mobileno}" /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="password" id="pwd"
						placeholder="Password" size="30" required="required"
						value="${user.password}" /></td>
					<td id="password-strength" style="color: red;"></td>
				</tr>
				<tr>
					<td>Confirm Password</td>
					<td><input type="password" name="confPassword" id="rpwd"
						placeholder="Confirm Password" size="30" required="required"
						value="${user.password}" onblur="check()" /></td>
					<td id="confirm-password-strength" style="color: red;"></td>
				</tr>

			</table>
			<br> <br> <input type="submit" value="Update Profile">
		</form>
	</div>
</body>

<!-- JavaScript code to load dynamic data through AJAX -->
<script>
	$('#profileform').submit(function() { // catch the form's submit event
		$.ajax({ // create an AJAX call...
			data : $(this).serialize(), // get the form data
			type : $(this).attr('method'), // GET or POST
			url : $(this).attr('action'), // the file to call
			success : function(response) { // on success..
				$('#pages').html(response); // update the DIV		
			}
		});
		return false; // cancel original event to prevent form submitting
	});
</script>
</html>