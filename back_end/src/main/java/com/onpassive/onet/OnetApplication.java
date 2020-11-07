package com.onpassive.onet;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.onpassive.onet.config.FileStorageProperties;

@SpringBootApplication
//@ComponentScan(basePackages = "com.onpassive.onet.*")
@EnableJpaAuditing
@EnableConfigurationProperties({ FileStorageProperties.class}) // To enable the ConfigurationProperties feature
public class OnetApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(OnetApplication.class, args);
	}
	
	@Bean
	public CorsFilter corsFilter() {
	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    final CorsConfiguration config = new CorsConfiguration();
	    config.setAllowCredentials(true);
	    config.setAllowedOrigins(Arrays.asList("https://opnetqaapi.onpassive.com", "https://opnetqaui.onpassive.com"));
	    config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept","Authorization"));
	    config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
	    source.registerCorsConfiguration("/**", config);
	    return new CorsFilter(source);
	}

}
