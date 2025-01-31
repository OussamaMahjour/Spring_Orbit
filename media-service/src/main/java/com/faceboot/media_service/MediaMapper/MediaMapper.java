package com.faceboot.media_service.MediaMapper;

import com.faceboot.media_service.MediaDTO.MediaRequestDTO;
import com.faceboot.media_service.MediaDTO.MediaResponseDTO;
import com.faceboot.media_service.MediaEntities.MediaEntity;
import com.faceboot.media_service.client.PostClient;
import com.faceboot.media_service.model.Post;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MediaMapper implements MediaMapperInterface{
    PostClient postClient;
    @Override
    public MediaRequestDTO toMediaRequestDTO(MediaEntity media) {
        return MediaRequestDTO.builder()
                .postId(media.getPostId())
                .type(media.getType())
                .path(media.getPath())
                .content(media.getContent())
                .build();

    }

    @Override
    public MediaResponseDTO toMediaResponseDTO(MediaEntity media) {
        Post post = postClient.getPostById(media.getPostId());
        return MediaResponseDTO.builder()
                .id(media.getId())
                .post(post)
                .type(media.getType())
                .content(media.getContent())
                .path(media.getPath())
                .build();
    }

    @Override
    public MediaEntity toMediaEntity(MediaRequestDTO mediaRequestDTO) {
        return MediaEntity.builder()
                .postId(mediaRequestDTO.getPostId())
                .type(mediaRequestDTO.getType())
                .path(mediaRequestDTO.getPath())
                .content(mediaRequestDTO.getContent())
                .build();
    }
}
