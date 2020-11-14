package com.onpassive.onet.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

//Automatically binding file storage properties to a POJO class
@Data
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
	
    private String uploadDir;
    private String uploadDirCvs;
}
