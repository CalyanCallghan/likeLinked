package com.onpassive.onet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.onpassive.onet.config.FileStorageProperties;

@SpringBootApplication
@ComponentScan(basePackages = "com.onpassive.onet.*")
@EnableConfigurationProperties({ FileStorageProperties.class}) // To enable the ConfigurationProperties feature
public class OnPassiveNetApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(OnPassiveNetApplication.class, args);
		//.allowedOrigins("http://localhost:4200","http://localhost:8080")
	}
	
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedOrigins("https://opnetqaapi.onpassive.com","https://opnetqaui.onpassive.com  ")
                .allowedHeaders("Access-Control-Request-Headers","*");
            }
        };
	}
	

}
