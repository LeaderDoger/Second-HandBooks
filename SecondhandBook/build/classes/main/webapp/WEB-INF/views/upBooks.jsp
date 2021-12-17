<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>发布/修改出售书籍消息平台</title>
</head>
<body>
	<br>
	<hr>
		<form action="${pageContext.request.contextPath }/uploadBook.book"
			method="post">
			书名:<input type="text" name="bookName" required="required" /><br> 
			作者:<input type="text" name="author" required="required" /><br> 
			简介:<textarea rows="10" cols="50" name="context">${requestScope.book.context}</textarea><br>
			<input type="submit" value="提交" />
			<a href="${pageContext.request.contextPath}/index.do">返回</a>
		</form>
	<hr>
	<h4>您还未发布的二手书籍出售消息</h4>
	<table border="1" cellspacing="0" cellpadding="5">
		<tr>
			<th width="50">编号</th>
			<th width="100">书名</th>
			<th width="100">作者</th>
			<th width="1000">简介</th>
			<th width="100">修改</th>
			<th width="100">发布</th>
			
		</tr>
		<c:if test="${!empty requestScope.unPostBooks}">
			<c:forEach items="${requestScope.unPostBooks}" var="unPostBook">
				<tr>
					<th>${unPostBook.id}</th>
					<th>${unPostBook.author}</th>
					<th>${unPostBook.bookName}</th>
					<th align="left">${unPostBook.context}</th>
					<td align="center"><a href="${pageContext.request.contextPath}/editBookInput.book?id=${unPostBook.id}">修改</a></td>
					<td align="center"><a href="${pageContext.request.contextPath}/postBook.book?id=${unPostBook.id}">发布</a></td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
</body>
</html>