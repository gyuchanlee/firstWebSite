package com.dodo.dodobirdWorld.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dodo.dodobirdWorld.board.vo.BoardVO;

@Mapper
public interface BoardListMapper {
	public List<BoardVO> boardList();
}