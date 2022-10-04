package com.dodo.dodobirdWorld.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {
	
	@RequestMapping(value="/")
	public ModelAndView index() {
		ModelAndView view = new ModelAndView("index");
		return view;
	}
	
	@RequestMapping(value="/test")
	public ModelAndView test() {
		ModelAndView view = new ModelAndView("test");
		return view;
	}
}
