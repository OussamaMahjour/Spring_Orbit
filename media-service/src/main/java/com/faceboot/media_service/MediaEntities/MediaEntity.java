package com.faceboot.media_service.MediaEntities;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;


@AllArgsConstructor
//@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
@Document(value = "media")
public class MediaEntity {
    @Id
    private String id;
    private String postId;
    private MediaType type;
    private String content;
    private String path;

    @Builder
    public MediaEntity(String postId, MediaType type, String content, String path) {
        this.id = UUID.randomUUID().toString();
        this.postId = postId;
        this.type = type;
        this.content = content;
        this.path = path;

    }
    public MediaEntity() {
        this.id = UUID.randomUUID().toString();
    }

}
