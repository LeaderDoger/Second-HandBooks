<%@page import="com.dgut.mvc.bean.Book"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>二手书出售信息搜索</title>
</head>
<body>
<h4>模糊搜索</h4>
<hr>
		<form action="${pageContext.request.contextPath }/searchBooks.book" method="post">
			编号:		<input 	type="text" name="id"  	/><br>
			卖家id:	<input 	type="text" name="fromUid"  	/><br>
			书名:		<input 	type="text" name="bookName"  	/><br>
			作者:		<input 	type="text" name="author"  	/><br>
			<input type="submit" value="查询"/>
			<a href="${pageContext.request.contextPath}/index.do">返回</a>
		</form>
<hr>

<h1>已发布的二手书出售信息</h1>
	<table border="1" cellspacing="0" cellpadding="5" >
		<tr>
			<th width="50">编号</th>
			<th width="100">书名</th>
			<th width="100">作者</th>
			<th width="100">卖家id</th>
			<th width="200">发布时间</th>
			<th width="1000">简介</th>
		</tr>
		<c:if test="${!empty requestScope.books}">
			<c:forEach items="${requestScope.books}" var="book">
				<tr>
					<th>${book.id}</th>
					<th>
						<a href="${pageContext.request.contextPath}/bookSpace.book?id=${book.id}">${book.bookName}</a>
					</th>
					<th>${book.author}</th>
					<th>${book.fromUid}</th>					
					<th>${book.postTime}</th>
					<th align="left">${book.context}</th>
				</tr>
			</c:forEach>
		</c:if>
	</table>
</body>
</html>