package com.faceboot.authentification_service.controller;


import com.faceboot.authentification_service.client.UserClient;
import com.faceboot.authentification_service.dto.UserCheckCredentialsDTO;
import com.faceboot.authentification_service.dto.UserRequestDTO;
import com.faceboot.authentification_service.service.AuthServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final UserClient userClient;
    private AuthServiceInterface authService;



    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody UserCheckCredentialsDTO checkCredentialsDTO) {

        if(authService.login(checkCredentialsDTO)){

        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRequestDTO userDto) {

        ResponseEntity<String> keycloakResponse = authService.createUser(userDto);

        if (keycloakResponse.getStatusCode() == HttpStatus.CREATED) {
           // userClient.add(userDto); // Store non-auth data
            return ResponseEntity.ok("User registered successfully");
        }

        return ResponseEntity.status(keycloakResponse.getStatusCode()).body(keycloakResponse.getBody());
    }
}
