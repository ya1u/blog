package com.cos.blog.controller.api;

//import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.Users;
import com.cos.blog.service.UserService;

@RestController
public class UserApiController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody Users user) {
		System.out.println("UserApiController 호출됨");
		
		// 받아온 값은 username, password, email만 있다.
//		user.setRoles(RoleType.USER);
		
		// 실제로 DB에 insert를 하고 아래에서(1자리에) return이 된다.
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
		// result가 1이면 성공, -1이면 실패
		// 자바 오브젝트를 리턴 받아옴.
	}
	
//	@PostMapping("/api/user/login")
//	public ResponseDto<Integer> login(@RequestBody Users user, HttpSession session) {
//		System.out.println("UserApiController: login 호출됨");
//		Users principal = userService.로그인(user);
//		System.out.println(principal);
//		// principal(접근주체)
//		if(principal != null) {
//			session.setAttribute("principal", principal);
//			return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
//		} else {
//			return new ResponseDto<Integer>(HttpStatus.OK.value(),-1);
//		}
//		// result가 1이면 성공, -1이면 실패
//		// 자바 오브젝트를 리턴받아옴.
//	}
}