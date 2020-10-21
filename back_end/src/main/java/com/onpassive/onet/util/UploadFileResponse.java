package com.onpassive.onet.util;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

// Class is used to return the response from "/uploadFile" and "/uploadMultipleFiles"
@Data
@AllArgsConstructor
public class UploadFileResponse {
	private LocalDateTime dateTime;
    HttpStatus status;
    private String message;
}
