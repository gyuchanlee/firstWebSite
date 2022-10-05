package com.dodo.dodobirdWorld.board.service.Impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodo.dodobirdWorld.board.mapper.BoardListTestMapper;
import com.dodo.dodobirdWorld.board.service.BoardListTestService;
import com.dodo.dodobirdWorld.board.vo.EmployeeVO;

@Service
public class BoardListTestServiceImpl implements BoardListTestService {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<EmployeeVO> testList() {
		BoardListTestMapper mapper = sqlSession.getMapper(BoardListTestMapper.class);
		List<EmployeeVO> list = mapper.testList();
		return list;
	}

}
