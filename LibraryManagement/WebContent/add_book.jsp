<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Book</title>
<style type='text/css'>
.errorMessage {
color:red;
float: right;
z-index: 1;
position: absolute;
margin-left: 184px;
margin-top: 8px;
}

</style>

</head>
<body>
<h3>Add Book</h3>
<div style='float: right; '>
		<a href='operatorpanel'>Operator Panel</a>
	</div>

	<s:actionerror />
	<br>
	<form action='addBook'>
		<table>
			<tr><td><s:textfield name="bookVO.uID" label="UID"></s:textfield></td></tr>
			<tr><td><s:textfield name="bookVO.bookName" label="BOOK NAME"></s:textfield></td></tr>
			<tr><td><s:textfield name="bookVO.publisher" label="PUBLISHER"></s:textfield></td></tr>
			<tr><td><s:textfield name="bookVO.author" label="AUTHOR"></s:textfield></td></tr>
			<tr><td><s:textarea name="bookVO.summary" label="SUMMARY"></s:textarea></td></tr>
			<tr><td><input type='submit' name='submit' value='submit'></td></tr>
		</table>
	</form>
</body>
</html>