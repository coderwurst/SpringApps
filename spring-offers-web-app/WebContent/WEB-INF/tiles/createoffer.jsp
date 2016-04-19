<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	Create a new Offer
	<p><a href="${pageContext.request.contextPath}/">home</a></p>


	<sf:form method="post" action="${pageContext.request.contextPath}/docreate" commandName="offer">
		<sf:input type="hidden" name="id" path="id"/>		<!-- path="id" is the Spring version for jsp (not always needed) -->
		
		<table class="formtable">
			<tr><td class="label">Services: </td><td><sf:textarea class="control" name="text" rows="10" cols="10" path="text"></sf:textarea><br/>
			<sf:errors path="text" cssClass="error"></sf:errors></td></tr>
		<tr>
			<td class="label">&nbsp;</td>
			<td><input class="control" value="save advert" type="submit" /></td>
		</tr>
		
		<c:if test="${ offer.id != 0 }">
			<tr>
				<td class="label">&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td class="label">&nbsp;</td>
				<td><input class="delete" id="delete" name="delete" class="control"
					value="delete this advert" type="submit" /></td>
			</tr>
		</c:if>
	</table>
	</sf:form>

	<script>

		function onDeleteClicked(event) {
			event.preventDefault();		// prevents form being submitted
			var doDelete = confirm("are you sure you want to delete this offer?");
			
			if (doDelete == false) {
				event.preventDefault();
			}
			
		}
		
		function onReady() {
			$("#delete").click(onDeleteClicked);
		}
	
		$(document).ready(onReady);
	
	</script>
