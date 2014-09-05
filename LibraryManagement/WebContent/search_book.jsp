<%@page import="com.mbm.librarymanagement.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Book</title>
<style type='text/css'>
.errorMessage {
	color: red;
	float: right;
	z-index: 1;
	position: absolute;
	margin-left: 184px;
	margin-top: 8px;
}
</style>

</head>
<body>
	<h3>Search Book</h3>
	<div style='float: right;'>
		<a href='operatorpanel'>Operator Panel</a>
	</div>

	<s:actionerror />
	<form action='searchBook'>
		<table>
			<tr>
				<td><s:textfield name="bookVO.bookName" label="BOOK NAME"></s:textfield></td>
				<td><s:textfield name="bookVO.publisher" label="PUBLISHER"></s:textfield></td>
			</tr>
			<tr>
				<td><s:textfield name="bookVO.author" label="AUTHOR"></s:textfield></td>
				<td><input type='submit' name='submit' value='submit'></td>
			</tr>
		</table>
	</form>
	<table border='1'>
		<tr>

			<td>U_ID</td>
			<td>Book Name</td>
			<td>Publisher</td>
			<td>Author</td>
			<td>Summary</td>
		</tr>

		<s:iterator id="bookVOs" value="bookVOs">
			<tr>
				<s:if test="%{editable}">
					<td><a href='updateBook?id=<s:property value="id" />'> <s:property
								value="uniqueId" /></a></td>
				</s:if>
				<s:else>
					<td><s:property value="uniqueId" /></td>
				</s:else>
				<td><s:property value="bookName" /></td>
				<td><s:property value="publisher" /></td>
				<td><s:property value="author" /></td>
				<td><s:property value="summary" /></td>
			</tr>
		</s:iterator>
	</table>
</body>
</html>