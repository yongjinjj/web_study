package com.app.controller.customer;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.app.common.ApiCommonCode;
import com.app.common.CommonCode;
import com.app.dto.api.ApiResponse;
import com.app.dto.api.ApiResponseHeader;
import com.app.dto.file.FileInfo;
import com.app.dto.user.User;
import com.app.dto.user.UserDupCheck;
import com.app.dto.user.UserProfileImage;
import com.app.dto.user.UserProfileRequestForm;
import com.app.dto.user.UserValidError;
import com.app.service.file.FileService;
import com.app.service.user.UserService;
import com.app.util.FileManager;
import com.app.util.LoginManager;
import com.app.validator.UserCustomValidator;
import com.app.validator.UserValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CustomerController {

	
	//사용자서비스 (계정에 관련된 것을 통합 관리 : 고객서비스/관리자서비스)  
	
	@Autowired
	UserService userService;
	
	@Autowired
	FileService fileService;

	
	@GetMapping("/customer/signup")
	public String singup() {
		return "customer/signup";
	}
	
	
	@PostMapping("/customer/signup")
	public String singupAction(/*@Valid*/ @ModelAttribute User user, BindingResult br, Model model) {
								//유효성검사			검사 결과
		
		//검증 결과에 문제가 있느냐 없느냐
		
//		if(br.hasErrors()) {
//			//문제 내용을 출력
//			List<ObjectError> errorList = br.getAllErrors();
//			for(ObjectError er : errorList) {
//				System.out.println(er.getObjectName());
//				System.out.println(er.getDefaultMessage());
//				System.out.println(er.getCode());
//				System.out.println(er.getCodes()[0]);
//			}
//			
//			//model.addAttribute("user", user);
//			
//			return "customer/signup";
//		}
		
		//custom validator
		UserValidError userValidError = new UserValidError();
		if(UserCustomValidator.validate(user, userValidError) == false) {
			//유효성 검증을 해봤는데, 뭔가 필드에 문제가 있다
			
			model.addAttribute("userValidError" ,userValidError);
			
			return "customer/signup";
		}
		
		
		//사용자 회원가입 -> 저장 
		int result = userService.saveCustomerUser(user);
		
		if(result > 0) { //성공
			return "redirect:/main";
		} else { //실패
			return "customer/signup";
		}
	}
	
	
//	@InitBinder("user")
//	public void initUserBinder(WebDataBinder binder) {
//		UserValidator userValidator = new UserValidator();
//		binder.setValidator(userValidator);
//	}
	
	@ResponseBody
	@PostMapping("/customer/checkDupId")
	public String checkDupId(@RequestBody String data) {  //단순 텍스트
		System.out.println("/customer/checkDupId");
		System.out.println(data);
		
		//처리 로직
		//클라이언트에서 보낸 데이터가 중복된 사용자 아이디인지 체크
		boolean result = userService.isDuplicatedId(data);
		if(result) { //중복O
			return "Y"; //1
		} else { //중복X
			return "N"; //2
		}
		
		//return "/customer/checkDupId";
		//return "kkkkkkkkkkkIdCheck";
	}
	
	// JSON 포맷으로 통신
	@ResponseBody
	@PostMapping("/customer/checkDupIdJson")
	//public String checkDupIdJson(@RequestBody String data) { 
	public ApiResponse<String> checkDupIdJson(@RequestBody UserDupCheck userDupCheck) {
								 //JSON format text 가 담겨져서 오면 동일한 key값:필드변수
								//			자동으로 객체형태로 파싱되서 데이터가 담겨진다 
		System.out.println("/customer/checkDupIdJson");
		System.out.println(userDupCheck);
		
		log.info("checkDupIdJson 아이디 중복체크 : {}", userDupCheck);
		
		//처리 로직
		//클라이언트에서 보낸 데이터가 중복된 사용자 아이디인지 체크
		boolean result = userService.isDuplicatedId(userDupCheck.getId());
		
		
		ApiResponse<String> apiResponse = new ApiResponse<String>();
		
		//header
		ApiResponseHeader header = new ApiResponseHeader();
		header.setResultCode(ApiCommonCode.API_RESULT_SUCCESS);
		header.setResultMessage(ApiCommonCode.API_RESULT_SUCCESS_MSG);
		
		apiResponse.setHeader(header);
		
		//body
		if(result) { //중복O
			apiResponse.setBody("Y");
		} else { //중복X
			apiResponse.setBody("N");
		}
		
		return apiResponse;  //객체 return -> JSON format 변환 
		//return "/customer/checkDupId";
		//return "kkkkkkkkkkkIdCheck";
	}
	
	
	
	//@GetMapping("/customer/login")
	@GetMapping("/customer/signin")
	public String signin() {
		return "customer/signin";
	}
	
	@PostMapping("/customer/signin")
	public String signinAction(User user, HttpSession session) {
		
		System.out.println("사용자가 입력한 아이디 비번 ");
		System.out.println(user);  // id pw
		
		//사용자가 입력한 id, pw 맞냐! DB에 존재하냐
		//서비스에서 비교시, userType 까지 비교
		user.setUserType( CommonCode.USER_USERTYPE_CUSTOMER );
		User loginUser = userService.checkUserLogin(user);
		
		if(loginUser == null) {  //로그인 실패 ( 뭐라도 틀린게 있다 )
			System.out.println("로그인 실패");
			
			return "customer/signin";
		} else {  //아이디 맞고 비번 맞고 userType도 일치하고 -> 로그인 성공
			System.out.println("로그인 성공");
			
			//현재 로그인 성공한 사용자 아이디 -> session 영역에 저장
			//session.setAttribute("loginUserId", loginUser.getId());
			LoginManager.setSessionLoginUserId(session, loginUser.getId());
			
			//return "redirect:/customer/mypage"; //로그인 성공한 후, 마이페이지로 이동
			return "redirect:/main";  //로그인 성공한 후, 메인 페이지로
		}
		
		
	}
	
	@GetMapping("/customer/mypage")
	public String mypage(Model model, HttpSession session) {
		
		//로그인을 했으면, 로그인 한 사용자의 정보를 보여주는 마이페이지
		
		//if(session.getAttribute("loginUserId") != null) {  //로그인이 된 상태
		if( LoginManager.isLogin(session) ) {

			//String loginUserId = (String)session.getAttribute("loginUserId");
			String loginUserId = LoginManager.getLoginUserId(session);
			
			//로그인 된 사용자 ID (세션에 저장되어있음)
			User user = userService.findUserById(loginUserId);
			//그 ID에 해당하는 사용자 정보 -> view 전달
			model.addAttribute("user", user);
			
			
			//현재 mypage 접속한 사용자가 프로필 사진이 있으면?
			//프로필 파일 정보도 같이 view 에 전달
			
			//userService.findUserProfileImageById(loginUserId);
			UserProfileImage userProfileImage = userService.findUserProfileImageById(user.getId());
			if(userProfileImage != null) {  //이미지 등록 되어 있다!
				
				FileInfo fileInfo = fileService.findFileInfoByFileName( userProfileImage.getFileName() );
				model.addAttribute("fileInfo", fileInfo);
			}
			
			
			return "customer/mypage";
		}

		//그게 아니면? 로그인이 안된 상태 -> 로그인 페이지로 이동
		return "redirect:/customer/signin";
	}
	
	@GetMapping("/customer/logout")
	public String logout(HttpSession session) {
		
		//세션 초기화
		//session.invalidate();
		LoginManager.logout(session);
		
		return "redirect:/main";
	}
	
	
	
	@GetMapping("/customer/modifyPw")
	public String modifyPw() {
		return "customer/modifyPw";
	}
	
	@PostMapping("/customer/modifyPw")
	public String modifyPwAction(User user, HttpSession session) {

		//세션 로그인 사용자 ID
		//화면에서 전달된 변경할 pw -> User 
		
		// 비밀번호 변경 처리 (user)   Service -> DAO -> mapper sql 
		// set pw = ?
		// where id = ?
		
		// User 전체 update -> 기존정보는 유지 + 변경할 비밀번호
		
		// 특정 id의 비밀번호만 update
		//user.setId((String)session.getAttribute("loginUserId"));
		user.setId( LoginManager.getLoginUserId(session) );
		
		System.out.println("비밀번호 변경에 사용할 user 객체");
		System.out.println(user);
		
		int result = userService.modifyUserPw(user);
		
		if(result > 0) {  //성공
			return "redirect:/customer/mypage";
		} else { //실패
			return "customer/modifyPw";
		}
	}
	
	
	@PostMapping("/customer/profile")
	public String profileAction(HttpServletRequest request,
			MultipartRequest multipartRequest) {
		
		System.out.println(request.getParameter("id"));
		System.out.println(request.getParameter("name"));
		
		MultipartFile file = multipartRequest.getFile("profileImage");
		
		System.out.println( file.getName() );
		System.out.println( file.getOriginalFilename() );
		System.out.println( file.isEmpty());
		System.out.println( file.getContentType());
		System.out.println( file.getSize());
		
		return "redirect:/customer/mypage";
	}
	
	@PostMapping("/customer/profiledto")
	public String profiledtoAction(UserProfileRequestForm userProfileRequestForm) {
		
		System.out.println(userProfileRequestForm.getId());
		System.out.println(userProfileRequestForm.getName());
		
		MultipartFile file = userProfileRequestForm.getProfileImage();
		//첨부파일 수신 확인
		
		//첨부파일에 대한 정보 확인
		System.out.println( file.getName() );
		System.out.println( file.getOriginalFilename() );
		System.out.println( file.isEmpty());
		System.out.println( file.getContentType());
		System.out.println( file.getSize());
		
		
		//1. 첨부된 파일을 저장소에 실제로 저장!
		
		/*
		//코드로 자체 저장
		try {
			file.transferTo( new File("d:/fileStorage/" + file.getOriginalFilename() )  );
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		//FileManager 클래스 활용
		
		
		//2. 저장된 파일에 대한 정보 + 처리해야하는 요청 정보 -> DB 저장
		
		try {
			
			//실제 저장폴더 경로에 파일이 저장된다
			FileInfo fileInfo = FileManager.storeFile(file);
			
			//저장된 파일 정보를 DB에 저장!
			int result = fileService.saveFileInfo(fileInfo);
			
			if(result > 0) {   //실제파일저장 -> DB에 파일정보 저장
				
				//어떤 사용자의 프로필 사진  DB 저장!
				
				UserProfileImage userProfileImage = new UserProfileImage();
				//id, fileName
				userProfileImage.setId(userProfileRequestForm.getId());
				userProfileImage.setFileName(fileInfo.getFileName());
				
				
				int result2 = userService.saveUserProfileImage(userProfileImage);
				
				//정상저장~ 
				//아니면~
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			//안됐을때 보여줄 정보, 실패시 보여줄 화면 >>> 
		}
		
		
		return "redirect:/customer/mypage";
	}
}
















