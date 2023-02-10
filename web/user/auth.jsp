<%@ page import="connector.ConnectionMaker" %>
<%@ page import="connector.MySqlConnectionMaker" %>
<%@ page import="controller.UserController" %>
<%@ page import="model.UserDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>로그인</title>
</head>
<body>
<%
    ConnectionMaker connectionMaker = new MySqlConnectionMaker();
    UserController userController = new UserController(connectionMaker);

    String username = request.getParameter("username");
    String password = request.getParameter("password");

    UserDTO userDTO = userController.auth(username, password);

    String address;

    if (userDTO == null) {
        address = "/index.jsp";
    } else {
        address = "/board/printList.jsp";
        session.setAttribute("logIn", userDTO);
    }

    response.sendRedirect(address);
%>
</body>
</html>














