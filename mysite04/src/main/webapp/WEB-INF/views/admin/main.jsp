<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%>
<%@ taglib uri="jakarta.tags.functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/admin/main.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/admin/include/header.jsp" />
		<div id="wrapper">
			<div id="content">
				<div id="site-form">
					<form method="post" action="${pageContext.request.contextPath }/admin/main/update" enctype="multipart/form-data">
						<input type="hidden" name="profile" value="${siteVo.profile }" />
						<label class="block-label" for="title">사이트 타이틀</label>
						<input id="title" name="title" type="text" value="${siteVo.title }">
						
						<label class="block-label" for="welcome">환영 메세지</label>
						<input id="welcomeMessage" name="welcome" type="text" value="${siteVo.welcome }">

						<label class="block-label">프로필 이미지</label>
						<img id="profile" src="${pageContext.request.contextPath }${siteVo.profile }">
						<input type="file" name="file">

						<label class="block-label">사이트 설명</label>
						<textarea name="description">${siteVo.description }</textarea>
						
						<input type="submit" value="변경" />
					</form>
				</div>
			</div>
			<c:import url="/WEB-INF/views/admin/include/navigation.jsp">
				<c:param name="menu" value="main"/>
			</c:import>
		</div>
	</div>
</body>
</html>