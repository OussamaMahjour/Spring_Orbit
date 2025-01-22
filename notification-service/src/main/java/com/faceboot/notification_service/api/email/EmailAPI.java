package com.faceboot.notification_service.api.email;

public interface EmailAPI {

    public void sendEmail(String to, String subject, String body);
}
