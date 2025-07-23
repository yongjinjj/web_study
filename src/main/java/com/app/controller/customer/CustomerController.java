package com.app.controller.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.dto.user.User;
import com.app.service.user.UserService;

@Controller
public class CustomerController {

	
	//사용자서비스 (계정에 관련된 것을 통합 관리 : 고객서비스/관리자서비스)  
	
	@Autowired
	UserService userService;

	
	@GetMapping("/customer/signup")
	public String singup() {
		return "customer/signup";
	}
	
	
	@PostMapping("/customer/signup")
	public String singupAction(User user) {
		
		//사용자 회원가입 -> 저장 
		int result = userService.saveCustomerUser(user);
		
		if(result > 0) { //성공
			return "redirect:/main";
		} else { //실패
			return "customer/signup";
		}
		
	}
}
