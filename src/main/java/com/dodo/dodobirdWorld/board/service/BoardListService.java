package com.dodo.dodobirdWorld.board.service;

import java.util.List;
import java.util.Map;

import com.dodo.dodobirdWorld.board.vo.BoardVO;

public interface BoardListService {
	public List<BoardVO> boardList();
	public List<BoardVO> boardListFilters(Map<String,Object> map); // 검색 기능 있는 전체리스트 조회
	public BoardVO boardOne(int board_id);
	public int boardInsert(BoardVO vo);
	public int boardUpdate(BoardVO vo);
	public int boardDelete(int board_id);
}
