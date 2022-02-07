package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public Map <String, Object> list2(int crtPage) {
		// 리스트 가져오기 
		crtPage = (crtPage> 0)? crtPage : 1;
		
		final int listCnt = 10;
		//-시작글 번호 1 -- > 1     /   6 -- > 51
		int startRnum = (crtPage-1)*listCnt +1;
		//-끝글 번호
		int endRnum = (startRnum + listCnt) -1;
		
		// 페이징버튼
		//-전체 글갯수
		int totalCnt = boardDao.selectTotal();
		System.out.println("totalCnt"+totalCnt);
		
		//-페이지당 버튼 갯수
		final int pageBtnCount = 5;
		
		//마지막 버튼 번호
			//1 1~5	  0.2
			//2 1~5   0.4
			//5 1~5   1.0
		
			//6  6~10 1.2
			//10 6~10 2.0
			//11 11~15 2.2
		int endPageBtnNo = (int)Math.ceil(crtPage/(double)pageBtnCount) * pageBtnCount; 		
		
		//시작 버튼 번호
		int startPageBtnNo = endPageBtnNo-pageBtnCount+1;
		
		//다음 화살표 유무
		boolean next = false;
		if(endPageBtnNo*listCnt <totalCnt) {
			next = true;
		}
		else { // 다음 화살표가 안보이면 마지막 버튼값 다시 계산
			endPageBtnNo = (int)Math.ceil(totalCnt/(double)listCnt);
		}
		
		boolean prev = false;
		if(startPageBtnNo != 1) {
			prev = true;
		}
		
		
		// 맵으로 만들어서 데이터들 보내기
		Map <String, Object> pMap = new HashMap<>();
		pMap.put("prev", prev);
		pMap.put("startPageBtnNo", startPageBtnNo);
		pMap.put("endPageBtnNo", endPageBtnNo);
		pMap.put("next", next);
		pMap.put("boardList", boardDao.getList2(startRnum, endRnum));
		
//		System.out.println("-----------------------");
//		System.out.println(pMap);
//		System.out.println("-----------------------");
		
		return pMap;
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
		int userNo = authVo.getNo();
		boardVo.setUserNo(userNo);
		boardDao.write(boardVo);
	}
	
	public void modify(BoardVo boardVo) {
		boardDao.modify(boardVo);
	}
	
	public void addBoard() {
		System.out.println("boardService/addBoard");
		
		for(int i=1;i<=123;i++) {
			BoardVo boardVo = new BoardVo();
			boardVo.setTitle(i+"번째 글입니다.");
			boardVo.setContent(i+"번째 글내용입니다 ");
			boardVo.setHit(0);
			boardVo.setUserNo(1);
			boardDao.write(boardVo);
		}
	}

	
	
}
