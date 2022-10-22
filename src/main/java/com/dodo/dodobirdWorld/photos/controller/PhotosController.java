package com.dodo.dodobirdWorld.photos.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.dodo.dodobirdWorld.photos.service.PhotosService;
import com.dodo.dodobirdWorld.photos.vo.PhotosVO;

@RestController
public class PhotosController {
	
	@Autowired
	PhotosService service;
	
	// 포토 갤러리 전체 보기
	@GetMapping(value="/photos")
	public ModelAndView photosList(@RequestParam(required = false) String search, 
			  					   @RequestParam(required = false) String keyword,
			  					   HttpSession session) {
		ModelAndView view = new ModelAndView("photosList");
		Map<String,String> map = new HashMap<>();
		if(search!=null) {
			map.put("keyword", keyword);
			map.put("search", search);
		}
		
		// 이미지 파일 리스트 select service
		List<PhotosVO> list =  service.photosList(map);
		view.addObject("list",list);
		// 페이징은 무한스크롤로 해보기
		
		// 로그인 시 session 정보
		view.addObject("id",session.getAttribute("id"));
		view.addObject("nickname",session.getAttribute("nickname"));
		view.addObject("userno", session.getAttribute("userno"));
		
		return view;
	}
	
	// 포토 갤러리 한 게시물 보기
	@GetMapping(value="/photos/{photo_id}")
	public ModelAndView photosList(@PathVariable(value = "photo_id", required = true) String photo_id,
			  					   HttpSession session) {
		ModelAndView view = new ModelAndView("photoOne");
		
		// 이미지 파일 리스트 select service
		
		// 로그인 시 session 정보
		view.addObject("id",session.getAttribute("id"));
		view.addObject("nickname",session.getAttribute("nickname"));
		view.addObject("userno", session.getAttribute("userno"));
		
		return view;
	}
	
	// 글 수정 페이지 이동
	@GetMapping(value="/photoWrite/{photo_id}")
	public ModelAndView photoWrite(@PathVariable(value = "photo_id", required = true) String photo_id, HttpSession session) {
		ModelAndView view = new ModelAndView("photoWrite");
		PhotosVO photo = service.photosOne(photo_id);
		view.addObject("photo",photo);
		return view;
	}
	
	// 글 등록 페이지 이동
	@GetMapping(value="/photoWrite")
	public ModelAndView photoWrite2(HttpSession session) {
		ModelAndView view = new ModelAndView("photoWrite");
		view.addObject("id",session.getAttribute("id"));
		view.addObject("nickname",session.getAttribute("nickname"));
		view.addObject("userno", session.getAttribute("userno"));
		return view;
	}
	
	// 글 Insert ajax
	@PostMapping("/photos")
	public int photosInsert(@RequestBody PhotosVO photo, HttpServletRequest request) {
		// 파일 업로드 2022-10-22 making
		String charSet = "utf-8";	
		String attachDir = "C:\\uploadTest";
		int LimitSize = 1024*1024;
		
		int success = service.photosInsert(photo);
		return success;
	}
	
	// 글 Update ajax
	@PutMapping("/photos/{photo_id}")
	public int photoUpdate(@RequestBody PhotosVO photo) {
		int success = service.photosUpdate(photo);
		return success;
	}
	
	// 글 delete ajax
	@DeleteMapping("/photo/{photo_id}")
	public int photoDelete(@RequestBody String photo_id) {
		int success = service.photosDelete(photo_id); // 논리삭제로 변경
		return success;
	}
}
