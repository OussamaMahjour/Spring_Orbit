package com.faceboot.comment_service.service;

import com.faceboot.comment_service.client.MediaClient;
import com.faceboot.comment_service.client.PostClient;
import com.faceboot.comment_service.client.UserClient;
import com.faceboot.comment_service.dto.CommentRequestDTO;
import com.faceboot.comment_service.dto.CommentResponseDTO;
import com.faceboot.comment_service.entity.Comment;
import com.faceboot.comment_service.mapper.CommentMapper;
import com.faceboot.comment_service.mapper.CommentMapperInterface;
import com.faceboot.comment_service.model.Post;
import com.faceboot.comment_service.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService implements CommentServiceInterface {


    private final CommentRepository commentRepository;
    private final CommentMapperInterface commentMapper;



    @Override
    public List<CommentResponseDTO> getAllComments(){
        return commentRepository.findAll().stream().map(
                commentMapper::commentToCommentResponseDTO
        ).toList() ;
    }

    @Override
    public CommentResponseDTO getCommentById(String id) {
        Comment comment = commentRepository.findCommentById(id);
        return commentMapper.commentToCommentResponseDTO(comment);

    }

    @Override
    public String deleteCommentById(String id){
        if(commentRepository.existsById(id)){
            commentRepository.deleteById(id);
            return "deleted succefully";
        }else{
            return "comment doesn't exist";
        }
    }

    @Override
    public String deleteCommentsByPostId(String postId) {
        List<Comment> comments = commentRepository.findCommentsByPostId(postId);
        if(comments.isEmpty()){
            return "post have no comments or doesn't exist";
        }
        commentRepository.deleteCommentsByPostId(postId);
        return "comments deleted";
    }

    @Override
    public List<CommentResponseDTO> getAllCommentsByPostId(String postId) {
        return commentRepository.findCommentsByPostId(postId).stream().map(
                commentMapper::commentToCommentResponseDTO
        ).toList();
    }

    @Override
    public CommentResponseDTO addComment(CommentRequestDTO commentRequestDTO){
        Comment comment = commentMapper.commentRequestDTOToComment(commentRequestDTO);
        commentRepository.save(comment);
        return commentMapper.commentToCommentResponseDTO(comment);
    }








}
