package com.dodo.dodobirdWorld.board.service;

import java.util.List;

import com.dodo.dodobirdWorld.board.vo.BoardVO;

public interface BoardListService {
	public List<BoardVO> boardList();
	public BoardVO boardOne(int board_id);
	public int boardInsert(BoardVO vo);
	public int boardUpdate(BoardVO vo);
	public int boardDelete(int board_id);
}
