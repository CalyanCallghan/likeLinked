package com.onpassive.onet.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.onpassive.onet.model.HomeRequestModel;

public interface PostStorageService {
	public int storeFile(MultipartFile file,HomeRequestModel model);
	public String storeAndUpdateProfileImage(MultipartFile file,int userId);

	public Resource loadFileAsResource(String fileName);
	
}
