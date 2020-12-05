<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Course</title>
</head>
<body>
<jsp:useBean id="course" scope="request" type="edu.epam.bsuir.bean.Course"/>
Name: ${course.name}
Start Date: ${course.startDate}
End Date: ${course.endDate}
Finished: ${course.finished}
Lector: ${course.lector}
<input type="hidden" name="courseId" value="${course.id}">
<c:when test="${sessionScope.role == 'Lector'}">
    <a href="${pageContext.request.contextPath}/stop_course">Stop course</a>
</c:when>
<c:otherwise>
    <a href="${pageContext.request.contextPath}/enroll">login</a>
</c:otherwise>
</body>
</html>
