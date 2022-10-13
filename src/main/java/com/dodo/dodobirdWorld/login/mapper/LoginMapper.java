package com.dodo.dodobirdWorld.login.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.dodo.dodobirdWorld.users.vo.UsersVO;

@Mapper
public interface LoginMapper {
	public UsersVO loadUserInfo(String id);
}
