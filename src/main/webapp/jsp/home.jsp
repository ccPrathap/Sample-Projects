<!DOCTYPE html>
<html>
<head>
<title>Home</title>
<h3>
<div align="left" style="float: left;">Welcome! ${name}</div>
<div id="bal" data-bal="${bal}" align="right">Your Balance : ${bal}</div>
<div align="center"><a href="<%=request.getContextPath()%>/cts/user/login">Sign out</a></div>
</h3>
</head>
</html>