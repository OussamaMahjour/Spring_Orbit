package com.faceboot.media_service.MediaControllers;
import com.faceboot.media_service.MediaDTO.MediaResponseDTO;
import com.faceboot.media_service.MediaServices.MediaServiceInterface;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/media")
public class MediaController {
    MediaServiceInterface mediaService;
    public MediaController(MediaServiceInterface mediaService) {
        this.mediaService = mediaService;
    }
    @GetMapping("/all")
    public List<Optional<MediaResponseDTO>> getAllMedia() {
        return mediaService.findAll();
    }

    @GetMapping("/post/{post_id}")
    public List<Optional<MediaResponseDTO>> findPostMedia(@PathVariable String post_id) {
        return mediaService.findByPostId(post_id);

    }

    @PostMapping("/upload")
    public Optional<MediaResponseDTO> uploadPost(@RequestParam("user_id") String user_id,
                                                 @RequestParam("post_id") String postId,
                                                 @RequestParam("type") String media_type,
                                                 @RequestParam("content") String media_content,
                                                 @RequestParam("media") MultipartFile file) throws IOException {
        return mediaService.addMedia(user_id, postId, media_type, media_content, file);
    }
    @DeleteMapping("/delete/postid/{post_id}/{user_id}")
    public String deletePostById(@PathVariable String post_id, @PathVariable String user_id) {
        return mediaService.deleteBypostId(post_id, user_id);
    }

    /*
    @DeleteMapping("/delete/userid/{userid}")
    */

    @GetMapping("/{media}")
    public ResponseEntity<Resource> getMedia(@PathVariable String media) {
        try{
        Path mediaFile = Paths.get("/media/storage/User_1/Post_1");
        Path filePath = mediaFile.resolve("9zbor.jpeg").normalize();
        Resource resource = new UrlResource(filePath.toUri());

        if (resource.exists()) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    } catch (Exception ex) {
        return ResponseEntity.internalServerError().build();
    }
    }

}
