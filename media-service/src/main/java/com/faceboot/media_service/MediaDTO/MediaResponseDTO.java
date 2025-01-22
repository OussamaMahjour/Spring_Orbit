package com.faceboot.media_service.MediaDTO;

import com.faceboot.media_service.MediaEntities.MediaTypeEnum;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class MediaResponseDTO {
    private String id;
    private String postId;
    private MediaTypeEnum type;
    private String content;
    private String path;
}