<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<div class="container px-4 px-lg-5">
		<a class="navbar-brand" href="main.do">쇼핑몰</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
				<li class="nav-item"><a class="nav-link active"
					aria-current="page" href="index.do">Home</a></li>
				<li class="nav-item"><a class="nav-link" href="#!">About</a></li>

				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
					role="button" data-bs-toggle="dropdown" aria-expanded="false">Shop</a>
					<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
						<li><a class="dropdown-item" href="productList.do">All
								Products</a></li>
						<li><hr class="dropdown-divider" /></li>
						<li><a class="dropdown-item" href="#!">Popular Items</a></li>
						<li><a class="dropdown-item" href="#!">New Arrivals</a></li>
					</ul></li>
			</ul>
			<c:choose>
				<c:when test="${!empty id}">
					<form class="d-flex" action="cartList.do">
						<input type="hidden" name="uid" value="${id }">
						<button class="btn btn-outline-dark" type="submit">
							(${id }) <i class="bi-cart-fill me-1"></i> 장바구니 <span
								class="badge bg-dark text-white ms-1 rounded-pill">${cartCount }
							</span>
						</button>
					</form>
					<form class="d-flex" action="memberLoginOut.do">
						<button class="btn btn-outline-dark" type="submit">로그아웃</button>
					</form>
				</c:when>
				<c:otherwise>
					<form class="d-flex" action="memberLoginForm.do">
						<button class="btn btn-outline-dark" type="submit">로그인</button>
					</form>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</nav>