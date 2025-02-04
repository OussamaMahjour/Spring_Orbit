package com.faceboot.authentification_service.service;

import com.faceboot.authentification_service.dto.UserCheckCredentialsDTO;
import com.faceboot.authentification_service.dto.UserRequestDTO;
import com.faceboot.authentification_service.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


public interface AuthServiceInterface {

    public User login(UserCheckCredentialsDTO userCheckCredentialsDTO);

    public User register(UserRequestDTO userRequestDTO);


}
