<%@ page import="dbConn.ConnectionMaker" %>
<%@ page import="controller.StudentController" %>
<%@ page import="dbConn.MySqlConnectionMaker" %><%--
  Created by IntelliJ IDEA.
  User: BIT
  Date: 2023-02-09
  Time: 오후 4:31
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
    ConnectionMaker connectionMaker = new MySqlConnectionMaker();
    StudentController controller = new StudentController(connectionMaker.makeConnection());

    controller.delete(id);

    response.sendRedirect("/day0209/printList.jsp");
%>
</body>
</html>
