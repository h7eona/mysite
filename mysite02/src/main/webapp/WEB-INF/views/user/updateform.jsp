<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<%@ page import="mysite.vo.UserVo" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:set var="path" value="${pageContext.servletContext.contextPath}"/>
<%
    UserVo authUser = (UserVo)session.getAttribute("authUser");
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <title>mysite</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <link href="${path}/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="container">
    <c:import url="/WEB-INF/views/includes/header.jsp"/>
    <div id="content">
        <div id="user">

            <form id="update-form" name="updateForm" method="post" action="${path}/user">
                <input type='hidden' name="a" value="update">
                <label class="block-label" for="name">이름</label>
                <input id="name" name="name" type="text">

                <label class="block-label" for="email">이메일</label>
                <h4 id="email"></h4>

                <label class="block-label">패스워드</label>
                <input name="password" type="password">

                <fieldset>
                    <legend>성별</legend>
                    <label>여</label> <input id="gender-female" type="radio" name="gender" value="female">
                    <label>남</label> <input id="gender-male" type="radio" name="gender" value="male">
                </fieldset>

                <input type="submit" value="수정하기">
            </form>
        </div>
    </div>
    <c:import url="/WEB-INF/views/includes/navigation.jsp"/>
    <c:import url="/WEB-INF/views/includes/footer.jsp"/>
</div>
</body>
<script>
    document.getElementById("email").innerHTML = "<%=authUser.getEmail() %>"
    document.getElementById("gender-<%=authUser.getGender()%>").checked = "checked";
    document.getElementById("name").value = "<%=authUser.getName() %>";
</script>
</html>