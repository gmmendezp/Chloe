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
        <link rel="stylesheet" href="css/style.css" />
        <link href='http://fonts.googleapis.com/css?family=Josefin+Sans' rel='stylesheet' type='text/css'>
        <title>JSP Page</title>
    </head>
    <body>

        <br/>
        <div class="mainBanner">Products Recommended for You</div>
        <div class="productContainer">
            <c:forEach var="event" items="${events}">    

                <div class="event_name">We noticed you will assist to the following event: <span class="event_name_val">${event.name}</span></div> 
                <div class="event_promo">Here are some recommendations to boost your experience!!!</div>

                <c:forEach var="item" items="${event.items}">
                    <div class="product">
                        <a href="http://www.backcountry.com/${item.url}">
                            <div class="product_images"><img src="http://www.backcountry.com/${item.image}" alt="${item.id}"/></div>
                            <div class="product_brand">${item.brand}</div>
                            <div class="product_title">${item.title}</div>
                            <div class="product_price">${item.price}</div>
                        </a>
                    </div>
                </c:forEach>
                <br/><br/>
            </c:forEach>
        </div>

    </body>
</html>
