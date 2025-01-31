package com.faceboot.post_service.PostControllers;

import com.faceboot.post_service.PostDTO.PostRequestDTO;
import com.faceboot.post_service.PostDTO.PostResponseDTO;
import com.faceboot.post_service.PostServices.PostServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostServiceInterface postService;

    @GetMapping("/all")
    public ResponseEntity<List<PostResponseDTO>> getAllPosts() {

        return ResponseEntity.ok()
                .body(postService.getallposts());
    }

    @GetMapping("/{post_id}")
    public ResponseEntity<PostResponseDTO> getPostById(@PathVariable String post_id) {
        return ResponseEntity.ok().body(postService.getPostById(post_id));
    }

    @GetMapping("/user/{user_id}")
    public ResponseEntity<List<PostResponseDTO>> getAllPostsByUserId(@PathVariable Long user_id) {
        return ResponseEntity.ok().body(postService.getPostsByUserId(user_id));
    }

    @PostMapping("/add")
    public ResponseEntity<PostResponseDTO> addPost(@RequestParam("user_id") Long user_id,

                                   @RequestParam("type") String media_type,
                                   @RequestParam("content") String media_content,
                                   @RequestParam("media") MultipartFile file,
                                   @RequestParam("archived") String archived) throws IOException {

        System.out.println(user_id);
        return ResponseEntity.ok()
                .body(
                postService.addPost(
                        user_id,
                        media_type,
                        media_content,
                        file,
                        archived));

    }





    @DeleteMapping("/delete/user/{user_id}")
    public ResponseEntity<List<PostResponseDTO>> deletePostById(@PathVariable Long user_id) {
        return ResponseEntity.ok().body(postService.deletePostsByUserId(user_id));
    }

    @DeleteMapping("/delete/{post_id}")
    public ResponseEntity<String> deletePost(@PathVariable String post_id) {
        return ResponseEntity.ok().body(postService.deletePost(post_id));
    }
}
