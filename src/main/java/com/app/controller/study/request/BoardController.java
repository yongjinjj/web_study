package com.app.controller.study.request;

import com.app.controller.HomeController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {

    private final HomeController homeController;

    BoardController(HomeController homeController) {
        this.homeController = homeController;
    }

	@RequestMapping("/faq")  //   /board/faq
	public String faq() {
		System.out.println("/board/faq 접속 확인됨");
		return "board/faq";
	}
	
	@GetMapping("/notice")   //   /board/notice
	public String notice() {
		System.out.println("/board/notice 담당 메소드 실행됨");
		return "board/notice";
	}
}
