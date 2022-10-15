package com.dodo.dodobirdWorld.login.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dodo.dodobirdWorld.login.mapper.LoginMapper;
import com.dodo.dodobirdWorld.login.service.LoginService;
import com.dodo.dodobirdWorld.users.vo.UsersVO;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	LoginMapper mapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UsersVO user = mapper.loadUserInfo(username);
		if (user == null){
			throw new UsernameNotFoundException("유저를 찾을 수 없습니다. : " + username);
		}
		return user;
	}

}
