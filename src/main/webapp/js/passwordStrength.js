/**
 * 
 * JavaScript method to find the password strength
 * 
 */
$(document)
		.ready(
				function() {
					$('#pwd').keyup(
							function() {
								$('#password-strength').html(
										checkStrength($('#pwd').val()));
							});

					function checkStrength(password) {
						// initial strength
						var strength = 0;
						// if the password length is less than 6, return
						// message.
						if (password.length == 0) {
							$('#password-strength').removeClass();
							return '';
						}
						if (password.length < 6) {
							$('#password-strength').removeClass();
							$('#password-strength').addClass('short');
							return 'Too short';
						}

						// length is ok, lets continue.
						// if length is 8 characters or more, increase strength
						// value
						if (password.length > 7)
							strength += 1;

						// if password contains both lower and uppercase
						// characters, increase strength value
						if (password.match(/([a-z].*[A-Z])|([A-Z].*[a-z])/))
							strength += 1;

						// if it has numbers and characters, increase strength
						// value
						if (password.match(/([a-zA-Z])/)
								&& password.match(/([0-9])/))
							strength += 1;

						// if it has one special character, increase strength
						// value
						if (password.match(/([!,%,&,@,#,$,^,*,?,_,~])/))
							strength += 1;

						// if it has two special characters, increase strength
						// value
						if (password
								.match(/(.*[!,%,&,@,#,$,^,*,?,_,~].*[!,%,&,@,#,$,^,*,?,_,~])/))
							strength += 1;

						// now we have calculated strength value, we can return
						// messages

						// if value is less than 2
						if (strength < 2) {
							$('#password-strength').removeClass();
							$('#password-strength').addClass('weak');
							return 'Weak';
						} else if (strength == 2) {
							$('#password-strength').removeClass();
							$('#password-strength').addClass('good');
							return 'Good';
						} else {
							$('#password-strength').removeClass();
							$('#password-strength').addClass('strong');
							return 'Strong';
						}
					}
				});

/**
 * 
 * JavaScript method to compare the passwords
 * 
 */
$(document).ready(
		function() {
			$('#rpwd').keyup(
					function() {
						$('#confirm-password-strength').html(
								checkValid($('#rpwd').val(), $('#pwd').val()));
					});

			function checkValid(cpassword, password) {
				
				if (cpassword != password) {
					$('#confirm-password-strength').removeClass();
					$('#confirm-password-strength').addClass('weak');
					return 'Passwords do not match';
				} else {
					$('#confirm-password-strength').removeClass();				
					return '';
				}
			}
		});

/**
 * 
 * JavaScript method to clear fields if passwords do not match
 * 
 */
function check() {
	var pwd = document.getElementById("pwd").value;
	var rpassword = document.getElementById("rpwd").value;
	if (pwd != rpassword) {
		document.getElementById("pwd").value = "";
		document.getElementById("rpwd").value = "";
		document.getElementById("password-strength").innerHTML = "Enter new password";
		document.getElementById("confirm-password-strength").innerHTML = "";
	}
}