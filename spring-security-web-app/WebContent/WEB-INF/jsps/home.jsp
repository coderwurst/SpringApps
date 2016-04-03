<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home Page</title>
</head>
<body>
Hello there!! Welcome to Home Screen
<p><a href="${pageContext.request.contextPath}/createoffer">create offer</a></p>
<p><a href="${pageContext.request.contextPath}/offers">show all offers</a></p>

<p><a href="${pageContext.request.contextPath}/admin">admin</a></p>

<!-- additional needed for Spring 4 (form submit) -->
<c:url var="logoutUrl" value="/logout"/>
<form action="${logoutUrl}" method="post">
<input type="submit" value="Log out"/> <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> </form>

</body>
</html>