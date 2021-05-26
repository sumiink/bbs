package com.yedam.bulletin.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.bulletin.service.BulletinService;
import com.yedam.bulletin.serviceImpl.BulletinServiceImpl;
import com.yedam.bulletin.vo.BulletinVO;
import com.yedam.common.DbCommand;

public class BulletinList implements DbCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		//게시글 리스트 - bulletinList.jsp 
		
		BulletinService service = new BulletinServiceImpl();
		List<BulletinVO> list = service.bulletinSelectList();
		
		request.setAttribute("bulletinList", list);
		
		
		return "bulletin/bulletinList.tiles";
	}

}
