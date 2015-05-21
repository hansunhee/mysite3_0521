<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% pageContext.setAttribute("newCharLine", "\n"); %>
<!doctype html>
<html>
<head>
<title>mysite3</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite3/assets/css/guestbook.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/mysite3/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$(".deleteImg").click(function(){
		var no=$(this).next().val();
		location.href="/mysite3/guestbook/delete?no="+no;
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
			<div id="guestbook">
				<form action="/mysite3/guestbook/insert" method="post">
				<table border=1 width=500 id="listTable">
				<tr>
					<th>이름</td><td><input type="text" name="name" required="required"></td>
					<th>비밀번호</td><td><input type="password" name="password" required="required"></td>
				</tr>
					<tr>
					<td colspan=4><textarea name="message" cols=70 rows=5 required="required"></textarea></td>
				</tr>

				</table>
				<div id="insertBtnDiv">
					<input type="submit" VALUE="등록하기" class="btn" id="insertBtn">
				</div>
				</form>
				
				<ul>
					<li>
						<c:forEach items="${list }" var="vo" varStatus="status">
						<table width=510 border=1 class="guestlistTable">
							<tr>
								<th>${status.index + 1 }</th>
								<td>${vo.name }</td>
								<td>${vo.date }</td>
								<td class="delete">
								<img src="/mysite3/assets/images/delete.png" class="deleteImg" width="20px">
								<input type="hidden" value="${vo.no }">
								</td>
							</tr>
							<tr>
								<td colspan=4 class="message">${fn:replace(vo.message, newCharLine,"<br>") }</td>
							</tr>
						</table>
						</c:forEach>
						<br>
					</li>
				</ul>
			</div>
		</div>
		<div id="navigation">
			<c:import url="/WEB-INF/views/include/navigation.jsp">
				<c:param name="type" value="guestbook"></c:param>
			</c:import>
		</div>
		<div id="footer">
			<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		</div>
	</div>
</body>
</html>