<%@page import='java.util.List'%>
<%@page language='java' contentType='text/html; charset=ISO-8859-1' pageEncoding='ISO-8859-1'%>
<%@taglib prefix='s' uri='/struts-tags' %>
<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'>

<html>
<head>

<meta http-equiv='Content-Type' content='text/html; charset=ISO-8859-1'>
<title>Update Form</title>
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
	<div style='float: right; '>
		<a href='operatorpanel'>Operator Panel</a>
	</div>
	<div
		style='font-size: 20; text-align: center; padding-top: 14px; font-size: xx-large;'>
		Update Form</div>
		<s:actionerror/>
			<form method='post' action='updateForm' >
				<table align='center'>
					
					<tr>
						<td>
						<div>	<s:textfield label='First Name' name='studentVO.firstName'/></div>
								<s:hidden name='studentVO.id' value='%{studentVO.id}' /> 
						</td>
					</tr>
					<tr>
						<td>
							<div><s:textfield label='Last Name ' name='studentVO.lastName'/></div>
						</td>
					</tr>
					<tr>
						<td>
							<s:radio label="Gender" name="studentVO.gender" list="{'Male','Female'}" />
						</td>
					</tr>
					<tr>
						<td>
							<s:textfield label='Father Name' name='studentVO.fatherName'/>
						</td>
					</tr>
					<tr>
						<td> 
							<s:textfield label='Email' name='studentVO.email' disabled='true'/>
						</td>
					</tr>
					
					<tr>
						<td> 
							<s:textfield label='Mobile' name='studentVO.mobile' />
						</td>
					</tr>
					<tr>
						<td> 
							<s:textfield label='Student number' name='studentVO.studentNumber' />
						</td>
					</tr>
					<tr>
						<td> 
							<s:textfield label='Admission Year' name='studentVO.admissionYear' />
						</td>
					</tr>
					<tr>
						<td> 
							<s:textfield label='Enroll No.' name='studentVO.enrollNumber' />
						</td>
					</tr>
					<tr>
						<td> 
							<s:textfield label='Password' name='studentVO.password'/>
						</td>
					</tr>
					<tr>
						<td>
							<button type='submit' name='submit' value='submit' class='button'>Submit</button>
						</td>
					</tr>

				</table>
			</form>
</body>
</html>