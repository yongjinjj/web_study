package com.app.validator;

import com.app.dto.user.User;
import com.app.dto.user.UserValidError;

public class UserCustomValidator {
	
	//public static boolean validateAdminUsersAdd(User user, UserValidError userValidError) {
	
	public static boolean validate(User user, UserValidError userValidError) {
		
		boolean result = true;
		
		//유효성 검증 비교
		if(user.getId() == null || user.getId().trim() == "") {
			userValidError.setId("아이디 입력 안하셨는데?");
			result = false;	
		}
		
		if(user.getId().equals("admin")) {
			userValidError.setId("이건 아이디로 쓰면 안됨");
			result = false;	
		}
		
		if(user.getPw().length() < 8 || user.getPw().length() > 12) {
			userValidError.setPw("비번은 8~12 제한이요");
			result = false;	
		}
		
		return result;
	}
}
