package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestBookDao;
import com.javaex.vo.GuestBookVo;

@Service
public class GuestbookService {

	@Autowired
	private GuestBookDao guestBookDao;
	
	public List<GuestBookVo> getList() {
		return guestBookDao.getList();
	}
	
	public void add(GuestBookVo guestBookVo) {
		guestBookDao.add(guestBookVo);
	}
	
	public void delete(GuestBookVo guestBookVo) {
		guestBookDao.delete(guestBookVo);
	}
	
}
