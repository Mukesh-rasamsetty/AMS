<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Audit Management System</title>
<SCRIPT type="text/javascript">
	window.history.forward();
	function noBack() {
		window.history.forward();
	}
</SCRIPT>
</head>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();">
	<h2>Authentication failed login again</h2>
	<br>
	<h2>
		<a href="/loginPage">Click here</a> to login again
	</h2>
</body>
</html>