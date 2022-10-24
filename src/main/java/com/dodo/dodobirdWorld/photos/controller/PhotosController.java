package com.dodo.dodobirdWorld.photos.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
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
	public void photosInsert(@RequestParam MultipartFile[] uploadFile, @RequestParam String title,
							@RequestParam String content, @RequestParam String writer,
							HttpServletResponse response) {
		// 파일 업로드 2022-10-22 making
		PhotosVO photo = new PhotosVO();
		photo.setWriter(writer);
		photo.setTitle(title);
		photo.setContent(content);
		String uploadFolder = "C:\\uploadTest";
		int fileSize = 0;
		for (MultipartFile multipartFile : uploadFile) {
			String uploadFileName = multipartFile.getOriginalFilename();
			fileSize = (int) multipartFile.getSize();
			photo.setFilename_original(uploadFileName);
			String fileName = fileNameRandom(uploadFileName);
			photo.setFilename(fileName);
			photo.setFilesize(fileSize);

			// 저장할 파일, 생성자로 경로와 이름을 지정해줌.
			File saveFile = new File(uploadFolder, fileName);

			try {
				// void transferTo(File dest) throws IOException : 업로드한 파일 데이터를 지정한 파일에 저장
				multipartFile.transferTo(saveFile);
			} catch (Exception e) {
				System.out.println("Error : "+ e.getMessage());
			}
		}
		int success = service.photosInsert(photo);
		String redirect_uri="/";
		try {
			response.sendRedirect(redirect_uri); // redirect 해줌.
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
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

	private String fileNameRandom(String originalName){ // uuid 활용하여 파일 업로드 시, 파일명 중복 방지.
		// uuid 생성
		UUID uuid = UUID.randomUUID();
		//savedName 변수에 uuid + 원래 이름 추가
		String savedName = uuid.toString()+"_"+originalName;

		return savedName;
	}
}
