<!DOCTYPE html>
<html>
<head>
<title>Demo</title>
<meta name="google-signin-client_id" content="352911980755-le50v77c5ko7uhpm1b2m2smionut0sdt.apps.googleusercontent.com">
<script src="https://apis.google.com/js/platform.js" async defer></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/style.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/animate-custom.css" />

<script type="text/javascript">

function onSignIn(googleUser) {
	var id_token = googleUser.getAuthResponse().id_token;
	document.getElementById("idToken").value=id_token;
	var form = $("#loginform");
	$('body').append(form);
	form.submit();
	signOut();
}

function signOut() {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
      console.log('User signed out.');
    });
}

</script>

</head>
<body background="<%=request.getContextPath()%>/resources/images/cts.jpg">
<div class="container">
<header>
    <h1 align="center">Google Sign-in Demo</h1>
</header>
	<section>				
	<div id="container_demo" >
	<div id="wrapper">
	<div id="login" class="animate form">
		<form id="loginform" action="<%=request.getContextPath()%>/cts/user/login" method="post">
		<h1>Log in</h1> 
		<!-- Google Sign-in -->
		<input id="idToken" name="idToken" type="hidden"/> 
		<div align="center">
		<label for="username" class="uname"> Sign-in with Google </label>
		<p></p>
		<div class="g-signin2" data-onsuccess="onSignIn"></div>
		</div>
		<div>
		<p></p>
		</div>
		</form>
	</div>
	</div>
	</div>  
	</section>
</div>
</body>
</html>