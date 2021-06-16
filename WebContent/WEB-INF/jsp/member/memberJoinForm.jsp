<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	$(function() {
		$('#idCheck').click(function() {
			if ($('#memberId').val() == "") {
				alert('아이디를 입력하세요!');
				$('#memberId').focus();
				return;
			}
			$.ajax({
				url : 'ajaxMemberIdCheck',
				data : {
					id : $('#memberId').val()
				},
				type : 'post',
				success : function(data) {
					console.log(data);
					if (data > 0) {
						alert('아이디가 존재합니다. 다른아이디를 입력하세요');
						$('#memberId').val("");
						$('#memberId').focus();
					} else {
						alert('사용가능한 아이디입니다.');
						$('#idCheck').val('checked');
						$('#memberPwd').focus();
					}
				},
				error : function(err) {
					console.log(err);
				}
			});
		});
	});
	function formCheck() {
		if (frm.memberId == "") {
			alert("아이디를 입력하세요");
			frm.memberId.focus();
			return false;
		}
		if (frm.idCheck.value == 'unChecked') {
			alert("중복체크를 하세요.");
			return false;
		}
		if (frm.memberPwd.value == "") {
			alert("비밀번호를 입력하세요.");
			frm.memberPwd.focus();
			return false;
		}
		frm.submit();

	}
</script>

<div align="center">
	<div>
		<h2>회원가입</h2>
	</div>
	<div>
		<form id="frm" action="memberJoin.do" method="post">
			<div>
				<table border="1">
					<tr>
						<th width="150">아이디</th>
						<td width="300"><input type="text" id="memberId"
							name="memberId">
							<button type="button" id="idCheck" value="unChecked">중복체크</button>
						</td>
					</tr>
					<tr>
						<th width="150">비밀번호</th>
						<td width="300"><input type="text" id="memberPwd"
							name="memberPwd"></td>
					</tr>
					<tr>
						<th width="150">이름</th>
						<td width="300"><input type="text" id="memberName"
							name="memberName"></td>
					</tr>
					<tr>
						<th width="150">핸드폰</th>
						<td width="300"><input type="text" id="phone" name="phone"></td>
					</tr>
					<tr>
						<th width="150">이메일</th>
						<td width="300"><input type="text" id="email" name="email"></td>
					</tr>
				</table>
			</div>
			<div>
				<button type="button" onclick="formCheck()">회원가입</button>
				<button type="reset">취소</button>
				<button type="button" onclick="location.href='index.do'">홈</button>
			</div>
		</form>

	</div>


</div>