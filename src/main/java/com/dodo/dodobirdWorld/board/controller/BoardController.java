package com.dodo.dodobirdWorld.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.dodo.dodobirdWorld.board.service.BoardListTestService;
import com.dodo.dodobirdWorld.board.vo.EmployeeVO;

@RestController
@RequestMapping(value="/board")
public class BoardController {
	
	@Autowired
	BoardListTestService service;
	
	@RequestMapping(value="/boardList")
	public ModelAndView boardList() {
		ModelAndView view = new ModelAndView("boardList");
		List<EmployeeVO> list = service.testList();
		view.addObject("list", list);
		return view;
	}
	
}
