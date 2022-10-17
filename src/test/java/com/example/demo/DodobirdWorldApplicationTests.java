package com.example.demo;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dodo.dodobirdWorld.board.mapper.BoardListMapper;
import com.dodo.dodobirdWorld.board.service.BoardListService;

@SpringBootTest
class DodobirdWorldApplicationTests {
	
	@Autowired BoardListMapper mapper;
	@Autowired BoardListService service;
	
	@Test
	void contextLoads() {
		
	}
}
