package com.app.controller.study.practice.practice04;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.dto.study.practice.practice04.Prac04RequestForm;
import com.app.dto.study.practice.practice04.TransferItem;

@Controller
public class Practice04Controller {


	/*
	1. /practice04/request1-x?category=100&product=4000 요청 시, 값들 출력 케이스
	Console창에 println 으로 출력 (사용자 요청을 서버가 읽어서 확인 가능한지)
	*/
	
	//1) request 활용 (/request1-1)
	@GetMapping("/practice04/request1-1")
	public String request1_1(HttpServletRequest request) {
		System.out.println(request.getParameter("category"));
		System.out.println(request.getParameter("product"));
		
		return "practice/practice04/req";
	}
	
	//2) RequestParam 활용 (/request1-2)
	@GetMapping("/practice04/request1-2")
	public String request1_2(@RequestParam String category,
							 @RequestParam String product) {
		System.out.println("/practice04/request1-2");
		System.out.println(category);
		System.out.println(product);
		
		return "practice/practice04/req";
	}
	
	//3) Dto 객체 활용 (/request1-3)
	@GetMapping("/practice04/request1-3")
	public String request1_3(@ModelAttribute Prac04RequestForm prac04RequestForm ) {
		System.out.println("/practice04/request1-3");
		System.out.println(prac04RequestForm.getCategory());
		System.out.println(prac04RequestForm.getProduct());
		
		return "practice/practice04/req";
	}
	
	//4) Map 활용 (/request1-4)
	@GetMapping("/practice04/request1-4")
	public String request1_4(@RequestParam Map<String, String> paramMap) {
		System.out.println("/practice04/request1-4");
		System.out.println(paramMap.get("category"));
		System.out.println(paramMap.get("product"));
		
		return "practice/practice04/req";
	}
	
	/*
	2. /practice04/viewData1-x요청시 화면(view .jsp)에 값 출력
	* 화면 전달된 값은 아래와 같이 작성시 출력되어야 함
	*전달되는 값은 임의로 작성 "넘어간 값"
	*/
	
	
	//1) request 활용  ( /viewData1-1)
	@GetMapping("/practice04/viewData1-1")
	public String viewData1_1(HttpServletRequest request) {
		
		request.setAttribute("response001", "적당히~");
		request.setAttribute("response099", "화면에~");
		
		return "practice/practice04/viewData";
	}
	
	//2) Model 활용    /viewData1-2
	@GetMapping("/practice04/viewData1-2")
	public String viewData1_2(Model model) {
		
		model.addAttribute("response001", "적당히~1-2");
		model.addAttribute("response099", "화면에~1-2");
		
		return "practice/practice04/viewData";
	}
	
	//3) ModelAndView 활용   /viewData1-3
	@GetMapping("/practice04/viewData1-3")
	public ModelAndView viewData1_3() {
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("practice/practice04/viewData");
		mav.addObject("response001", "적당히~1-3");
		mav.addObject("response099", "화면에~1-3");
		
		return mav;
	}
	//4) ModelAttribute 객체 활용    /viewData1-4
	@GetMapping("/practice04/viewData1-4")
	public String viewData1_4(Model model) {
		
		model.addAttribute("response001", "적당히~1-4");
		model.addAttribute("response099", "화면에~1-4");
		
		TransferItem transferItem = new TransferItem();
		transferItem.setValue001("v011");
		transferItem.setValue099("vvv999");
		model.addAttribute("transferItem", transferItem);
		
		return "practice/practice04/viewData";
	}
	
}
















