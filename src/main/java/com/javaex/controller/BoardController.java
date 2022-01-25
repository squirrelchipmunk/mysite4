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

import com.javaex.service.BoardService;
import com.javaex.service.RBoardService;
import com.javaex.vo.BoardVo;
import com.javaex.vo.RBoardVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {

	/*  일 반   게 시 판  */
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value = {"","/", "/list"}, method= {RequestMethod.GET,RequestMethod.POST})
	public String list(Model model) {
		List<BoardVo> boardList = boardService.list();
		model.addAttribute("bList", boardList);
		
		return "board/list";
	}
	
	@RequestMapping(value = "/read", method= {RequestMethod.GET,RequestMethod.POST})
	public String read(@RequestParam int no,
					   Model model) {
		BoardVo boardVo = boardService.read(no);
		
		model.addAttribute("board", boardVo);
		return "board/read";
	}
	
	@RequestMapping(value = "/delete", method= {RequestMethod.GET,RequestMethod.POST})
	public String delete(@RequestParam int no) {
		boardService.delete(no);
		
		return "redirect:/board/";
	}
	
	
	@RequestMapping(value = "/writeForm", method= {RequestMethod.GET,RequestMethod.POST})
	public String writeForm(@SessionAttribute(value="authUser", required=false) UserVo authVo) {
		
		if(authVo != null) {
			return "board/writeForm";
		}
		else { // 잘못된 접근 처리
			return "redirect:/main";
		}
	}
	
	@RequestMapping(value = "/write", method= {RequestMethod.GET,RequestMethod.POST})
	public String write(@SessionAttribute(value="authUser", required=false) UserVo authVo,
						@ModelAttribute BoardVo boardVo) {
		
		boardService.write(authVo, boardVo);
		return "redirect:/board/";
	}
	
	@RequestMapping(value = "/modifyForm", method= {RequestMethod.GET,RequestMethod.POST})
	public String modifyForm(@RequestParam int no,
							 Model model) {
		BoardVo boardVo = boardService.getBoard(no);
		
		model.addAttribute("board", boardVo);
		return "board/modifyForm";
	}
	
	@RequestMapping(value = "/modify", method= {RequestMethod.GET,RequestMethod.POST})
	public String modify(@ModelAttribute BoardVo boardVo) {
		boardService.modify(boardVo);
		
		return "redirect:/board/";
	}
	
	
	
	
	/*   댓 글    게 시 판    */
	@Autowired
	private RBoardService rBoardService;
	
	@RequestMapping(value = "/rList", method= {RequestMethod.GET,RequestMethod.POST})
	public String rList(Model model) {
		
		List<RBoardVo> boardList = rBoardService.list();
		model.addAttribute("bList", boardList);
		
		return "board/replyList";
	}
	
	@RequestMapping(value = "/rRead", method= {RequestMethod.GET,RequestMethod.POST})
	public String rRead(@RequestParam int no,
			   			Model model) {
		RBoardVo boardVo = rBoardService.read(no);
		model.addAttribute("board", boardVo);
		
		return "board/replyRead";
	}
	
	@RequestMapping(value = "/rWriteForm", method= {RequestMethod.GET,RequestMethod.POST})
	public String rWriteForm(Model model) {
		
		return "board/replyWriteForm";
	}
	
	@RequestMapping(value = "/rWrite", method= {RequestMethod.GET,RequestMethod.POST})
	public String rWrite(@SessionAttribute(value="authUser", required=false) UserVo authVo,
							@ModelAttribute RBoardVo boardVo) {
		rBoardService.write(authVo, boardVo);
		return "redirect:/board/rList";
	}
	
	@RequestMapping(value = "/rModifyForm", method= {RequestMethod.GET,RequestMethod.POST})
	public String rModifyForm(@RequestParam int no,
							  Model model) {
		RBoardVo boardVo = rBoardService.getBoard(no);
		
		model.addAttribute("board", boardVo);
		return "board/replyModifyForm";
	}
	
	@RequestMapping(value = "/rModify", method= {RequestMethod.GET,RequestMethod.POST})
	public String rModify(@ModelAttribute RBoardVo boardVo) {
		rBoardService.modify(boardVo);
		return "redirect:/board/rList";
	}
	
	@RequestMapping(value = "/rDelete", method= {RequestMethod.GET,RequestMethod.POST})
	public String rDelete(@RequestParam int no) {
		rBoardService.delete(no);
		
		return "redirect:/board/rList";
	}
}
