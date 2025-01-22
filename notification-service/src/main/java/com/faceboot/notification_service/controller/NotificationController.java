package com.faceboot.notification_service.controller;


import com.faceboot.notification_service.api.email.GmailApi;
import com.faceboot.notification_service.dto.Email;
import com.faceboot.notification_service.dto.NotificationRequestDTO;
import com.faceboot.notification_service.dto.SMS;
import com.faceboot.notification_service.service.NotificationService;
import com.faceboot.notification_service.service.NotificationServiceInterface;
import com.twilio.exception.ApiException;
import lombok.AllArgsConstructor;
import org.springframework.boot.actuate.web.exchanges.HttpExchange;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notification")
@AllArgsConstructor
public class NotificationController {

    private NotificationService notificationService;
    private GmailApi gmailApi;

    @PostMapping("/email")
    public ResponseEntity<String> sendEmail(@RequestBody Email email) {
        notificationService.sendEmail(email);
        return new ResponseEntity<>("succes",HttpStatusCode.valueOf(200));
    }
    @PostMapping("/sms")
    public ResponseEntity<String> sendSMS(@RequestBody SMS sms) {
        try{
            notificationService.sendSMS(sms);
            return new ResponseEntity<>("message send succesfully",HttpStatusCode.valueOf(200));
        }catch (ApiException e){
            return new ResponseEntity<>("error sending message \n Error:"+e.getMessage(),HttpStatusCode.valueOf(302));
        }
    }
    @PostMapping("/notify")
    public ResponseEntity<String> sendNotification(@RequestBody NotificationRequestDTO notificationRequestDTO) {
        notificationService.sendNotification(notificationRequestDTO);
        return new ResponseEntity<>("succes",HttpStatusCode.valueOf(200));
    }
    @GetMapping("/getNotificationById/{id}")
    public ResponseEntity<String> getNotificationById(@PathVariable("id") Long id) {
        return new ResponseEntity<>("succes",HttpStatusCode.valueOf(200));
    }
    @GetMapping("/getNotificaitonByUserId/{id}")
    public ResponseEntity<String> getNotificationByUserId(@PathVariable("id") Long id) {
        return new ResponseEntity<>("succes",HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteNotification(@PathVariable("id") String id) {
        notificationService.deleteNotifcation(id);
        return new ResponseEntity<>("succes",HttpStatusCode.valueOf(200));
    }

}
