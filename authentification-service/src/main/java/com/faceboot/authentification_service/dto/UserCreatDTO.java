package com.faceboot.authentification_service.dto;

public record UserCreatDTO (
    String name,
    String gender,
    String email,
    String password
){}
