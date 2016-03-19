<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet" type="text/css">
<title>Create Offer</title>
</head>
<body>
	<h2>Create a new Account</h2>
	<p><a href="${pageContext.request.contextPath}/">home</a></p>


	<sf:form method="post" action="${pageContext.request.contextPath}/createaccount" commandName="user">
		<table class="formtable">
			<tr><td class="label">Username: </td><td><sf:input class="control" name="username" type="text" path="username"/><br/>
			<sf:errors path="username" cssClass="error"></sf:errors></td></tr>
			<tr><td class="label">Email: </td><td><sf:input class="control" name="email" type="text" path="email"/><br/>
			<sf:errors path="email" cssClass="error"></sf:errors></td></tr>
			<tr><td class="label">Password: </td><td><sf:input class="control" name="password" type="text" path="password"/><br/>
			<sf:errors path="password" cssClass="error"></sf:errors></td></tr>
			<tr><td class="label">Confirm Password: </td><td><input class="control" name="confirmpass" type="text"/><br/></td></tr>
			<tr><td class="label">&nbsp;</td><td><input class="control" value="create account" type="submit"/></td></tr>
		</table>
	</sf:form>

</body>
</html>