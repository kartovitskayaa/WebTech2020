<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.get('lang')}"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <title>Add course</title>
</head>
<body>
<form id ="login-form" class="login-form" method="post">
    <c:choose>
        <c:when test="${not empty requestScope.error}">
            <div class = "text"><fmt:message key="course.error"/></div>
        </c:when>
    </c:choose>
    <input type="hidden" name="command" value="/add_course">
    <label for="name">
        <input id ="name" name="name" placeholder="<fmt:message key="course.name"/>" required/>
    </label>
    <label for="startDate">
        <input id="startDate" name="startDate" placeholder="<fmt:message key="course.startDate"/>" required/>
    </label>
    <label for="endDate">
        <input id="endDate" name="endDate" placeholder="<fmt:message key="course.endDate"/>" required/>
    </label>
    <button type="submit"><fmt:message key="course.create"/></button>
</form>
</body>
</html>
