<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${book.bookName}</title>
<script src="http://cdn.bootcss.com/jquery/2.2.4/jquery.js"></script>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
	-webkit-touch-callout: none;
	/* prevent callout to copy image, etc when tap to hold */
	-webkit-text-size-adjust: none;
	/* prevent webkit from resizing text to fit */
	-webkit-tap-highlight-color: rgba(210, 210, 210, 0.35);
	/* make transparent link selection, adjust last value opacity 0 to 1.0 */
	-webkit-user-select: none;
	/* prevent copy paste, to allow, change 'none' to 'text' */
}

body {
	font-family: "微软雅黑";
}

ul, li {
	list-style: none;
}

.ylcon {
	width: 100%;
	min-width: 320px;
}

.tit {
	height: 26px;
	line-height: 26px;
	padding: 0px 15px;
	position: relative;
	font-size: 15px;
	color: #aaa;
	border-bottom: 1px solid rgba(0, 0, 0, 0.15);
}

.story {
	border-bottom: 1px dashed #cecece;
	padding: 0 15px 3px;
	position: relative;
}

.story_t {
	font-size: 1.2em;
	color: rgba(0, 0, 0, 1);
	padding-top: 5px;
	padding-bottom: 2px;
}

.story_m {
	color: rgba(110, 110, 110, 1);
	line-height: 21px;
	word-break: break-all;
	word-wrap: break-word;
	overflow: hidden;
	font-size: 1.2em;
	padding: 2px 0;
}

.story_time {
	color: rgba(154, 154, 154, 1);
	padding: 2px 0;
}

.story_hf {
	background: rgb(245, 245, 245);
	font-size: 1.2em;
	border: 1px solid rgba(204, 204, 204, 0.2);
	border-radius: 2px;
	color: rgba(110, 110, 110, 1);
	padding: 4px;
	padding-left: 20px;
	margin-bottom: 5px;
}

.hf_time {
	color: rgba(154, 154, 154, 1);
	padding: 2px 0;
}

.opbtn {
	position: absolute;
	top: 0;
	right: 0;
}
</style>
<style type="text/css">
.contextForm {
	padding-left: 10px;
	padding-right: 10px;
}
</style>
</head>
<body>


	<div class="contextForm">
		<h1>书籍信息</h1>
		<a href="${pageContext.request.contextPath}/index.do">返回</a>
	</div>

	<hr>
	<br>

	<div class="bookInformation" align="center">
		<table border="1" cellspacing="0" cellpadding="5">
			<tr>
				<th>id</th>
				<th>${book.id}</th>
			</tr>
			<tr>
				<th>书名</th>
				<th>${book.bookName}</th>
			</tr>
			<tr>
				<th>作者</th>
				<th>${book.author}</th>
			</tr>
			<tr>
				<th>卖家id</th>
				<th>${book.fromUid}</th>
			</tr>
			<tr>
				<th>发布时间</th>
				<th>${book.postTime}</th>
			</tr>
			<tr>
				<th width="100">简介</th>
				<th width="1400">${book.context}</th>
			</tr>
		</table>
	</div>

	<br>
	<c:if test="${book.isComment==1}">
		<div class="contextForm">
			<form
				action="${pageContext.request.contextPath }/addComment.comment?bookId=${book.id}"
				method="post">
				<div class="title">留言:</div>
				<textarea rows="5" cols="200" name="context" required="required"></textarea>
				<br> <input type="submit" value="留言" />
			</form>
		</div>
	</c:if>
	<br>
	<h1>所有留言</h1>
	<hr>

	<c:if test="${!empty requestScope.comments}">
		<c:forEach items="${requestScope.comments}" var="comment">
			<div id="messDivId">
				<div class="story">
					<div class="opbtn"></div>
					<p class="story_t">${requestScope.userNames[comment.uid]}</p>
					<p class="story_time">${comment.date}</p>
					<p class="story_m">${comment.context}</p>
					<c:if test="${!empty requestScope.replys[comment.commentId]}">
						<div class="story_hf">
							<c:forEach items="${requestScope.replys[comment.commentId]}"
								var="reply">
								<p>${requestScope.userNames[reply.uid]}:${reply.context}</p>
								<p class="hf_time">${reply.date}</p>
							</c:forEach>
						</div>
					</c:if>
					<div class='reply_textarea'>
						<form action='${pageContext.request.contextPath}/addReply.comment?bookId=${book.id}&parentId=${comment.commentId}'method='post'>
							<textarea name='context${comment.commentId}' rows='5' cols='200' required='required' ></textarea>
							<br>
							<input type='submit' value='回复' />
						</form>
					</div>
				</div>
				<hr>
			</div>
		</c:forEach>
	</c:if>

</body>
</html>