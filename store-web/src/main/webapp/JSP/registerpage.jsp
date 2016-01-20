<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<script type="text/javascript" language="javascript" src="resources/scripts/validate.js"></script>

<form action="/store/register" method="post" name="register" onsubmit="return validateForm()">
    <table border="0">
        <tr>
            <td>
                Name:
            </td>
            <td>
                <input type="text" name="name" value="${param.name}" />
            </td>
        </tr>
        <tr>
            <td>
                LastName:
            </td>
        </tr>
    </table>
</form>