package com.dodo.dodobirdWorld.login.contoller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.dodo.dodobirdWorld.users.vo.UsersVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class LoginController {
	
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	
	// 로그인 하러가기
	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView view = new ModelAndView("login");
		return view;
	}
	
	// 로그인 시도 성공
	@PostMapping("/login_success")
	public ModelAndView login_success (HttpSession session, Authentication authentication) {
		authentication = SecurityContextHolder.getContext().getAuthentication();
		// DB의 유저 정보 필요한만큼 세션 등록
		UsersVO user = (UsersVO) authentication.getPrincipal();
		session.setAttribute("id", user.getId());
		session.setAttribute("nickname", user.getNickname());
		session.setAttribute("userno", user.getUser_no());
		
		ModelAndView view = new ModelAndView("index");
		String message = "로그인성공!!";
		
		log.info("session : '{}'",session.getAttribute("id"));
		
		view.addObject("message",message);
		view.addObject("id",user.getId());
		view.addObject("nickname",user.getNickname());
		view.addObject("userno", user.getUser_no());
		return view;
	}
	// 로그인 실패
	@PostMapping("/access_denied")
	public ModelAndView access_denied () {
		ModelAndView view = new ModelAndView("index");
		String message = "로그인 실패!!";
		view.addObject("message",message);
		return view;
	}
	
	//로그아웃
	@PostMapping("/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response,
			Authentication auth,HttpSession session) {
		auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			session.removeAttribute("id");
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		log.info("로그아웃 떤냐??");
	}
	
}
