package com.faceboot.authentification_service.dto;

import java.time.LocalDateTime;

public record UserRequestDTO(
        String name,
        String gender,
        String email,
        String password


) {
}
