package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;


@Repository
public class GuestbookDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<GuestbookVo> getList() {
		List<GuestbookVo> guestBookList = sqlSession.selectList("guestbook.getList");
		return guestBookList;
	}
	
	public void add(GuestbookVo guestbookVo) {
		sqlSession.insert("guestbook.add", guestbookVo);
	}

	public void delete(GuestbookVo guestbookVo) {
		sqlSession.delete("guestbook.delete", guestbookVo);
	}

	public int insertSelectKey(GuestbookVo guestbookVo) {
		System.out.println("guestbookDao.insertSelectKey");
		System.out.println(guestbookVo);
		sqlSession.insert("guestbook.insertSelectKey", guestbookVo);
		System.out.println(guestbookVo);
		return guestbookVo.getNo();
	}
	
	public GuestbookVo selectByNo(int no) {
		return sqlSession.selectOne("guestbook.selectByNo", no);
	}
	
}