package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping("/api/guest")
public class ApiGuestbookController {

	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping("/addList") // 생략 표현
	public String addList() {
		System.out.println("ApiGuestbookController.addList()");
		return "aGuestbook/addList";
	}
	
	@ResponseBody
	@RequestMapping("/list")  
	public List<GuestbookVo> list() {
		System.out.println("ApiGuestbookController.list()");
		List<GuestbookVo> guestbookList = guestbookService.getList();
		return guestbookList;
	}
	
	@ResponseBody
	@RequestMapping("/write")
	public GuestbookVo write(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("ApiGuestbookController.write()");
		System.out.println(guestbookVo);
	
		GuestbookVo gVo = guestbookService.addGuestResultVo(guestbookVo); // 값 저장 + 저장된 값 가져오기
		System.out.println(gVo);
		return gVo; // json으로 데이터 보내기
	}
	
	@ResponseBody
	@RequestMapping("/write2")
	public GuestbookVo write2(@RequestBody GuestbookVo guestbookVo) {
		System.out.println("ApiGuestbookController.write2()");
		System.out.println(guestbookVo);
		
		GuestbookVo gVo = guestbookService.addGuestResultVo(guestbookVo); // 값 저장 + 저장된 값 가져오기
		System.out.println(gVo);
		return gVo;
	}
	
	
	@ResponseBody
	@RequestMapping("/remove")
	public String remove(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("ApiGuestbookController.remove()");
		System.out.println(guestbookVo);
		
		String result = guestbookService.removeGuest(guestbookVo);
		// success fail
		
		return result; // json으로 데이터 보내기
	}
	
	
}
