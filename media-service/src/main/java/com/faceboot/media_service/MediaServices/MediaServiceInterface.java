package com.faceboot.media_service.MediaServices;

import com.faceboot.media_service.MediaDTO.MediaResponseDTO;
import com.faceboot.media_service.MediaEntities.MediaEntity;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface MediaServiceInterface {
    List<Optional<MediaResponseDTO>> findAll();
    Optional<MediaResponseDTO> findById(String id);
    List<Optional<MediaResponseDTO>> findByPostId(String post_id);
    Optional<MediaEntity> addMedia(String user_id,
                                   String postId,
                                   String media_type,
                                   String media_content,
                                   MultipartFile file) throws IOException ;
    String deleteBypostId(String user_id, String post_id);

    ResponseEntity<Resource> getMediaFile(String post_id);
}
