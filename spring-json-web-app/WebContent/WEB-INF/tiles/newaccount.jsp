<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

	<h2>Create a new Account</h2>
	<p><a href="${pageContext.request.contextPath}/">home</a></p>


	<sf:form id="details" method="post" action="${pageContext.request.contextPath}/createaccount" commandName="user">
		<table class="formtable">
			<tr><td class="label">Username: </td><td><sf:input class="control" name="username" type="text" path="username"/>
			<div class="error"><sf:errors path="username"></sf:errors></div></td></tr>
			<tr><td class="label">Name: </td><td><sf:input class="control" name="name" type="text" path="name"/>
			<div class="error"><sf:errors path="name"></sf:errors></div></td></tr>
			<tr><td class="label">Email: </td><td><sf:input class="control" name="email" type="text" path="email"/>
			<div class="error"><sf:errors path="email"></sf:errors></div></td></tr>
			<tr><td class="label">Password: </td><td><sf:input class="control" name="password" id="password" type="password" path="password"/>
			<div class="error"><sf:errors path="password"></sf:errors></div></td></tr>
			<tr><td class="label">Confirm Password: </td><td><input class="control" id="confirmpass" name="confirmpass" type="password"/>
			<div id="matchpass"></div>
			</td></tr>
			<tr><td class="label">&nbsp;</td><td><input class="control" value="create account" type="submit"/></td></tr>
		</table>
	</sf:form>
