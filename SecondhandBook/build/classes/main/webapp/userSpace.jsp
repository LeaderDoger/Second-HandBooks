<%@page import="com.dgut.mvc.bean.Book"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${requestScope.username}的个人空间</title>
</head>
<body>

	<h4>欢迎你${requestScope.username}</h4>
	<a href="${pageContext.request.contextPath}/userInput.do">修改密码</a>
	<a href="${pageContext.request.contextPath}/index.do">返回</a>
	
	<br>
	<h1>我的二手书出售信息</h1>
	<table border="1" cellspacing="0" cellpadding="6">
		<tr>
			<th>编号</th>
			<th>作者</th>
			<th>书名</th>
			<th>发布时间</th>
			<th width="1200">简介</th>
			<th>状态</th>
			<th>操作</th>
		</tr>
		<c:if test="${!empty requestScope.books}">
			<c:forEach items="${requestScope.books}" var="book">
				<tr>
					<th>${book.id}</th>
					<th>${book.author}</th>
					<th>${book.bookName}</th>
					<th>
						<c:if test="${book.postTime!=null}">${book.postTime}</c:if>
						<c:if test="${book.postTime==null}">未发布</c:if>
					</th>
					
					<th align="left">${book.context}</th>
					
					<c:if test="${book.isPost==0}">
						<th>已提交</th>
						<th><a href="${pageContext.request.contextPath}/editBookInput.book?id=${book.id}">修改</th>
					</c:if>
					<c:if test="${book.isPost==1}">
						<th>已发布</th>
						<th><a href="${pageContext.request.contextPath}/revokeBook.book?id=${book.id}">撤回</th>
					</c:if>
					
				</tr>
			</c:forEach>
		</c:if>
	</table>
</body>
</html>