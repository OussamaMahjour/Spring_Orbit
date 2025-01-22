package com.faceboot.media_service.MediaMapper;

import com.faceboot.media_service.MediaDTO.MediaRequestDTO;
import com.faceboot.media_service.MediaDTO.MediaResponseDTO;
import com.faceboot.media_service.MediaEntities.MediaEntity;
import org.springframework.stereotype.Component;

@Component
public interface MediaMapperInterface {
    MediaRequestDTO toMediaRequestDTO(MediaEntity media);
    MediaResponseDTO toMediaResponseDTO(MediaEntity media);
    MediaEntity toMediaEntity(MediaRequestDTO mediaRequestDTO);
}
