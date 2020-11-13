package com.onpassive.onet.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onpassive.onet.model.HomeRequestModel;
import com.onpassive.onet.model.PostDetails;
import com.onpassive.onet.repository.PostRepository;
import com.onpassive.onet.service.PostStorageService;
import com.onpassive.onet.util.UploadFileResponse;

//@CrossOrigin(origins = {"https://opnetqaapi.onpassive.com","https://opnetqaui.onpassive.com"})
//@CrossOrigin("*")
@RestController
@RequestMapping("/file")
public class PostController {
	private static final Logger logger = LoggerFactory.getLogger(PostController.class);
	@Autowired
	HttpServletRequest request;
	@Autowired
	private PostStorageService postStorageService;
	
	@Autowired
	private PostRepository postRepository;
	

	// For uploading files to File system
	@SuppressWarnings("static-access")
	@PostMapping("/uploadFile")
	public ResponseEntity<UploadFileResponse> uploadFile(@RequestParam("files") MultipartFile[] files,
			@RequestParam("data") String data) {
		//postRepository.
		logger.debug("data---uploadFile------>"+data);
		String message = "";
		LocalDateTime dateTime = null;
		//List<String> fileNames = new ArrayList<>();
		int postId = 0;
		PostDetails postDetails = null;
		
		try {
			ObjectMapper obj = new ObjectMapper();
			HomeRequestModel model = obj.readValue(data, HomeRequestModel.class);

			//Arrays.asList(files).stream().forEach(file -> {
			//postId  = postStorageService.storeFile(files[0], model);
			if(files.length>0)
				postId  = postStorageService.storeFile(files[0], model);
					//fileNames.add(files[0].getOriginalFilename());
				//});
				else
				postId = postStorageService.saveData(model);
				//fileNames.add(files[0].getOriginalFilename());
			//});
			message = "Uploaded the file successfully.";
			postDetails = postRepository.specificPostData(postId);
			
			// return ResponseEntity.status(HttpStatus.OK).body(message);
			return ResponseEntity.ok(new UploadFileResponse(dateTime.now(), HttpStatus.OK, message,postDetails));
		} catch (Exception e) {
			message = "Fail to upload files!!!";
			logger.error("error occured at uploadFile---->"+e.getMessage());
			// return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
			return ResponseEntity.ok(new UploadFileResponse(dateTime.now(), HttpStatus.EXPECTATION_FAILED, message,postDetails));
		}

	}
	

	// Downloading file based on the fileName
	@GetMapping("/downloadFile/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
		String contentType = null;
		logger.debug("fileName-----downloadFile------>"+fileName);
		// Load file as Resource
		Resource resource = postStorageService.loadFileAsResource(fileName);
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			ex.getMessage();
		}
		// If type could not be determined then assigning the default content type
		if (contentType == null) {
			contentType = "application/octet-stream";
		}
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
	
	@GetMapping("/getAllPosts/{type}")
	public ResponseEntity<List<PostDetails>> getAllPosts(@PathVariable String type) {
		logger.debug("type---getAllPosts------>"+type);
		List<PostDetails> data= postStorageService.getAllPosts(type);
		return new ResponseEntity<List<PostDetails>>(data, new HttpHeaders(), HttpStatus.OK);
	}
	@GetMapping("/getAllPostsWithCommentsAndLikes/{type}")
	public ResponseEntity<List<PostDetails>> getAllPostsWithCommentsAndLikes(@PathVariable String type) {
		logger.debug("type---getAllPosts------>"+type);
		List<PostDetails> postDetails = new ArrayList<>();
		List<Object[]> allPosts =postRepository.allthePostsDataWithCountAndLike(type);
		for(Object[] postDAta : allPosts)
	    {	
			System.err.println(postDAta[0]);
			System.err.println(postDAta[1]);
			System.err.println(postDAta[2]);
			System.err.println(postDAta[3]);
			System.err.println(postDAta[4]);
			System.err.println(postDAta[5]);
			System.err.println(postDAta[6]);
			System.err.println(postDAta[7]);
			System.err.println(postDAta[8]);
			System.err.println(postDAta[9]);
			System.err.println("------------");
			postDetails.add(new PostDetails(((Integer)postDAta[0]),((String)postDAta[1]),((String)postDAta[2]),((String)postDAta[3]),((String)postDAta[4]),((String)postDAta[5]),((String)postDAta[6]),((String)postDAta[7]),(((BigInteger)postDAta[8]).longValue()),(((BigInteger)postDAta[9]).longValue())));
		}
		return new ResponseEntity<List<PostDetails>>(postDetails, new HttpHeaders(), HttpStatus.OK);
	}

}
