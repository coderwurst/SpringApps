<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home Page</title>
</head>
<body>
Hello there!!

Session: <%= session.getAttribute("name") %> </p>

Request: <%= request.getAttribute("name") %> </p>

Request (using el): ${name} </p>

Request using c-out :<c:out value="${name}"></c:out> </p>

<!-- SQL for JSTL -->
<sql:query var="rs" dataSource="jdbc/spring">
select id, name, email, text from offers
</sql:query>


Results using JSTL:
<c:forEach var="row" items="${rs.rows}">
    ID: ${row.id}<br/>
    Name ${row.name}<br/>
    Email ${row.email}<br/>
    Offer ${row.offer}<br/>
</c:forEach>

</body>
</html>