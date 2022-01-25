package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.RBoardDao;
import com.javaex.vo.RBoardVo;
import com.javaex.vo.UserVo;

@Service
public class RBoardService {

	@Autowired
	private RBoardDao rBoardDao;

	public void write(UserVo authVo, RBoardVo boardVo) {
		int userNo = authVo.getNo();
		boardVo.setUserNo(userNo);
		
		System.out.println(boardVo);
		if(boardVo.getGroupNo() == 0) {	// 글쓰기
			rBoardDao.write(boardVo);
		}
		else { // 댓글쓰기
			rBoardDao.updateOrder(boardVo.getOrderNo()+1);	 // 공간 만들기
			boardVo.setOrderNo( boardVo.getOrderNo()+1 );	 // 위치 정하기
			boardVo.setDepth( boardVo.getDepth()+1 );		 // 깊이 정하기
			rBoardDao.rWrite(boardVo);
		}
	}

	public List<RBoardVo> list() {
		List<RBoardVo> list = rBoardDao.getList();
		for(RBoardVo vo : list) {
			for(int i=0; i<vo.getDepth() ;i++) {
				vo.setTitle( "&nbsp;&nbsp;&nbsp;&nbsp;"+ vo.getTitle());
			}
		}
		return list;
	}

	public RBoardVo read(int no) {
		rBoardDao.incHit(no);
		RBoardVo boardVo = rBoardDao.getBoard(no);
		boardVo.setTitle  ( boardVo.getTitle().replace(" ", "&nbsp;") );
		boardVo.setContent( boardVo.getContent().replace(" ", "&nbsp;").replace("\n", "<br>") );
		return boardVo;
	}

	public void delete(int no) {
		rBoardDao.delete(no);
		
	}

	public RBoardVo getBoard(int no) {
		return rBoardDao.getBoard(no);
	}

	public void modify(RBoardVo boardVo) {
		rBoardDao.modify(boardVo);
		
	}
	
	
	
}
