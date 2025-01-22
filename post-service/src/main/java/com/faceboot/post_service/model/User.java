package com.faceboot.post_service.model;

import lombok.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class User {
    private Long id;
    private String name;
    private String gender;
    private String email;
    private Boolean verified;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
}
