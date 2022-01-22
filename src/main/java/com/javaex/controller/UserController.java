package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value = "/loginForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String loginForm() {
		System.out.println("loginForm()");
		return "user/loginForm";
	}
	
	@RequestMapping(value = "/login", method= {RequestMethod.GET, RequestMethod.POST})
	public String login(@ModelAttribute UserVo userVo,
						HttpSession session) {
		System.out.println("login()");
	
		UserVo authVo = userDao.getUser(userVo);
		if(authVo == null) {
			System.out.println("로그인실패");
			return "redirect:loginForm?result=fail";
		}
		else {
			session.setAttribute("authUser",authVo);
			System.out.println("로그인성공");
			return "redirect:/main";
		}
		
	}
	
	@RequestMapping(value = "/joinForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String joinForm() {
		System.out.println("joinForm()");
		return "user/joinForm";
	}
	
	@RequestMapping(value = "/join", method= {RequestMethod.GET, RequestMethod.POST})
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("join()");
		
		userDao.insert(userVo);
		
		return "user/joinOk";
	}
	
	@RequestMapping(value = "/logout", method= {RequestMethod.GET, RequestMethod.POST})
	public String logout(HttpSession session) {
		System.out.println("logout()");
		
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/main";
	}
	
	@RequestMapping(value = "/modifyForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(@SessionAttribute("authUser") UserVo authUser,
							 Model model) {
		System.out.println("modifyForm()");
		UserVo userData = userDao.getUserData(authUser.getNo());
		System.out.println(userData);
		model.addAttribute("userData",userData);
		
		return "user/modifyForm";
	}
	
	@RequestMapping(value = "/modify", method= {RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute UserVo userVo,
						 HttpSession session) {
		userDao.modify(userVo);
		UserVo authVo = userDao.getUser(userVo);
		session.setAttribute("authUser", authVo);
		
		return "redirect:/main";
	}
	
}
