package com.onpassive.onet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import com.onpassive.onet.config.FileStorageProperties;

@SpringBootApplication
@ComponentScan(basePackages = "com.onpassive.onet.*")
@EnableConfigurationProperties({ FileStorageProperties.class}) // To enable the ConfigurationProperties feature
public class OnPassiveNetApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnPassiveNetApplication.class, args);
	}

}
