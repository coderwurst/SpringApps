<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

	Create a new Offer
	<p><a href="${pageContext.request.contextPath}/">home</a></p>


	<sf:form method="post" action="${pageContext.request.contextPath}/docreate" commandName="offer">
		<table class="formtable">
			<tr><td class="label">Services: </td><td><sf:textarea class="control" name="text" rows="10" cols="10" path="text"></sf:textarea><br/>
			<sf:errors path="text" cssClass="error"></sf:errors></td></tr>
			<tr><td class="label">&nbsp;</td><td><input class="control" value="create advert" type="submit"/></td></tr>
		</table>
	</sf:form>
