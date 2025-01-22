package com.faceboot.notification_service.api.sms;

public interface SMSAPI {
    public void send(String phoneNumber, String message);
}
