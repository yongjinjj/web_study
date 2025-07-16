package com.app.controller.study.practice.practice11;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Practice11Controller {

	@GetMapping("/practice11/first")
	public String first(Model model) {
		
		model.addAttribute("accessUrl", "/first");
		
		return "practice/practice11/targetPage";
	}
	
	@GetMapping("/practice11/firsthide1")
	public String firsthide1(Model model) {
		
		model.addAttribute("accessUrl", "/firsthide1");
		
		return "practice/practice11/targetPage";
	}
	
	@GetMapping("/practice11/firsthide2")
	public String firsthide2(Model model) {
		
		//model.addAttribute("accessUrl", "/first");
		
		return "redirect:/practice11/first";
		//return "practice/practice11/targetPage";
	}
	
}
