package com.faceboot.media_service.MediaServices;

import com.faceboot.media_service.MediaDTO.MediaRequestDTO;
import com.faceboot.media_service.MediaDTO.MediaResponseDTO;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface MediaServiceInterface {
    List<MediaResponseDTO> findAll();
    List<MediaResponseDTO> findByPostId(String post_id);
    MediaResponseDTO addMedia(String user_id,
                                        String postId,
                                        String media_type,
                                        String media_content,
                                        MultipartFile file) throws IOException ;
    boolean deleteBypostId(String post_id);

    Resource getMediaPost(String id);
}
