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
            <td>
                <input type="text" name="lastName" value="${param.lastName}" />
            </td>
        </tr>
        <tr>
            <td>
                Email:
            </td>
        </tr>
        <tr>
            <td>
                <input type="email" name="email" value="${param.email}" />
            </td>
            <td>
                <div style="color: red;"> ${emailError} </div>
            </td>

        </tr>
        <tr>
            <td>
                Login:
            </td>
            <td>
                <input id="login" type="login" name="login" value="${param.login}" />
            </td>
            <td>
                <div style="color: red"> ${loginError} </div>
            </td>
        </tr>
        <tr>
            <td>
                Password:
            </td>
            <td>
                <input id="password" type="password" name="password" onchange="validatePwd()" onkeyup="validatePwd()" />
            </td>
        </tr>
        <tr>
            <td>
                Repeat password:
            </td>
            <td>
                <input id="passwordRepeat" type="password" name="passwordRepeat" onchange="validatePwd()" onkeyup="validatePwd()" />
            </td>
            <td>
                <div id="pwdRepeat" style="color: red; display: none"> Passwords are not equal! </div>
            </td>
        </tr>
        <tr>
            <td>
                Date of birth:
            </td>
            <td>
                <input type="data" dataformatas="yyyy-MM--dd" name="dateOfBirth" onchange="hideDateErrorMessage()" value="${param.dateOfBirth}" />
            </td>
        </tr>
        <tr>
            <div id="dataError" style="color: red"> ${dataError} </div>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="register" name="register" formmethod="post" />
            </td>

        </tr>
    </table>
</form>