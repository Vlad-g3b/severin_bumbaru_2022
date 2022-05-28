package com.antii.IntershipREST;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.antii.IntershipREST.helper.FileStorageProperites;

@SpringBootApplication
@EnableConfigurationProperties({
    FileStorageProperites.class
})
@ComponentScan(basePackages = {"com.antii.IntershipREST"})
public class IntershipRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntershipRestApplication.class, args);
	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("POST","GET","PUT","DELETE");
			}
		};
	}
//private static final int MAX_UPLOAD_SIZE = 1024 * 1024; //1MB
//	
//	private static final int MAX_IN_MEMORY_SIZE = 1024 * 256; //256KB
//
//	@Bean
//	public MultipartResolver multipartResolver() {
//		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
//		multipartResolver.setMaxUploadSize(MAX_UPLOAD_SIZE);
//		multipartResolver.setMaxInMemorySize(MAX_IN_MEMORY_SIZE);
//		return multipartResolver;
//	}
}
