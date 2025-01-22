package com.faceboot.user_service.user_service.mapper;

import com.faceboot.user_service.user_service.dtos.UserCreateDTO;
import com.faceboot.user_service.user_service.dtos.UserResponseDTO;
import com.faceboot.user_service.user_service.dtos.UserUpdateDTO;
import com.faceboot.user_service.user_service.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "verified", ignore = true)
    User userCreateDtoToUser(UserCreateDTO dto);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "verified", ignore = true)
    User userUpdateDtoToUser(UserUpdateDTO dto);


    UserResponseDTO userToUserResponseDto(User user);
}
