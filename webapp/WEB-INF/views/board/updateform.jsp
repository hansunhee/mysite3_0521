<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/mysite3/assets/css/board.css" rel="stylesheet" type="text/css">
<title>mysite3</title>
<script type="text/javascript" src="/mysite3/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#deleteBtn").click(function(){
		var no=$(this).next().val();
		location.href="/mysite3/board/delete?no="+no;
	})
})

</script>
</head>
<body>

<div id="container">
		<div id="header">
			<c:import url="/WEB-INF/views/include/header.jsp">
			</c:import>
		</div>
		<div id="content">
			<div id="board">
				<form action="/mysite3/board/update" method="post">
				<input type="hidden" name="no" value="${vo.no }">
				<table width=500 id="insertForm">
				<tr>
					<th>제목</th><td colspan="3"><input type="text" name="title" required="required" id="title-box" value="${vo.title }"></td>
				</tr>
				<tr>
					<th>작성자</th><td><input type="text" name="name" required="required" readonly="readonly" value="${vo.membername}"></td>
					<th>조회수</th><td><input type="text" name="viewcnt" required="required" readonly="readonly" value="${vo.viewcnt }"></td>
				</tr>
					<tr>
					<td colspan=4><textarea name="content" cols=70 rows=5 required="required">${vo.content}</textarea></td>
				</tr>
				</table>
				<div id="writeBtnDiv">
					<span>
					<input type="submit" value="수정하기" id="updateBtn" class="btn">
					
					<input type="button" VALUE="삭제하기" class="btn" id="deleteBtn">
					<input type="hidden" value="${vo.no }">
					</span>
				</div>
				</form>

				
			</div>
		</div>
		<div id="navigation">
			<c:import url="/WEB-INF/views/include/navigation.jsp">
				<c:param name="type" value="board"></c:param>
			</c:import>
		</div>
		<div id="footer">
			<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		</div>
	</div>
</body>
</html>
