<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="//cdn.ckeditor.com/4.16.1/full/ckeditor.js"></script>
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<script>
		$(function () {
			CKEDITOR.replace('content', {
				filebrowserUploadUrl: '${pageContext.request.contextPath}/fileUpload',
				height: '600px',
				width: '800px'
			})

/* 			$('#btnUpdate').click(function (e) {
				e.preventDefault();
				console.log(CKEDITOR.instances.content.getData());

				let id = $('#id').val();
				let title = $('#title').val();
				let content = CKEDITOR.instance.content.getData();

				$.ajx({
					url: 'ajaxBulletinUpdate',
					data: {
						id: id,
						title: title,
						content: content
					},
					type: 'post',
					success: function (data) {
						console.log(data);
						if (data == null) {
							alert('변경사항이 없습니다.')
						} else {
							alert('변경완료')
						}
					},
					error: function (err) {
						console.log(err);
					}
				});
			}); */
		});
		
		function bulletinUpdate(e){
			e.preventDefault();
			let id = document.getElementById("cid").innerHTML;
			let title = document.getElementById("ctitle").value;
			let content = document.getElementById("ccontent").value;
			
			frm.id.value = id;
			frm.title.value = title;
			frm.content.value = content;
			frm.submit();
		}
	</script>
</head>

<body>
	<div align="center">
		<h3>게시글 내용보기</h3>
		<form id="frm" action="bulletinUpdate.do" method="post">
			<input type="hidden" name="id" value="${bulletin.id }">
			<table border="1">
				<tr>
					<th>순번</th>
					<td id="cid">${bulletin.id }</td>
					<th>작성일자</th>
					<td>${bulletin.regDate }</td>
					<th>작성자</th>
					<td>${bulletin.writer }</td>
					<th>조회수</th>
					<td>${bulletin.hit }</td>
				</tr>
				<tr>
					<th>제목</th>
					<td colspan="7">
						<c:if test="${id eq bulletin.writer }">
							<input id="ctitle" name="title" type="text" value="${bulletin.title }">
						</c:if>
						<c:if test="${id ne bulletin.writer }">
							${bulletin.title }
						</c:if>
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td colspan="7">
						<textarea id="ccontent" name="content" rows="6" cols="90">${bulletin.content }</textarea></td>
				</tr>
			</table>
			<div>
				<button type="button" onclick="location.href='bulletinList.do'">목록보기</button>
				<c:if test="${id eq bulletin.writer }">
					<button type="button" onclick="bulletinUpdate(event)">수정</button>
					<button type="submit" >삭제</button>
				</c:if>
			</div>
		</form>
	</div>
</body>

</html>