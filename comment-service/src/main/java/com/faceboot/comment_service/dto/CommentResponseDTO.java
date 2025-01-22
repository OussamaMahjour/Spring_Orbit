package com.faceboot.comment_service.dto;

import com.faceboot.comment_service.model.Post;
import com.faceboot.comment_service.model.User;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;

public record CommentResponseDTO(@Nullable String id, User user, Post post, String content, LocalDateTime createTime) {
}
