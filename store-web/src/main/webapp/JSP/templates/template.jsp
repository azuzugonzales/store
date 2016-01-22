<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

    <table border="0" width="100%">
        <tr>
            <td align="right" valign="top" width="100%" height="150px" colspan="2">
                <tiles:insertAttribute name="header" />
            </td>
        </tr>

        <tr>
            <td align="left" valign="middle" width="150"><tiles:insertAttribute name="menu" /></td>
            <td align="center" valign="middle" width="500"><tiles:insertAttribute name="body" /></td>
            <%--<td align="center" valign="middle" width="500"><tiles:insertAttribute name="order" /></td>--%>
        </tr>
    </table>