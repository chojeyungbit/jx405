<%@ page import="model.StudentDTO" %>
<%@ page import="dbConn.ConnectionMaker" %>
<%@ page import="dbConn.MySqlConnectionMaker" %>
<%@ page import="controller.StudentController" %><%--
  Created by IntelliJ IDEA.
  User: BIT
  Date: 2023-02-09
  Time: 오후 5:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        String name = request.getParameter("name");
        int korean = Integer.parseInt(request.getParameter("korean"));
        int english = Integer.parseInt(request.getParameter("english"));
        int math = Integer.parseInt(request.getParameter("math"));

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName(name);
        studentDTO.setKorean(korean);
        studentDTO.setEnglish(english);
        studentDTO.setMath(math);

        ConnectionMaker connectionMaker = new MySqlConnectionMaker();
        StudentController controller = new StudentController(connectionMaker.makeConnection());

        controller.insert(studentDTO);

        response.sendRedirect("/day0209/printList.jsp");
    %>
</body>
</html>
