<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%>
<%@ taglib uri="jakarta.tags.functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%pageContext.setAttribute("newLine", "\n");%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="guestbook">
				<form action="${pageContext.request.contextPath }/guestbook/add" method="post">
					<table>
						<tr>
							<td><spring:message code="guestbook.name" /></td><td><input type="text" name="name"></td>
							<td><spring:message code="guestbook.password" /></td><td><input type="password" name="password"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="contents" id="content"></textarea></td>
						</tr>
						<tr>
							<spring:message code="guestbook.check" var="guestbookCheck"/>
							<td colspan=4 align=right><input type="submit" value=" ${guestbookCheck} "></td>
						</tr>
					</table>
				</form>
				<ul>
					<c:set var="count" value="${fn:length(list) }" />
					<c:forEach items="${list }" var="vo" varStatus="status">
						<li>
							<table>
								<tr>
									<td>[${count-status.index }]</td>
									<td>${vo.name }</td>
									<td>${vo.regDate }</td>
									<td><a href="${pageContext.request.contextPath }/guestbook/delete/${vo.id }">삭제</a></td>
								</tr>
								<tr>
									<td colspan=4>
										${fn:replace(vo.contents, newLine, "<br>") }
									</td>
								</tr>
							</table>
							<br>
						</li>				
					</c:forEach>
				</ul>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"/>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>