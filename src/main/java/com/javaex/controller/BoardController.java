package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;


@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardDao boardDao;
	
	@RequestMapping(value = {"", "/list"}, method= {RequestMethod.GET,RequestMethod.POST})
	public String list(Model model) {
		List<BoardVo> boardList = boardDao.getBoardList();
		model.addAttribute("bList", boardList);
		
		return "board/list";
	}
	
	@RequestMapping(value = {"/read"}, method= {RequestMethod.GET,RequestMethod.POST})
	public String read(@RequestParam int no,
					   Model model) {
		boardDao.incHit(no);
		BoardVo boardVo = boardDao.getBoard(no);
		model.addAttribute("board", boardVo);
		
		return "board/read";
	}
	
	@RequestMapping(value = {"/delete"}, method= {RequestMethod.GET,RequestMethod.POST})
	public String delete(@RequestParam int no) {
		boardDao.delete(no);
		
		return "redirect:/board";
	}
	
	
	@RequestMapping(value = {"/writeForm"}, method= {RequestMethod.GET,RequestMethod.POST})
	public String writeForm(@SessionAttribute(value="authUser", required=false) UserVo authUser) {
		
		if(authUser != null) {
			return "board/writeForm";
		}
		else { // 잘못된 접근 처리
			return "redirect:/main";
		}
	}
	
	@RequestMapping(value = {"/write"}, method= {RequestMethod.GET,RequestMethod.POST})
	public String write(@SessionAttribute(value="authUser", required=false) UserVo authUser,
						@ModelAttribute BoardVo boardVo) {
		int userNo = authUser.getNo();
		boardVo.setUserNo(userNo);
		boardDao.write(boardVo);
		
		return "redirect:/board";
	}
	
	@RequestMapping(value = {"/modifyForm"}, method= {RequestMethod.GET,RequestMethod.POST})
	public String modifyForm(@RequestParam int no,
							 Model model) {
		BoardVo boardVo = boardDao.getBoard(no);
		model.addAttribute("board", boardVo);
		
		return "board/modifyForm";
	}
	
	@RequestMapping(value = {"/modify"}, method= {RequestMethod.GET,RequestMethod.POST})
	public String modify(@ModelAttribute BoardVo boardVo) {
		boardDao.modify(boardVo);
		
		return "redirect:/board";
	}
	
	
	
}
