package com.yedam.bulletin.web;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.bulletin.service.BulletinService;
import com.yedam.bulletin.serviceImpl.BulletinServiceImpl;
import com.yedam.bulletin.vo.BulletinVO;
import com.yedam.common.DbCommand;
import com.yedam.notice.service.NoticeService;
import com.yedam.notice.serviceImpl.NoticeServiceImpl;
import com.yedam.notice.web.NoticeListPaging;


public class BulletinUpdate implements DbCommand{

	@Override  
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		BulletinVO vo = new BulletinVO();
		vo.setId(Integer.parseInt(id));
		vo.setTitle(title);
		vo.setContent(content);
		
		BulletinService service = new BulletinServiceImpl();
		service.updateBulletin(vo);
		
		DbCommand command = new NoticeListPaging();
		String path = command.execute(request, response);
		
		request.setAttribute("bulletin", vo);
		
		return "bulletin/bulletinList.tiles";
	}
 
}
