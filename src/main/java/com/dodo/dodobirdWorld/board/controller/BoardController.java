package com.dodo.dodobirdWorld.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

@RestController
public class BoardController {
	
	@Autowired
	BoardListService service;
	// 게시판 페이지 이동
	@GetMapping(value="/board")
	public ModelAndView boardList(@RequestParam(required = false) String search, @RequestParam(required = false) String keyword ) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(search != null) { // 검색 조건이 param으로 넘어왔을 시, 값을 hashMap에 넣어줌.
			map.put("search", search);
			map.put("keyword", keyword);
		}
		
		ModelAndView view = new ModelAndView("boardList");
		List<BoardVO> list = service.boardList(map);
		view.addObject("list", list);
		return view;
	}
	
	// 글 상세 읽기 페이지 이동
	@Transactional(rollbackFor = Exception.class)
	@GetMapping(value="/board/{board_id}")
	public ModelAndView boardOne(@PathVariable(value = "board_id", required = true) String board_id) {
		ModelAndView view = new ModelAndView("boardOne");
		// 글 상세 조회 들어가면서 조회수 +1 서비스 호출
		service.hitUpdate(Integer.parseInt(board_id));
		// 조회수 올린 뒤, 글 상세 조회
		BoardVO board = service.boardOne(Integer.parseInt(board_id));
		view.addObject("board",board);
		return view;
	}
	
	// 글 수정 페이지 이동
	@GetMapping(value="/boardWrite/{board_id}")
	public ModelAndView boardWrite(@PathVariable(value = "board_id", required = true) String board_id) {
		ModelAndView view = new ModelAndView("boardWrite");
		BoardVO board = service.boardOne(Integer.parseInt(board_id));
		view.addObject("board",board);
		return view;
	}
	
	// 글 등록 페이지 이동
	@GetMapping(value="/boardWrite")
	public ModelAndView boardWrite2() {
		ModelAndView view = new ModelAndView("boardWrite");
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
