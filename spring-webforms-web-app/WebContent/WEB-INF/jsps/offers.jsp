<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet" type="text/css">

<title>Insert title here</title>
</head>
<body>
Current Offers:
<table class="offers">
<tr><td>Name</td><td>Email</td><td>Services</td></tr>
	<c:forEach var="offer" items="${offers}">
	 <tr>
	 	<td><c:out value="${offer.name}"></c:out></td>	
	 	<td><c:out value="${offer.email}"></c:out></td>	
	 	<td><c:out value="${offer.text}"></c:out></td>	
	 </tr>
	</c:forEach>
	<p><a href="${pageContext.request.contextPath}/">home</a></p>
</table>

</body>
</html>