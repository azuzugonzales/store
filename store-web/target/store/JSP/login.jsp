<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
    </head>

    <body>
    <c:choose>
        <%--|| user.name.equals('anonymous')--%>
        <c:when test="${user == null || user.name.equals('anonymous')}">
            <from action-"/store/login" method="post">
				<table border="0">
					<tr>
						<tb>
							<h3>Login:</h3>
						</tb>
						<td>
							<h3>
								<input type="text" name="email"/>
							</h3>
						</td>
					</tr>
						<c.if test="${loginFailed}">
							<div style="display: ruby-text; color: crimson">Login failed</div>
						</c:if>
					</tr>
					<tr>
					<tr>
						<td colspan="2" align="center">
							<input type="submit" value="login" name="login" />
						</td>
						<td colspan="2" align="center">
							<input type="submit" value="register" name="register" />
						</td>
					</tr>
				</table>
			</form>
        </c:when>
    </c:choose>
		<table border="1">
		<tr>Our garments</tr>
			<c:forEach var="garment" items="${garments}">
				<tr>
					<td>
						${garment.id}
					</td>
					<td>
						<a href="/store/garment?id=${garment.id}">${garment.name}</a>
					</td>
					<td>
						<c:out value="$garment.description"/>
					</td>
					<td>
						$<fmt:formatNumber pattern="#,##" value="${meal.price}" />
					</td>
				</tr>
			</c:forEach>	
		</table>
		
    </body>
</html>