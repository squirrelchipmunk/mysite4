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
	
	public void delete(GuestbookVo guestbookVo) {
		guestbookDao.delete(guestbookVo);
	}

	public GuestbookVo addGuestResultVo(GuestbookVo guestbookVo) {
		int no = guestbookDao.insertSelectKey(guestbookVo);
		return guestbookDao.selectByNo(no);
	}
	
}
