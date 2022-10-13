package com.dodo.dodobirdWorld.users.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class UsersVO implements UserDetails {
	private int user_no;
	private String id;
	private String nickname;
	private String profile;
	private Date register_date;
	private String password;
	// 나중에 개선 필요
	private String authority;
	private String email;
	private String address;
	private String phone;
	
	// ajax에서 UsersVO를 json화 할때 생기는 문제 해결 > ddserializer 커스텀 class 추가.
	@JsonDeserialize(using = CustomAuthorityDeserializer.class)
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<GrantedAuthority> auth = new ArrayList<>();
		auth.add(new SimpleGrantedAuthority(this.authority));
		return auth;
	}
	@Override
	public String getUsername() {
		return this.id;
	}
//	@Override
//	public String getPassword() {
//		return password;
//	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
}
