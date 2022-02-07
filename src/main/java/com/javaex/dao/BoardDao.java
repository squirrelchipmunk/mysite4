package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;

	public List<BoardVo> getList() {
		List<BoardVo> boardList = sqlSession.selectList("board.getList");
		return boardList;
	}

	public void incHit(int no) {
		sqlSession.update("board.incHit", no);
	}

	public BoardVo getBoard(int no) {
		BoardVo boardVo = sqlSession.selectOne("board.getBoard",no);
		return boardVo;
	}

	public void write(BoardVo boardVo) {
		sqlSession.insert("board.write",boardVo);
	}

	public void delete(int no) {
		sqlSession.delete("board.delete",no);
	}

	public void modify(BoardVo boardVo) {
		sqlSession.update("board.modify",boardVo);
	}

	public List<BoardVo> getList2(int startRnum, int endRnum) {
		//TODO Auto-generated method stub
		System.out.println("getList2");
		System.out.println(startRnum+" "+endRnum);
		Map <String, Integer> map = new HashMap<>();
		map.put("startRnum", startRnum);
		map.put("endRnum", endRnum);
		return sqlSession.selectList("board.getList2", map);
	}

	
	public int selectTotal() {
		return sqlSession.selectOne("board.totalCnt");
	}
	
}	
