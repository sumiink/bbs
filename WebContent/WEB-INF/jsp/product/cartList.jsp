<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1" width="400px">
		<tr>
			<th>상품명</th>
			<th>단가</th>
			<th>상품설명</th>
			<th>수량</th>
			<th>금액</th>
			<th>&nbsp;</th>
		</tr>
		<c:forEach var="row" items="${}">
			<tr align="center">
				<td>${id }</td>

				<td><fmt:formatNumber value="${product.name}" pattern="#,###,###" /></td>
				<td><input type="number" name="amount" style="width: 30px;"
					value="<fmt:formatNumber value="${row.amount}"
                            pattern="#,###,###" />">
					<input type="hidden" name="cart_id" value="${}">

				</td>
				<td><fmt:formatNumber value="${}" pattern="#,###,###" /></td>
				<td><a
					href="${path}/shop/cart/delete.do?cart_id=${row.cart_id}">[삭제]</a>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="5" align="right">장바구니 금액 합계 : <fmt:formatNumber
					value="${}" pattern="#,###,###" /><br> 배송료 :
				${}<br> 총합계 : <fmt:formatNumber value="${}"
					pattern="#,###,###" />
			</td>
		</tr>
	</table>
	<button id="btnUpdate">수정</button>
	<button type="button" id="btnDelete">장바구니 비우기</button>
</body>
</html>