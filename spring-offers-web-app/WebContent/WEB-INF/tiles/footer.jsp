<sec:authorize access="isAuthenticated()">
	<!-- additional needed for Spring 4 (form submit) NEEDS UPDATED FOR TILES-->
	<c:url var="logoutUrl" value="/logout"/>
	<form action="${logoutUrl}" method="post">
	<input class="login" type="submit" value="Log out"/>
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> </form>
</sec:authorize>
<p>created by coderwurst, 2016 http://www.coderwurst.com</p>