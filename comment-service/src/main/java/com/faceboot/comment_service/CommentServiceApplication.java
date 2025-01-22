package com.faceboot.comment_service;

import com.faceboot.comment_service.entity.Comment;
import com.faceboot.comment_service.mapper.CommentMapper;
import com.faceboot.comment_service.repository.CommentRepository;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;


@EnableFeignClients
@SpringBootApplication
public class CommentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommentServiceApplication.class, args);
	}


}