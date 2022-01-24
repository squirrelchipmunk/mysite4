package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/loginForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String loginForm() {
		System.out.println("loginForm()");
		return "user/loginForm";
	}
	
	@RequestMapping(value = "/login", method= {RequestMethod.GET, RequestMethod.POST})
	public String login(@ModelAttribute UserVo userVo,
						HttpSession session) {
		System.out.println("login()");
		UserVo authVo = userService.getAuthUser(userVo);
		
		if(authVo == null) {
			System.out.println("로그인실패");
			return "redirect:loginForm?result=fail";
		}
		else {
			System.out.println("로그인성공");
			session.setAttribute("authUser",authVo);
			return "redirect:/main";
		}
		
	}
	
	@RequestMapping(value = "/logout", method= {RequestMethod.GET, RequestMethod.POST})
	public String logout(HttpSession session) {
		System.out.println("logout()");
		
		session.removeAttribute("authUser");
		session.invalidate();
		return "redirect:/main";
	}
	
	@RequestMapping(value = "/joinForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String joinForm() {
		System.out.println("joinForm()");
		
		return "user/joinForm";
	}
	
	@RequestMapping(value = "/join", method= {RequestMethod.GET, RequestMethod.POST})
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("join()");
		
		userService.join(userVo);
		return "user/joinOk";
	}
	
	@RequestMapping(value = "/modifyForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(@SessionAttribute("authUser") UserVo authVo,
							 Model model) {
		System.out.println("modifyForm()");
		
		UserVo userInfo = userService.getUserInfo(authVo);
		model.addAttribute("userInfo",userInfo);
		
		return "user/modifyForm";
	}
	
	@RequestMapping(value = "/modify", method= {RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute UserVo userVo,
						 HttpSession session) {
		System.out.println("modify()");
		
		userService.modify(userVo);
		UserVo authVo = userService.getAuthUser(userVo);
		session.setAttribute("authUser", authVo);
		
		return "redirect:/main";
	}
	
}
