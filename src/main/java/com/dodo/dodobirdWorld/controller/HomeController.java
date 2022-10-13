package com.dodo.dodobirdWorld.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {
	
	@RequestMapping(value="/")
	public ModelAndView index(HttpSession session) {
		ModelAndView view = new ModelAndView("index");
		
		view.addObject("id",session.getAttribute("id"));
		view.addObject("nickname",session.getAttribute("nickname"));
		view.addObject("userno", session.getAttribute("userno"));
		return view;
	}
	
	@RequestMapping(value="/test")
	public ModelAndView test() {
		ModelAndView view = new ModelAndView("test");
		return view;
	}
}
