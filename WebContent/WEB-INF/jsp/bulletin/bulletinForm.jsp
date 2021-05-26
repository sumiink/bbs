<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>bulletinForm.jsp</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="//cdn.ckeditor.com/4.16.1/standard/ckeditor.js"></script>
<script>
	$(function(){
		CKEDITOR.replace('content', {
			filebrowserUploadUrl: '${pageContext.request.contextPath}/fileUpload',
			height: '600px',
			width: '800px'
		}); 
	})
	
	function formCheck() {
		if(frm.title.value == ""){
			alert("제목을 입력하세요.");
			frm.title.focus();
			return false;
		}
		frm.submit();
	}
</script> 
</head>
<body>
<div align="center">
	<div>
		<h1>게시글 등록</h1>
	</div>
	<div>
		<form id="frm" action="bulletinInsert.do"method="post">
			<input type="hidden" name="id" value="${id }">				<!-- 로그인한 id값 -->
			<div>
				<table border="1">
					<tr>
						<th width="300">제목</th>
						<td width="150">
							<input type="text" id="title" name="title">
						</td>
					</tr>
					<tr>
						<th width="150">내용</th>
						<td width="300">
							<textarea id="content" name="content" rows="" cols=""></textarea>
						</td>
					</tr>
				</table>
			</div>
			<div>
				<button type="button" onclick="formCheck()">등록</button>
				<button type="reset">취소</button>
				<button type="button" onclick="location.href='main.do'">홈</button>
			</div>
		</form>
	</div>
</div>
</body>
</html>