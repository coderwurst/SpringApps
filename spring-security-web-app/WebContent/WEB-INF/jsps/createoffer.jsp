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
	Create a new Offer
	<p><a href="${pageContext.request.contextPath}/">home</a></p>


	<sf:form method="post" action="${pageContext.request.contextPath}/docreate" commandName="offer">
		<table class="formtable">
			<tr><td class="label">Name: </td><td><sf:input class="control" name="name" type="text" path="name"/><br/>
			<sf:errors path="name" cssClass="error"></sf:errors></td></tr>
			<tr><td class="label">Email: </td><td><sf:input class="control" name="email" type="text" path="email"/><br/>
			<sf:errors path="email" cssClass="error"></sf:errors></td></tr>
			<tr><td class="label">Services: </td><td><sf:textarea class="control" name="text" rows="10" cols="10" path="text"></sf:textarea><br/>
			<sf:errors path="text" cssClass="error"></sf:errors></td></tr>
			<tr><td class="label">&nbsp;</td><td><input class="control" value="create advert" type="submit"/></td></tr>
		</table>
	</sf:form>

</body>
</html>