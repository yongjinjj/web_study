package com.app.controller.study.practice.practice10;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Practice10Controller {
	
	@GetMapping("/practice10/A")
	public String a(Model model) {
		
		model.addAttribute("fromA", "fromA");
		// ...
		
		return "practice/practice10/a";
	}
	
	@GetMapping("/practice10/B")
	public String b(HttpSession session) {
	//public String b(HttpServletRequest request) {
		//HttpSession session = request.getSession();
		
		// request / session 			/ application
		
		//request : 각 요청별 독립
		//session : 브라우저(사용자) 별로 구분 사용
		
		session.setAttribute("fromBMsg", "fromB");
		
		
		return "practice/practice10/b";
	}
}
