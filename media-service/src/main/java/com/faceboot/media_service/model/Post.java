package com.faceboot.media_service.model;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class Post{

    private String id;
    private User user;
    private LocalDateTime createAt;
    private boolean Archived;

}