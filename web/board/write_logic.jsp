<%@ page import="model.UserDTO" %>
<%@ page import="model.BoardDTO" %>
<%@ page import="controller.BoardController" %>
<%@ page import="connector.MySqlConnectionMaker" %>
<%@ page import="connector.ConnectionMaker" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    request.setCharacterEncoding("UTF-8");
    UserDTO logIn = (UserDTO) session.getAttribute("logIn");
    if (logIn == null) {
        response.sendRedirect("/index.jsp");
    }

    ConnectionMaker connectionMaker = new MySqlConnectionMaker();
    BoardController boardController = new BoardController(connectionMaker);
    BoardDTO b = new BoardDTO();

    b.setWriterId(logIn.getId());
    b.setTitle(request.getParameter("title"));
    b.setContent(request.getParameter("content"));

    boardController.insert(b);

    response.sendRedirect("/board/printList.jsp");
%>
</body>
</html>
