<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board">
			
				<!--  검색 -->
				<form id="search_form" action="" method="post">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				
				<!--  게시글 리스트 -->
				<c:set var="listCount" value="${fn:length(list)}"/>
				<c:set var="rowSizePerPage" value="5"/>
				<c:set var="firstPage" value="${pageInfo.get('firstPage')}"/>
				<c:set var="curPage" value="${pageInfo.get('curPage')}"/>
				<c:set var="lastPage" value="${pageInfo.get('lastPage')}"/>
				
					<table class="tbl-ex">
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>글쓴이</th>
							<th>조회수</th>
							<th>작성일</th>
							<th>&nbsp;</th>
						</tr>			
						
						<c:forEach var="vo" items="${list}" varStatus="index">	
							<c:if test='${((curPage-1)*rowSizePerPage+1<=index.count) && (index.count<=(curPage-1)*rowSizePerPage+rowSizePerPage)}'>
								<tr>
									<td>${listCount-index.count+1}</td>
									<td style="text-align:left; padding-left:${vo.depth*20}px"> 
										<c:if test='${vo.depth!=0 }'>
											<img src="${pageContext.request.contextPath }/assets/images/reply.png">
										</c:if>
										<a href="${pageContext.request.contextPath}/board/view/${vo.id}">${vo.title}</a></td>
									<td>${vo.userName}</td>
									<td>${vo.hit}</td>
									<td>${vo.regDate }</td>
									<td>									
										<c:if test='${(not empty authUser) and (authUser.id eq vo.userId)}'>
											<a href="${pageContext.request.contextPath}/board/delete/${vo.id}" class="del">삭제</a>
										</c:if>
									</td>
								</tr>
							</c:if>
						</c:forEach>
					</table>
				
				<div class="pager">
					<ul>
						<c:choose>
							<c:when test="${firstPage == 1}">
								<li>◀</li>
							</c:when>
							<c:otherwise>
								<li><a href="${pageContext.request.contextPath}/board/${firstPage-1}/${curPage-1}/${lastPage-1}">◀</a></li>
							</c:otherwise>
						</c:choose>
					
						
						<c:forEach var="i" begin="${firstPage }" end="${lastPage }" step="1">
							<c:choose>
								<c:when test="${i == curPage }">
									<li class="selected">${i }</li>
								</c:when>
								<c:when test="${i> ((listCount -1)/rowSizePerPage+1)}">
									<li>${i }</li>
								</c:when>
								<c:otherwise>
									<li><a href="${pageContext.request.contextPath}/board/${firstPage}/${i}/${lastPage}">${i }</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						
						<c:choose>
							<c:when test="${curPage == Math.floor((listCount -1)/rowSizePerPage)+1  }">
								<li>▶</li>
							</c:when>
							<c:otherwise>
								<li><a href="${pageContext.request.contextPath}/board/${firstPage+1}/${curPage+1}/${lastPage+1}">▶</a></li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>				
				
				<div class="bottom">
					<c:if test='${not empty authUser }'>
						<a href="${pageContext.request.contextPath}/board/write" id="new-book">글쓰기</a>
					</c:if>
				</div>				
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"/>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>