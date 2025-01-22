package com.faceboot.notification_service.mapper;


import com.faceboot.notification_service.client.UserClient;
import com.faceboot.notification_service.dto.NotificationRequestDTO;
import com.faceboot.notification_service.dto.NotificationResponseDTO;
import com.faceboot.notification_service.entity.Notification;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationMapper implements NotificationMapperInterface {

    private final UserClient userClient;

    @Override
    public NotificationResponseDTO notificationToNotficationResponseDTO(Notification notification) {
        return new NotificationResponseDTO(
                notification.getId(),
                notification.getType(),
                userClient.getUserById(notification.getDestination()),
                notification.getMessage()
        );
    }

    @Override
    public Notification notificationRequestDTOToNotification(NotificationRequestDTO notificationRequestDTO) {
        return Notification.builder()
                .destination(notificationRequestDTO.destination())
                .type(notificationRequestDTO.type())
                .message(notificationRequestDTO.message())
                .build();
    }
}
