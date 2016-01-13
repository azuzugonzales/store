<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <title></title>
    </head>
    <body>

        <table border="1">
            <tr>
                <td>Id</td>
                <td>
                    ${garment.id}
                </td>
            </tr>
            <tr>
                <td>Name</td>
                <td>
                    ${garment.name}
                </td>
            </tr>
            <tr>
                <td>Description</td>
                <td>
                    ${garment.description}
                </td>
            </tr>
            <tr>
                <td>Price</td>
                <td>
                    $<fmt:formatNumber pattern="#,##" value="${garment.price}" />
                </td>
            </tr>
        </table>
    </body>
</html>