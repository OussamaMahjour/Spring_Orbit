package com.faceboot.post_service.PostMapper;

import com.faceboot.post_service.PostDTO.PostRequestDTO;
import com.faceboot.post_service.PostDTO.PostResponseDTO;
import com.faceboot.post_service.PostEntities.PostEntity;
import com.faceboot.post_service.client.UserClient;
import com.faceboot.post_service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostMapper implements PostMapperInterface {

    @Autowired
    private UserClient userClient;

    @Override
    public PostEntity postRequestDTOToPostEntity(PostRequestDTO postRequestDTO) {
        PostEntity postEntity = PostEntity.builder()
                .userid(postRequestDTO.getUserid())
                .Archived(postRequestDTO.isArchived())
                .createAt(postRequestDTO.getCreateAt())
                .build();
        return postEntity;
    }

    @Override
    public PostResponseDTO postEntityToPostResponseDTO(PostEntity postEntity) {
        User user = userClient.getUserById(postEntity.getUserid());
        PostResponseDTO postResponseDTO = PostResponseDTO.builder()
                .id(postEntity.getId())
                .user(user)
                .createAt(postEntity.getCreateAt())
                .Archived(postEntity.isArchived())
                .build();
        return postResponseDTO;
    }
}
