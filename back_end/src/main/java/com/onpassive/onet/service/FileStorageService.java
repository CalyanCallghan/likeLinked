package com.onpassive.onet.service;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.onpassive.onet.model.HomeRequestModel;
import com.onpassive.onet.model.PostDetails;

public interface FileStorageService {
	public String storeFile(MultipartFile file,HomeRequestModel model);
	public int storeAndUpdateProfileImage(MultipartFile file,int userId);

	public Resource loadFileAsResource(String fileName);
	
	public List<PostDetails> getAllPosts(String type);
}
