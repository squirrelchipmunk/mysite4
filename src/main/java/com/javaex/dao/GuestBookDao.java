package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestBookVo;


@Repository
public class GuestBookDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<GuestBookVo> getGuestBookList() {
		List<GuestBookVo> guestBookList = sqlSession.selectList("guestbook.getGuestBookList");
		return guestBookList;
	}
	
	public void addGuestBook(GuestBookVo guestBookVo) {
		sqlSession.insert("guestbook.addGuestBook", guestBookVo);
	}

	public void deleteGuestBook(GuestBookVo guestBookVo) {
		sqlSession.delete("guestbook.deleteGuestBook", guestBookVo);
	}
	
}