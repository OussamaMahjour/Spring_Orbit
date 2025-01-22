package com.faceboot.comment_service.service;

import com.faceboot.comment_service.dto.CommentRequestDTO;
import com.faceboot.comment_service.dto.CommentResponseDTO;

import java.util.List;

public interface CommentServiceInterface  {
    public List<CommentResponseDTO> getAllComments();
    public CommentResponseDTO getCommentById(String id);
    public CommentResponseDTO addComment(CommentRequestDTO commentRequestDTO);
    public String deleteCommentById(String id);
    public String deleteCommentsByPostId(String postId);
    public List<CommentResponseDTO> getAllCommentsByPostId(String postId);
}
