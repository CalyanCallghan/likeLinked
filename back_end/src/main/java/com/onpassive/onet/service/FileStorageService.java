package com.onpassive.onet.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.onpassive.onet.model.HomeRequestModel;

public interface FileStorageService {
	public String storeFile(MultipartFile file,HomeRequestModel model);
	public int storeAndUpdateProfileImage(MultipartFile file,int userId);

	public Resource loadFileAsResource(String fileName);
}
