package com.faceboot.authentification_service.dto;

import java.time.LocalDateTime;

public record UserRequestDTO(
        Long id,
        String name,
        String gender,
        String email,
        Boolean verified,
        LocalDateTime createdAt,
        LocalDateTime deletedAt
) {
}
