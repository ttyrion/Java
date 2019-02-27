
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>

<html>
    <head>
        <title>Spittr</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/spittles.css" />" >
    </head>
    <body>
        <h1>Recent Spittles</h1>
        <ul class="spittleList">
            <c:forEach items="${spittles}" var="spittle">
                <li id="spittle_<c:out value="${spittle.id}"/>">
                <div class="spittleMessage">
                    <c:out value="${spittle.message}"/>
                </div>
                <div>
                    <span class="spittleTime"><c:out value="${spittle.time}"/></span>
                    <span class="spittleLocation">
                    (<c:out value="${spittle.latitude}"/>, <c:out value="${spittle.longitude}"/>)
                    </span>
                </div>
                </li>
            </c:forEach>
        </ul>
    </body>
</html>