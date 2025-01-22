package com.faceboot.user_service.user_service.dtos;

import com.faceboot.user_service.user_service.model.Gender;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class UserResponseDTO {
    private Long id;
    private String name;
    private Gender gender;
    private String email;
    private Boolean verified;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
}
