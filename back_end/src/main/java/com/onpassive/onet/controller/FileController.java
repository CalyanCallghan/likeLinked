package com.onpassive.onet.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onpassive.onet.model.HomeRequestModel;
import com.onpassive.onet.service.FileStorageService;
import com.onpassive.onet.util.UploadFileResponse;

//@CrossOrigin(origins = {"https://opnetqaapi.onpassive.com","https://opnetqaui.onpassive.com"})
@CrossOrigin(origins = {"http://localhost:8080","http://localhost:4200"})
@RestController
@RequestMapping("/file")
public class FileController {
	@Autowired
	HttpServletRequest request;
	@Autowired
	private FileStorageService fileStorageService;

	// For uploading files to File system
	@SuppressWarnings("static-access")
	@PostMapping("/uploadFile")
	public ResponseEntity<UploadFileResponse> uploadFile(@RequestParam("files") MultipartFile[] files,
			@RequestParam("data") String data) {
		String message = "";
		LocalDateTime dateTime = null;
		List<String> fileNames = new ArrayList<>();
		try {
			System.err.println("data--->"+data);
			ObjectMapper obj = new ObjectMapper();
			HomeRequestModel model = obj.readValue(data, HomeRequestModel.class);

			Arrays.asList(files).stream().forEach(file -> {
				fileStorageService.storeFile(file, model);
				fileNames.add(file.getOriginalFilename());
			});
			message = "Uploaded the files. ";
			// return ResponseEntity.status(HttpStatus.OK).body(message);
			return ResponseEntity.ok(new UploadFileResponse(dateTime.now(), HttpStatus.OK, message));
		} catch (Exception e) {
			message = "Fail to upload files!!!";
			// return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
			return ResponseEntity.ok(new UploadFileResponse(dateTime.now(), HttpStatus.EXPECTATION_FAILED, message));
		}

	}

	// Downloading file based on the fileName
	@GetMapping("/downloadFile/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
		String contentType = null;
		// Load file as Resource
		Resource resource = fileStorageService.loadFileAsResource(fileName);
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

}
