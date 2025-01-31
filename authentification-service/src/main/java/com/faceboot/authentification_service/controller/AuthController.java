package com.faceboot.authentification_service.controller;


import com.faceboot.authentification_service.client.UserClient;
import com.faceboot.authentification_service.dto.UserCheckCredentialsDTO;
import com.faceboot.authentification_service.service.AuthServiceInterface;
import lombok.AllArgsConstructor;
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
        return ResponseEntity.ok(authService.login(checkCredentialsDTO));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestPart String email, @RequestBody String password) {
        return null;
    }
}
