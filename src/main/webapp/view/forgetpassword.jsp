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
			<div id="mainheading" >
				<h1>Welcome To TravelSmart</h1>
			</div>
			<br />
			<div id='homemenu' style="clear: both;">
				<ul>
					<li><a id="start"><span>Home</span></a></li>
					<li><a id="register"><span>Register</span></a></li>
					<li><a id="deals"><span>Top Deals</span></a></li>
					<li><a id="adminlogin"><span>Administration</span></a></li>
					<li><a id="about"><span>About</span></a></li>
					<li><a id="contact"><span>Contact Us</span></a></li>
				</ul>
			</div>
		</div>

		<div id="pages" align="center" style="margin-bottom: 10px;">
			<br>
			<h3>Retrieve your Password</h3>
			<h4>${message}</h4>
			<form action="actionPassword" method="POST" id="forgetform">
				Enter your E-mail ID(User ID) <input type="text" name="userId"
					required="required" autofocus="autofocus"
					placeholder="Email ID (User ID)"
					pattern="^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" /> <br> <br>
				<input type="submit" value="Continue" />
			</form>
		</div>
	</div>
</body>

<!-- JavaScript code to load dynamic data through AJAX -->
<script>
	$('#forgetform').submit(function() { // catch the form's submit event
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