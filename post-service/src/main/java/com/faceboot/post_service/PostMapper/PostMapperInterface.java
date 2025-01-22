package com.faceboot.post_service.PostMapper;

import com.faceboot.post_service.PostDTO.PostRequestDTO;
import com.faceboot.post_service.PostDTO.PostResponseDTO;
import com.faceboot.post_service.PostEntities.PostEntity;
import org.springframework.stereotype.Component;

@Component
public interface PostMapperInterface {
    PostEntity postRequestDTOToPostEntity(PostRequestDTO postRequestDTO);
    PostResponseDTO postEntityToPostResponseDTO(PostEntity postEntity);
}
