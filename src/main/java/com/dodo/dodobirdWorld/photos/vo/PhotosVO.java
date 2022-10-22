package com.dodo.dodobirdWorld.photos.vo;

import java.util.Date;

import lombok.Data;

@Data
public class PhotosVO {
	private String photo_id;
	private String writer;
	private String title;
	private String content;
	private String filename;
	private String filename_original;
	private int filesize;
	private Date insert_date;
	private Date update_date;
	private Date delete_date;
	private int hit;
}
