<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>



<p><a href="${pageContext.request.contextPath}/createoffer">create offer</a></p>

<table class="offers">
<tr><td>Name</td><td>Email</td><td>Services</td></tr>
	<c:forEach var="offer" items="${offers}">
	 <tr>
	 	<td><c:out value="${offer.user.name}"></c:out></td>	
	 	<td><a href="<c:url value='/message?uid=${offer.username}'/>">contact</a></td>	
	 	<td><c:out value="${offer.text}"></c:out></td>	
	 </tr>
	</c:forEach>
</table>

<c:choose>
	<c:when test="${ hasOffer }">
		<p>
			<a href="${pageContext.request.contextPath}/createoffer">edit or delete your current offer</a>
		</p>
	</c:when>
	<c:otherwise>
	<p>
		<a href="${pageContext.request.contextPath}/createoffer">add a new offer</a>
	</p>
	</c:otherwise>
</c:choose>

<p><a href="${pageContext.request.contextPath}/">home</a></p>

<sec:authorize access="hasRole('ROLE_ADMIN')">
	<p><a href="${pageContext.request.contextPath}/admin">admin</a></p>
</sec:authorize>
