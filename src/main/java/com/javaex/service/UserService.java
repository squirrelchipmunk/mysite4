package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	public UserVo getAuthUser(UserVo userVo) {
		return userDao.getAuthUser(userVo);
	}
	
	public UserVo getUserInfo(UserVo authVo) {
		 return userDao.getUserInfo(authVo.getNo());
	}
	
	public void join(UserVo userVo) {
		userDao.insert(userVo);
	}
	
	public void modify(UserVo userVo) {
		userDao.modify(userVo);
	}

	public boolean dupCheck(String id) {
		
		int num = userDao.searchId(id);
		if(num >= 1)
			return true;
		else
			return false;
	}
	
	
}
