package com.yedam.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yedam.product.service.ProductService;
import com.yedam.product.serviceImpl.ProductServiceImpl;

public class AddCart implements DbCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// cart 테이블에 한건 추가(회원 아이디, 상품정보, 수량(=1)
		HttpSession session = request.getSession();
		
		String id = request.getParameter("id");
		String itemCode = request.getParameter("itemCode");
		int qty = 1;
		
		ProductServiceImpl service = new ProductServiceImpl();
		service.addCart(id, itemCode, qty);
		
		service = new ProductServiceImpl();
		int cnt =service.getCountCart(id);
		
		session.setAttribute("cartCount", cnt);
		
		
		return "/index.do";
	}

}
