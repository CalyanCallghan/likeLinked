package com.onpassive.onet.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

//Automatically binding file storage properties to a POJO class
@Data
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
    private String uploadDir;

	public String getUploadDir() {
		return uploadDir;
	}

	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}
    
}
