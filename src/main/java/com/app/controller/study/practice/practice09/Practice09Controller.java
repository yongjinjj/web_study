package com.app.controller.study.practice.practice09;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Practice09Controller {

	//http://localhost:8080/practice09/listTest?type=member
	// >>> http://localhost:8080/practice07/listTest/member 
	//http://localhost:8080/practice09/listTest?type=str
	// >>> http://localhost:8080/practice07/listTest/str
	
	@GetMapping("/practice09/listTest")
	public String listTest(@RequestParam String type) {
		
		
		//redirect : 사용자 측에서 다시 요청을 수행 (요청 주소가 변경)
		//forward : 서버측에서 바로 특정 경로로 연결 (요청 주소가 유지)
		
		// type : member -> forward
		// type : str -> redirect
		
		if(type.equals("member")) {
			return "forward:/practice07/listTest/member";
		} else { //str
			return "redirect:/practice07/listTest/str";
		}
		
	}
		
		
}
