package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dodo.dodobirdWorld.board.mapper.BoardListTestMapper;

@SpringBootTest
class DodobirdWorldApplicationTests {

	@Autowired BoardListTestMapper mapper;

	@Test
	void contextLoads() {
		mapper.testList();
	}

}
