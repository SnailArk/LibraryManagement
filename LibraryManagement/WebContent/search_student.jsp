<%@page import='java.util.*'%>
<%@ page language='java' contentType='text/html; charset=ISO-8859-1'   pageEncoding='ISO-8859-1'%>
<%@taglib prefix='s' uri='/struts-tags' %>
<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'>
<html>
<head>
<meta http-equiv='Content-Type' content='text/html; charset=ISO-8859-1'>
<title>Search</title>


</head>
<body>
	<div style='float: right; '>
		<a href='operatorpanel'>Operator Panel</a>
	</div>

<div>
	<form method='get' action='search'>
		<table>
			<tr>
				<th>
					Search Student
				</th>
			</tr>
			<tr>
				<td>
				<s:textfield label='Email' name='studentVO.email'/>
				</td>
			</tr>
			<tr>
				<td>
				<s:textfield label='First Name' name='studentVO.firstName'/>
				</td>
			</tr>
			<tr>
				<td>
				<s:textfield label='Last Name ' name='studentVO.lastName'/>
				</td>
			</tr>
			<tr>
				<td colspan='2'>
					<button type='submit' value='submit' name='submit'> Search</button>
				</td>
			</tr>
		</table>
	</form>
</div>
<div>
	<table align='center' border='1'>
		<tr>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Gender</th>
			<th>Father Name</th>
			<th>E-Mail</th>
			<th>Mobile No.</th>		
			<th>Student Number</th>
			<th>Admission Year</th>
			<th>Enroll No.</th>
		</tr>
		
			<s:iterator value='studentList'>
					<tr>
							<td><a href='updateForm?id=<s:property value="id"/>'><s:property value='firstName'/></a></td>
							<td><s:property value='lastName'/></td>
							<td><s:property value='gender'/></td>
							<td><s:property value='fatherName'/></td>
							<td><s:property value='email'/></td>
							<td><s:property value='mobile'/></td>		
							<td><s:property value='StudentNumber'/></td>
							<td><s:property value='admissionYear'/></td>
							<td><s:property value='enrollNumber'/></td>
					</tr>
			</s:iterator>
	</table>
</div>
</body>
</html>