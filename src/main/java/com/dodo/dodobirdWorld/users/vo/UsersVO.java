package com.dodo.dodobirdWorld.users.vo;

import java.util.Date;
import lombok.Data;

@Data
public class UsersVO {
	private int user_no;
	private String id;
	private String nickname;
	private String profile;
	private Date register_date;
}
