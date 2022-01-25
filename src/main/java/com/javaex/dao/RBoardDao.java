package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.RBoardVo;

@Repository
public class RBoardDao {

	@Autowired
	private SqlSession sqlSession;
	
	public void write(RBoardVo boardVo) {
		sqlSession.insert("rBoard.write",boardVo);
	}

	public List<RBoardVo> getList() {
		return sqlSession.selectList("rBoard.getList");
	}

	public void incHit(int no) {
		sqlSession.update("rBoard.incHit", no);
		
	}

	public RBoardVo getBoard(int no) {
		return sqlSession.selectOne("rBoard.getBoard",no);
	}

	public void delete(int no) {
		sqlSession.delete("rBoard.delete",no);
	}

	public void modify(RBoardVo boardVo) {
		sqlSession.update("rBoard.modify",boardVo);
		
	}

	public void rWrite(RBoardVo boardVo) {
		sqlSession.insert("rBoard.rWrite",boardVo);
	}

	public void updateOrder(int orderNo) {
		sqlSession.update("rBoard.updateOrder", orderNo);
		
	}
}
