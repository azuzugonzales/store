
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html>
<body>
<c:if test="${not empty (sessionScope.order)}" >
    <c:forEach var="garment" items="${sessionScope.order.garments}" >
        </br>
        <c:out value="${garment.name} Price = ${garment.price}'$'" />
    </c:forEach>
    </br>

</c:if>
</body>
</html>
