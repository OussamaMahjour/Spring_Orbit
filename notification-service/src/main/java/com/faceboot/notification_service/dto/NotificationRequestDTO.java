package com.faceboot.notification_service.dto;

import com.faceboot.notification_service.entity.Type;

public record NotificationRequestDTO(Type type, Long destination,String message) {
}
