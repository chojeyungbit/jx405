<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    pageContext.setAttribute("title", "제목입니다.");
    pageContext.setAttribute("userNick", "관리자");
%>
<div class="container-fluid">
    <div class="row justify-content-end">
        <div class="col-10">
            <span>${title}</span>
            <span>${userNick} 님, 환영합니다.</span>
        </div>
    </div>
</div>
