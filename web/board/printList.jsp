<%@ page import="model.UserDTO" %>
<%@ page import="connector.ConnectionMaker" %>
<%@ page import="connector.MySqlConnectionMaker" %>
<%@ page import="controller.BoardController" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.BoardDTO" %>
<%@ page import="controller.UserController" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>게시판</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/assets/board/printList.css">
</head>
<body>
<div class="container-fluid">
    <div class="row vh-100 align-items-center">
        <%
            UserDTO logIn = (UserDTO) session.getAttribute("logIn");

            if (logIn == null) {
                response.sendRedirect("/index.jsp");
            }

            int pageNo;
            try {
                String pageStr = request.getParameter("pageNo");
                pageNo = Integer.parseInt(pageStr);
            } catch (Exception e) {
                pageNo = 1;
            }

            ConnectionMaker connectionMaker = new MySqlConnectionMaker();
            BoardController boardController = new BoardController(connectionMaker);
            UserController userController = new UserController(connectionMaker);

            ArrayList<BoardDTO> list = boardController.selectAll(pageNo);

            int totalPage = boardController.countTotalPage();

            int startNum;
            int endNum;

            if (pageNo < 3) {
                startNum = 1;
                endNum = 5;
            } else if (pageNo > totalPage - 3) {
                startNum = totalPage - 4;
                endNum = totalPage;
            } else if (totalPage <= 5) {
                startNum = 1;
                endNum = totalPage;
            } else {
                startNum = pageNo - 2;
                endNum = pageNo + 2;
            }

            pageContext.setAttribute("list", list);
            pageContext.setAttribute("userController", userController);
            pageContext.setAttribute("currentPage", pageNo);
            pageContext.setAttribute("startPage", startNum);
            pageContext.setAttribute("endPage", endNum);
            pageContext.setAttribute("totalPage", totalPage);
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
                                <tr class="table-danger page-end" onclick="location.href='/board/printOne.jsp?id=${b.id}'">
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
                            <tr>
                                <td colspan="5">
                                    <ul class="pagination justify-content-center m-auto">
                                        <li class="page-item">
                                            <a href="/board/printList.jsp?pageNo=${1}" class="page-link page-end">
                                                <span>&laquo;</span>
                                            </a>
                                        </li>
                                        <c:forEach begin="${startPage}" end="${endPage}" var="i">
                                            <c:choose>
                                                <c:when test="${currentPage eq i}">
                                                    <li class="page-item active">
                                                        <a href="/board/printList.jsp?pageNo=${i}" class="page-link">
                                                            <span>${i}</span>
                                                        </a>
                                                    </li>
                                                </c:when>
                                                <c:otherwise>
                                                    <li class="page-item">
                                                        <a href="/board/printList.jsp?pageNo=${i}" class="page-link">
                                                            <span>${i}</span>
                                                        </a>
                                                    </li>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                        <li class="page-item">
                                            <a href="/board/printList.jsp?pageNo=${totalPage}" class="page-link page-end">
                                                <span>&raquo;</span>
                                            </a>
                                        </li>
                                    </ul>
                                </td>
                            </tr>
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















