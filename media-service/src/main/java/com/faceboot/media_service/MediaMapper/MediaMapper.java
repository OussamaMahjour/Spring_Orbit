package com.faceboot.media_service.MediaMapper;

import com.faceboot.media_service.MediaDTO.MediaRequestDTO;
import com.faceboot.media_service.MediaDTO.MediaResponseDTO;
import com.faceboot.media_service.MediaEntities.MediaEntity;
import org.springframework.stereotype.Component;

@Component
public class MediaMapper implements MediaMapperInterface{
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
        return MediaResponseDTO.builder()
                .id(media.getId())
                .postId(media.getPostId())
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
