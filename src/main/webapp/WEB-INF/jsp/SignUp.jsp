<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.get('lang')}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html lang="${sessionScope.get('lang')}">
<body>
<div class="login-page">
    <div class="form">
        <c:choose>
            <c:when test="${not empty requestScope.error}">
                <div class = "text"><fmt:message key="login.error"/></div>
            </c:when>
        </c:choose>
        <div id="error-message" class="text"></div>
        <form id ="login-form" class="login-form" method="post">
            <input type="hidden" name="command" value="/login_user">
            <label for="username">
                <input id ="username" name="username" placeholder="<fmt:message key="login.username"/>" required/>
            </label>
            <label for="password">
                <input id="password" name="password" placeholder="<fmt:message key="login.password"/>" required/>
            </label>
            <label>
                <input type="radio" name="role" value="Student" checked="checked">Student
                <input type="radio" name="role" value="Lector">Lector
            </label>
            <button type="submit"><fmt:message key="login.login"/></button>
            <p class="message"><fmt:message key="login.question"/><br>
                <a href="${pageContext.request.contextPath}/login"><fmt:message key="login.suggestion"/></a>
            </p>
        </form>
    </div>
</div>
</body>
</html>