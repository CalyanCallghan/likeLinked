package com.onpassive.onet.util;

import lombok.AllArgsConstructor;
import lombok.Data;

// Class is used to return the response from "/uploadFile" and "/uploadMultipleFiles"
@Data
@AllArgsConstructor
public class UploadFileResponse {
	private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
	public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, long size) {
		super();
		this.fileName = fileName;
		this.fileDownloadUri = fileDownloadUri;
		this.fileType = fileType;
		this.size = size;
	}
}
