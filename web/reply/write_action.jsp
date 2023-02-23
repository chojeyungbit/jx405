<%@ page import="model.UserDTO" %>
<%@ page import="model.ReplyDTO" %>
<%@ page import="controller.ReplyController" %>
<%@ page import="connector.ConnectionMaker" %>
<%@ page import="connector.MySqlConnectionMaker" %><%--
  Created by IntelliJ IDEA.
  User: BIT
  Date: 2023-02-13
  Time: 오후 1:22
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
    int boardId = Integer.parseInt(request.getParameter("boardId"));
    String content = request.getParameter("content");
    ReplyDTO replyDTO = new ReplyDTO();
    replyDTO.setBoardId(boardId);
    replyDTO.setWriterId(logIn.getId());
    replyDTO.setContent(content);

    ConnectionMaker connectionMaker = new MySqlConnectionMaker();
    ReplyController replyController = new ReplyController(connectionMaker);

    replyController.insert(replyDTO);

    response.sendRedirect("/board/printOne.jsp?id=" + boardId);
%>
</body>
</html>
