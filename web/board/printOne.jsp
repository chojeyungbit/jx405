<%@ page import="model.UserDTO" %>
<%@ page import="connector.ConnectionMaker" %>
<%@ page import="connector.MySqlConnectionMaker" %>
<%@ page import="controller.BoardController" %>
<%@ page import="model.BoardDTO" %>
<%@ page import="controller.UserController" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="controller.ReplyController" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.ReplyDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <%
        UserDTO logIn = (UserDTO) session.getAttribute("logIn");
        if (logIn == null) {
            response.sendRedirect("/index.jsp");
        }

        int id = Integer.parseInt(request.getParameter("id"));

        ConnectionMaker connectionMaker = new MySqlConnectionMaker();
        BoardController boardController = new BoardController(connectionMaker);
        UserController userController = new UserController(connectionMaker);
        ReplyController replyController = new ReplyController(connectionMaker);

        BoardDTO b = boardController.selectOne(id);
        ArrayList<ReplyDTO> replyList = replyController.selectAll(id);

        pageContext.setAttribute("replyList", replyList);
        pageContext.setAttribute("userController", userController);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
    %>

    <script>
        function confirmDelete() {
            let result = confirm("정말로 삭제하시겠습니까?");
            if (result) {
                location.href = "/board/delete.jsp?id=" +
                <%=b.getId()%>
            }
        }
    </script>
    <title>
        <%=b.getTitle()%>
    </title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
</head>
<body>
<div class="container-fluid">
    <c:set var="b" value="<%=b%>"/>
    <c:set var="logIn" value="<%=logIn%>"/>
    <div class="row align-items-center vh-100  justify-content-center">
        <div class="col-10 mb-3">
            <table class="table table-striped table-dark" id="table-board">
                <tr>
                    <th class="col-2">글번호</th>
                    <td class="col-10">${b.id}
                    </td>
                </tr>
                <tr>
                    <th>제목</th>
                    <td>${b.title}
                    </td>
                </tr>
                <tr>
                    <th>작성자</th>
                    <td><%=userController.selectOne(b.getWriterId()).getNickname()%>
                    </td>
                </tr>
                <tr>
                    <th>작성일</th>
                    <td>
                        <fmt:formatDate value="${b.entryDate}" pattern="yyyy년 MM월 dd일 HH시 mm분 ss초"/>
                    </td>
                </tr>
                <tr class="mb-2">
                    <th>수정일</th>
                    <td><fmt:formatDate value="${b.modifyDate}" pattern="yyyy년 MM월 dd일 HH시 mm분 ss초"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" class="text-center">
                        ${b.content}
                    </td>
                </tr>
                <c:if test="${b.writerId eq logIn.id}">
                    <tr>
                        <td colspan="2" class="text-center">
                            <div class="btn btn-outline-success" onclick="location.href='/board/update.jsp?id=<%=b.getId()%>'">수정하기</div>
                            <div class="btn btn-outline-danger" onclick="confirmDelete()">삭제하기</div>
                        </td>
                    </tr>
                </c:if>
                <tr id="tr-new-row">
                    <td colspan="2" class="text-end" onclick="addNewRow()">
                        새 줄 추가하기
                    </td>
                </tr>
            </table>
        </div>

        <div class="col-10">
            <form action="/reply/write_action.jsp?boardId=${b.id}" method="post">
                <table class="table table-striped">
                    <c:choose>
                        <c:when test="${empty replyList}">
                            <tr>
                                <td>
                                    아직 등록된 댓글이 존재하지 않습니다.
                                </td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${replyList}" var="replyDTO">
                                <tr>
                                    <td>
                                            ${userController.selectOne(replyDTO.writerId).nickname}: ${replyDTO.id} ${replyDTO.content}
                                        <fmt:formatDate value="${replyDTO.entryDate}" pattern="yy/MM/dd"/>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                    <tr>
                        <td>
                            <input type="text" name="content" class="form-control">
                            <button class="btn btn-outline-light">댓글 달기</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>

    </div>
    <script>
        let addNewRow = () => {
            let newRow = document.getElementById('tr-new-row');
            let table = document.getElementById('table-board');

            table.children[0].removeChild(newRow);

            let tr = document.createElement('tr');
            let td = document.createElement('td');
            td.colSpan = 2;

            tr.appendChild(td);

            table.children[0].appendChild(tr);
            table.children[0].appendChild(newRow);
        }
    </script>
</div>
</body>
</html>




















