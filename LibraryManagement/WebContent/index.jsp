<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Library Management System</title>
<style type='text/css'>
.errorMessage {
	color: red;
	float: right;
	z-index: 1;
	position: absolute;
	margin-left: 200px;
	margin-top: 8px;
}
</style>

</head>
<body>
	<h1 align="center">Library Management System</h1>
	<hr>
	<div style='float: right;'>
		<a href="registration">student registration</a>
	</div>
	<br>
	<div align="center">
		<fieldset
			style="HEIGHT: 180px; WIDTH: 300px; border: 2px solid black;">
			<legend align="left" style="color: black; font-size: 25px;">Login</legend>
			<s:actionerror />
			<s:actionmessage />
			<s:form action="login" method="post">
				<table>
					<tr>
						<td><s:textfield label="Username" name="userVO.userId" /></td>
					</tr>
					<tr>
						<td><s:password label="Password" name="userVO.password" /></td>
					</tr>
					<tr>
						<td><s:radio label="Type" name="userVO.userType"
								list="{'student','operator'}" /></td>
					</tr>
					<tr>
						<td><input type='submit' name='submit' value='submit'></td>
					</tr>
				</table>
			</s:form>
		</fieldset>
	</div>
</body>
</html>