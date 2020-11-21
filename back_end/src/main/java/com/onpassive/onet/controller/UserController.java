package com.onpassive.onet.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.onpassive.onet.entity.User;
import com.onpassive.onet.model.UserDetails;
import com.onpassive.onet.repository.UserRepository;
import com.onpassive.onet.service.PostStorageService;
import com.onpassive.onet.service.UserService;
import com.onpassive.onet.util.UploadFileResponse;

@RestController
@RequestMapping("/user")
public class UserController {
	//private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private PostStorageService postStorageService;
	
	@GetMapping("/getAllUsers")
	public List<User> getUsers() {
		//logger.info("<-----getUsers-------start--->");
	    return userService.findAll();
	}
	
	@PostMapping("/uploadProfileImage")
	public ResponseEntity<UploadFileResponse> uploadProfileImage(@RequestParam("file") MultipartFile[] files,
			@RequestParam("userId") int userId) {
		//logger.debug("<-----uploadProfileImage-------start---userId>"+userId);

		String message = "";
		LocalDateTime dateTime = null;
		try {
			String fileName = postStorageService.storeAndUpdateProfileImage(files[0], userId);
			message = "Profile image uploaded successfully.";
			return ResponseEntity.ok(new UploadFileResponse(dateTime.now(), HttpStatus.OK, message,fileName));
		} catch (Exception e) {
			message = "Fail to profile image!!!";
			return ResponseEntity.ok(new UploadFileResponse(dateTime.now(), HttpStatus.EXPECTATION_FAILED, message,""));
		}
	}
	
	
	@GetMapping("/userDetails/{emailId}")
	public UserDetails getUserDetails(@PathVariable("emailId") String emailId) {
		//logger.debug("<-----getUserDetails-------start---emailId>"+emailId);
		UserDetails userDetails = userRepository.findUserByEmailId(emailId);
		return userDetails;
	}
	
	@DeleteMapping("/deleteUser/{empId}")
	public ResponseEntity<UploadFileResponse> deleteuser(@PathVariable Long empId) {
		String message = "";
		try {
			userService.deleteUser(empId);
			message = "User deleted successfully.";
			return ResponseEntity.ok(new UploadFileResponse(LocalDateTime.now(), HttpStatus.OK, message));
		} catch (Exception e) {
			message = "Getting Exception while deleting.!!!";
			return ResponseEntity
					.ok(new UploadFileResponse(LocalDateTime.now(), HttpStatus.EXPECTATION_FAILED, message));
		}
	}

	
	@RequestMapping(value = "/saveAll")
	public List<User> saveUsers(@RequestBody List<User> requestList) {
		
		List<User> userList = (List<User>) userService.saveUsers(requestList);
		return userList;
	}
	@GetMapping("/getUsers")
	public List<UserDetails> getAllUse() {
		//logger.info("<-----getUsers-------start--->");
		return userService.findAllUser();
	}
	
	 @PutMapping(value = "/updateUsers")
	 public User updateUsers(@RequestBody User user) {
		User updatedUser = userService.updateUser(user);
		return updatedUser;
	}
	
	
}