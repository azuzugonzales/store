<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form action="/store/register" method="post">
    <table border="0">
        <tr>
            <td>
                <h3>Name:</h3>
            </td>
            <td>
                <h3>
                    <input type="text" name="name" />
                </h3>
            </td>
        </tr>
        <tr>
            <td>
                <h3>LastName:</h3>
            </td>
            <td>
                <h3>
                    <input type="text" name="lastName" />
                </h3>
            </td>
        </tr>
        <tr>
            <td>
                <h3>Email:</h3>
            </td>
        </tr>
        <tr>
            <td>
                <h3>
                    <input type="text" name="email" />
                </h3>
            </td>
        </tr>
        <tr>
            <td>
                <h3>Login:</h3>
            </td>
            <td>
                <h3>
                    <input type="login" name="login" />
                </h3>
            </td>
        </tr>
        <tr>
            <td>
                <h3>Password:</h3>
            </td>
            <td>
                <h3>
                    <input type="password" name="password" />
                </h3>
            </td>
        </tr>
        <tr>
            <td>
                <h3>Date of birth</h3>
            </td>
            <td>
                <h3>
                    <input type="date" dataformatas="yyyy-mm-dd" name="dateOfBirth" />
                </h3>
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="register" name="register" formmethod="post" />
            </td>
        </tr>
    </table>
</form>
</body>
</html>
