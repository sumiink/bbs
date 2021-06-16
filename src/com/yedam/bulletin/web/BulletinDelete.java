package com.yedam.bulletin.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.bulletin.service.BulletinService;
import com.yedam.bulletin.serviceImpl.BulletinServiceImpl;
import com.yedam.bulletin.vo.BulletinVO;
import com.yedam.common.DbCommand;
import com.yedam.notice.web.NoticeListPaging;

public class BulletinDelete implements DbCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		
		BulletinService service = BulletinService();
		//service.deleteBulletin(vo);
				
//		request.setAttribute("id", id);
//		
//		BulletinList commad = new BulletinList();				//변경된 리스트 
//		commad.execute(request, response);
		return "bulletin/bulletinList.tiles";
	}

}
