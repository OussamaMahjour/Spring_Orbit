package com.faceboot.comment_service.dto;

import com.faceboot.comment_service.model.Post;
import com.faceboot.comment_service.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Value;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;

public record CommentRequestDTO(@Nullable  String id , Long userId, String postId, String content, LocalDateTime createTime) {
    public CommentRequestDTO( Long userId, String postId, String content, LocalDateTime createTime) {
        this("", userId,postId, content, createTime);
    }
}
