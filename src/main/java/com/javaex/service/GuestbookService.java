package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service
public class GuestbookService {

	@Autowired
	private GuestbookDao guestbookDao;
	
	public List<GuestbookVo> getList() {
		return guestbookDao.getList();
	}
	
	public void add(GuestbookVo guestbookVo) {
		guestbookDao.add(guestbookVo);
	}
	
	public int delete(GuestbookVo guestbookVo) {
		return guestbookDao.delete(guestbookVo);
	}

	public GuestbookVo addGuestResultVo(GuestbookVo guestbookVo) {
		int no = guestbookDao.insertSelectKey(guestbookVo);
		return guestbookDao.selectByNo(no);
	}

	public String removeGuest(GuestbookVo guestbookVo) {
		int count = guestbookDao.delete(guestbookVo);
		if(count > 0)
			return "success";
		else
			return "fail";
	}
	
}
