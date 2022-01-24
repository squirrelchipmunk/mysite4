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
	
	public List<GuestBookVo> getList() {
		List<GuestBookVo> guestBookList = sqlSession.selectList("guestbook.getList");
		return guestBookList;
	}
	
	public void add(GuestBookVo guestBookVo) {
		sqlSession.insert("guestbook.add", guestBookVo);
	}

	public void delete(GuestBookVo guestBookVo) {
		sqlSession.delete("guestbook.delete", guestBookVo);
	}
	
}