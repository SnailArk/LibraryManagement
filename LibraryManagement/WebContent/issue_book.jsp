<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.mbm.librarymanagement.model.IssueBookVO"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Issue Book</title>
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
<h3>Issue Book</h3>
<div style='float: right; '>
		<a href='operatorpanel'>Operator Panel</a>
	</div>
<s:actionmessage/>
<s:actionerror/>

	<s:form action="issueBook" method="post">

		<table>
			<tr>
				<td><s:textfield label="Student Unique Id" name="issueBookVO.studentID"/></td>
			</tr>

			<tr>
				<td><s:textfield label="Book Unique Id" name="issueBookVO.bookID"/></td>
			</tr>

			<tr>
				<td><s:radio label="Type" name="action"
						list="{'issue','receive'}" /></td>
			</tr>
			<tr><td><button type='submit' name='submit' value='submit' class='button'>Submit</button></td> </tr>
		</table>
		
	</s:form>
</body>
</html>