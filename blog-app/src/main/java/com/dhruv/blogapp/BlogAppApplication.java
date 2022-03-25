package com.dhruv.blogapp;

import com.dhruv.blogapp.repositories.BlogRepo;
import com.dhruv.blogapp.service.BlogService;
import com.dhruv.blogapp.service.impl.BlogServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogAppApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(BlogAppApplication.class, args);
	}

}
