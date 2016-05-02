<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
Current Offers:
<table class="offers">
<tr><td>Name</td><td>Email</td><td>Services</td></tr>
	<c:forEach var="offer" items="${offers}">
	 <tr>
	 	<td><c:out value="${offer.user.name}"></c:out></td>	
	 	<td><c:out value="${offer.user.email}"></c:out></td>	
	 	<td><c:out value="${offer.text}"></c:out></td>	
	 </tr>
	</c:forEach>
	<p><a href="${pageContext.request.contextPath}/">home</a></p>
</table>
