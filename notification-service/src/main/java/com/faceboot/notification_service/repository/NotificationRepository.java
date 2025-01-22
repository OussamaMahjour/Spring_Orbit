package com.faceboot.notification_service.repository;

import com.faceboot.notification_service.entity.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface NotificationRepository extends MongoRepository<Notification, String> {
        public List<Notification> findNotificationsByDestination(Long userId);

}
