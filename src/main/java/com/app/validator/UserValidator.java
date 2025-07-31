package com.app.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.app.dto.user.User;

public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return User.class.isAssignableFrom(clazz);
	}

	@Override				
	public void validate(Object target, Errors errors) {
		
		User user = (User)target;
		
		//유효성 검증 비교
		if(user.getId() == null || user.getId().trim() == "") {
			errors.rejectValue("id", "EmptyUserId", "아이디 입력 안하셨는데?");	
		}
		
		if(user.getId().equals("admin")) {
			errors.rejectValue("id", "InvalidUserId", "이건 아이디로 쓰면 안됨");
		}
		
		if(user.getId().length() < 2 || user.getId().length() > 15) {
			errors.rejectValue("pw", "SizeUserPw", "비번은 8~12 제한이요");
		}
		
	}
	
}
