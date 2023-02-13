<%@ page import="model.UserDTO" %>
<%@ page import="connector.ConnectionMaker" %>
<%@ page import="connector.MySqlConnectionMaker" %>
<%@ page import="controller.BoardController" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.BoardDTO" %>
<%@ page import="controller.UserController" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>게시판</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
</head>
<body>
<div class="container-fluid">
    <div class="row vh-100 align-items-center">
        <%
            UserDTO logIn = (UserDTO) session.getAttribute("logIn");

            if (logIn == null) {
                response.sendRedirect("/index.jsp");
            }

            ConnectionMaker connectionMaker = new MySqlConnectionMaker();
            BoardController boardController = new BoardController(connectionMaker);
            UserController userController = new UserController(connectionMaker);

            ArrayList<BoardDTO> list = boardController.selectAll();

            pageContext.setAttribute("list", list);
            pageContext.setAttribute("userController", userController);
        %>
        <c:choose>
            <c:when test="${list.isEmpty()}">
                <div class="row">
                    <div class="col-6">
                        <span>아직 등록된 글이 존재하지 않습니다.</span>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="row justify-content-center">
                    <div class="col-10">
                        <table class="table table-primary table-hover">
                            <thead>
                            <tr>
                                <th>번호</th>
                                <th>제목</th>
                                <th>작성자</th>
                                <th>작성일</th>
                                <th>수정일</th>
                            </tr>
                            </thead>

                            <tbody>
                            <c:forEach var="b" items="${list}">
                                <tr class="table-danger" onclick="location.href='/board/printOne.jsp?id=${b.id}'">
                                    <td>
                                            ${b.id}
                                    </td>
                                    <td>
                                            ${b.title}
                                    </td>
                                    <td>
                                            ${userController.selectOne(b.writerId).nickname}
                                    </td>
                                    <td>
                                            ${b.entryDate}
                                    </td>
                                    <td>
                                            ${b.modifyDate}
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>

                    </div>
                </div>
            </c:otherwise>
        </c:choose>

        <div class="row">
            <div class="col-12 text-end">
                <span class="btn btn-outline-info" onclick="location.href='/board/write.jsp'">글 작성하기</span>
            </div>
        </div>
    </div>
</div>
</body>
</html>















