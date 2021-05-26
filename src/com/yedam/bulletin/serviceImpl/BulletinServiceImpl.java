package com.yedam.bulletin.serviceImpl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.bulletin.service.BulletinService;
import com.yedam.bulletin.vo.BulletinVO;
import com.yedam.common.DAO;
import com.yedam.notice.vo.NoticeVO;

public class BulletinServiceImpl extends DAO implements BulletinService {
	PreparedStatement psmt;
	ResultSet rs;
	String sql;
	
	//전체조회
	@Override
	public List<BulletinVO> bulletinSelectList() {
		List<BulletinVO> list = new ArrayList<>();
		
		sql = "select * from bulletin order by 1";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				BulletinVO vo = new BulletinVO();
				vo.setContent(rs.getString("content"));
				vo.setHit(rs.getInt("hit"));
				vo.setId(rs.getInt("id"));
				vo.setRegDate(rs.getDate("reg_date"));
				vo.setTitle(rs.getString("title"));
				vo.setWriter(rs.getString("writer"));
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
	public BulletinVO bulletinSelect(BulletinVO vo) {
		sql = "select * from bulletin where id=?";
		List<BulletinVO>list = new ArrayList<>();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getId());
			
			rs = psmt.executeQuery();
			if(rs.next()) {
				vo.setContent(rs.getString("content"));
				vo.setHit(rs.getInt("hit"));
				vo.setId(rs.getInt("id"));
				vo.setRegDate(rs.getDate("reg_date"));
				vo.setTitle(rs.getString("title"));
				vo.setWriter(rs.getString("writer"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return vo;
	}

	@Override
	public int insertBulletin(BulletinVO vo) {
		sql ="insert into bulletin values(bulletin_seq.nextval,?,?,?,sysdate,?)";
		int r=0;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getTitle());
			psmt.setString(2, vo.getContent());
			psmt.setString(3, vo.getWriter());
			psmt.setInt(4, vo.getHit());
			
			r = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return r;
	}

	@Override
	public int updateBulletin(BulletinVO vo) {
		String sql = "update bulletin set title=?,content=? where id=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getTitle());
			psmt.setString(2, vo.getContent());
			psmt.setInt(3, vo.getId());
			int r = psmt.executeUpdate();
			System.out.println(r+ "건 수정");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return 0;
	}

	@Override
	public int deleteBulletin(BulletinVO vo) {
		String sql = "delete from bulletin where id=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
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

	public List<BulletinVO> bulletinListPaging(int page) {
		String sql = "select b.*\r\n"
				+ "from ( select rownum rn, a.*\r\n"
				+ "      from (select * from bulletin n order by n.id)a\r\n"
				+ "    )b\r\n"
				+ "where b.rn between ? and ? ";
		List<BulletinVO> list = new ArrayList<>();
		
		int firstCnt = 0,  lastCnt = 0;
		firstCnt = (page -1) * 10 + 1;	//1, 11
		lastCnt = (page * 10);
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1,  firstCnt);
			psmt.setInt(2, lastCnt);
			
			rs = psmt.executeQuery();
			while(rs.next()) {
				BulletinVO vo  = new BulletinVO();
				vo.setContent(rs.getString("content"));
				vo.setHit(rs.getInt("hit"));
				vo.setId(rs.getInt("id"));
				vo.setWriter(rs.getString("writer"));
				vo.setRegDate(rs.getDate("reg_date"));
				vo.setTitle(rs.getString("title"));
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
