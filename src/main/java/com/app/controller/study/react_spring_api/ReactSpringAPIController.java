package com.app.controller.study.react_spring_api;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.study.DrinkItem;
import com.app.dto.study.Member;
import com.app.util.LoginManager;


@RestController
public class ReactSpringAPIController {

	//API 통신용 컨트롤러
	
	@GetMapping("/api/getMsg")
	public String getMsg() {
		
		System.out.println("/api/getMsg");
		
		return "spring rest api msg";
	}
	
	@GetMapping("/api/getDrinks")
	public List<DrinkItem> getDrinks(){
		
		List<DrinkItem> drinkList = new ArrayList<DrinkItem>();
		drinkList.add(new DrinkItem("아메리카노", "커피"));
		drinkList.add(new DrinkItem("카페라떼", "커피"));
		drinkList.add(new DrinkItem("레몬차", "차"));
		
		// Service -> DAO -> DB
		
		return drinkList;
	}
	
	@GetMapping("/api/getDrinksDiv")
	public List<DrinkItem> getDrinksDiv(HttpServletRequest request){
		
		String type = request.getParameter("type");
		
		List<DrinkItem> drinkList = new ArrayList<DrinkItem>();
		// 요청 type에 따라서 각각 다른 데이터 조회 
		// where type = '커피'         where type = '차'
		
		if(type != null) {
			
			if(type.equals("커피")) {
				drinkList.add(new DrinkItem("카페모카", "커피"));
				drinkList.add(new DrinkItem("에스프레소", "커피"));
				drinkList.add(new DrinkItem("카푸치노", "커피"));
			}
			
			if(type.equals("차")) {
				drinkList.add(new DrinkItem("허브티", "차"));
				drinkList.add(new DrinkItem("얼그레이티", "차"));
				drinkList.add(new DrinkItem("페퍼민트티", "차"));
			}
		}
		
		return drinkList;
	}
	
	
	// String(num)     { id: ... name:... type:... }
	// @RequestBody String -> 파싱
	// @RequestBody 객체타입
	@PostMapping("/api/getDrinksNum")   
	public List<DrinkItem> getDrinksNum(@RequestBody String num){
		System.out.println(num);
		
		List<DrinkItem> drinkList = new ArrayList<DrinkItem>();
		drinkList.add(new DrinkItem("name"+num, "type"+num));
		
		return drinkList;
	}
	
	
	// 로그인
	@PostMapping("/api/login")
	public String login(@RequestBody Member member, HttpServletRequest request) {
		
		System.out.println(member.getId());
		System.out.println(member.getPw());
		
		// id pw 값 -> DB 사용자 계정정보 비교 
		// -> 서비스 -> DAO ->
		
		// 로그인 성공  return "ok"
		// 로그인 실패  return "no"
		// return apiResponse  (header, body)
		// 1 2 3 4  resultCode...
		
		// 로그인 성공으로 간주 -> ok 
		
		// Session 로그인 여부 처리
		LoginManager.setSessionLoginUserId(request, member.getId());
		
		return "ok";
	}
	
	
	@PostMapping("/api/loginCheck")
	public String loginCheck(HttpServletRequest request) {
		
		if(LoginManager.isLogin(request)) {
			String loginId = LoginManager.getLoginUserId(request);
			System.out.println(loginId);
			return "login user : " + loginId;
		} else {
			return "Not Login";
		}
	}
	
	
}










