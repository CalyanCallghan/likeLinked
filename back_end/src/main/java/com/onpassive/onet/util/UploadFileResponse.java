package com.onpassive.onet.util;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.onpassive.onet.model.PostDetails;

import lombok.AllArgsConstructor;
import lombok.Data;

// Class is used to return the response from "/uploadFile" and "/uploadMultipleFiles"
@Data
public class UploadFileResponse {
	private LocalDateTime dateTime;
    HttpStatus status;
    private String message;
    private PostDetails postDetails;
    private String fileName;
	public UploadFileResponse(LocalDateTime dateTime, HttpStatus status, String message, PostDetails postDetails) {
		this.dateTime = dateTime;
		this.status = status;
		this.message = message;
		this.postDetails = postDetails;
	}
	
	public UploadFileResponse(LocalDateTime dateTime, HttpStatus status, String message, String fileName) {
		this.dateTime = dateTime;
		this.status = status;
		this.message = message;
		this.fileName = fileName;
	}
}
