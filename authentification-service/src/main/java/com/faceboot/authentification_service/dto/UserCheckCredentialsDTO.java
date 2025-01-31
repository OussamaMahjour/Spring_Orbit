package com.faceboot.authentification_service.dto;

public record UserCheckCredentialsDTO (
        String email,
        String password
){
}
