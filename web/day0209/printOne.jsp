<%@ page import="dbConn.ConnectionMaker" %>
<%@ page import="dbConn.MySqlConnectionMaker" %>
<%@ page import="controller.StudentController" %>
<%@ page import="model.StudentDTO" %><%--
  Created by IntelliJ IDEA.
  User: BIT
  Date: 2023-02-09
  Time: 오후 3:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        int id = Integer.parseInt(request.getParameter("id"));
    %>
    <title>개별 학생 보기</title>
    <script>
        function showConfirm() {
            let result = confirm("정말로 삭제하시겠습니까?");
            if (result) {
                location.href = "delete.jsp?id=" +<%=id%>;
            }
        }
    </script>

    <style>
        /* 태그 선택하기 */
        table {
            border: 2px solid black;
        }

        /* 클래스 선택하기 */
        .info_row {
            font-size: 20px;
            color: royalblue;
        }

        /* 아이디 선택하기 */
        #a-update {
            font-size: 30px;
            color: darkorange;
        }

        /* 특정 클래스를 가진 태그 선택하기 */
        tr.selection_row {
            font-size: 40px;
            color: mediumseagreen;
        }
        /*특정 태그 안에 특정 태그 선택하기*/
        tr td {
            border: 1px dotted sienna;
        }
    </style>
</head>
<body>
<%
    ConnectionMaker connectionMaker = new MySqlConnectionMaker();
    StudentController controller = new StudentController(connectionMaker.makeConnection());
    StudentDTO studentDTO = controller.selectOne(id);
    int sum = studentDTO.getKorean() + studentDTO.getEnglish() + studentDTO.getMath();
    double average = (double) sum / 3;
%>
<table class="selection_row">
    <tr class="info_row">
        <td>번호: <%=studentDTO.getId()%>번</td>
        <td>이름: <%=studentDTO.getName()%>
        </td>
    </tr>
    <tr class="info_row">
        <td>국어: <%=studentDTO.getKorean()%>점</td>
        <td>영어: <%=studentDTO.getEnglish()%>점</td>
        <td>수학: <%=studentDTO.getMath()%>점</td>
    </tr>
    <tr class="info_row">
        <td>총점: <%=sum%>점</td>
        <td>평균: <%=average%>점</td>
    </tr>
    <tr class="selection_row">
        <td><a href="/day0209/update.jsp?id=<%=studentDTO.getId()%>" id="a-update">수정</a></td>
        <td><a onclick="showConfirm();">삭제</a></td>
    </tr>
</table>
</body>
</html>
