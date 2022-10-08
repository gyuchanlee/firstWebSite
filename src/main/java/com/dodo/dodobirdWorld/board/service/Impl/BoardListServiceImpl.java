package com.dodo.dodobirdWorld.board.service.Impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodo.dodobirdWorld.board.mapper.BoardListMapper;
import com.dodo.dodobirdWorld.board.service.BoardListService;
import com.dodo.dodobirdWorld.board.vo.BoardVO;

@Service
public class BoardListServiceImpl implements BoardListService {
	
	@Autowired
	BoardListMapper mapper;
	
	@Override
	public List<BoardVO> boardList() {
		List<BoardVO> list = mapper.boardList();
		return list;
	}

	@Override
	public BoardVO boardOne(int board_id) {
		BoardVO board = mapper.boardOne(board_id);
		return board;
	}

	@Override
	public int boardInsert(BoardVO vo) {
		return mapper.boardInsert(vo);
	}

	@Override
	public int boardUpdate(BoardVO vo) {
		return mapper.boardUpdate(vo);
	}

	@Override
	public int boardDelete(int board_id) {
		return mapper.boardDelete(board_id);
	}

	@Override
	public List<BoardVO> boardListFilters(Map<String, Object> map) {
		List<BoardVO> list = mapper.boardListFilters(map);
		return list;
	}

}
