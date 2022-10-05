package com.dodo.dodobirdWorld.board.service.Impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodo.dodobirdWorld.board.mapper.BoardListMapper;
import com.dodo.dodobirdWorld.board.service.BoardListService;
import com.dodo.dodobirdWorld.board.vo.BoardVO;

@Service
public class BoardListServiceImpl implements BoardListService {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<BoardVO> boardList() {
		BoardListMapper mapper = sqlSession.getMapper(BoardListMapper.class);
		List<BoardVO> list = mapper.boardList();
		return list;
	}

}
