package com.faceboot.user_service.user_service.controller;

import com.faceboot.user_service.user_service.dtos.PasswordUpdateDTO;
import com.faceboot.user_service.user_service.dtos.UserCreateDTO;
import com.faceboot.user_service.user_service.dtos.UserResponseDTO;
import com.faceboot.user_service.user_service.dtos.UserUpdateDTO;
import com.faceboot.user_service.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Validated UserCreateDTO dto) {
        return ResponseEntity.ok(userService.createUser(dto));
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDTO> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }
    @PostMapping("/check-credentials")
    public ResponseEntity<Boolean> checkCredentials(@RequestBody UserCheckPassword dto) {
        return ResponseEntity.ok(userService.checkCredentials(dto.getEmail(), dto.getPassword()));
    }



    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody @Validated UserUpdateDTO dto) {
        return ResponseEntity.ok(userService.updateUser(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> softDeleteUser(@PathVariable Long id) {
        userService.softDeleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/reactivate")
    public ResponseEntity<Void> reactivateUser(@PathVariable Long id) {
        userService.reactivateUser(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/verify")
    public ResponseEntity<UserResponseDTO> verifyUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.verifyUser(id));
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<UserResponseDTO> updatePassword(@PathVariable Long id, @RequestBody @Validated PasswordUpdateDTO dto) {
        return ResponseEntity.ok(userService.updatePassword(id, dto));
    }
}
