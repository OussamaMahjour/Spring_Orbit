package com.faceboot.notification_service.service;

import com.faceboot.notification_service.dto.Email;
import com.faceboot.notification_service.dto.NotificationRequestDTO;
import com.faceboot.notification_service.dto.NotificationResponseDTO;
import com.faceboot.notification_service.dto.SMS;
import com.faceboot.notification_service.entity.Notification;
import org.springframework.boot.actuate.web.exchanges.HttpExchange;

import java.util.List;

public interface NotificationServiceInterface {
    public void sendNotification(NotificationRequestDTO notification);
    public void sendEmail(Email email);
    public void sendSMS(SMS sms);


    void deleteNotifcation(String id);

    public NotificationResponseDTO getNotificatinById(String id);
    public List<NotificationResponseDTO> getNotificationsByUserId(Long userId);
}
