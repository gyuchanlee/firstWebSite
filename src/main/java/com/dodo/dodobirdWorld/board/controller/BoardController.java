package com.dodo.dodobirdWorld.board.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value="/board")
public class BoardController {
	
	@RequestMapping(value="/boardList")
	public ModelAndView boardList() {
		ModelAndView view = new ModelAndView("boardList");
		return view;
	}
	
}
