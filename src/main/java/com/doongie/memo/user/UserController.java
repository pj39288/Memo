package com.doongie.memo.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	
	//회원가입 화면
	@GetMapping("/signup/view")
	public String signupInput() {
		
		return "/user/signup";
	}
	
	
	//로그인 화면
	@GetMapping("/signin/view")
	public String signinInput() {
		
		return "/user/signin";
	}
	
	
	
	// redirect로 하면 jsp경로가 아니고 url경로가 입력된다
	@GetMapping("/signout")
	public String signout(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		session.removeAttribute("userId");
		session.removeAttribute("userName");
		
		return "redirect:/user/signin/view";
		
	}
	
	
}
