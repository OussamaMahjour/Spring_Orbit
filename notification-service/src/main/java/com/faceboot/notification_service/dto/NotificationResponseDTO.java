package com.faceboot.notification_service.dto;

import com.faceboot.notification_service.entity.Type;
import com.faceboot.notification_service.model.User;

public record NotificationResponseDTO(String id, Type type, User destination , String Message) {

}
