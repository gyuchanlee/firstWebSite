package com.dodo.dodobirdWorld.board.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodo.dodobirdWorld.board.mapper.BoardListMapper;
import com.dodo.dodobirdWorld.board.service.BoardListService;
import com.dodo.dodobirdWorld.board.vo.BoardCommentVO;
import com.dodo.dodobirdWorld.board.vo.BoardVO;

@Service
public class BoardListServiceImpl implements BoardListService {
	
	@Autowired
	BoardListMapper mapper;
	
	@Override
	public List<BoardVO> boardList(Map<String, Object> map) {
		List<BoardVO> list = mapper.boardList(map);
		return list;
	}

	@Override
	public BoardVO boardOne(String board_id) {
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
	public int boardDelete(String board_id) {
		return mapper.boardDelete(board_id);
	}

	@Override
	public void hitUpdate(String board_id) {
		mapper.hitUpdate(board_id);
	}

	// 페이징을 위한 전체 게시물 개수 카운트 메서드
	@Override
	public int boardListCount(Map<String, Object> map) {
		return mapper.boardListCount(map);
	}

	@Override
	public List<BoardCommentVO> commentList(String board_id) {
		return mapper.commentList(board_id);
	}

	@Override
	public int commentInsert(BoardCommentVO vo) {
		return mapper.commentInsert(vo);
	}

	@Override
	public int commentUpdate(BoardCommentVO vo) {
		return mapper.commentUpdate(vo);
	}

	@Override
	public int commentDelete(String comment_id) {
		return mapper.commentDelete(comment_id);
	}

}
