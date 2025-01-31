package com.faceboot.media_service.MediaDTO;

import com.faceboot.media_service.MediaEntities.MediaType;
import com.faceboot.media_service.client.PostClient;
import com.faceboot.media_service.model.Post;
import lombok.*;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
@Component
public class MediaResponseDTO {
    private String id;
    private Post post;
    private MediaType type;
    private String content;
    private String path;



}