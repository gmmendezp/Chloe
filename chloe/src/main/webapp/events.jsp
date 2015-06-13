<%-- 
    Document   : events
    Created on : Jun 13, 2015, 11:42:05 AM
    Author     : Martín
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Eventos</title>
    </head>
    <body>
        ${events}
    <c:forEach var="event" items="${events}">
        ${event.name}
    </c:forEach>
    </body>
</html>
