package com.yedam.product.serviceImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.common.DAO;
import com.yedam.product.service.ProductService;
import com.yedam.product.vo.CartVO;
import com.yedam.product.vo.ProductVO;

public class ProductServiceImpl extends DAO implements ProductService {

	PreparedStatement psmt;
	ResultSet rs;
	String sql;	
	
	//cart정보 추가하는 메소드
	public void addCart(String id, String item, int qty) {
		sql = "insert into cart values(?,?,?)";
		try {
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, item);
			psmt.setInt(3, qty);
			int r = psmt.executeUpdate();
			System.out.println(r +"저장");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
	}
	//회원별 장바구니 상품개수
	public int getCountCart(String id) {
		sql = "select count(*) cnt from cart where user_id =?";
		int rCnt = 0;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if(rs.next()) {
				rCnt = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return rCnt;
	}
	
	@Override
	public List<ProductVO> productSelectList() {
		//전체조회
		List<ProductVO> list = new ArrayList<>();
		sql = "select * from product order by 1";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				ProductVO vo = new ProductVO();
				vo.setItemCode(rs.getString("item_code"));
				vo.setItemDesc(rs.getString("item_desc"));
				vo.setItemImage(rs.getString("item_image"));
				vo.setItemName(rs.getString("item_name"));
				vo.setLikeIt(rs.getInt("like_it"));
				vo.setPrice(rs.getInt("price"));
				vo.setSale(rs.getString("sale"));
				vo.setSalePrice(rs.getInt("sale_price"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return list;
	}

	@Override
	public ProductVO productselect() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertproduct(ProductVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updatetproduct(ProductVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteproduct(ProductVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	private void close() {
		if(rs!=null)
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if(psmt!=null)
			try {
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if(conn!=null)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	@Override
	public List<CartVO> cartList() {
		List<CartVO> list = new ArrayList<>();
		sql = "select*\r\n"
				+ "from(select user_id, item_code, sum(item_qty)qty from cart group by user_id, item_code) cart, product p\r\n"
				+ "where cart.item_code =p.item_code\r\n"
				+ "and cart.user_id=?";
		try {
			psmt = conn.prepareStatement(sql);
			//psmt.setString(1, id);
			rs = psmt.executeQuery();
			while(rs.next()) {
				CartVO vo = new CartVO();
				vo.setItemCode(rs.getString("item_code"));
				vo.setItemDesc(rs.getString("item_desc"));
				vo.setItemImage(rs.getString("item_image"));
				vo.setItemName(rs.getString("item_name"));
				vo.setLikeIt(rs.getInt("like_it"));
				vo.setPrice(rs.getInt("price"));
				vo.setSale(rs.getString("sale"));
				vo.setSalePrice(rs.getInt("sale_price"));
				vo.setId(rs.getString("id"));
				
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return list;
	}
}
