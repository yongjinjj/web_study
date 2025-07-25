package com.app.controller.study.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.common.ApiCommonCode;
import com.app.common.CommonCode;
import com.app.controller.study.practice.practice01.Practice01Controller;
import com.app.controller.study.viewdata.ViewData01Controller;
import com.app.dto.api.ApiResponse;
import com.app.dto.api.ApiResponseHeader;
import com.app.dto.room.Room;
import com.app.dto.study.api.ApiDelivery;
import com.app.dto.study.api.ApiMenu;
import com.app.dto.study.api.ApiResponseDelivery;
import com.app.dto.study.api.ApiStore;
import com.app.dto.user.User;

@RestController
public class Rest03Controller {

    private final ViewData01Controller viewData01Controller;

    Rest03Controller(ViewData01Controller viewData01Controller) {
        this.viewData01Controller = viewData01Controller;
    }

	//API 요청에 대해 응답 -> JSON 데이터
	
	// header, body 영역으로 나눠서 응답
	
	
	/*
		{
			header: {},
			body : {}
		}
		객체 -> json
	 */
	
	
	@GetMapping("/rest/object1")
	public ApiMenu object1() {
		
		ApiMenu apiMenu = new ApiMenu();
		apiMenu.setName("아메리카노");
		apiMenu.setPrice(1700);
		
		return apiMenu;
	}
	
	@GetMapping("/rest/object2")
	public List<ApiMenu> object2(){
		
		List<ApiMenu> menuList = new ArrayList<ApiMenu>();
		
		menuList.add(new ApiMenu("아메리카노", 1700));
		menuList.add(new ApiMenu("아이스티", 2500));
		menuList.add(new ApiMenu("샌드위치", 3700));
		menuList.add(new ApiMenu("초코쿠키", 2100));
		
		return menuList;
	}
	
	@GetMapping("/rest/object3")
	public ApiDelivery object3() {
		
		ApiDelivery apiDelivery = new ApiDelivery();
		
		apiDelivery.setStaffName("이병헌");
		apiDelivery.setDestination("한강공원 분수대 앞");
		apiDelivery.setPhone("010-1234-1223");
		
		ApiStore apiStore = new ApiStore();
		apiStore.setName("맥도날드");
		apiStore.setAddress("한강동 123번지 1,2층");
		apiStore.setTel("02-123-1212");
		
		apiDelivery.setApiStore(apiStore);
		
		List<ApiMenu> menuList = new ArrayList<ApiMenu>();
		
		menuList.add(new ApiMenu("아이스드립커피", 1700));
		menuList.add(new ApiMenu("스낵랩", 2500));
		menuList.add(new ApiMenu("토네이도", 3700));
		menuList.add(new ApiMenu("감자튀김", 2100));
		
		apiDelivery.setMenuList(menuList);
		
		return apiDelivery;
	}
	
	
	@GetMapping("/rest/res1")
	public int res1() {
		//리턴 해주는 응답 코드
		//10 정상
		//20 서버 내부 문제
		//30 인증키 없다
		//40 너 파라미터 잘못 보냈다
		//50 데이터 자체 없다
		return 30;
	}
	
	@GetMapping("/rest/res2")
	public ApiResponseHeader res2() {
		
		ApiResponseHeader apiResponseHeader = new ApiResponseHeader();
		
		apiResponseHeader.setResultCode(ApiCommonCode.API_RESULT_SUCCESS);
		apiResponseHeader.setResultMessage(ApiCommonCode.API_RESULT_SUCCESS_MSG);
		
		return apiResponseHeader;	
	}
	
	@GetMapping("/rest/res3")
	public ApiResponseDelivery res3() {

		//response header 영역
		ApiResponseHeader apiResponseHeader = new ApiResponseHeader();
		
		apiResponseHeader.setResultCode(ApiCommonCode.API_RESULT_SUCCESS);
		apiResponseHeader.setResultMessage(ApiCommonCode.API_RESULT_SUCCESS_MSG);
		
		//response body 영역
		ApiDelivery apiDelivery = new ApiDelivery();
		
		apiDelivery.setStaffName("이병헌");
		apiDelivery.setDestination("한강공원 분수대 앞");
		apiDelivery.setPhone("010-1234-1223");
		
		ApiStore apiStore = new ApiStore();
		apiStore.setName("맥도날드");
		apiStore.setAddress("한강동 123번지 1,2층");
		apiStore.setTel("02-123-1212");
		
		apiDelivery.setApiStore(apiStore);
		
		List<ApiMenu> menuList = new ArrayList<ApiMenu>();
		
		menuList.add(new ApiMenu("아이스드립커피", 1700));
		menuList.add(new ApiMenu("스낵랩", 2500));
		menuList.add(new ApiMenu("토네이도", 3700));
		menuList.add(new ApiMenu("감자튀김", 2100));
		
		apiDelivery.setMenuList(menuList);
		
		//최종 응답용 객체 
		ApiResponseDelivery apiResponseDelivery = new ApiResponseDelivery();
		apiResponseDelivery.setHeader(apiResponseHeader); //header 세팅
		apiResponseDelivery.setBody(apiDelivery); //body 세팅
		
		return apiResponseDelivery;
	}
	
	
	@GetMapping("/rest/res4")
	public ApiResponse<User> res4() {
		List<String> strList;
		List<Integer> intList;
		List<User> userList;
		List<Room> roomList;
		
		ApiResponse<String> ar1;
		ApiResponse<ApiMenu> ar2;
		ApiResponse<ApiDelivery> ar3;
		
		ApiResponse<List<User>> ar4;
		
		
		ApiResponse<User> res = new ApiResponse<User>();
		
		//header
		ApiResponseHeader apiResponseHeader = new ApiResponseHeader();
		
		apiResponseHeader.setResultCode(ApiCommonCode.API_RESULT_SUCCESS);
		apiResponseHeader.setResultMessage(ApiCommonCode.API_RESULT_SUCCESS_MSG);
		
		//body 지금 바디는 User 객체
		User user = new User();
		user.setId("ididid");
		user.setPw("pwpw");
		user.setName("nameanaa");
		user.setUserType(CommonCode.USER_USERTYPE_ADMIN);
		
		//response header 와 body 에 세팅
		res.setHeader(apiResponseHeader);
		res.setBody(user);
		
		return res;
	}
}

























