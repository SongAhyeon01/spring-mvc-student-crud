<%--
  Created by IntelliJ IDEA.
  User: SONG
  Date: 2024-02-20
  Time: PM 3:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8" />
    <title>학생 정보 조회</title>
</head>
<body>
    이름: ${student.name}<br />
    이메일: ${student.email}<br />
    <c:if test="${not empty hideScore}" >
    </c:if>
    <c:if test="${empty hideScore}">
        성적: ${student.score}<br />
        평가: ${student.comment}<br />
    </c:if>
<br />
<a href="/student/${student.id}/modify">정보 수정</a><br />
</body>
</html>
