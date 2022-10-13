package com.dodo.dodobirdWorld.users.service;

import com.dodo.dodobirdWorld.users.vo.UsersVO;

public interface UsersService {
	public UsersVO selectUserInfo(String id);
	public int userInsert(UsersVO vo);
	public int userUpdate(UsersVO vo);
	public int userDelete(String id);
}
