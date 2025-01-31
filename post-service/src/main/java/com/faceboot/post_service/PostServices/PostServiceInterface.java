package com.faceboot.post_service.PostServices;


import com.faceboot.post_service.PostDTO.PostRequestDTO;
import com.faceboot.post_service.PostDTO.PostResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PostServiceInterface {

    List<PostResponseDTO> getallposts();

    PostResponseDTO getPostById(String post_id);

    List<PostResponseDTO> getPostsByUserId(Long userId);

    PostResponseDTO addPost(Long user_id,  String media_type, String media_content,
                            MultipartFile file, String archived) throws IOException;

    String deletePost(String post_id);

    List<PostResponseDTO> deletePostsByUserId(Long user_id);
}
