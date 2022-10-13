package com.dodo.dodobirdWorld.users.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.dodo.dodobirdWorld.users.vo.UsersVO;

@Mapper
public interface UsersMapper {
	public UsersVO selectUserInfo(String id);
	public int userInsert(UsersVO vo);
	public int userUpdate(UsersVO vo);
	public int userDelete(String id);
}
