package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;

@Controller
@RequestMapping("gallery")
public class GalleryController {
	
	@Autowired
	private GalleryService galleryService;
	
	@RequestMapping("list")
	public String list(Model model) {
		System.out.println("gallcon list");
		List<GalleryVo> galleryList = galleryService.getList();
		model.addAttribute("galleryList",galleryList);
		
		return "gallery/list";
	}
	
	@RequestMapping("write")
	public String wrtie(@ModelAttribute GalleryVo galleryVo, @RequestParam("file") MultipartFile file) {
		System.out.println("gallcon write");
		galleryService.write(galleryVo, file);
		return "redirect:list";
	}
	
	@ResponseBody
	@RequestMapping("read")
	public GalleryVo read(@RequestParam("no") int no) {
		System.out.println("gallcon read");
		GalleryVo galleryVo =  galleryService.read(no);
		
		System.out.println(galleryVo);
		return galleryVo;
	}
	
	@ResponseBody
	@RequestMapping("remove")
	public String remove(@RequestParam("no") int no) {
		System.out.println("gallcon read");
		
		String resultMsg = galleryService.remove(no);
		return resultMsg;
	}
	
}
