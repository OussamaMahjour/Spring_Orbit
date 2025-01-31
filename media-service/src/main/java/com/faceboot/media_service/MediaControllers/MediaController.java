package com.faceboot.media_service.MediaControllers;
import com.faceboot.media_service.MediaDTO.MediaResponseDTO;
import com.faceboot.media_service.MediaServices.MediaServiceInterface;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
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
    public List<MediaResponseDTO> getAllMedia() {
        return mediaService.findAll();
    }

    @GetMapping("/post/{post_id}")
    public List<MediaResponseDTO> findPostMedia(@PathVariable String post_id) {
        return mediaService.findByPostId(post_id);

    }

    @PostMapping("/upload")
    public MediaResponseDTO uploadPost(@RequestParam("user_id") String user_id,
                                                 @RequestParam("post_id") String postId,
                                                 @RequestParam("type") String media_type,
                                                 @RequestParam("content") String media_content,
                                                 @RequestParam("media") MultipartFile file) throws IOException {
        return mediaService.addMedia(user_id, postId, media_type, media_content, file);
    }

    @DeleteMapping("/delete/postid/{post_id}")
    public ResponseEntity<String> deletePostById(@PathVariable String post_id) {
        if(mediaService.deleteBypostId(post_id)){
            return ResponseEntity.ok("media deleted successfully");
        }
        else{
            return new ResponseEntity<>("coudn't delete media", HttpStatusCode.valueOf(400));
        }
    }



    @GetMapping("/{media}")
    public ResponseEntity<Resource> getMedia(@PathVariable String media) {
       Resource resource = mediaService.getMediaPost(media);
        if (resource.exists()) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

}
