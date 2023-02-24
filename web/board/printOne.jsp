<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>
    </title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
    <script src="/assets/js/board/printOne.js"></script>
</head>
<body onload="initPage()">
<div class="container-fluid">
    <div class="row justify-content-center vh-100 align-items-center">
        <div class="row justify-content-center">
            <div class="col-10">
                <table class="table table-striped table-info" id="table-board">
                    <tr>
                        <th>글 번호</th>
                        <td id="td-id"></td>
                    </tr>
                    <tr>
                        <th>글 제목</th>
                        <td id="td-title"></td>
                    </tr>
                    <tr>
                        <th>글 작성자</th>
                        <td id="td-writer"></td>
                    </tr>
                    <tr>
                        <th>작성일</th>
                        <td id="td-entry-date"></td>
                    </tr>
                    <tr>
                        <th>수정일</th>
                        <td id="td-modify-date"></td>
                    </tr>
                    <tr>
                        <th colspan="2" class="text-center">내용</th>
                    </tr>
                    <tr>
                        <td colspan="2" id="td-content"></td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>




















