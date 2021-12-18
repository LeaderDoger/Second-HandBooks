<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>二手书主页</title>
</head>
<body>

<h1>欢迎${requestScope.userName}<h1>
<a href="${pageContext.request.contextPath}/userSpace.do">个人中心</a>
<a href="${pageContext.request.contextPath}/signUp.do">退出</a><br>
<c:if test="${requestScope.isAdministrator==1}">
	<a href="${pageContext.request.contextPath}/administrator.do">管理员界面</a><br>
</c:if>
<a href="${pageContext.request.contextPath}/upBooks.book">发布/提交出售书籍消息</a>
<a href="${pageContext.request.contextPath}/searchBook.book">搜索出售书籍消息</a>


<h2>已发布的二手书出售信息</h2>
<hr>
	<table border="1" cellspacing="0" cellpadding="5">
		<tr>
			<th width="50">编号</th>
			<th width="100">书名</th>
			<th width="100">作者</th>
			<th width="200">发布时间</th>
			<th width="1000">简介</th>
			<c:if test="${user.isAdministrator==1}">
				<th width="100">更改发布权限</th>
			</c:if>
		</tr>
		<c:if test="${!empty requestScope.books}">
			<c:forEach items="${requestScope.books}" var="book">
				<tr>
					<th>${book.id}</th>
					<th>
						<a href="${pageContext.request.contextPath}/bookSpace.book?bookId=${book.id}">${book.bookName}</a>
					</th>
					<th>${book.author}</th>
					<th>${book.postTime}</th>
					<th align="left">${book.context}</th>
					<c:if test="${user.isAdministrator==1}">
						<th width="100">
							<c:if test="${book.isComment==1}">
								<a href="${pageContext.request.contextPath}/changeBookCommentRight.book?bookId=${book.id}">关闭发布权限</a>
							</c:if>
							<c:if test="${book.isComment==0}">
								<a href="${pageContext.request.contextPath}/changeBookCommentRight.book?bookId=${book.id}">开启发布权限</a>
							</c:if>
						</th>
					</c:if>
					
				</tr>
			</c:forEach>
		</c:if>
	</table>
</body>
</html>