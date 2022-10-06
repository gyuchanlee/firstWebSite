package com.dodo.dodobirdWorld.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.dodo.dodobirdWorld.board.service.BoardListService;
import com.dodo.dodobirdWorld.board.vo.BoardVO;

@RestController
@RequestMapping(value="/board")
public class BoardController {
	
	@Autowired
	BoardListService service;
	// 게시판 페이지 이동
	@RequestMapping(value="/boardList")
	public ModelAndView boardList() {
		ModelAndView view = new ModelAndView("boardList");
		List<BoardVO> list = service.boardList();
		view.addObject("list", list);
		return view;
	}
	// 글 상세 읽기 페이지 이동
	@GetMapping(value="/boardOne/{board_id}")
	public ModelAndView boardOne(@PathVariable(value = "board_id", required = true) String board_id) {
		ModelAndView view = new ModelAndView("boardOne");
		BoardVO board = service.boardOne(Integer.parseInt(board_id));
		view.addObject("board",board);
		return view;
	}
	
	// 글 수정 / 등록 페이지 이동
	@GetMapping(value="/boardWrite/{board_id}")
	public ModelAndView boardWrite(@PathVariable(value = "board_id", required = true) String board_id) {
		ModelAndView view = new ModelAndView("boardWrite");
		BoardVO board = service.boardOne(Integer.parseInt(board_id));
		view.addObject("board",board);
		return view;
	}
	
	// 글 Update ajax
	@PutMapping("/boardWrite")
	public int boardUpdate(@RequestBody BoardVO board) {
		int success = service.boardUpdate(board);
		System.out.println(success);
		return success;
	}
	
}
