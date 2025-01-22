package com.faceboot.comment_service.model;


import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class Post {
    private String id;
    private User user;
    private LocalDateTime createdAt;
    private Long votes;
    private List<Media> medias;
}
