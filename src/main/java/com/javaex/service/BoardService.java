package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;
	
	public List<BoardVo> list() {
		return boardDao.getList();
	}

	public BoardVo getBoard(int no) {
		return boardDao.getBoard(no);
	}
	
	public BoardVo read(int no) {
		boardDao.incHit(no);
		BoardVo boardVo = boardDao.getBoard(no);
		boardVo.setTitle  ( boardVo.getTitle().replace(" ", "&nbsp;") );
		boardVo.setContent( boardVo.getContent().replace(" ", "&nbsp;").replace("\n", "<br>") );
		return boardVo;
	}
	public void delete(int no) {
		boardDao.delete(no);
	}
	
	public void write(UserVo authVo, BoardVo boardVo) {
		System.out.println(authVo);
		System.out.println(boardVo);
		int userNo = authVo.getNo();
		boardVo.setUserNo(userNo);
		boardDao.write(boardVo);
	}
	
	public void modify(BoardVo boardVo) {
		boardDao.modify(boardVo);
	}
	
	
}
