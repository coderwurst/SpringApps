<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<div id="messages">
	

</div>

<meta name="_csrf" content="${_csrf.token}" />
<!-- default header name is X-CSRF-TOKEN -->
<meta name="_csrf_header" content="${_csrf.headerName}" />

<script type="text/javascript">
	
	var timer;		// global variable to be used in start and stop timer methods
	
	function showReply(i) {
		stopTimer();		// called to prevent the screen refresh and loosing entered text
		$("#form" + i).toggle();
	}
	
	function success(data) {
		$("#form" + data.target).toggle();
		$("#alert" + data.target).text("message sent");
		startTimer();		// message sent text appears and disappears when screen refreshed
	}
	
	function error() {
		alert("error sending message");
	}
	
	function sendMessage(i, name, email) {
		var text = $('#textBox' + i).val();

		$.ajax( {				// takes a hash as param to call method ({})
			"type" : 'POST',								// post command
			"url" : '<c:url value="/sendMessage" />',		// url in spring controller
			"data" : JSON.stringify({"target" : i, "text" : text , "name" : name, "email": email}),		// data hash for JSON
			"success" : success,				// next 4 parts always required
			"error" : error,
			contentType : "application/json",
			dataType : "json"
		});		
	}
	
	function showMessages(data) {
		
		$("div#messages").html("");		// clear the div each reload
		
		for (var i = 0; i < data.messages.length; i++) {
			// message data
			var message = data.messages[i];
			
			// create new div for each message
			var messageDiv = document.createElement("div");
			messageDiv.setAttribute("class", "message");		// for formatting
			
			// span appear on side of a div (not inline) for subject
			var subjectSpan = document.createElement("span");
			subjectSpan.setAttribute("class", "subject");		// for formatting
			subjectSpan.appendChild(document.createTextNode(message.subject));
			
			messageDiv.appendChild(subjectSpan);
			
			// message body span
			var messageBodySpan = document.createElement("span");
			messageBodySpan.setAttribute("class", "messageBody");		// for formatting
			messageBodySpan.appendChild(document.createTextNode(message.content));
			
			messageDiv.appendChild(messageBodySpan);
			
			// name span
			var nameSpan = document.createElement("span");
			nameSpan.setAttribute("class", "name");		// for formatting
			nameSpan.appendChild(document.createTextNode(message.name + " ("));
			
			// link containing email
			var link = document.createElement("a");
			link.setAttribute("class", "replyLink");
			link.setAttribute("href", "#");
			link.setAttribute("onClick", "showReply(" + i + ")");
			link.appendChild(document.createTextNode(message.email));
			nameSpan.appendChild(link);
			nameSpan.appendChild(document.createTextNode(")"));
			
			messageDiv.appendChild(nameSpan);
			
			// span for message sent text
			var alertSpan = document.createElement("span");
			alertSpan.setAttribute("class", "alert");
			alertSpan.setAttribute("id", "alert" + i);
			// alertSpan.appendChild(document.createTextNode("message sent"));
			
			messageDiv.appendChild(alertSpan);
			
			// span to contain reply form
			var replyForm = document.createElement("form");
			replyForm.setAttribute("class", "replyForm");		// for formatting
			replyForm.setAttribute("id", "form" + i);
			
			var textArea = document.createElement("textarea");
			textArea.setAttribute("class", "replyArea");
			textArea.setAttribute("id", "textBox" + i);
			
			var replyButton = document.createElement("input");
			replyButton.setAttribute("type", "button");
			replyButton.setAttribute("class", "replyButton");
			replyButton.setAttribute("value", "reply");
			replyButton.onclick = function(contents, name, email) {		// anon function
				return function() {					// sets onclick to return value of this function
							sendMessage(contents, name, email);			// reference to another anon function
						}
			}(i, message.name, message.email);									// i is only added when code is run
			
			replyForm.appendChild(textArea);
			replyForm.appendChild(replyButton);			
			
			messageDiv.appendChild(replyForm);	
			
			// add to messages div
			$("div#messages").append(messageDiv);
		}
	}
	
	function onLoad() {
		updatePage();
		startTimer();
	}
	
	function startTimer() {
		// params - number of seconds, method to be called
		timer = window.setInterval(updatePage, 5000);
	}
	
	function stopTimer() {
		window.clearInterval(timer);
	}
	
	function updatePage() {
		// json to go to url and try to get data - pass data into updateMessage function
		$.getJSON("<c:url value="/getMessages"/>", showMessages);
	}
	

	$(function() {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
	});

	$(document).ready(onLoad);
</script>
