<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<ul id="menu">
    <li><a href="./main"><span>Main</span></a></li>
    <li><a href="./book"><span>Menu</span></a></li>
    <li><a href="#"><span>My orders</span></a></li>
    <c:choose>
        <c:when test="${not empty (user.name)}">
            <li><a href="./userData"><span>User information</span></a></li>
        </c:when>
        <c:otherwise>
            <li><a href="./register"><span>Register</span></a></li>
        </c:otherwise>
    </c:choose>
    <li><a href="#"><span>Comments</span></a></li>
</ul>