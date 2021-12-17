<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理员管理平台</title>
</head>
<body>
<h4>欢迎你管理员${requestScope.administrator.userName}</h4>
<a href="${pageContext.request.contextPath}/index.do">返回</a>

<h1>用户信息</h1>
<hr>
	<table border="1" cellspacing="0" cellpadding="5">
		<tr>
			<th width="50">id</th>
			<th width="100">用户名</th>
			<th width="100">身份</th>
			<th width="100">身份权限更改</th>
			<th width="200">发布权限</th>
			<th width="100">发布权限更改</th>
			<th width="100">注销</th>
		</tr>
		<c:if test="${!empty requestScope.users}">
			<c:forEach items="${requestScope.users}" var="user">
				<tr>
					<th>${user.uid}</th>
					<th>${user.userName}</th>
					<th>
						<c:if test="${user.isAdministrator==1}">管理员</c:if>
						<c:if test="${user.isAdministrator==0}">会员</c:if>
					</th>
					<th>
						<a href="${pageContext.request.contextPath }/changeUserAdministratorRight.do?id=${user.uid}">
							<c:if test="${user.isAdministrator==1}">更改为会员</c:if>
							<c:if test="${user.isAdministrator==0}">更改为管理员</c:if>
						</a>
					</th>
					<th>
						<c:if test="${user.postRight==1}">可正常发布</c:if>
						<c:if test="${user.postRight==0}">无权限</c:if>
					</th>
					<th>
						<a href="${pageContext.request.contextPath }/changeUserPostRight.do?id=${user.uid}">更改权限</a>
					</th>
					<th>
						<a href="${pageContext.request.contextPath }/deleteUser.do?id=${user.uid}">注销</a>
					</th>
				</tr>
			</c:forEach>
		</c:if>
	</table>
</body>
</html>