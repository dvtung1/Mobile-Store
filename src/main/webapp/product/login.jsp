<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<p style="color: red">
		<html:errors />
	</p>
	<html:form action="/loginVerified.do">
		<p>
			<b>Username</b>
		</p>
		<html:text name="admin" property="userName"></html:text>
		<p>
			<b>Password</b>
		</p>
		<html:password name="admin" property="passWord"></html:password>

		<br />

		<html:submit>Log In</html:submit>
	</html:form>
</body>
</html>