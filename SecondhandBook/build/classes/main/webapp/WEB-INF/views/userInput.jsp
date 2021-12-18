<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>密码修改</title>
</head>
<body>

<h4>密码修改</h4>
<form action="${pageContext.request.contextPath }/changePassword.do" method="post">
		用户名:	<input type="text" name="name" disabled="disabled" value="${requestScope.username}"/><br>
		旧密码:	<input type="password" name="oldPassword" 		/><br>
		新密码:	<input type="password" name="newPassword"  	/><br>
		重复新密码:	<input type="password" name="qrnewPassword" 	/><br>
		<input type="submit" value="Submit"/>
	</form>
	<br>
	<a href="${pageContext.request.contextPath}/index.do">返回</a>
</body>
</html>