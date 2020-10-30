package com.onpassive.onet.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onpassive.onet.entity.User;
import com.onpassive.onet.model.HomeRequestModel;
import com.onpassive.onet.model.UserDetails;
import com.onpassive.onet.repository.UserRepository;
import com.onpassive.onet.service.FileStorageService;
import com.onpassive.onet.service.UserService;
import com.onpassive.onet.util.UploadFileResponse;

@CrossOrigin(origins = {"http://localhost:8080","http://localhost:4200"})
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private FileStorageService fileStorageService;
	
	@GetMapping("/getAllUsers")
	public List<User> getUsers() {
	    return userService.findAll();
	}
	
	@PostMapping("/uploadProfileImage")
	public ResponseEntity<UploadFileResponse> uploadProfileImage(@RequestParam("file") MultipartFile[] files,
			@RequestParam("userId") int userId) {
		String message = "";
		LocalDateTime dateTime = null;
		try {
			Arrays.asList(files).stream().forEach(file -> {
				fileStorageService.storeAndUpdateProfileImage(file, userId);
			});
			message = "Profile image updated. ";
			return ResponseEntity.ok(new UploadFileResponse(dateTime.now(), HttpStatus.OK, message));
		} catch (Exception e) {
			message = "Fail to profile image!!!";
			return ResponseEntity.ok(new UploadFileResponse(dateTime.now(), HttpStatus.EXPECTATION_FAILED, message));
		}
	}
	
	
	@GetMapping("/userDetails/{emailId}")
	public UserDetails getUserDetails(@PathVariable("emailId") String emailId) {
		UserDetails userDetails = userRepository.findUserByEmailId(emailId);
		return userDetails;
	}
	
	
}