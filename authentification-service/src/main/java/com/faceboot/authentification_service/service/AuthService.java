package com.faceboot.authentification_service.service;

import com.faceboot.authentification_service.client.UserClient;
import com.faceboot.authentification_service.dto.UserCheckCredentialsDTO;
import com.faceboot.authentification_service.dto.UserRequestDTO;
import com.faceboot.authentification_service.model.User;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Component;

import org.springframework.web.client.RestTemplate;



@Component
@AllArgsConstructor
public class AuthService implements AuthServiceInterface{
    private final UserClient userClient;


    @Override
    public User login(UserCheckCredentialsDTO userCheckCredentialsDTO) {

        if(userClient.checkCredentials(userCheckCredentialsDTO)){
            return userClient.getUserByEmail(userCheckCredentialsDTO.email());
        }
        return null;
    }

    @Override
    public User register(UserRequestDTO userRequestDTO) {
        return null;
    }


}
