package com.faceboot.notification_service.service;


import com.faceboot.notification_service.api.email.EmailAPI;
import com.faceboot.notification_service.api.sms.SMSAPI;
import com.faceboot.notification_service.dto.Email;
import com.faceboot.notification_service.dto.NotificationRequestDTO;
import com.faceboot.notification_service.dto.NotificationResponseDTO;
import com.faceboot.notification_service.dto.SMS;
import com.faceboot.notification_service.entity.Notification;
import com.faceboot.notification_service.mapper.NotificationMapperInterface;
import com.faceboot.notification_service.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NotificationService implements NotificationServiceInterface{

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    private EmailAPI emailAPI;
    private SMSAPI smsAPI;
    private NotificationRepository notificationRepository;
    private NotificationMapperInterface notificationMapper;

    @Override
    public void sendNotification(NotificationRequestDTO notification) {
       kafkaTemplate.send("notification",notification.message() +"is sent to "+notification.destination());
       Notification notification1 = notificationMapper.notificationRequestDTOToNotification(notification);
       notificationRepository.save(notification1);

    }
    @KafkaListener(topics = "notification",groupId = "foo")
    public void listenGroupFoo(String message) {
        System.out.println("Received Message in group foo: " + message);
    }

    @Override
    public void sendEmail(Email email) {
        emailAPI.sendEmail(email.To(), email.subject(), email.content());
    }

    @Override
    public void sendSMS(SMS sms) {
        smsAPI.send(sms.To(), sms.content());
    }

    @Override
    public void deleteNotifcation(String id){
        notificationRepository.deleteById(id);

    }

    @Override
    public NotificationResponseDTO getNotificatinById(String id) {
            Notification notification = notificationRepository.findById(id).orElse(null);
            return notificationMapper.notificationToNotficationResponseDTO(
                    notification
            );
    }

    @Override
    public List<NotificationResponseDTO> getNotificationsByUserId(Long userId) {
        return notificationRepository.findNotificationsByDestination(userId).stream()
                .map(notificationMapper::notificationToNotficationResponseDTO)
                .toList();
    }
}
