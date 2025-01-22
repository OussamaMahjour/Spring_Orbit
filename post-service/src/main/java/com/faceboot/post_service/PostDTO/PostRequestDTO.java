package com.faceboot.post_service.PostDTO;

import com.faceboot.post_service.model.User;
import lombok.*;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class PostRequestDTO {

    private Long userid;
    private LocalDateTime createAt;
    private boolean Archived;

}
