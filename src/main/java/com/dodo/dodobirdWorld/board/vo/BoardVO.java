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
}
