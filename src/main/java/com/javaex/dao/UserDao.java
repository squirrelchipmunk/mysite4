package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public void insert(UserVo userVo) {
		sqlSession.insert("user.insertUser",userVo);
	}

	public UserVo getAuthUser(UserVo userVo) {
		UserVo authVo = sqlSession.selectOne("user.getUser",userVo);
		return authVo; 
	}
	
	public UserVo getUserInfo(int no) {
		UserVo userVo = sqlSession.selectOne("user.getUserInfo",no);
		return userVo; 
	}
	
	public void modify(UserVo userVo) {
		sqlSession.update("user.modifyUser", userVo);
	}
	
	
}
