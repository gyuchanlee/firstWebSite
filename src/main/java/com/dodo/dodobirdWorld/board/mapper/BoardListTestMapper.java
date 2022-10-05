package com.dodo.dodobirdWorld.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.dodo.dodobirdWorld.board.vo.EmployeeVO;

@Repository
@Mapper
public interface BoardListTestMapper {
	public List<EmployeeVO> testList();
}
