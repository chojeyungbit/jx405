<%--
  Created by IntelliJ IDEA.
  User: BIT
  Date: 2023-02-09
  Time: 오후 5:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>학생 추가 하기</title>
</head>
<body>
<form action="/day0209/insert_logic.jsp" method="post">
    <table>
        <tr>
            <td>이름</td>
            <td><input type="text" name="name"></td>
        </tr>
        <tr>
            <td>국어</td>
            <td><input type="text" name="korean"></td>
        </tr>
        <tr>
            <td>영어</td>
            <td><input type="text" name="english"></td>
        </tr>
        <tr>
            <td>수학</td>
            <td><input type="text" name="math"></td>
        </tr>
        <tr>
            <td colspan="2">
                <button>입력</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
