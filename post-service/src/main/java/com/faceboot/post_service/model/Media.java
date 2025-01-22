package com.faceboot.post_service.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class Media {
    private String id;
    private String postId;
    private String type;
    private String content;
    private String path;
}