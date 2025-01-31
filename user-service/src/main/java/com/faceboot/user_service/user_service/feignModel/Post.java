package com.faceboot.user_service.user_service.feignModel;

import com.faceboot.user_service.user_service.dtos.UserResponseDTO;

import java.time.LocalDateTime;

public class Post{

    private String id;
    private UserResponseDTO user;
    private LocalDateTime createAt;
    private boolean Archived;

}