<%--
  Created by IntelliJ IDEA.
  User: SONG
  Date: 2024-02-20
  Time: PM 3:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8" />
    <title>학생 정보 수정</title>
</head>
<body>
<form method="post" action="/student/${student.id}/modify">
    이름: <input type="text" name="name" value="${student.name}" /><br />
    이메일: <input type="text" name="email" value="${student.email}" /><br />
    성적: <input type="text" name="score" value="${student.score}" /><br />
    평가: <textarea name="comment" rows="10" cols="80">${student.comment}</textarea><br />
    <br />
    <input type="submit" />
</form>

</body>
</html>
