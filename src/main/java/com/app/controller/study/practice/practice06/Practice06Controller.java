package com.app.controller.study.practice.practice06;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.dto.study.practice.practice06.PersonBmi;

@Controller
public class Practice06Controller {

	/*
	1. /practice06/ask-bmi 경로로 요청시, 화면에는
	이름 / 키 / 몸무게를 입력하는 항목과 확인하기 버튼이 있습니다.
	*버튼 submit 발생
	*/
	@GetMapping("/practice06/ask-bmi")
	public String ask_bmi() {
		
		return "practice/practice06/ask-bmi";
	}
	
	/*
	2. /practice06/result-bmi 경로로 이동하여
	요청에 따라서 컨트롤러가 비만도를 계산해서 결과를 보여줍니다.
	
	화면에는 사용자가 입력한 이름 / 키 / 몸무게가 표시되고,
	추가로 BMI 가 표시됩니다.
	 */
	@PostMapping("/practice06/result-bmi")
	public String result_bmi(HttpServletRequest request) {
		
		//ask-bmi 페이지에서 입력한 값
		System.out.println(request.getParameter("name"));
		System.out.println(request.getParameter("height"));
		System.out.println(request.getParameter("weight"));
		
		String name = request.getParameter("name");
		String height = request.getParameter("height");
		String weight = request.getParameter("weight");
		
		//bmi 계산    체중(kg)/신장(m)^2
		double heightDb = Double.parseDouble(height);  //175 -> 1.75
		double weightDb = Double.parseDouble(weight);
		double bmi = weightDb / ( (heightDb/100)*(heightDb/100));
		
		//결과화면 result-bmi 페이지에서 보여줄 값 세팅
		request.setAttribute("name", name);
		request.setAttribute("height", height);
		request.setAttribute("weight", weight);
		request.setAttribute("bmi", bmi);
		
		return "practice/practice06/result-bmi";
	}
	
	
	@PostMapping("/practice06/result-bmi2")
	public String result_bmi2(@RequestParam String name,
							  @RequestParam String height,
						  	  @RequestParam String weight,
						  	  Model model) {
		
		//ask-bmi 페이지에서 입력한 값
		System.out.println(name);
		System.out.println(height);
		System.out.println(weight);
		
		//bmi 계산    체중(kg)/신장(m)^2
		double heightDb = Double.parseDouble(height);  //175 -> 1.75
		double weightDb = Double.parseDouble(weight);
		double bmi = weightDb / ( (heightDb/100)*(heightDb/100));
		
		//결과화면 result-bmi 페이지에서 보여줄 값 세팅
		model.addAttribute("name", name);
		model.addAttribute("height", height);
		model.addAttribute("weight", weight);
		model.addAttribute("bmi", bmi);
		
		return "practice/practice06/result-bmi";
	}
	
	
	@PostMapping("/practice06/result-bmi3")
	public String result_bmi3(@RequestParam Map<String,String> paramMap, Model model) {
		
		//ask-bmi 페이지에서 입력한 값
		System.out.println(paramMap.get("name"));
		System.out.println(paramMap.get("height"));
		System.out.println(paramMap.get("weight"));
		
		String name = paramMap.get("name");
		String height = paramMap.get("height");
		String weight = paramMap.get("weight");
		
		//bmi 계산    체중(kg)/신장(m)^2
		double bmi = calculateBmi(height, weight);
		
		//결과화면 result-bmi 페이지에서 보여줄 값 세팅
		model.addAttribute("name", name);
		model.addAttribute("height", height);
		model.addAttribute("weight", weight);
		model.addAttribute("bmi", bmi);
		
		return "practice/practice06/result-bmi";
	}
	
	//@PostMapping("/practice06/result-bmi4")
	@RequestMapping("/practice06/result-bmi4")
	public String result_bmi4(@ModelAttribute PersonBmi personBmi, Model model) {
		
		//ask-bmi 페이지에서 입력한 값
		System.out.println(personBmi.getName());
		System.out.println(personBmi.getHeight());
		System.out.println(personBmi.getWeight());
		
		String name = personBmi.getName();
		String height = personBmi.getHeight();
		String weight = personBmi.getWeight();
		
		//bmi 계산    체중(kg)/신장(m)^2
		double bmi = calculateBmi(height, weight);
		
		personBmi.setBmi(bmi);
		
		//결과화면 result-bmi 페이지에서 보여줄 값 세팅
		/*
		model.addAttribute("name", name);
		model.addAttribute("height", height);
		model.addAttribute("weight", weight);
		model.addAttribute("bmi", bmi);
		*/
		model.addAttribute("personBmi", personBmi);
		
		return "practice/practice06/result-bmi";
	}
	
	
	
	
	//bmi 계산하는 메소드
	public double calculateBmi(double height, double weight) {
		double bmi = weight / Math.pow((height/100), 2);
		return bmi;
	}
	
	public double calculateBmi(String height, String weight) {
		double heightDb = Double.parseDouble(height); 
		double weightDb = Double.parseDouble(weight);
		return calculateBmi(heightDb, weightDb);
	}
	
}






















