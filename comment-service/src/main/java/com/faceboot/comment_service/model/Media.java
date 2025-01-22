package com.faceboot.comment_service.model;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class Media {
    private Long id;
    private Long postId;
    private String type;
    private String content;
    private String path;
}
