<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>로그인</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<body>
<%
    // jsp에서 자바 코드를 작성할 시에는
    // % 태그를 사용하게 되고
    // 해당 페이지에서 사용할 수 있는 변수를 단순 출력할 시에는
    // %= 태그를 사용하게 된다.
    int a = 3;
    int b = 7;
    int sum = a + b;
%>
<%= sum%>
<form action="/day0209/login.jsp" method="post">
    <input type="text" name="username" id="username" placeholder="아이디" class="form-control"> <br>
    <input type="password" name="password" id="password" placeholder="비밀번호" class="form-control"> <br>
    <button class="btn btn-outline-primary">로그인</button>
</form>
</body>
</html>
