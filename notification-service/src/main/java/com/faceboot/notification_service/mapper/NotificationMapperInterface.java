package com.faceboot.notification_service.mapper;

import com.faceboot.notification_service.dto.NotificationRequestDTO;
import com.faceboot.notification_service.dto.NotificationResponseDTO;
import com.faceboot.notification_service.entity.Notification;

public interface NotificationMapperInterface {
    public NotificationResponseDTO notificationToNotficationResponseDTO(Notification notification);
    public Notification notificationRequestDTOToNotification(NotificationRequestDTO notificationRequestDTO);
}
