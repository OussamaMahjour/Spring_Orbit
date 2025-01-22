package com.faceboot.media_service.MediaDTO;

import com.faceboot.media_service.MediaEntities.MediaType;
import lombok.*;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class MediaRequestDTO {
    private String postId;
    private MediaType type;
    private String content;
    private String path;
}
