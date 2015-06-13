<%-- 
    Document   : widget.jsp
    Created on : Jun 13, 2015, 4:25:56 PM
    Author     : Martín
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    <c:forEach var="item" items="${items}">
        <c:out value="${item.title}" /><br/>
    </c:forEach>
    </body>
</html>
