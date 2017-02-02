<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%!int i;%>
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
					<li><a id="aboutlogin"><span>About</span></a></li>
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
			<%
			    i = 0;
			%>
			<h3>
				You selected the flight ${flight.flightId.flightId} of
				${flight.flightId.provider} <br>Fill the Booking Form below:<br>

			</h3>

			<form action="actionBook" method="POST" id="bookform">
				<c:if test="${adult>0}">
					<fieldset style="width: 500px;">
						<legend>
							<b>Adult Details</b>
						</legend>
						<c:forEach begin="0" end="${adult-1}">

							<table>
								<tr>
									<td>First Name</td>
									<td>Last Name</td>
								</tr>
								<tr>
									<td><input type="text" name="travellers[<%=i%>].firstname"
										autofocus="autofocus" placeholder="First Name" size="30"
										required="required"
										pattern="^([a-zA-Z]+(_[a-zA-Z]+)*)(\s([a-zA-Z]+(_[a-zA-Z]+)*))*$" /></td>
									<td><input type="text" name="travellers[<%=i%>].lastname"
										placeholder="Last Name" size="30"
										pattern="^([a-zA-Z]+(_[a-zA-Z]+)*)(\s([a-zA-Z]+(_[a-zA-Z]+)*))*$" /></td>
								</tr>

								<tr>
									<td>Sex</td>
									<td>Age</td>
								</tr>
								<tr>
									<td><select id="combobox" name="travellers[<%=i%>].sex">
											<option value="Male">Male</option>
											<option value="Female">Female</option>
									</select></td>
									<td><input type="text" name="travellers[<%=i%>].age"
										required="required" pattern="[0-9]{2,3}"
										placeholder="Adult Age" title="Adult Age: 12+ Years" /></td>
								</tr>

							</table>
							<%
							    i++;
							%>
						</c:forEach>
					</fieldset>

				</c:if>
				<c:if test="${children>0}">
					<c:forEach begin="0" end="${children-1}">
						<fieldset style="width: 500px;">
							<legend>
								<b>Children Details</b>
							</legend>
							<table>
								<tr>
									<td>First Name</td>
									<td>Last Name</td>
								</tr>
								<tr>
									<td><input type="text" name="travellers[<%=i%>].firstname"
										autofocus="autofocus" placeholder="First Name" size="30"
										required="required"
										pattern="^([a-zA-Z]+(_[a-zA-Z]+)*)(\s([a-zA-Z]+(_[a-zA-Z]+)*))*$" /></td>
									<td><input type="text" name="travellers[<%=i%>].lastname"
										placeholder="Last Name" size="30"
										pattern="^([a-zA-Z]+(_[a-zA-Z]+)*)(\s([a-zA-Z]+(_[a-zA-Z]+)*))*$" /></td>
								</tr>
								<tr>
									<td>Sex</td>
									<td>Age</td>
								</tr>
								<tr>
									<td><select id="combobox" name="travellers[<%=i%>].sex">
											<option value="Male">Male</option>
											<option value="Female">Female</option>
									</select></td>
									<td><input type="text" name="travellers[<%=i%>].age"
										required="required" pattern="[0-9]{1,2}"
										placeholder="Child Age" title="Children Age:3-12 Years" /></td>
								</tr>

							</table>
						</fieldset>
						<%
						    i++;
						%>
					</c:forEach>
				</c:if>
				<c:if test="${infant>0}">
					<c:forEach begin="0" end="${infant-1}">
						<fieldset style="width: 500px;">
							<legend>
								<b>Infant Details</b>
							</legend>
							<table>
								<tr>
									<td>First Name</td>
									<td>Last Name</td>
								</tr>
								<tr>
									<td><input type="text" name="travellers[<%=i%>].firstname"
										autofocus="autofocus" placeholder="First Name" size="30"
										required="required"
										pattern="^([a-zA-Z]+(_[a-zA-Z]+)*)(\s([a-zA-Z]+(_[a-zA-Z]+)*))*$" /></td>
									<td><input type="text" name="travellers[<%=i%>].lastname"
										placeholder="Last Name" size="30"
										pattern="^([a-zA-Z]+(_[a-zA-Z]+)*)(\s([a-zA-Z]+(_[a-zA-Z]+)*))*$" /></td>
								</tr>
								<tr>
									<td>Sex</td>
									<td>Age</td>
								</tr>
								<tr>
									<td><select id="combobox" name="travellers[<%=i%>].sex">
											<option value="Male">Male</option>
											<option value="Female">Female</option>
									</select></td>
									<td><input type="text" name="travellers[<%=i%>].age"
										required="required" pattern="[0-2]{1}"
										placeholder="Infant Age" title="Infant Age: 0-2 Years" /></td>
								</tr>
							</table>
						</fieldset>
						<%
						    i++;
						%>
					</c:forEach>
				</c:if>
				<br> <a href="welcome" class="button-link">Cancel</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
					type="submit" value="Continue"> <br>
			</form>
		</div>
	</div>
</body>

<!-- JavaScript code to load dynamic data through AJAX -->
<script>
	$('#bookform').submit(function() { // catch the form's submit event
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