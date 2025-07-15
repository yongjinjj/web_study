package com.app.controller.study.practice.practice08;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.dto.study.practice.practice08.Member;

@Controller
public class Practice08Controller {

	
	@GetMapping("/practice08/member")
	public String member(@RequestParam String auth, Model model) {
		
		
		//필터링 처리가 사전에 된다면, 뒤쪽에 if else 처리
		if( !(auth.equals("basic") || auth.equals("manager") || auth.equals("admin")) ) {
			//auth == "basic" "manager" "admin" 이 세개가 아닌경우
			//파라미터가 잘못된 경우
			return "파라미터가 잘못된 경우 보여줄 페이지";
		}
		
		List<Member> memberList = new ArrayList<Member>();

		memberList.add(new Member("user1", "pass123", "홍길동", "basic"));
		memberList.add(new Member("user2", "pass456", "김철수", "manager"));
		memberList.add(new Member("user3", "pass789", "이영희", "basic"));
		memberList.add(new Member("user4", "passabc", "박민지", "manager"));
		memberList.add(new Member("user5", "passdef", "정재영", "basic"));
		memberList.add(new Member("user6", "passxyz", "최성민", "basic"));
		memberList.add(new Member("user7", "pass123", "서지원", "manager"));
		memberList.add(new Member("user8", "pass456", "장성호", "basic"));
		memberList.add(new Member("user9", "pass789", "신지수", "basic"));
		memberList.add(new Member("user10", "passabc", "한영희", "manager"));
		
		
		
		// 1) 서버에서 화면에 전달할 데이터 분리 -> 전달
		
		List<Member> viewMemberList = new ArrayList<Member>();
		
		//auth == "basic" "manager" "admin"
		
//		if(auth.equals("basic")) {
//			for(Member m : memberList) {
//				if(m.getType().equals("basic")) {
//					viewMemberList.add(m);
//				}
//			}
//		} else if(auth.equals("manager")) {
//			for(Member m : memberList) {
//				if(m.getType().equals("manager")) {
//					viewMemberList.add(m);
//				}
//			}
//		} else { //admin  // member?auth=abc
//			viewMemberList = memberList;
//		}
		
		if( auth.equals("basic") || auth.equals("manager") ) {
			for(Member m : memberList) {
				if(m.getType().equals(auth)) {
					viewMemberList.add(m);
				}
			}
		} else { //admin  // member?auth=abc
			viewMemberList = memberList;
		}
		
		model.addAttribute("viewMemberList", viewMemberList);
		
		
		
		// 2) 화면(view) jstl 구분해서 처리
		
		model.addAttribute("memberList", memberList);
		model.addAttribute("auth", auth);
		
		
		
		return "practice/practice08/member";
		
	}
}
