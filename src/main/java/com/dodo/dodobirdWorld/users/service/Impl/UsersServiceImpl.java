package com.dodo.dodobirdWorld.users.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodo.dodobirdWorld.users.mapper.UsersMapper;
import com.dodo.dodobirdWorld.users.service.UsersService;
import com.dodo.dodobirdWorld.users.vo.UsersVO;

@Service
public class UsersServiceImpl implements UsersService {
	
	@Autowired
	UsersMapper mapper;
	
	@Override
	public UsersVO selectUserInfo(String id) {
		UsersVO user = mapper.selectUserInfo(id);
		return user; // 유저 정보 가져오기
	}

	@Override
	public int userInsert(UsersVO vo) {
		return mapper.userInsert(vo);
	}

	@Override
	public int userUpdate(UsersVO vo) {
		return mapper.userUpdate(vo);
	}

	@Override
	public int userDelete(String id) {
		return mapper.userDelete(id);
	}

}
