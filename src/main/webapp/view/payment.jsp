<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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

<style type="text/css">
div.ui-datepicker {
	font-size: 13px;
}
</style>


<!-- Javascript code for datepicker -->
<script src="${pageContext.request.contextPath}/js/datepicker.js"></script>


<title>Payment</title>
</head>
<body class="background">
	<div>
		<br> ${message}
		<h3>Payment Page</h3>
		<form action="actionPayment" method="POST" id="paymentform">
			<table>
				<tr>
					<td>Total Amount</td>
					<td><input type="text" readonly="readonly"
						value="&#8377;${ticketdata.totalPrice}" title="Total Fare" /></td>
				</tr>
				<tr>
					<td>Card Type</td>
					<td><select id="combobox" name="cardType">
							<option value="Debit">Debit</option>
							<option value="Credit">Credit</option>
					</select></td>
				</tr>

				<tr>
					<td>Card Number</td>
					<td><input type="text" id="first" size="4" required="required"
						pattern="[0-9]{4}" maxlength="4" style="width: 40px;" /> - <input
						type="text" id="second" size="4" maxlength="4" required="required"
						pattern="[0-9]{4}" style="width: 40px;" /> - <input type="text"
						id="third" size="4" maxlength="4" required="required"
						pattern="[0-9]{4}" style="width: 40px;" /> - <input type="text"
						name="cardNum" id="fourth" size="4" maxlength="4"
						required="required" pattern="[0-9]{4}" style="width: 40px;" /></td>
				</tr>

				<tr>
					<td>Name on Card</td>
					<td><input type="text" name="cardName" required="required"
						placeholder="Name on Card" title="Enter name written on card"
						pattern="^([a-zA-Z]+(_[a-zA-Z]+)*)(\s([a-zA-Z]+(_[a-zA-Z]+)*))*$" /></td>
				</tr>

				<tr>
					<td>Expiry Date</td>
					<td><input type="text" name="cardExpiry" required="required"
						id="datepicker" placeholder="Expiry Date"
						title="Expiry Date of Card" /></td>
				</tr>

				<tr>
					<td>CVV Number</td>
					<td><input type="password" name="cvv" required="required"
						placeholder="CVV Number" pattern="[0-9]{3}" maxlength="3"
						title="Enter 3 digit CVV no. written at back of card" /></td>
				</tr>
			</table>
			<br> <br> <a href="welcome" class="button-link">Cancel</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
				type="submit" value="Pay"> <br>
		</form>
	</div>
</body>


<!-- JavaScript code to load dynamic data through AJAX -->
<script>
	$('#paymentform').submit(function() { // catch the form's submit event
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