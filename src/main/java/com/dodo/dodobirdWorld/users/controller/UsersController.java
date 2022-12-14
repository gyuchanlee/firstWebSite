package com.dodo.dodobirdWorld.users.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.dodo.dodobirdWorld.users.service.UsersService;
import com.dodo.dodobirdWorld.users.vo.UsersVO;

@RestController
public class UsersController {
	
	@Autowired
	UsersService service;
	
	// 유저 개인 정보 확인 페이지
	@GetMapping("/user/{id}")
	public ModelAndView selectUserInfo(@PathVariable(value = "id", required = true) String id, HttpSession session) {
		ModelAndView view = new ModelAndView("userInfo");
		UsersVO user = service.selectUserInfo(id);
		view.addObject("user",user);
		return view;
	}
	
	// 회원 가입 insert ajax
	@GetMapping("/userSignUp")
	public ModelAndView userSignUp() {
		ModelAndView view = new ModelAndView("userSignUp");
		return view;
	}
	
	// 회원 가입 insert ajax
	@PostMapping("/user")
	public int userInsert(@RequestBody UsersVO user) {
		int success = service.userInsert(user);
		return success;
	}
	
	// 회원 수정 update ajax
	@Transactional(rollbackFor = Exception.class)
	@PutMapping("/user/{id}")
	public int userUpdate(@RequestBody UsersVO user, HttpSession session) {
		String id = user.getId();
		// 유저 update
		int success = service.userUpdate(user);
		// update된 유저 정보 다시 select
		UsersVO changedUser = service.selectUserInfo(id);
		// 수정되었을 때 session에 담긴 정보도 바꿈.
		session.setAttribute("id", changedUser.getId());
		session.setAttribute("nickname", changedUser.getNickname());
		session.setAttribute("userno", changedUser.getUser_no());
		
		return success;
	}
	
	// 회원 탈퇴 delete ajax
	@DeleteMapping("/user/{id}")
	public int userInsert(@RequestBody Map<String,String> id, HttpServletRequest request, HttpServletResponse response,
			Authentication auth,HttpSession session) {
		String ids = id.get("id"); // @RequestBody에서 바로 String을 받아오는 경우, JSON에서 받아온 "ID" 따옴표 형식까지 등록이 되어 map으로 받아옴.
		System.out.println(ids);
		int success = service.userDelete(ids);
		// 회원 게시글/댓글 삭제
		
		// 로그아웃
		auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(success);
		if(success > 0) { // 회원 탈퇴 성공 시,
			session.removeAttribute("id");
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return success;
	}
}
