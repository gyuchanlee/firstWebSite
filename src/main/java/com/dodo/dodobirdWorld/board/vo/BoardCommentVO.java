package com.dodo.dodobirdWorld.board.vo;

import java.util.Date;

import lombok.Data;

@Data
public class BoardCommentVO {
	private String board_id;
	private String comment_id;
	private String parent_comment_id;
	private String content;
	private String writer;
	private Date insert_date;
	private Date update_date;
	private Date delete_date;
	// 검색용 변수
	private String id;
	private String nickname;
}
