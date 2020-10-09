package com.onpassive.onet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.onpassive.onet.config.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({ FileStorageProperties.class}) // To enable the ConfigurationProperties feature
public class FileUploadServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileUploadServiceApplication.class, args);
	}

}
