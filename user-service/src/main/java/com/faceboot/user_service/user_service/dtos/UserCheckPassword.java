package com.faceboot.user_service.user_service.dtos;

import lombok.Data;

@Data
public class UserCheckPassword {
    private String email;
    private String password;
}

