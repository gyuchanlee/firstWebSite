package com.dodo.dodobirdWorld.photos.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodo.dodobirdWorld.photos.mapper.PhotosMapper;
import com.dodo.dodobirdWorld.photos.service.PhotosService;
import com.dodo.dodobirdWorld.photos.vo.PhotosVO;

@Service
public class PhotosServiceImpl implements PhotosService {
	
	@Autowired
	PhotosMapper mapper;

	@Override
	public List<PhotosVO> photosList(Map<String, String> map) {
		return mapper.photosList(map);
	}

	@Override
	public PhotosVO photosOne(String photo_id) {
		return mapper.photosOne(photo_id);
	}

	@Override
	public int photosInsert(PhotosVO vo) {
		return mapper.photosInsert(vo);
	}

	@Override
	public int photosUpdate(PhotosVO vo) {
		return mapper.photosUpdate(vo);
	}

	@Override
	public int photosDelete(String photo_id) {
		return mapper.photosDelete(photo_id);
	}

	@Override
	public void hitUpdate(String photo_id) {
		mapper.hitUpdate(photo_id);
	}

}
