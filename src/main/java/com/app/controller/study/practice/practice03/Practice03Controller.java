package com.app.controller.study.practice.practice03;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.dto.study.practice.practice03.Beverage;
import com.app.dto.study.practice.practice03.DrinkItem;

@Controller
@RequestMapping("/practice03")
public class Practice03Controller {

	
	/*
	1. /practice03/request1-x?item=americano&type=coffee 요청 시,
	값들 출력 케이스	Console창에 println 으로 출력 (사용자 요청을 서버가 읽어서 확인 가능한지)
	*/
	
	//1) request 활용 (/request1-1)
	//  /practice03/request1-1?item=americano&type=coffee
	@GetMapping("/request1-1")
	public String request1_1(HttpServletRequest request){
		
		System.out.println("/practice03/request1-1");
		System.out.println(request.getParameter("item"));
		System.out.println(request.getParameter("type"));
		
		return "practice/practice03/req";
	}
	
	//2) RequestParam 활용 (/request1-2)
	//  /practice03/request1-2?item=americano&type=coffee
	@GetMapping("/request1-2")
	public String request1_2(@RequestParam(required = false) String item,
							 @RequestParam(required = false) String type ) {
		System.out.println("/practice03/request1-2");
		System.out.println(item);
		System.out.println(type);
		
		return "practice/practice03/req";
	}

	//3) RequestParam Map 활용 (/request1-3)
	//  /practice03/request1-3?item=americano&type=coffee
	@GetMapping("/request1-3")
	public String request1_3(@RequestParam Map<String,String> paramMap) {
		System.out.println("/practice03/request1-3");
		System.out.println(paramMap.get("item"));
		System.out.println(paramMap.get("type"));
		
		return "practice/practice03/req";
	}

	//4) 자바 Dto 객체 활용 (/request1-4)
	//  /practice03/request1-4?item=americano&type=coffee
	@GetMapping("/request1-4")
	public String request1_4(@ModelAttribute Beverage beverage) {
		System.out.println("/practice03/request1-4");
		System.out.println(beverage);
		
		return "practice/practice03/req";
	}
	
	
	
	/*2. /practice03/viewData1-x 요청시 화면에 값 출력*/

	//1) request 활용 /viewData1-1
	@GetMapping("/viewData1-1")
	public String viewData1_1(HttpServletRequest request) {
		System.out.println("/practice03/viewData1-1");
		request.setAttribute("name", "싸이버거");
		request.setAttribute("type", "햄버거");
		
		return "practice/practice03/viewData";
	}
	
	//2) Model 활용 /viewData1-2
	@GetMapping("/viewData1-2")
	public String viewData1_2(Model model) {
		System.out.println("/practice03/viewData1-2");
		model.addAttribute("name", "후라이드치킨");
		model.addAttribute("type", "치킨");
		
		return "practice/practice03/viewData";
	}
	
	//3) ModelAndView 활용 /viewData1-3
	@GetMapping("/viewData1-3")
	public ModelAndView viewData1_3() {
		System.out.println("/practice03/viewData1-3");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("practice/practice03/viewData");
		mav.addObject("name", "순대국밥");
		mav.addObject("type", "국밥");
		
		return mav;
	}
	
	//4) Model에 객체 단위로 전달 활용 /viewData1-4
	@GetMapping("/viewData1-4")
	public String viewData1_4(Model model) {
		System.out.println("/practice03/viewData1-4");
		
		//model.addAttribute("name", "후라이드치킨");
		//model.addAttribute("type", "치킨");
		
		
		DrinkItem drinkItem = new DrinkItem();
		drinkItem.setName("콜라");
		drinkItem.setType("탄산음료");
		
		model.addAttribute("drinkItem", drinkItem);
		
		return "practice/practice03/viewData";
	}
}













