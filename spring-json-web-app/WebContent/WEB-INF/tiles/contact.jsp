<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

	<h2>Send Message</h2>
	<p><a href="${pageContext.request.contextPath}/">home</a></p>

	<sf:form method="post" commandName="message">
	
	<input type="hidden" name="_flowExectionKey" value="${ flowExecutionKey }"></input>
	<input type="hidden" name="_eventId" value="send"></input>
		
		<table class="formtable">
			<tr><td class="label">Your Name: </td><td><sf:input class="control" type="text" path="name" value="${fromName}"/>
			<div class="error"><sf:errors path="name"></sf:errors></div></td></tr>
			<tr><td class="label">Your Email: </td><td><sf:input class="control" type="text" path="email" value="${fromEmail}"/>
			<div class="error"><sf:errors path="email"></sf:errors></div></td></tr>
			<tr><td class="label">Subject: </td><td><sf:input class="control" type="text" path="subject"/>
			<div class="error"><sf:errors path="subject"></sf:errors></div></td></tr>
			<tr><td class="label">Your Message: </td><td><sf:textarea class="control" type="text" path="content"/>
			<div class="error"><sf:errors path="content"></sf:errors></div></td></tr>
			
			<tr><td class="label">&nbsp;</td><td><input class="control" value="send" type="submit"/></td></tr>
		</table>
	</sf:form>
