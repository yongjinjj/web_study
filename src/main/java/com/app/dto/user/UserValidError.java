package com.app.dto.user;

import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserValidError {
	
	//유효성 검증 결과 저장용
	//값 X(null) 통과
	//값 O : 검증통과실패, 에러 메시지
	
	String id;
	String pw;
	String name;
	String userType; 
}
