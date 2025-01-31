package com.faceboot.post_service.PostServices;

import com.faceboot.post_service.PostDTO.PostResponseDTO;
import com.faceboot.post_service.PostEntities.PostEntity;
import com.faceboot.post_service.PostMapper.PostMapper;
import com.faceboot.post_service.PostRepositories.PostRepository;
import com.faceboot.post_service.client.MediaClient;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class PostService implements PostServiceInterface {


    private PostRepository postRepository;
    private PostMapper postMapper;
    private MediaClient mediaClient;

    @Override
    public List<PostResponseDTO> getallposts() {
        return postRepository.findAll().stream().map(
                postMapper::postEntityToPostResponseDTO
        ).toList();
    }

    @Override
    public PostResponseDTO getPostById(String post_id) {
        PostEntity postEntity = postRepository.findById(post_id).orElse(null);
        if(postEntity!=null){
            return postMapper.postEntityToPostResponseDTO(postEntity);
        }
        return null;
    }

    @Override
    public List<PostResponseDTO> getPostsByUserId(Long userId) {
        return postRepository.findAllByUserid(userId).stream().map(
                postMapper::postEntityToPostResponseDTO
        ).toList();
    }

    @Override
    public PostResponseDTO addPost(Long user_id,
                                   String media_type,
                                   String media_content,
                                   MultipartFile file, String archived) throws IOException {


        PostEntity addedPost = PostEntity.builder()
                .userid(user_id)
                .Archived(archived.equals("true"))
                .build();
        addedPost = postRepository.save(addedPost);

      mediaClient.addMedia(user_id, addedPost.getId(), media_type, media_content, file);
        return postMapper.postEntityToPostResponseDTO(addedPost);
    }

    @Override
    @Transactional
    public String deletePost(String post_id) {
        mediaClient.deleteMediaByPostId(post_id);
        postRepository.deleteById(post_id);
        return "Post Deleted Successfully !";
    }

    @Override
    @Transactional
    public List<PostResponseDTO> deletePostsByUserId(Long user_id) {

        List<PostResponseDTO> deleted_posts = postRepository.findAllByUserid(user_id).stream().map(
                postMapper::postEntityToPostResponseDTO
        ).toList();
        for (PostResponseDTO post : deleted_posts) {
            mediaClient.deleteMediaByPostId(post.getId());
        }
        postRepository.deletePostEntitiesByUserid(user_id);
        return deleted_posts;

    }


}
