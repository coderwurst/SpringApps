<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/script/jquery.js"></script>

<script type="text/javascript">

function onLoad() {
	$("#password").keyup(checkPasswordsMatch);
	$("#confirmpass").keyup(checkPasswordsMatch);
	$("#details").submit(canSubmit);
}

function canSubmit() {
	var password = $("#password").val();
	var confirmPass = $("#confirmpass").val();
	
	if (password != confirmPass) {
		alert("passwords must match");
		return false;
	} else {
		return true;
	}
	
}

function checkPasswordsMatch() {
	var password = $("#password").val();
	var confirmPass = $("#confirmpass").val();

	// let the user get started
	if (password.length > 3 || confirmPass.length > 3) {
	
		if (password == confirmPass) {
			$('#matchpass').text("<fmt:message key='MatchedPasswords.user.password'/>");
			$('#matchpass').addClass("valid");
			$('#matchpass').removeClass("error");
		} else {
			$('#matchpass').text("<fmt:message key='UnmatchedPasswords.user.password'/>");
			$('#matchpass').addClass("error");
			$('#matchpass').removeClass("valid");
		}
	}
	
}

$(document).ready(onLoad);

</script>

<title>Create Offer</title>
</head>
<body>
	<h2>Create a new Account</h2>
	<p><a href="${pageContext.request.contextPath}/">home</a></p>


	<sf:form id="details" method="post" action="${pageContext.request.contextPath}/createaccount" commandName="user">
		<table class="formtable">
			<tr><td class="label">Username: </td><td><sf:input class="control" name="username" type="text" path="username"/>
			<div class="error"><sf:errors path="username"></sf:errors></div></td></tr>
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

</body>
</html>