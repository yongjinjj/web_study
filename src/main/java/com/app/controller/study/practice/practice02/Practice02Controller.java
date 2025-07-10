package com.app.controller.study.practice.practice02;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Practice02Controller {
	
	//@GetMapping
	@RequestMapping(value="/practice02/req1", method = RequestMethod.GET)
	public String req1() {
		System.out.println("/practice02/req1 GET 요청");
		
		return "practice/practice02/req";
	}
	
	@PostMapping("/practice02/req2")
//	@RequestMapping(value="", method = RequestMethod.POST)
	public String req2() {
		System.out.println("/practice02/req2 POST 요청");
		
		return "practice/practice02/req";
	}

	@RequestMapping("/practice02/req3")
	public String req3(HttpServletRequest request) {
		System.out.println("/practice02/req3 요청");
		
		//      http://localhost:8080/practice02/req3?data1=122&data2=eodsaksalk
		
		System.out.println( request.getParameter("data1") );
		System.out.println( request.getParameter("data2") );
		
		
		return "practice/practice02/req";
	}
}














