package com.faceboot.notification_service.api.sms;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


@Component

public class TwilioSMSAPI implements SMSAPI {

    @Value(value = "${twilio.account.id}")
    private String accountId;

    @Value(value = "${twilio.account.authToken}")
    private String authToken ;

    @Value(value = "${twilio.account.number}")
    private String accountNumber;




    @Override
    public void send(String phoneNumber, String message) {
        Twilio.init(accountId,authToken);
        Message Twiliomessage = Message
                .creator(
                        new PhoneNumber(phoneNumber),
                        new PhoneNumber(accountNumber),
                        message
                )
                .create();
    }
}
