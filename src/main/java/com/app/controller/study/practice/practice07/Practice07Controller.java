package com.app.controller.study.practice.practice07;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.dto.study.practice.practice07.Member;

@Controller
public class Practice07Controller {

	
	//1. localhost:8080/practice07/listTest    ?type=str 경로로 접근시
			
	//2. localhost:8080/practice07/listTest    ?type=member 경로로 접근시
	
	@GetMapping("/practice07/listTest")
	public String listTest(@RequestParam String type, Model model) {
		
		
		// str 반복 케이스
		//1) 그냥 jsp 내에서 고정 텍스트 반복
		
		//2) 전달할 스트링(메시지) 전달하고 그걸 반복
		
		//3) 전달할 메시지를 리스트 형태로 전달 반복
		
		
		
		// 화면 처리 케이스
		// 1) type으로 아예 화면 자체를 구분
		
		/*
		//type : str 혹은 member 둘중에 하나로 확정 전제
		if(type.equals("str")) {
			
			model.addAttribute("msg", "스트링 리스트입니다.");
			
			List<String> msgList = new ArrayList<String>();
			for(int i=1; i<=10; i++) {
				msgList.add("스트링 리스트입니다.");
			}
			
			model.addAttribute("msgList", msgList);
			
			return "practice/practice07/str";
			
		} else {  //member
			
			List<Member> memberList = new ArrayList<Member>();
			
			for(int i=1; i<=5; i++) {
				memberList.add( new Member("아이디"+i, "비번"+i, "이름"+i) );
			}
			
			
//			memberList.add( new Member("아이디1", "비번1", "이름1") );
//			memberList.add( new Member("아이디2", "비번2", "이름2") );
//			memberList.add( new Member("아이디3", "비번3", "이름3") );
//			memberList.add( new Member("아이디4", "비번4", "이름4") );
//			memberList.add( new Member("아이디5", "비번5", "이름5") );
			
			
			model.addAttribute("memberList", memberList);
			
			
			return "practice/practice07/member";
			
		}
		*/
		
		// 2) 같은 화면에서 type에 따라 동적으로 구분
		
		model.addAttribute("type", type);
		
		//각 type 파라미터값에 맞춰서 필요한 연산작업만 수행
		if(type.equals("str")) {
			model.addAttribute("msg", "스트링 리스트입니다.");
		} else {
			List<Member> memberList = new ArrayList<Member>();
			
			for(int i=1; i<=5; i++) {
				memberList.add( new Member("아이디"+i, "비번"+i, "이름"+i) );
			}
			
			model.addAttribute("memberList", memberList);	
		}
		
		return "practice/practice07/listTest";
		
		
	}
}






