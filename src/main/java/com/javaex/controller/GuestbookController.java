package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestBookVo;

@Controller
@RequestMapping("/guest")
public class GuestbookController {

	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping(value={"","/","/addList"}, method= {RequestMethod.GET, RequestMethod.POST})
	public String addList(Model model){
		List<GuestBookVo> guestBookList = guestbookService.getList();
		
		model.addAttribute("gList",guestBookList);
		return "guestbook/addList";
	}
	
	@RequestMapping(value="/deleteForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String deleteForm(){
		return "guestbook/deleteForm";
	}
	
	@RequestMapping(value="/add", method= {RequestMethod.GET, RequestMethod.POST})
	public String add(@ModelAttribute GuestBookVo guestBookVo){
		guestbookService.add(guestBookVo);
		return "redirect:addList";
	}
	
	@RequestMapping(value="/delete", method= {RequestMethod.GET, RequestMethod.POST})
	public String delete(@ModelAttribute GuestBookVo guestBookVo){
		guestbookService.delete(guestBookVo);
		return "redirect:addList";
	}
	
}
