<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<a href="${pageContext.request.contextPath}/createoffer">create offer</a>

<c:choose>
	<c:when test="${ hasOffer }">
			<a href="${pageContext.request.contextPath}/createoffer">edit or delete your current offer</a>
	</c:when>
	<c:otherwise>
		<a href="${pageContext.request.contextPath}/createoffer">add a new offer</a>
	</c:otherwise>
</c:choose>


<sec:authorize access="hasRole('ROLE_ADMIN')">
	<a href="${pageContext.request.contextPath}/admin">admin</a>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
<a href="${pageContext.request.contextPath}/messages">messages (<span id="numberOfMessages">0</span>)</a>
</sec:authorize>

<script type="text/javascript">
	
	function updateMessageLink(data) {
		$("#numberOfMessages").text(data.number);
	}
	
	function onLoad() {
		updatePage();
		// params - number of seconds, method to be called
		window.setInterval(updatePage, 5000);
	}
	
	function updatePage() {
		// json to go to url and try to get data - pass data into updateMessage function
		$.getJSON("<c:url value="/getMessages"/>", updateMessageLink);
	}
	
	$(document).ready(onLoad);
</script>

