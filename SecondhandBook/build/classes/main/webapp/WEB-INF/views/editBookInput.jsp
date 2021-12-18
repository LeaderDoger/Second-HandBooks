<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="${pageContext.request.contextPath }/editBook.book",method="post">
		书名:<input type="text" name="bookName" required="required" value="${requestScope.book.bookName}"/><br>
		作者:<input type="text" name="author" required="required" value="${requestScope.book.author}"/><br>
		简介:<textarea rows="10" cols="50" name="context">${requestScope.book.context}</textarea><br>
		<input type="submit" value="提交" />
	</form>

</body>
</html>