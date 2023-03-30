package com.doongie.memo.common;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class PermissionInterceptor implements HandlerInterceptor{

	@Override 
	public boolean preHandle(
			HttpServletRequest request
			, HttpServletResponse response
			, Object handler) throws IOException {
		
		HttpSession session = request.getSession();
		
		Integer userId = (Integer) session.getAttribute("userId");
		
		// user/signin/view
		String uri = request.getRequestURI();
		
		if(userId != null) {
			// 로그인이 되었을때 
			// 회원가입, 로그인 페이지 접근 불가처리하기
			// 이런 페이지는 너무많으니 /user로 시작하는 모든 페이지 처리
			
			// 그리고 리스트 페이지로 이동시키기
			
			if(uri.startsWith("/user")) {
				// 리다이렉트
				response.sendRedirect("/post/list/view");
				return false;
			}
			
		 	
		} else {			
			// 로그인 안되었을때
			// 리스트, 글쓰기, 상세화면 페이지 접근 불가처리하기
			// /post로 시작하는 페이지에 접근하려하면...
			
			// 그리고 로그인 페이지로 이동시키기
			
			if(uri.startsWith("/post")) {
				// 리다이렉트
				response.sendRedirect("/user/signin/view");
				return false;
			}
		}
		
		return true;
		
		
		
	}
}
