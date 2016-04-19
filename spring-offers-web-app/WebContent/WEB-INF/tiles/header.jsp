<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<a class="title" href="<c:url value='/'/>">offers</a>

<sec:authorize access="!isAuthenticated()">
	<a class="login" href="${pageContext.request.contextPath}/login">login</a>
</sec:authorize>