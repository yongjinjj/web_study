package com.app.dto.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class User {
	
	//필수입력 빈칸X
	//@NotBlank(message = "이거 아이디 필순데?")
	String id;
	
	//입력 길이 제한
	//@Size(min = 8, max = 12, message = "비번 길이 확인하라고")
	String pw;
	String name;
	String userType; // CUS / ADM
					 // 사용자 계정 구분
					 // Customer / Admin
}
