package com.dodo.dodobirdWorld.board.vo;

import java.util.Date;

import lombok.Data;

@Data
public class BoardVO {
	private String board_id;
	private String writer;
	private String title;
	private String content;
	private Date insert_date;
	private Date update_date;
	private Date delete_date;
	private int hit;
	// 검색 받아오기 용
	private String nickname;
}
