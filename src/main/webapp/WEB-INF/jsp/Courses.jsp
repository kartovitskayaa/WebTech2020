<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.get('lang')}"/>
<fmt:setBundle basename="messages"/>

<jsp:useBean id="courses" scope="request" type="java.util.List"/>

<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:useBean id="course" scope="request" type="edu.epam.bsuir.bean.Course"/>
<c:forEach var="horse" items="${courses}" varStatus="status">
    <form method="post">
        <input type="hidden" name="courseId" value="${course.id}">
        <a href="${pageContext.request.contextPath}/get_course">${course.name} &nbsp; ${course.startDate} &nbsp;&nbsp;&nbsp;&nbsp; ${course.endDate}
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${course.finished} &nbsp;&nbsp;&nbsp; ${course.lector.login}</a>
    </form>
</c:forEach>
</body>
</html>
