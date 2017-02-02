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
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css" />

<script>
	history.forward();
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload XML</title>
</head>
<body class="background">
	
	<br>
	<h3>Deal Management</h3>
	<h4>${message}</h4>
	<form action="actionXMLUpload" method="POST"
		enctype="multipart/form-data">
		Select XML file to upload <input type="file" name="file"
			accept="text/xml" /> <input type="submit" value="Upload XML" />
	</form>

</body>

<!-- JavaScript code to load dynamic data through AJAX -->
<script>
	$('#dealform').submit(function() { // catch the form's submit event
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