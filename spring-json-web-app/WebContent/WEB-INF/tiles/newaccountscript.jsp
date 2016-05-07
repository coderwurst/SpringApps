<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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