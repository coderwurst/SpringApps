<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>



<p><a href="${pageContext.request.contextPath}/createoffer">create offer</a></p>
<p><a href="${pageContext.request.contextPath}/offers">show all offers</a></p>

<sec:authorize access="hasRole('ROLE_ADMIN')">
	<p><a href="${pageContext.request.contextPath}/admin">admin</a></p>
</sec:authorize>
