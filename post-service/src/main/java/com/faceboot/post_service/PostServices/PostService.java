package com.faceboot.post_service.PostServices;

import com.faceboot.post_service.PostDTO.PostResponseDTO;
import com.faceboot.post_service.PostEntities.PostEntity;
import com.faceboot.post_service.PostMapper.PostMapper;
import com.faceboot.post_service.PostRepositories.PostRepository;
import com.faceboot.post_service.client.MediaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class PostService implements PostServiceInterface {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private MediaClient mediaClient;

    @Override
    public List<PostResponseDTO> getallposts() {
        return postRepository.findAll().stream().map(
                postMapper::postEntityToPostResponseDTO
        ).toList();
    }

    @Override
    public PostResponseDTO getPostById(String post_id) {
        return postMapper.postEntityToPostResponseDTO(postRepository.findById(post_id).orElse(null));
    }

    @Override
    public List<PostResponseDTO> getPostsByUserId(Long userId) {
        return postRepository.findAllByUserid(userId).stream().map(
                postMapper::postEntityToPostResponseDTO
        ).toList();
    }

    @Override
    public PostResponseDTO addPost(String user_id, String postId, String media_type, String media_content,
                                   MultipartFile file, String archived) throws IOException {
        PostEntity addedPost = postRepository.save(PostEntity.builder()
                        .userid(Long.valueOf(user_id))
                        .Archived(archived.equals("true"))
                        .build());
        mediaClient.addMedia(Long.valueOf(user_id), postId, media_type, media_content, file);
        return postMapper.postEntityToPostResponseDTO(addedPost);
    }

    @Override
    public String deletePost(String post_id) {
        postRepository.deleteById(post_id);
        return "Post Deleted Successfully !";
    }

    @Override
    public List<PostResponseDTO> deletePostsByUserId(Long user_id) {

        List<PostResponseDTO> deleted_posts = postRepository.findAllByUserid(user_id).stream().map(
                postMapper::postEntityToPostResponseDTO
        ).toList();
        for (PostResponseDTO post : deleted_posts) {
            mediaClient.deleteMediaByPostId(user_id.toString(), post.getId());
        }
        postRepository.deletePostEntitiesByUserid(user_id);
        return deleted_posts;
    }


}
