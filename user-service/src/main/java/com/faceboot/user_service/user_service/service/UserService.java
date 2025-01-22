package com.faceboot.user_service.user_service.service;

import com.faceboot.user_service.user_service.dtos.PasswordUpdateDTO;
import com.faceboot.user_service.user_service.dtos.UserCreateDTO;
import com.faceboot.user_service.user_service.dtos.UserResponseDTO;
import com.faceboot.user_service.user_service.dtos.UserUpdateDTO;
import com.faceboot.user_service.user_service.mapper.UserMapper;
import com.faceboot.user_service.user_service.model.Gender;
import com.faceboot.user_service.user_service.model.User;
import com.faceboot.user_service.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final UserMapper userMapper;

    public UserResponseDTO createUser(UserCreateDTO dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("User with email " + dto.getEmail() + " already exists.");
        }
        User user = userMapper.userCreateDtoToUser(dto);
        user.setCreatedAt(LocalDateTime.now());
        user.setVerified(false);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        User saved = userRepository.save(user);
        return userMapper.userToUserResponseDto(saved);
    }
    public UserResponseDTO getUserByEmail(String Email) {
        User user = userRepository.findByEmail(Email)
                .orElseThrow(() -> new RuntimeException("User with Email " + Email + " not found."));
        return userMapper.userToUserResponseDto(user);
    }
    public Boolean checkCredentials(String email, String Password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User with Email " + email + " not found."));
        return passwordEncoder.matches(Password, user.getPassword());
    }


    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with ID " + id + " not found."));
        return userMapper.userToUserResponseDto(user);
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::userToUserResponseDto)
                .collect(Collectors.toList());
    }

    public UserResponseDTO updateUser(Long id, UserUpdateDTO dto) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with ID " + id + " not found."));
        User updatedFields = userMapper.userUpdateDtoToUser(dto);
        existingUser.setName(updatedFields.getName());
        existingUser.setGender(updatedFields.getGender());
        existingUser.setEmail(updatedFields.getEmail());
        User savedUser = userRepository.save(existingUser);
        return userMapper.userToUserResponseDto(savedUser);
    }

    public void softDeleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with ID " + id + " not found."));
        user.setDeletedAt(LocalDateTime.now());
        userRepository.save(user);
    }
    public void deleteUsersPermanentlyAfter30Days() {
        LocalDateTime thresholdDate = LocalDateTime.now().minusDays(30);
        List<User> usersToDelete = userRepository.findAllByDeletedAtBefore(thresholdDate);
        if (!usersToDelete.isEmpty()) {
            log.info("Deleting {} users permanently", usersToDelete.size());
            userRepository.deleteAll(usersToDelete);
        } else {
            log.info("No users found for permanent deletion.");
        }
    }

    public void reactivateUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with ID " + id + " not found."));
        if (user.getDeletedAt() != null) {
            user.setDeletedAt(null);
            userRepository.save(user);
        }
    }

    public UserResponseDTO verifyUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with ID " + id + " not found."));
        if (!user.getVerified()) {
            user.setVerified(true);
            userRepository.save(user);
        }
        return userMapper.userToUserResponseDto(user);
    }

    public UserResponseDTO updatePassword(Long id, PasswordUpdateDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with ID " + id + " not found."));
        if (dto.getNewPassword() == null || dto.getNewPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty.");
        }
        user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        User saved = userRepository.save(user);
        return userMapper.userToUserResponseDto(saved);
    }

}
