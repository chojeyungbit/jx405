<%@ page import="java.util.ArrayList" %>
<%@ page import="model.StudentDTO" %>
<%@ page import="dbConn.ConnectionMaker" %>
<%@ page import="dbConn.MySqlConnectionMaker" %>
<%@ page import="controller.StudentController" %><%--
  Created by IntelliJ IDEA.
  User: BIT
  Date: 2023-02-09
  Time: 오후 3:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>학생 목록</title>
</head>
<body>
<%
    ConnectionMaker connectionMaker = new MySqlConnectionMaker();
    StudentController controller = new StudentController(connectionMaker.makeConnection());
    ArrayList<StudentDTO> list = controller.selectAll();
%>
<table>
    <tr>
        <td>번호</td>
        <td>이름</td>
    </tr>
    <%
        for (StudentDTO s : list) {
    %>
    <tr>
        <td><%=s.getId()%>
        </td>
        <td><a href="/day0209/printOne.jsp?id=<%=s.getId()%>"><%=s.getName()%>
        </a></td>
    </tr>
    <%
        }
    %>
</table>
<a href="/day0209/insert.jsp">학생 입력하기</a>
</body>
</html>
