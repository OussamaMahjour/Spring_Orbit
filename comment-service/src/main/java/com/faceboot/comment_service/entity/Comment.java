package com.faceboot.comment_service.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(value = "comment")
public class Comment {
    @Id
    private String id;
    private String content;
    private LocalDateTime createTime;
    private String postId;
    private Long userId;
}

@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(value = "comment")
public class Comment {
    @Id
    private String id;
    private String content;
    private LocalDateTime createTime;
    private String postId;
    private Long userId;
}
