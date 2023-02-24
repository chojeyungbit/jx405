<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>게시판</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
    <script src="/assets/js/board/printList.js"></script>
    <link rel="stylesheet" href="/assets/board/printList.css">
</head>
<body onload="initPage()">
<div class="container-fluid">
    <div class="row vh-100 align-items-center">
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
                    <tr>
                        <td colspan="5" id="td-pagination"></td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="row">
                <div class="col-10 text-end">
                    <span class="btn btn-outline-info" onclick="location.href='/board/write.jsp'">글 작성하기</span>
                </div>
            </div>
        </div>

    </div>
</div>
</body>
</html>















