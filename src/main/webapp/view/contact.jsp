<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TravelSmart</title>
</head>
<body class="background">
	<br>
	<h3>Contact TravelSmart</h3>
	<h4>${message}</h4>
	<br>
	<form action="contact" method="POST" id="contactform">
		<input type="email" name="from" placeholder="E-mail ID" size="30"
			required="required"
			pattern="^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"
			title="Enter email id in format: user@example.com" /> <br> <br>
		<textarea name="body" rows="5" name="body" style="width: 250px;"
			placeholder="Your Feedback/Query" required="required"
			title="Enter text to contact TravelSmart"></textarea>
		<br> <br> <input type="submit" value="Submit Response" /> <br>
	</form>
</body>

<!-- JavaScript code to load dynamic data through AJAX -->
<script>
	$('#contactform').submit(function() { // catch the form's submit event
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