package com.yedam.bulletin.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.bulletin.service.BulletinService;
import com.yedam.bulletin.serviceImpl.BulletinServiceImpl;
import com.yedam.bulletin.vo.BulletinVO;
import com.yedam.common.DbCommand;
import com.yedam.notice.web.NoticeListPaging;

public class BulletinInsert implements DbCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		//입력값을 DB에 insert후 -> bulletinList.jsp
		String writer = request.getParameter("id");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		BulletinVO vo = new BulletinVO();
		vo.setContent(content);
		vo.setTitle(title);
		vo.setWriter(writer);
		
		BulletinService service = new BulletinServiceImpl();
		int cnt = service.insertBulletin(vo);
		
		DbCommand command = new NoticeListPaging();
		String path = command.execute(request, response);

		request.setAttribute("bulletin", vo);
		
		BulletinList commad = new BulletinList();				//변경된 리스트 
		commad.execute(request, response);
		
		return "bulletin/bulletinList.tiles";
	}

}
