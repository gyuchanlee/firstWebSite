package com.dodo.dodobirdWorld.photos.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.dodo.dodobirdWorld.photos.vo.PhotosVO;

@Mapper
public interface PhotosMapper {
	public List<PhotosVO> photosList(Map<String, String> map); // 포토 갤러리 리스트
	public PhotosVO photosOne(String photo_id); // 포토 갤러리 한 건 조회
	public int photosInsert(PhotosVO vo); // 등록
	public int photosUpdate(PhotosVO vo); // 업데이트
	public int photosDelete(String photo_id); // 논리삭제
	public void hitUpdate(String photo_id); // 조회수 +1
}
