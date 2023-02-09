<%@ page import="dbConn.ConnectionMaker" %>
<%@ page import="dbConn.MySqlConnectionMaker" %>
<%@ page import="controller.StudentController" %>
<%@ page import="model.StudentDTO" %><%--
  Created by IntelliJ IDEA.
  User: BIT
  Date: 2023-02-09
  Time: 오후 4:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>학생 정보 수정하기</title>
</head>
<body>
<%
    int id = Integer.parseInt(request.getParameter("id"));
    ConnectionMaker connectionMaker = new MySqlConnectionMaker();
    StudentController controller = new StudentController(connectionMaker.makeConnection());
    StudentDTO studentDTO = controller.selectOne(id);
%>
<form action="/day0209/update_action.jsp?id=<%=studentDTO.getId()%>" method="post">
    <table>
        <tr>
            <td>번호: <%=studentDTO.getId()%>
            </td>
            <td>이름: <%=studentDTO.getName()%>
            </td>
            <td>국어: <input type="text" value="<%=studentDTO.getKorean()%>" name="korean"></td>
            <td>영어: <input type="text" value="<%=studentDTO.getEnglish()%>" name="english"></td>
            <td>수학: <input type="text" value="<%=studentDTO.getMath()%>" name="math"></td>
            <td>
                <button>수정하기</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
