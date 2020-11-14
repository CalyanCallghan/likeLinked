package com.onpassive.onet.service;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.onpassive.onet.model.HomeRequestModel;
import com.onpassive.onet.model.PostDetails;

public interface PostStorageService {
	public int storeFile(MultipartFile file, HomeRequestModel model);

	public String storeAndUpdateProfileImage(MultipartFile file, int userId);

	public Resource loadFileAsResource(String fileName);

	public List<PostDetails> getAllPosts(String type);

	public int saveData(HomeRequestModel model);
	
	public String storeFileCvs(MultipartFile file);

}
