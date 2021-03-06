<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<table class="offers">
<tr><td>Name</td><td>Contact</td><td>Services</td></tr>
	<c:forEach var="offer" items="${offers}">
	 <tr>
	 	<td class="name"><c:out value="${offer.user.name}"></c:out></td>	
	 	<td class="contact"><a href="<c:url value='/message?uid=${offer.username}'/>">contact</a></td>	
	 	<td class="offer"><c:out value="${offer.text}"></c:out></td>	
	 </tr>
	</c:forEach>
</table>
