package com.faceboot.user_service.user_service.dtos;

import com.faceboot.user_service.user_service.model.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserUpdateDTO {

    @NotBlank(message = "Name is required")
    private String name;
    private Gender gender;
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    private Boolean verified;
}
