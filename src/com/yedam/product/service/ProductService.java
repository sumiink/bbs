package com.yedam.product.service;

import java.util.List;

import com.yedam.member.vo.MemberVO;
import com.yedam.product.vo.CartVO;
import com.yedam.product.vo.ProductVO;

public interface ProductService {
	
	List<ProductVO> productSelectList();
	ProductVO productselect();
	public int insertproduct(ProductVO vo);
	public int updatetproduct(ProductVO vo);
	public int deleteproduct(ProductVO vo);
	
	List<CartVO> cartList();
}
