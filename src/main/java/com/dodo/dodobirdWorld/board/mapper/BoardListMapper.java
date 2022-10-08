package com.dodo.dodobirdWorld.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.dodo.dodobirdWorld.board.vo.BoardVO;

@Mapper
public interface BoardListMapper {
	public List<BoardVO> boardList(Map<String, Object> map); // 전체 글 조회 + 검색 param
	public BoardVO boardOne(int board_id);
	public int boardInsert(BoardVO vo);
	public int boardUpdate(BoardVO vo);
	public int boardDelete(int board_id);
	public void hitUpdate(int board_id); // 조회수 +1
}
