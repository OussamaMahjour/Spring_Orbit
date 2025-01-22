package com.faceboot.comment_service.mapper;

import com.faceboot.comment_service.dto.CommentRequestDTO;
import com.faceboot.comment_service.dto.CommentResponseDTO;
import com.faceboot.comment_service.entity.Comment;

public interface CommentMapperInterface  {
    public CommentResponseDTO commentToCommentResponseDTO(Comment comment);
    public Comment commentRequestDTOToComment(CommentRequestDTO commentRequestDTO);

}
