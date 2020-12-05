<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.get('lang')}"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <title>Title</title>
</head>
<body>
<c:choose>
    <c:when test="${sessionScope.role == 'Lector'}">
        <a href="${pageContext.request.contextPath}/add_course_page">Create course</a>
        <a href="${pageContext.request.contextPath}/courses_page">See All courses</a>
    </c:when>
    <c:otherwise>
        <a href="${pageContext.request.contextPath}/login_page">login</a>
        <a href="${pageContext.request.contextPath}/signup_page">signup</a>
    </c:otherwise>
</c:choose>
</body>
</html>
