package com.faceboot.post_service.PostEntities;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
@Document(value = "post")
public class PostEntity {
    @Id
    private String id;
    private Long userid;
    private LocalDateTime createAt;
    private boolean Archived;

}

