package com.faceboot.authentification_service.service;

import com.faceboot.authentification_service.client.UserClient;
import com.faceboot.authentification_service.dto.UserCheckCredentialsDTO;
import com.faceboot.authentification_service.dto.UserRequestDTO;
import com.faceboot.authentification_service.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthService implements AuthServiceInterface{
    private final UserClient userClient;

    @Override
    public boolean login(UserCheckCredentialsDTO userCheckCredentialsDTO) {

        return userClient.checkCredentials(userCheckCredentialsDTO);
    }

    @Override
    public User register(UserRequestDTO userRequestDTO) {
        return null;
    }
}
