package com.faceboot.notification_service.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "notification")
public class Notification {
    @Id
    private String id;
    private Type type;
    private Long destination;
    private String message;
}
