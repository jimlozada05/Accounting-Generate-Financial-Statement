<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Login</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script src='jquery/jquery-3.3.1.js'>
	</script>
	<script src='jquery/jquery-validation@1.17.0/jquery.validate.js'>
	</script>
	<script src="jquery/jquery.validation/additional-methods.min.js">
	</script>
	
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/iconic/css/material-design-iconic-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="css/util.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
<!--===============================================================================================-->
</head>
<script>
$(document).ready(function(){
	if ($('#password').val() == "" && $('#confirm_password').val() == "" && $('#username').val() == "") {
		 $('#submit').addClass('disabled');
		 $('#submit').prop('disabled', true);
	}
});
</script>
<body background="images/business-3528035__340.jpg">
	
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-t-85 p-b-20">
				<form method="post" action="uploadlogin" enctype="multipart/form-data" class="login100-form validate-form">
					<span class="login100-form-title p-b-70">
						Welcome New User
					</span>

					<div class="wrap-input100 validate-input m-t-85 m-b-35" data-validate = "Enter username">
						<input class="input100" type="text" name="username" id="username">
						<span class="focus-input100" data-placeholder="Username"></span>
					</div>
					<span id="user_message" style="content-align:center;"></span>
					
					<div class="wrap-input100 validate-input m-b-50" data-validate="Enter password">
						<input class="input100" type="password" name="password" id="password">
						<span class="focus-input100" data-placeholder="Password"></span>
					</div>
					
					<div class="wrap-input100 validate-input m-b-50" data-validate="Enter password">
						<input class="input100" type="password" name="confirm_password" id="confirm_password">
						<span class="focus-input100" data-placeholder="Confirm Password"></span>
					</div>
					
					<div>
					<span id="message" style="content-align:center;"></span>
					</div>
					
					<div class="container-login100-form-btn">
						<button type="submit" class="login100-form-btn" id="submit">
							Register
						</button>
					</div>

					<ul class="login-more p-t-190">
						<li class="m-b-8">
						<li>
							<span class="txt1">
								Have an account?
							</span>

							<a href="login.jsp" class="txt2">
								Sign in
							</a>
						</li>
					</ul>
				</form>
			</div>
		</div>
	</div>
	<div id="dropDownSelect1"></div>
			
<script>
$('form').validate();
var countu = 0;
var countpw1 = 0;
var countpw2 = 0;
$('#password, #confirm_password, #username').on('keyup', function() {
  if ($('#password').val() == $('#confirm_password').val()) {
  	if ($('#password').val() != "" && $('#confirm_password').val() != ""){
	  $('#message').html('').css('color', 'green');
      countpw1 = 1;
      countpw2 = 1;
    	
      if($('#password').val() == $('#confirm_password').val() && $('#password').val().length < 6){
    	  $('#message').html('Passwords are less than 6').css('color', 'red');
    	  countpw2 = 0;
    	  countpw2 = 0;
    	}  
     
      if($('#password').val() == $('#username').val()){
    	  $('#message').html('Passwords cannot be the same as username').css('color', 'red');
    	  countu = 0;
    	  countpw1 = 0;
    	  countpw2 = 0;
      }
      re = /[0-9]/;
      if(!re.test($('#password').val())) {
    	  $('#message').html('Passwords must contain number').css('color', 'red');
    	  countpw1 = 0;
    	  countpw2 = 0;
        }
      
      re = /[a-z]/;
      if(!re.test($('#password').val())) {
    	  $('#message').html('Passwords must contain letters').css('color', 'red');
    	  countpw1 = 0;
    	  countpw2 = 0;
        }
      
      re = /[A-Z]/;
      if(!re.test($('#password').val())) {
    	  $('#message').html('Passwords must contain one upercase').css('color', 'red');
    	  countpw1 = 0;
    	  countpw2 = 0;
        }
      }
  } 
  if ($('#password').val() != $('#confirm_password').val()) {
    $('#message').html('Passwords do not Match').css('color', 'red');
    countpw1 = 0;
    countpw2 = 0;
  }
  
  if($('#username').val() == "") {
	  $('#user_message').html('username cannot be blank').css('color', 'red');
      $('#username').focus();
      countpu = 0;
    }
  
  if($('#username').val() != "") {
	  $('#user_message').html('').css('color', 'green');
	  countu = 1;
	  
	  re = /^\w+$/;
      if(!re.test($('#username').val())) {
    	  $('#user_message').html('Username cant contain symbols').css('color', 'red');
    	  countu = 0;
        }
      
    }
  
  
  if(countu == 1 && countpw1 == 1 && countpw2 == 1){
	  $('#submit').removeClass('disabled');
	  $('#submit').prop('disabled', false);
  }
  if(countu == 0 || countpw1 == 0 || countpw2 == 0){
	  $('#submit').addClass('disabled');
	  $('#submit').prop('disabled', true);
  }
});
</script>

</body>
</html>