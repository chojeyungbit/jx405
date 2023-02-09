<%@ page import="dbConn.ConnectionMaker" %>
<%@ page import="dbConn.MySqlConnectionMaker" %>
<%@ page import="controller.StudentController" %>
<%@ page import="model.StudentDTO" %><%--
  Created by IntelliJ IDEA.
  User: BIT
  Date: 2023-02-09
  Time: 오후 4:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    int id = Integer.parseInt(request.getParameter("id"));
    int korean = Integer.parseInt(request.getParameter("korean"));
    int english = Integer.parseInt(request.getParameter("english"));
    int math = Integer.parseInt(request.getParameter("math"));
    ConnectionMaker connectionMaker = new MySqlConnectionMaker();
    StudentController controller = new StudentController(connectionMaker.makeConnection());
    StudentDTO studentDTO = controller.selectOne(id);
    studentDTO.setKorean(korean);
    studentDTO.setEnglish(english);
    studentDTO.setMath(math);
    controller.update(studentDTO);

    response.sendRedirect("/day0209/printOne.jsp?id="+id);
%>
</body>
</html>
