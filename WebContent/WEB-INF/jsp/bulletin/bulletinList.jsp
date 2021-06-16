<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 리스트</title>
<script>
	// 게시글 id로 조회
	function formSubmit(bulletinId){
		frm.id.value = bulletinId; 
		frm.submit();
	}
</script>

</head>
<body>
	<h3>게시글 리스트</h3>
	<form id = "frm" action="bulletinSelect.do" method="post">
		<input type="hidden" id="id" name="id">
	</form>
	<div align="center">
		<div style="width: 80%">
			<table class="table" border="1">
			<tr>
				<th width="100">순번</th>
				<th width="200">제목</th>
				<th width="150">작성자</th>
				<th width="150">작성일자</th>
				<th width="100">조회수</th>
			</tr>
			<c:forEach items="${bulletinList }" var="vo">
				<tr onclick="formSubmit(${vo.id })">
					<td>${vo.id }</td>
					<td>${vo.title }</td>
					<td>${vo.writer }</td>
					<td>${vo.regDate }</td>
					<td>${vo.hit }</td>
				</tr>
			</c:forEach>
		</table>
		<div>
			<button type="button" onclick="location.href='main.do'">홈</button>
			<c:if test="${!empty id }">
			<button type="button" onclick="location.href='bulletinForm.do'">등록</button>
			<button type="button" onclick="location.href=''">삭제</button>
			</c:if>
		</div>
	</div>
	</div>
</body>
</html>