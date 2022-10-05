package com.dodo.dodobirdWorld.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@RequestMapping(value="/boardList")
	public ModelAndView boardList() {
		ModelAndView view = new ModelAndView("boardList");
		List<BoardVO> list = service.boardList();
		view.addObject("list", list);
		return view;
	}
	
}
