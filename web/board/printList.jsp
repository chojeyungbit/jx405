<%@ page import="model.UserDTO" %>
<%@ page import="connector.ConnectionMaker" %>
<%@ page import="connector.MySqlConnectionMaker" %>
<%@ page import="controller.BoardController" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.BoardDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <%
        UserDTO logIn = (UserDTO) session.getAttribute("logIn");
        System.out.println(logIn);

        if (logIn == null) {
            response.sendRedirect("/index.jsp");
        }

        ConnectionMaker connectionMaker = new MySqlConnectionMaker();
        BoardController boardController = new BoardController(connectionMaker);

        ArrayList<BoardDTO> list = boardController.selectAll();

        if (list.isEmpty()) {
    %>
    <div class="row">
        <div class="col-6">
            <span>아직 등록된 글이 존재하지 않습니다.</span>
        </div>
    </div>
    <%
    } else {
    %>
    <div class="table">
        <div class="row">
            <div class="col">번호</div>
            <div class="col">제목</div>
            <div class="col">작성자</div>
        </div>

        <%
            for (BoardDTO b : list) {
        %>
        <div class="row">
            <div class="col">
                <%=b.getId()%>
            </div>
            <div class="col">
                <%=b.getTitle()%>
            </div>
            <div class="col">
                <%=b.getWriterId()%>
            </div>
        </div>

        <%
            }
        %>
    </div>
    <%
        }
    %>
</div>
</body>
</html>















