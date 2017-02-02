/**
 * 
 * JavaScript method to check the availability of username
 * 
 */
function availCheck() {
		var xmlhttp;
		var k = document.getElementById("username").value;
		var urls = "view/availcheck.jsp?ver=" + k;

		if (window.XMLHttpRequest) {
			xmlhttp = new XMLHttpRequest();
		} else {
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}

		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4) {
				document.getElementById("err").innerHTML = xmlhttp.responseText;
			}
			else{
				document.getElementById("err").innerHTML = "";
			}
		};

		xmlhttp.open("GET", urls, true);
		xmlhttp.send();
	}