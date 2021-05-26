package com.yedam.notice.serviceImpl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.common.DAO;
import com.yedam.member.web.memberLogOut;
import com.yedam.notice.service.NoticeService;
import com.yedam.notice.vo.NoticeVO;

public class NoticeServiceImpl extends DAO implements NoticeService {
	PreparedStatement psmt;
	ResultSet rs;
	
	public List<NoticeVO> noticeListPaging(int page){
		String sql="select b.*\r\n" //
				+ "from ( select rownum rn, a.*\r\n" //
				+ "      from (select * from notice n order by n.id)a\r\n" //
				+ "    )b\r\n" //
				+ "where b.rn between ? and ?";
		List<NoticeVO> list = new ArrayList<>();
		 
		int firstCnt = 0,  lastCnt = 0;
		firstCnt = (page -1) * 10 + 1;	//1, 11
		lastCnt = (page * 10);	//10, 20 
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1,  firstCnt);
			psmt.setInt(2, lastCnt);
			
			rs = psmt.executeQuery();
			while(rs.next()) {
				NoticeVO vo  = new NoticeVO();
				vo.setContent(rs.getString("content"));
				vo.setHit(rs.getInt("hit"));
				vo.setId(rs.getInt("id"));
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
	
	@Override
	public List<NoticeVO> noticeSelectList() {
		String sql = "select * from notice order by 1";
		List<NoticeVO> list = new ArrayList<>();
		 try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				NoticeVO vo = new NoticeVO();
				vo.setId(rs.getInt("id"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setRegDate(rs.getDate("reg_date"));
				vo.setHit(rs.getInt("hit"));
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
	public NoticeVO noticeSelect(NoticeVO vo) {
		String sql = "select * from notice where id=?";
		 
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getId());
			rs = psmt.executeQuery();
			if(rs.next()) {
				hitCount(vo.getId());	//조회수증가
				
				vo.setId(rs.getInt("id"));
				vo.setContent(rs.getString("content"));
				vo.setRegDate(rs.getDate("reg_date"));
				vo.setHit(rs.getInt("hit"));
				vo.setTitle(rs.getString("title"));
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}finally {
			close();
		}
		return vo;
	}

	@Override
	public int insertNotice(NoticeVO vo) {
		String sql = "insert into notice(id, title, content,reg_data,hit) values(?,?,?,?,?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getId());
			psmt.setString(2, vo.getTitle());
			psmt.setString(3, vo.getContent());
			psmt.setDate(4, (Date) vo.getRegDate());
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return 0;
	}

	@Override
	public int updateNotice(NoticeVO vo) {
		String sql = "update notice set title=?,content=? where id=?";
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
	public int deleteNotice(NoticeVO vo) {
		String sql = "delete from notice where id=?";
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
	
	public void hitCount(int id) {
		String sql = "update notice set hit = hit + 1 where id=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, id);
			psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
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

}
