<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%>
<%@ taglib uri="jakarta.tags.functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="user">
				<form id="login-form" name="loginform" method="post" action="${pageContext.request.contextPath }/user/auth">
					<label class="block-label" for="email"><spring:message code="user.signin.label.email" /></label>
					<input id="email" name="email" type="text" value="${email }">
					<label class="block-label" ><spring:message code="user.signin.label.password" /></label>
					<input name="password" type="password" value="">
					<c:if test='${"fail" == result}'>
						<p style="padding: 5px 0; margin 0; color:#f00">
							<spring:message code="user.signin.fail" />
						</p>
					</c:if>
					<spring:message code="user.signin.button.text" var="userSignInButtonText" />
					<input type="submit" value="${userSignInButtonText}">
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"/>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>