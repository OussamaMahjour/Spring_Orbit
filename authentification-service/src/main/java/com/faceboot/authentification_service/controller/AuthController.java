package com.faceboot.authentification_service.controller;


import com.faceboot.authentification_service.client.UserClient;
import com.faceboot.authentification_service.dto.UserCheckCredentialsDTO;
import com.faceboot.authentification_service.dto.UserCreatDTO;
import com.faceboot.authentification_service.dto.UserRequestDTO;
import com.faceboot.authentification_service.model.User;
import com.faceboot.authentification_service.service.AuthServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final UserClient userClient;
    private AuthServiceInterface authService;



    @PostMapping("/login")
    public ResponseEntity<UserClient> login(@RequestBody UserCheckCredentialsDTO checkCredentialsDTO) {

        return new ResponseEntity(authService.login(checkCredentialsDTO), HttpStatusCode.valueOf(200));
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserCreatDTO userCreatDTO) {

        return ResponseEntity.status(200).body(userClient.addUser(userCreatDTO));
    }
}
