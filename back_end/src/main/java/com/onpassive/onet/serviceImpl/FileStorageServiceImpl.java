package com.onpassive.onet.serviceImpl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.onpassive.onet.config.FileStorageProperties;
import com.onpassive.onet.entity.Post;
import com.onpassive.onet.exception.FileStorageException;
import com.onpassive.onet.exception.MyFileNotFoundException;
import com.onpassive.onet.model.HomeRequestModel;
import com.onpassive.onet.repository.FileRepository;
import com.onpassive.onet.service.FileStorageService;

@Service
public class FileStorageServiceImpl implements FileStorageService {

	private final Path fileStorageLocation;

	@Autowired
	private FileRepository fileRepository;

	@Autowired
	public FileStorageServiceImpl(FileStorageProperties fileStorageProperties) {
		this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",
					ex);
		}
	}

	public String storeFile(MultipartFile file, HomeRequestModel model) {
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}
			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Post post = new Post();
			post.setDescription(model.getContent());
			post.setCreatedBy(model.getCreatedBy());
			post.setType(model.getType());
			post.setFileName(fileName);
			post.setCreatedDt(LocalDateTime.now());
			post.setGroupId(model.getGroupId());
			post.setFilePath(this.fileStorageLocation.toString());
			post.setFormat(model.getFormat());
			// Saving file/copy file to the target location (Replacing existing file with
			// the same name)
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			fileRepository.save(post);
			return fileName;
		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}
	
	public int storeAndUpdateProfileImage(MultipartFile file, int userID) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		int count;
		try {
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}
			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			count = fileRepository.updateProfilePic(fileName,userID);
			return count;
		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}
	

	public Resource loadFileAsResource(String fileName) {
		try {
			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists()) {
				return resource;
			} else {
				throw new MyFileNotFoundException("File not found " + fileName);
			}
		} catch (MalformedURLException ex) {
			throw new MyFileNotFoundException("File not found " + fileName, ex);
		}
	}
	
	@Override
	public List<Post> getAllPosts(String type) {
		return fileRepository.returnAllthePosts(type);
	}

}
