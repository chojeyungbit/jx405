<%@ page import="model.UserDTO" %>
<%@ page import="connector.ConnectionMaker" %>
<%@ page import="connector.MySqlConnectionMaker" %>
<%@ page import="controller.BoardController" %>
<%@ page import="model.BoardDTO" %><%--
  Created by IntelliJ IDEA.
  User: BIT
  Date: 2023-02-10
  Time: 오후 3:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    UserDTO logIn = (UserDTO) session.getAttribute("logIn");
    if (logIn == null) {
        response.sendRedirect("/index.jsp");
    }

    int id = Integer.parseInt(request.getParameter("id"));

    ConnectionMaker connectionMaker = new MySqlConnectionMaker();
    BoardController boardController = new BoardController(connectionMaker);
    BoardDTO b = boardController.selectOne(id);

    if (b.getWriterId() != logIn.getId()) {
        response.sendRedirect("/board/printOne.jsp?id=" + id);
    }

    b.setTitle(request.getParameter("title"));
    b.setContent(request.getParameter("content"));

    boardController.update(b);

    response.sendRedirect("/board/printOne.jsp?id=" + id);
%>
</body>
</html>

















