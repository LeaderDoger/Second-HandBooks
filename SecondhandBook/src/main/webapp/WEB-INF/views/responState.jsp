<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${requestScope.title}</title>
</head>
<body>
<h1>${requestScope.state} </h1><br>
<a href ="${pageContext.request.contextPath}/${requestScope.nextWeb}">返回</a>
</body>
</html>