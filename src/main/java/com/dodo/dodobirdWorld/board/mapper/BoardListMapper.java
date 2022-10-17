package com.dodo.dodobirdWorld.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.dodo.dodobirdWorld.board.vo.BoardCommentVO;
import com.dodo.dodobirdWorld.board.vo.BoardVO;

@Mapper
public interface BoardListMapper {
	public List<BoardVO> boardList(Map<String, Object> map); // 전체 글 조회 + 검색 param
	public int boardListCount(Map<String, Object> map); // 게시물 카운트 for Paging
	public BoardVO boardOne(String board_id);
	
	public int boardInsert(BoardVO vo);
	public int boardUpdate(BoardVO vo);
	public int boardDelete(String board_id);
	public void hitUpdate(String board_id); // 조회수 +1
	
	public List<BoardCommentVO> commentList(String board_id); // 게시물의 댓글 가져오기
	public int commentInsert(BoardCommentVO vo); // 댓글 등록
	public int commentUpdate(BoardCommentVO vo); // 댓글 수정
	public int commentDelete(String board_id); // 댓글 논리 삭제
}
