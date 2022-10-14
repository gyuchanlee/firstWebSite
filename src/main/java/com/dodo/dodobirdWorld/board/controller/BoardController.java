package com.dodo.dodobirdWorld.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.dodo.dodobirdWorld.board.service.BoardListService;
import com.dodo.dodobirdWorld.board.vo.BoardVO;
import com.dodo.dodobirdWorld.common.paging.Pagination;

@RestController
public class BoardController {
	
	@Autowired
	BoardListService service;
	// 게시판 페이지 이동 + 검색 + 페이지네이션 적용
	@GetMapping(value="/board")
	public ModelAndView boardList(@RequestParam(required = false) String search, 
								  @RequestParam(required = false) String keyword,
								  @RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
						          @RequestParam(value = "cntPerPage", required = false, defaultValue = "8") int cntPerPage,
						          @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
						          HttpSession session) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(search != null) { // 검색 조건이 param으로 넘어왔을 시, 값을 hashMap에 넣어줌.
			map.put("search", search);
			map.put("keyword", keyword);
		}
		
		int listCount = service.boardListCount(map); // 게시물 총 개수
		Pagination pagination = new Pagination(currentPage,cntPerPage,pageSize); // 페이징 객체 불러오기
		pagination.setTotalRecordCount(listCount); //  페이징 계산
		// 쿼리에 param으로 넘길 값들 map에 넣기.
		map.put("firstRecordIndex", pagination.getFirstRecordIndex());
		map.put("lastRecordIndex", pagination.getLastRecordIndex());
		
		ModelAndView view = new ModelAndView("boardList");
		List<BoardVO> list = service.boardList(map);
		if(search != null) { // 페이지 하단 이동할때 param으로 쓸 변수
			view.addObject("search",search);
			view.addObject("keyword",keyword);
		}
		view.addObject("pagination",pagination);
		view.addObject("list", list);
		// 로그인 시 session 정보
		view.addObject("id",session.getAttribute("id"));
		view.addObject("nickname",session.getAttribute("nickname"));
		view.addObject("userno", session.getAttribute("userno"));
		return view;
	}
	
	// 글 상세 읽기 페이지 이동
	@Transactional(rollbackFor = Exception.class)
	@GetMapping(value="/board/{board_id}")
	public ModelAndView boardOne(@PathVariable(value = "board_id", required = true) String board_id, HttpSession session) {
		ModelAndView view = new ModelAndView("boardOne");
		// 글 상세 조회 들어가면서 조회수 +1 서비스 호출
		service.hitUpdate(Integer.parseInt(board_id));
		// 조회수 올린 뒤, 글 상세 조회
		BoardVO board = service.boardOne(Integer.parseInt(board_id));
		view.addObject("board",board);
		view.addObject("id",session.getAttribute("id"));
		view.addObject("nickname",session.getAttribute("nickname"));
		view.addObject("userno", session.getAttribute("userno"));
		return view;
	}
	
	// 글 수정 페이지 이동
	@GetMapping(value="/boardWrite/{board_id}")
	public ModelAndView boardWrite(@PathVariable(value = "board_id", required = true) String board_id, HttpSession session) {
		ModelAndView view = new ModelAndView("boardWrite");
		BoardVO board = service.boardOne(Integer.parseInt(board_id));
		view.addObject("board",board);
		return view;
	}
	
	// 글 등록 페이지 이동
	@GetMapping(value="/boardWrite")
	public ModelAndView boardWrite2(HttpSession session) {
		ModelAndView view = new ModelAndView("boardWrite");
		view.addObject("id",session.getAttribute("id"));
		view.addObject("nickname",session.getAttribute("nickname"));
		view.addObject("userno", session.getAttribute("userno"));
		return view;
	}
	
	// 글 Insert ajax
	@PostMapping("/board")
	public int boardInsert(@RequestBody BoardVO board) {
		int success = service.boardInsert(board);
		return success;
	}
	
	// 글 Update ajax
	@PutMapping("/board/{board_id}")
	public int boardUpdate(@RequestBody BoardVO board) {
		int success = service.boardUpdate(board);
		return success;
	}
	
	// 글 delete ajax
	@DeleteMapping("/board/{board_id}")
	public int boardDelete(@RequestBody int board_id) {
		int success = service.boardDelete(board_id);
		return success;
	}
	
}
